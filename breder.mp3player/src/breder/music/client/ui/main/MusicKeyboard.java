package breder.music.client.ui.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import breder.music.client.logic.music.Music;
import breder.music.client.task.NextMusicTask;
import breder.music.client.task.PlayMusicTask;

public class MusicKeyboard extends KeyAdapter {

	private final MusicTable table;

	public MusicKeyboard(MusicTable table) {
		super();
		this.table = table;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.play();
			e.consume();
		}
	}

	private void play() {
		int row = table.getTable().getSelectedRow();
		if (row == -1) {
			new NextMusicTask().start();
		} else {
			Music music = table.getModel().getRow(row);
			new PlayMusicTask(music).start();
		}
	}

}
