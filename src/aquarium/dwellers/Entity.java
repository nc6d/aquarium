package aquarium.dwellers;

import aquarium.Aquarium;
import aquarium.AquariumEntity;
import aquarium.Cell;

public abstract class Entity extends AquariumEntity {
    protected final Cell cell;
    protected Entity(final Aquarium aquarium, final Cell cell) {
        super(aquarium);
        this.cell = cell;
    }
}
