package breder.music.client.task;

import breder.music.client.logic.user.UserManager;
import breder.music.client.ui.MusicTrayIcon;
import breder.music.client.ui.main.MainFrame;
import breder.util.lookandfeel.ILookAndFeel;
import breder.util.task.LocalTask;
import breder.util.trayicon.BTrayIcon;

public class InitializeMediaPlayer extends LocalTask {

	private String[] paths;

	public InitializeMediaPlayer(String[] paths) {
		this.paths = paths;
	}

	@Override
	public void updateUI() {
		ILookAndFeel.DEFAULT.installMetal();
		BTrayIcon.build(new MusicTrayIcon());
		UserManager.getInstance().load(this.paths);
		MainFrame.getInstance().setVisible(true);
	}

}
