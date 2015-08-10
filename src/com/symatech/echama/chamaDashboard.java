package com.symatech.echama;


import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class chamaDashboard extends Activity implements OnClickListener {
    
	public dbFunctions dbFunctions;
	public Intent intent;
	public ImageButton backButton , infoImageButton , netImageButton , membersImageButton , projectsImageButton;
	public LinearLayout infoLinearLayout , netLinearLayout , membersLinearLayout , projectsLinearLayout;
	public TextView infoTextView , netTextView , membersTextView , projectsTextView;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.chama_dashboard);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		infoImageButton = (ImageButton) findViewById(R.id.infoImageButton);
		netImageButton = (ImageButton) findViewById(R.id.netImageButton);
		membersImageButton = (ImageButton) findViewById(R.id.membersImageButton);
		projectsImageButton = (ImageButton) findViewById(R.id.projectsImageButton);
		
		infoLinearLayout = (LinearLayout) findViewById(R.id.infoLinearLayout);
		netLinearLayout = (LinearLayout) findViewById(R.id.netLinearLayout);
		membersLinearLayout = (LinearLayout) findViewById(R.id.membersLinearLayout);
		projectsLinearLayout = (LinearLayout) findViewById(R.id.projectsLinearLayout);
		
		infoTextView = (TextView) findViewById(R.id.infoTextView);
		netTextView = (TextView) findViewById(R.id.netTextView);
		membersTextView = (TextView) findViewById(R.id.membersTextView);
		projectsTextView = (TextView) findViewById(R.id.projectsTextView);
		
		backButton.setOnClickListener(this);
		infoImageButton.setOnClickListener(this);
		netImageButton.setOnClickListener(this);
		membersImageButton.setOnClickListener(this);
		infoLinearLayout.setOnClickListener(this);
		netLinearLayout.setOnClickListener(this);
		membersLinearLayout.setOnClickListener(this);
		infoTextView.setOnClickListener(this);
		netTextView.setOnClickListener(this);
		membersTextView.setOnClickListener(this);
		projectsImageButton.setOnClickListener(this);
		projectsLinearLayout.setOnClickListener(this);
		projectsTextView.setOnClickListener(this);
		
		
		
		
		

	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	    this.finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.backButton:
			this.finish();
			break;
		case R.id.infoImageButton:
			intent = new Intent (chamaDashboard.this , chamaInfo.class);
			startActivity(intent);
			break;
		case R.id.infoLinearLayout:
			intent = new Intent (chamaDashboard.this , chamaInfo.class);
			startActivity(intent);
			break;
		case R.id.infoTextView:
			intent = new Intent (chamaDashboard.this , chamaInfo.class);
			startActivity(intent);
			break;
		case R.id.netImageButton:
			intent = new Intent (chamaDashboard.this , chamaNetWorth.class);
			startActivity(intent);
			break;
		case R.id.netTextView:
			intent = new Intent (chamaDashboard.this , chamaNetWorth.class);
			startActivity(intent);
			break;
		case R.id.netLinearLayout:
			intent = new Intent (chamaDashboard.this , chamaNetWorth.class);
			startActivity(intent);
			break;
			
		case R.id.membersImageButton:
			intent = new Intent (chamaDashboard.this , chamaMembers.class);
			startActivity(intent);
			break;
		case R.id.membersLinearLayout:
			intent = new Intent (chamaDashboard.this , chamaMembers.class);
			startActivity(intent);
			break;
		case R.id.membersTextView:
			intent = new Intent (chamaDashboard.this , chamaMembers.class);
			startActivity(intent);
			break;
			
		case R.id.projectsImageButton:
			intent = new Intent (chamaDashboard.this , memberViewProjects.class);
			startActivity(intent);
			break;
		case R.id.projectsLinearLayout:
			intent = new Intent (chamaDashboard.this , memberViewProjects.class);
			startActivity(intent);
			break;
		case R.id.projectsTextView:
			intent = new Intent (chamaDashboard.this , memberViewProjects.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}


}
