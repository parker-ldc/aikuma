package au.edu.melbuni.boldapp.persisters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;

import au.edu.melbuni.boldapp.Segment;
import au.edu.melbuni.boldapp.Segments;
import au.edu.melbuni.boldapp.Timeline;
import au.edu.melbuni.boldapp.Timelines;
import au.edu.melbuni.boldapp.User;
import au.edu.melbuni.boldapp.Users;

// A persister defines in what format
// the data is saved, and where.
//
// Not the order though.
// Because that is something else entirely.
//
public abstract class Persister {

	// Public API.
	//
	
	public abstract void save(Users users);
	
	public abstract Users loadUsers();

	public abstract void save(User user);

	public abstract User loadUser(String identifier);
	
	public abstract void save(Timelines timelines);
	
	public abstract Timelines loadTimelines();
	
	public abstract void save(Timeline timeline);
	
	public abstract Timeline loadTimeline(String identifier);

	public abstract void save(Segments segments);
	
	public abstract Segments loadSegments();
	
	public abstract void save(Segment segment);
	
	public abstract Segment loadSegment(String identifier);
	
	public abstract String fileExtension();
	
	// Current
	//
	
	// Returns null if no current user has been saved.
	//
	public User loadCurrentUser(Users users) {
		String identifier = null;
		try {
			identifier = read(pathForCurrentUser());
		} catch (IOException e) {
			identifier = null;
		}
		
		for (User user : users) {
			if (user.getIdentifierString() == identifier) {
				return user;
			}
		}
		return null;
	}
	
	public void saveCurrentUser(User user) {
		write(pathForCurrentUser(), user.getIdentifierString());
	}
	
	public String pathForCurrentUser() {
		return "current/user.txt";
	}
	
//	// Returns null if no current timeline has been saved.
//	//
//	public Timeline loadCurrentTimeline(Timelines timelines) {
//		String identifier = read(pathForCurrentUser());
//		for (Timeline timeline : timelines) {
//			if (timeline.getIdentifierString() == identifier) {
//				return timeline;
//			}
//		}
//		return null;
//	}
//	
//	public void saveCurrentTimeline(Timeline timeline) {
//		write(pathForCurrentUser(), timeline.getIdentifierString());
//	}
//	
//	public String pathForCurrentTimeline() {
//		return "current/timeline.txt";
//	}

	// User(s).
	//
	
	public void write(Users users, String data) {
		write(pathFor(users), data);
	}
	
	public String readUsers() throws IOException {
		return read(pathForUsers());
	}

	public void write(User user, String data) {
		write(pathFor(user), data);
	}

	public String readUser(String identifier) throws IOException {
		return read(pathForUser(identifier));
	}

	// Segment(s).
	//
	
	public void write(Segments segments, String data) {
		write(pathFor(segments), data);
	}

	public String readSegments() throws IOException {
		return read(pathForSegments());
	}

	public void write(Segment segment, String data) {
		write(pathFor(segment), data);
	}

	public String readSegment(String identifier) throws IOException {
		return read(pathForSegment(identifier));
	}

	// Timeline(s).
	//
	
	public void write(Timelines timelines, String data) {
		write(pathFor(timelines), data);
	}

	public String readTimelines() throws IOException {
		return read(pathForTimelines());
	}

	public void write(Timeline timeline, String data) {
		write(pathFor(timeline), data);
	}

	public String readTimeline(String identifier) throws IOException {
		return read(pathForUser(identifier));
	}

	// Helper methods.
	//
	
	public String pathFor(Users users) {
		return pathForUsers();
	}
	
	public String pathForUsers() {
		return "users/list" + fileExtension();
	}

	public String pathFor(User user) {
		return pathForUser(user.getIdentifierString());
	}

	public String pathForUser(String identifier) {
		return "users/" + identifier + fileExtension();
	}
	
	public String pathFor(Timelines timelines) {
		return pathForTimelines();
	}
	
	public String pathForTimelines() {
		return "timelines/list" + fileExtension();
	}
	
	public String pathFor(Timeline timeline) {
		return pathForTimeline(timeline.getIdentifierString());
	}

	public String pathForTimeline(String identifier) {
		return "timelines/" + identifier + fileExtension();
	}
	
	public String pathFor(Segments segments) {
		return pathForSegments();
	}
	
	public String pathForSegments() {
		return "segments/list" + fileExtension(); // TODO Really?
	}
	
	public String pathFor(Segment segment) {
		return pathForTimeline(segment.getIdentifierString());
	}

	public String pathForSegment(String identifier) {
		return "segments/" + identifier + fileExtension();
	}

	// Write the data to the file.
	//
	// TODO Buffer?
	//
	public void write(String fileName, String data) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.write(data);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Read the content of the file.
	//
	public String read(String fileName) throws IOException {
		FileReader fileReader = new FileReader(fileName);
		CharBuffer target = CharBuffer.allocate(1024); // TODO 1024?
		fileReader.read(target);
		return target.toString();
	}

}
