package at.fhtw.mt.management_system_chooser.database.server.controller;

import at.fhtw.mt.management_system_chooser.database.model.RequirementQuestion;
import at.fhtw.mt.management_system_chooser.database.repository.RequirementQuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuestionsController {
    private final RequirementQuestionRepository requirementQuestionRepository;

    public QuestionsController(RequirementQuestionRepository requirementQuestionRepository) {
        this.requirementQuestionRepository = requirementQuestionRepository;
    }

    @GetMapping("/")
    public String getSystemElementsPage() {
        return "system_elements";
    }

    @PostMapping("/questions")
    public List<RequirementQuestion> getQuestions(){
        List<RequirementQuestion> all = requirementQuestionRepository.findAll();
        System.out.println(all.size());
        return all;
    }
}
