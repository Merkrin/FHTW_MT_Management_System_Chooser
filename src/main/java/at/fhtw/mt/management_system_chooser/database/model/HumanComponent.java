package at.fhtw.mt.management_system_chooser.database.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "human_components")
public class HumanComponent {
    @Id
    @Column(name = "component_id")
    private Integer componentId;

    private String description;
}
