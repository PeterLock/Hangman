/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains the implementation of the HangmanLexicon
 * class.
 * 
 * Date: 20-3-2016
 * Programmer: Peter Lock
 */

import acm.program.ConsoleProgram;
import acm.util.*;

import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon extends ConsoleProgram {

	private ArrayList<String> strlist = new ArrayList<String>();
	private int wordCount = 0;
	
	public HangmanLexicon(){
		
		BufferedReader reader = null;
		
		if(reader == null){
			
			try{
				reader = new BufferedReader(new FileReader("KidsLexicon.txt"));
				System.out.println("File opened successfully");
			} catch (IOException ex){
				System.out.println("Nice try punk. That file doesnt exist.");
			}
			
		}
		
		try{
			while(true){
				String line = reader.readLine();
				if(line == null) break;
				
				strlist.add(line.toUpperCase());
				wordCount++;
				
				System.out.println("Read line: [" + line + "]");
			}
			reader.close();
			System.out.println("Words read successfully");
			System.out.println("File closed successfully");
			System.out.println("Words read: " + wordCount);
		} catch(IOException ex){
			throw new ErrorException(ex);
		}
		
	}
	
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordCount;
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
					
		return strlist.get(index);
		
	}

}
