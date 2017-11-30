package final_project;

//SOURCE @ http://makemobiapps.blogspot.com/p/multiple-client-server-chat-programming.html
//used as a basis for student client

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class StudentClient implements Runnable {
	
	//client socket
	private static Socket cSocket = null;
	//output to server
	private static PrintStream ps = null;
	//input from keyboard
	private static DataInputStream dis = null;
	static boolean yes = true;
	
	public static void main(String args[]) {
		
		//port number
		int portNum = 45678;
		//ip of server
		String ip = "10.200.27.248";
		
		BufferedReader question = null;
		
		try {
			cSocket = new Socket(ip, portNum);
			dis = new DataInputStream(cSocket.getInputStream());
			ps = new PrintStream(cSocket.getOutputStream());
			question = new BufferedReader(new InputStreamReader(System.in));
		} catch (UnknownHostException e) {
			
		} catch (IOException e) {
			
		}
		
		try {
			
			new Thread(new StudentClient()).start();
			while (yes) {
				System.out.println(GUI.submitBtnClick);
				if (GUI.submitBtnClick) {
					System.out.print("hello");
					ps.println(GUI.questionString);
//					GUI.submitBtnClick = false; //take gui string and put into print stream
				}
//				ps.println(question.readLine().trim());
			}
			
			ps.close();
			dis.close();
			cSocket.close();			
		} catch (IOException e) {
			
		}
	}

/*
 * Create a thread to read from the server. (non-Javadoc)
 * 
 * @see java.lang.Runnable#run()
 */
	public void run() {
  /*
   * Keep on reading from the socket till we receive "Bye" from the
   * server. Once we received that then we want to break.
   */
		String responseLine;
		try {
			while ((responseLine = dis.readLine()) != null) {
//				System.out.println(responseLine);
				if (GUI.isStudent)
				{
					GUI.addQuestionToStudentGUI(responseLine);
				}
				if (responseLine.indexOf("*** Bye") != -1)
					break;
			}
			yes = false;
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}
	}
}




