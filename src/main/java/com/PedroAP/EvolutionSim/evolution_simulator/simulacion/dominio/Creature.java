// src/main/java/com/PedroAP/EvolutionSim/evolution_simulator/simulacion/dominio/Creature.java
package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double x;
    private double y;
    private double velocity;
    private double resistance;
    private double energy;
    private double vision;

    @Embedded
    private Genes genes;

    /**
     * Método que simula el movimiento de la criatura.
     * Se actualiza la posición según la velocidad y se consume energía.
     */
    public void move(double dx, double dy) {
        this.x += dx * velocity;
        this.y += dy * velocity;
        // Consumo de energía proporcional a la distancia recorrida (valor arbitrario)
        this.energy -= Math.sqrt(dx * dx + dy * dy) * 0.1;
    }
}
