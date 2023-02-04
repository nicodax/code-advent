package me.nicodax.day9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RopeTest {
    private final Integer PART_1_ROPE_LENGTH = 2;

    @Test
    @DisplayName("It should return the number of unique positions the tail visited")
    public void getNumberOfPositionsTailVisitedAtLeastOnce() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        Position position1 = new Position(1, 0);
        Position position2 = new Position(1, 1);
        Position position3 = new Position(0, 1);
        Position position4 = new Position(0, 0);
        Position position5 = new Position(1, 0);
        rope.addTailPosition(position1);
        rope.addTailPosition(position2);
        rope.addTailPosition(position3);
        rope.addTailPosition(position4);
        rope.addTailPosition(position5);
        // position0 (0, 0), position1 (0, 1), position2 (1, 1) and position3 (1, 0)
        // other positions are duplicates
        Integer expectedNumberOfUniquePositions = 4;

        assertEquals(expectedNumberOfUniquePositions, rope.getNumberOfPositionsTailVisitedAtLeastOnce());
    }

    @Test
    @DisplayName("It should move a given standard section to a new position")
    public void moveSection_neitherHeadNorTail() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH + 1);
        int sectionIndex = 1; // middle section index in this case
        Integer xModifier = 1;
        Integer yModifier = 1;
        rope.moveSection(sectionIndex, xModifier, yModifier);

        assertEquals(xModifier, rope.getSectionPositionList().get(sectionIndex).getX()); // started at (0, 0)
        assertEquals(yModifier, rope.getSectionPositionList().get(sectionIndex).getY()); // started at (0, 0)
        assertEquals(1, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // tail did not move
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // tail did not move
    }

    @Test
    @DisplayName("It should move a given tail section to a new position and save the newly visited position")
    public void moveSection_tail() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        int sectionIndex = 1; // tail section index in this case
        Integer xModifier = 1;
        Integer yModifier = 1;
        rope.moveSection(sectionIndex, xModifier, yModifier);

        assertEquals(xModifier, rope.getSectionPositionList().get(sectionIndex).getX()); // started at (0, 0)
        assertEquals(yModifier, rope.getSectionPositionList().get(sectionIndex).getY()); // started at (0, 0)
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial tail position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial tail position
        assertEquals(xModifier, rope.getTailPositionList().get(1).getX()); // new tail position
        assertEquals(yModifier, rope.getTailPositionList().get(1).getY()); // new tail position
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - no movement necessary")
    public void followPreviousSection_2SectionLongRope_NONE() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(1, 0)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(1, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // did not move
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(sectionIndex).getX()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(sectionIndex).getY()); // did not move
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving up")
    public void followPreviousSection_2SectionLongRope_UP() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(0, 2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(0, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(0, rope.getSectionPositionList().get(sectionIndex).getX()); // moved up
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved up
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving up-right (1)")
    public void followPreviousSection_2SectionLongRope_UP_RIGHT_1() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(1, 2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved up-right
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved up-right
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving up-right (2)")
    public void followPreviousSection_2SectionLongRope_UP_RIGHT_2() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(2, 2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved up-right
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved up-right
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving up-right (3)")
    public void followPreviousSection_2SectionLongRope_UP_RIGHT_3() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(2, 1)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved up-right
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved up-right
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving right")
    public void followPreviousSection_2SectionLongRope_RIGHT() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(2, 0)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(0, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved right
        assertEquals(0, rope.getSectionPositionList().get(sectionIndex).getY()); // moved right
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving down-right (1)")
    public void followPreviousSection_2SectionLongRope_DOWN_RIGHT_1() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(1, -2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(-1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved down-right
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved down-right
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving down-right (2)")
    public void followPreviousSection_2SectionLongRope_DOWN_RIGHT_2() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(2, -2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(-1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved down-right
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved down-right
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving down-right (3)")
    public void followPreviousSection_2SectionLongRope_DOWN_RIGHT_3() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(2, -1)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(-1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved down-right
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved down-right
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving down")
    public void followPreviousSection_2SectionLongRope_DOWN() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(0, -2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(0, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(-1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(0, rope.getSectionPositionList().get(sectionIndex).getX()); // moved down
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved down
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving down-left (1)")
    public void followPreviousSection_2SectionLongRope_DOWN_LEFT_1() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(-1, -2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(-1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(-1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved down-left
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved down-left
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving down-left (2)")
    public void followPreviousSection_2SectionLongRope_DOWN_LEFT_2() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(-2, -2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(-1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(-1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved down-left
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved down-left
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving down-left (3)")
    public void followPreviousSection_2SectionLongRope_DOWN_LEFT_3() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(-2, -1)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(-1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(-1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved down-left
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved down-left
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving left")
    public void followPreviousSection_2SectionLongRope_LEFT() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(-2, 0)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(-1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(0, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved left
        assertEquals(0, rope.getSectionPositionList().get(sectionIndex).getY()); // moved left
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving up-left (1)")
    public void followPreviousSection_2SectionLongRope_UP_LEFT_1() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(-2, 1)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(-1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved up-left
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved up-left
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving up-left (2)")
    public void followPreviousSection_2SectionLongRope_UP_LEFT_2() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(-2, 2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(-1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved up-left
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved up-left
    }

    @Test
    @DisplayName("following a head section movement, it should move the tail accordingly - moving up-left (3)")
    public void followPreviousSection_2SectionLongRope_UP_LEFT_3() {
        Rope rope = new Rope(PART_1_ROPE_LENGTH);
        rope.setSectionPosition(0, new Position(-1, 2)); // head section position (always)
        int sectionIndex = 1; // tail section index in this case
        rope.followPreviousSection(sectionIndex);

        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // initial position
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // initial position
        assertEquals(-1, rope.getTailPositionList().get(1).getX()); // new position
        assertEquals(1, rope.getTailPositionList().get(1).getY()); // new position
        assertEquals(-1, rope.getSectionPositionList().get(sectionIndex).getX()); // moved up-left
        assertEquals(1, rope.getSectionPositionList().get(sectionIndex).getY()); // moved up-left
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly "
            + "- no movement necessary")
    public void followPreviousSection_5SectionLongRope_NONE() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(4, 0));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(3, 0));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(2, 0));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(1, 0));
        rope.followPreviousSection(SECTION_2_INDEX);

        assertEquals(1, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX()); // did not move
        assertEquals(0, rope.getTailPositionList().get(0).getY()); // did not move
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(3, rope.getSectionPositionList().get(SECTION_2_INDEX).getX()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(SECTION_2_INDEX).getY()); // did not move
        assertEquals(2, rope.getSectionPositionList().get(SECTION_3_INDEX).getX()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(SECTION_3_INDEX).getY()); // did not move
        assertEquals(1, rope.getSectionPositionList().get(SECTION_4_INDEX).getX()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(SECTION_4_INDEX).getY()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getX()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getY()); // did not move
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - moving up")
    public void followPreviousSection_5SectionLongRope_UP() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(0, 5));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(0, 3));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(0, 2));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(0, 1));
        rope.followPreviousSection(SECTION_2_INDEX);

        // ROPE MOVED UP
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(0, rope.getTailPositionList().get(1).getX());
        assertEquals(1, rope.getTailPositionList().get(1).getY());
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_2_INDEX).getX());
        assertEquals(4, rope.getSectionPositionList().get(SECTION_2_INDEX).getY());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_3_INDEX).getX());
        assertEquals(3, rope.getSectionPositionList().get(SECTION_3_INDEX).getY());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_4_INDEX).getX());
        assertEquals(2, rope.getSectionPositionList().get(SECTION_4_INDEX).getY());
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getX());
        assertEquals(1, rope.getSectionPositionList().get(TAIL_INDEX).getY());
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - "
            + "moving up-right")
    public void followPreviousSection_5SectionLongRope_UP_RIGHT() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(5, 5));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(3, 3));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(2, 2));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(1, 1));
        rope.followPreviousSection(SECTION_2_INDEX);

        // ROPE MOVED UP-RIGHT
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(1, rope.getTailPositionList().get(1).getX());
        assertEquals(1, rope.getTailPositionList().get(1).getY());
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(4, rope.getSectionPositionList().get(SECTION_2_INDEX).getX());
        assertEquals(4, rope.getSectionPositionList().get(SECTION_2_INDEX).getY());
        assertEquals(3, rope.getSectionPositionList().get(SECTION_3_INDEX).getX());
        assertEquals(3, rope.getSectionPositionList().get(SECTION_3_INDEX).getY());
        assertEquals(2, rope.getSectionPositionList().get(SECTION_4_INDEX).getX());
        assertEquals(2, rope.getSectionPositionList().get(SECTION_4_INDEX).getY());
        assertEquals(1, rope.getSectionPositionList().get(TAIL_INDEX).getX());
        assertEquals(1, rope.getSectionPositionList().get(TAIL_INDEX).getY());
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - moving right")
    public void followPreviousSection_5SectionLongRope_RIGHT() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(5, 0));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(3, 0));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(2, 0));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(1, 0));
        rope.followPreviousSection(SECTION_2_INDEX);

        // ROPE MOVED RIGHT
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(1, rope.getTailPositionList().get(1).getX());
        assertEquals(0, rope.getTailPositionList().get(1).getY());
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(4, rope.getSectionPositionList().get(SECTION_2_INDEX).getX());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_2_INDEX).getY());
        assertEquals(3, rope.getSectionPositionList().get(SECTION_3_INDEX).getX());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_3_INDEX).getY());
        assertEquals(2, rope.getSectionPositionList().get(SECTION_4_INDEX).getX());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_4_INDEX).getY());
        assertEquals(1, rope.getSectionPositionList().get(TAIL_INDEX).getX());
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getY());
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - "
            + "moving down-right")
    public void followPreviousSection_5SectionLongRope_DOWN_RIGHT() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(5, -5));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(3, -3));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(2, -2));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(1, -1));
        rope.followPreviousSection(SECTION_2_INDEX);

        // ROPE MOVED DOWN-RIGHT
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(1, rope.getTailPositionList().get(1).getX());
        assertEquals(-1, rope.getTailPositionList().get(1).getY());
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(4, rope.getSectionPositionList().get(SECTION_2_INDEX).getX());
        assertEquals(-4, rope.getSectionPositionList().get(SECTION_2_INDEX).getY());
        assertEquals(3, rope.getSectionPositionList().get(SECTION_3_INDEX).getX());
        assertEquals(-3, rope.getSectionPositionList().get(SECTION_3_INDEX).getY());
        assertEquals(2, rope.getSectionPositionList().get(SECTION_4_INDEX).getX());
        assertEquals(-2, rope.getSectionPositionList().get(SECTION_4_INDEX).getY());
        assertEquals(1, rope.getSectionPositionList().get(TAIL_INDEX).getX());
        assertEquals(-1, rope.getSectionPositionList().get(TAIL_INDEX).getY());
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - moving down")
    public void followPreviousSection_5SectionLongRope_DOWN() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(0, -5));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(0, -3));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(0, -2));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(0, -1));
        rope.followPreviousSection(SECTION_2_INDEX);

        // ROPE MOVED DOWN
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(0, rope.getTailPositionList().get(1).getX());
        assertEquals(-1, rope.getTailPositionList().get(1).getY());
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_2_INDEX).getX());
        assertEquals(-4, rope.getSectionPositionList().get(SECTION_2_INDEX).getY());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_3_INDEX).getX());
        assertEquals(-3, rope.getSectionPositionList().get(SECTION_3_INDEX).getY());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_4_INDEX).getX());
        assertEquals(-2, rope.getSectionPositionList().get(SECTION_4_INDEX).getY());
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getX());
        assertEquals(-1, rope.getSectionPositionList().get(TAIL_INDEX).getY());
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - "
            + "moving down-left")
    public void followPreviousSection_5SectionLongRope_DOWN_LEFT() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(-5, -5));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(-3, -3));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(-2, -2));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(-1, -1));
        rope.followPreviousSection(SECTION_2_INDEX);

        // ROPE MOVED DOWN-LEFT
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(-1, rope.getTailPositionList().get(1).getX());
        assertEquals(-1, rope.getTailPositionList().get(1).getY());
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(-4, rope.getSectionPositionList().get(SECTION_2_INDEX).getX());
        assertEquals(-4, rope.getSectionPositionList().get(SECTION_2_INDEX).getY());
        assertEquals(-3, rope.getSectionPositionList().get(SECTION_3_INDEX).getX());
        assertEquals(-3, rope.getSectionPositionList().get(SECTION_3_INDEX).getY());
        assertEquals(-2, rope.getSectionPositionList().get(SECTION_4_INDEX).getX());
        assertEquals(-2, rope.getSectionPositionList().get(SECTION_4_INDEX).getY());
        assertEquals(-1, rope.getSectionPositionList().get(TAIL_INDEX).getX());
        assertEquals(-1, rope.getSectionPositionList().get(TAIL_INDEX).getY());
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - moving left")
    public void followPreviousSection_5SectionLongRope_LEFT() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(-5, 0));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(-3, 0));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(-2, 0));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(-1, 0));
        rope.followPreviousSection(SECTION_2_INDEX);

        // ROPE MOVED LEFT
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(-1, rope.getTailPositionList().get(1).getX());
        assertEquals(0, rope.getTailPositionList().get(1).getY());
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(-4, rope.getSectionPositionList().get(SECTION_2_INDEX).getX());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_2_INDEX).getY());
        assertEquals(-3, rope.getSectionPositionList().get(SECTION_3_INDEX).getX());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_3_INDEX).getY());
        assertEquals(-2, rope.getSectionPositionList().get(SECTION_4_INDEX).getX());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_4_INDEX).getY());
        assertEquals(-1, rope.getSectionPositionList().get(TAIL_INDEX).getX());
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getY());
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - "
            + "moving up-left")
    public void followPreviousSection_5SectionLongRope_UP_LEFT() {
        Rope rope = new Rope(5);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int TAIL_INDEX = 4;
        rope.setSectionPosition(HEAD_INDEX, new Position(-5, 5));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(-3, 3));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(-2, 2));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(-1, 1));
        rope.followPreviousSection(SECTION_2_INDEX);

        // ROPE MOVED UP-LEFT
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(-1, rope.getTailPositionList().get(1).getX());
        assertEquals(1, rope.getTailPositionList().get(1).getY());
        assertEquals(5, rope.getSectionPositionList().size());
        assertEquals(-4, rope.getSectionPositionList().get(SECTION_2_INDEX).getX());
        assertEquals(4, rope.getSectionPositionList().get(SECTION_2_INDEX).getY());
        assertEquals(-3, rope.getSectionPositionList().get(SECTION_3_INDEX).getX());
        assertEquals(3, rope.getSectionPositionList().get(SECTION_3_INDEX).getY());
        assertEquals(-2, rope.getSectionPositionList().get(SECTION_4_INDEX).getX());
        assertEquals(2, rope.getSectionPositionList().get(SECTION_4_INDEX).getY());
        assertEquals(-1, rope.getSectionPositionList().get(TAIL_INDEX).getX());
        assertEquals(1, rope.getSectionPositionList().get(TAIL_INDEX).getY());
    }

    @Test
    @DisplayName("following a head section movement, it should move the following sections accordingly - "
            + "partial movement")
    public void followPreviousSection_5SectionLongRope_PARTIAL_MOVEMENT() {
        Rope rope = new Rope(7);
        Integer HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int SECTION_3_INDEX = 2;
        int SECTION_4_INDEX = 3;
        int SECTION_5_INDEX = 4;
        int SECTION_6_INDEX = 5;
        int TAIL_INDEX = 6;
        rope.setSectionPosition(HEAD_INDEX, new Position(-1, 3));
        rope.setSectionPosition(SECTION_2_INDEX, new Position(1, 3));
        rope.setSectionPosition(SECTION_3_INDEX, new Position(2, 3));
        rope.setSectionPosition(SECTION_4_INDEX, new Position(2, 2));
        rope.setSectionPosition(SECTION_5_INDEX, new Position(2, 1));
        rope.setSectionPosition(SECTION_6_INDEX, new Position(1, 0));
        rope.followPreviousSection(SECTION_2_INDEX);

        // PARTIAL MOVEMENT
        assertEquals(1, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(7, rope.getSectionPositionList().size());
        assertEquals(0, rope.getSectionPositionList().get(SECTION_2_INDEX).getX()); // moved left
        assertEquals(3, rope.getSectionPositionList().get(SECTION_2_INDEX).getY()); // moved left
        assertEquals(1, rope.getSectionPositionList().get(SECTION_3_INDEX).getX()); // moved left
        assertEquals(3, rope.getSectionPositionList().get(SECTION_3_INDEX).getY()); // moved left
        assertEquals(2, rope.getSectionPositionList().get(SECTION_4_INDEX).getX()); // did not move
        assertEquals(2, rope.getSectionPositionList().get(SECTION_4_INDEX).getY()); // did not move
        assertEquals(2, rope.getSectionPositionList().get(SECTION_5_INDEX).getX()); // did not move
        assertEquals(1, rope.getSectionPositionList().get(SECTION_5_INDEX).getY()); // did not move
        assertEquals(1, rope.getSectionPositionList().get(SECTION_6_INDEX).getX()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(SECTION_6_INDEX).getY()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getX()); // did not move
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getY()); // did not move
    }

    @Test
    @DisplayName("It should move the head section and the following sections")
    public void moveHead() {
        Rope rope = new Rope(3);
        int HEAD_INDEX = 0;
        int SECTION_2_INDEX = 1;
        int TAIL_INDEX = 2;
        Integer numberOfRepetitions = 4;
        Integer xModifier = 1; // move right
        Integer yModifier = 0; // move right
        rope.moveHead(numberOfRepetitions, xModifier, yModifier);

        // ROPE MOVED RIGHT
        assertEquals(3, rope.getTailPositionList().size());
        assertEquals(0, rope.getTailPositionList().get(0).getX());
        assertEquals(0, rope.getTailPositionList().get(0).getY());
        assertEquals(1, rope.getTailPositionList().get(1).getX());
        assertEquals(0, rope.getTailPositionList().get(1).getY());
        assertEquals(2, rope.getTailPositionList().get(2).getX());
        assertEquals(0, rope.getTailPositionList().get(2).getY());
        assertEquals(3, rope.getSectionPositionList().size());
        assertEquals(4, rope.getSectionPositionList().get(HEAD_INDEX).getX()); // moved right
        assertEquals(0, rope.getSectionPositionList().get(HEAD_INDEX).getY()); // moved right
        assertEquals(3, rope.getSectionPositionList().get(SECTION_2_INDEX).getX()); // moved right
        assertEquals(0, rope.getSectionPositionList().get(SECTION_2_INDEX).getY()); // moved right
        assertEquals(2, rope.getSectionPositionList().get(TAIL_INDEX).getX()); // moved right
        assertEquals(0, rope.getSectionPositionList().get(TAIL_INDEX).getY()); // moved right
    }
}
