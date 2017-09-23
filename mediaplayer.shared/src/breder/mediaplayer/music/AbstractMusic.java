package breder.mediaplayer.music;

public class AbstractMusic {

	private String artist;

	private String name;

	private Integer duration;
	
	private long counter;

	public String getFullName() {
		return String.format("%s - %s", this.artist, this.name);
	}

	public String getArtist() {
		return artist;
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

}
