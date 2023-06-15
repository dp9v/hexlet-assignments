package exercise.servlet;

import exercise.TemplateEngineUtil;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                try {
                    showArticles(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
        throws IOException, ServletException, SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        var page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int offset = (page - 1) * 10;
        String sql = "SELECT id, title, body FROM articles ORDER BY id LIMIT 10 OFFSET ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, offset);
        ResultSet resultSet = statement.executeQuery();
        var articles = new ArrayList<Map<String, String>>();

        // Вывод списка статей
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String body = resultSet.getString("body");
            articles.add(Map.of(
                "id", id,
                "title", title,
                "body", body
            ));
        }

        request.setAttribute("articles", articles);
        request.setAttribute("prevPage", page - 1);
        request.setAttribute("nextPage", page + 1);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
        throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String id = getId(request);
        Map<String, String> article = new HashMap<>();
        String queryMaxId = "SELECT MAX(id) FROM articles";
        int maxId;
        try {
            var statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queryMaxId);
            result.next();
            maxId = result.getInt("max(id)");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        assert id != null;
        if (Integer.parseInt(id) > maxId) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        String query = "SELECT title, body FROM articles WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                article.put("title", rs.getString("title"));
                article.put("body", rs.getString("body"));
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("article", article);

        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
