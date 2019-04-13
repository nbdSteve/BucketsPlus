package dev.neur.bp.generators;

public class GeneratorType {

    int numberOfBlocksToGenerate;
    long delayBetweenBlockGeneration;

    public GeneratorType(int numberOfBlocksToGenerate, long delayBetweenBlockGeneration, String generatorKey) {
        this.numberOfBlocksToGenerate = numberOfBlocksToGenerate;
        this.delayBetweenBlockGeneration = delayBetweenBlockGeneration;
    }
}
