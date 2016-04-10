package Interface;

import java.util.List;

import model.Appointment_Model;
import model.Appointment_Parts_Model;



public interface Appointment_Interface {
	
	void persist(Appointment_Model c);
	
	void persistPart(Appointment_Parts_Model c);
	
	void update(Appointment_Model c);
	
	Appointment_Model findById(int id);
	
	Appointment_Parts_Model findPartByname(int part);
	
	void delete(Appointment_Model c);
	
	void deletePart(Appointment_Parts_Model c);
	
	List<Appointment_Model> findAll();
	
	List<Appointment_Parts_Model> findAllParts();

}
