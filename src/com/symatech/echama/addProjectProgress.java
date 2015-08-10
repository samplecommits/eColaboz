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


public class addProjectProgress extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public Button requestButton;
	public EditText descEditText;
	Bundle extras;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_project_progress);
		extras = getIntent().getExtras();
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		requestButton = (Button) findViewById(R.id.requestButton);
		descEditText = (EditText) findViewById(R.id.descEditText);
		
		backButton.setOnClickListener(this);
		requestButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(addProjectProgress.this);
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
			
		case R.id.requestButton:
			
			if(descEditText.getText().toString().trim().length() > 0){
				
               dbFunctions.addProjectProgress(extras.getString("ID"), descEditText);
			}else{
				Toast.makeText(addProjectProgress.this, "Field cannot be blank", Toast.LENGTH_LONG).show();
			}
		        
			break;

		default:
			break;
		}
		
	}


}
