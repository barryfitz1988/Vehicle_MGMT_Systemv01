package Interface;

import java.util.List;

import model.Sales_Model;



public interface Sales_Interface {
	
	void persist(Sales_Model c);
	
	void update(Sales_Model c);
	
	Sales_Model findById(int id);
	
	void delete(Sales_Model c);
	
	List<Sales_Model> findAll();
	
	
	

}
