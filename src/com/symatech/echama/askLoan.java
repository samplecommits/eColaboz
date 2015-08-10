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


public class askLoan extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public Button requestButton;
	public EditText loanEditText;
	public TextView maxLoan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ask_loan);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		requestButton = (Button) findViewById(R.id.requestButton);
		loanEditText = (EditText) findViewById(R.id.loanEditText);
		maxLoan = (TextView) findViewById(R.id.maxLoan);
		
		backButton.setOnClickListener(this);
		requestButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(askLoan.this);
		dbFunctions.open();
		
		maxLoan.setText(" Borrowing Limit : "+String.valueOf(dbFunctions._getChamaNetWorth())+" KES");
		
		
		
		
		
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
			
		case R.id.requestButton:
			
			if(loanEditText.getText().toString().trim().length() > 0){
				if( Integer.parseInt(loanEditText.getText().toString().trim()) >   dbFunctions._getChamaNetWorth()){
					Toast.makeText(askLoan.this, "You cannot borrow more than the limit", Toast.LENGTH_LONG).show();
				}else{
					dbFunctions.requestLoan(dbFunctions.getUserID(), loanEditText.getText().toString().trim() , loanEditText);
				}
					
			}else{
				Toast.makeText(askLoan.this, "Field cannot be blank", Toast.LENGTH_LONG).show();
			}
		        
			break;

		default:
			break;
		}
		
	}


}
