package me.nicodax.day11;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Monkey {
    public static final Integer OPERATION_NUMBER_IS_ITSELF_MARKER = -99;
    private final Integer RELIEF_DIVIDER;
    @Getter
    @Setter
    private List<Long> itemWorryLevelList = new ArrayList<>();
    @Getter
    private final OperationTypes operation;
    private final Integer operationNumber;
    @Getter
    private final Integer conditionDivider;
    @Getter
    private final Integer trueTestMonkeyTarget;
    @Getter
    private final Integer falseTestMonkeyTarget;
    @Getter
    private Integer totalInspectedItems = 0;

    public Monkey(List<Long> itemWorryLevelList,
                  OperationTypes operation,
                  Integer operationNumber,
                  Integer conditionDivider,
                  Integer trueTestMonkeyTarget,
                  Integer falseTestMonkeyTarget,
                  Integer reliefDivider) {
        this.itemWorryLevelList.addAll(itemWorryLevelList);
        this.operation = operation;
        this.operationNumber = operationNumber;
        this.conditionDivider = conditionDivider;
        this.trueTestMonkeyTarget = trueTestMonkeyTarget;
        this.falseTestMonkeyTarget = falseTestMonkeyTarget;
        RELIEF_DIVIDER = reliefDivider;
    }

    public void inspectItem(Long superModulo) {
        totalInspectedItems++;
        Long newItemWorryLevel = switch (operation) {
            case MULTIPLY -> itemWorryLevelList.get(0) * getOperationNumber();
            case DIVIDE -> itemWorryLevelList.get(0) / getOperationNumber();
            case ADD -> itemWorryLevelList.get(0) + getOperationNumber();
            default -> itemWorryLevelList.get(0) - getOperationNumber();
        };
        if (newItemWorryLevel < 0) {
            System.out.println("HELLO");
        }
        setWorryLevelListFor(0, newItemWorryLevel % superModulo);
    }

    public void getsBored() {
        // TO DO
    }

    public Boolean testItem() {
        return (itemWorryLevelList.get(0) % conditionDivider) == 0;
    }

    public void applyReliefTo() {
        Long newItemWorryLevel = itemWorryLevelList.get(0) / RELIEF_DIVIDER;
        setWorryLevelListFor(0, newItemWorryLevel);
    }

    public Long getOperationNumber() {
        return Objects.equals(operationNumber, OPERATION_NUMBER_IS_ITSELF_MARKER) ?
                itemWorryLevelList.get(0) : operationNumber;
    }

    public void setWorryLevelListFor(Integer itemIndex, Long newItemWorryLevel) {
        itemWorryLevelList.set(itemIndex, newItemWorryLevel);
    }

    public Integer throwItemTo() {
        return testItem() ? trueTestMonkeyTarget : falseTestMonkeyTarget;
    }

    public Long throwItem() {
        return itemWorryLevelList.remove(0);
    }

    public void catchItem(Long worryLevel) {
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
                falseTestMonkeyTarget,
                RELIEF_DIVIDER);
    }

}
