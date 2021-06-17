package aquarium.dwellers;

import aquarium.Aquarium;
import aquarium.Cell;

public class Herb extends Entity {
    public int hungerScore = 0;
    public Herb(final Aquarium aquarium, final Cell cell) {
        super(aquarium, cell);
    }

    @Override
    public void action() {
        hungerScore = (hungerScore + 1) % aquarium.cfg.feedScores;
    }

    @Override
    public String toString() {
        return "G";
    }
}
