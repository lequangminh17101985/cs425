
package cs425.ebazaar.com.business.externalinterfaces;


public interface CustomerProfile {
    public String getFirstName();
    public String getLastName();
    public Integer getCustId();
    public boolean isAdmin();
    public void setFirstName(String fn);
    public void setLastName(String ln);
    public void setCustId(Integer id);
    
}
