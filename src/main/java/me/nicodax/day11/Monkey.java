package me.nicodax.day11;

import lombok.Getter;
import lombok.Setter;
import me.nicodax.day9.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Monkey {
    public static final Integer OPERATION_NUMBER_IS_ITSELF_MARKER = -99;
    private final Integer RELIEF_DIVIDER = 3;
    @Getter
    @Setter
    private List<Integer> itemWorryLevelList = new ArrayList<>();
    @Getter
    private OperationTypes operation;
    private Integer operationNumber;
    @Getter
    private Integer conditionDivider;
    @Getter
    private Integer trueTestMonkeyTarget;
    @Getter
    private Integer falseTestMonkeyTarget;
    @Getter
    private Integer totalInspectedItems = 0;

    public Monkey(List<Integer> itemWorryLevelList,
                  OperationTypes operation,
                  Integer operationNumber,
                  Integer conditionDivider,
                  Integer trueTestMonkeyTarget,
                  Integer falseTestMonkeyTarget) {
        this.itemWorryLevelList.addAll(itemWorryLevelList);
        this.operation = operation;
        this.operationNumber = operationNumber;
        this.conditionDivider = conditionDivider;
        this.trueTestMonkeyTarget = trueTestMonkeyTarget;
        this.falseTestMonkeyTarget = falseTestMonkeyTarget;
    }

    public void inspectItem() {
        totalInspectedItems++;
        Integer newItemWorryLevel = switch (operation) {
            case MULTIPLY -> itemWorryLevelList.get(0) * getOperationNumber();
            case DIVIDE -> itemWorryLevelList.get(0) / getOperationNumber();
            case ADD -> itemWorryLevelList.get(0) + getOperationNumber();
            default -> itemWorryLevelList.get(0) - getOperationNumber();
        };
        setWorryLevelListFor(0, newItemWorryLevel);
    }

    public void getsBored() {
        // TO DO
    }

    public Boolean testItem() {
        return (itemWorryLevelList.get(0) % conditionDivider) == 0;
    }

    public void applyReliefTo() {
        Integer newItemWorryLevel = itemWorryLevelList.get(0) / RELIEF_DIVIDER;
        setWorryLevelListFor(0, newItemWorryLevel);
    }

    public Integer getOperationNumber() {
        return Objects.equals(operationNumber, OPERATION_NUMBER_IS_ITSELF_MARKER) ?
                itemWorryLevelList.get(0) : operationNumber;
    }

    public void setWorryLevelListFor(Integer itemIndex, Integer newItemWorryLevel) {
        itemWorryLevelList.set(itemIndex, newItemWorryLevel);
    }

    public Integer throwItemTo() {
        return testItem() ? trueTestMonkeyTarget : falseTestMonkeyTarget;
    }

    public Integer throwItem() {
        return itemWorryLevelList.remove(0);
    }

    public void catchItem(Integer worryLevel) {
        itemWorryLevelList.add(worryLevel);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Monkey otherMonkey)) return false;
        return itemWorryLevelList.equals(otherMonkey.itemWorryLevelList);
    }

    @Override
    public String toString() {
        return itemWorryLevelList.toString();
    }

    @Override
    public Monkey clone() {
        return new Monkey(itemWorryLevelList,
                operation,
                operationNumber,
                conditionDivider,
                trueTestMonkeyTarget,
                falseTestMonkeyTarget);
    }

}
