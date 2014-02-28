package org.lp20.aikuma.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;
import java.io.File;
import java.util.UUID;
import org.lp20.aikuma.R;
import org.lp20.aikuma.util.VideoUtils;

/**
 * An activity that allows the user to watch a video before binding metadata to
 * it and sharing it, allowing the option of discarding it.
 *
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 */
public class VideoReview extends AikumaActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_review);
		// Flag to let the AikumaActivity superclass know that the user should
		// be warned that data will be discarded if they transition to another
		// activity.
		safeActivityTransition = true;

		Intent intent = getIntent();
		UUID uuid = UUID.fromString(
				(String) intent.getExtras().get("uuidString"));
		setUpVideo(uuid);
	}

	/**
	 * Prepares the video to be played.
	 *
	 * @param	uuid	The UUID of the video to be played.
	 */
	public void setUpVideo(UUID uuid) {
		File videoFile = VideoUtils.getNoSyncVideoFile(uuid);

		VideoView videoView = (VideoView) findViewById(R.id.videoView);
		videoView.setVideoPath(videoFile.getPath());
		videoView.start();
	}
}
