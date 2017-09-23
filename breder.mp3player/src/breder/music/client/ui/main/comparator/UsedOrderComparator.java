package breder.music.client.ui.main.comparator;

import java.util.Comparator;

import breder.music.client.logic.conf.MusicConfigurator;
import breder.music.client.logic.music.Music;

public class UsedOrderComparator implements Comparator<Music> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(Music o1, Music o2) {
		Integer i1 = MusicConfigurator.getInstance().getCounter(o1);
		Integer i2 = MusicConfigurator.getInstance().getCounter(o2);
		return i1.compareTo(i2) * -1;
	}

}
