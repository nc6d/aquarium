package aquarium.dwellers;

import aquarium.Aquarium;
import aquarium.Cell;

public class Stone extends Entity {
    public Stone(final Aquarium aquarium, final Cell cell) {
        super(aquarium, cell);
    }

    @Override
    public void action() {}

    @Override
    public String toString() {
        return "X";
    }
}
