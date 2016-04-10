package controller;


import gui.EmployeeTable;
import gui.Employee_GUI;
import gui.Main_Menu_GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;











import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import service.Employee_Service;
import utility.FieldValidator;
import model.Customer_Model;
import model.Employee_Model;




public class Employee_Controller {
	
	private Main_Menu_GUI maingui = new Main_Menu_GUI();
	private Employee_GUI employeegui = new Employee_GUI();
	private Employee_Model employeemodel = new Employee_Model();
	private Employee_Service employeeservice = new Employee_Service();
	private EmployeeTable employeetable;
	private ArrayList<Employee_Model> employees = new ArrayList<Employee_Model>();
	
    public Employee_Controller(Main_Menu_GUI maingui, Employee_GUI employeegui, Employee_Model employeemodel) {
    	        this.maingui = maingui;
    	        this.employeegui= employeegui;
    	        this.employeemodel = employeemodel;
    	         

    	        this.employeegui.exitListener(new exitListener());    
    	        this.maingui.addEmployeeListener(new Listener());
    			this.employeegui.tableSelecterListener(new rowSelectedListener());
    			this.employeegui.addNewEmployeeListener(new addListener());
    			this.employeegui.editButtonListener(new editListener());
    			this.employeegui.deleteListener(new deleteListener());
    			
    
    
    }
	class rowSelectedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {

			try {
				int rowSelected = (int) employeegui.getEmployeeTable().getValueAt(employeegui.getEmployeeTable().getSelectedRow(),0);

				for (Employee_Model c : employees) {

					if (c.getEmployee_id() == rowSelected) {

						employeegui.getInfoIDTextField().setText(
								Integer.toString(c.getEmployee_id()));
						//employeegui.getIdTextField().setEditable(false);
						employeegui.getInfoFirstnameTextField().setText(
								c.getEmp_firstname());
						employeegui.getInfoSurnameTextField().setText(
								c.getEmp_surname());
						employeegui.getInfoPhoneTextField().setText(
								Integer.toString(c.getEmp_phone_number()));


					}
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}

	}
	
	
	class deleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {

				double idextract = Double.parseDouble(employeegui
						.getInfoIDTextField().getText());
				int id = (int) idextract;

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Delete Employee?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					employeeservice.open();
					employeeservice.delete(id);
					employeeservice.close();

					employeegui.getInfoIDTextField().setText("");
					employeegui.getInfoFirstnameTextField().setText("");
					employeegui.getInfoSurnameTextField().setText("");
					employeegui.getInfoPhoneTextField().setText("");
				
					
				}

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}

		}

	}
	
	
	class editListener implements ActionListener {
		private String message;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				
				boolean validFirstName = FieldValidator.checkNameField(employeegui.getInfoFirstnameTextField());
				boolean validSurName = FieldValidator.checkNameField(employeegui.getInfoSurnameTextField());
				boolean validPhoneNumber = FieldValidator.checkPhoneNo(employeegui.getInfoPhoneTextField());			

				
				
				
				message = "";
				
				if(validFirstName == false ){
					message = "INVALID ENTRY IN NAME FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					employeegui.getInfoFirstnameTextField().setBackground(Color.YELLOW);
				}else
					employeegui.getInfoFirstnameTextField().setBackground(Color.WHITE);
				
				if(validSurName == false){
					message = message + "INVALID ENTRY IN SURNAME FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					employeegui.getInfoSurnameTextField().setBackground(Color.YELLOW);
				}else
					employeegui.getInfoSurnameTextField().setBackground(Color.WHITE);
				
				if(validPhoneNumber == false){
					message = message + "INVALID ENTRY IN PHONE NO. FIELD - (not empty, only digits \n";
					employeegui.getInfoPhoneTextField().setBackground(Color.YELLOW);
				}else
					employeegui.getInfoPhoneTextField().setBackground(Color.WHITE);
				
				

				
			
				if(validFirstName == true && validSurName == true && validPhoneNumber == true )
				{
					

				String firstname = employeegui.getInfoFirstnameTextField().getText();
				String surname = employeegui.getInfoSurnameTextField().getText();
				double idextract = Double.parseDouble(employeegui.getInfoIDTextField().getText());
				int id = (int) idextract;
				double phoneextract = Double.parseDouble(employeegui.getInfoPhoneTextField().getText());
				int phone = (int) phoneextract;

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Update Employee Information?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					for (Employee_Model c : employees) {

						boolean found = false;
						if (c.getEmployee_id() == employeeservice.findById(id)
								.getEmployee_id()) {
							System.out.println("Sucess");
							employeeservice.open();
							c.setEmp_firstname(firstname);
							c.setEmp_surname(surname);
							c.setEmp_phone_number(phone);
			
							
							employeeservice.update(c);
							employeeservice.close();
							found = true;

							employeegui.getInfoIDTextField().setText("");
							employeegui.getInfoFirstnameTextField().setText("");
							employeegui.getInfoSurnameTextField().setText("");
							employeegui.getInfoPhoneTextField().setText("");
						}

						else {

							found = false;
						}

					}

					
				}
				}else {
			    	
			    	JOptionPane.showMessageDialog( employeegui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
				
				
			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}

		}

	}
	
	
	class addListener implements ActionListener {

		private String message;
		public void actionPerformed(ActionEvent e) {

			try {
				
				boolean validFirstName = FieldValidator.checkNameField(employeegui.getNameTextfield());
				boolean validSurName = FieldValidator.checkNameField(employeegui.getSurnameTextField());
				boolean validPhoneNumber = FieldValidator.checkPhoneNo(employeegui.getPhoneTextField());			

				
				
				
				message = "";
				
				if(validFirstName == false ){
					message = "INVALID ENTRY IN FIRST NAME FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					employeegui.getNameTextfield().setBackground(Color.YELLOW);
				}else
					employeegui.getNameTextfield().setBackground(Color.WHITE);
				
				if(validSurName == false){
					message = "INVALID ENTRY IN SURNAME FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					employeegui.getSurnameTextField().setBackground(Color.YELLOW);
				}else
					employeegui.getSurnameTextField().setBackground(Color.WHITE);
				
				if(validPhoneNumber == false){
					message = message + "INVALID ENTRY IN PHONE NO. FIELD - (not empty, only digits \n";
					employeegui.getPhoneTextField().setBackground(Color.YELLOW);
				}else
					employeegui.getPhoneTextField().setBackground(Color.WHITE);
				
				

				
			
				if(validFirstName == true && validSurName == true && validPhoneNumber == true )
				{
					

				employeeservice.open();
				String firstname = employeegui.getNameTextfield().getText();
				String surname = employeegui.getSurnameTextField().getText();
				double phoneextract = Double.parseDouble(employeegui.getPhoneTextField().getText());
				int phone = (int) phoneextract;


				employeemodel.setEmp_firstname(firstname);
				employeemodel.setEmp_surname(surname);
				employeemodel.setEmp_phone_number(phone);

				

				employeeservice.persist(employeemodel);
				employeeservice.close();
				
		
				employeegui.getNameTextfield().setText("");
				employeegui.getSurnameTextField().setText("");
				employeegui.getPhoneTextField().setText("");
	

				}else {
			    	
			    	JOptionPane.showMessageDialog( employeegui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
				
			} catch (NumberFormatException nfe) {

				System.out.println("ERROR CANNOT BE DONE: " + nfe.getMessage());

			}

		}

	}
	
	
    
	class exitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				
				employeegui.getNameTextfield().setText("");
				employeegui.getSurnameTextField().setText("");
				employeegui.getPhoneTextField().setText("");
				employeegui.getInfoIDTextField().setText("");
				employeegui.getInfoFirstnameTextField().setText("");
				employeegui.getInfoSurnameTextField().setText("");
				employeegui.getInfoPhoneTextField().setText("");
				employeegui.getNameTextfield().setBackground(Color.WHITE);
				employeegui.getSurnameTextField().setBackground(Color.WHITE);
				employeegui.getPhoneTextField().setBackground(Color.WHITE);
				employeegui.getInfoFirstnameTextField().setBackground(Color.WHITE);
				employeegui.getInfoSurnameTextField().setBackground(Color.WHITE);
				employeegui.getInfoPhoneTextField().setBackground(Color.WHITE);
				employees.clear();
				employeegui.dispose();
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
 				employeetable = new EmployeeTable(employees);
 				employeegui.getEmployeeTable().setModel(employeetable);

 				List<Employee_Model> c = employeeservice.findAll();

 				for (int x = 0; x < c.size(); x++) {
 					employeemodel = c.get(x);
 					employees.add(employeemodel);

 				}
    			 
    			 employeegui.setVisible(true);
    			 
    		 
    		 } catch (NumberFormatException nfe) {
  			   
                 System.out.println("Not A Number: " + nfe.getMessage());
       
              }
    	
    	
    }
    	
}
	

}
