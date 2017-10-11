package com.beikbank.android.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ContentFragmentPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
	public ContentFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public ContentFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> pagerItemList ) {
		super(fm);
		this.pagerItemList = pagerItemList;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		if (position < pagerItemList.size()){
			fragment = pagerItemList.get(position);
		}else{
			fragment = pagerItemList.get(0);
		}		
		return fragment;		
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pagerItemList.size();
	}
	

}
