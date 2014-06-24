package com.example.functionsolver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{
	SplashSurface surface;
	Intent in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		surface = new SplashSurface(this);
		setContentView(surface);
	
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	surface.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Thread thread = new Thread(){
			@Override
			public void run(){
				try{
					sleep(2000);
				}catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					in = new Intent("com.example.functionsolver.Listview");
					startActivity(in);   			
				}						
			}
		};
		thread.start();
		surface.resume();
	}
	

	

}
