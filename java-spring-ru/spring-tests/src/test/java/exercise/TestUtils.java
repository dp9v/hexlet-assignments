package exercise;

import exercise.model.Person;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;


public class TestUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static int getUserIdByEmail(MockMvc mockMvc, String email) throws Exception {
        var response = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();
        var body = response.getContentAsString();

        List<Map> users = mapper.readValue(body, List.class);
        var existingUser = users.stream()
                .filter(user -> user.get("email").equals(email))
                .findAny()
                .get();

        return (int) existingUser.get("id");
    }

    public static Person getById(MockMvc mockMvc, int id) throws Exception {
        var response = mockMvc
            .perform(get("/people/{id}", id))
            .andReturn()
            .getResponse();

        if(response.getContentAsString().isEmpty()) {
            return null;
        }

        return mapper.readValue(response.getContentAsString(), Person.class);
    }
}
