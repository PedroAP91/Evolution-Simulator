package com.PedroAP.EvolutionSim.evolution_simulator.visual;

import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Creature;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Environment;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Zone;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.ZoneType;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.servicio.SimulationService;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.genetica.GeneticAlgorithm;
import java.util.Random;

public class SimulationServiceDummy extends SimulationService {
    private final Random random = new Random();

    public SimulationServiceDummy() {
        // Llamamos al constructor de la clase base pasando null para los repositorios
        super(null, null);
        // Ahora que los campos de la subclase ya están inicializados, llamamos a initEnvironment()
        initEnvironment();
    }

    @Override
    protected void initEnvironment() {
        // Inicializamos el entorno sin persistencia.
        environment = new Environment(1000, 1000);

        // Agregamos zonas sin persistir.
        environment.addZone(new Zone(null, 100, 100, 200, 200, ZoneType.FOOD));
        environment.addZone(new Zone(null, 500, 500, 150, 150, ZoneType.SHELTER));
        environment.addZone(new Zone(null, 300, 300, 100, 100, ZoneType.OBSTACLE));

        // Agregamos algunas criaturas sin persistir.
        for (int i = 0; i < 5; i++) {
            Creature creature = new Creature();
            creature.setX(random.nextDouble() * 1000);
            creature.setY(random.nextDouble() * 1000);
            creature.setEnergy(100);
            // ¡Establecer una velocidad para que puedan moverse!
            creature.setVelocity(1 + random.nextDouble()); // Por ejemplo, entre 1 y 2.
            // Si es necesario, inicializar otros atributos, p.ej.:
            // creature.setResistance(1 + random.nextDouble());
            // creature.setVision(50 + random.nextDouble() * 50);
            environment.addCreature(creature);
        }
    }


    @Override
    public void simulateStep() {
        // Ejemplo: mover aleatoriamente las criaturas.
        for (Creature creature : environment.getCreatures()) {
            double dx = random.nextDouble() * 2 - 1;
            double dy = random.nextDouble() * 2 - 1;
            creature.move(dx, dy);
        }
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }
}
