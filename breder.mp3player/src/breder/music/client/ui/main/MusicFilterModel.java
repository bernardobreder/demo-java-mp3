package breder.music.client.ui.main;

import breder.music.client.logic.music.Music;
import breder.util.swing.model.FilterObjectModel;
import breder.util.swing.model.IObjectModel;
import breder.util.util.StringUtil;

public class MusicFilterModel extends FilterObjectModel<Music> {

	private String filter;

	public MusicFilterModel(IObjectModel<Music> next) {
		super(next);
	}

	@Override
	public boolean accept(Music element) {
		if (filter == null || filter.length() == 0) {
			return true;
		}
		String[] strings = StringUtil.split(filter);
		for (int n = 0; n < strings.length; n++) {
			String string = strings[n];
			string = string.trim().toLowerCase();
			if (element.getArtist().toLowerCase().indexOf(string) >= 0) {
				return true;
			}
			if (element.getName().toLowerCase().indexOf(string) >= 0) {
				return true;
			}
		}
		return false;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
