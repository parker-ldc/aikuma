package org.lp20.aikuma.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.UUID;
import org.lp20.aikuma.R;

/**
 * The activity that allows metadata about a video to be entered by the user.
 *
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 * @author	Florian Hanke	<florian.hanke@gmail.com>
 */
public class VideoMetadataActivity extends AikumaListActivity {

	private UUID uuid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_metadata);
		safeActivityTransition = true;

		Intent intent = getIntent();
		uuid = UUID.fromString(
				(String) intent.getExtras().get("uuidString"));
	}
}
