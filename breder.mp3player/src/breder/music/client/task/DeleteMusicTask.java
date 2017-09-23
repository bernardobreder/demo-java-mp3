package breder.music.client.task;

import javax.swing.JOptionPane;

import breder.music.client.logic.music.Music;
import breder.music.client.ui.main.MainFrame;
import breder.music.client.ui.main.MusicTable;
import breder.util.task.LocalTask;

public class DeleteMusicTask extends LocalTask {

	@Override
	public void updateUI() {
		MusicTable table = MainFrame.getInstance().getTable();
		int[] rows = table.getTable().getSelectedRows();
		for (int row : rows) {
			Music music = table.getModel().getRow(row);
			if (JOptionPane.showConfirmDialog(null, "Do you want to delete, for ever in the filesystem, the file :\r\n" + music.toFile().getAbsolutePath(), "Delete Music", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				music.toFile().delete();
			}
		}
		table.getModel().refresh();
	}

}
