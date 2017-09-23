package breder.music.client.task;

import breder.music.client.logic.music.MusicManager;
import breder.music.client.ui.main.MainFrame;
import breder.util.task.RemoteTask;

public class StopMusicTask extends RemoteTask {

	public StopMusicTask() {
		super(null);
	}

	@Override
	public void perform() throws Throwable {
		MusicManager.getInstance().stop();
	}

	@Override
	public void updateUI() {
		MainFrame.getInstance().getTable().repaint();
	}

}
