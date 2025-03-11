package at.fhtw.mt.management_system_chooser.server.controller;

import at.fhtw.mt.management_system_chooser.database.model.Behavior;
import at.fhtw.mt.management_system_chooser.database.model.HumanComponent;
import at.fhtw.mt.management_system_chooser.database.model.QuestionAnswer;
import at.fhtw.mt.management_system_chooser.database.model.RequirementQuestion;
import at.fhtw.mt.management_system_chooser.database.repository.QuestionAnswerRepository;
import at.fhtw.mt.management_system_chooser.database.repository.RequirementQuestionRepository;
import at.fhtw.mt.management_system_chooser.server.service.QuestionsMapperService;
import at.fhtw.mt.management_system_chooser.server.utils.CollectionsUtils;
import jakarta.servlet.http.HttpSession;
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
    private final QuestionsMapperService questionsMapperService;

    public QuestionsController(RequirementQuestionRepository requirementQuestionRepository,
                               QuestionsMapperService questionsMapperService) {
        this.requirementQuestionRepository = requirementQuestionRepository;
        this.questionsMapperService = questionsMapperService;
    }

    @GetMapping("/")
    public String getSystemElementsPage() {
        logger.info("Opened index page");

        return "system_elements";
    }

    @PostMapping("/questions")
    public String getQuestions(@RequestParam List<String> systems, Model model, HttpSession session) {
        logger.info("Started questions loading...");

        session.removeAttribute("previousAnswers");
        session.setAttribute("systems", systems);

        List<RequirementQuestion> requirementQuestions = requirementQuestionRepository.findAll();
        logger.debug("Loaded {} questions for display", requirementQuestions.size());

        Map<HumanComponent, Map<Behavior, List<RequirementQuestion>>> questionsByComponentsAndBehaviors = questionsMapperService.getQuestionsByComponentsAndBehaviors(
                requirementQuestions);

        Map<String, List<QuestionAnswer>> questionAnswers = questionsMapperService.getQuestionAnswers(requirementQuestions);
        logger.debug("Loaded {} answer lists for display, {} answers in total",
                     CollectionsUtils.getMapLength(questionAnswers), CollectionsUtils.getMapSize(questionAnswers));

        model.addAttribute("systems", systems);
        model.addAttribute("questionsMap", questionsByComponentsAndBehaviors);
        model.addAttribute("answersMap", questionAnswers);

        return "requirements_questions";
    }

    @GetMapping("/questions")
    public String showQuestions(Model model, HttpSession session) {
        Map<String, String> previousAnswers = (Map<String, String>) session.getAttribute("previousAnswers");
        model.addAttribute("previousAnswers", previousAnswers);

        List<RequirementQuestion> requirementQuestions = requirementQuestionRepository.findAll();
        Map<HumanComponent, Map<Behavior, List<RequirementQuestion>>> questionsByComponentsAndBehaviors = questionsMapperService.getQuestionsByComponentsAndBehaviors(
                requirementQuestions);
        Map<String, List<QuestionAnswer>> questionAnswers = questionsMapperService.getQuestionAnswers(requirementQuestions);
        model.addAttribute("questionsMap", questionsByComponentsAndBehaviors);
        model.addAttribute("answersMap", questionAnswers);

        model.addAttribute("systems", session.getAttribute("systems"));

        return "requirements_questions";
    }

    @PostMapping("/submit")
    public String submitAnswers(@RequestParam Map<String, String> selectedAnswers, Model model, HttpSession session) {
        session.setAttribute("previousAnswers", selectedAnswers);


        Map<String, Long> results = selectedAnswers.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().split("_")[0],
                        Collectors.counting()
                ));

        model.addAttribute("results", results);

        return "results";
    }
}
