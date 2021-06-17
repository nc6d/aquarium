package aquarium;

import aquarium.dwellers.*;
import aquarium.dwellers.Stone;
import aquarium.dwellers.Herb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aquarium {
    public final Cell[][] aquariumArr;
    public final List<Herb> herbArr;
    public final List<FishHerb> fishHerbArr;
    public final List<FishPredator> fishPredatorArr;
    public final Parser cfg;

    public Aquarium(final String cfg) {
        this.cfg = new Parser(cfg);
        this.aquariumArr = new Cell[this.cfg.height][this.cfg.width];
        this.fishHerbArr = new ArrayList<>(this.cfg.fishHerbsNumber);
        this.fishPredatorArr = new ArrayList<>(this.cfg.fishPredatorsNumber);
        this.herbArr = new ArrayList<>(this.cfg.herbsNumber);

        ArrayList<Cell> cells = new ArrayList<>();

        for (int i = 0; i < this.cfg.width; i++) {
            for (int j = 0; j < this.cfg.height; j++) {
                cells.add(new Cell(i, j, null, null, null, null));
            }
        }

        Collections.shuffle(cells);

        var objectIndex = 0;
        for (int i = 0; i < this.cfg.stonesNumber; i++) {
            cells.get(objectIndex).stone = new Stone(this, cells.get(objectIndex));
            objectIndex += 1;
        }
        for (int i = 0; i < this.cfg.herbsNumber; i++) {
            herbArr.add(new Herb(this, cells.get(objectIndex)));
            cells.get(objectIndex).herb = herbArr.get(i);
            objectIndex += 1;
        }
        for (int i = 0; i < this.cfg.fishHerbsNumber; i++) {
            fishHerbArr.add(new FishHerb(this, cells.get(objectIndex)));
            cells.get(objectIndex).fishHerb = fishHerbArr.get(i);
            objectIndex += 1;
        }
        for (int i = 0; i < this.cfg.fishPredatorsNumber; i++) {
            fishPredatorArr.add(new FishPredator(this, cells.get(objectIndex)));
            cells.get(objectIndex).fishPredator = fishPredatorArr.get(i);
            objectIndex += 1;
        }
        for (Cell cell : cells) {
            aquariumArr[cell.axisY][cell.axisX] = cell;
        }

    }

    public void checkState() {
        for (Cell[] line : aquariumArr) {
            for (Cell cell : line) {
                System.out.format("⋮%8s", cell);
            }
            System.out.print("⋮\n");
        }
    }


    public void nextIteration() {
        int size = herbArr.size();
        for (int i = 0; i < size; i++) {
            herbArr.get(i).action();
        }
        size = fishPredatorArr.size();
        for (int i = 0; i < size; i++) {
            fishPredatorArr.get(i).action();
        }
        size = fishHerbArr.size();
        for (int i = 0; i < size; i++) {
            fishHerbArr.get(i).action();
        }
    }
}
