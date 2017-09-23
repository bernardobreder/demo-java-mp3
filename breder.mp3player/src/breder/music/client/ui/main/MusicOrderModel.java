package breder.music.client.ui.main;

import breder.music.client.logic.music.Music;
import breder.music.client.ui.main.comparator.NormalOrderComparator;
import breder.util.swing.model.OrderObjectModel;
import breder.util.swing.model.StaticObjectModel;

public class MusicOrderModel extends OrderObjectModel<Music> {

	public MusicOrderModel(StaticObjectModel<Music> next) {
		super(next, new NormalOrderComparator());
	}

}
