package sit.int202.quizjpaproject.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sit.int202.quizjpaproject.entities.User;

import java.io.IOException;

@WebFilter(filterName = "AdminOnlyFilter",
        urlPatterns = {"/manage-users"})
public class AdminOnlyFilter implements Filter {
    private FilterConfig filterConfig;
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig =config;
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if(session == null || session.getAttribute("user") == null){
            filterConfig.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
            User user = (User) session.getAttribute("user");
            if(user.getUserName().equals("admin")){
                chain.doFilter(request, response);
            }else {
                response.getWriter().write("<h1 style='color: red;'>This page is not allowed!</h1>");
            }
        }
    }
}
