package breder.music;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import breder.util.deploy.Deploy;
import breder.util.io.HomeFile;
import breder.util.swing.StandardDialogs;

public class MusicPlayerDeploy extends Deploy {

	private static final String WEBAPPS_PUB = "../webapps/pub/";

	private String mainClass = "breder.music.Main";

	private File jarFile = new File(super.outDir, "music.jar");

	private File exeFile = new File(super.outDir, "music.exe");

	private File appFile = new File(super.outDir, "Music.app");

	private File appZipFile = new File(super.outDir, "music.app.zip");

	private File exeZipFile = new File(super.outDir, "music.exe.zip");

	protected File icnsfile = new File("src/breder/music/client/resource/trayicon.icns");

	public MusicPlayerDeploy() throws IOException {
		try {
			this.buildJar(jarFile, mainClass, new File("../breder.mp3player/bin"), new File("../breder.mp3player/lib/breder_mp3.jar"), new File("../breder.mp3player/lib/breder_util.jar"));
			this.buildApp(jarFile, mainClass, this.icnsfile, this.appFile);
			this.buildZip(appZipFile, this.appFile);
			if (StandardDialogs.showYesNoDialog(null, "Gerando", "Está sendo gerado os arquivos, deseja continuar ?") == JOptionPane.YES_OPTION) {
				this.buildExe(jarFile, iconfile, false, new File(new HomeFile(), "breder/launch4j/launch4j"), this.exeFile);
				this.buildZip(exeZipFile, this.exeFile);
				this.publish(WEBAPPS_PUB + jarFile.getName(), jarFile);
				this.publish(WEBAPPS_PUB + appZipFile.getName(), appZipFile);
				this.publish(WEBAPPS_PUB + exeZipFile.getName(), exeZipFile);
			}
		} finally {
			this.finish();
		}
	}

	public static void main(String[] args) throws IOException {
		new MusicPlayerDeploy();
	}

}
