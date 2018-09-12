package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface BeansModel {
	
public AbstractBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(AbstractBean product) throws SQLException;
	
	public boolean doDelete(int code) throws SQLException;

}
