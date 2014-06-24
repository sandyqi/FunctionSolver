package com.example.functionsolver;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Listview extends ListActivity{
	List<String> helpers=new ArrayList<String>();
	ListAdapter adapter;
	Typeface font;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		helpers.add("Linear Function");
		helpers.add("Other Functions");
		helpers.add("Calculator");
		adapter = new ListAdapter(this, helpers);
		setListAdapter(adapter);
		font = Typeface.createFromAsset(this.getAssets(), "goodchoice.ttf");
		TextView title = (TextView)findViewById(R.id.titleInListview);
		title.setTypeface(font);

	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Log.i("PPP", "the "+position);
		if (position == 0){
			Intent in = new Intent(Listview.this, MainActivity.class);
			startActivity(in);
		}
	}
	

}
