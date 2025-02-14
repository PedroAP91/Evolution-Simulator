// src/main/java/com/PedroAP/EvolutionSim/evolution_simulator/simulacion/repositorio/CreatureRepository.java
package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.repositorio;

import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long> {
    // Métodos personalizados de consulta se pueden agregar aquí
}
