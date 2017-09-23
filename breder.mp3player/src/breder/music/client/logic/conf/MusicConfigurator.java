package breder.music.client.logic.conf;

import breder.music.client.logic.music.AbstractMusic;

public class MusicConfigurator extends AbstractConfigurator {

	private static final MusicConfigurator instance = new MusicConfigurator();

	private MusicConfigurator() {
		super("music");
	}

	public void incCount(AbstractMusic music) {
		String value = this.get(music.getFullName());
		if (value == null) {
			value = "0";
		}
		Integer ivalue = new Integer(value);
		this.set(music.getFullName(), ((Integer) (ivalue + 1)).toString());
	}

	public static MusicConfigurator getInstance() {
		return instance;
	}

	public Integer getCounter(AbstractMusic music) {
		String value = this.get(music.getFullName());
		if (value == null) {
			value = "0;false";
		}
		String[] split = value.split(";");
		Integer ivalue;
		try {
			ivalue = new Integer(split[0]);
		} catch (NumberFormatException e) {
			ivalue = 0;
			this.incCount(music);
		}
		return ivalue;
	}

	/**
	 * Atualiza a musica
	 * 
	 * @param music
	 */
	public void set(AbstractMusic music) {
		this.set(music.getFullName(), "" + music.getStar() + ";" + music.isLooked());
	}

	/**
	 * Indica se a musica já foi tocada ou modificada
	 * 
	 * @param music
	 * @return musica ja tocada ou modificada
	 */
	public boolean isLooked(AbstractMusic music) {
		String value = this.get(music.getFullName());
		if (value == null) {
			this.set(music.getFullName(), null);
			return false;
		}
		String[] split = value.split(";");
		if (split.length <= 1) {
			return false;
		} else {
			return new Boolean(split[1]);
		}
	}

}
