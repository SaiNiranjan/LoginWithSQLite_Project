package com.example.datewithme;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Register extends Activity {
	Intent i = null;
	ImageView im = null;
	EditText Ed1, Ed2, Ed3, Ed4;
	boolean flag = false;
	SQLiteDatabase db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		im = (ImageView) findViewById(R.id.show_hide);
		Ed1 = (EditText) findViewById(R.id.name);
		Ed2 = (EditText) findViewById(R.id.email_id);
		Ed3 = (EditText) findViewById(R.id.phone);
		Ed4 = (EditText) findViewById(R.id.password);
		db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
		db.execSQL("create table if not exists login(name varchar,mobile_no varchar,email_id varchar,password varchar,flag varchar)");

		im.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (flag == false) {
					im.setImageResource(R.drawable.hide);
					Ed4.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
					flag = true;
				} else {
					im.setImageResource(R.drawable.show);
					Ed4.setInputType(129);
					flag = false;

				}
			}
		});
	}

	public void action(View v) {
		switch (v.getId()) {
		case R.id.login:
			i = new Intent(this, Login.class);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_top,
					R.anim.slide_out_bottom);
			finish();
			break;
		case R.id.signin:
			String name = Ed1.getText().toString();
			String email_id = Ed2.getText().toString();
			String mobile_no = Ed3.getText().toString();
			String password = Ed4.getText().toString();
			if (name == null || name == "" || name.length() < 3) {
				show("Please Enter Correct Name.");
			} else if (mobile_no == null || mobile_no == ""
					|| mobile_no.length() < 10) {
				show("Please Enter Correct mobile number.");
			} else if (email_id == null || email_id == ""
					|| email_id.length() < 10) {
				show("Please Enter Correct Email id.");
			} else if (password == null || password == ""
					|| password.length() < 6) {
				show("Please Enter Strong Password.");
			} else {
				db.execSQL("insert into login values('" + name + "','"
						+ mobile_no + "','" + email_id + "','" + password
						+ "','nothing')");
				i = new Intent(this, Welcome.class);
				Welcome.username = name;
				startActivityForResult(i, 500);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
				db.close();
				finish();
			}
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void show(String str) {
		Toast.makeText(this, str, Toast.LENGTH_LONG).show();
	}
}
