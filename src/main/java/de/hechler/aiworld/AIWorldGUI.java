package de.hechler.aiworld;

import java.util.List;

import de.hechler.aiworld.core.VisibleObject;
import de.hechler.aiworld.things.SimplyMovingThing;
import de.hechler.aiworld.things.SpinningThing;
import de.hechler.aiworld.util.RandomUtil;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class AIWorldGUI extends Application {

	private final static int WORLD_SIZE = 300;
	private final static int HALF_WORLD_SIZE = WORLD_SIZE / 2;
	private final static double DIV_255 = 1.0 / 255.0;
	
	private AIWorld world;
	
    public static void main(String[] args) {
        launch(args);
    }

    Canvas canvasOverview;

	private Timeline timeline;
	
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Things That Learn");
        Group root = new Group();
        canvasOverview = new Canvas(WORLD_SIZE, WORLD_SIZE);

        HBox buttonsPane = new HBox();

        Button timerButton = new Button("Run");
        Button startButton = new Button("Tick");
        
        timerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (timeline != null) {
            		timeline.stop();
            		timeline = null;
            		timerButton.setText("Run");
            		startButton.setDisable(false);
            	}
            	else {
	            	timeline = new Timeline(new KeyFrame(
	            	        Duration.millis(100),
	            	        ae -> tick()));
	            	timeline.setCycleCount(Animation.INDEFINITE);
	            	timeline.play();
            		timerButton.setText("Stop");
            		startButton.setDisable(true);
            	}                
            }
        });
        buttonsPane.getChildren().add(timerButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                tick();
            }
        });
        buttonsPane.getChildren().add(startButton);

        VBox verticalPane = new VBox();
        verticalPane.getChildren().add(buttonsPane);
        verticalPane.getChildren().add(canvasOverview);
        
        root.getChildren().add(verticalPane);
        primaryStage.setScene(new Scene(root));

        createWorld();
        
        primaryStage.show();
    }



	private void createWorld() {
		world = new AIWorld(WORLD_SIZE);
		for (int i=0; i<5; i++) {
			world.add(new SimplyMovingThing(world, RandomUtil.getPosition(WORLD_SIZE)));
			world.add(new SpinningThing(world, RandomUtil.getPosition(WORLD_SIZE)));
		}
	}


    



	private void tick() {
    	world.tick();
    	showWorld();
    }

	private void showWorld() {
		GraphicsContext gc = canvasOverview.getGraphicsContext2D();
		gc.clearRect(0, 0, WORLD_SIZE, WORLD_SIZE);

		List<VisibleObject> visibleObjects = world.getVisibleObjects(0, 0, WORLD_SIZE, WORLD_SIZE);
		for (VisibleObject visibleObj:visibleObjects) {
			show(gc, visibleObj);
		}
	}



	private void show(GraphicsContext gc, VisibleObject gObj) {
		Color col = Color.rgb(gObj.getCol().getR(), gObj.getCol().getG(), gObj.getCol().getB(), gObj.getCol().getA() * DIV_255);
		double x = HALF_WORLD_SIZE + gObj.getPos().getX();
		double y = HALF_WORLD_SIZE + gObj.getPos().getY();
//		double dir = gObj.getPos().getDir();
		gObj.getPos().getX();
		switch(gObj.getShape().getType()) {
		case PIX: {
			gc.getPixelWriter().setArgb((int) x, (int) y, gObj.getCol().getArgb());
			break;
		}
		case DOT: {
			gc.setStroke(col);
			gc.setLineWidth(2.0);
			gc.strokeRect(x, y, 3, 3);
			break;
		}
		default:
			throw new UnsupportedOperationException("SHAPETYPE '"+gObj.getShape().getType()+"' IS NOT SUPPORTED");
		}
	}    
	
}