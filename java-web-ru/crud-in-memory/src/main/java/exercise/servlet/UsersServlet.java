package exercise.servlet;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static exercise.Data.getNextId;
import static exercise.Data.getUsers;

public class UsersServlet extends HttpServlet {

    private List<Map<String, String>> users = getUsers();

    private String getId(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, "");
    }

    private Map<String, String> getUserById(String id) {
        Map<String, String> user = users
            .stream()
            .filter(u -> u.get("id").equals(id))
            .findAny()
            .orElse(null);

        return user;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showUsers(request, response);
                break;
            case "new":
                newUser(request, response);
                break;
            case "edit":
                editUser(request, response);
                break;
            case "show":
                showUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
        throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "new":
                createUser(request, response);
                break;
            case "edit":
                updateUser(request, response);
                break;
            case "delete":
                destroyUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
        throws IOException, ServletException {

        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);
    }


    private void showUser(HttpServletRequest request,
                          HttpServletResponse response)
        throws IOException, ServletException {
        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(request, response);
    }

    private void newUser(HttpServletRequest request,
                         HttpServletResponse response)
        throws IOException, ServletException {

        // BEGIN

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void createUser(HttpServletRequest request,
                            HttpServletResponse response)
        throws IOException, ServletException {

        // BEGIN
        var errors = new HashMap<String, String>();
        var newUser = new HashMap<String, String>();
        newUser.put("firstName", request.getParameter("firstName"));
        newUser.put("lastName", request.getParameter("lastName"));
        newUser.put("email", request.getParameter("email"));

        if (StringUtils.isBlank(newUser.get("firstName"))) {
            errors.put("firstName", "firstName is empty");
        }

        if (StringUtils.isBlank(newUser.get("lastName"))) {
            errors.put("lastName", "lastName is empty");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("user", newUser);
            request.setAttribute("errors", errors);
            response.setStatus(422);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        newUser.put("id", getNextId());

        users.add(newUser);
        // END
    }

    private void editUser(HttpServletRequest request,
                          HttpServletResponse response)
        throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void updateUser(HttpServletRequest request,
                            HttpServletResponse response)
        throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN

        // BEGIN
        var errors = new HashMap<String, String>();
        user.put("firstName", request.getParameter("firstName"));
        user.put("lastName", request.getParameter("lastName"));
        user.put("email", request.getParameter("email"));

        if (StringUtils.isBlank(user.get("firstName"))) {
            errors.put("firstName", "firstName is empty");
        }

        if (StringUtils.isBlank(user.get("lastName"))) {
            errors.put("lastName", "lastName is empty");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("user", user);
            request.setAttribute("errors", errors);
            response.setStatus(422);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        users.remove(user);
        users.add(user);
        // END
    }

    private void deleteUser(HttpServletRequest request,
                            HttpServletResponse response)
        throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.jsp");
        requestDispatcher.forward(request, response);

    }

    private void destroyUser(HttpServletRequest request,
                             HttpServletResponse response)
        throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        users.remove(user);
        response.sendRedirect("/users");
    }
}
