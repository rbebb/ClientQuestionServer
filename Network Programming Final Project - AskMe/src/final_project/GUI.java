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
//		Size for program window (startPane)
		int paneWidth = 1000;
		int paneHeight = 700;
		
		QuestionServer.main(null);
		
		int numQuestions = 0;
		
		Scene startScene = new Scene(startPane, paneWidth, paneHeight);
		primaryStage.setScene(startScene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		while (QuestionServer.questions.size() > numQuestions)
		{
			numQuestions++;
			ToGUI chatMessage = new ToGUI();
			chatMessage.addText(100, 100, QuestionServer.questions.get(QuestionServer.questions.size()-1), "Verdana", 20, Color.RED, startPane);
		}
	}

}
