package breder.music.client.logic.user;

import java.io.File;

import breder.music.client.logic.conf.UserConfigurator;
import breder.music.client.logic.music.AbstractMusic;
import breder.music.client.logic.music.Mp3Music;

public class UserManager {

	private static final UserManager instance = new UserManager();

	private User user;

	private String[] paths;

	private UserManager() {
	}

	public User load(String[] paths) {
		this.paths = paths;
		this.user = new User(null);
		this.refreshMusic();
		return this.user;
	}

	public void changePath(String path) {
		UserConfigurator.getInstance().set("music.source", path);
		this.refreshMusic();
	}

	public void refreshMusic() {
		user.cleanMusics();
		if (this.paths.length == 0) {
			if (UserConfigurator.getInstance().getMusicSource() == null) {
				return;
			}
			File dir = new File(UserConfigurator.getInstance().getMusicSource());
			if (dir.exists()) {
				this.refreshMusic(dir);
			}
		} else {
			for (String path : this.paths) {
				File file = new File(path);
				if (file.exists()) {
					if (file.getName().toLowerCase().endsWith(".mp3")) {
						AbstractMusic music = new Mp3Music(file);
						user.addMusic(music);
					}
				}
			}
		}
	}

	private void refreshMusic(File dir) {
		for (File file : dir.listFiles()) {
			if (!file.isHidden()) {
				if (file.isDirectory()) {
					this.refreshMusic(file);
				} else {
					String name = file.getName().toLowerCase();
					if (name.endsWith(".mp3")) {
						AbstractMusic music = new Mp3Music(file);
						user.addMusic(music);
					}
				}
			}
		}
	}

	public User getUser() {
		return user;
	}

	public static UserManager getInstance() {
		return instance;
	}

}
