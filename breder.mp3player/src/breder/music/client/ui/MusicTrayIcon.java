package breder.music.client.ui;

import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import breder.music.client.resource.MediaPlayerResource;
import breder.music.client.task.NextMusicTask;
import breder.music.client.task.StopMusicTask;
import breder.music.client.ui.main.MainFrame;
import breder.util.swing.BFrame;
import breder.util.trayicon.ITrayIcon;

public class MusicTrayIcon implements ITrayIcon {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().setExtendedState(Frame.NORMAL);
		MainFrame.getInstance().setVisible(true);
		MainFrame.getInstance().requestFocusInWindow();
		MainFrame.getInstance().repaint();
		MainFrame.getInstance().invalidate();
		MainFrame.getInstance().validate();
	}

	@Override
	public MenuItem[] buildMenu() {
		List<MenuItem> list = new ArrayList<MenuItem>();
		{
			MenuItem item = new MenuItem("Stop");
			item.addActionListener(new StopMusicTask());
			list.add(item);
		}
		{
			MenuItem item = new MenuItem("Next");
			item.addActionListener(new NextMusicTask());
			list.add(item);
		}
		return list.toArray(new MenuItem[0]);
	}

	@Override
	public String getName() {
		return "Media Player";
	}

	@Override
	public Image getIcon() {
		return MediaPlayerResource.TRAYICON;
	}

	@Override
	public BFrame getMainFrame() {
		return MainFrame.getInstance();
	}

}
