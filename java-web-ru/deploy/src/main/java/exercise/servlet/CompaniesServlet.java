package exercise.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Collectors;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        var serachString = Optional.ofNullable(request.getParameter("search")).orElse("");

        var filteredCompanies = getCompanies()
            .stream()
            .filter(c -> c.contains(serachString))
            .collect(Collectors.toList());

        if (filteredCompanies.isEmpty()) {
            out.println("Companies not found");
            return;
        }
        filteredCompanies.forEach(out::println);
    }
}
