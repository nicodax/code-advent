package validation;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import me.nicodax.day13.Day13;

public class TestDay13 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "test-input-day13.txt");
    
    @Test
    public void valuesAreInRightOrder_bothAreIntegers() {
        Day13 day13 = new Day13();

        assertTrue(day13.valuesAreInRightOrder(2, 5));
        assertFalse(day13.valuesAreInRightOrder(4, 3));
        assertTrue(day13.valuesAreInRightOrder(3, 3));
    }
    
    @Test
    public void valuesAreInRightOrder_bothAreLists() {
        Day13 day13 = new Day13();
        List<Integer> left1 = asList(1, 1, 3, 1, 1);
        List<Integer> right1 = asList(1, 1, 5, 1, 1);

        List<Integer> left2 = asList(2, 3, 4);
        List<Integer> right2 = asList(4);

        List<Integer> left3 = asList(7, 7, 7, 7);
        List<Integer> right3 = asList(7, 7, 7);

        assertTrue(day13.valuesAreInRightOrder(left1, right1));
        assertTrue(day13.valuesAreInRightOrder(left2, right2));
        assertFalse(day13.valuesAreInRightOrder(left3, right3));
    }

    @Test
    public void getSumOfRightOrderIndexes() {
        Day13 day13 = new Day13();
        day13.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(13, day13.getPart1Solution());
    }
}
