package org.lp20.aikuma.audio;

import android.util.Log;
import android.media.MediaPlayer;
import java.io.IOException;
import org.lp20.aikuma.model.Recording;

/**
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 * @author	Florian Hanke	<florian.hanke@gmail.com>
 */
public class SimplePlayer extends Player {

	/**
	 * Creates a player to play the supplied recording.
	 *
	 * @param	recording	The metadata of the recording to play.
	 */
	public SimplePlayer(Recording recording) throws IOException {
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setDataSource(recording.getFile().getCanonicalPath());
		mediaPlayer.prepare();
	}

	/** Starts or resumes playback of the recording. */
	public void play() {
		mediaPlayer.start();
	}

	/** Pauses the playback. */
	public void pause() {
		mediaPlayer.pause();
	}

	/** Indicates whether the recording is currently being played. */
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	/** Get current point in the recording in milliseconds. */
	public int getCurrentMsec() {
		return mediaPlayer.getCurrentPosition();
	}

	/** Get the duration of the recording in milliseconds. */
	public int getDurationMsec() {
		return mediaPlayer.getDuration();
	}

	/** Seek to a given point in the recording in milliseconds. */
	public void seekToMsec(int msec) {
		mediaPlayer.seekTo(msec);
	}

	/** Set the callback to be run when the recording completes playing. */
	public void setOnCompletionListener(final OnCompletionListener listener) {
		mediaPlayer.setOnCompletionListener(
				new MediaPlayer.OnCompletionListener() {
					public void onCompletion(MediaPlayer _mp) {
						listener.onCompletion(SimplePlayer.this);
					}
				});
	}


	/** The MediaPlayer used to play the recording. **/
	private MediaPlayer mediaPlayer;
}