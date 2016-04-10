package Interface;

import java.util.List;



import model.Invoice_Model;
import model.Items_Model;

public interface Invoice_Interface {
	
	
	
	void persist(Invoice_Model c);
	
	void persistItems(Items_Model c);
	
	void update(Invoice_Model c);
	
	Invoice_Model findById(int id);
	
	void delete(Invoice_Model c);
	
	List<Invoice_Model> findAll();
	
	List<Items_Model> findAllItems();
	
	

}
