package breder.music.client.resource;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import breder.util.resource.Resource;

public class MediaPlayerResource {

	public static final BufferedImage TRAYICON = getImage("trayicon.png");

	public static final BufferedImage STAR0 = getImage("star0.png");

	public static final BufferedImage STAR1 = getImage("star1.png");

	public static final BufferedImage STAR2 = getImage("star2.png");

	public static final BufferedImage ARTISH = getImage("artist.png");

	public static final BufferedImage EJECT = getImage("eject.png");

	public static final BufferedImage MUSIC = getImage("music.png");

	public static final BufferedImage NEXT = getImage("next.gif");

	public static final BufferedImage REFRESH = getImage("refresh.png");

	public static final BufferedImage STOP = getImage("stop.png");

	public static final BufferedImage EXIT = getImage("exit.png");

	public static final BufferedImage DELETE = getImage("delete.gif");

	public static final BufferedImage RENAME = getImage("rename.gif");

	/**
	 * Retorna a imagem
	 * 
	 * @param name
	 * @return imagem
	 */
	private static BufferedImage getImage(String name) {
		try {
			return ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("breder/music/client/resource/" + name));
		} catch (Exception e) {
			return new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		}
	}

}
