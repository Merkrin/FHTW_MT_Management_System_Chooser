package at.fhtw.mt.management_system_chooser.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "behaviors")
@Getter
@Setter
@NoArgsConstructor
public class Behavior {
    @Id
    @Column(name = "behavior_id")
    private Integer behaviorId;

    private String description;

    @OneToMany(mappedBy = "behavior")
    private List<RequirementQuestion> requirementQuestions;
}
