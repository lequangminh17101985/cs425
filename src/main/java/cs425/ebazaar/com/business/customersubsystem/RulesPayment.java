package cs425.ebazaar.com.business.customersubsystem;

import java.util.*;

import cs425.ebazaar.com.business.exceptions.BusinessException;
import cs425.ebazaar.com.business.exceptions.RuleException;
import cs425.ebazaar.com.business.externalinterfaces.DynamicBean;
import cs425.ebazaar.com.business.externalinterfaces.Address;
import cs425.ebazaar.com.business.externalinterfaces.CreditCard;
import cs425.ebazaar.com.business.externalinterfaces.Rules;
import cs425.ebazaar.com.business.externalinterfaces.RulesSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.RulesConfigKey;
import cs425.ebazaar.com.business.externalinterfaces.RulesConfigProperties;
import cs425.ebazaar.com.business.rulesbeans.PaymentBean;
import cs425.ebazaar.com.business.rulesubsystem.RulesSubsystemFacade;

public class RulesPayment implements Rules {
	
	private HashMap<String,DynamicBean> table;
	private DynamicBean bean;	
	private RulesConfigProperties config = new RulesConfigProperties();
	
	public RulesPayment(Address address, CreditCard creditCard){
		bean = new PaymentBean(address,creditCard);
	}	
	
	
	///////////////implementation of interface
	public String getModuleName(){
		return config.getProperty(RulesConfigKey.PAYMENT_MODULE.getVal());
	}
	public String getRulesFile() {
		return config.getProperty(RulesConfigKey.PAYMENT_RULES_FILE.getVal());
	}
	public void prepareData() {
		table = new HashMap<String,DynamicBean>();		
		String deftemplate = config.getProperty(RulesConfigKey.PAYMENT_DEFTEMPLATE.getVal());
		table.put(deftemplate, bean);		
	}
	public void runRules() throws BusinessException, RuleException{
    	RulesSubsystem rules = new RulesSubsystemFacade();
    	rules.runRules(this);
	}
	public HashMap<String,DynamicBean> getTable(){
		return table;
	}
	/* expect a list of address values, in order
	 * street, city, state ,zip
	 */
	public void populateEntities(List<String> updates){
		//do nothing
		
	}
	
	public List<Object> getUpdates() {
		//do nothing
		return null;
	}
	

}
