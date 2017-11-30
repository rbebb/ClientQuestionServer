package final_project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {
	
	private static int numQuestions = 0;
	static String questionString = null;
	private static Pane startPane, teacherPane, studentPane;
	private static ScrollPane teacherScrollPane, studentScrollPane;
	private static int textX = 290;
	private static int textY = 25;
	static boolean submitBtnClick = false;
	static boolean isTeacher = false;
	static boolean isStudent = false;
	static TextArea enterQuestion;

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) throws Exception {
		
//		Program Title
		primaryStage.setTitle("AskMe");
		
		startPane = new Pane();
//		Size for program window (startPane)
		int paneWidth = 1000;
		int paneHeight = 700;
		
		String backgroundString = "-fx-background-image: url('chalkboard-background.jpg'); "
				+ "-fx-background-size: cover; ";
		startPane.setStyle(backgroundString);
		
		Scene startScene = new Scene(startPane, paneWidth, paneHeight);
		primaryStage.setScene(startScene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		ToGUI titleAskMe = new ToGUI();
		titleAskMe.addText(240, 215, "AskMe", "Chalkboard", 175, Color.WHITE, startPane);
		
		ToGUI teacherBtn = new ToGUI();
		teacherBtn.addButton("Teacher", 250, 150, 160, 350, startPane);
		teacherBtn.btn.setStyle("-fx-font-size: 16;");
		ToGUI studentBtn = new ToGUI();
		studentBtn.addButton("Student", 250, 150, 610, 350, startPane);
		studentBtn.btn.setStyle("-fx-font-size: 16;");
		
		
		teacherPane = new Pane();
		teacherPane.setStyle(backgroundString);
		Scene teacherScene = new Scene(teacherPane, paneWidth, paneHeight);
		
		studentPane = new Pane();
		studentPane.setStyle(backgroundString);
		Scene studentScene = new Scene(studentPane, paneWidth, paneHeight);
		
		teacherScrollPane = new ScrollPane();
		teacherScrollPane.setPrefSize(713, 650);
		teacherScrollPane.setLayoutX(275);
		teacherScrollPane.setLayoutY(30);
		teacherScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		teacherPane.getChildren().add(teacherScrollPane);
		
		studentScrollPane = new ScrollPane();
		studentScrollPane.setPrefSize(713, 495);
		studentScrollPane.setLayoutX(275);
		studentScrollPane.setLayoutY(30);
		studentScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		studentPane.getChildren().add(studentScrollPane);
		
		
		enterQuestion = new TextArea();
		enterQuestion.setLayoutX(275);
		enterQuestion.setLayoutY(550);
		enterQuestion.setPrefSize(600, 100);
		studentPane.getChildren().add(enterQuestion);
		
		ToGUI submitBtn = new ToGUI();
		submitBtn.addButton("Submit", 100, 100, 888, 550, studentPane);
		submitBtn.btn.setStyle("-fx-font-size: 16;");
		
		(submitBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				questionString = enterQuestion.getText();
				submitBtnClick = true;
				enterQuestion.clear();
			}
		});
		
		
		ToGUI exitTeacherBtn = new ToGUI();
		String exitTeacherBtnLabel = "Exit";
		exitTeacherBtn.addButton(exitTeacherBtnLabel, 200, 75, 30, 30, teacherPane);
//		Sets the color of the text
		(exitTeacherBtn.btn).setTextFill(Color.RED);
		exitTeacherBtn.btn.setStyle("-fx-font-size: 16;");
		
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
		exitStudentBtn.addButton(exitStudentBtnLabel, 200, 75, 30, 30, studentPane);
		(exitStudentBtn.btn).setTextFill(Color.RED);
		exitStudentBtn.btn.setStyle("-fx-font-size: 16;");
		
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
				
				isTeacher = true;
				
				primaryStage.setScene(teacherScene);
				primaryStage.setResizable(false);
				primaryStage.show();
			}
		});
		
//		Action when student clicks start button
		(studentBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				new Thread(()->{StudentClient.main(null);}).start();
				
				isStudent = true;
				
				primaryStage.setScene(studentScene);
				primaryStage.setResizable(false);
				primaryStage.show();
			}
		});
		
	}

	public static void addQuestionToTeacherGUI(String newQuestion) {
		ToGUI chatMessage = new ToGUI();
		textY = textY+18;
		Platform.runLater(() -> chatMessage.addText(textX, textY, newQuestion, "Verdana", 10, Color.BLACK, teacherPane));
	}
	
	public static void addQuestionToStudentGUI(String newQuestion) {
		ToGUI chatMessage = new ToGUI();
		textY = textY+18;
		Platform.runLater(() -> chatMessage.addText(textX, textY, newQuestion, "Verdana", 10, Color.BLACK, studentPane));
	}
}


