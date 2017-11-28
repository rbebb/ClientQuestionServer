package final_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ProfanityFilter { 
	
	
	public static HashSet<String> makeFilter() {

	
		try (
				Scanner fin = new Scanner(new File("src/explitives"));
				) {
		
			HashSet<String> swears = new HashSet<String>(10);
			//HashSet<String> bangWords = new HashSet< >();

			while (fin.hasNext()) {
			
				String temp = fin.next();
			
				while (temp.charAt(temp.length() - 1) < 65 || temp.charAt(temp.length() - 1) > 122) {
					temp = temp.substring(0, temp.length() - 1);
				}
			
				temp = temp.toLowerCase();
			
				swears.add(temp);
			
				return swears;
			}
		} catch (FileNotFoundException e) {
			System.out.println("It's not there.");
		} catch (NoSuchElementException ex) {
			System.out.println("It's empty.");
		} finally {
		}
		return null;
	}
	
	
	
	
	public static String filterQuestion(String s, HashSet<String> hs) {
		
		//ArrayList<String> words = new ArrayList<String>();
		
		String[] words = s.split("\\W+");
		
		for (String ss : words) {
			if (hs.contains(ss)) {
				String ph = "";
				for (int i = 0; i < ss.length(); i++) {
					ph = ph + "*";
				}
				ss = ph;
			}
		}
		
		s = String.join(" ", words);
		
		return s;
	}
	
}

