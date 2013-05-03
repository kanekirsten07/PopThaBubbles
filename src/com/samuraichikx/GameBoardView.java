package com.samuraichikx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class GameBoardView extends View implements OnTouchListener, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8447136390653306335L;
	int bubblenum = 0;
	private int score = 0;
	public int bubblesize = 50;
	private Bitmap bubble , end;
	public static final int PAUSE = 0;
    public static final int RUNNING = 1;
    ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    boolean paused = false;
    Context c;
    SharedPreferences prefs;
    public boolean gameover = false;
    Editor editor;
    
    private long _spawnDelay = 500;
	
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int savedscore){
		this.score =savedscore;
	}
	
	
	 private RefreshHandler _redrawHandler = new RefreshHandler();

	    class RefreshHandler extends Handler {

	        @Override
	        public void handleMessage(Message message) {
	            GameBoardView.this.update();
	            GameBoardView.this.invalidate();
	        }

	        public void sleep(long delayMillis) {
	            this.removeMessages(0);
	            sendMessageDelayed(obtainMessage(0), delayMillis);
	        }
	    };

	    public GameBoardView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        
	        c = context;
			prefs = c.getSharedPreferences("MyPrefsKey", 0);
			editor = prefs.edit();
			/*
			if(prefs == null){
				Log.d("GameBoardView Constructor", "prefs is null");
			}
			else{
				Log.d("GameBoardView Constructor", "prefs is not null: " + prefs.toString());
			}
			Log.d("TEST", "B");
	        */
	        initGameBoardView();
	    }
	    
	    public void setMode(int mode) {
	        if (mode == RUNNING) {
	        	paused = false;
	            update();
	            return;
	        }
	        if (mode == PAUSE) {
	        	paused = true;
	            // TODO: implement.
	        }
	    }
	
	@Override
    protected void onDraw(Canvas canvas) {
		
		 
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background2));
        
		if(gameover){
			handleGameOver(canvas, background);
			setFocusable(false);
			
			return;
			
		}
		 
		
        Random r = new Random();
        //x-coordinate
        Random r1 = new Random();
        
        //y-coordinate
        Random r2 = new Random();
       
        
        
        
        for(int i = 0; i < Integer.parseInt(OptionsActivity.getNumBubbles(super.getContext())); i++){
        	bubblenum = r.nextInt(9);
        	 {
             
        		 if(bubblenum == 0){
        			 bubble = BitmapFactory.decodeResource(getResources(), R.drawable.bluebubble);
        			 Bubble b = new Bubble(r1.nextInt(getWidth()-100), r2.nextInt(getHeight()), bubble, 10);
        		 bubbles.add(b);

        	 }else if(bubblenum == 1)
        	 {
        		 bubble  = BitmapFactory.decodeResource(getResources(), R.drawable.greenbubble);
        		 Bubble b = new Bubble(r1.nextInt(getWidth()-100), r2.nextInt(getHeight()), bubble, 20);
        		 bubbles.add(b);

        	 }else if(bubblenum == 2)
        	 {
        		 bubble = BitmapFactory.decodeResource(getResources(), R.drawable.redbubble);
        		 Bubble b = new Bubble(r1.nextInt(getWidth()-100), r2.nextInt(getHeight()), bubble, 30);
        		 bubbles.add(b);
        	 }	
        	 else if(bubblenum == 3){
        		 bubble = BitmapFactory.decodeResource(getResources(), R.drawable.lightbluebubble);
        		 Bubble b = new Bubble(r1.nextInt(getWidth()-100), r2.nextInt(getHeight()), bubble, 40);
        		 bubbles.add(b);

        	 }else if(bubblenum == 4){
        		 bubble = BitmapFactory.decodeResource(getResources(), R.drawable.purplebubble);
        		 Bubble b = new Bubble(r1.nextInt(getWidth()-100), r2.nextInt(getHeight()), bubble, 50);
        		 bubbles.add(b);


        	 }else if(bubblenum == 5)
        	 {
        		 bubble = BitmapFactory.decodeResource(getResources(), R.drawable.orangebubble);
        		 Bubble b = new Bubble(r1.nextInt(getWidth()-100), r2.nextInt(getHeight()-100), bubble, 60);
        		 bubbles.add(b);
        	 }else if(bubblenum == 6)
        	 {
        		 bubble = BitmapFactory.decodeResource(getResources(), R.drawable.goldbubble);
        		 Bubble b = new Bubble(r1.nextInt(getWidth()-100), r2.nextInt(getHeight()-100), bubble, 70);
        		 bubbles.add(b);
        	 }else if (bubblenum == 7 || bubblenum == 8)
        	 {
        		 bubble = BitmapFactory.decodeResource(getResources(), R.drawable.bombbubble);
        		 bubble = Bitmap.createScaledBitmap(bubble, 150, 130, true);
        		 Bubble b = new Bubble(r1.nextInt(getWidth()-100), r2.nextInt(getHeight()-100), bubble, 100);
        		 bubbles.add(b);
        	 }


             	
             	
             }
               	
        }
       
        
        
        
        // draw background
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        background.setStyle(Style.FILL); 
        background.setColor(Color.BLACK);
		background.setTextSize(20);
        canvas.drawText("Score: " + score, getWidth()-200,30 , background);
        canvas.drawText("HighScore: " + prefs.getInt("HighScore", 0), getWidth()-350, 30, background);
        
        //draw bubble
        for(int i = 0; i < bubbles.size(); i++)
        {
        	Bitmap bm = bubbles.get(i).getBitmap();
        	bubblesize = bm.getHeight();
        	int x = bubbles.get(i).getxcoord();
        	int y = bubbles.get(i).getycoord();
        canvas.drawBitmap(bm, x, y, background);
        bubbles.get(i).fall();
        bubbles.get(i).drift();
        if(bubbles.get(i).getycoord()+bubblesize>= getHeight()){
        	bubbles.get(i).getBitmap().recycle();
			bubbles.remove(i);
        }
        }
}
	
	private void update() {
        
        _redrawHandler.sleep(_spawnDelay);
    }
	
	private void initGameBoardView() {
        setFocusable(true);
       // Log.d("Testing Logcat", "It's good chief");
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent m)
	{
		for(int i = 0; i< bubbles.size(); i++){
			if(bubbles.get(i).getxcoord()<= m.getX() && (bubbles.get(i).getxcoord() + bubblesize)>= m.getX() && bubbles.get(i).getycoord()<= m.getY() && (bubbles.get(i).getycoord() + bubblesize)>= m.getY())
			{
				Log.d("pop!", "I popped!");
				if(bubbles.get(i).getScoreValue()>=100){
					this.gameover = true;
				}
				score += bubbles.get(i).addScore();
				bubbles.get(i).getBitmap().recycle();
				bubbles.remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent m) {
		//Log.d("onTouch", "moo");
		for(Bubble b: bubbles){
			if(b.getxcoord()<= m.getX() && (b.getxcoord() + bubblesize)>= m.getX() && b.getycoord()<= m.getY() && (b.getycoord() + bubblesize)>= m.getY())
			{
				
				//Log.d("pop!", "I popped!");
				b.getBitmap().recycle();
				return true;
			}
		}
		return false;
	}
	
	public void handleGameOver(Canvas c, Paint p)
	{
		this.setMode(this.PAUSE); 
		bubbles.clear();
		
		 
		
		if(prefs.getInt("HighScore", 0) < this.score){
		editor.putInt("HighScore", this.score);
		editor.commit();
		}
		editor.putBoolean("HasSavedFile", false);
		
		c.drawText("Game Over", getWidth()/3, getHeight()/3, p);
		
		
		
	}
	
}
