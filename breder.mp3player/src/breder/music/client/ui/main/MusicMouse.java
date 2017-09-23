package breder.music.client.ui.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import breder.music.client.logic.music.Music;
import breder.music.client.task.PlayMusicTask;

public class MusicMouse extends MouseAdapter {

	private final MusicTable table;

	public MusicMouse(MusicTable table) {
		super();
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1) {
			if (e.getClickCount() > 1) {
				int row = e.getY() / table.getTable().getRowHeight();
				Music music = table.getModel().getRow(row);
				new PlayMusicTask(music).start();
			}
		}
	}

}
