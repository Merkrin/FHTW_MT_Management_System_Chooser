package at.fhtw.mt.management_system_chooser.server.service;

import at.fhtw.mt.management_system_chooser.database.model.Behavior;
import at.fhtw.mt.management_system_chooser.database.model.HumanComponent;
import at.fhtw.mt.management_system_chooser.database.model.QuestionAnswer;
import at.fhtw.mt.management_system_chooser.database.model.RequirementQuestion;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class QuestionsMapperService {
    protected static final Logger logger = LogManager.getLogger();

    public Map<HumanComponent, Map<Behavior, List<RequirementQuestion>>> getQuestionsByComponentsAndBehaviors(
            List<RequirementQuestion> requirementQuestions) {
        logger.debug("Got {} questions to process", requirementQuestions.size());

        Map<HumanComponent, Map<Behavior, List<RequirementQuestion>>> result = new HashMap<>();

        for (RequirementQuestion requirementQuestion : requirementQuestions) {
            if (requirementQuestion != null) {
                logger.debug("Question {} for human component {} and behavior {}",
                             requirementQuestion.getRequirementId(),
                             requirementQuestion.getHumanComponent().getDescription(),
                             requirementQuestion.getBehavior().getDescription());

                putResultValue(result, requirementQuestion);
            } else {
                throw new RuntimeException("Requirement question is null");
            }
        }

        return result;
    }

    public Map<String, List<QuestionAnswer>> getQuestionAnswers(List<RequirementQuestion> requirementQuestions) {
        return requirementQuestions.stream().collect(
                Collectors.toMap(RequirementQuestion::getRequirementId, RequirementQuestion::getQuestionAnswers));
    }

    private void putResultValue(Map<HumanComponent, Map<Behavior, List<RequirementQuestion>>> result,
                                RequirementQuestion requirementQuestion) {
        if (requirementQuestion.getHumanComponent() != null && requirementQuestion.getBehavior() != null) {
            if (!result.containsKey(requirementQuestion.getHumanComponent())) {
                logger.debug("No results for human component {} contained yet, create new entry...",
                             requirementQuestion.getHumanComponent().getDescription());

                List<RequirementQuestion> requirementQuestions = new ArrayList<>();
                requirementQuestions.add(requirementQuestion);

                Map<Behavior, List<RequirementQuestion>> requirementQuestionsByBehavior = new HashMap<>();
                requirementQuestionsByBehavior.put(requirementQuestion.getBehavior(), requirementQuestions);

                result.put(requirementQuestion.getHumanComponent(), requirementQuestionsByBehavior);
            } else {
                if (!result.get(requirementQuestion.getHumanComponent())
                        .containsKey(requirementQuestion.getBehavior())) {
                    logger.debug("No results for behavior {} in component {} contained yet, create new entry...",
                                 requirementQuestion.getBehavior().getDescription(),
                                 requirementQuestion.getHumanComponent().getDescription());

                    List<RequirementQuestion> requirementQuestions = new ArrayList<>();
                    requirementQuestions.add(requirementQuestion);

                    result.get(requirementQuestion.getHumanComponent())
                            .put(requirementQuestion.getBehavior(), requirementQuestions);
                } else {
                    logger.debug("Add question into existing entry.");

                    result.get(requirementQuestion.getHumanComponent()).get(requirementQuestion.getBehavior())
                            .add(requirementQuestion);
                }
            }
        } else {
            throw new RuntimeException("One of requirement question's elements is null");
        }
    }
}
