package Interface;

import java.util.List;

import model.Employee_Model;



public interface Employee_Interface {
	
	
	void persist(Employee_Model c);
	
	void update(Employee_Model c);
	
	Employee_Model findById(int id);
	
	void delete(Employee_Model c);
	
	List<Employee_Model> findAll();
	
	

}
