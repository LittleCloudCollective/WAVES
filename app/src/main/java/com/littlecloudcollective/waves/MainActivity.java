package com.littlecloudcollective.waves;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(SaveSharedPreference.getUsername(MainActivity.this).length() == 0)
		{
			Intent i = new Intent(this, LoginActivity.class);
			startActivity(i);
		} else
		{
			Intent i = new Intent(this, LandingActivity.class);
			startActivity(i);
		}
	}

}
