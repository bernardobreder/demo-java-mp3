package breder.music.client.task;

import breder.music.client.logic.conf.MusicConfigurator;
import breder.music.client.logic.conf.UserConfigurator;
import breder.util.task.RemoteTask;

/**
 * Decrementa uma estrela
 * 
 * 
 * @author Bernardo Breder
 */
public class MusicSaveTask extends RemoteTask {

	public MusicSaveTask() {
		super(null);
	}

	@Override
	public void perform() throws Throwable {
		synchronized (MusicSaveTask.class) {
			MusicConfigurator.getInstance().save();
			UserConfigurator.getInstance().save();
		}
	}

	@Override
	public void updateUI() {
	}

}
