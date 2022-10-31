package sit.int202.quizjpaproject.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sit.int202.quizjpaproject.entities.User;
import sit.int202.quizjpaproject.repositories.UserRepository;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

@WebServlet(name = "ManageUserServlet", value = "/manage-users")
public class ManageUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        String action = request.getParameter("action");

        UserRepository userRepo = new UserRepository();

        if (strId != null && strId.trim().length() > 0) {
            if (strId.equals("new")) {
                request.setAttribute("id", "new");
                getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
                return;
            } else {
                try {
                    if (action != null && action.equals("remove-user")) {
                        if (userRepo.delete(strId)) {
                            System.out.println("User " + strId + " is removed successfully!");
                            response.sendRedirect("manage-users");
                            return;
                        }
                    } else if(action != null && action.equals("edit-user")){
                        User user = userRepo.find(strId);
                        request.setAttribute("user", user);
                        getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
                        return;
                    } else if (action != null && action.equals("edit")) {
                        User user = userRepo.find(strId);
                        request.setAttribute("user", user);
                        request.setAttribute("action", action);
                        getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
                        return;
                    } else {
                        User user = userRepo.find(strId);
                        if (user != null) {
//                            Cookie lastUser = new Cookie("lastuser", "manage-users?id=" + strId);
//                            lastUser.setMaxAge(7 * 24 * 60 * 60);
//                            response.addCookie(lastUser);
                            request.setAttribute("user", user);
                            getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
                            return;
                        }
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Error : Number parsing!");
                }
            }
        } else {
            List<User> usersList = userRepo.findAll();
            request.setAttribute("users", usersList);
            getServletContext().getRequestDispatcher("/list_users.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("/manage-user");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String strId = request.getParameter("id");
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");

        if (strId != null) {

            UserRepository userRepo = new UserRepository();
            User user;

            if (action != null && action.trim().equals("add-user") && username != null && username.trim().length() > 0) {
                user = new User(username,sha256(password),firstname,lastname,email);
                if(userRepo.save(user)){
                    System.out.println("User is created successfully!");
                    strId = user.getUserName();
                }
            }else if(action != null && action.trim().equals("edit-user") && strId.trim().length() > 0){
                user = userRepo.find(strId) ;
                if(user != null){
                    user.setFirstName(firstname);
                    user.setLastName(lastname);
                    user.setEmail(email);
                    if(userRepo.update(user)){
                        System.out.println("User is updated successfully!");
                        strId = user.getUserName();
                    }
                }
            }
        }
        response.sendRedirect("manage-users?id=" + strId);
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
