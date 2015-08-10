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
import android.widget.Toast;


public class updateLoanAdmin extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public Button approveButton;

	public EditText interestEditText , dateEditText;
	Bundle extras;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.approve_loan);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		approveButton = (Button) findViewById(R.id.approveButton);
		

		interestEditText = (EditText) findViewById(R.id.interestEditText);
		dateEditText = (EditText) findViewById(R.id.dateEditText);
		
		
		backButton.setOnClickListener(this);
		approveButton.setOnClickListener(this);
	
		extras = getIntent().getExtras();
		
		
		dbFunctions = new dbFunctions(updateLoanAdmin.this);
		dbFunctions.open();
		
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
			
		case R.id.approveButton:
			
			if(interestEditText.getText().toString().trim().length() > 0 && dateEditText.getText().toString().trim().length() > 0){
				dbFunctions.approveLoan(extras.getString("ID"), interestEditText.getText().toString().trim(), dateEditText.getText().toString().trim());	
			}else{
				Toast.makeText(updateLoanAdmin.this, "Field cannot be blank", Toast.LENGTH_LONG).show();
			}
		        
			break;

		default:
			break;
		}
		
	}


}
