package breder.music.client.ui.main;

import breder.music.client.logic.music.Music;
import breder.music.client.logic.user.UserManager;
import breder.util.swing.model.StaticObjectModel;

public class MusicContentModel extends StaticObjectModel<Music> {

	public MusicContentModel() {
		super(null, new String[] { "Artist", "Music", "Star" });
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Comparable<?> getValueAt(Music music, int row, int column) {
		if (column == 0) {
			return music.getArtist();
		} else if (column == 1) {
			return music.getName();
		} else if (column == 2) {
			return music.getStar();
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public Music getRow(int row) {
		return (Music) UserManager.getInstance().getUser().getMusics().get(row);
	}

	@Override
	public int getSize() {
		return UserManager.getInstance().getUser().getMusics().size();
	}

	@Override
	public void refreshModel() {
		UserManager.getInstance().refreshMusic();
	}

}
