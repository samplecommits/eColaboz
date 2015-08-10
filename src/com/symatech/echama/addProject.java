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


public class addProject extends Activity implements OnClickListener {
    
	public dbFunctions dbFunctions;
	public Intent intent;
	public ImageButton backButton;
	public Button addButton;
	public EditText descEditText , nameEditText;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.add_project);
        
        backButton = (ImageButton) findViewById(R.id.backButton);
        addButton = (Button) findViewById(R.id.addButton);
        descEditText = (EditText) findViewById(R.id.descEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        
        backButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        
        dbFunctions = new dbFunctions(addProject.this);
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
		case R.id.addButton:
			
			if(nameEditText.getText().toString().trim().length() > 0  && descEditText.getText().toString().trim().length() > 0){
				dbFunctions.addProject(nameEditText, descEditText);
			}else{
				Toast.makeText(addProject.this, "Fill in all fields", Toast.LENGTH_LONG).show();
			}
	        
			break;

		default:
			break;
		}
		
	}


}
