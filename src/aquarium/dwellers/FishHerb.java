package aquarium.dwellers;

import aquarium.Aquarium;
import aquarium.Cell;

import java.util.ArrayList;
import java.util.Random;

public class FishHerb extends Fish {
    public FishHerb(final Aquarium aquarium, final Cell cell) {
        super(aquarium, cell);
    }

    @Override
    protected boolean eat(final Cell currentCell) {
        if (currentCell.herb != null) {
            hunger = Math.max(0, hunger - currentCell.herb.hungerScore);
            currentCell.herb = null;
            return true;
        }
        return false;
    }

    //    Скорость определяется как + и - координат в цикле ниже
    @Override
    public Cell seekWay() {
        ArrayList<Cell> possibleCells = new ArrayList<>();
        for (int i = Math.max(this.axisY - 3, 0); i <= Math.min(this.axisY + 3, aquarium.aquariumArr.length - 3); i++) {
            for (int j = Math.max(this.axisX - 3, 0); j <= Math.min(this.axisX + 3, aquarium.aquariumArr[0].length - 3); j++) {
                if (aquarium.aquariumArr[i][j].fishHerb == null) {
                    if (aquarium.aquariumArr[i][j].isEmpty()) {
                        possibleCells.add(aquarium.aquariumArr[i][j]);
                    } else if (aquarium.aquariumArr[i][j].herb != null) {
                        return aquarium.aquariumArr[i][j];
                    }
                }
            }
        }
        if (possibleCells.isEmpty()) {
            return aquarium.aquariumArr[this.axisY][this.axisX];
        }
        return possibleCells.get(new Random().nextInt(possibleCells.size()));
    }

    @Override
    protected void positionChange(final Cell to) {
        aquarium.aquariumArr[this.axisY][this.axisX].fishHerb = null;
        aquarium.aquariumArr[to.axisY][to.axisX].fishHerb = this;
        moveToCell(aquarium.aquariumArr[to.axisY][to.axisX]);
    }

    @Override
    protected void executeSuicide() {
        aquarium.aquariumArr[this.axisY][this.axisX].fishHerb = null;
    }

    @Override
    protected void bornInCell(final Cell cell) {
        aquarium.fishHerbArr.add(new FishHerb(aquarium, cell));
        cell.fishHerb = aquarium.fishHerbArr.get(aquarium.fishHerbArr.size() - 1);
    }

    @Override
    protected boolean impregnate(Cell cell) {
        if (isMale && !madeChildren && cell.fishHerb != null && !cell.fishHerb.madeChildren && !cell.fishHerb.isMale && aquarium.cfg.ageBeginMature <= cell.fishHerb.age &&
                cell.fishHerb.age <= aquarium.cfg.ageBeginOld && !cell.fishHerb.isPregnant) {
            madeChildren = true;
            cell.fishHerb.madeChildren = true;
            cell.fishHerb.isPregnant = true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "H" + giveName();
    }
}
