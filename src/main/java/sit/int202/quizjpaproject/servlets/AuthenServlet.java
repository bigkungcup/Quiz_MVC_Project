package sit.int202.quizjpaproject.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.quizjpaproject.entities.User;
import sit.int202.quizjpaproject.repositories.UserRepository;

import java.io.IOException;
import java.security.MessageDigest;

@WebServlet(name = "AuthenServlet", value = "/authen")
public class AuthenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }
        }
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");

        if (username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0) {
            UserRepository userRepo = new UserRepository();
            User user = userRepo.find(username);

            if (user != null) {
                if (sha256(password).equals(user.getPassword())) {
                    HttpSession session = request.getSession(true);
                    if (session.getAttribute("user") == null) {
                        session.setAttribute("user", user);
                    }
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }
            }
        }
        response.sendRedirect("login.jsp");

    }

    private String sha256(final String base) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
