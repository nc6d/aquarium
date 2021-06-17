package aquarium;

public abstract class AquariumEntity {
    protected final Aquarium aquarium;
    protected AquariumEntity(final Aquarium aquarium) {
        this.aquarium = aquarium;
    }
    public abstract void action();
}
