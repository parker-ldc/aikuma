package au.edu.melbuni.boldapp.models;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import au.edu.melbuni.boldapp.Player;
import au.edu.melbuni.boldapp.Recorder;
import au.edu.melbuni.boldapp.Sounder;
import au.edu.melbuni.boldapp.listeners.OnCompletionListener;
import au.edu.melbuni.boldapp.persisters.Persister;

public class Segment extends Observable {

	Timeline timeline = null;
	String identifier = null;

	protected boolean selected = false;
	protected boolean playing = false;
	protected boolean recording = false;

	public Segment(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public static Segment fromHash(Map<String, Object> hash) {
		String identifier = hash.get("id") == null ? "" : (String) hash.get("identifier");
		return new Segment(identifier);
	}
	public Map<String, Object> toHash() {
		Map<String, Object> hash = new LinkedHashMap<String, Object>();
		hash.put("id", this.identifier);
		return hash;
	}
	
	// Load a segment based on its identifier.
	//
	public static Segment load(Persister persister, String identifier) {
		return persister.loadSegment(identifier);
	}
	
	public String getSoundfilePath() {
		return Sounder.generateFullFilename("recording_" + getIdentifier());
	}
	
	public void putSoundfile(byte[] bytes) {
		String path = getSoundfilePath(); // TODO Refactor!
		
		BufferedOutputStream bufOut = null;
		try {
			// TODO DRY. See above.
			//
        	File file = new File(path);
        	file.getParentFile().mkdirs();
        	file.createNewFile();
        	
            FileOutputStream out = new FileOutputStream(path);
            bufOut = new BufferedOutputStream(out);
            
            bufOut.write(bytes);
            
            bufOut.close();
        } catch (Exception e) {
        	System.out.println("ERROR:" + path);
            Log.e("Error reading file", e.toString());
        }
	}
	
	// Save the segment's metadata.
	//
	public void save(Persister persister) {
		persister.save(this);
	}
	
	public boolean isPlaying() {
		return this.playing;
	}
	public void setRecording(boolean recording) {
		this.recording = recording;
		setChanged();
		notifyObservers();
	}
	public boolean isRecording() {
		return this.recording;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
		setChanged();
		notifyObservers();
	}
	public boolean isSelected() {
		return this.selected;
	}

	public void startPlaying(Player player, OnCompletionListener listener) {
		player.startPlaying(identifier, listener);
		setPlaying(true);
	}

	public void stopPlaying(Player player) {
		player.stopPlaying();
		setPlaying(false);
	}

	public void startRecording(Recorder recorder) {
		recorder.startRecording(identifier);
		setRecording(true);
	}

	public void stopRecording(Recorder recorder) {
		recorder.stopRecording();
		setRecording(false);
	}

	public void select() {
		setSelected(true);
	}
	
	public void deselect() {
		setSelected(false);
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
		setChanged();
		notifyObservers();
	}
	
	public static class ViewHandler implements Observer {
		
		private View view;

		public ViewHandler(View view) {
			this.view = view;
		}

		@Override
		public void update(Observable observable, Object data) {
			if (view == null) {
				return;
			}
			
			Segment segment = (Segment) observable;
			
			System.out.println(">>>" + segment.recording + segment.playing + segment.selected);
			
			if (segment.recording) {
				view.setBackgroundColor(Color.RED);
				return;
			}
			if (segment.playing) {
				view.setBackgroundColor(Color.GREEN);
				return;
			}
			if (segment.selected) {
				view.setBackgroundColor(Color.LTGRAY);
			} else {
				view.setBackgroundColor(Color.GRAY);
			}
		}
	}

}