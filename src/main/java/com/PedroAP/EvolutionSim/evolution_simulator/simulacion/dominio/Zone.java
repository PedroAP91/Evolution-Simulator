// src/main/java/com/PedroAP/EvolutionSim/evolution_simulator/simulacion/dominio/Zone.java
package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double x;
    private double y;
    private double width;
    private double height;

    @Enumerated(EnumType.STRING)
    private ZoneType type;
}
