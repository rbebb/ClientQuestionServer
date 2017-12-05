package final_project;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The class ToGUI
 * COMP2100-07, Fall 2017
 * Due: December 5th, 2017
 * 
 ****************************
 * Description 
 ****************************
 *
 * This program adds a button or text to a desired pane.
 * 
 * For example, if a user wants to add text to the pane, they will specify the x 
 * position of the text in the desired pane, the y position of the text in the 
 * desired pane, what the text will say, the font, the text size, the color of the 
 * text, and the pane to add the text to.
 *
 ****************************
 * Analysis
 ****************************
 *
 * Inputs: The necessary parameters for a button or text.
 * 
 * Outputs: Button or text to be displayed to the GUI
 *
 ****************************
 * Pseudocode
 ****************************
 *
 * 1. Get the parameters for the button or text to add
 * 2. Display the element being added in the GUI
 *
 * @author Ryan Bebb
 */

public class ToGUI {
	
	ImageView currentImageView;
	Button btn;
	Text text;
	
	/**
	 * Allows the user to make a ToGUI object
	 * to add an element (image, button, or text)
	 * to the GUI.
	 */
	public ToGUI() {
		
	}
	
	/**
	 * Adds a button to the 
	 * associated pane
	 * 
	 * @param label Text for the button
	 * @param width The desired width of the button
	 * @param height The desired height of the button
	 * @param x The desired x position in the pane
	 * @param y The desired y position in the pane
	 * @param pane The pane to add the button to
	 */
	public void addButton(String label, int width, int height, int x, int y, Pane pane) {
		Button btn = new Button();
		btn.setText(label);
//		Sets the width and height of the button
		btn.setPrefSize(width, height);
		btn.setLayoutX(x);
		btn.setLayoutY(y);
		this.btn = btn;
		pane.getChildren().add(btn);
	}
	
	/**
	 * Adds text to the 
	 * associated pane
	 * 
	 * @param x The desired x position in the pane
	 * @param y The desired y position in the pane
	 * @param label Text that will go inside the text being added
	 * @param font The desired font for the text
	 * @param labelSize The desired size for the text
	 * @param c The desired color for the text
	 * @param pane The pane to add the text to
	 */
	public void addText(int x, int y, String label, String font, int labelSize, Color c, Pane pane) {
		Text text = new Text(x, y, label);
		text.setFont(Font.font(font, labelSize));
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(c);
		this.text = text;
		pane.getChildren().add(text);
	}

	
}
