package exercise;

import java.lang.reflect.Proxy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
@Slf4j
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, String> classesToLogging = new HashMap<>();
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            var level = bean.getClass().getAnnotation(Inspect.class).level();
            classesToLogging.put(beanName, level);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!classesToLogging.containsKey(beanName)) {
            return bean;
        }
        return Proxy.newProxyInstance(
            bean.getClass().getClassLoader(),
            bean.getClass().getInterfaces(),
            (proxy, method, args) -> {
                var level = classesToLogging.get(beanName);
                log.atLevel(Level.valueOf(level.toUpperCase()))
                    .log("Was called method: {}() with arguments: {}", method.getName(), args);
                return method.invoke(bean, args);
            }
        );
    }
}
// END
