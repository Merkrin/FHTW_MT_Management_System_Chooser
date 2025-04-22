package at.fhtw.mt.management_system_chooser.server.service;

import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Service
public class ResultCounterService {
    protected static final Logger logger = LogManager.getLogger();

    public Map<String, Integer> getResultsBySystems(Map<String, String> selectedAnswers){
        logger.info("Started results counting.");

        Map<String, Integer> results = new HashMap<>();

        for (Map.Entry<String, String> entry : selectedAnswers.entrySet()){
            String key = entry.getKey();      // example: "SystemA_RQ001_3"
            String value = entry.getValue();  // example: "12_4_3"

            logger.debug("{}.{} rating counter start...", key, value);

            String[] keyParts = key.split("_");

            if(keyParts.length == 3){
                String system = keyParts[0];

                int rating;
                try {
                    rating = Integer.parseInt(keyParts[2]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }

                String[] valueParts = value.split("_");

                if (valueParts.length == 3){
                    int answerValue;
                    try {
                        answerValue = Integer.parseInt(valueParts[1]);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }

                    int result = answerValue * rating;
                    logger.debug("{}: {}", system, result);
                    results.put(system, results.getOrDefault(system, 0) + result);
                }
            }
        }

        return results;
    }
}
