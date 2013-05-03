package com.samuraichikx;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;

public class PopThaBubblesActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button newgame = (Button)findViewById(R.id.newgame);
        Button options = (Button)findViewById(R.id.options);
        Button resume = (Button)findViewById(R.id.resume);
        Button howto = (Button)findViewById(R.id.howto);
       // Button highscore = (Button)findViewById(R.id.highscore);
        newgame.setOnClickListener(this);
        
        options.setOnClickListener(this);
        resume.setOnClickListener(this);
        howto.setOnClickListener(this);
       // highscore.setOnClickListener(this);
    }
   
    
		
		
		public void onClick(View v) {
			MediaPlayer mp = MediaPlayer.create(getBaseContext(),
                    R.raw.cartoon130);
			Intent gameintent =new Intent(this, GameBoard.class);
			switch(v.getId()){
			case R.id.newgame:
			mp.start();
            startActivity(gameintent);
            break;
            case R.id.resume:
            	
	            mp.start();
	            startActivity(gameintent);
	            break;
			case R.id.options:
				mp.start();
				Intent optionsintent = new Intent(this,OptionsActivity.class);
				startActivity(optionsintent);
				break;
			case R.id.howto:
				mp.start();
				Intent howtointent = new Intent(this, HowToActivity.class);
				startActivity(howtointent);
				break;
				/*
			case R.id.highscore:
				mp.start();
				Intent highscoreintent = new Intent(this, HighScoreActivity.class);
				startActivity(highscoreintent);
				break;
	            */
			}
		
		};
	
  

	
    
    
    }
