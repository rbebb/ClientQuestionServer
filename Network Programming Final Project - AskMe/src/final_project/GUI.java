package final_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {
	
	private static int numQuestions = 0;
	
	static String questionString = "";

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
		
		Scene startScene = new Scene(startPane, paneWidth, paneHeight);
		primaryStage.setScene(startScene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
		ToGUI teacherBtn = new ToGUI();
		teacherBtn.addButton("Teacher", 250, 150, 165, 300, startPane);
		ToGUI studentBtn = new ToGUI();
		studentBtn.addButton("Student", 250, 150, 615, 300, startPane);
		
		
		Pane teacherPane = new Pane();
		Scene teacherScene = new Scene(teacherPane, paneWidth, paneHeight);
		
		
		Pane studentPane = new Pane();
		Scene studentScene = new Scene(studentPane, paneWidth, paneHeight);
		
		TextArea enterQuestion = new TextArea();
		enterQuestion.setLayoutX(275);
		enterQuestion.setLayoutY(550);
		enterQuestion.setPrefSize(600, 100);
		studentPane.getChildren().add(enterQuestion);
		
		ToGUI submitBtn = new ToGUI();
		submitBtn.addButton("Submit", 100, 100, 888, 550, studentPane);
		
		(submitBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				questionString = enterQuestion.getText();
				QuestionServer.questions.add(questionString);
				enterQuestion.clear();
			}
		});
		
		
		ToGUI exitTeacherBtn = new ToGUI();
		String exitTeacherBtnLabel = "Exit";
		exitTeacherBtn.addButton(exitTeacherBtnLabel, 140, 55, 20, 20, teacherPane);
//		Sets the color of the text
		(exitTeacherBtn.btn).setTextFill(Color.RED);
		
//		Action when teacher clicks the exit button
		(exitTeacherBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		ToGUI exitStudentBtn = new ToGUI();
		String exitStudentBtnLabel = "Exit";
		exitStudentBtn.addButton(exitStudentBtnLabel, 140, 55, 20, 20, studentPane);
		(exitStudentBtn.btn).setTextFill(Color.RED);
		
//		Action when student clicks the exit button
		(exitStudentBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				System.exit(0);
			}
		});

		
//		Action when teacher clicks start button
		(teacherBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				new Thread(()->{QuestionServer.main(null);}).start();
				
				primaryStage.setScene(teacherScene);
				primaryStage.setResizable(false);
				primaryStage.show();
				
				while (QuestionServer.questions.size() > numQuestions)
				{
					numQuestions++;
					ToGUI chatMessage = new ToGUI();
					chatMessage.addText(300, 300, QuestionServer.questions.get(QuestionServer.questions.size()-1), "Verdana", 20, Color.BLACK, teacherPane);
				}
			}
		});
		
//		Action when student clicks start button
		(studentBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				new Thread(()->{StudentClient.main(null);}).start();
				
				primaryStage.setScene(studentScene);
				primaryStage.setResizable(false);
				primaryStage.show();
			}
		});
		
	}

}
