package at.fhtw.mt.management_system_chooser.database.repository;

import at.fhtw.mt.management_system_chooser.database.model.RequirementQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementQuestionRepository extends JpaRepository<RequirementQuestion, String> {
}
