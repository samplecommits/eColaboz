package com.symatech.echama;


import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class adminDashboard extends Activity implements OnClickListener {
    
	public dbFunctions dbFunctions;
	public Intent intent;
	public ImageButton backButton , infoImageButton , netImageButton , membersImageButton , logoutImageButton;
	public LinearLayout infoLinearLayout , netLinearLayout , membersLinearLayout , logoutLinearLayout;
	public TextView infoTextView , netTextView , membersTextView , logoutTextView;
	public Button logoutButton;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.admin_dashboard);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		infoImageButton = (ImageButton) findViewById(R.id.infoImageButton);
		netImageButton = (ImageButton) findViewById(R.id.netImageButton);
		membersImageButton = (ImageButton) findViewById(R.id.membersImageButton);
		logoutImageButton = (ImageButton) findViewById(R.id.logoutImageButton);
		
		infoLinearLayout = (LinearLayout) findViewById(R.id.infoLinearLayout);
		netLinearLayout = (LinearLayout) findViewById(R.id.netLinearLayout);
		membersLinearLayout = (LinearLayout) findViewById(R.id.membersLinearLayout);
		logoutLinearLayout = (LinearLayout) findViewById(R.id.logoutLinearLayout);
		logoutTextView = (TextView) findViewById(R.id.logoutTextView);
		
		logoutButton = (Button) findViewById(R.id.logoutButton);
		
		infoTextView = (TextView) findViewById(R.id.infoTextView);
		netTextView = (TextView) findViewById(R.id.netTextView);
		membersTextView = (TextView) findViewById(R.id.membersTextView);
		
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
		logoutImageButton.setOnClickListener(this);
		logoutLinearLayout.setOnClickListener(this);
		logoutTextView.setOnClickListener(this);
		logoutButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(adminDashboard.this);
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
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.backButton:
			this.finish();
			break;
		case R.id.infoImageButton:
			intent = new Intent (adminDashboard.this , approveLoans.class);
			startActivity(intent);
			break;
		case R.id.infoLinearLayout:
			intent = new Intent (adminDashboard.this , approveLoans.class);
			startActivity(intent);
			break;
		case R.id.infoTextView:
			intent = new Intent (adminDashboard.this , approveLoans.class);
			startActivity(intent);
			break;
		case R.id.netImageButton:
			intent = new Intent (adminDashboard.this , chamaNetWorth.class);
			startActivity(intent);
			break;
		case R.id.netTextView:
			intent = new Intent (adminDashboard.this , chamaNetWorth.class);
			startActivity(intent);
			break;
		case R.id.netLinearLayout:
			intent = new Intent (adminDashboard.this , chamaNetWorth.class);
			startActivity(intent);
			break;
			
		case R.id.membersImageButton:
			intent = new Intent (adminDashboard.this , chamaMembers.class);
			startActivity(intent);
			break;
		case R.id.membersLinearLayout:
			intent = new Intent (adminDashboard.this , chamaMembers.class);
			startActivity(intent);
			break;
		case R.id.membersTextView:
			intent = new Intent (adminDashboard.this , chamaMembers.class);
			startActivity(intent);
			break;
			
		case R.id.logoutLinearLayout:

			
			intent = new Intent (adminDashboard.this , viewProjects.class);
			startActivity(intent);
			
			break;
		case R.id.logoutImageButton:

			
			intent = new Intent (adminDashboard.this , viewProjects.class);
			startActivity(intent);
			
			break;
		case R.id.logoutTextView:

			intent = new Intent (adminDashboard.this , viewProjects.class);
			startActivity(intent);

			
			break;
		case R.id.logoutButton:
			dbFunctions.logOut();
			
			intent = new Intent(adminDashboard.this , userLogin.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}


}
