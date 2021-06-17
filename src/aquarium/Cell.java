package aquarium;

import aquarium.dwellers.FishPredator;
import aquarium.dwellers.FishHerb;
import aquarium.dwellers.Stone;
import aquarium.dwellers.Herb;

public class Cell {
    public final int axisX;
    public final int axisY;
    public Stone stone;
    public Herb herb;
    public FishHerb fishHerb;
    public FishPredator fishPredator;

    public Cell(final int axisX, final int axisY, Stone stone, Herb herb, FishHerb fishHerb, FishPredator fishPredator) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.stone = stone;
        this.herb = herb;
        this.fishHerb = fishHerb;
        this.fishPredator = fishPredator;
    }

    public boolean isEmpty() {
        return stone == null &&
                herb == null &&
                fishHerb == null &&
                fishPredator == null;
    }
// Разделитель объектов в клетке

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (stone != null) {
            sb.append(stone).append(" ");
        }
        if (herb != null) {
            sb.append(herb).append(" ");
        }
        if (fishHerb != null) {
            sb.append(fishHerb).append(" ");
        }
        if (fishPredator != null) {
            sb.append(fishPredator);
        }
        return sb.toString();
    }
}
