package breder.music.client.task;

import breder.music.client.logic.conf.MusicConfigurator;
import breder.music.client.logic.music.Music;
import breder.music.client.logic.music.MusicManager;
import breder.music.client.ui.main.MainFrame;
import breder.util.task.RemoteTask;

public class PlayMusicTask extends RemoteTask {

	private final Music music;

	public PlayMusicTask(Music music) {
		super(null);
		this.music = music;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void perform() throws Throwable {
		MusicManager.getInstance().setMusic(music);
		MusicManager.getInstance().play();
		if (!music.isLooked()) {
			music.setLooked(true);
			MusicConfigurator.getInstance().set(music);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUI() {
		MainFrame.getInstance().getTable().refresh();
	}

}
