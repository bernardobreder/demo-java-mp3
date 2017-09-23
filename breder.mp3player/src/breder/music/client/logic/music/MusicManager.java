package breder.music.client.logic.music;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import breder.music.client.task.PlayMusicTask;
import breder.music.client.ui.main.MainFrame;
import breder.music.player.IMusicPlayer;
import breder.util.swing.model.IObjectModel;

public class MusicManager {

	private static final MusicManager instance = new MusicManager();

	private final IMusicPlayer player = IMusicPlayer.DEFAULT;

	private Music music;

	private final MusicThread thread;

	private List<MusicListener> listeners = new ArrayList<MusicListener>();

	private float volume;

	private List<String> names = new ArrayList<String>();

	private MusicManager() {
		this.volume = 1.0f;
		thread = new MusicThread();
		thread.start();
	}

	public Music getMusic() {
		return music;
	}

	public synchronized void setMusic(Music music) {
		this.music = music;
	}

	public synchronized int getCurrentTime() {
		return 0;
	}

	public synchronized int getLastTime() {
		return 0;
	}

	public MusicThread getThread() {
		return thread;
	}

	public void addListener(MusicListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(MusicListener listener) {
		this.listeners.remove(listener);
	}

	protected void fireMusicChange(Music music) {
		for (MusicListener listener : this.listeners) {
			listener.musicChange(music);
		}
	}

	protected void fireTimerChange(int value) {
		for (MusicListener listener : this.listeners) {
			listener.timerChange(value);
		}
	}

	public static MusicManager getInstance() {
		return instance;
	}

	public void play() throws Exception {
		player.stop();
		player.play(music.toFile());
		music.setDuration(0);
		this.fireMusicChange(music);
	}

	public void stop() throws Exception {
		player.stop();
	}

	public synchronized void setVolume(float volume) {
		this.volume = volume;
	}

	public float getVolume() {
		return volume;
	}

	public void next() {
		try {
			this.player.stop();
		} catch (Exception e) {
		}
		Music music = this.nextNumber();
		if (music != null) {
			new PlayMusicTask(music).start();
		}
	}

	public Music nextNumber() {
		IObjectModel<Music> model = MainFrame.getInstance().getTable().getModel();
		int rowCount = model.getRowCount();
		if (rowCount == 0) {
			return null;
		}
		int count = 0;
		for (int n = 0; n < rowCount; n++) {
			Music music = model.getRow(n);
			count += music.getStar();
		}
		int random = new Random(System.currentTimeMillis()).nextInt(count);
		for (int n = 0; n < rowCount; n++) {
			Music music = model.getRow(n);
			random -= music.getStar();
			if (random < 0) {
				return music;
			}
		}
		if (rowCount > 0) {
			Music music = null;
			for (;;) {
				int row = (int) (Math.random() * rowCount);
				music = model.getRow(row);
				if (!this.names.contains(music.getName())) {
					this.names.add(music.getName());
					if (this.names.size() > rowCount / 2) {
						this.names.remove(0);
					}
					break;
				}
			}
			return music;
		} else {
			return null;
		}
	}

	public void cleanCache() {
		this.names.clear();
	}

	public boolean isComplete() {
		return this.player.isFinished();
	}

}
