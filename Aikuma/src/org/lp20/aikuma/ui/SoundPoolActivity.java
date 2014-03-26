/*
	Copyright (C) 2013, The Aikuma Project
	AUTHORS: Oliver Adams and Florian Hanke
*/
package org.lp20.aikuma.ui;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
import android.os.Bundle;
import java.io.File;
import java.io.IOException;
import org.lp20.aikuma.R;
import org.lp20.aikuma.model.Recording;
import org.lp20.aikuma.ui.AikumaActivity;

/**
 * Activity to test accelerating playback.
 * 
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 */
public class SoundPoolActivity extends AikumaActivity {

	private File mSoundFile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.soundpool);

		Intent intent = getIntent();
		String id = (String) intent.getExtras().get("id");
		mSoundFile = new File(Recording.getRecordingsPath(),
				Recording.getGroupIdFromId(id) + "/" + id + ".wav");
		Log.i("SoundPool", mSoundFile.getPath());

		testSoundPool();
	}

	private void testSoundPool() {
		SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		final int soundId = soundPool.load(mSoundFile.getPath(), 1);

		AudioManager am = (AudioManager)
				getSystemService(Context.AUDIO_SERVICE);
		final float volume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(
					SoundPool sp, int _int, int _int2) {
				sp.play(soundId, volume, volume, 1, 0, 1.5f);
			}
		});
	}
}
