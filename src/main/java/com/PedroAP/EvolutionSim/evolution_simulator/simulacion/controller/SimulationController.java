// src/main/java/com/PedroAP/EvolutionSim/evolution_simulator/simulacion/controlador/SimulationController.java
package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.controller;

import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Environment;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.servicio.SimulationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/api/environment")
    public Environment getEnvironment() {
        return simulationService.getEnvironment();
    }

    @GetMapping("/api/simulate")
    public String simulateStep() {
        simulationService.simulateStep();
        return "Simulación actualizada";
    }
}
