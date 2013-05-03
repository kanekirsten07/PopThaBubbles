package com.samuraichikx;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class HighScoreActivity extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
      int score = 0;
      TextView textView = (TextView) findViewById(R.id.highscoretext);
        try {
        	FileInputStream fileIn = new FileInputStream("highscore.ser");
        	ObjectInputStream in = new ObjectInputStream(fileIn);
        	try {
				score = (Integer)in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				textView.setText("0");
			}
			
		     textView.setText(score);
        	in.close();
        	fileIn.close();
        	
        }catch(IOException i){textView.setText("0");}
       
       
      
      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_high_score, menu);
        return true;
    }

    
}
