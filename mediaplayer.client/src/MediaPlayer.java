import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import breder.mediaplayer.client.resource.MediaPlayerResource;
import breder.mediaplayer.client.service.IServiceLocator;
import breder.mediaplayer.client.task.FinishProgramTask;
import breder.mediaplayer.client.task.InitializeMediaPlayer;
import breder.mediaplayer.client.task.NextMusicTask;
import breder.mediaplayer.client.task.StopMusicTask;
import breder.mediaplayer.client.ui.main.MainFrame;
import breder.mediaplayer.user.User;
import breder.webservice.IServerService;

public class MediaPlayer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (false) {
			{
				IServerService.DEFAULT.getConfig().setTomcatUrl(
						"http://localhost:8080");
				// IServerService.DEFAULT.getConfig().setTomcatUrl(
				// "http://www.breder.org");
				IServerService.DEFAULT.getConfig().setServletUrl(
						"mediaplayer/service");
				IServerService.DEFAULT.getConfig().setSessionTimeout(10 * 1000);
			}
			{
				try {
					IServerService.DEFAULT.init(false);
					User user = IServiceLocator.user.login("bbreder", "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			if (SystemTray.isSupported()) {
				PopupMenu menu = new PopupMenu();
				{
					MenuItem item = new MenuItem("Stop");
					item.addActionListener(new StopMusicTask());
					menu.add(item);
				}
				{
					MenuItem item = new MenuItem("Next");
					item.addActionListener(new NextMusicTask());
					menu.add(item);
				}
				menu.addSeparator();
				{
					MenuItem item = new MenuItem("Quit");
					item.addActionListener(new FinishProgramTask());
					menu.add(item);
				}
				TrayIcon trayIcon = new TrayIcon(MediaPlayerResource.ICON,
						"Media Player", menu);
				trayIcon.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						MainFrame.getInstance().setVisible(true);
						MainFrame.getInstance().requestFocusInWindow();
					}
				});
				SystemTray.getSystemTray().add(trayIcon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		new InitializeMediaPlayer().start();
	}
}
