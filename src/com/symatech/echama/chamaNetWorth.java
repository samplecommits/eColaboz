package com.symatech.echama;


import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;



public class chamaNetWorth extends Activity implements OnClickListener{
    
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public TextView chamaNetworth;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chama_networth);
		
		backButton = (ImageButton) findViewById(R.id.backButton);
		chamaNetworth = (TextView) findViewById(R.id.chamaNetworth);
		
		backButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(chamaNetWorth.this);
		dbFunctions.open();
		
		dbFunctions.getChamaNetWorth(chamaNetworth);
		
		dbFunctions.getChamaDeposits();

		
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
