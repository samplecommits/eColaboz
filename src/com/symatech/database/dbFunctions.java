package com.symatech.database;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.symatech.echama.R;
import com.symatech.echama.adminDashboard;
import com.symatech.echama.loanDetails;
import com.symatech.echama.memberViewProjects;
import com.symatech.echama.memberviewProject;
import com.symatech.echama.updateLoanAdmin;
import com.symatech.echama.userDashboard;
import com.symatech.echama.viewProject;
import com.symatech.echama.viewProjects;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class dbFunctions {

	public SQLiteOpenHelper dbhelper;
	public SQLiteDatabase database;
	public Context context;
	public LayoutInflater inflator;
	public static LinearLayout listingsStreamLayout;
	public Activity activity;

	public dbFunctions(Context c) {
		dbhelper = new sqldatabasehelper(c);
		this.context = c;
		activity = (Activity) c;
		
	}

	public void open() {
		database = dbhelper.getWritableDatabase();
	}
	
	public void userRegistration(String fname , String lname , String username , String email , String phone , String password , String chama , String userlevel , String pinNumber , String idNumber) {
		
        if(this.emailExists(email)){
        	Toast.makeText(context, "Email Already Exists", Toast.LENGTH_LONG).show();
        }else{
    		ContentValues values = new ContentValues();
    		values.put(sqldatabasehelper.FNAME, fname);
    		values.put(sqldatabasehelper.LNAME, lname);
    		values.put(sqldatabasehelper.USERNAME, username);
    		values.put(sqldatabasehelper.EMAIL, email);
    		values.put(sqldatabasehelper.TELEPHONE, phone);
    		values.put(sqldatabasehelper.PASSWORD, password);
    		values.put(sqldatabasehelper.USER_CHAMA_ID, chama);
    		values.put(sqldatabasehelper.USER_LEVEL, userlevel);
    		values.put(sqldatabasehelper.USER_ID_NUMBER, idNumber );
    		values.put(sqldatabasehelper.USER_PIN, pinNumber);
    		
    		try {
    			database.insert(sqldatabasehelper.TBL_USERS, null, values);
                Toast.makeText(context, "Successfully Registered", Toast.LENGTH_LONG).show();
    		} catch (SQLException e) {
    			Log.i("SQL_ERRO", e.getMessage());
    		}
        }


	}

	public void close() {
		database.close();
	}

	public void logOut() {
		try {
			database.execSQL("DELETE FROM "
					+ sqldatabasehelper.TBL_LOGGED);
			Log.i("SUCCESS_LOGING_OUT", "SUCCESS");
		} catch (SQLException e) {
			Log.i("SQL_ERROR", e.getMessage());
		}

	}


	
	public boolean isLoggedIn(){
		String query = "SELECT " + sqldatabasehelper.LOGGED_USER_ID + " FROM " + sqldatabasehelper.TBL_LOGGED;
		if(this.count(query) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public String getUserID(){
		String query = "SELECT " + sqldatabasehelper.LOGGED_USER_ID + " FROM " + sqldatabasehelper.TBL_LOGGED;
		Cursor cursor = database.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {

				return cursor.getString(0).toString().trim();
				

			} while (cursor.moveToNext());
		}
       
		return null;
	}
	
	public boolean isAdmin(String userID){

		String query = "SELECT " + sqldatabasehelper.LOGGED_USER_LEVEL + " FROM " + sqldatabasehelper.TBL_LOGGED+ " WHERE "+sqldatabasehelper.LOGGED_USER_LEVEL+ " ='Admin'";
		if(this.count(query) > 0){
			return true;
		}else{
			return false;
		}

	}
	
	public boolean emailExists( String email ){
		String query = "SELECT " + sqldatabasehelper.EMAIL + " FROM " + sqldatabasehelper.TBL_USERS + " WHERE "+sqldatabasehelper.EMAIL+" = '"+email+"'";
		if(this.count(query) > 0){
			return true;
		}else{
			return false;
		}
	}


	
	public int count(String query) {

		int rows = 0;
		Cursor num_rows = null;
		try {
			num_rows = database.rawQuery(query, null);
			num_rows.moveToFirst();
			rows = num_rows.getCount();
		} catch (SQLException e) {
			Log.i("SQL_ERROR", e.getMessage());
		}
		if (rows == 0) {
			num_rows.close();
			return 0;
		} else {
			num_rows.close();
			return rows;
		}

	}

	public void logUser(String userID , String level){
		
		this.logOut();
		ContentValues values = new ContentValues();
		values.put(sqldatabasehelper.LOGGED_USER_ID, userID);
		values.put(sqldatabasehelper.LOGGED_USER_LEVEL, level);
		
		
  		try {
			database.insert(sqldatabasehelper.TBL_LOGGED, null, values);
		} catch (SQLException e) {
			Log.i("SQL_ERRO", e.getMessage());
		}

	}
	
	public void userLogin(String email , String password) {

		String query = "SELECT app_user_id , user_level FROM " + sqldatabasehelper.TBL_USERS + " WHERE "+sqldatabasehelper.EMAIL+" = '"+email.trim()+"' AND "+sqldatabasehelper.PASSWORD+" = '"+password.trim()+"'";
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

					this.logUser(cursor.getString(0).toString().trim() , cursor.getString(1).toString().trim() );
					
					if(cursor.getString(1).toString().trim().equalsIgnoreCase("Admin")){
						Intent intent = new Intent(context , adminDashboard.class);
						activity.startActivity(intent);
						
					}else{
						Intent intent = new Intent(context , userDashboard.class);
						activity.startActivity(intent);
					}
					

				} while (cursor.moveToNext());
			}
			cursor.close();

		}else{
		    Toast.makeText(context, "Email/Password is invalid", Toast.LENGTH_LONG).show();
		}

	}
   
	
	public void updateProfileDetails(String userID , EditText fname , EditText lname , EditText username , EditText email , EditText telephone , EditText pin , EditText idNumber){
		
		ContentValues values = new ContentValues();
		values.put(sqldatabasehelper.FNAME, fname.getText().toString().trim());
		values.put(sqldatabasehelper.LNAME, lname.getText().toString().trim());
		values.put(sqldatabasehelper.USERNAME, username.getText().toString().trim());
		values.put(sqldatabasehelper.EMAIL, email.getText().toString().trim());
		values.put(sqldatabasehelper.TELEPHONE, telephone.getText().toString().trim());
		values.put(sqldatabasehelper.USER_ID_NUMBER, idNumber.getText().toString().trim());
		values.put(sqldatabasehelper.USER_PIN, pin.getText().toString().trim());

		
		try {
			database.update(sqldatabasehelper.TBL_USERS, values, "app_user_id = '"+userID+"'", null);
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_LONG).show();
            this.getProfileDetails(userID, fname, lname, username, email, telephone , pin , idNumber);
		} catch (SQLException e) {
			Log.i("SQL_ERRO", e.getMessage());
		}
	}
	

	
	public void getProfileDetails(String userID , EditText fname , EditText lname , EditText username , EditText email , EditText telephone , EditText pin , EditText idNumber){
		
		String query = "SELECT "+sqldatabasehelper.FNAME+" , "+sqldatabasehelper.LNAME+" , "+sqldatabasehelper.USERNAME+" , "+sqldatabasehelper.EMAIL+" , "+sqldatabasehelper.TELEPHONE+", "+sqldatabasehelper.USER_PIN+", "+sqldatabasehelper.USER_ID_NUMBER+" FROM " + sqldatabasehelper.TBL_USERS + " WHERE app_user_id"+" = '"+userID+"'";
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

				fname.setText(cursor.getString(0).toString().trim());
				lname.setText(cursor.getString(1).toString().trim());
				username.setText(cursor.getString(2).toString().trim());
				email.setText(cursor.getString(3).toString().trim());
				telephone.setText(cursor.getString(4).toString().trim());
				pin.setText(cursor.getString(5).toString().trim());
				idNumber.setText(cursor.getString(6).toString().trim());
					

				} while (cursor.moveToNext());
			}
			cursor.close();

		}
	}

	
	/* Loans */

  public void requestLoan(String userID , String amount , EditText loanEditText ){
	    
	  Calendar c = Calendar.getInstance();
	  System.out.println("Current time => " + c.getTime());

	  SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	  String formattedDate = df.format(c.getTime());
	  
	    ContentValues values = new ContentValues();
		values.put(sqldatabasehelper.LOAN_USER_ID, userID);
		values.put(sqldatabasehelper.LOAN_AMOUNT, amount);
		values.put(sqldatabasehelper.LOAN_DATE, formattedDate);
		values.put(sqldatabasehelper.LOAN_INTEREST, "Not Set");
		values.put(sqldatabasehelper.LOAN_DEADLINE, "Not Set");
		values.put(sqldatabasehelper.LOAN_STATUS, "Awaiting");

		try {
			database.insert(sqldatabasehelper.TBL_LOANS, null, values);
			loanEditText.setText(""); 
			Toast.makeText(context, "Loan request was Successful", Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {
			Log.i("SQL_ERRO", e.getMessage());
		}
  }

	public void getLoans( String userID ){
		
		inflator = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		listingsStreamLayout = (LinearLayout) activity.findViewById(R.id.listingLayout);

		
		String query = "SELECT app_loan_id , "+sqldatabasehelper.LOAN_AMOUNT+" , "+sqldatabasehelper.LOAN_DATE+" , "+sqldatabasehelper.LOAN_STATUS+" FROM " + sqldatabasehelper.TBL_LOANS + " WHERE "+sqldatabasehelper.LOAN_USER_ID+" = '"+userID+"' ORDER BY app_loan_id DESC";
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

					View listingStream = inflator.inflate(R.layout.cr_layout, null);
					TextView id = (TextView) listingStream.findViewById(R.id.crID);
				    TextView date = (TextView) listingStream.findViewById(R.id.dateTextView);
					TextView amount = (TextView) listingStream.findViewById(R.id.amountTextView);
					TextView status = (TextView) listingStream.findViewById(R.id.statusTextView);
					id.setText(cursor.getString(0).toString().trim());
					date.setText(cursor.getString(2).toString().trim());
					amount.setText("Ksh "+cursor.getString(1).toString().trim());
					status.setText(cursor.getString(3).toString().trim());
					
					listingStream.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							TextView idTv = (TextView) v.findViewById(R.id.crID);
							
							Intent intent = new Intent(context , loanDetails.class);
	    					intent.putExtra("ID", idTv.getText().toString());
	    					activity.startActivity(intent);
						}
					});
				
					listingsStreamLayout.addView(listingStream);

				} while (cursor.moveToNext());
			}
			cursor.close();

		}

		
	}
	
	public void getLoansAdmin(  ){
		
		inflator = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		listingsStreamLayout = (LinearLayout) activity.findViewById(R.id.listingLayout);

		
		String query = "SELECT app_loan_id , "+sqldatabasehelper.LOAN_AMOUNT+" , "+sqldatabasehelper.LOAN_DATE+" , "+sqldatabasehelper.LOAN_STATUS+" FROM " + sqldatabasehelper.TBL_LOANS + " WHERE "+sqldatabasehelper.LOAN_STATUS+ " = 'Awaiting'  ORDER BY app_loan_id DESC";
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

					View listingStream = inflator.inflate(R.layout.cr_layout, null);
					TextView id = (TextView) listingStream.findViewById(R.id.crID);
				    TextView date = (TextView) listingStream.findViewById(R.id.dateTextView);
					TextView amount = (TextView) listingStream.findViewById(R.id.amountTextView);
					TextView status = (TextView) listingStream.findViewById(R.id.statusTextView);
					id.setText(cursor.getString(0).toString().trim());
					date.setText(cursor.getString(2).toString().trim());
					amount.setText("Ksh "+cursor.getString(1).toString().trim());
					status.setText(cursor.getString(3).toString().trim());
					
					listingStream.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							TextView idTv = (TextView) v.findViewById(R.id.crID);
							
							Intent intent = new Intent(context , updateLoanAdmin.class);
	    					intent.putExtra("ID", idTv.getText().toString());
	    					activity.startActivity(intent);
						}
					});
				
					listingsStreamLayout.addView(listingStream);

				} while (cursor.moveToNext());
			}
			cursor.close();

		}

		
	}
	
	public void approveLoan(String loanID , String interest , String deadline){
		
		ContentValues values = new ContentValues();
		values.put(sqldatabasehelper.LOAN_INTEREST, interest);
		values.put(sqldatabasehelper.LOAN_DEADLINE, deadline);
		values.put(sqldatabasehelper.LOAN_STATUS, "Approved");


		
		try {
			database.update(sqldatabasehelper.TBL_LOANS, values, "app_loan_id = '"+loanID+"'", null);
            Toast.makeText(context, "Successfully Approved", Toast.LENGTH_LONG).show();
           
		} catch (SQLException e) {
			Log.i("SQL_ERRO", e.getMessage());
		}
	}
	
	public void getLoan( String loanID , TextView date , TextView amount , TextView status , TextView deadline , TextView interest ){
	
		String query = "SELECT app_loan_id , "+sqldatabasehelper.LOAN_AMOUNT+" , "+sqldatabasehelper.LOAN_DATE+" , "+sqldatabasehelper.LOAN_STATUS+" , "+sqldatabasehelper.LOAN_DEADLINE+" , "+sqldatabasehelper.LOAN_INTEREST+" FROM " + sqldatabasehelper.TBL_LOANS + " WHERE app_loan_id = '"+loanID+"'";
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

	
			

					
					date.setText(cursor.getString(2).toString().trim());
					amount.setText("Ksh "+cursor.getString(1).toString().trim());
					status.setText(cursor.getString(3).toString().trim());
					deadline.setText(cursor.getString(4).toString().trim());
					interest.setText(cursor.getString(5).toString().trim());


				} while (cursor.moveToNext());
			}
			cursor.close();

		}
		
	}
	
	public void makeDeposit(String userID , EditText amount){
		  Calendar c = Calendar.getInstance();
		  System.out.println("Current time => " + c.getTime());

		  SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		  String formattedDate = df.format(c.getTime());
		  
		    ContentValues values = new ContentValues();
			values.put(sqldatabasehelper.DEPOSIT_USER_ID, userID);
			values.put(sqldatabasehelper.DEPOSIT_AMOUNT, amount.getText().toString().trim());
			values.put(sqldatabasehelper.DEPOSIT_DATE, formattedDate);
			values.put(sqldatabasehelper.DEPOSIT_CHAMA_ID, "1");
			values.put(sqldatabasehelper.DEPOSIT_TOTAL, String.valueOf(this._getChamaNetWorth()));
			
			try {
				database.insert(sqldatabasehelper.TBL_DEPOSITS, null, values);
				amount.setText(""); 
				Toast.makeText(context, "Deposit was Successful", Toast.LENGTH_SHORT).show();
			} catch (SQLException e) {
				Log.i("SQL_ERRO", e.getMessage());
			}

	}
	
	
	public void getDeposits( String userID ){
		
		inflator = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		listingsStreamLayout = (LinearLayout) activity.findViewById(R.id.listingLayout);

		
		String query = "SELECT app_deposit_id , "+sqldatabasehelper.DEPOSIT_AMOUNT+" , "+sqldatabasehelper.DEPOSIT_DATE+" FROM " + sqldatabasehelper.TBL_DEPOSITS + " WHERE "+sqldatabasehelper.DEPOSIT_USER_ID+" = '"+userID+"' ORDER BY app_deposit_id DESC";
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

					View listingStream = inflator.inflate(R.layout.deposit_layout, null);
					TextView id = (TextView) listingStream.findViewById(R.id.crID);
				    TextView date = (TextView) listingStream.findViewById(R.id.dateTextView);
					TextView amount = (TextView) listingStream.findViewById(R.id.amountTextView);

					id.setText(cursor.getString(0).toString().trim());
					date.setText(cursor.getString(2).toString().trim());
					amount.setText("Ksh "+cursor.getString(1).toString().trim());
		
				
					listingsStreamLayout.addView(listingStream);

				} while (cursor.moveToNext());
			}
			cursor.close();

		}

		
	}
	
	public void getChamaDeposits(){
		
		inflator = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		listingsStreamLayout = (LinearLayout) activity.findViewById(R.id.listingLayout);

		
		String query = "SELECT app_deposit_id , "+sqldatabasehelper.DEPOSIT_AMOUNT+" , "+sqldatabasehelper.DEPOSIT_DATE+" FROM " + sqldatabasehelper.TBL_DEPOSITS + " ORDER BY app_deposit_id DESC";
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

					View listingStream = inflator.inflate(R.layout.deposit_layout, null);
					TextView id = (TextView) listingStream.findViewById(R.id.crID);
				    TextView date = (TextView) listingStream.findViewById(R.id.dateTextView);
					TextView amount = (TextView) listingStream.findViewById(R.id.amountTextView);

					id.setText(cursor.getString(0).toString().trim());
					date.setText(cursor.getString(2).toString().trim());
					amount.setText("Ksh "+cursor.getString(1).toString().trim());
		
				
					listingsStreamLayout.addView(listingStream);

				} while (cursor.moveToNext());
			}
			cursor.close();

		}

		
	}

	public void getChamaNetWorth(TextView netWorth){
		
		int total = 0;


		
		String query = "SELECT  "+sqldatabasehelper.DEPOSIT_AMOUNT+" FROM " + sqldatabasehelper.TBL_DEPOSITS;
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

			

					total += Integer.parseInt(cursor.getString(0).toString().trim());
		
				
		

				} while (cursor.moveToNext());
			}
			cursor.close();
            netWorth.setText(String.valueOf(total + " KES"));
		}

		
	}
	
	public int _getChamaNetWorth(){
		
		int total = 0;


		
		String query = "SELECT  "+sqldatabasehelper.DEPOSIT_AMOUNT+" FROM " + sqldatabasehelper.TBL_DEPOSITS;
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

			

					total += Integer.parseInt(cursor.getString(0).toString().trim());
		
				
		

				} while (cursor.moveToNext());
			}
			cursor.close();
           return total;   
		}
		return total;   
		
	}
	
	public int updateChamaNetWorth(String amount){
		
		int total = 0 , finalInt = 0;
        

		
		String query = "SELECT  "+sqldatabasehelper.DEPOSIT_AMOUNT+" FROM " + sqldatabasehelper.TBL_DEPOSITS;
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

			

					total += Integer.parseInt(cursor.getString(0).toString().trim());
		
				
		

				} while (cursor.moveToNext());
			}
			finalInt = total - Integer.parseInt(amount);
			cursor.close();

		}
		
		return finalInt;

		
	}
	
	
	public void getChamaMembers(){
		
		inflator = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		listingsStreamLayout = (LinearLayout) activity.findViewById(R.id.listingLayout);

		
		String query = "SELECT "+sqldatabasehelper.FNAME+" , "+sqldatabasehelper.LNAME+" FROM " + sqldatabasehelper.TBL_USERS +" WHERE "+sqldatabasehelper.USER_LEVEL+ " = 'Member' ORDER BY app_user_id DESC";
		Cursor cursor = database.rawQuery(query, null);
		if(this.count(query) > 0){
			if (cursor.moveToFirst()) {
				do {

					View listingStream = inflator.inflate(R.layout.deposit_layout, null);
					TextView id = (TextView) listingStream.findViewById(R.id.crID);
				    TextView fname = (TextView) listingStream.findViewById(R.id.dateTextView);
					TextView lname = (TextView) listingStream.findViewById(R.id.amountTextView);

					id.setText(cursor.getString(0).toString().trim());
					fname.setText(cursor.getString(0).toString().trim());
					lname.setText(cursor.getString(1).toString().trim());
		
				
					listingsStreamLayout.addView(listingStream);

				} while (cursor.moveToNext());
			}
			cursor.close();

		}

		
	}
	
	/* Projects */
	
	  public void addProject(EditText name  , EditText desc ){
		    
		  Calendar c = Calendar.getInstance();
		  System.out.println("Current time => " + c.getTime());

		  SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		  String formattedDate = df.format(c.getTime());
		  
		    ContentValues values = new ContentValues();
			values.put(sqldatabasehelper.PROJECT_NAME, name.getText().toString().trim());
			values.put(sqldatabasehelper.PROJECT_DESC, desc.getText().toString().trim());
			values.put(sqldatabasehelper.PROJECT_STATUS, "Ongoing");
			values.put(sqldatabasehelper.PROJECT_START_DATE, formattedDate);


			try {
				database.insert(sqldatabasehelper.TBL_PROJECTS, null, values);
				
				Toast.makeText(context, "Project Added", Toast.LENGTH_SHORT).show();
				name.setText("");
				desc.setText("");
			} catch (SQLException e) {
				Log.i("SQL_ERRO", e.getMessage());
			}
	  }
	  
		public void getProjects(){
			
			inflator = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			listingsStreamLayout = (LinearLayout) activity.findViewById(R.id.listingLayout);

			
			String query = "SELECT app_project_id , "+sqldatabasehelper.PROJECT_NAME+" , "+sqldatabasehelper.PROJECT_STATUS+" FROM " + sqldatabasehelper.TBL_PROJECTS+"  ORDER BY app_project_id DESC";
			Cursor cursor = database.rawQuery(query, null);
			if(this.count(query) > 0){
				if (cursor.moveToFirst()) {
					do {

						View listingStream = inflator.inflate(R.layout.deposit_layout, null);
						TextView id = (TextView) listingStream.findViewById(R.id.crID);
					    TextView name = (TextView) listingStream.findViewById(R.id.dateTextView);
						TextView desc = (TextView) listingStream.findViewById(R.id.amountTextView);

						id.setText(cursor.getString(0).toString().trim());
						name.setText(cursor.getString(1).toString().trim());
						desc.setText(cursor.getString(2).toString().trim());
			            
						listingStream.setOnClickListener( new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								TextView idTv = (TextView) v.findViewById(R.id.crID);
								
								Intent intent = new Intent(context , viewProject.class);
		    					intent.putExtra("ID", idTv.getText().toString());
		    					activity.startActivity(intent);
							}
						}); 
					
						listingsStreamLayout.addView(listingStream);

					} while (cursor.moveToNext());
				}
				cursor.close();

			}

			
		}
		
		public void getMemberProjects(){
			
			inflator = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			listingsStreamLayout = (LinearLayout) activity.findViewById(R.id.listingLayout);

			
			String query = "SELECT app_project_id , "+sqldatabasehelper.PROJECT_NAME+" , "+sqldatabasehelper.PROJECT_STATUS+" FROM " + sqldatabasehelper.TBL_PROJECTS+"  ORDER BY app_project_id DESC";
			Cursor cursor = database.rawQuery(query, null);
			if(this.count(query) > 0){
				if (cursor.moveToFirst()) {
					do {

						View listingStream = inflator.inflate(R.layout.deposit_layout, null);
						TextView id = (TextView) listingStream.findViewById(R.id.crID);
					    TextView name = (TextView) listingStream.findViewById(R.id.dateTextView);
						TextView desc = (TextView) listingStream.findViewById(R.id.amountTextView);

						id.setText(cursor.getString(0).toString().trim());
						name.setText(cursor.getString(1).toString().trim());
						desc.setText(cursor.getString(2).toString().trim());
			            
						listingStream.setOnClickListener( new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								TextView idTv = (TextView) v.findViewById(R.id.crID);
								
								Intent intent = new Intent(context , memberviewProject.class);
		    					intent.putExtra("ID", idTv.getText().toString());
		    					activity.startActivity(intent);
							}
						}); 
					
						listingsStreamLayout.addView(listingStream);

					} while (cursor.moveToNext());
				}
				cursor.close();

			}

			
		}
		
		public void getProjectDetails(String ProjectID , TextView name , TextView desc , TextView status){


			
			String query = "SELECT app_project_id , "+sqldatabasehelper.PROJECT_NAME+" , "+sqldatabasehelper.PROJECT_STATUS+", "+sqldatabasehelper.PROJECT_DESC+" FROM " + sqldatabasehelper.TBL_PROJECTS+" WHERE app_project_id = '"+ProjectID+"'";
			Cursor cursor = database.rawQuery(query, null);
			if(this.count(query) > 0){
				if (cursor.moveToFirst()) {
					do {

                        name.setText(cursor.getString(1).toString().trim());
                        desc.setText(cursor.getString(3).toString().trim());
                        status.setText(cursor.getString(2).toString().trim());


					} while (cursor.moveToNext());
				}
				cursor.close();

			}

			
		}
		
		  public void addProjectProgress(String projectID  , EditText progressDesc ){
			    
			  Calendar c = Calendar.getInstance();
			  System.out.println("Current time => " + c.getTime());

			  SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			  String formattedDate = df.format(c.getTime());
			  
			    ContentValues values = new ContentValues();
				values.put(sqldatabasehelper.PROJECT_ID, projectID);
				values.put(sqldatabasehelper.PROJECT_PROGRESS_DESC, progressDesc.getText().toString().trim());
				values.put(sqldatabasehelper.PROJECT_PROGRESS_DATE, formattedDate);
	


				try {
					database.insert(sqldatabasehelper.TBL_PROJECT_PROGRESS, null, values);
					
					Toast.makeText(context, "Project Progress Added", Toast.LENGTH_SHORT).show();
					progressDesc.setText("");
	
				} catch (SQLException e) {
					Log.i("SQL_ERRO", e.getMessage());
				}
		  }
		  
			public void getProjectProgress(String projectID){
				
				inflator = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
				listingsStreamLayout = (LinearLayout) activity.findViewById(R.id.listingLayout);

				
				String query = "SELECT app_progress_id , "+sqldatabasehelper.PROJECT_PROGRESS_DATE+" , "+sqldatabasehelper.PROJECT_PROGRESS_DESC+" FROM " + sqldatabasehelper.TBL_PROJECT_PROGRESS+" WHERE "+sqldatabasehelper.PROJECT_ID+" ='"+projectID+"'  ORDER BY app_progress_id DESC";
				Cursor cursor = database.rawQuery(query, null);
				if(this.count(query) > 0){
					if (cursor.moveToFirst()) {
						do {

							View listingStream = inflator.inflate(R.layout.deposit_layout, null);
							TextView id = (TextView) listingStream.findViewById(R.id.crID);
						    TextView name = (TextView) listingStream.findViewById(R.id.dateTextView);
							TextView desc = (TextView) listingStream.findViewById(R.id.amountTextView);

							id.setText(cursor.getString(0).toString().trim());
							name.setText(cursor.getString(1).toString().trim());
							desc.setText(cursor.getString(2).toString().trim());

						
							listingsStreamLayout.addView(listingStream);

						} while (cursor.moveToNext());
					}
					cursor.close();

				}

				
			}
	
}
