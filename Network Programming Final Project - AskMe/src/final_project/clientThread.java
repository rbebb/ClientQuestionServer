package final_project;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

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
				if (question.contains("sweet"))
				{
					StringBuilder filteredQuestion = new StringBuilder(question);
					filteredQuestion.replace(6, 11, "*****");
					String filteredQuestionString = filteredQuestion.toString();
					System.out.println(filteredQuestionString);
					continue;
				}
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
