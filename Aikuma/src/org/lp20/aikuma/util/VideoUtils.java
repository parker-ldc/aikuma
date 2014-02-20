package org.lp20.aikuma.util;

import java.io.File;
import java.util.UUID;

/**
 * Offers methods pertaining to videos.
 *
 * @author	Oliver Adams	<oliver.adams@gmail.com>
 */
public final class VideoUtils {
	private VideoUtils() {}

	private static File getVideosPath() {
		File path = new File(FileIO.getAppRootPath(), "videos");
		path.mkdirs();
		return path;
	}

	/**
	 * Returns a video corresponding to the given UUID
	 *
	 * @param	uuid	The UUID of the video.
	 * @return	A File representing the video file.
	 */
	public static File getVideoFile(UUID uuid) {
		return new File(getVideosPath(), uuid.toString() + ".mp4");
	}
}
