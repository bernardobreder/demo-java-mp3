package breder.mediaplayer.service.server;

import java.rmi.RemoteException;

import breder.mediaplayer.user.User;
import breder.webservice.IService;

public interface IUserService extends IService {

	public User login(String username, String password) throws RemoteException;

	public void logout() throws RemoteException;

}
