package breder.music.client.task;

import breder.music.client.logic.user.UserManager;
import breder.music.client.ui.main.MainFrame;
import breder.util.task.LocalTask;

public class RefreshMusicTask extends LocalTask {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUI() {
		UserManager.getInstance().refreshMusic();
		MainFrame.getInstance().getTable().getModel().refresh();
	}

}
