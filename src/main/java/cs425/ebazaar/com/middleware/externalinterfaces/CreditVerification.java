package cs425.ebazaar.com.middleware.externalinterfaces;

import cs425.ebazaar.com.middleware.exceptions.MiddlewareException;
import cs425.ebazaar.com.business.externalinterfaces.Address;
import cs425.ebazaar.com.business.externalinterfaces.CreditCard;
import cs425.ebazaar.com.business.externalinterfaces.CustomerProfile;

public interface CreditVerification {
	public void checkCreditCard(CustomerProfile custProfile, Address billingAddress,
                                CreditCard creditCard, double amount) throws MiddlewareException;
}
