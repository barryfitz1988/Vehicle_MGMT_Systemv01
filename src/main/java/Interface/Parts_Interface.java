package Interface;

import java.util.List;

import model.Parts_Model;



public interface Parts_Interface {
	
	
	
	
	
	void persist(Parts_Model c);
	
	void update(Parts_Model c);
	
	Parts_Model findById(int id);
	
	void delete(Parts_Model c);
	
	List<Parts_Model> findAll();
	

}
