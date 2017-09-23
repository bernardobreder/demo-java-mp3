package breder.music.client.ui.main.comparator;

import java.util.Comparator;

import breder.music.client.logic.music.AbstractMusic;

public class MusicOrderComparator implements Comparator<AbstractMusic> {

	@Override
	public int compare(AbstractMusic o1, AbstractMusic o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
