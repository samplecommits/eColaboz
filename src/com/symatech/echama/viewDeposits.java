package com.symatech.echama;


import com.symatech.database.dbFunctions;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;



public class viewDeposits extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loan_status);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		
		backButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(viewDeposits.this);
		dbFunctions.open();
		
		dbFunctions.getDeposits(dbFunctions.getUserID());

		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	    this.finish();
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
          
		case R.id.backButton:
			this.finish(); 
			break;

		default:
			break;
		}
		
	}


}
