package at.fhtw.mt.management_system_chooser.database.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question_answers")
public class QuestionAnswer {
    @Id
    @Column(name = "answer_id")
    private Integer answerId;

    @ManyToOne
    @JoinColumn(name = "requirement_question_id")
    private RequirementQuestion requirementQuestion;

    private String description;
}
