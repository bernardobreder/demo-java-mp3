package breder.music.client.ui.main;

import breder.music.client.logic.music.Music;
import breder.music.client.logic.music.MusicListener;

public class MainMusicListener extends MusicListener {

	@Override
	public void musicChange(Music music) {
		MainFrame.getInstance().getPanel().getMusicLabel().setText(music.getFullName());
		// MainFrame.getInstance().getPanel().getScroll().setMaximum(
		// music.getDuration().intValue());
	}

	@Override
	public void timerChange(int value) {
		MainFrame.getInstance().getPanel().getScroll().setValue(value);
	}

}
