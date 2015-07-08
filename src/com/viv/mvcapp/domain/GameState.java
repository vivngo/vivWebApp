package com.viv.mvcapp.domain;

import java.util.Arrays;
import java.util.Date;

public class GameState {
	private Date startTime;
	private Date endTime;
	private boolean won;
	private int treasureLocation;
	private int numButtons;
	private int numRows;
	private String[] buttonText;
	
	
	public GameState() {
		numRows = 3;
		constructorHelper(3);
	}
	
	public GameState(int numRows) {
		this.numRows = numRows;
		constructorHelper(this.numRows);
	}
	
	public GameState(String numRows) {
		this.numRows = Integer.parseInt(numRows);
		constructorHelper(this.numRows);
	}
	
	private void constructorHelper(int numRows) {
		startTime = new Date();
		endTime = null;
		won = false;
		numButtons = numRows * numRows;
		buttonText = generateHomogenousArray(numButtons);
		treasureLocation = pickRandomIndex(numButtons);
	}
	
	private String[] generateHomogenousArray(int number) {
        String[] stringArray = new String[number];
        Arrays.fill(stringArray, "dig");
        return stringArray;
    }
    
    private int pickRandomIndex(int sizeArray) {
        return (int)(Math.random()*sizeArray);
    }
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public void setEndTime() {
		this.endTime = new Date();
	}
	
	public boolean isWon() {
		return won;
	}
	
	public void setWon(boolean won) {
		this.won = won;
	}
	
	public int getTreasureLocation() {
		return treasureLocation;
	}
	
	public void setTreasureLocation(int treasureLocation) {
		this.treasureLocation = treasureLocation;
	}
	
	public int getNumButtons() {
		return numButtons;
	}
	
	public void setNumButtons(int numButtons) {
		this.numButtons = numButtons;
	}
	
	public String[] getButtonText() {
		return buttonText;
	}
	
	public void setButtonText(String[] buttonText) {
		this.buttonText = buttonText;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
    
}
