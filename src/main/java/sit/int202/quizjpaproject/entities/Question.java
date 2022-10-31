package sit.int202.quizjpaproject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor()
@Entity
@Table(name = "questions")
@NamedQueries({
        @NamedQuery(name = "Question.SortByQuestion", query = "SELECT q FROM Question q ORDER BY q.question DESC"),
        @NamedQuery(name = "Question.SearchByQuestion", query = "SELECT q FROM Question q WHERE q.question = :param"),
        @NamedQuery(name = "Question.QuestionsByUserName", query = "SELECT q FROM Question q WHERE q.username = :param" )
})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String question;

    @NonNull
    private String username;

    @OneToMany(mappedBy = "question",orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>(10);

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +", answers='"+answers+"'"+
                '}';
    }

    public void addAnswer(Answer answer){
        answers.add(answer) ;
    }
}
















