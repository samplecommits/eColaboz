package com.symatech.echama;


import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;



public class memberViewProjects extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public Button newProjectButton;
	public Intent intent;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.member_view_projects);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		
		
		backButton.setOnClickListener(this);
		
		
		dbFunctions = new dbFunctions(memberViewProjects.this);
		dbFunctions.open();
		
		dbFunctions.getMemberProjects();

		
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
