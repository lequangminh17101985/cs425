package cs425.ebazaar.com.business.externalinterfaces;

import java.util.List;

public interface CatalogTypes {
    public String getCatalogName(Integer id);
    public Integer getCatalogId(String name);
    public void addCatalog(Integer id, String name);
}
