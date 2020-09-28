package cs425.ebazaar.com.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs425.ebazaar.com.business.exceptions.BusinessException;
import cs425.ebazaar.com.business.exceptions.RuleException;
import cs425.ebazaar.com.business.externalinterfaces.DynamicBean;
import cs425.ebazaar.com.business.externalinterfaces.Rules;
import cs425.ebazaar.com.business.externalinterfaces.RulesSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.RulesConfigKey;
import cs425.ebazaar.com.business.externalinterfaces.RulesConfigProperties;
import cs425.ebazaar.com.business.rulesbeans.QuantityBean;
import cs425.ebazaar.com.business.rulesubsystem.RulesSubsystemFacade;

public class RulesQuantity implements Rules {

	private HashMap<String,DynamicBean> table;
	private DynamicBean bean;
	private RulesConfigProperties config = new RulesConfigProperties();
	
	public RulesQuantity(int quantityAvail, String quantityRequested){
		bean = new QuantityBean(quantityRequested, quantityAvail);
	}
	public String getModuleName() {
		return config.getProperty(RulesConfigKey.QUANTITY_MODULE.getVal());
	}

	public String getRulesFile() {
		return config.getProperty(RulesConfigKey.QUANTITY_RULES_FILE.getVal());
	}
	public void prepareData() {
		table = new HashMap<String,DynamicBean>();		
		String deftemplate = config.getProperty(RulesConfigKey.QUANTITY_DEFTEMPLATE.getVal());
		table.put(deftemplate, bean);
	}
	public void runRules() throws BusinessException, RuleException {
    	RulesSubsystem rules = new RulesSubsystemFacade();
    	rules.runRules(this);
	}
	public HashMap<String,DynamicBean> getTable(){
		return table;
	}

	public List<Object> getUpdates() {
		// nothing to do
		return new ArrayList<Object>();
	}

	public void populateEntities(List<String> updates) {
		// nothing to do
		
	}




}
