package com.samuraichikx;

import java.io.Serializable;
import java.util.Random;

import android.graphics.Bitmap;

public class Bubble implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7569949223686091760L;
	private int xcoord, ycoord, scorevalue;
	private Bitmap bubblecolor;
	
	public Bubble (int x, int y, Bitmap color, int popvalue)
	{
		this.xcoord = x;
		this.ycoord = y;
		this.bubblecolor = color;
		this.scorevalue = popvalue;
	}
	
	public void fall()
	{
		ycoord+= 5;
	}
	
	public int getxcoord()
	{
		return xcoord;
	}
	public int getycoord()
	{
		return ycoord;
	}
	
	public Bitmap getBitmap ()
	{
		return bubblecolor;
	}
	
	public void drift(){
		Random r = new Random();
		int lor = r.nextInt(2);
		switch(lor){
		case(0):
			xcoord+= 5;
		case(1): 
			xcoord-=5;
		}
	}
	
	public int addScore(){
		return this.scorevalue;
	}
	public int getScoreValue(){
		return this.scorevalue;
	}
	

}
