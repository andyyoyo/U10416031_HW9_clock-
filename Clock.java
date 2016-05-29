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
	
		//make the buttons and  	
		Button btStart = new Button("Start");
		Button btStop = new Button("Stop");
		btStart.setPrefSize(60,50);
		btStop.setPrefSize(60,50);
		
		HBox h = new HBox(10);
		BorderPane pane = new BorderPane();
		h.getChildren().addAll(btStart, btStop);
		pane.setCenter(clock);
		pane.setBottom(h);		
		
		EventHandler<ActionEvent> eventHandler = e->{
			clock.setCurrentTime();
		};
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		//Set button action
		btStart.setOnAction(e -> {
			//Play animation
			animation.play();
		});
		
		//Set button action
		btStop.setOnAction(e -> {
			//Stop animation
			animation.stop();
		});		
		
		Scene scene = new Scene(pane,250,280);
		primaryStage.setTitle("ClockAnimation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main (String[] args){
		launch(args);
	}
}
