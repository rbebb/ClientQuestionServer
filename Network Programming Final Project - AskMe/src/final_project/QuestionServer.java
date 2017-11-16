package final_project;

//SAUCE @ http://makemobiapps.blogspot.com/p/multiple-client-server-chat-programming.html
//used as a framework basis for this server

import java.io.DataInputStream;
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
	//limit to num students that can connect simultaniously
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

/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. The thread broadcast the incoming messages to all clients and
 * routes the private message to the particular client. When a client leaves the
 * chat room this thread informs also all the clients about that and terminates.
 */
//SAUCE @ http://makemobiapps.blogspot.com/p/multiple-client-server-chat-programming.html
//used as a framework basis for this server

class clientThread extends Thread {
	private String student = null;
	private Socket cSocket = null;
	private int maxNumStudents = 25;
	private final clientThread[] threads;
	
	public clientThread(Socket cSocket, clientThread[] threads) {
		this.cSocket = cSocket;
		this.threads = threads;
		maxNumStudents = threads.length;
	}
	
	public void run() {
		int maxNumStudents = this.maxNumStudents;
		clientThread[] threads = this.threads;
		
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(cSocket.getInputStream());
		} catch (IOException e) {
			
		}
		
		PrintStream ps = null;
		try {
			ps = new PrintStream(cSocket.getOutputStream());
		} catch (IOException e) {
			
		}
		
		String student;
		while (true) {
			ps.println("What's your name?");
			try {
				student = dis.readLine();
				break;
			} catch (IOException e) {
				break;
			}
		}
		
		
		while (true) {
			
			try {
				String question = dis.readLine();
				//filter profanity
				System.out.println(question);
				QuestionServer.questions.add(question);
				
			} catch (IOException e) {
				break;
			}
			
		}
		
		
		try {
			dis.close();
		} catch (IOException e) {
			
		}
		ps.close();
		try {
			cSocket.close();
		} catch (IOException e) {
			
		}
		
	}
	
	
	
	
	
}




















