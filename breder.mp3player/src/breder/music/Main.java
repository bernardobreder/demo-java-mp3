package breder.music;

import breder.music.client.task.InitializeMediaPlayer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new InitializeMediaPlayer(args).start();
	}

}
