package com.littlecloudcollective.waves;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LandingActivity extends ActionBarActivity {

	private static final String DIALOG_LOGOUT = "LOGOUT";
	//private ViewPager viewPager;
	//private TabsPagerAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);
		
		//viewPager = (ViewPager) findViewById(R.id.pager);
		ActionBar actionBar = getSupportActionBar();
		//mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
			
		//viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//Add tabs
		Tab tab = actionBar.newTab()
						   .setIcon(R.drawable.feed_icon)
						   .setTabListener(new TabListener<SoundFragment>(this, "feed", SoundFragment.class));
		actionBar.addTab(tab);
		
		tab = actionBar.newTab()
					.setIcon(R.drawable.discover_icon)
				   .setTabListener(new TabListener<DiscoverFragment>(this, "discover", DiscoverFragment.class));
		actionBar.addTab(tab);
		
		tab = actionBar.newTab()
					.setIcon(R.drawable.profile_icon)
				   .setTabListener(new TabListener<ProfileFragment>(this, "profile", ProfileFragment.class));
		actionBar.addTab(tab);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()) {
		case R.id.action_record: 
			Intent i = new Intent(LandingActivity.this, AudioRecorderActivity.class);
		   	startActivity(i);
		case R.id.action_logout:
			FragmentManager fm = this.getSupportFragmentManager();
			LogoutDialogFragment f = new LogoutDialogFragment();
			f.show(fm, DIALOG_LOGOUT);
		default: 
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	@SuppressLint("ValidFragment")
	public class LogoutDialogFragment extends DialogFragment {
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
			builder.setMessage(R.string.logout_dialog_message)
		       	   .setTitle(R.string.logout_dialog_title);
			// Set the action buttons
	        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   //User clicked yes, so log out
	            	   SaveSharedPreference.clearUsername(getApplicationContext());
	            	   SaveSharedPreference.clearPassword(getApplicationContext());
	            	   Intent i = new Intent(LandingActivity.this, LoginActivity.class);
	            	   startActivity(i);
	               }
	           });
	         builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	               }
	           });
			return builder.create();
		}
	}

}