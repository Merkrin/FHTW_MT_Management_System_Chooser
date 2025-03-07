package at.fhtw.mt.management_system_chooser.database.server.controller;

import at.fhtw.mt.management_system_chooser.database.model.RequirementQuestion;
import at.fhtw.mt.management_system_chooser.database.repository.RequirementQuestionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionsController {
    private final RequirementQuestionRepository requirementQuestionRepository;

    public QuestionsController(RequirementQuestionRepository requirementQuestionRepository) {
        this.requirementQuestionRepository = requirementQuestionRepository;
    }

    @GetMapping("/questions")
    public List<RequirementQuestion> getQuestions(){
        List<RequirementQuestion> all = requirementQuestionRepository.findAll();
        System.out.println(all.size());
        return all;
    }
}
