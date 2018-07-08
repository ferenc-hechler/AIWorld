package de.hechler.aiworld;

import java.util.List;

import de.hechler.aiworld.core.AIWPosition;
import de.hechler.aiworld.core.VisibleObject;
import de.hechler.aiworld.things.BatteryThing;
import de.hechler.aiworld.things.SimplyMovingThing;
import de.hechler.aiworld.things.SpinningThing;
import de.hechler.aiworld.things.WallThing;
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

    private int delay;
    
	private Timeline timeline;
	
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AI-World");
        Group root = new Group();
        canvasOverview = new Canvas(WORLD_SIZE+2, WORLD_SIZE+2);

        HBox buttonsPane = new HBox();

        Button timerButton = new Button("Run");
        Button fastButton = new Button("slow");
        Button startButton = new Button("Tick");
        fastButton.setDisable(true);
        
        timerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (timeline != null) {
            		timeline.stop();
            		timeline = null;
            		timerButton.setText("Run");
            		startButton.setDisable(false);
                    fastButton.setDisable(true);
            	}
            	else {
	            	timeline = new Timeline(new KeyFrame(
	            	        Duration.millis(100),
	            	        ae -> tick()));
	            	timeline.setCycleCount(Animation.INDEFINITE);
	            	timeline.play();
            		timerButton.setText("Stop");
            		startButton.setDisable(true);
                    fastButton.setDisable(false);
            	}                
            }
        });
        buttonsPane.getChildren().add(timerButton);

        delay = 100;
        fastButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (timeline != null) {
            		toggleDelay();
            		fastButton.setText(getDelayText());
            		timeline.stop();
	            	timeline = new Timeline(new KeyFrame(
	            	        Duration.millis(delay),
	            	        ae -> tick()));
	            	timeline.setCycleCount(Animation.INDEFINITE);
	            	timeline.play();
            	}
            }
        });
        buttonsPane.getChildren().add(fastButton);

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
        showWorld();
    }

	private void toggleDelay() {
		if (delay == 100) {
			delay = 10;
		}
		else if (delay == 10) {
			delay = 1;
		}
		else {
			delay = 100;
		}
	}

	private String getDelayText() {
		if (delay == 100) {
			return "slow";
		}
		else if (delay == 10) {
			return "medium";
		}
		else {
			return "fast";
		}
	}



    



	private void tick() {
    	world.tick();
    	showWorld();
    }

	private void showWorld() {
		GraphicsContext gc = canvasOverview.getGraphicsContext2D();
		gc.clearRect(0, 0, WORLD_SIZE+2, WORLD_SIZE+2);

		List<VisibleObject> visibleObjects = world.getVisibleObjects(0, 0, WORLD_SIZE, WORLD_SIZE);
		for (VisibleObject visibleObj:visibleObjects) {
			show(gc, visibleObj);
		}
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1.0);
		gc.strokeRect(0, 0, WORLD_SIZE+2, WORLD_SIZE+2);
		
	}



	private void show(GraphicsContext gc, VisibleObject gObj) {
		Color col = Color.rgb(gObj.getCol().getR(), gObj.getCol().getG(), gObj.getCol().getB(), gObj.getCol().getA() * DIV_255);
		double x = HALF_WORLD_SIZE + 1 + gObj.getPos().getX();
		double y = HALF_WORLD_SIZE + 1 + gObj.getPos().getY();
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
		case LINE: {
			double len = gObj.getShape().getSize();
			double dx = gObj.getPos().getDX();
			double dy = gObj.getPos().getDY();
			gc.setStroke(col);
			gc.setLineWidth(2.0);
			gc.strokeRect(x, y, 3+dx*len, 3+dy*len);
			break;
		}
		case SQUARE: {
			double len = gObj.getShape().getSize();
			gc.setFill(col);
			gc.fillRect(x-len, y-len, 2*len, 2*len);
			break;
		}
		default:
			throw new UnsupportedOperationException("SHAPETYPE '"+gObj.getShape().getType()+"' IS NOT SUPPORTED");
		}
	}    

	
	

	private void createWorld() {
		world = new AIWorld(WORLD_SIZE);
		// create background elements (visible, but no collision)
		for (int i=0; i<2; i++) {
			world.add(new BatteryThing(world, RandomUtil.getPosition(WORLD_SIZE).setDir(AIWPosition.RAD_0), 10+RandomUtil.getDouble(15)));
		}
		// create static elements 
		for (int i=0; i<5; i++) {
			world.add(new WallThing(world, RandomUtil.getPosition(WORLD_SIZE).setDir(AIWPosition.RAD_0), RandomUtil.getDouble(WORLD_SIZE/5)));
			world.add(new WallThing(world, RandomUtil.getPosition(WORLD_SIZE).setDir(AIWPosition.RAD_90), RandomUtil.getDouble(WORLD_SIZE/5)));
		}
		// create living elements
		for (int i=0; i<5; i++) {
			world.add(new SimplyMovingThing(world, RandomUtil.getPosition(WORLD_SIZE)));
			world.add(new SpinningThing(world, RandomUtil.getPosition(WORLD_SIZE)));
		}
	}
	
}