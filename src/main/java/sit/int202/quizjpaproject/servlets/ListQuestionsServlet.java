package sit.int202.quizjpaproject.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.quizjpaproject.entities.Question;
import sit.int202.quizjpaproject.entities.User;
import sit.int202.quizjpaproject.repositories.QuestionRepository;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListQuestionsServlet", value = "/list-questions")
public class ListQuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
//        if(session == null || session.getAttribute("user") == null ){
//            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
//            return;
//        }

        User user = (User) session.getAttribute("user");

        QuestionRepository questionRepo = new QuestionRepository() ;
        List<Question> questions = questionRepo.getResultListFromNamedQueryWithParam("Question.QuestionsByUserName", user.getUserName());
        request.setAttribute("questions", questions);
        getServletContext().getRequestDispatcher("/list_questions.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

