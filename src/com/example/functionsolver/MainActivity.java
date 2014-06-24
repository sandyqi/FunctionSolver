package com.example.functionsolver;

import java.text.NumberFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.math3.linear.*;

public class MainActivity extends Activity implements OnClickListener {
	Button buttonX, buttonY, buttonZ, buttonClear, buttonSolve;
	TextView formulaT1, formulaT2, answerT, formula1, formula2, answerXYZ;
	EditText editX, editY, editZ;
	int x1, y1, z1, x2, y2, z2;
	String sformula1X = "", sformula1Y = "", sformula1Z = "", sformula2X = "",
			sformula2Y = "", sformula2Z = "";
	boolean formulaT1checked = true;
	boolean formulaT2checked = false;
	String[] finalResult=new String[2];
	ImageView star1, star2;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
		
	}

	public String[] getXY(int x1, int y1, int x2, int y2, int z1, int z2) {
		String[] result = new String[2];
	
		RealMatrix rm = new Array2DRowRealMatrix(new double[][] { { x1, y1 },
				{ x2, y2 } }, false);
		DecompositionSolver solver = new LUDecomposition(rm).getSolver();
		double[] c = { z1, z2 };
		RealVector constants = new ArrayRealVector(c, false);
		RealVector solution = solver.solve(constants);
		double x = solution.getEntry(0);
		double y = solution.getEntry(1);
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		result[0] = " X = " + nf.format(x);
		result[1] = " Y = " + nf.format(y);  // round a double to 2 significant figures after decimal point
		
		return result;
	}

	public void initialize() {
		buttonX = (Button) findViewById(R.id.buttonX);
		buttonY = (Button) findViewById(R.id.buttonY);
		buttonZ = (Button) findViewById(R.id.buttonZ);
		buttonClear = (Button) findViewById(R.id.buttonClear);
		buttonSolve = (Button) findViewById(R.id.buttonSolve);
		editX = (EditText) findViewById(R.id.editTextX);
		editY = (EditText) findViewById(R.id.editTextY);
		editZ = (EditText) findViewById(R.id.editTextZ);
		formulaT1 = (TextView) findViewById(R.id.formulaTitle1);
		formulaT2 = (TextView) findViewById(R.id.formulaTitle2);
		formula1 = (TextView) findViewById(R.id.formula1);
		formula2 = (TextView) findViewById(R.id.formula2);
		answerT = (TextView) findViewById(R.id.answerTitle);
		answerXYZ = (TextView) findViewById(R.id.answerXYZ);
		star1 = (ImageView)findViewById(R.id.star1);
		star2 =(ImageView)findViewById(R.id.star2);
		buttonX.setOnClickListener(this);
		buttonY.setOnClickListener(this);
		buttonZ.setOnClickListener(this);
		buttonClear.setOnClickListener(this);
		buttonSolve.setOnClickListener(this);
		formulaT1.setOnClickListener(this);
		formulaT2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonX:
			if (editX.getText().length() > 0) {
				if (formulaT1checked) {
					x1 = Integer.parseInt(editX.getText().toString());
					sformula1X = editX.getText().toString() + "X ";
					formula1.setText(sformula1X + sformula1Y + sformula1Z);
					editX.setText("");
					editY.setFocusable(true);
				} else {
					x2 = Integer.parseInt(editX.getText().toString());
					sformula2X = editX.getText().toString() + "X ";
					formula2.setText(sformula2X + sformula2Y + sformula2Z);
					editX.setText("");
				}
			}
			break;
		case R.id.buttonY:
			if (editY.getText().length() > 0) {
				if (formulaT1checked) {
					y1 = Integer.parseInt(editY.getText().toString());
					sformula1Y = "+ "+editY.getText().toString() + "Y ";
					formula1.setText(sformula1X + sformula1Y + sformula1Z);
					editY.setText("");
				} else {
					y2 = Integer.parseInt(editY.getText().toString());
					sformula2Y = "+ " +editY.getText().toString() + "Y ";
					formula2.setText(sformula2X+ sformula2Y + sformula2Z);
					editY.setText("");
				}
			}
			break;
		case R.id.buttonZ:
			if (editZ.getText().length() > 0) {
				if (formulaT1checked) {
					z1 = Integer.parseInt(editZ.getText().toString());
					sformula1Z = "= "+editZ.getText().toString() + "Z";
					formula1.setText(sformula1X + sformula1Y + sformula1Z);
					editZ.setText("");
				} else {
					z2 = Integer.parseInt(editZ.getText().toString());
					sformula2Z = "= "+editZ.getText().toString() + "Z";
					formula2.setText(sformula2X + sformula2Y + sformula2Z);
					editZ.setText("");
				}
			}
		case R.id.formulaTitle1:
			star1.setVisibility(View.VISIBLE);
			formulaT1checked = true;
			formulaT2checked = false;
			star2.setVisibility(View.INVISIBLE);
			break;
		case R.id.formulaTitle2:
			star2.setVisibility(View.VISIBLE);
			formulaT2checked = true;
			formulaT1checked = false;
			star1.setVisibility(View.INVISIBLE);
			break;
		case R.id.buttonClear:
			x1=x2=y1=y2=z1=z2=0;
			sformula1X=sformula1Y=sformula1Z=sformula2X=sformula2Y=sformula2Z="";
			formula1.setText("");
			formula2.setText("");
			editX.setText("");
			editY.setText("");
			editZ.setText("");
			answerXYZ.setText("");
			break;
		case R.id.buttonSolve:
			if(sformula1X !="" && sformula1Y !="" && sformula1Z !="" && sformula2X!="" &&sformula2Y!= "" && sformula2Z!="")
			try{
			finalResult= getXY(x1,y1,x2,y2,z1,z2);
			answerT.setText(finalResult[0] +"   "+finalResult[1]);
			}catch(Exception e){
				e.printStackTrace();
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setTitle("Error ");
				builder.setMessage("Input error! No solution.");
				builder.setNeutralButton("OK",null) ;
				builder.show();
				buttonClear.performClick();
			}
			break;
		}

	}
	
	
}
