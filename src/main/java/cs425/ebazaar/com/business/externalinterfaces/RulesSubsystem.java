package cs425.ebazaar.com.business.externalinterfaces;

import cs425.ebazaar.com.business.exceptions.BusinessException;
import cs425.ebazaar.com.business.exceptions.RuleException;

public interface RulesSubsystem {
	public void runRules(Rules rulesIface) throws BusinessException,RuleException;
	
}
