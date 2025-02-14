// src/main/java/com/PedroAP/EvolutionSim/evolution_simulator/simulacion/dominio/Environment.java
package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    private double width;
    private double height;
    private List<Zone> zones;
    private List<Creature> creatures;

    public Environment(double width, double height) {
        this.width = width;
        this.height = height;
        this.zones = new ArrayList<>();
        this.creatures = new ArrayList<>();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void addZone(Zone zone) {
        zones.add(zone);
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    /**
     * Método para actualizar el estado del entorno.
     * Aquí se incluirán reglas de movimiento, interacciones, consumo de energía, etc.
     */
    public void update() {
        // Lógica de actualización (iterar sobre criaturas, aplicar reglas, etc.)
    }

    @Override
    public String toString() {
        return "Environment{" +
                "width=" + width +
                ", height=" + height +
                ", zones=" + zones +
                ", creatures=" + creatures +
                '}';
    }
}
