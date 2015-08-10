package com.symatech.echama;


import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;



public class viewProjects extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public Button newProjectButton;
	public Intent intent;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.projects_layout);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		newProjectButton = (Button) findViewById(R.id.newProjectButton);
		
		backButton.setOnClickListener(this);
		newProjectButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(viewProjects.this);
		dbFunctions.open();
		
		dbFunctions.getProjects();

		
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
		case R.id.newProjectButton:
			intent = new Intent(viewProjects.this , addProject.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}


}
