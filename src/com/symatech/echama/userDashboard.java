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

public class userDashboard extends Activity implements OnClickListener{
    

	public dbFunctions dbFunctions;
	public LinearLayout profileLinearLayout , accountLinearLayout , chamaLinearLayout , logoutLinearLayout;
	public ImageButton accountImageButton , userProfileImageButton , chamaImageButton , logoutImageButton;
	public TextView accountTextView , userProfileTextView , chamaTextView , logoutTextView;
	public Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.user_dashboard);

	    profileLinearLayout = (LinearLayout) findViewById(R.id.profileLinearLayout);
	    accountLinearLayout = (LinearLayout) findViewById(R.id.accountLinearLayout);
	    chamaLinearLayout = (LinearLayout) findViewById(R.id.chamaLinearLayout);
	    logoutLinearLayout = (LinearLayout) findViewById(R.id.logoutLinearLayout);
	    chamaImageButton = (ImageButton) findViewById(R.id.chamaImageButton);
	    logoutImageButton = (ImageButton) findViewById(R.id.logoutImageButton);
	    chamaTextView     = (TextView) findViewById(R.id.chamaTextView);
	    logoutTextView  = (TextView) findViewById(R.id.logoutTextView);
	    accountImageButton  = (ImageButton) findViewById(R.id.accountImageButton);
	    userProfileImageButton = (ImageButton) findViewById(R.id.userProfileImageButton);
	    accountTextView = (TextView) findViewById(R.id.accountTextView);
	    userProfileTextView = (TextView) findViewById(R.id.userProfileTextView);
	    
	    profileLinearLayout.setOnClickListener(this);

	    
	    accountLinearLayout.setOnClickListener(this);
	    accountImageButton.setOnClickListener(this);
	    accountTextView.setOnClickListener(this);
	    userProfileImageButton.setOnClickListener(this);
	    userProfileTextView.setOnClickListener(this);
	    chamaImageButton.setOnClickListener(this);
	    chamaLinearLayout.setOnClickListener(this);
	    chamaTextView.setOnClickListener(this);
	    logoutLinearLayout.setOnClickListener(this);
	    logoutImageButton.setOnClickListener(this);
	    logoutTextView.setOnClickListener(this);
	    
	    dbFunctions = new dbFunctions(userDashboard.this);
	    dbFunctions.open();
	    
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {

			
		case R.id.profileLinearLayout:

			
			intent = new Intent(userDashboard.this , userProfile.class);
			startActivity(intent);
			
			break;
		case R.id.accountLinearLayout:

			
			intent = new Intent(userDashboard.this , memberAccount.class);
			startActivity(intent);
			
			break;
		case R.id.accountImageButton:

			
			intent = new Intent(userDashboard.this , memberAccount.class);
			startActivity(intent);
			
			break;
		case R.id.accountTextView:

			
			intent = new Intent(userDashboard.this , memberAccount.class);
			startActivity(intent);
			
			break;
		case R.id.userProfileImageButton:

			
			intent = new Intent(userDashboard.this , userProfile.class);
			startActivity(intent);
			
			break;
		case R.id.userProfileTextView:

			
			intent = new Intent(userDashboard.this , userProfile.class);
			startActivity(intent);
			
			break;
		case R.id.chamaImageButton:

			
			intent = new Intent(userDashboard.this , chamaDashboard.class);
			startActivity(intent);
			
			break;
		case R.id.chamaLinearLayout:

			
			intent = new Intent(userDashboard.this , chamaDashboard.class);
			startActivity(intent);
			
			break;
		case R.id.chamaTextView:

			
			intent = new Intent(userDashboard.this , chamaDashboard.class);
			startActivity(intent);
			
			break;
			
		case R.id.logoutLinearLayout:

			
			dbFunctions.logOut();
			
			intent = new Intent(userDashboard.this , userLogin.class);
			startActivity(intent);
			
			break;
		case R.id.logoutImageButton:

			
			dbFunctions.logOut();
			
			intent = new Intent(userDashboard.this , userLogin.class);
			startActivity(intent);
			
			break;
		case R.id.logoutTextView:

			
			dbFunctions.logOut();
			
			intent = new Intent(userDashboard.this , userLogin.class);
			startActivity(intent);
			
			break;
 
		default:
			break;
		}
		
	}         
}
