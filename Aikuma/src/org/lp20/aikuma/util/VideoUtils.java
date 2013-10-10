package org.lp20.aikuma.util;

import java.io.File;
import java.util.UUID;

public final class VideoUtils {
	private VideoUtils() {}

	private static File getVideosPath() {
		File path = new File(FileIO.getAppRootPath(), "videos");
		path.mkdirs();
		return path;
	}

	public static File getVideoFile(UUID uuid) {
		return new File(getVideosPath(), uuid.toString() + ".mp4");
	}
}
