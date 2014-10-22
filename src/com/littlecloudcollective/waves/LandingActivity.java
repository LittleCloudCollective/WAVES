package com.littlecloudcollective.waves;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LandingActivity extends ActionBarActivity {

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
		default: 
			return super.onOptionsItemSelected(item);
		}
	}

}