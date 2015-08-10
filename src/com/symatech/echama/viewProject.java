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



public class viewProject extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public Button ProgressButton;
	public Intent intent;
	public TextView nameTextView , descTextView , statusTextView;
	Bundle extras;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_details);
		
		extras = getIntent().getExtras();
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		ProgressButton = (Button) findViewById(R.id.ProgressButton);
		nameTextView = (TextView) findViewById(R.id.nameTextView);
		descTextView = (TextView) findViewById(R.id.descTextView);
		statusTextView = (TextView) findViewById(R.id.statusTextView);
		
		
		backButton.setOnClickListener(this);
		ProgressButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(viewProject.this);
		dbFunctions.open();
		
		dbFunctions.getProjectDetails(extras.getString("ID") , nameTextView , descTextView , statusTextView);

        dbFunctions.getProjectProgress(extras.getString("ID"));   
		
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
		case R.id.ProgressButton:
			
			Intent intent = new Intent(viewProject.this , addProjectProgress.class);
			intent.putExtra("ID", extras.getString("ID"));
			startActivity(intent);
			
			break;

		default:
			break;
		}
		
	}


}
