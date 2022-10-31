package sit.int202.quizjpaproject.servlets;

import jakarta.persistence.EntityManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.quizjpaproject.entities.Answer;
import sit.int202.quizjpaproject.entities.Question;
import sit.int202.quizjpaproject.entities.User;
import sit.int202.quizjpaproject.repositories.AnswerRepository;
import sit.int202.quizjpaproject.repositories.QuestionRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageQuestionServlet", value = "/manage-question")
public class ManageQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id") ;
        String action = request.getParameter("action") ;
        if(strId != null & strId.trim().length()>0){

            QuestionRepository questionRepo = new QuestionRepository() ;

            if(strId.equals("new")){
                request.setAttribute("id","new");
                getServletContext().getRequestDispatcher("/question.jsp").forward(request,response);
                return ;
            } else {
                try{
                    Long id = Long.parseLong(strId) ;

                    if(action != null && action.equals(("remove-question"))){
                        if(questionRepo.delete(id)){
                            System.out.println("Question "+id+" is removed successfully!");
                            response.sendRedirect("list-questions");
                            return ;
                        }
                    } else if(action != null && action.equals("remove-answer")){
                        AnswerRepository answerRepo = new AnswerRepository() ;
                        Answer answer = answerRepo.find(id) ;
                        Question question = answer.getQuestion() ;
                        Long qid = question.getId() ;
                        System.out.println(" # answers = "+question.getAnswers().size());
                        question.getAnswers().remove(answer) ;
                        System.out.println(" # answers = "+question.getAnswers().size());
                        if(questionRepo.update(question)){
                            System.out.println("Answer "+id+" is removed successfully!");
                            response.sendRedirect("manage-question?id="+qid);
                            return ;
                        }
                    } else {
                        Question question = questionRepo.find(id);
                        if (question != null) {
                            Cookie lastQuestion = new Cookie("lastquestion", "manage-question?id=" + id);
                            lastQuestion.setMaxAge(7 * 24 * 60 * 60);
                            response.addCookie(lastQuestion);
                            request.setAttribute("question", question);
                            getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
                            return;
                        }
                    }

                } catch (NumberFormatException nfe){
                    System.out.println("Error : Number parsing!");
                }
            }
        }
        response.sendRedirect("/list-questions");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id") ;
        String action = request.getParameter("action") ;
        String text = request.getParameter("text") ;

        Long id;
        try {
            id = Long.parseLong(strId);
        } catch (NumberFormatException nfe) {
            id = -1L;
        }

        if(strId != null) {

            QuestionRepository questionRepo = new QuestionRepository();
            Question question;

            if (action != null && action.trim().equals("add-question") && text != null && text.trim().length() > 0) {

                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("user");

                question = new Question(text,user.getUserName());
                if (questionRepo.save(question)) {
                    System.out.println("Question is created successfully!");
                    id = question.getId() ;
                }
            } else if (action != null && action.trim().equals("add-answer") && text != null && text.trim().length() > 0) {
                String correct = request.getParameter("isCorrect");

                int isCorrect;
                if (correct != null) {
                    isCorrect = 1;
                } else {
                    isCorrect = 0;
                }

                question = questionRepo.find(id) ;
                if(question != null){
                    AnswerRepository answerRepo = new AnswerRepository() ;
                    Answer answer = new Answer(text,isCorrect) ;
                    answer.setQuestion(question);
                    answerRepo.save(answer) ;
                }
            } else if (action != null && text != null && text.length() > 0 && action.equals("edit-question")) {
                question = questionRepo.find(id) ;
                if( question != null){
                    question.setQuestion(text);
                    if(questionRepo.update(question)){
                        System.out.println("Question is updated successfully!");
                    }
                }
            } else if (action != null && text != null && text.length() > 0 && action.equals("edit-answer")) {
                String correct = request.getParameter("isCorrect");
                String oldText = request.getParameter("oldText") ;

                int isCorrect ;
                if(correct != null){
                    isCorrect = 1 ;
                } else {
                    isCorrect = 0 ;
                }
                AnswerRepository answerRepo = new AnswerRepository() ;
                Answer answer = answerRepo.find(id) ;
                answer.setAnswer(text);
                answer.setIsCorrect(isCorrect);
                id = answer.getQuestion().getId() ;
                if(answerRepo.update(answer)){
                    System.out.println("Answer is updated successfully!");
                }
            }

        }

        response.sendRedirect("manage-question?id="+id);
    }
}
