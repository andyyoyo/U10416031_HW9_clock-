import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Clock extends Application{
	
	public void start(Stage primaryStage){
		
		ClockPane clock = new ClockPane();
		
		EventHandler<ActionEvent> eventHandler = e->{
			clock.setCurrentTime();
		};
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		Scene scene = new Scene(clock,250,280);
		primaryStage.setTitle("ClockAnimation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main (String[] args){
		launch(args);
	}
}
