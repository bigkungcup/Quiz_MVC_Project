package sit.int202.quizjpaproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int202.quizjpaproject.entities.Answer;
import sit.int202.quizjpaproject.entities.Question;
import sit.int202.quizjpaproject.repositories.AnswerRepository;
import sit.int202.quizjpaproject.repositories.QuestionRepository;

import java.util.List;

public class TestQuizJpa {
    public static void main(String[] args) {
        //testQuiz() ;
        //testQuizRepo();
        //testQuizAdd();
        //testQuizAddAnswer();
        //testQuizBaseRepository() ;
        //testQuizDeleteQuestion() ;
        //testQuizDeleteOneAnswer() ;
//        testQuizDeleteOneAnswer2() ;
    }

    public static void testQuiz(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default") ;
        EntityManager em = emf.createEntityManager() ;
        Question question = em.find(Question.class,1) ;
        System.out.println(question);
    }

    public static void testQuizRepo(){
        QuestionRepository questionRepo = new QuestionRepository() ;
//        Question question = questionRepo.find(Question.class,1L) ;
        Question question = questionRepo.find(1L) ;
        System.out.println(question);
    }

//    public static void testQuizAdd(){
//        QuestionRepository questionRepo = new QuestionRepository() ;
//        Question question = new Question("5!",) ;
//        Answer answer = new Answer("24",0) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        answer = new Answer("120",1) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        answer = new Answer("720",0) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        if(questionRepo.save(question)){
//            System.out.println("Question is created successfully!");
//        } else {
//            System.out.println("There is something wrong for creating the Question!");
//        }
//    }

    public static void testQuizAddAnswer(){
        QuestionRepository questionRepo = new QuestionRepository() ;
        Question question = questionRepo.find(Question.class,1L) ;
        AnswerRepository answerRepo = new AnswerRepository() ;
        Answer answer = new Answer("5",0) ;
        answer.setQuestion(question);
        if(answerRepo.save(answer)){
            System.out.println("Answer is created successfully!");
        } else {
            System.out.println("There is something wrong for creating the Answer!");
        }
    }

    public  static void testQuizBaseRepository(){
        QuestionRepository questionRepo = new QuestionRepository() ;

        int countRows = questionRepo.countRows() ;
        System.out.println("-----------Count Question Rows--------------");
        System.out.println("# Question rows = "+countRows);

        List<Question> questions = questionRepo.getResultListFromNamedQuery("Question.SortByQuestion") ;
        System.out.println("-----------Get Question From Named Query-----");
        for(Question question: questions){
            System.out.println(question);
        }

        questions = questionRepo.getResultListFromQuery("SELECT q FROM Question q WHERE q.question LIKE '%2%'") ;
        System.out.println("-----------Get Question From User Query------");
        for(Question question: questions){
            System.out.println(question);
        }
    }

//    public static void testQuizDeleteQuestion(){
//        QuestionRepository questionRepo = new QuestionRepository() ;
//        Question question = new Question("5!") ;
//        Answer answer = new Answer("24",0) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        answer = new Answer("120",1) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        if(questionRepo.save(question)){
//            System.out.println("Question is created successfully!");
//        } else {
//            System.out.println("There is something wrong for creating the Question!");
//        }
//        System.out.println("New Id = "+question.getId());
//        AnswerRepository answerRepo = new AnswerRepository() ;
//        System.out.println("Answer rows = "+answerRepo.countRows());
//        System.out.println("Delete Question Id "+question.getId());
//        if(questionRepo.delete(Question.class, 1L)){
//            System.out.println("Question is deleted successfully!");
//        } else {
//            System.out.println("There is something wrong for deleting the Question!");
//        }
//        System.out.println("Answer rows = "+answerRepo.countRows());
//    }
//
//    public static void testQuizDeleteOneAnswer(){
//        QuestionRepository questionRepo = new QuestionRepository() ;
//        Question question = new Question("5!") ;
//        Answer answer = new Answer("24",0) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        answer = new Answer("120",1) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        if(questionRepo.save(question)){
//            System.out.println("Question is created successfully!");
//        } else {
//            System.out.println("There is something wrong for creating the Question!");
//        }
//        System.out.println("New Id = "+question.getId());
//        System.out.println(question);
//        AnswerRepository answerRepo = new AnswerRepository() ;
//        System.out.println("Answer rows = "+answerRepo.countRows());
//        answer = question.getAnswers().get(0) ;
//        System.out.println("Answer information before deleting");
//        System.out.println(answer);
//        System.out.println("Delete Answer at index = 0 ");
//        question.getAnswers().remove(answer) ; // or .remove(0)
//        questionRepo.update(question) ;
//        System.out.println("Answer rows = "+answerRepo.countRows());
//        System.out.println(question);
//    }
//
//    public  static void testQuizDeleteOneAnswer2(){
//        // Before running this method, remove orphanRemoval=true
//        QuestionRepository questionRepo = new QuestionRepository() ;
//        Question question = new Question("5!") ;
//        Answer answer = new Answer("24",0) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        answer = new Answer("120",1) ;
//        answer.setQuestion(question);
//        question.addAnswer(answer);
//        if(questionRepo.save(question)){
//            System.out.println("Question is created successfully!");
//        } else {
//            System.out.println("There is something wrong for creating the Question!");
//        }
//        System.out.println("New Id = "+question.getId());
//        System.out.println(question);
//        AnswerRepository answerRepo = new AnswerRepository() ;
//        System.out.println("Answer rows = "+answerRepo.countRows());
//        answer = question.getAnswers().get(0) ;
//        Question ques = null;
//        try {
//            EntityManager em = answerRepo.getEntityManager();
//            em.getTransaction().begin();
//            Answer ans = em.find(Answer.class, answer.getId());
//            Long ansId = ans.getId();
//            ques = ans.getQuestion();
//            ques.getAnswers().remove(ans);
//            em.merge(ques);
//            em.remove(ans);
//            em.getTransaction().commit();
//        } catch (Exception e){
//            System.out.println("ERROR : " +e.getMessage());
//        }
//        System.out.println("Answer rows = "+answerRepo.countRows());
//        System.out.println(ques);
//    }
}












