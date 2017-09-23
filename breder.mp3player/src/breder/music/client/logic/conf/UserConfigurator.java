package breder.music.client.logic.conf;

import breder.music.client.task.EjectMusicTask;

public class UserConfigurator extends AbstractConfigurator {

	private static final UserConfigurator instance = new UserConfigurator();

	private UserConfigurator() {
		super("config");
	}

	public static UserConfigurator getInstance() {
		return instance;
	}

	public String getMusicSource() {
		String value = this.get("music.source");
		if (value == null) {
			new EjectMusicTask().start();
		}
		return value;
	}

}
