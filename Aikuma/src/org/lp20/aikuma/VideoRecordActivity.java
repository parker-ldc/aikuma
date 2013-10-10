package org.lp20.aikuma.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import java.io.File;
import java.util.UUID;
import org.lp20.aikuma.R;
import org.lp20.aikuma.util.VideoUtils;

public class VideoRecordActivity extends AikumaActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_record);
		uuid = UUID.randomUUID();
	}

	public void onVideoRecordButton(View view) {
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			File videoFile = VideoUtils.getVideoFile(uuid);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
		startActivityForResult(intent, ACTION_TAKE_VIDEO);
	}

	public static final int ACTION_TAKE_VIDEO = 1;
	private UUID uuid;
}
