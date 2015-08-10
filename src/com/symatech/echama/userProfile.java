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
import android.widget.Toast;

public class userProfile extends Activity implements OnClickListener {

	public Intent intent;
	public Button saveButton;
	public dbFunctions dbFunctions;
	public ImageButton backButton;
	public EditText fnameEditText, lnameEditText, usernameEditText,
			emailEditText, telEditText, pinEditText, idEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_layout);

		fnameEditText = (EditText) findViewById(R.id.fnameEditText);
		lnameEditText = (EditText) findViewById(R.id.lnameEditText);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		pinEditText = (EditText) findViewById(R.id.pinEditText);
		idEditText = (EditText) findViewById(R.id.idEditText);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		telEditText = (EditText) findViewById(R.id.telEditText);
		saveButton = (Button) findViewById(R.id.saveButton);
		backButton = (ImageButton) findViewById(R.id.backButton);

		dbFunctions = new dbFunctions(userProfile.this);
		dbFunctions.open();

		dbFunctions.getProfileDetails(dbFunctions.getUserID(), fnameEditText,
				lnameEditText, usernameEditText, emailEditText, telEditText,
				pinEditText, idEditText);

		saveButton.setOnClickListener(this);
		backButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.backButton:
			this.finish();
			break;

		case R.id.saveButton:
			if (fnameEditText.getText().toString().trim().length() > 0
					&& lnameEditText.getText().toString().trim().length() > 0
					&& usernameEditText.getText().toString().trim().length() > 0
					&& emailEditText.getText().toString().trim().length() > 0
					&& telEditText.getText().toString().trim().length() > 0
					&& pinEditText.getText().toString().trim().length() > 0
					&& idEditText.getText().toString().trim().length() > 0) {
				dbFunctions.updateProfileDetails(dbFunctions.getUserID(),
						fnameEditText, lnameEditText, usernameEditText,
						emailEditText, telEditText, pinEditText, idEditText);
			} else {
               Toast.makeText(userProfile.this, "Fill in all fields", Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}

	}
}
