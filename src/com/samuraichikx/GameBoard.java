package com.samuraichikx;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class GameBoard extends Activity implements Serializable{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5543686861443683175L;
	private GameBoardView _gameboardView;
	
	
	DialogInterface.OnClickListener yesnobox = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			switch(which){
			case DialogInterface.BUTTON_POSITIVE:
				_gameboardView.setScore(_gameboardView.prefs.getInt("SaveScore", 0)); 
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				
				break;
			}
			
		}
	};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);
        
     
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        
        _gameboardView = (GameBoardView)findViewById(R.id.game_board_view); 
        _gameboardView.setMode(GameBoardView.RUNNING);
        
        if(_gameboardView.prefs.getBoolean("HasSavedFile", false)== true)
        {
        	builder.setMessage("You have a saved game. Would you like to continue?").setPositiveButton("Yes", yesnobox).setNegativeButton("No", yesnobox).show();
        	Log.d("save", "true");
        }else 
        {
        	Log.d("save", "false");
        }
    }
	
	@Override
	protected void onPause()
	{
		super.onPause();
		_gameboardView.setMode(GameBoardView.PAUSE);
		
	}
	@Override
	public void onBackPressed(){
		Log.d("Back", "Presssed");
		if(!_gameboardView.gameover){
		_gameboardView.editor.putBoolean("HasSavedFile", true);
		_gameboardView.editor.putInt("SaveScore", _gameboardView.getScore());
		_gameboardView.editor.commit();
		
		}else
		{
			_gameboardView.editor.putBoolean("HasSavedFile", false);
			_gameboardView.editor.commit();
		}
		try{
			FileOutputStream fileOut = new FileOutputStream("highscore.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(_gameboardView.prefs.getInt("HighScore", 0));
			out.close();
			fileOut.close();
		}catch(IOException e){e.printStackTrace();};
		finish();
	}
	
	
	@Override
	protected void onResume()
	{
		super.onResume();
		_gameboardView.setMode(_gameboardView.RUNNING);
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("HighScore", _gameboardView.getScore());
		
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
	  _gameboardView.setScore(savedInstanceState.getInt("HighScore"));
	  
	  
	}
}
