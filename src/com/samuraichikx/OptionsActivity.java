package com.samuraichikx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OptionsActivity extends PreferenceActivity{

	private static final String numBubbles = "NUMBER OF BUBBLES";
	private static final String numBubbles_Default = "1";
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        addPreferencesFromResource(R.xml.options);
	    }
	 
	 
	 public static String getNumBubbles(Context context)
	 {
		 return PreferenceManager.getDefaultSharedPreferences(context).
		 	getString(numBubbles, numBubbles_Default);
	 }
	 
	 
}
