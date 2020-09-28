/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs425.ebazaar.com.business.externalinterfaces;

import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.middleware.exceptions.DatabaseException;
import cs425.ebazaar.com.middleware.externalinterfaces.DbClass;

public interface DbClassShoppingCartForTest extends DbClass{
    public ShoppingCart retrieveSavedCart(CustomerProfile custProfile) throws BackendException, DatabaseException;
}
