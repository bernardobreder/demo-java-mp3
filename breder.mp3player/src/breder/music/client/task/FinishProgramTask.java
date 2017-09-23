package breder.music.client.task;

import javax.swing.SwingUtilities;

import breder.music.client.ui.main.MainFrame;
import breder.util.task.RemoteTask;

public class FinishProgramTask extends RemoteTask {

	public FinishProgramTask() {
		super(null);
	}

	@Override
	public void perform() throws Throwable {
		// IServiceLocator.user.logout();
		// IServerService.DEFAULT.finish();
	}

	@Override
	public void handler(Throwable t) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateUI();
			}
		});
	}

	@Override
	public void updateUI() {
		MainFrame.getInstance().dispose();
		System.exit(0);
	}

}
