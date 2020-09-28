package cs425.ebazaar.com.presentation.data;

import cs425.ebazaar.com.presentation.gui.LoginWindow;
import cs425.ebazaar.com.business.usecasecontrol.LoginControl;
import cs425.ebazaar.com.business.Login;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.exceptions.UserException;

public class LoginData {
	LoginControl usecaseControl = new LoginControl() ;
	public int authenticate(Login login) throws UserException, BackendException {   	
    	try {
    		int authorizationLevel = usecaseControl.authenticate(login);
    		return authorizationLevel;
    	} catch(UserException e) {
    		throw(e);
    	} catch(BackendException e) {
    		throw(e);
    	}	
	}
	public Login extractLogin(LoginWindow loginWindow) {
		Integer id = Integer.parseInt(loginWindow.getId());
    	String pwd = loginWindow.getPassword();
    	Login login = new Login(id, pwd);
    	return login;
	}
    public void loadCustomer(Login login, int authorizationLevel) throws BackendException {
        usecaseControl.prepareAndStoreCustomerObject(login, authorizationLevel); 
    }
}
