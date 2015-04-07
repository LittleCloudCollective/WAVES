package com.littlecloudcollective.waves;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm){
		super(fm);
	}
	
	@Override
	public Fragment getItem(int index) {
		
		switch(index) {
		case 0:
			//Feed fragment activity
			return new FeedFragment();
		case 1:
			//Discover fragment activity
			return new DiscoverFragment();
		case 2:
			//Profile fragment activity
			return new ProfileFragment();
		}
		
		return null;
	}
	
	@Override
	public int getCount() {
		//get item count - equal to number of tabs
		return 3;
	}

}
