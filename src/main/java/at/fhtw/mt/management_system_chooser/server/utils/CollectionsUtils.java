package at.fhtw.mt.management_system_chooser.server.utils;

import at.fhtw.mt.management_system_chooser.database.model.QuestionAnswer;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

@UtilityClass
public class CollectionsUtils {
    /**
     * Get "classic" map size meaning number of all key-value entries.
     *
     * @param map given map
     * @return length of the map
     */
    public static int getMapLength(Map<?, ?> map) {
        return CollectionUtils.size(map);
    }

    /**
     * Get map size meaning amount of values in the values' lists.
     *
     * @param map given map with list as a value
     * @return size of the map
     */
    public static int getMapSize(Map<?, List<QuestionAnswer>> map) {
        return map.values().stream().mapToInt(List::size).sum();
    }
}
