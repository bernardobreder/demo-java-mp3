package breder.music.client.logic.music;

import java.io.Serializable;

public class AbstractMusic implements Serializable {

	private String artist;

	private String name;

	private Integer duration;

	private int star = 0;

	private boolean looked = false;

	public String getFullName() {
		return String.format("%s - %s", this.artist, this.name);
	}

	public String getArtist() {
		return artist;
	}

	/**
	 * Retorna
	 * 
	 * @return looked
	 */
	public boolean isLooked() {
		return looked;
	}

	/**
	 * @param looked
	 */
	public void setLooked(boolean looked) {
		this.looked = looked;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * Retorna
	 * 
	 * @return star
	 */
	public int getStar() {
		return star;
	}

	/**
	 * @param star
	 */
	public void setStar(int star) {
		this.star = star;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AbstractMusic other = (AbstractMusic) obj;
		if (artist == null) {
			if (other.artist != null) {
				return false;
			}
		} else if (!artist.equals(other.artist)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
