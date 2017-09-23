package breder.music.client.ui.main;

import breder.music.client.logic.music.Music;
import breder.music.client.ui.main.comparator.UsedOrderComparator;
import breder.util.swing.model.IObjectModel;
import breder.util.swing.model.OrderObjectModel;
import breder.util.swing.table.BTable;

public class MusicTable extends BTable<Music> {

	public MusicTable() {
		super(new MusicFilterModel(new OrderObjectModel<Music>(new MusicContentModel(), new UsedOrderComparator())));
		this.getTable().setShowGrid(false);
		this.getTable().setDefaultRenderer(Object.class, new MusicRenderer());
		this.getTable().addMouseListener(new MusicMouse(this));
		this.getTable().addKeyListener(new MusicKeyboard(this));
		this.getTable().getColumnModel().getColumn(2).setMinWidth(100);
		this.getTable().getColumnModel().getColumn(2).setMaxWidth(100);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IObjectModel<Music> getModel() {
		return super.getModel();
	}

}
