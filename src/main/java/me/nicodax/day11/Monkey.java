package me.nicodax.day11;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    public static final Integer OPERATION_NUMBER_IS_ITSELF_MARKER = -99;
    @Getter
    @Setter
    private List<Integer> itemWorryLevelList = new ArrayList<>();
    @Getter
    private OperationTypes operation;
    @Getter
    private Integer operationNumber;
    @Getter
    private Integer conditionDivider;
    @Getter
    private Integer trueTestMonkeyTarget;
    @Getter
    private Integer falseTestMonkeyTarget;
    @Getter
    private Integer totalInspectedItems = 0;

    public Monkey(List<Integer> heldItemsWorryLevelList,
                  OperationTypes operation,
                  Integer operationNumber,
                  Integer conditionDivider,
                  Integer trueTestMonkeyTarget,
                  Integer falseTestMonkeyTarget) {
        // TO DO
    }

    public void inspectItem() {
        // TO DO
    }

    public void getsBored() {
        // TO DO
    }

    public Boolean testItem() {
        // TO DO
        return false;
    }

    public void applyReliefTo() {
        // TO DO
    }
}
