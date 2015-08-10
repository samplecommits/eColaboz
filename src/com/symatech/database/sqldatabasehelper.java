package com.symatech.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sqldatabasehelper extends SQLiteOpenHelper {

	public static String DB_NAME = "echama.db";
	public static int DB_VER = 1;
	public static String TBL_USERS = "tbl_users" , TBL_CHAMA = "tbl_chama" , TBL_PROJECTS = "tbl_projects" , TBL_PROJECT_PROGRESS = "tbl_project_progress" , TBL_LOGGED = "tbl_logged" , TBL_LOANS = "tbl_loans" , TBL_DEPOSITS = "tbl_deposits";
	public static String USERID = "user_id" , USERNAME = "user_name", PASSWORD = "user_password", FNAME = "user_fname" , LNAME = "user_lname" , EMAIL ="user_email" , TELEPHONE = "user_telephone" , USER_CHAMA_ID = "user_chama" , USER_LEVEL = "user_level" , USER_ID_NUMBER = "user_idnumber" , USER_PIN = "user_pin_number";
	public static String CHAMA_ID = "chama_id" , CHAMA_NAME = "chama_name";
	public static String PROJECT_NAME = "project_name" , PROJECT_DESC = "project_desc", PROJECT_START_DATE = "project_start_date", PROJECT_CHAMA_ID = "project_chama" , PROJECT_STATUS = "project_status" , PROJECT_ID = "project_id" , PROJECT_PROGRESS_DESC = "progress_desc" , PROJECT_PROGRESS_DATE = "progress_date";
	public static String LOGGED_USER_ID = "user_id_level" , LOGGED_USER_LEVEL = "user_logged_level";
	public static String LOAN_USER_ID = "load_user_id" , LOAN_AMOUNT = "loan_amount" , LOAN_DATE = "loan_date" , LOAN_STATUS = "loan_status" , LOAN_INTEREST = "loan_interest" , LOAN_DEADLINE = "loan_deadline";
	public static String DEPOSIT_USER_ID = "deposit_user_id" , DEPOSIT_DATE = "deposit_date" , DEPOSIT_CHAMA_ID = "deposit_chama" , DEPOSIT_AMOUNT = "deposit_amount" , DEPOSIT_TOTAL = "deposit_total";
	public static String CREATE_TBL_USERS = "CREATE TABLE " + TBL_USERS
			+ " (app_user_id INTEGER PRIMARY KEY AUTOINCREMENT , "+USERNAME + "  TEXT , "+PASSWORD+ " TEXT , "+FNAME+ " TEXT , "+LNAME+" TEXT , "+EMAIL+" TEXT , "+TELEPHONE+" TEXT , "+USER_CHAMA_ID+ " TEXT ,"+USER_LEVEL+" TEXT ,"+USER_ID_NUMBER+" TEXT ,"+USER_PIN+" TEXT)";
	public static String CREATE_TBL_CHAMA = "CREATE TABLE " + TBL_CHAMA
			+ " (app_chama_id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ CHAMA_ID + " TEXT , "+ CHAMA_NAME + "  TEXT )";
	public static String CREATE_TBL_PROJECTS = "CREATE TABLE " + TBL_PROJECTS
			+ " (app_project_id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ PROJECT_NAME + " TEXT , "+ PROJECT_CHAMA_ID + "  TEXT , "+PROJECT_STATUS+" TEXT , "+PROJECT_DESC+" TEXT , "+PROJECT_START_DATE+" TEXT )";
	public static String CREATE_TBL_PROJECT_PROGRESS = "CREATE TABLE " + TBL_PROJECT_PROGRESS
			+ " (app_progress_id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ PROJECT_ID + " TEXT , "+ PROJECT_PROGRESS_DESC + "  TEXT , "+PROJECT_PROGRESS_DATE+" TEXT )";
	public static String CREATE_TBL_LOGGED = "CREATE TABLE " + TBL_LOGGED
			+ " (app_logged_id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ LOGGED_USER_ID + " TEXT , "+LOGGED_USER_LEVEL+" TEXT )";
	public static String CREATE_TBL_LOANS= "CREATE TABLE " + TBL_LOANS
			+ " (app_loan_id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ LOAN_USER_ID + " TEXT , "+ LOAN_AMOUNT + "  TEXT , "+LOAN_STATUS+" TEXT , "+ LOAN_INTEREST + "  TEXT , "+LOAN_DEADLINE+" TEXT , "+LOAN_DATE+ " TEXT )";
	public static String CREATE_TBL_DEPOSITS = "CREATE TABLE " + TBL_DEPOSITS
			+ " (app_deposit_id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ DEPOSIT_USER_ID + " TEXT , "+ DEPOSIT_DATE + "  TEXT , "+DEPOSIT_CHAMA_ID+" TEXT , "+ DEPOSIT_AMOUNT + "  TEXT, "+DEPOSIT_TOTAL+ " TEXT)";

	public sqldatabasehelper(Context context) {
		super(context, DB_NAME, null, DB_VER);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		try {
			db.execSQL(CREATE_TBL_USERS);
			db.execSQL(CREATE_TBL_CHAMA);
			db.execSQL(CREATE_TBL_LOGGED);
			db.execSQL(CREATE_TBL_PROJECT_PROGRESS);
			db.execSQL(CREATE_TBL_PROJECTS);
			db.execSQL(CREATE_TBL_LOANS);
			db.execSQL(CREATE_TBL_DEPOSITS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.d("SQL_ERROR_ONCREATE", e.getMessage().toString());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

	}

}
