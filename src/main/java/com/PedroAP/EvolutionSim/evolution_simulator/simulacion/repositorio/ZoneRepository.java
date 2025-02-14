// src/main/java/com/PedroAP/EvolutionSim/evolution_simulator/simulacion/repositorio/ZoneRepository.java
package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.repositorio;

import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    // Métodos personalizados de consulta se pueden agregar aquí
}
