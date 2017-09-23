package breder.music.client.task;

import java.io.File;

import javax.swing.JFileChooser;

import breder.music.client.logic.user.UserManager;
import breder.music.client.ui.main.MainFrame;
import breder.util.task.LocalTask;

public class EjectMusicTask extends LocalTask {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUI() {
		File file = new File(".");
		{
			String env = System.getenv("HOME");
			if (env != null) {
				file = new File(env);
			}
		}
		JFileChooser chooser = new JFileChooser(file);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int state = chooser.showOpenDialog(MainFrame.getInstance());
		if (state == JFileChooser.APPROVE_OPTION) {
			File path = chooser.getSelectedFile();
			UserManager.getInstance().changePath(path.getAbsolutePath());
			MainFrame.getInstance().getTable().getModel().refresh();
		}
	}

}
