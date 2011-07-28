package au.edu.melbuni.boldapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.base);
        
        // Configure view.
        //
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout menu = (LinearLayout) findViewById(R.id.menu);
        
        // Menu.
        //
     	menu.addView(layoutInflater.inflate(R.layout.navigation, menu, false));
     	menu.addView(layoutInflater.inflate(R.layout.user, menu, false));
     	menu.addView(layoutInflater.inflate(R.layout.help, menu, false));
     	
     	// Content.
     	//
     	FrameLayout content = (FrameLayout) findViewById(R.id.content);
     	content.addView(layoutInflater.inflate(R.layout.main, content, false));
     	
     	// Behavior.
     	//
        final ImageButton recordButton = (ImageButton) findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	startActivityForResult(new Intent(view.getContext(), OriginalChoiceActivity.class), 0);
            }
        });
    }
}