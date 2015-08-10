package com.symatech.echama;

import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class userRegistration extends Activity implements OnClickListener{
    
	public EditText idEditText , pinEditText ,fnameEditText , lnameEditText , usernameEditText , emailEditText , telephoneEditText , passwordEditText;
	public Spinner chamaID , userLevel;
	public Button registerButton;
	public dbFunctions dbFunctions;
	public TextView loginMember;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.register);
	    
	    fnameEditText = (EditText) findViewById(R.id.fnameEditText);
	    lnameEditText = (EditText) findViewById(R.id.lnameEditText);
	    usernameEditText = (EditText) findViewById(R.id.usernameEditText);
	    emailEditText = (EditText) findViewById(R.id.emailEditText);
	    telephoneEditText = (EditText) findViewById(R.id.telephoneEditText);
	    passwordEditText = (EditText) findViewById(R.id.passwordEditText);
	
	    idEditText = (EditText)findViewById(R.id.idEditText);
	    pinEditText = (EditText) findViewById(R.id.pinEditText);
	    chamaID = (Spinner) findViewById(R.id.chamaID);
	    userLevel = (Spinner) findViewById(R.id.userLevel);
	    registerButton = (Button) findViewById(R.id.registerButton);
	    loginMember = (TextView) findViewById(R.id.loginMember);
	    
	    registerButton.setOnClickListener(this);
	    loginMember.setOnClickListener(this);
	    
	    dbFunctions = new dbFunctions(userRegistration.this);
	    dbFunctions.open();
	    
	}


	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.registerButton:
			if(pinEditText.getText().toString().trim().length() > 0 && idEditText.getText().toString().trim().length() > 0 && fnameEditText.getText().toString().trim().length() > 0 && lnameEditText.getText().toString().trim().length() > 0 && usernameEditText.getText().toString().trim().length() > 0 && emailEditText.getText().toString().trim().length() > 0 && telephoneEditText.getText().toString().trim().length() > 0 && passwordEditText.getText().toString().trim().length() > 0 && !chamaID.getSelectedItem().toString().trim().equalsIgnoreCase("Select Chama") && !userLevel.getSelectedItem().toString().trim().equalsIgnoreCase("Select User Level") ){
				dbFunctions.userRegistration(fnameEditText.getText().toString().trim(), lnameEditText.getText().toString().trim() , usernameEditText.getText().toString().trim(), emailEditText.getText().toString().trim(), telephoneEditText.getText().toString().trim(), passwordEditText.getText().toString().trim(), chamaID.getSelectedItem().toString().trim() , userLevel.getSelectedItem().toString().trim() , pinEditText.getText().toString().trim() , idEditText.getText().toString().trim());
			}else{
				Toast.makeText(userRegistration.this, "Fill in all fields", Toast.LENGTH_LONG).show();
			}
			
			break;
			
		case R.id.loginMember:
			
			Intent intent = new Intent(userRegistration.this , userLogin.class);
            startActivity(intent);
            
			break;



		default:
			break;
		}
		
	}         
}
