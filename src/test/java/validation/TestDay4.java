package validation;

import me.nicodax.day4.Day4;
import me.nicodax.day4.Section;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static me.nicodax.day4.OverlapType.CONTAINED;
import static me.nicodax.day4.OverlapType.FULL;
import static me.nicodax.day4.OverlapType.NONE;
import static me.nicodax.day4.OverlapType.PARTIAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay4 {
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day4.txt");

    @Test
    @DisplayName("Should return NONE if asked a section it does not fully contains")
    public void fullyContains_noOverlap() {
        Section A = new Section(2, 4);
        Section B = new Section(6, 8);

        assertEquals(NONE, A.contains(B));
        assertEquals(NONE, B.contains(A));

        Section C = new Section(2, 3);
        Section D = new Section(4, 5);

        assertEquals(NONE, C.contains(D));
        assertEquals(NONE, D.contains(C));
    }

    @Test
    @DisplayName("Should return PARTIAL if asked a section it only partially contains")
    public void fullyContains_partialOverlap() {
        Section A = new Section(5, 7);
        Section B = new Section(7, 9);

        assertEquals(PARTIAL, A.contains(B));
        assertEquals(PARTIAL, B.contains(A));

        Section C = new Section(2, 6);
        Section D = new Section(4, 8);

        assertEquals(PARTIAL, C.contains(D));
        assertEquals(PARTIAL, D.contains(C));
    }

    @Test
    @DisplayName("Should return FULL if asked a section it fully contains")
    public void fullyContains_fullOverlap_bInA() {
        Section A = new Section(2, 8);
        Section B = new Section(3, 7);

        assertEquals(FULL, A.contains(B));
    }

    @Test
    @DisplayName("Should return CONTAINED if asked a section fully containing it")
    public void fullyContains_fullyContained_aInB() {
        Section A = new Section(3, 7);
        Section B = new Section(2, 8);

        assertEquals(CONTAINED, A.contains(B));
    }

    @Test
    @DisplayName("Should return the number of occurences where a section in a pair fully contains the other section")
    public void getNumberOfFullyContained() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(2, day4.getPart1Solution());
    }
}
