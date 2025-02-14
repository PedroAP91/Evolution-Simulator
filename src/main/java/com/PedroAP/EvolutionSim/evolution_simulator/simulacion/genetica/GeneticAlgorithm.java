// src/main/java/com/PedroAP/EvolutionSim/evolution_simulator/simulacion/genetica/GeneticAlgorithm.java
package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.genetica;

import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Creature;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Genes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private static final Random random = new Random();
    // Probabilidad de mutación para cada gen
    private static final double MUTATION_RATE = 0.1;
    // Rango máximo de cambio en la mutación
    private static final double MUTATION_RANGE = 0.1;

    /**
     * Evoluciona la población actual y genera una nueva generación.
     * @param population Población actual de criaturas.
     * @return Nueva generación de criaturas.
     */
    public List<Creature> evolve(List<Creature> population) {
        List<Creature> newGeneration = new ArrayList<>();

        // Ordenar la población por energía (fitness)
        population.sort((a, b) -> Double.compare(b.getEnergy(), a.getEnergy()));

        // Seleccionar la mitad superior como padres
        int numParents = population.size() / 2;
        List<Creature> parents = population.subList(0, numParents);

        // Generar nuevos individuos hasta completar la población original
        while (newGeneration.size() < population.size()) {
            Creature parent1 = parents.get(random.nextInt(parents.size()));
            Creature parent2 = parents.get(random.nextInt(parents.size()));
            Creature child = crossover(parent1, parent2);
            mutate(child);
            // Reiniciamos la energía del hijo (puedes ajustar este valor)
            child.setEnergy(100);
            // Opcional: asignar una posición aleatoria en el entorno
            child.setX(random.nextDouble() * 1000);
            child.setY(random.nextDouble() * 1000);
            newGeneration.add(child);
        }
        return newGeneration;
    }

    /**
     * Realiza el cruce de los genes de dos padres para generar un hijo.
     */
    private Creature crossover(Creature parent1, Creature parent2) {
        Genes genes1 = parent1.getGenes();
        Genes genes2 = parent2.getGenes();

        double newSpeedGene = (genes1.getSpeedGene() + genes2.getSpeedGene()) / 2;
        double newVisionGene = (genes1.getVisionGene() + genes2.getVisionGene()) / 2;
        double newResistanceGene = (genes1.getResistanceGene() + genes2.getResistanceGene()) / 2;

        Genes childGenes = new Genes(newSpeedGene, newVisionGene, newResistanceGene);
        Creature child = new Creature();
        child.setGenes(childGenes);
        // Asignamos atributos derivados de los genes, por ejemplo:
        child.setVelocity(1 + childGenes.getSpeedGene());
        child.setVision(50 + childGenes.getVisionGene() * 50);
        child.setResistance(1 + childGenes.getResistanceGene());
        return child;
    }

    /**
     * Aplica mutación a los genes del individuo con una probabilidad definida.
     */
    private void mutate(Creature creature) {
        Genes genes = creature.getGenes();
        if(random.nextDouble() < MUTATION_RATE) {
            genes.setSpeedGene(genes.getSpeedGene() + (random.nextDouble() * 2 - 1) * MUTATION_RANGE);
        }
        if(random.nextDouble() < MUTATION_RATE) {
            genes.setVisionGene(genes.getVisionGene() + (random.nextDouble() * 2 - 1) * MUTATION_RANGE);
        }
        if(random.nextDouble() < MUTATION_RATE) {
            genes.setResistanceGene(genes.getResistanceGene() + (random.nextDouble() * 2 - 1) * MUTATION_RANGE);
        }
    }
}
