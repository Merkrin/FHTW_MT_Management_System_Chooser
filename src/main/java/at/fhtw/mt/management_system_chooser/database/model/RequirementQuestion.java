package at.fhtw.mt.management_system_chooser.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "requirement_questions")
@Getter
@Setter
@NoArgsConstructor
public class RequirementQuestion {
    @Id
    @Column(name = "requirement_id")
    private String requirementId;

    @ManyToOne
    @JoinColumn(name = "human_component_id")
    private HumanComponent humanComponent;

    @ManyToOne
    @JoinColumn(name = "behavior_id")
    private Behavior behavior;

    private String description;

    @OneToMany(mappedBy = "requirementQuestion")
    private List<QuestionAnswer> questionAnswers;
}
