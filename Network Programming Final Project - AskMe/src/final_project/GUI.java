package final_project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
	
	static String questionString = null;
	static Pane startPane, teacherPane, studentPane, teacherPaneQuestions, studentPaneQuestions;
	private static int textX = 290;
	private static int textY = 30;
	private static int textXX = 30;
	private static int textYY = 115;
	static boolean submitBtnClick = false;
	static boolean isTeacher = false;
	static boolean isStudent = false;
	static TextArea enterQuestion;

	public static void main(String[] args) {
		launch(args);

	}
	  
	public void start(Stage primaryStage) throws Exception {
		
//		Program Title
		primaryStage.setTitle("AskMe!");
		 
		startPane = new Pane();
//		Size for program window (startPane)
		int paneWidth = 1000;
		int paneHeight = 700;
		
		String backgroundImageString = "-fx-background-image: url('01_free-soft-blurry-background.jpg'); "
				+ "-fx-background-size: cover; ";
		startPane.setStyle(backgroundImageString);
		
		Scene startScene = new Scene(startPane, paneWidth, paneHeight);
		primaryStage.setScene(startScene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		ToGUI titleAskMe = new ToGUI();
		titleAskMe.addText(105, 340, "Ask", "Impact", 270, Color.CORNFLOWERBLUE, startPane);
		titleAskMe.addText(500, 340, "Me!", "Impact", 270, Color.GOLD, startPane);
		
		
		ToGUI teacherBtn = new ToGUI();
		teacherBtn.addButton("Teacher", 280, 130, 150, 450, startPane);
		teacherBtn.btn.setStyle("-fx-font-family: Lato;"
				+ "-fx-font-size: 40;");
		(teacherBtn.btn).setTextFill(Color.CORNFLOWERBLUE);
		ToGUI studentBtn = new ToGUI();
		studentBtn.addButton("Student", 280, 130, 570, 450, startPane);
		studentBtn.btn.setStyle("-fx-font-family: Lato;"
				+ "-fx-font-size: 40;");
		(studentBtn.btn).setTextFill(Color.CORNFLOWERBLUE);
		
		
		teacherPane = new Pane();
		teacherPane.setStyle(backgroundImageString);
		Scene teacherScene = new Scene(teacherPane, paneWidth, paneHeight);
		
		studentPane = new Pane();
		studentPane.setStyle(backgroundImageString);
		Scene studentScene = new Scene(studentPane, paneWidth, paneHeight);
		
		teacherPaneQuestions = new Pane();
		teacherPaneQuestions.setPrefSize(700, 640);
		teacherPaneQuestions.setLayoutX(275);
		teacherPaneQuestions.setLayoutY(30);
		teacherPaneQuestions.setStyle("-fx-background-color: whitesmoke;");
		teacherPane.getChildren().add(teacherPaneQuestions);
		
		studentPaneQuestions = new Pane();
		studentPaneQuestions.setPrefSize(700, 515);
		studentPaneQuestions.setLayoutX(275);
		studentPaneQuestions.setLayoutY(30);
		studentPaneQuestions.setStyle("-fx-background-color: whitesmoke;");
		studentPane.getChildren().add(studentPaneQuestions);
		
		
		enterQuestion = new TextArea();
		enterQuestion.setLayoutX(275);
		enterQuestion.setLayoutY(570);
		enterQuestion.setPrefSize(580, 100);
		enterQuestion.setFont(Font.font("Lato"));
		enterQuestion.setStyle("-fx-control-inner-background: whitesmoke;");
		studentPane.getChildren().add(enterQuestion);
		
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
		
		ToGUI submitBtn = new ToGUI();
		submitBtn.addButton("Submit", 100, 100, 875, 570, studentPane);
		submitBtn.btn.setStyle("-fx-font-family: Lato;"
				+ "-fx-font-size: 21;");
		(submitBtn.btn).setTextFill(Color.CORNFLOWERBLUE);
		
		(submitBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				questionString = enterQuestion.getText();
				submitBtnClick = true;
				StudentClient.sendQuestion(questionString);
				enterQuestion.clear();
			}
		});
		
		
		ToGUI exitTeacherBtn = new ToGUI();
		String exitTeacherBtnLabel = "Exit";
		exitTeacherBtn.addButton(exitTeacherBtnLabel, 200, 75, 30, 30, teacherPane);
		(exitTeacherBtn.btn).setTextFill(Color.CORNFLOWERBLUE);
		exitTeacherBtn.btn.setStyle("-fx-font-family: Lato;"
				+ "-fx-font-size: 28;");
		
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
		(exitStudentBtn.btn).setTextFill(Color.CORNFLOWERBLUE);
		exitStudentBtn.btn.setStyle("-fx-font-family: Lato;"
				+ "-fx-font-size: 28;");
		
//		Action when student clicks the exit button
		(exitStudentBtn.btn).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				System.exit(0);
			}
		});
		
	}
	

	public static void addQuestionToTeacherGUI(String newQuestion) {
		ToGUI chatMessage = new ToGUI();
		textY = textY+30;
		Platform.runLater(() -> chatMessage.addText(textX, textY, newQuestion, "Lato", 18, Color.BLACK, teacherPane));
	}
	
	public static void addQuestionToStudentGUI(String newQuestion) {
		ToGUI chatMessage = new ToGUI();
		textY = textY+30;
		Platform.runLater(() -> chatMessage.addText(textX, textY, newQuestion, "Lato", 18, Color.BLACK, studentPane));
	}
	
	public static void addStudentNameToTeacherGUI(String name) {
		ToGUI nameText = new ToGUI();
		textYY = textYY+45;
		Platform.runLater(() -> nameText.addText(textXX, textYY, name, "Lato", 30, Color.WHITE, teacherPane));
	}
}

