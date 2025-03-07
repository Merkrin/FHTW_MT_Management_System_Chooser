package at.fhtw.mt.management_system_chooser.database.repository;

import at.fhtw.mt.management_system_chooser.database.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {
}
