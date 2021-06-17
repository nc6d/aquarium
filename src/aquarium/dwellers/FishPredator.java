package aquarium.dwellers;

import aquarium.Aquarium;
import aquarium.Cell;

import java.util.ArrayList;
import java.util.Random;

public class FishPredator extends Fish {
    public FishPredator(final Aquarium aquarium, final Cell cell) {
        super(aquarium, cell);
    }

    @Override
    protected void positionChange(Cell to) {
        aquarium.aquariumArr[this.axisY][this.axisX].fishPredator = null;
        aquarium.aquariumArr[to.axisY][to.axisX].fishPredator = this;
        moveToCell(aquarium.aquariumArr[to.axisY][to.axisX]);
    }

//    Скорость определяется как + и - координат в цикле ниже
    @Override
    public Cell seekWay() {
        ArrayList<Cell> freeCells = new ArrayList<>();
        for (int i = Math.max(this.axisY - 2, 0); i <= Math.min(this.axisY + 2, aquarium.aquariumArr.length - 2); i++) {
            for (int j = Math.max(this.axisX - 2, 0); j <= Math.min(this.axisX + 2, aquarium.aquariumArr[0].length - 2); j++) {
                if (aquarium.aquariumArr[i][j].fishPredator == null && aquarium.aquariumArr[i][j].fishHerb != null) {
                    return aquarium.aquariumArr[i][j];
                }
                if (aquarium.aquariumArr[i][j].fishPredator == null && (aquarium.aquariumArr[i][j].isEmpty() ||
                        aquarium.aquariumArr[i][j].herb != null)) {
                    freeCells.add(aquarium.aquariumArr[i][j]);
                }

            }
        }
        if (freeCells.isEmpty()) {
            return aquarium.aquariumArr[this.axisY][this.axisX];
        }
        return freeCells.get(new Random().nextInt(freeCells.size()));
    }

    @Override
    protected boolean eat(final Cell thisCell) {
        if (thisCell.fishHerb != null) {
            hunger = Math.max(0, hunger - thisCell.fishHerb.hunger * 4);
            thisCell.fishHerb = null;
            return true;
        }
        return false;
    }


    @Override
    protected void executeSuicide() {
        aquarium.aquariumArr[this.axisY][this.axisX].fishPredator = null;
    }

    @Override
    protected void bornInCell(final Cell cell) {
        aquarium.fishPredatorArr.add(new FishPredator(aquarium, cell));
        cell.fishPredator = aquarium.fishPredatorArr.get(aquarium.fishPredatorArr.size() - 1);
    }

    @Override
    protected boolean impregnate(Cell cell) {
        if (isMale && !madeChildren &&
                cell.fishPredator != null && !cell.fishPredator.madeChildren &&
                !cell.fishPredator.isMale &&
                aquarium.cfg.ageBeginMature <= cell.fishPredator.age &&
                cell.fishPredator.age <= aquarium.cfg.ageBeginOld &&
                !cell.fishPredator.isPregnant) {
            madeChildren = true;
            cell.fishPredator.madeChildren = true;
            cell.fishPredator.isPregnant = true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "P" + giveName();
    }
}
