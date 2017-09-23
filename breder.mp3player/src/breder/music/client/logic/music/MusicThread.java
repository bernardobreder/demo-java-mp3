package breder.music.client.logic.music;

import breder.music.client.ui.main.MainFrame;
import breder.util.task.ThreadUtil;

public class MusicThread extends Thread {

	public MusicThread() {
		super("MusicThread");
	}

	@Override
	public void run() {
		while (true) {
			while (!MusicManager.getInstance().isComplete()) {
				if (MainFrame.getInstance().isClosed()) {
					return;
				}
				try {
					Integer timer = MusicManager.getInstance().getCurrentTime();
					if (timer != null) {
						MusicManager.getInstance().fireTimerChange(timer);
					}
				} catch (Exception e) {
				}
				ThreadUtil.sleep(1000);
			}
			MusicManager.getInstance().next();
			ThreadUtil.sleep(1000);
		}
	}

}
