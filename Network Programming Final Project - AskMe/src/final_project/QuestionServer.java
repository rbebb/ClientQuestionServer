package final_project;

//SOURCE @ http://makemobiapps.blogspot.com/p/multiple-client-server-chat-programming.html
//used as a framework basis for this server

import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.net.ServerSocket;

//Our central hub of questions

public class QuestionServer {
	
	//server socket
	private static ServerSocket sSocket = null;
	//client socket
	private static Socket cSocket = null;
	//limit to num students that can connect simultaneously
	private static final int maxNumStudents = 25;
	private static final clientThread[] threads = new clientThread[maxNumStudents];
	//data structure for stored questions
	static ArrayList<String> questions = new ArrayList<> ();
	
	public static void main(String args[]) {
		//default port number
		int portNum = 45678;
		
		try {
			sSocket = new ServerSocket(portNum);
		} catch (IOException e1) {
			
		}
		
		//now we accept client connections by creating sockets and passing them to our thread collection
		while (true) {
			try {
				cSocket = sSocket.accept();
				int i = 0;
				for (i = 0; i < maxNumStudents; i++) {
					if (threads[i] == null) {
						(threads[i] = new clientThread(cSocket, threads)).start();
						break;
					}
				}
				if (i == maxNumStudents) {
					PrintStream ps = new PrintStream(cSocket.getOutputStream());
					ps.println("Classroom full!");
					ps.close();
					cSocket.close();
				}
			} catch (IOException e) {
				
			}
		}
	}
}


