package breder.music.client.logic.music;

import java.io.File;

public class Mp3Music extends Music {

	private final transient File file;

	public Mp3Music(File file) {
		super();
		this.file = file;
		this.init();
	}

	private void init() {
		String name = file.getName();
		int index = name.indexOf('-');
		int dotIndex = name.lastIndexOf('.');
		if (index == -1) {
			this.setArtist("");
			this.setName(name.substring(0, dotIndex));
		} else {
			this.setArtist(name.substring(0, index).trim());
			this.setName(name.substring(index + 1, dotIndex).trim());
		}
	}

	@Override
	public File toFile() {
		return this.file;
	}

}
