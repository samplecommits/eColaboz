package com.symatech.echama;


import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity {
    
	public dbFunctions dbFunctions;
	public Intent intent;
	public String userId = null;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dbFunctions = new dbFunctions(MainActivity.this);
		dbFunctions.open();
		
		userId = dbFunctions.getUserID();
		if(dbFunctions.isLoggedIn()){
			if(dbFunctions.isAdmin(userId)){ 
				intent = new Intent(MainActivity.this , adminDashboard.class);
				startActivity(intent);
			}else{
				intent = new Intent(MainActivity.this , userDashboard.class);
				startActivity(intent);
			}

		}else{
			intent = new Intent(MainActivity.this , userLogin.class);
			startActivity(intent);
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	    this.finish();
	}


}
