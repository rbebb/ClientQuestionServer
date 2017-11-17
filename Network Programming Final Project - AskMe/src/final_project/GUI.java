package final_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) throws Exception {
		
//		Program Title
		primaryStage.setTitle("AskMe");
		
		Pane startPane = new Pane();
//		Size for program window (startPane and gamePane)
		int paneWidth = 1000;
		int paneHeight = 700;
		
		new QuestionServer();
		
		if ()
		ToGUI chatMessage = new ToGUI();
		chatMessage.addText(100, 100, QuestionServer.questions.toString(), "Verdana", 20, Color.RED, startPane);
		
		Scene startScene = new Scene(startPane, paneWidth, paneHeight);
		primaryStage.setScene(startScene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
