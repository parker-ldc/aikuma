/*
	Copyright (C) 2013, The Aikuma Project
	AUTHORS: Oliver Adams and Florian Hanke
*/
package org.lp20.aikuma.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lp20.aikuma.model.Speaker;
import org.lp20.aikuma.model.Language;
import org.lp20.aikuma.R;

/**
 * The array adapter for dealing with lists of Speakers.
 *
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 * @author	Florian Hanke	<florian.hanke@gmail.com>
 */
public class SpeakerArrayAdapter extends ArrayAdapter<Speaker> {

	/**
	 * Constructor.
	 *
	 * @param	context	The context that the array adapter will be used in.
	 * @param	speakers	The list of speakers that the array adapter is to
	 * deal with.
	 * @param	prevSelectedSpeakers	The list of speakers currently selected
	 * to be part of the recording
	 */
	public SpeakerArrayAdapter(Context context, List<Speaker> speakers,
			List<Speaker> prevSelectedSpeakers) {
		super(context, LIST_ITEM_LAYOUT, speakers);
		this.speakers = speakers;
		this.prevSelectedSpeakers = prevSelectedSpeakers;
		this.newSelectedSpeakers =
				new ArrayList<Speaker>(this.prevSelectedSpeakers);
		inflater = (LayoutInflater)
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View _, ViewGroup parent) {
		View speakerView =
				(View) inflater.inflate(LIST_ITEM_LAYOUT, parent, false);
		final Speaker speaker = getItem(position);
		TextView speakerNameView =
				(TextView) speakerView.findViewById(R.id.speakerName);
		speakerNameView.setText(speaker.getName());
		TextView speakerLanguagesView =
				(TextView) speakerView.findViewById(R.id.speakerLanguages);
		List<Language> languages = new
				ArrayList<Language>(speaker.getLanguages());
		if (languages.size() > 0) {
			String languageNames = languages.remove(0).getName();
			for (Language language : languages) {
				languageNames = languageNames + ", " + language.getName();
			}
			speakerLanguagesView.setText(languageNames);
		}
		ImageView speakerImage =
				(ImageView) speakerView.findViewById(R.id.speakerImage);
		try {
			speakerImage.setImageBitmap(speaker.getSmallImage());
		} catch (IOException e) {
			// If the image can't be loaded, we just leave it at that.
		}

		// Deal with checkbox related stuff
		CheckBox speakerCheckBox = (CheckBox)
				speakerView.findViewById(R.id.speakerCheckBox);
		if (newSelectedSpeakers.contains(speaker)) {
			speakerCheckBox.setChecked(true);
		}

		speakerCheckBox.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				boolean checked = ((CheckBox) view).isChecked();
				if (checked) {
					newSelectedSpeakers.add(speaker);
					checked = true;
				} else {
					newSelectedSpeakers.remove(speaker);
					checked = false;
				}
			}
		});

		return speakerView;
	}

	public List<Speaker> getNewSelectedSpeakers() {
		return newSelectedSpeakers;
	}

	private static final int LIST_ITEM_LAYOUT = R.layout.speaker_checkbox_list_item;
	private LayoutInflater inflater;
	private List<Speaker> speakers;
	private List<Speaker> prevSelectedSpeakers;
	private List<Speaker> newSelectedSpeakers;

}
