package breder.music.client.task;

import breder.music.client.logic.music.MusicManager;
import breder.music.client.ui.main.MainFrame;
import breder.util.task.LocalTask;

public class NextMusicTask extends LocalTask {

	@Override
	public void updateUI() {
		MusicManager.getInstance().next();
		MainFrame.getInstance().getTable().repaint();
	}

}
