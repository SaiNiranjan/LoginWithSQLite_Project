package com.example.datewithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends Activity{
	public static String username = null;
	View logout;
	Intent i = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		TextView name = (TextView) findViewById(R.id.w_welcome);
		name.setText("Welcome "+username);
		logout = (Button) findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				i = new Intent();
				i.setClass(getApplicationContext(), MainActivity.class);
				startActivityForResult(i, 500);
				overridePendingTransition(R.anim.slide_in_top,
						R.anim.slide_out_bottom);
			}
		});
	}
}
