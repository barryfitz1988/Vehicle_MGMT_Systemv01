package controller;


import gui.BlankTable_Frame;
import gui.CustomerTable;
import gui.Main_Menu_GUI;
import gui.VehicleTable;
import gui.Vehicle_GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

import service.Customer_Service;
import service.Vehicle_Service;
import utility.FieldValidator;
import model.Customer_Model;
import model.Customers_Vehicle_Model;
import model.Parts_Model;
import model.Vehicle_Model;


public class Vehicle_Controller {
	

	private Main_Menu_GUI maingui = new Main_Menu_GUI();
	private Vehicle_GUI vehiclegui = new Vehicle_GUI();
	private Vehicle_Model vehiclemodel = new Vehicle_Model();
	private Customer_Model customermodel = new Customer_Model();
	private Vehicle_Service vehicleservice = new Vehicle_Service();
	private Customer_Service customerservice = new Customer_Service();
	private Customers_Vehicle_Model owners_car = new Customers_Vehicle_Model();
	private ArrayList<Vehicle_Model> vehicles = new ArrayList<Vehicle_Model>();
	private ArrayList<Customer_Model> customers = new ArrayList<Customer_Model>();
	private ArrayList<Customers_Vehicle_Model> owners_carList   = new ArrayList<Customers_Vehicle_Model>();
	private VehicleTable vehicletable;
	private CustomerTable customertable;
	private BlankTable_Frame blanktable = new BlankTable_Frame();
	private BlankTable_Frame editblanktable = new BlankTable_Frame();
	
    public Vehicle_Controller(Main_Menu_GUI maingui, Vehicle_GUI vehiclegui, Vehicle_Model vehiclemodel) {
    	        this.maingui = maingui;
    	        this.vehiclegui= vehiclegui;
    	        this.vehiclemodel = vehiclemodel;
    	         
    	        
    	        this.vehiclegui.exitListener(new exitListener());   
    	        this.maingui.addvehicleListener(new Listener());
    	        this.vehiclegui.tableSelecterListener(new rowSelectedListener());
    	        
    	        
    	        this.vehiclegui.EditCustomertoVehicle(new editCustomerListener());
    			this.vehiclegui.addNewVehicleListener(new addListener());
    			this.vehiclegui.editButtonListener(new editListener());
    			this.vehiclegui.deleteListener(new deleteListener());
    			this.vehiclegui.addCustomertoVehicle(new CustomerListener());
    			this.blanktable.tableSelecterListener(new customerSelectedListener());
    			this.editblanktable.tableSelecterListener(new EditcustomerSelectedListener());
    }

	public void refreshTable(){


		owners_carList.clear();


		List<Customers_Vehicle_Model> cuscar = vehicleservice.findAllOwners();

		for (int x = 0; x < cuscar.size(); x++) {
			owners_car = cuscar.get(x);
			owners_carList.add(owners_car);

		}


		vehicletable.fireTableDataChanged();


	}
	
	
	class editListener implements ActionListener {

		private String message;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				
				
				boolean validReg = FieldValidator.checkRegistration(vehiclegui.getInfoRegTextField());
				boolean validMake = FieldValidator.checkNameField(vehiclegui.getInfoMakeTextField());
				boolean validModel = FieldValidator.checkParts(vehiclegui.getInfoModelTextField());
				boolean validChassis = FieldValidator.checkRegistration(vehiclegui.getInfoChassisTextField());
				boolean validCustomer = FieldValidator.checkCustomerField(vehiclegui.getInfoCustomerTextField());
				
				
				
				message = "";
				
				if(validReg == false ){
					message = "INVALID ENTRY IN REGISTRATION FIELD - (not empty, only letters or numbers & not more than 30 characters)\n";
					vehiclegui.getInfoRegTextField().setBackground(Color.YELLOW);
				}else
					vehiclegui.getInfoRegTextField().setBackground(Color.WHITE);
				
				if(validMake == false){
					message = message + "INVALID ENTRY IN MAKE FIELD - (not empty, only letters, not more than 30 characters)\n";
					vehiclegui.getInfoMakeTextField().setBackground(Color.YELLOW);
				}else
					vehiclegui.getInfoMakeTextField().setBackground(Color.WHITE);
				
				if(validModel == false){
					message = message + "INVALID ENTRY IN MODEL FIELD - (not empty, only digits, from 8 to 15 digits)\n";
					vehiclegui.getInfoModelTextField().setBackground(Color.YELLOW);
				}else
					vehiclegui.getInfoModelTextField().setBackground(Color.WHITE);
				
				
				if(validChassis == false){
					message = message + "INVALID ENTRY IN CHASSIS FIELD - (not empty, only letters or numbers & not more than 30 characters)\n";
					vehiclegui.getInfoChassisTextField().setBackground(Color.YELLOW);
				}else
					vehiclegui.getInfoChassisTextField().setBackground(Color.WHITE);
				
				if(validCustomer == false){
					message = message + "INVALID ENTRY IN CUSTOMER FIELD - (Please Select a customer from table)\n";
					vehiclegui.getInfoCustomerTextField().setBackground(Color.YELLOW);
				}else
					vehiclegui.getInfoCustomerTextField().setBackground(Color.WHITE);
				
			
				if(validReg == true && validMake == true && validModel == true && 
						validChassis == true  && validCustomer)
				{

				String reg = vehiclegui.getInfoRegTextField().getText();
				String make = vehiclegui.getInfoMakeTextField().getText();
				String model = vehiclegui.getInfoModelTextField().getText();
				String chassis = vehiclegui.getInfoChassisTextField().getText();
				
				double idextract = Double.parseDouble(vehiclegui.getInfoIDTextField().getText().toString());
				int id = (int) idextract;

				int rowSelected = (int) vehiclegui.getVehicleTable().getValueAt(vehiclegui.getVehicleTable().getSelectedRow(),0);
				int selectedOption = JOptionPane
						.showConfirmDialog(null,
								"Save Update to  Vehicle Information?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
	
					
					for (Customers_Vehicle_Model c : owners_carList) {
					
						boolean found = false;
						
						if (c.getVehicle().getVehicle_id() == vehicleservice.findById(id).getVehicle_id()) {
							System.out.println("Sucess");
							vehicleservice.open();
							c.getVehicle().setVehicle_make(make);
							c.getVehicle().setVehicle_reg(reg);
							c.getVehicle().setVehicle_model(model);
							c.getVehicle().setVehicle_chassis(chassis);
							c.setCustomer(owners_car.getCustomer());
							vehicleservice.update(c.getVehicle());
							vehicleservice.updateOwner(c);
							vehicleservice.close();
							found = true;
							refreshTable();
							vehiclegui.getInfoIDTextField().setText("");
							vehiclegui.getInfoRegTextField().setText("");
							vehiclegui.getInfoMakeTextField().setText("");
							vehiclegui.getInfoModelTextField().setText("");
							vehiclegui.getInfoChassisTextField().setText("");
							vehiclegui.getInfoCustomerTextField().setText("");
							
					
						}
						

						else {
							System.out.println("Fail");
							found = false;

						
					}
						
					}
					}
				}else {
			    	
			    	JOptionPane.showMessageDialog( vehiclegui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}

		}

	}
    
    
    
    
	class rowSelectedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {

			try {
				int rowSelected = (int) vehiclegui.getVehicleTable().getValueAt(vehiclegui.getVehicleTable().getSelectedRow(),0);

				for (Customers_Vehicle_Model c : owners_carList) {
					
		
					
					if (c.getVehicle().getVehicle_id() == rowSelected) {
					

					vehiclegui.getInfoRegTextField().setText(
							(c.getVehicle().getVehicle_reg()));
					vehiclegui.getInfoMakeTextField().setText(
							(c.getVehicle().getVehicle_make()));
					vehiclegui.getInfoModelTextField().setText(
							(c.getVehicle().getVehicle_model()));
					vehiclegui.getInfoChassisTextField().setText(
							(c.getVehicle().getVehicle_chassis()));
					vehiclegui.getInfoCustomerTextField().setText(c.getCustomer().getFirst_name()+" "
							+ c.getCustomer().getSurname());
				vehiclegui.getInfoIDTextField().setText(Integer.toString
							(c.getVehicle().getVehicle_id()));
					}

				}

						
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}

	} 
    
    class addListener implements ActionListener {
    	private String message;
    	
		public void actionPerformed(ActionEvent e) {

			try {

				
				
				boolean validReg = FieldValidator.checkRegistration(vehiclegui.getRegTextfield());
				boolean validMake = FieldValidator.checkNameField(vehiclegui.getMakeTextField());
				boolean validModel = FieldValidator.checkNameField(vehiclegui.getModelTextField());			
				boolean validChassis = FieldValidator.checkRegistration(vehiclegui.getChassisTextfield());
				boolean validCustomer = FieldValidator.checkCustomerField(vehiclegui.getCustomerTextField());
				
				
				
				message = "";
				
				if(validReg == false ){
					message = "INVALID ENTRY IN REGISTRATION FIELD - (not empty, only letters or numbers & not more than 30 characters)\n";
					vehiclegui.getRegTextfield().setBackground(Color.YELLOW);
				}else
					vehiclegui.getRegTextfield().setBackground(Color.WHITE);
				
				if(validMake == false){
					message = message + "INVALID ENTRY IN MAKE FIELD - (not empty, only letters, not more than 30 characters)\n";
					vehiclegui.getMakeTextField().setBackground(Color.YELLOW);
				}else
					vehiclegui.getMakeTextField().setBackground(Color.WHITE);
				
				if(validModel == false){
					message = message + "INVALID ENTRY IN MODEL FIELD - (not empty, only digits, from 8 to 15 digits)\n";
					vehiclegui.getModelTextField().setBackground(Color.YELLOW);
				}else
					vehiclegui.getModelTextField().setBackground(Color.WHITE);
				
				
				if(validChassis == false){
					message = message + "INVALID ENTRY IN CHASSIS FIELD - (not empty, only letters or numbers & not more than 30 characters)\n";
					vehiclegui.getChassisTextfield().setBackground(Color.YELLOW);
				}else
					vehiclegui.getChassisTextfield().setBackground(Color.WHITE);
				
				if(validCustomer == false){
					message = message + "INVALID ENTRY IN CUSTOMER FIELD - (Please Select a customer from table)\n";
					vehiclegui.getCustomerTextField().setBackground(Color.YELLOW);
				}else
					vehiclegui.getCustomerTextField().setBackground(Color.WHITE);
				
			
				if(validReg == true && validMake == true && validModel == true && 
						validChassis == true  && validCustomer)
				{
				
				
				vehicleservice.open();
				String reg = vehiclegui.getRegTextfield().getText();
				String make = vehiclegui.getMakeTextField().getText();
				String model = vehiclegui.getModelTextField().getText();
				String chassis = vehiclegui.getChassisTextfield().getText();

				
				

				vehiclemodel.setVehicle_reg(reg);
				vehiclemodel.setVehicle_make(make);
				vehiclemodel.setVehicle_model(model);
				vehiclemodel.setVehicle_chassis(chassis);
					owners_car.setVehicle(vehiclemodel);
				
				vehicleservice.persist(vehiclemodel);
				vehicleservice.persistOwner(owners_car);
			
				
				
				vehicleservice.close();
				refreshTable();

				vehiclegui.getRegTextfield().setText("");
				vehiclegui.getMakeTextField().setText("");
				vehiclegui.getModelTextField().setText("");
				vehiclegui.getChassisTextfield().setText("");
				vehiclegui.getCustomerTextField().setText("Click To Add Customer");
				
				}else {
			    	
			    	JOptionPane.showMessageDialog( vehiclegui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException nfe) {

				System.out.println("ERROR CANNOT BE DONE: " + nfe.getMessage());

			}

		}

	}
    
    
    
    
	class customerSelectedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
		
			try {
				int rowSelected = (int) blanktable.getBlankTable().getValueAt(blanktable.getBlankTable().getSelectedRow(),0);
			
			
				for (Customer_Model c : customers) {

					if (c.getCustomer_id() == rowSelected) {
						
						vehiclegui.getCustomerTextField().setText(
								(c.getFirst_name()+" "+ c.getSurname()));
						vehiclegui.getHiddenCustomerIDLbl().setText(String.valueOf(c.getCustomer_id()));
						owners_car.setCustomer(c);
					}
				
				}
				
				customers.clear();
				blanktable.dispose();

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}

	}
	
	
	class EditcustomerSelectedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
		
			try {
				int rowSelected = (int) editblanktable.getBlankTable().getValueAt(editblanktable.getBlankTable().getSelectedRow(),0);
			
			
				for (Customer_Model c : customers) {

					if (c.getCustomer_id() == rowSelected) {
						
						vehiclegui.getInfoCustomerTextField().setText(
								(c.getFirst_name()+" "+ c.getSurname()));
						vehiclegui.getHiddenCustomerIDLbl().setText(String.valueOf(c.getCustomer_id()));
						owners_car.setCustomer(c);
					}
				
				}
				
				customers.clear();
				editblanktable.dispose();

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}

	}
    
    
    class CustomerListener implements MouseInputListener {


		

		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {	 
				
				
  				blanktable.setVisible(true);
  				customertable = new CustomerTable(customers);
  				blanktable.getBlankTable().setModel(customertable);
  				
  				List<Customer_Model> c = customerservice.findAll();

  				for (int x = 0; x < c.size(); x++) {
  					customermodel = c.get(x);
  					customers.add(customermodel);

  				}
  			

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}



	}
class editCustomerListener implements MouseInputListener {


		

		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {	 
				
				
  				editblanktable.setVisible(true);
  				customertable = new CustomerTable(customers);
  				editblanktable.getBlankTable().setModel(customertable);
  				
  				List<Customer_Model> c = customerservice.findAll();

  				for (int x = 0; x < c.size(); x++) {
  					customermodel = c.get(x);
  					customers.add(customermodel);

  				}
  			

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}



	}
    
    
	class deleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {


				
				int id = Integer.valueOf(vehiclegui.getInfoIDTextField().getText());

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Delete Vehicle?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					vehicleservice.open();
					vehicleservice.delete(id);
					//vehicleservice.deleteOwner(id);
					vehicleservice.close();

					refreshTable();
					vehiclegui.getInfoIDTextField().setText("");
					vehiclegui.getInfoRegTextField().setText("");
					vehiclegui.getInfoMakeTextField().setText("");
					vehiclegui.getInfoModelTextField().setText("");
					vehiclegui.getInfoChassisTextField().setText("");
					vehiclegui.getInfoCustomerTextField().setText("");
					
				}

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}

		}

	}
    
    
    
	class exitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				vehiclegui.getRegTextfield().setText("");
				vehiclegui.getMakeTextField().setText("");
				vehiclegui.getModelTextField().setText("");
				vehiclegui.getChassisTextfield().setText("");
				vehiclegui.getCustomerTextField().setText("Click To Add Customer");
				vehiclegui.getInfoIDTextField().setText("");
				vehiclegui.getInfoRegTextField().setText("");
				vehiclegui.getInfoMakeTextField().setText("");
				vehiclegui.getInfoModelTextField().setText("");
				vehiclegui.getInfoChassisTextField().setText("");
				vehiclegui.getInfoCustomerTextField().setText("Click To Edit Customer");
				vehiclegui.getRegTextfield().setBackground(Color.WHITE);
				vehiclegui.getMakeTextField().setBackground(Color.WHITE);
				vehiclegui.getModelTextField().setBackground(Color.WHITE);			
				vehiclegui.getChassisTextfield().setBackground(Color.WHITE);
				vehiclegui.getCustomerTextField().setBackground(Color.WHITE);
				vehiclegui.getInfoRegTextField().setBackground(Color.WHITE);
				vehiclegui.getInfoMakeTextField().setBackground(Color.WHITE);
				vehiclegui.getInfoModelTextField().setBackground(Color.WHITE);			
				vehiclegui.getInfoChassisTextField().setBackground(Color.WHITE);
				vehiclegui.getInfoCustomerTextField().setBackground(Color.WHITE);
				owners_carList.clear();
				vehiclegui.dispose();
				maingui.setVisible(true);

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}

		}

	}
    
    class Listener implements ActionListener{
    	
    	
    	public void actionPerformed(ActionEvent e) {
   
    		
    		
    		 try {

    			 maingui.dispose();
    			vehiclegui.setVisible(true);
    			vehicletable = new VehicleTable(owners_carList);
   				vehiclegui.getVehicleTable().setModel(vehicletable);
   				
 
   				List<Customers_Vehicle_Model> cuscar = vehicleservice.findAllOwners();
   				
   				for (int x = 0; x < cuscar.size(); x++) {
   					owners_car = cuscar.get(x);
   					owners_carList.add(owners_car);
   					
   				}

    		 } catch (NumberFormatException nfe) {
  			   
                 System.out.println("Not A Number: " + nfe.getMessage());
       
              }
    	
    	
    }
    	
}	
	
	

}
