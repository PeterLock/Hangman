/*
 * File: Hangman.java
 * ------------------
 * This program plays the game Hangman
 * 
 * Date: 22-2-2016
 * Programmer: Peter Lock
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private HangmanCanvas canvas;
	
	private RandomGenerator rgen = new RandomGenerator();
	private HangmanLexicon lexicon = new HangmanLexicon();
	
	private String ANSWER_FONT = "Courier-48";

	private int guess = 8;
	    	
	private String word;
    private char[] state;
    
    public void init(){
    	canvas = new HangmanCanvas();
    	add(canvas);
    }

    public void run() {
    	
    	this.setSize(800, this.getHeight());
    	
    	canvas.reset();
    	intializeState();

    	
    	println("Welcome to Hangman!");
    	while(true){
        	
    		print("The word now looks like this: ");
    		showState();
        	println();

    		println("You have " + guess + " guessess left.");

    		
    		if((guess == 0) && !(guessed())) {
    			println("You're completely hung.");
    			println("The word was: " + word);
    			display();
    			break;
    		}
    		
    		if(guessed()){
    			println("You guessed the word: " + word);
    			display();
    			break;
    		}
    		
    		String input = readLine("Your guess: ").toUpperCase();
    		
    		if(input.length() >1){
    			if(input.equals(word.toUpperCase())){
    				println("That guess is correct.");
    				canvas.displayWord(stateToString());
    				display();
    				break;
    			} else{
    				println("That guess is incorrect.");
    				guess -=1;
    				canvas.noteIncorrectGuess(input.charAt(0));
    			}
    		}
    		
    		if(compare(input.charAt(0))){
    			println("That guess is correct.");
    			canvas.displayWord(stateToString());
    		} else{
    			println("That guess is incorrect.");    			
        		guess -=1;
    			canvas.noteIncorrectGuess(input.charAt(0));
    		}
    				
    	}
       
    }


	private void display() {
		GLabel answer = new GLabel(word);
		answer.setFont(ANSWER_FONT);
		answer.setLocation(canvas.getWidth()/2 - answer.getWidth()/2, canvas.getHeight()-50);
		canvas.add(answer);
	}

	private boolean compare(char input) {

    	boolean flag = false;
    	
    	for(int i = 0; i < word.length(); i++){
    		
    		if(word.charAt(i) == input){
    			state[i] = input;
    			flag = true;
    		}
    		
    	}
    	
    	return flag;
	}

	private boolean guessed() {
    	
    	for(int i = 0; i < word.length(); i++){
    		
    		if(word.charAt(i) != state[i]) return false;
    	
    	}
    	
		return true;
	}

	private void showState() {
    	for(int i = 0; i < state.length; i++) print(state[i] + " ");
	}

	private void intializeState() {
    	word = lexicon.getWord(rgen.nextInt(0, lexicon.getWordCount()-1));
    	state = new char[word.length()];
    	
    	
    	if(word.length() > 10){
    		ANSWER_FONT = "Courier-24";
    	}
    	
    	for(int i = 0; i < word.length(); i++){
    		if(word.charAt(i) == ' '){
    			state[i] = ' ';
    		} else state[i] = '-';
    	}
    	
    	canvas.displayWord(stateToString());
    	        	
	}
	
	private String stateToString(){
		String arrayToString = "";
		for(int i = 0; i < word.length(); i++){
			
			arrayToString += state[i];
			
		}
		
		return arrayToString;
		
	}

}
