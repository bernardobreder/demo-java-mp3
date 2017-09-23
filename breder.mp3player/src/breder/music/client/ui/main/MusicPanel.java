package breder.music.client.ui.main;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import breder.music.client.logic.music.Music;
import breder.music.client.logic.music.MusicListener;
import breder.music.client.logic.music.MusicManager;
import breder.music.client.resource.MediaPlayerResource;
import breder.music.client.task.EjectMusicTask;
import breder.music.client.task.NextMusicTask;
import breder.music.client.task.PlayMusicTask;
import breder.music.client.task.RefreshMusicTask;
import breder.music.client.task.StopMusicTask;
import breder.music.client.ui.main.comparator.MusicOrderComparator;
import breder.music.client.ui.main.comparator.NormalOrderComparator;
import breder.music.client.ui.main.comparator.UsedOrderComparator;
import breder.music.player.IMusicPlayer;
import breder.util.swing.BAction;
import breder.util.swing.GBC;
import breder.util.swing.model.IObjectModel;
import breder.util.swing.model.OrderObjectModel;
import breder.util.task.EventTask;

public class MusicPanel extends JPanel {

	private JLabel musicLabel;

	private JScrollBar scroll;

	private JTextField filterText;

	private ButtonGroup group = new ButtonGroup();

	public MusicPanel() {
		this.build();
	}

	private void build() {
		this.setLayout(new GridBagLayout());
		this.add(this.buildMusicLabel(), new GBC(0, 0).horizontal());
		this.add(this.buildProgress(), new GBC(0, 1).horizontal());
		this.add(this.buildButtons(), new GBC(0, 2).horizontal());
	}

	private JToolBar buildButtons() {
		JToolBar bar = new JToolBar();
		bar.setFloatable(false);
		bar.add(this.buildEject());
		bar.add(this.buildRefresh());
		bar.add(this.buildStop());
		bar.add(this.buildNext());
		bar.addSeparator();
		bar.add(this.buildSearch());
		bar.addSeparator();
		bar.add(this.buildNormal());
		bar.add(this.buildUsed());
		bar.add(this.buildMusic());
		return bar;
	}

	/**
	 * Constroi a barra de progresso
	 * 
	 * @return a barra de progresso
	 */
	private Component buildProgress() {
		final JProgressBar bar = new JProgressBar(0, 1000);
		IMusicPlayer.DEFAULT.addListener(new Runnable() {
			@Override
			public void run() {
				final int value = (int) (IMusicPlayer.DEFAULT.getPercent() * 1000);
				if (bar.getValue() != value) {
					EventTask.invokeLater(new Runnable() {
						@Override
						public void run() {
							bar.setValue(value);
						}
					});
				}
			}
		});
		return bar;
	}

	private Component buildVolume() {
		final JScrollBar bar = new JScrollBar();
		bar.setMinimum(0);
		bar.setMaximum(110);
		bar.setValue(100 - (int) (MusicManager.getInstance().getVolume() * 100));
		bar.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				float value = ((float) (100 - bar.getValue())) / 100;
				MusicManager.getInstance().setVolume(value);
			}
		});
		return bar;
	}

	private Component buildSearch() {
		JPanel panel = new JPanel(new BorderLayout());
		filterText = new JTextField();
		filterText.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				onSearchAction();
			}
		});
		panel.add(filterText, BorderLayout.CENTER);
		return panel;
	}

	protected void onSearchAction() {
		MusicFilterModel model = MainFrame.getInstance().getTable().getModel().findNext(MusicFilterModel.class);
		model.setFilter(filterText.getText().trim());
		if (model.filter()) {
			MusicManager.getInstance().cleanCache();
		}
	}

	private Component buildMusicLabel() {
		musicLabel = new JLabel(" ", SwingConstants.CENTER);
		return musicLabel;
	}

	private Component buildMusicController() {
		JPanel panel = new JPanel(new GridBagLayout());
		{
			final JLabel label = new JLabel();
			MusicManager.getInstance().addListener(new MusicListener() {
				@Override
				public void timerChange(int value) {
					int s = value / 1000;
					int min = s / 60;
					int seg = s - min * 60;
					label.setText(String.format("%02d:%02d", min, seg));
				}
			});
			panel.add(label, new GBC(0, 0));
		}
		{
			this.scroll = new JScrollBar(Adjustable.HORIZONTAL);
			this.scroll.setMaximum(0);
			this.scroll.addAdjustmentListener(new AdjustmentListener() {
				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
					onScrollChange();
				}
			});
			panel.add(this.scroll, new GBC(1, 0).horizontal());
		}
		{
			final JLabel label = new JLabel();
			MusicManager.getInstance().addListener(new MusicListener() {
				@Override
				public void musicChange(Music music) {
					double value = music.getDuration();
					int min = (int) value / 60;
					int seg = (int) value - min * 60;
					label.setText(String.format("%02d:%02d", min, seg));
				}
			});
			panel.add(label, new GBC(2, 0));
		}
		return panel;
	}

	protected void onScrollChange() {
	}

	private Component buildStop() {
		JButton button = new JButton(new ImageIcon(MediaPlayerResource.STOP));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onStopClick();
			}
		});
		button.setFocusable(false);
		return button;
	}

	private Component buildEject() {
		JButton button = new JButton(new BAction(new ImageIcon(MediaPlayerResource.EJECT), new EjectMusicTask()));
		button.setFocusable(false);
		return button;
	}

	private Component buildRefresh() {
		JButton button = new JButton(new BAction(new ImageIcon(MediaPlayerResource.REFRESH), new RefreshMusicTask()));
		button.setFocusable(false);
		return button;
	}

	private Component buildNext() {
		JButton button = new JButton(new ImageIcon(MediaPlayerResource.NEXT));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onNextClick();
			}
		});
		button.setFocusable(false);
		return button;
	}

	protected void onNextClick() {
		new NextMusicTask().start();
	}

	protected void onStopClick() {
		new StopMusicTask().start();
	}

	protected void onPlayClick() {
		if (MainFrame.getInstance().getTable().getModel().getRowCount() != 0) {
			int row = MainFrame.getInstance().getTable().getTable().getSelectedRow();
			if (row == -1) {
				new NextMusicTask().start();
			} else {
				Music music = MainFrame.getInstance().getTable().getModel().getRow(row);
				new PlayMusicTask(music).start();
			}
		}
	}

	protected void onUsedOptionClick() {
		IObjectModel<Music> model = MainFrame.getInstance().getTable().getModel();
		model.findNext(OrderObjectModel.class).setComparator(new UsedOrderComparator());
		model.refresh();
	}

	protected void onNormalOptionClick() {
		IObjectModel<Music> model = MainFrame.getInstance().getTable().getModel();
		model.findNext(OrderObjectModel.class).setComparator(new NormalOrderComparator());
		model.refresh();
	}

	protected void onMusicOptionClick() {
		IObjectModel<Music> model = MainFrame.getInstance().getTable().getModel();
		model.findNext(OrderObjectModel.class).setComparator(new MusicOrderComparator());
		model.refresh();
	}

	private AbstractButton buildNormal() {
		AbstractButton option = new JToggleButton("Artist");
		option.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onNormalOptionClick();
			}
		});
		option.setFocusable(false);
		group.add(option);
		return option;
	}

	private AbstractButton buildUsed() {
		AbstractButton option = new JToggleButton("Top");
		option.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onUsedOptionClick();
			}
		});
		option.setSelected(true);
		option.setFocusable(false);
		group.add(option);
		return option;
	}

	private AbstractButton buildMusic() {
		AbstractButton option = new JToggleButton("Music");
		option.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onMusicOptionClick();
			}
		});
		option.setFocusable(false);
		group.add(option);
		return option;
	}

	public JLabel getMusicLabel() {
		return musicLabel;
	}

	public JScrollBar getScroll() {
		return scroll;
	}

}
