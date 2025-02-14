package com.PedroAP.EvolutionSim.evolution_simulator.visual;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Creature;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Environment;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.Zone;
import com.PedroAP.EvolutionSim.evolution_simulator.simulacion.dominio.ZoneType;
import com.PedroAP.EvolutionSim.evolution_simulator.visual.SimulationServiceDummy;

public class SimulationVisualizer extends Application {

    private SimulationServiceDummy simulationService;
    private Environment environment;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        // Inicializamos nuestro servicio dummy
        simulationService = new SimulationServiceDummy();
        environment = simulationService.getEnvironment();

        canvas = new Canvas(1000, 1000);
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Simulación Evolutiva");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Actualizamos la simulación cada segundo
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            simulationService.simulateStep();
            drawEnvironment();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void drawEnvironment() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Limpiar el canvas
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Dibujar las zonas
        for (Zone zone : environment.getZones()) {
            switch (zone.getType()) {
                case FOOD:
                    gc.setFill(Color.LIGHTGREEN);
                    break;
                case SHELTER:
                    gc.setFill(Color.LIGHTBLUE);
                    break;
                case OBSTACLE:
                    gc.setFill(Color.LIGHTGRAY);
                    break;
            }
            gc.fillRect(zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight());
        }

        // Dibujar las criaturas
        gc.setFill(Color.RED);
        for (Creature creature : environment.getCreatures()) {
            double x = creature.getX();
            double y = creature.getY();
            gc.fillOval(x - 5, y - 5, 10, 10);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
