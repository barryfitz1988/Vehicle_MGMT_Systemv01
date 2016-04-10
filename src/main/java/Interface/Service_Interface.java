package Interface;

import java.util.List;

import model.Service_Model;


public interface Service_Interface {
	
	
	void persist(Service_Model c);
	
	void update(Service_Model c);
	
	Service_Model findById(int id);
	
	void delete(Service_Model c);
	
	List<Service_Model> findAll();
	

}
