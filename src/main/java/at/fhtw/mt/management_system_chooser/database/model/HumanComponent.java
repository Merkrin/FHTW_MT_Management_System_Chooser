package at.fhtw.mt.management_system_chooser.database.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "human_components")
@Getter
@Setter
@NoArgsConstructor
public class HumanComponent {
    @Id
    @Column(name = "component_id")
    private Integer componentId;

    private String description;

    @OneToMany(mappedBy = "humanComponent")
    private List<RequirementQuestion> requirementQuestions;
}
