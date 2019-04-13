package dev.neur.bp.generators;

import java.util.HashMap;

public class BlockGenerators {

    public static HashMap<Types, GeneratorType> generators;

    public enum Types {
        VERTICAL, HORIZONTAL, GRAVITY
    }

    public void loadGeneratorType() {
        generators = new HashMap<>();
//        generators.put(Types.VERTICAL, );
    }
}
