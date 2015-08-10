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


public class makeDeposit extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public Button depositButton;
	public EditText depositEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.make_deposit);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		depositButton = (Button) findViewById(R.id.depositButton);
		depositEditText = (EditText) findViewById(R.id.depositEditText);
		
		backButton.setOnClickListener(this);
		depositButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(makeDeposit.this);
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
			
		case R.id.depositButton:
			
			if(depositEditText.getText().toString().trim().length() > 0){
				dbFunctions.makeDeposit(dbFunctions.getUserID(), depositEditText);	
			}else{
				Toast.makeText(makeDeposit.this, "Field cannot be blank", Toast.LENGTH_LONG).show();
			}
		        
			break;

		default:
			break;
		}
		
	}


}
