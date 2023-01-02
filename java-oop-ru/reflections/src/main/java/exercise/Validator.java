package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static List<String> validate(Address address) {
        var result = new ArrayList<String>();
        for (Field declaredField : address.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(NotNull.class)) {
                declaredField.setAccessible(true);
                try {
                    var value = declaredField.get(address);
                    if (value == null) {
                        result.add(declaredField.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
