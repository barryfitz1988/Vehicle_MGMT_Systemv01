package Interface;

import java.util.List;

import model.Customer_Model;

public interface Customer_Interface {
	
	
		
		void persist(Customer_Model c);
		
		void update(Customer_Model c);
		
		Customer_Model findById(int id);
		
		void delete(Customer_Model c);
		
		List<Customer_Model> findAll();
		


}
