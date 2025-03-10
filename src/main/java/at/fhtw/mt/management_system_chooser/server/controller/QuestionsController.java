package at.fhtw.mt.management_system_chooser.server.controller;

import at.fhtw.mt.management_system_chooser.database.model.QuestionAnswer;
import at.fhtw.mt.management_system_chooser.database.model.RequirementQuestion;
import at.fhtw.mt.management_system_chooser.database.repository.QuestionAnswerRepository;
import at.fhtw.mt.management_system_chooser.database.repository.RequirementQuestionRepository;
import at.fhtw.mt.management_system_chooser.server.utils.CollectionsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class QuestionsController {
    protected static final Logger logger = LogManager.getLogger();

    private final RequirementQuestionRepository requirementQuestionRepository;

    public QuestionsController(RequirementQuestionRepository requirementQuestionRepository) {
        this.requirementQuestionRepository = requirementQuestionRepository;
    }

    @GetMapping("/")
    public String getSystemElementsPage() {
        logger.info("Opened index page");

        return "system_elements";
    }

    @PostMapping("/questions")
    public String getQuestions(@RequestParam List<String> systems, Model model) {
        logger.info("Started questions loading...");

        List<RequirementQuestion> requirementQuestions = requirementQuestionRepository.findAll();
        logger.debug("Loaded {} questions for display", requirementQuestions.size());

        Map<String, List<QuestionAnswer>> questionAnswers = requirementQuestions.stream().collect(
                Collectors.toMap(RequirementQuestion::getRequirementId, RequirementQuestion::getQuestionAnswers));
        logger.debug("Loaded {} answer lists for display, {} answers in total",
                     CollectionsUtils.getMapLength(questionAnswers), CollectionsUtils.getMapSize(questionAnswers));

        model.addAttribute("systems", systems);
        model.addAttribute("questions", requirementQuestions);
        model.addAttribute("answersMap", questionAnswers);

        return "requirements_questions";
    }
}
