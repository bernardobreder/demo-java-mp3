package breder.music.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.player.Player;

/**
 * Implementa o player
 * 
 * 
 * @author Bernardo Breder
 */
public class MusicPlayer implements IMusicPlayer {

	/** Instancia */
	private static final MusicPlayer instance = new MusicPlayer();
	/** Player */
	private Player player;
	/** Thread */
	private Thread thread;
	/** Parou */
	private boolean stoped;
	/** Porcentagem da conclusão da musica */
	private double percent;
	/** Listeners */
	private List<Runnable> listeners = new ArrayList<Runnable>();

	/**
	 * Construtor
	 */
	private MusicPlayer() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void play(final InputStream input) throws Exception {
		if (this.player != null) {
			this.player.close();
		}
		try {
			this.player = new Player(input);
			this.player.play(1);
			this.stoped = false;
			this.thread = new Thread("Player") {
				@Override
				public void run() {
					try {
						int frames = input.available();
						for (; stoped == false && input.available() > 0;) {
							player.play(1);
							double newPercent = 1 - ((double) input.available() / frames);
							if (newPercent != percent) {
								percent = newPercent;
								for (Runnable r : listeners) {
									r.run();
								}
							}
						}
					} catch (Exception e) {
					}
					thread = null;
					if (!stoped) {
						player = null;
					}
				}
			};
			this.thread.start();
		} catch (Exception e) {
			this.player = null;
			this.thread = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void play(final File file) throws FileNotFoundException, Exception {
		if (!file.exists()) {
			return;
		}
		this.play(new FileInputStream(file));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void stop() {
		if (thread != null) {
			this.stoped = true;
			this.player.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized boolean isFinished() {
		return this.thread == null && player == null;
	}

	/**
	 * Adiciona um listener
	 */
	public void addListener(Runnable r) {
		this.listeners.add(r);
	}

	/**
	 * Porcentagem da musica
	 * 
	 * @return Porcentagem da musica
	 */
	public double getPercent() {
		return percent;
	}

	/**
	 * Retorna a instancia unica
	 * 
	 * @return instancia unica
	 */
	public static MusicPlayer getInstance() {
		return instance;
	}

	public static void main(String[] args) throws FileNotFoundException, Exception {
		final FileInputStream input = new FileInputStream("/Users/bernardobreder/Music/Music/50 Cent - Bad news.mp3");
		IMusicPlayer.DEFAULT.play(new InputStream() {
			private int count = 0;

			@Override
			public int read(byte[] b) throws IOException {
				int n = input.read(b);
				count += n;
				System.out.println(count);
				return n;
			}

			@Override
			public int read(byte[] b, int off, int len) throws IOException {
				int n = input.read(b, off, len);
				count += n;
				System.out.println(count);
				return n;
			}

			@Override
			public long skip(long n) throws IOException {
				return input.skip(n);
			}

			@Override
			public int available() throws IOException {
				return input.available();
			}

			@Override
			public void close() throws IOException {
				input.close();
			}

			@Override
			public synchronized void mark(int readlimit) {
				input.mark(readlimit);
			}

			@Override
			public synchronized void reset() throws IOException {
				input.reset();
			}

			@Override
			public boolean markSupported() {
				return input.markSupported();
			}

			@Override
			public int read() throws IOException {
				count++;
				System.out.println(count);
				return input.read();
			}
		});
	}

}
