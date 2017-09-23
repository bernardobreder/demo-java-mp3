package breder.music.client.ui.main.comparator;

import java.util.Comparator;

import breder.music.client.logic.music.Music;

public class NormalOrderComparator implements Comparator<Music> {

	@Override
	public int compare(Music o1, Music o2) {
		return o1.getFullName().compareTo(o2.getFullName());
	}

}
