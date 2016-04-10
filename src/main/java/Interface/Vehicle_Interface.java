package Interface;

import java.util.List;

import model.Customers_Vehicle_Model;
import model.Vehicle_Model;


public interface Vehicle_Interface {
	
	
	
	void persist(Vehicle_Model c);
	
	void persistOwner(Customers_Vehicle_Model c);
	
	void update(Vehicle_Model c);
	
	Vehicle_Model findById(int id);
	
	Customers_Vehicle_Model findByRegistration(String reg);
	
	Customers_Vehicle_Model findByOwnersId(int id);
	
	void delete(Vehicle_Model c);
	
	
	List<Vehicle_Model> findAll();
	
	List<Customers_Vehicle_Model> findAllOwners();
	

}
