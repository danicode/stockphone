package com.view.main;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.stockphone.R;
import com.view.main.MenuItem;

public class MainAdapter extends BaseAdapter {
	
	private ArrayList<MenuItem> listMenuItem; 
	private Context context;			
	
	public MainAdapter(ArrayList<MenuItem> listMenuItem, Context context) {
		this.listMenuItem = listMenuItem;
		this.context = context;
	}

	public int getCount() {		
		return this.listMenuItem.size();
	}

	
	public MenuItem getItem(int index) {		
		return this.listMenuItem.get(index);
	}

	
	public long getItemId(int index) {
		return 0;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		LayoutInflater li = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = li.inflate(R.layout.lyt_main_item,null);
		
		ImageView mgvimageItem = (ImageView) view.findViewById(R.id.mgvImageItem);		
		TextView lblNameItem = (TextView) view.findViewById(R.id.lblNameItem);
		
		MenuItem menuItem = this.getItem(position);
		
		mgvimageItem.setImageResource(menuItem.getImagenItem());		
		lblNameItem.setText(menuItem.getNameItem());
		
		return view;
	}
}
