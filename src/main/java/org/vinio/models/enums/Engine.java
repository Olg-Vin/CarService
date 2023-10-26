package org.vinio.models.enums;

public enum Engine {
    Gasoline(100), Diesel(200), Electric(300), Hybrid(400);

    public int engine;

    Engine(int engine) {
        this.engine = engine;
    }

    public int getEngine() {
        return engine;
    }

    public static Engine of(int num) {
        for (Engine engine : Engine.values()) {
            if (engine.getEngine() == num) {
                return engine;
            }
        }
        throw new IllegalArgumentException();
    }
}
