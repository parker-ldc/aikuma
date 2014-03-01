package org.lp20.aikuma.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;
import java.io.File;
import java.util.UUID;
import org.lp20.aikuma.R;
import org.lp20.aikuma.util.VideoUtils;

/**
 * An activity that allows a user to take a video
 *
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 */
public class VideoRecordActivity extends AikumaActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_record);
		uuid = UUID.randomUUID();
	}

	/**
	 * When the video record button is pressed
	 *
	 * @param	view	The video record button
	 */
	public void onVideoRecordButton(View view) {
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		videoFile = VideoUtils.getNoSyncVideoFile(uuid);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
		startActivityForResult(intent, ACTION_TAKE_VIDEO);
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
				Intent intent = new Intent(this, VideoReviewActivity.class);
				intent.putExtra("uuidString", uuid.toString());
				startActivity(intent);
				VideoRecordActivity.this.finish();
				//VideoView videoView = (VideoView) findViewById(R.id.videoView);
				//videoView.setVideoPath(videoFile.getPath());
				//videoView.start();
			}
		}
	}

	/**
	 * Code to indicate that a video is to be recorded.
	 */
	public static final int ACTION_TAKE_VIDEO = 1;
	private UUID uuid;
	private File videoFile;
}
