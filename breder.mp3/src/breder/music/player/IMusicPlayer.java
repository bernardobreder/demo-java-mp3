package breder.music.player;

import java.io.File;
import java.io.InputStream;

/**
 * Interface de player
 * 
 * 
 * @author Bernardo Breder
 */
public interface IMusicPlayer {

	/** Padrão */
	public static final IMusicPlayer DEFAULT = MusicPlayer.getInstance();

	/**
	 * Toca um arquivo
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void play(File file) throws Exception;

	/**
	 * Toca uma stream
	 * 
	 * @param input
	 * @throws Exception
	 */
	public void play(InputStream input) throws Exception;

	/**
	 * Pausa o player
	 */
	public void stop();

	/**
	 * Indica se acabou
	 * 
	 * @return acabou?
	 */
	public boolean isFinished();

	/**
	 * Adiciona um listener de modificação de propriedade da musica
	 * 
	 * @param r
	 */
	public void addListener(Runnable r);

	/**
	 * Porcentagem da musica
	 * 
	 * @return Porcentagem da musica
	 */
	public double getPercent();

}
