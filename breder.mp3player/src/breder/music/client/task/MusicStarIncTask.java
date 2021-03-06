package breder.music.client.task;

import breder.music.client.logic.conf.MusicConfigurator;
import breder.music.client.logic.music.Music;
import breder.music.client.ui.main.MainFrame;
import breder.util.task.LocalTask;

/**
 * Incrementa uma estrela
 * 
 * 
 * @author Bernardo Breder
 */
public class MusicStarIncTask extends LocalTask {

	private Music[] musics;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean preAction() {
		musics = MainFrame.getInstance().getTable().getSelectedRows(Music.class);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUI() {
		for (Music music : musics) {
			music.setStar(Math.min(5, music.getStar() + 1));
			music.setLooked(true);
			MusicConfigurator.getInstance().set(music);
		}
		MainFrame.getInstance().getTable().refresh();
		new MusicSaveTask().start();
	}

}
