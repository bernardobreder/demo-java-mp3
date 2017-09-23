package breder.music.client.ui.main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import breder.music.client.task.FinishProgramTask;

public class MainWindowListener implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		new FinishProgramTask().start();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		MainFrame.getInstance().validate();
		MainFrame.getInstance().repaint();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		MainFrame.getInstance().setVisible(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

}
