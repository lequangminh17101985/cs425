package cs425.ebazaar.com.middleware.creditverifcation;

import cs425.ebazaar.com.middleware.exceptions.MiddlewareException;
import cs425.ebazaar.com.middleware.externalinterfaces.CreditVerification;
import publicview.IVerificationSystem;
import publicview.VerificationManager;
import cs425.ebazaar.com.business.externalinterfaces.Address;
import cs425.ebazaar.com.business.externalinterfaces.CreditCard;
import cs425.ebazaar.com.business.externalinterfaces.CustomerProfile;

public class CreditVerificationFacade implements CreditVerification {

	/**
	 * Use of "amount" here is a violation of encapsulation. Should use a
	 * command object to encapsulate all the data.
	 * @param custProfile
	 * @param billingAddress
	 * @param creditCard
	 * @param amount
	 */
	@Override
	public void checkCreditCard(CustomerProfile custProfile,Address billingAddress, CreditCard creditCard, double amount) throws MiddlewareException {
		
		IVerificationSystem verifSystem = VerificationManager.clientInterface();
		CreditVerifMediator mediator = new CreditVerifMediator();
		mediator.processCreditRequest(verifSystem, custProfile, billingAddress, creditCard, amount);

	}

}
