package com.example.functionsolver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SplashSurface extends SurfaceView implements Runnable{
	Thread thread = null;
	SurfaceHolder holder;
	Bitmap image_hat, image_tree,image_bulb;
	Typeface font_anger, font_goodchoice;
	boolean isRunning = true;
	Canvas canvas;
	int changingHeight;
	boolean secondRunning = false;
	
	public SplashSurface(Context context) {
		super(context);
		
		holder = getHolder();
		image_hat = BitmapFactory.decodeResource(getResources(), R.drawable.magichat);
		image_tree = BitmapFactory.decodeResource(getResources(), R.drawable.tree);
		image_bulb = BitmapFactory.decodeResource(getResources(), R.drawable.bulb);
		font_anger = Typeface.createFromAsset(context.getAssets(), "anger.ttf");
		font_goodchoice = Typeface.createFromAsset(context.getAssets(), "goodchoice.ttf");
		changingHeight = 1800/4*3;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int initSpeed=0;
		Paint paint = new Paint();
		paint.setTypeface(font_anger);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(50);
		while(isRunning){
			if(!holder.getSurface().isValid()){
				continue;
			}
			canvas = holder.lockCanvas();
			canvas.drawRGB( 128, 0, 0);
			canvas.drawText("Little Math Helper", canvas.getWidth()/2, canvas.getHeight()/2+100, paint);
			canvas.drawText("Make Life Better", canvas.getWidth()/2, canvas.getHeight()/2+160, paint);
			if(changingHeight>canvas.getHeight()/4){
				changingHeight -=initSpeed;
				initSpeed +=2;
				canvas.drawBitmap(image_bulb, canvas.getWidth()/3, changingHeight,null);
				holder.unlockCanvasAndPost(canvas);
			}else{ // moving finished, words pop up.
				canvas.drawBitmap(image_bulb, canvas.getWidth()/3, changingHeight,null);
				holder.unlockCanvasAndPost(canvas);
				break;
				
			}			
		}
	}

	public void pause() {
		// TODO Auto-generated method stub
		isRunning = false;
		while (true) {
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		thread = null;
	}
	public void resume(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

}
