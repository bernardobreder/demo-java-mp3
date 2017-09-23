package breder.music.client.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import breder.music.client.logic.music.Music;
import breder.music.client.ui.main.MainFrame;
import breder.music.client.ui.main.MusicTable;
import breder.util.task.LocalTask;
import breder.util.util.FileUtil;

public class RenameMusicTask extends LocalTask {

	@Override
	public void updateUI() {
		MusicTable table = MainFrame.getInstance().getTable();
		int[] rows = table.getTable().getSelectedRows();
		for (int row : rows) {
			Music music = table.getModel().getRow(row);
			String name = music.toFile().getName();
			int index = name.lastIndexOf('.');
			name = name.substring(0, index);
			String other = JOptionPane.showInputDialog("Rename with format : <artist> - <music>", name);
			if (other != null) {
				File ifile = music.toFile();
				try {
					InputStream input = new FileInputStream(ifile.getAbsolutePath());
					File ofile = new File(ifile.getParent(), other.trim() + ifile.getName().substring(index));
					if (ofile.exists()) {
						throw new Exception("file already exist");
					}
					OutputStream output = new FileOutputStream(ofile);
					FileUtil.copy(input, output);
					input.close();
					output.close();
					if (!ifile.delete()) {
						ofile.delete();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
		table.getModel().refresh();
	}

}
