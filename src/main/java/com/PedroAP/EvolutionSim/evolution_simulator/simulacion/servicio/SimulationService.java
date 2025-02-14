package com.PedroAP.EvolutionSim.evolution_simulator.simulacion.servicio;

import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Creature;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Environment;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Genes;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Zone;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.ZoneType;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.repositorio.CreatureRepository;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.repositorio.ZoneRepository;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.genetica.GeneticAlgorithm;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SimulationService {

    protected Environment environment;
    protected CreatureRepository creatureRepository;
    protected ZoneRepository zoneRepository;

    public SimulationService(CreatureRepository creatureRepository, ZoneRepository zoneRepository) {
        this.creatureRepository = creatureRepository;
        this.zoneRepository = zoneRepository;
        // Inicializar el entorno (sin invocar initEnvironment aquí)
        environment = new Environment(1000, 1000);
    }

    /**
     * Inicializa el entorno con persistencia.
     * Este método se sobrescribirá en la versión Dummy.
     */
    protected void initEnvironment() {
        // Lógica de inicialización para producción (con persistencia)
        Zone foodZone = new Zone(null, 100, 100, 200, 200, ZoneType.FOOD);
        Zone shelterZone = new Zone(null, 500, 500, 150, 150, ZoneType.SHELTER);
        Zone obstacleZone = new Zone(null, 300, 300, 100, 100, ZoneType.OBSTACLE);
        zoneRepository.save(foodZone);
        zoneRepository.save(shelterZone);
        zoneRepository.save(obstacleZone);
        environment.addZone(foodZone);
        environment.addZone(shelterZone);
        environment.addZone(obstacleZone);

        for (int i = 0; i < 5; i++) {
            Genes genes = new Genes(Math.random(), Math.random(), Math.random());
            Creature creature = new Creature(
                    null,
                    Math.random() * 1000,
                    Math.random() * 1000,
                    1 + Math.random(),
                    1 + Math.random(),
                    100,
                    50 + Math.random() * 50,
                    genes);
            creatureRepository.save(creature);
            environment.addCreature(creature);
        }
    }

    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Simula un paso en el entorno.
     */
    public void simulateStep() {
        environment.update();
    }

    /**
     * Evoluciona la población de criaturas.
     */
    public void evolvePopulation() {
        List<Creature> currentPopulation = environment.getCreatures();
        GeneticAlgorithm ga = new GeneticAlgorithm();
        List<Creature> newPopulation = ga.evolve(currentPopulation);

        environment.getCreatures().clear();
        environment.getCreatures().addAll(newPopulation);

        creatureRepository.deleteAll();
        creatureRepository.saveAll(newPopulation);
    }
}
