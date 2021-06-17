package aquarium.dwellers;

import aquarium.Aquarium;
import aquarium.AquariumEntity;
import aquarium.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Fish extends AquariumEntity {
    protected int axisX;
    protected int axisY;
    protected int age = 0;
    protected int hunger = 0;
    private boolean isAlive = true;
    private int pregnancyStage = 0;
    protected boolean isPregnant = false;
    protected final boolean isMale;
    protected boolean madeChildren = false;
    protected int moveSpeed = 1;

    protected Fish(final Aquarium aquarium, Cell cell) {
        super(aquarium);
        this.axisX = cell.axisX;
        this.axisY = cell.axisY;
        this.isMale = new Random().nextBoolean();
    }

    public void moveToCell(final Cell cell) {
        this.axisX = cell.axisX;
        this.axisY = cell.axisY;
    }

    protected abstract Cell seekWay();

    protected abstract boolean eat(final Cell currentCell);

    protected abstract void positionChange(final Cell to);

    private void death() {
        this.isAlive = false;
        executeSuicide();
    }

    protected abstract void executeSuicide();

    @Override
    public void action() {
        if (!isAlive) {
            return;
        }
        if (!eat(aquarium.aquariumArr[this.axisY][this.axisX])) {
            positionChange(seekWay());
        }
        controlAge();
        controlHunger();
        controlPregnancy();
    }

    private void controlAge() {
        age += 1;
        hunger += 1;
        if (age == aquarium.cfg.ageMax) {
            death();
        }
    }
    private void controlHunger() {
        hunger += 1;
        if (hunger == aquarium.cfg.fish_maxHunger) {
            death();
        }
    }

    private void controlPregnancy() {
        if (isMale) {
            for (int i = Math.max(0, this.axisY - 1); i <= Math.min(aquarium.cfg.height - 1, this.axisY + 1); i++) {
                for (int j = Math.max(0, this.axisX - 1); j <= Math.min(aquarium.cfg.height - 1, this.axisX + 1); j++) {
                    if (!(i == this.axisY && j == this.axisX) && impregnate(aquarium.aquariumArr[i][j])) {
                        return;
                    }
                }
            }
            return;
        }
        if (this.pregnancyStage == aquarium.cfg.pregnancyPeriod && isPregnant) {
            spawnChildren();
        }
        if (this.isPregnant) {
            pregnancyStage += 1;
        }
    }

    protected abstract boolean impregnate(final Cell cell);

    private void spawnChildren() {
        for (Cell cell : findEmptyCells(aquarium.cfg.numberOfChildren)) {
            bornInCell(cell);
        }
        isPregnant = false;
    }

    protected abstract void bornInCell(final Cell cell);

    private ArrayList<Cell> findEmptyCells(int limit) {
        int counter = 0;
        ArrayList<Cell> res = new ArrayList<>(limit);
        for (Cell[] line : aquarium.aquariumArr) {
            for (Cell cell : line) {
                if (cell.isEmpty()) {
                    res.add(cell);
                    counter += 1;
                    if (counter == limit) {
                        Collections.shuffle(res);
                        return res;
                    }
                }
            }
        }
        Collections.shuffle(res);
        return res;
    }

    protected String giveName() {
        var sb = new StringBuilder();
        if (isMale) {
            sb.append("M");
        } else {
            sb.append("F");
        }
        if (age >= aquarium.cfg.ageBeginOld) {
            sb.append("3");
        } else if (age >= aquarium.cfg.ageBeginMature) {
            sb.append("2");
        } else {
            sb.append("1");
        }
        if (!isMale) {
            if (isPregnant) {
                sb.append("+");
            } else {
                sb.append("-");
            }
        }
        return sb.toString();
    }
}
