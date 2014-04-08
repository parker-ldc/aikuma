/*
	Copyright (C) 2013, The Aikuma Project
	AUTHORS: Oliver Adams and Florian Hanke
*/
package org.lp20.aikuma.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import org.lp20.aikuma.model.Speaker;
import org.lp20.aikuma.R;

/**
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 * @author	Florian Hanke	<florian.hanke@gmail.com>
 */
public class SpeakersActivity extends AikumaListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speakers);
		//Lets method in superclass know to ask user if they are willing to
		//discard new data on an activity transition via the menu.
		safeActivityTransition = true;
		selectedSpeakers = new ArrayList<Speaker>();
	}

	@Override
	public void onResume() {
		super.onResume();

		speakers = Speaker.readAll();
		adapter =
				new SpeakerArrayAdapter(this, speakers, selectedSpeakers);
		setListAdapter(adapter);
	}

	/**
	 * Starts the AddSpeakerActivity.
	 *
	 * @param	_view	The add-speaker button that was pressed.
	 */
	public void addSpeakerButtonPressed(View _view) {
		Intent intent = new Intent(this, AddSpeakerActivity.class);
		startActivity(intent);
	}

	/**
	 * Returns to the RecordingMetadataActivity, so there is no need to prompt
	 * the user that they may be discarding data.
	 */
	@Override
	public void onBackPressed() {
		this.finish();
	}

	/**
	 * When the OK button is pressed
	 *
	 * @param	button	The OK button
	 */
	public void onOkButtonPressed(View button) {
		// Set the selected speakers to reflect what was checked in the list of
		// checkboxes.
		selectedSpeakers = adapter.getNewSelectedSpeakers();

		// Make an intent containing the list of speakers and send it back to
		// the recording metadata activity.
		Intent intent = new Intent();
		intent.putParcelableArrayListExtra("speakers", selectedSpeakers);
		setResult(RESULT_OK, intent);
		this.finish();
	}

	/**
	 * When the Cancel button is pressed
	 *
	 * @param	button	The cancel button
	 */
	public void onCancelButtonPressed(View button) {
		this.finish();
	}

	private List<Speaker> speakers;
	private ArrayList<Speaker> selectedSpeakers;
	private SpeakerArrayAdapter adapter;
}
