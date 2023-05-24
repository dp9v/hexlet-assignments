package exercise.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<Map<String, String>> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        var content = new String(Files.readAllBytes(Paths.get("src/main/resources/users.json")));
        return new ObjectMapper().readValue(content, List.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
        throws IOException {

        StringBuilder result = new StringBuilder();
        result.append("<table><tr>");

        getUsers().stream()
            .map(u -> "<td>" + u.get("id") + "</td><td><a href=\"/users/" + u.get("id") + "\">" + u.get("firstName") + " " + u.get("lastName") + "</a></td>")
            .forEach(result::append);
        result.append("<table><tr>");

        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println(result);
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
        throws IOException {

        // BEGIN
        var userOpt = getUsers().stream()
            .filter(u -> u.get("id").equals(id))
            .findFirst();

        if (userOpt.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("Not Found");
            return;
        }

        var user = userOpt.get();
        response.getWriter().println(user);
    }
}
