/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 * 
 * Date: 20-2-2016
 * Programmer: Peter Lock
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	public GLabel state;
	public GLabel misses;
	private int incorrect = 0;
	private boolean flag = true;
	private boolean flag2 = true;

	
	private String fails = "";
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		this.removeAll();
		
		GLine beam = new GLine(getWidth()/2+ADJUSTMENT, BEAM_FROM_TOP, getWidth()/2-BEAM_LENGTH, BEAM_FROM_TOP);
		add(beam);
		
		GLine scaffold = new GLine(getWidth()/2-BEAM_LENGTH,BEAM_FROM_TOP, getWidth()/2-BEAM_LENGTH, BEAM_FROM_TOP + SCAFFOLD_HEIGHT);
		add(scaffold);
		
		GLine rope = new GLine(getWidth()/2+ADJUSTMENT, BEAM_FROM_TOP, getWidth()/2 + ADJUSTMENT, BEAM_FROM_TOP+ROPE_LENGTH);
		add(rope);
		
		
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {

		
		if(!flag) {
			this.remove(state);
		}

		state = new GLabel(word);
		state.setFont(WORD_FONT);
		add(state, 50, getHeight()-WORD_FROM_BOTTOM);	
		flag = false;

	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {

		incorrect++;
		fails += letter;
		
		if(!flag2) {
			this.remove(misses);
		}

		misses = new GLabel(fails);
		misses.setFont(WORD_FAIL_FONT);
		add(misses, 50, getHeight()-(WORD_FROM_BOTTOM - 30));	
		flag2 = false;
		
		addBodyPart();
		
	}

private void addBodyPart() {
	switch(incorrect){
	case 1: {
		//Add the head
		GOval head = new GOval(HEAD_RADIUS*2, HEAD_RADIUS*2);
		head.setLocation(getWidth()/2+ADJUSTMENT - head.getWidth()/2, BEAM_FROM_TOP+ROPE_LENGTH);
		add(head);

		break;
	}
	case 2: {
		//Add trunk
		GLine trunk = new GLine(getWidth()/2+ADJUSTMENT, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2, getWidth()/2+ADJUSTMENT, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
		add(trunk);		
		
		break;
	}
	
	case 3: {
		//Add right arm upper
		GLine rightArmUpper = new GLine(getWidth()/2+ADJUSTMENT, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, getWidth()/2+ADJUSTMENT+UPPER_ARM_LENGTH, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
		add(rightArmUpper);
		
		//Add right arm lower
		GLine rightArmLower = new GLine(getWidth()/2+ADJUSTMENT+UPPER_ARM_LENGTH, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, getWidth()/2+ADJUSTMENT+UPPER_ARM_LENGTH, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		add(rightArmLower);	
		
		break;
	}
	
	case 4:{
		//Add left arm upper
		GLine leftArmUpper = new GLine(getWidth()/2+ADJUSTMENT, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, getWidth()/2-(ADJUSTMENT+UPPER_ARM_LENGTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
		add(leftArmUpper);
		
		//Add left arm lower
		GLine leftArmLower = new GLine(getWidth()/2+ADJUSTMENT-UPPER_ARM_LENGTH, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, getWidth()/2-(ADJUSTMENT+UPPER_ARM_LENGTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		add(leftArmLower);		
		
		break;
	}
	
	case 5:{
		//Add left hip
		GLine leftHip = new GLine(getWidth()/2+ADJUSTMENT, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, getWidth()/2 - (ADJUSTMENT + HIP_WIDTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
		add(leftHip);
		
		//Add left leg
		GLine leftLeg = new GLine(getWidth()/2-(ADJUSTMENT+HIP_WIDTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, getWidth()/2 - (ADJUSTMENT+HIP_WIDTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
		add(leftLeg);	
		
		break;
	}
	
	case 6: {
		//Add left foot
		GLine leftFoot = new GLine(getWidth()/2 - (ADJUSTMENT+HIP_WIDTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, getWidth()/2 - (ADJUSTMENT+HIP_WIDTH+FOOT_LENGTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
		add(leftFoot);		
		
		break;
	}
	
	case 7: {
		//Add right hip
		GLine rightHip = new GLine(getWidth()/2+ADJUSTMENT, BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, getWidth()/2 + (ADJUSTMENT + HIP_WIDTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
		add(rightHip);
		
		//Add right leg
		GLine rightLeg = new GLine(getWidth()/2+(ADJUSTMENT+HIP_WIDTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, getWidth()/2 + (ADJUSTMENT+HIP_WIDTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
		add(rightLeg);		
		
		break;
	}
	
	case 8:{
		//Add right foot
		GLine rightFoot = new GLine(getWidth()/2 + (ADJUSTMENT+HIP_WIDTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, getWidth()/2 + (ADJUSTMENT+HIP_WIDTH+FOOT_LENGTH), BEAM_FROM_TOP+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
		add(rightFoot);	
		
		break;
	}
	default: break;
	}
}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int BEAM_FROM_TOP = 100;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int ADJUSTMENT = 0;
	private static final int WORD_FROM_BOTTOM = 150;
	private static final String WORD_FONT = "Courier-32";
	private static final String WORD_FAIL_FONT = "Courier-22";

}
