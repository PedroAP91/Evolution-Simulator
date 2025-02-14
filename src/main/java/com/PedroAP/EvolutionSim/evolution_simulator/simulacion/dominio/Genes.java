// src/main/java/com/PedroAP/EvolutionSim/evolution_simulator/simulacion/dominio/Genes.java
package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genes {
    private double speedGene;
    private double visionGene;
    private double resistanceGene;
}
