package breder.music.client.ui.main;

import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import breder.music.client.logic.music.MusicManager;
import breder.music.client.resource.MediaPlayerResource;
import breder.music.client.task.DeleteMusicTask;
import breder.music.client.task.FinishProgramTask;
import breder.music.client.task.MusicSaveTask;
import breder.music.client.task.MusicStarDecTask;
import breder.music.client.task.MusicStarIncTask;
import breder.music.client.task.NextMusicTask;
import breder.music.client.task.RenameMusicTask;
import breder.music.client.task.StopMusicTask;
import breder.music.player.IMusicPlayer;
import breder.util.so.SoUtil;
import breder.util.swing.BAction;
import breder.util.swing.BFrame;
import breder.util.swing.BMenuItem;
import breder.util.swing.GBC;

public class MainFrame extends BFrame {

	private static final MainFrame instance = new MainFrame();

	private MusicTable table;

	private MusicPanel panel;

	private MainFrame() {
		super(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(MediaPlayerResource.TRAYICON);
		this.setTitle("Breder - Media Player");
		this.buildMenu();
		this.build();
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setSize(768, 1024);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				IMusicPlayer.DEFAULT.stop();
				new MusicSaveTask().start();
			}
		});
		this.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == 1) {
					MainFrame.this.setExtendedState(Frame.ICONIFIED);
					MainFrame.this.setVisible(false);
				} else {
					MainFrame.this.setExtendedState(Frame.NORMAL);
					MainFrame.this.setVisible(true);
					MainFrame.this.validateTree();
				}
			}
		});
	}

	private void buildMenu() {
		JMenuBar bar = new JMenuBar();
		bar.add(this.buildMusicMenu());
		bar.add(this.buildEditMenu());
		this.setJMenuBar(bar);
	}

	private JMenu buildMusicMenu() {
		JMenu menu = new JMenu("Music");
		menu.add(new BMenuItem(new BAction(new ImageIcon(MediaPlayerResource.NEXT), "Next", new NextMusicTask()), KeyStroke.getKeyStroke(KeyEvent.VK_N, SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem(new BAction(new ImageIcon(MediaPlayerResource.STOP), "Stop", new StopMusicTask()), KeyStroke.getKeyStroke(KeyEvent.VK_S, SoUtil.CTRL_MASK)));
		menu.add(new JSeparator());
		menu.add(new BMenuItem(new BAction(new ImageIcon(MediaPlayerResource.STAR2), "Star Inc", new MusicStarIncTask()), KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem(new BAction(new ImageIcon(MediaPlayerResource.STAR0), "Star Dec", new MusicStarDecTask()), KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, SoUtil.CTRL_MASK)));
		menu.add(new JSeparator());
		menu.add(new BMenuItem(new BAction(new ImageIcon(MediaPlayerResource.EXIT), "Quit", new FinishProgramTask()), KeyStroke.getKeyStroke(KeyEvent.VK_W, SoUtil.CTRL_MASK)));
		return menu;
	}

	private JMenu buildEditMenu() {
		JMenu menu = new JMenu("Edit");
		menu.add(new BMenuItem(new BAction(new ImageIcon(MediaPlayerResource.RENAME), "Rename", new RenameMusicTask()), KeyStroke.getKeyStroke(KeyEvent.VK_R, SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem(new BAction(new ImageIcon(MediaPlayerResource.DELETE), "Delete Forever", new DeleteMusicTask()), KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, SoUtil.CTRL_MASK)));
		return menu;
	}

	private void build() {
		this.setLayout(new GridBagLayout());
		this.add(this.buildPanel(), new GBC(0, 0).horizontal());
		this.add(this.buildTable(), new GBC(0, 1).both());
		MusicManager.getInstance().addListener(new MainMusicListener());
	}

	private MusicTable buildTable() {
		table = new MusicTable();
		return table;
	}

	private MusicPanel buildPanel() {
		panel = new MusicPanel();
		return panel;
	}

	public MusicTable getTable() {
		return table;
	}

	public MusicPanel getPanel() {
		return panel;
	}

	public static MainFrame getInstance() {
		return instance;
	}

}
