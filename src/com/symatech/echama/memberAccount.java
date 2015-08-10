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

public class memberAccount extends Activity implements OnClickListener {

	public dbFunctions dbFunctions;
	public Intent intent;
	public LinearLayout askLoanLinearLayout, statusLinearLayout,
			depositLinearLayout, contributionLinearLayout;
	public ImageButton askLoanImageButton, statusImageButton,
			depositImageButton, contributionsImageButton, backButton;
	public TextView askLoanTextView, statusTextView, depositTextView,
			contributionsTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.member_account);

		backButton = (ImageButton) findViewById(R.id.backButton);
		askLoanLinearLayout = (LinearLayout) findViewById(R.id.askLoanLinearLayout);
		statusLinearLayout = (LinearLayout) findViewById(R.id.statusLinearLayout);
		depositLinearLayout = (LinearLayout) findViewById(R.id.depositLinearLayout);
		depositImageButton = (ImageButton) findViewById(R.id.depositImageButton);
		depositTextView = (TextView) findViewById(R.id.depositTextView);
		
		contributionLinearLayout = (LinearLayout) findViewById(R.id.contributionLinearLayout);
		contributionsImageButton = (ImageButton) findViewById(R.id.contributionsImageButton);
		contributionsTextView = (TextView) findViewById(R.id.contributionsTextView);
		
		askLoanImageButton = (ImageButton) findViewById(R.id.askLoanImageButton);
		statusImageButton = (ImageButton) findViewById(R.id.statusImageButton);
		statusTextView = (TextView) findViewById(R.id.statusTextView);
		askLoanTextView = (TextView) findViewById(R.id.askLoanTextView);

		askLoanLinearLayout.setOnClickListener(this);
		askLoanImageButton.setOnClickListener(this);
		askLoanTextView.setOnClickListener(this);
		backButton.setOnClickListener(this);
		statusLinearLayout.setOnClickListener(this);
		statusImageButton.setOnClickListener(this);
		statusTextView.setOnClickListener(this);
		depositLinearLayout.setOnClickListener(this);
		depositImageButton.setOnClickListener(this);
		depositTextView.setOnClickListener(this);
		contributionLinearLayout.setOnClickListener(this);
		contributionsImageButton.setOnClickListener(this);
		contributionsTextView.setOnClickListener(this);
		

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

		case R.id.askLoanLinearLayout:

			intent = new Intent(memberAccount.this, askLoan.class);
			startActivity(intent);

			break;

		case R.id.askLoanImageButton:

			intent = new Intent(memberAccount.this, askLoan.class);
			startActivity(intent);

			break;

		case R.id.askLoanTextView:

			intent = new Intent(memberAccount.this, askLoan.class);
			startActivity(intent);

			break;

		case R.id.statusLinearLayout:

			intent = new Intent(memberAccount.this, viewLoanRequests.class);
			startActivity(intent);

			break;
		case R.id.statusImageButton:

			intent = new Intent(memberAccount.this, viewLoanRequests.class);
			startActivity(intent);

			break;
		case R.id.statusTextView:

			intent = new Intent(memberAccount.this, viewLoanRequests.class);
			startActivity(intent);

			break;
		case R.id.depositLinearLayout:

			intent = new Intent(memberAccount.this, makeDeposit.class);
			startActivity(intent);

			break;
		case R.id.depositImageButton:

			intent = new Intent(memberAccount.this, makeDeposit.class);
			startActivity(intent);

			break;
		case R.id.depositTextView:

			intent = new Intent(memberAccount.this, makeDeposit.class);
			startActivity(intent);

			break;
		case R.id.contributionsImageButton:

			intent = new Intent(memberAccount.this, viewDeposits.class);
			startActivity(intent);

			break;
		case R.id.contributionLinearLayout:

			intent = new Intent(memberAccount.this, viewDeposits.class);
			startActivity(intent);

			break;
		case R.id.contributionsTextView:

			intent = new Intent(memberAccount.this, viewDeposits.class);
			startActivity(intent);

			break;

		default:
			break;
		}

	}

}
