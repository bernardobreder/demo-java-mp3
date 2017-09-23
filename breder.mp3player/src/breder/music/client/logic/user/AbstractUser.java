package breder.music.client.logic.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breder.music.client.logic.conf.MusicConfigurator;
import breder.music.client.logic.music.AbstractMusic;

public class AbstractUser implements Serializable {

	private final String username;

	private final List<AbstractMusic> musics = new ArrayList<AbstractMusic>();

	public AbstractUser(String username) {
		super();
		this.username = username;
	}

	public List<AbstractMusic> getMusics() {
		return Collections.unmodifiableList(musics);
	}

	public void addMusic(AbstractMusic music) {
		music.setStar(MusicConfigurator.getInstance().getCounter(music));
		music.setLooked(MusicConfigurator.getInstance().isLooked(music));
		this.musics.add(music);
	}

	public void cleanMusics() {
		this.musics.clear();
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractUser other = (AbstractUser) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

}
