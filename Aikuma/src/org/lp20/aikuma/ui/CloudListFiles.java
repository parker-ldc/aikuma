package org.lp20.aikuma.ui;

import org.lp20.aikuma.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CloudListFiles extends AikumaActivity {
	TextView log;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cloud_list_files);
		log = (TextView) findViewById(R.id.text_file_list);
		final Button button = (Button) findViewById(R.id.button_list_files);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listFiles();
			}
		});
		
		findViewById(R.id.button_sign_in).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				signIn();
			}
		});
	}
	
	private void listFiles() {
		
	}
	
	private void signIn() {
	}
}
