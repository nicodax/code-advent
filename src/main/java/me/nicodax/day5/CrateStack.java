package me.nicodax.day5;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.reverse;
import static java.util.Collections.singletonList;
import static me.nicodax.day5.CraneMode.ONE_BY_ONE;

@NoArgsConstructor
@Getter
public class CrateStack {
    List<Crate> stack = new ArrayList<>();

    public void moveTo(CraneMode craneMode, CrateStack destination, Integer numberOfCrates) {
        if (stack.size() < numberOfCrates) return;
        if (craneMode.equals(ONE_BY_ONE)) moveOneByOne(destination, numberOfCrates);
        else moveAllAtOnce(destination, numberOfCrates);
    }

    private void moveOneByOne(CrateStack destination, Integer numberOfCrates) {
        for (int i = 0; i < numberOfCrates; i++) {
            Crate crate = stack.remove(stack.size() - 1);
            destination.addCrates(singletonList(crate));
        }
    }

    private void moveAllAtOnce(CrateStack destination, Integer numberOfCrates) {
        List<Crate> crateList = new ArrayList<>();
        for (int i = 0; i < numberOfCrates; i++) {
            Crate crate = stack.remove(stack.size() - 1);
            crateList.add(crate);
        }
        reverse(crateList);
        destination.addCrates(crateList);
    }

    public void addCrates(List<Crate> crateList) {
        stack.addAll(crateList);
    }
}
