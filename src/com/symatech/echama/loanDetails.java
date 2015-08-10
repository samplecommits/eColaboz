package com.symatech.echama;


import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class loanDetails extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public TextView requestDateTextView , amountTextView , statusTextView , deadLineTextView , interestTextView;
	Bundle extras;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loan_details);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		requestDateTextView = (TextView) findViewById(R.id.requestDateTextView);
		amountTextView = (TextView) findViewById(R.id.amountTextView);
		statusTextView = (TextView) findViewById(R.id.statusTextView);
		deadLineTextView = (TextView) findViewById(R.id.deadLineTextView);
		interestTextView = (TextView) findViewById(R.id.interestTextView);
		
		
		backButton.setOnClickListener(this);

		
		dbFunctions = new dbFunctions(loanDetails.this);
		dbFunctions.open();
		
		extras = getIntent().getExtras();
		
		

		
		dbFunctions.getLoan(extras.getString("ID"), requestDateTextView, amountTextView, statusTextView, deadLineTextView, interestTextView);
		
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
