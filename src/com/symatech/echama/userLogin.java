package com.symatech.echama;

import com.symatech.database.dbFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class userLogin extends Activity implements OnClickListener {

	public TextView registerMember;
	public Intent intent;
	public Button loginButton;
	public dbFunctions dbFunctions;
	public EditText emailEditText , passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		registerMember = (TextView) findViewById(R.id.registerMember);
		loginButton = (Button) findViewById(R.id.loginButton);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		
		registerMember.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		
		dbFunctions = new dbFunctions(userLogin.this);
		dbFunctions.open();
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.registerMember:
           
			intent = new Intent (userLogin.this , userRegistration.class);
			startActivity(intent);
			
			break;
			
		case R.id.loginButton:
			if(emailEditText.getText().toString().trim().length() > 0 && passwordEditText.getText().toString().trim().length() > 0 ){
				dbFunctions.userLogin(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
			}else{
				Toast.makeText(userLogin.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
			}
			
			break;

		default:
			break;
		}

	}
}
