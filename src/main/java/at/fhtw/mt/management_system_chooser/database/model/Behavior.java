package at.fhtw.mt.management_system_chooser.database.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "behaviors")
public class Behavior {
    @Id
    @Column(name = "behavior_id")
    private Integer behaviorId;

    private String description;
}
