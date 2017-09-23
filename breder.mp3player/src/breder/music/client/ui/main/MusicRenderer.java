package breder.music.client.ui.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import breder.music.client.logic.music.AbstractMusic;
import breder.music.client.logic.music.Music;
import breder.music.client.logic.music.MusicManager;
import breder.music.client.resource.MediaPlayerResource;
import breder.util.swing.BImage;

public class MusicRenderer extends DefaultTableCellRenderer {

	private JPanel starPanel;

	private BImage startImage1;

	private BImage startImage2;

	private BImage startImage3;

	private BImage startImage4;

	private BImage startImage5;

	public MusicRenderer() {
		this.starPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.starPanel.add(this.startImage1 = new BImage(MediaPlayerResource.STAR0));
		this.starPanel.add(this.startImage2 = new BImage(MediaPlayerResource.STAR0));
		this.starPanel.add(this.startImage3 = new BImage(MediaPlayerResource.STAR0));
		this.starPanel.add(this.startImage4 = new BImage(MediaPlayerResource.STAR0));
		this.starPanel.add(this.startImage5 = new BImage(MediaPlayerResource.STAR0));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		AbstractMusic music = MainFrame.getInstance().getTable().getModel().getRow(row);
		if (column == 2) {
			this.starPanel.setOpaque(isSelected);
			this.starPanel.setBackground(c.getBackground());
			BufferedImage starEmpty = MediaPlayerResource.STAR0;
			BufferedImage starFull = MediaPlayerResource.STAR2;
			this.startImage1.setImage(starEmpty);
			this.startImage2.setImage(starEmpty);
			this.startImage3.setImage(starEmpty);
			this.startImage4.setImage(starEmpty);
			this.startImage5.setImage(starEmpty);
			switch (music.getStar()) {
			case 5: {
				this.startImage5.setImage(starFull);
			}
			case 4: {
				this.startImage4.setImage(starFull);
			}
			case 3: {
				this.startImage3.setImage(starFull);
			}
			case 2: {
				this.startImage2.setImage(starFull);
			}
			case 1: {
				this.startImage1.setImage(starFull);
			}
			}
			return this.starPanel;
		} else {
			JLabel label = (JLabel) c;
			if (!music.isLooked()) {
				c.setFont(c.getFont().deriveFont(Font.BOLD));
			}
			label.setOpaque(isSelected);
			{
				Music selMusic = MusicManager.getInstance().getMusic();
				if (selMusic != null && selMusic.equals(music)) {
					label.setForeground(Color.BLUE);
				} else {
					label.setForeground(Color.BLACK);
				}
			}
			return c;
			// label.setText(value.toString());
			// this.label.setBackground(ColorUtil.light(c.getBackground(),
			// 0.5f));

			// return label;
		}
	}
}
