package org.lp20.aikuma.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import java.io.File;
import java.util.UUID;
import org.lp20.aikuma.R;
import org.lp20.aikuma.util.VideoUtils;

import java.util.UUID;
import android.provider.MediaStore;
import org.lp20.aikuma.util.VideoUtils;
import android.content.Intent;
import android.net.Uri;

/**
 * An activity that allows the user to watch a video before binding metadata to
 * it and sharing it, allowing the option of discarding it.
 *
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 */
public class VideoReviewActivity extends AikumaActivity {

	static final int ACTION_TAKE_VIDEO = 1;

	private UUID uuid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.video_review);
		// Flag to let the AikumaActivity superclass know that the user should
		// be warned that data will be discarded if they transition to another
		// activity.
		safeActivityTransition = true;

		requestVideo();
	}

	/**
	 * Request a video to be taken
	 */
	public void requestVideo() {
		uuid = UUID.randomUUID();
		Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		File videoFile = VideoUtils.getNoSyncVideoFile(uuid);
		videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
		startActivityForResult(videoIntent, ACTION_TAKE_VIDEO);
	}

	/**
	 * Called when the video recording is complete
	 *
	 * @param	requestCode	The code indicating the type of request (should
	 * only be video)
	 * @param	resultCode	Code indicating success or not
	 * @param	_intent	The intent sent back to this activity.
	 */
	protected void onActivityResult(
			int requestCode, int resultCode, Intent _intent) {
		if (requestCode == ACTION_TAKE_VIDEO) {
			if (resultCode == RESULT_OK) {
				setUpVideo(uuid);
			}
		}
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

	/**
	 * When the user presses the ok button.
	 *
	 * @param	button	The Ok button.
	 */
	public void onOkButtonPressed(View button) {
		Intent intent = new Intent(this, VideoMetadataActivity.class);
		intent.putExtra("uuidString", uuid.toString());
		startActivity(intent);
		VideoReviewActivity.this.finish();
	}

	/**
	 * When the user presses the cancel button.
	 *
	 * @param	button	The cancel button.
	 */
	public void onCancelButtonPressed(View button) {
		onBackPressed();
	}
}
