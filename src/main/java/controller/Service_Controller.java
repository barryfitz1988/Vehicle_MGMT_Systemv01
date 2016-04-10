package controller;


import gui.Main_Menu_GUI;
import gui.ServiceTable;
import gui.Service_GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.ArrayList;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import service.Service_Service;
import utility.FieldValidator;
import model.Service_Model;

public class Service_Controller {
	

	private Main_Menu_GUI maingui = new Main_Menu_GUI();
	private Service_GUI servicegui = new Service_GUI();
	private Service_Model servicemodel = new Service_Model();
	private Service_Service serviceservice = new Service_Service();
	private ServiceTable servicetable;
	private ArrayList<Service_Model> service = new ArrayList<Service_Model>();
	
	
    public Service_Controller(Main_Menu_GUI maingui, Service_GUI servicegui, Service_Model servicemodel) {
    	        this.maingui = maingui;
    	        this.servicegui= servicegui;
    	        this.servicemodel = servicemodel;
    	         

    	    this.servicegui.exitListener(new exitListener());    
    		this.maingui.addServiceListener(new Listener());
   			this.servicegui.tableSelecterListener(new rowSelectedListener());
   			this.servicegui.addNewServiceListener(new addListener());
   			this.servicegui.editButtonListener(new editListener());
   			this.servicegui.deleteListener(new deleteListener());
   
    
    
    }
    
	class deleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {

				double idextract = Double.parseDouble(servicegui
						.getInfoServiceIDTextField().getText());
				int id = (int) idextract;

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Delete Parts?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					serviceservice.open();
					serviceservice.delete(id);
					serviceservice.close();

					servicegui.getInfoServiceIDTextField().setText("");
					servicegui.getInfoServiceNameTextField().setText("");
					servicegui.getInfoServicePriceTextField().setText("");
					servicegui.getInfoServiceTimeTextfield().setText("");
					
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
				boolean validServiceName = FieldValidator.checkServiceName(servicegui.getInfoServiceNameTextField());
				boolean validServiceTime = FieldValidator.checkForInteger(servicegui.getInfoServiceTimeTextfield());
				boolean validServicePrice = FieldValidator.checkForDouble(servicegui.getInfoServicePriceTextField());			

				
				
				
				message = "";
				
				if(validServiceName == false ){
					message = "INVALID ENTRY IN NAME FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					servicegui.getInfoServiceNameTextField().setBackground(Color.YELLOW);
				}else
					servicegui.getInfoServiceNameTextField().setBackground(Color.WHITE);
				
				if(validServiceTime == false){
					message = message + "INVALID ENTRY IN DESCRIPTION FIELD - (not empty, only digits, DO NOT EXCEED 12 HOURS)\n";
					servicegui.getInfoServiceTimeTextfield().setBackground(Color.YELLOW);
				}else
					servicegui.getInfoServiceTimeTextfield().setBackground(Color.WHITE);
				
				if(validServicePrice == false){
					message = message + "INVALID ENTRY IN PRICE FIELD - (not empty, only digits) \n";
					servicegui.getInfoServicePriceTextField().setBackground(Color.YELLOW);
				}else
					servicegui.getInfoServicePriceTextField().setBackground(Color.WHITE);
				
				

				
			
				if(validServiceName == true && validServiceTime == true && validServicePrice == true )
				{
				
				String name = servicegui.getInfoServiceNameTextField().getText();
				double timeextract = Double.parseDouble(servicegui.getInfoServiceTimeTextfield().getText());
				int time = (int) timeextract;
				double idextract = Double.parseDouble(servicegui.getInfoServiceIDTextField().getText());
				int id = (int) idextract;
				double price = Double.parseDouble(servicegui.getInfoServicePriceTextField().getText());

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Update Service Information?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					for (Service_Model c : service) {

						boolean found = false;
						if (c.getJob_id() == serviceservice.findById(id)
								.getJob_id()) {
							System.out.println("Sucess");
							serviceservice.open();
							c.setJob_id(id);
							c.setJob_name(name);
							c.setJob_price(price);
							c.setJob_time(time);

							serviceservice.update(c);
							serviceservice.close();
							found = true;

							servicegui.getInfoServiceIDTextField().setText("");
							servicegui.getInfoServiceNameTextField().setText("");
							servicegui.getInfoServicePriceTextField().setText("");
							servicegui.getInfoServiceTimeTextfield().setText("");
						}

						else {

							found = false;
						}

					}


				}
				}else {
			    	
			    	JOptionPane.showMessageDialog( servicegui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
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

				boolean validServiceName = FieldValidator.checkServiceName(servicegui.getNameTextfield());
				boolean validServiceTime = FieldValidator.checkForInteger(servicegui.getTimeTextField());
				boolean validServicePrice = FieldValidator.checkForDouble(servicegui.getPriceTextField());			

				
				
				
				message = "";
				
				if(validServiceName == false ){
					message = "INVALID ENTRY IN NAME FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					servicegui.getNameTextfield().setBackground(Color.YELLOW);
				}else
					servicegui.getNameTextfield().setBackground(Color.WHITE);
				
				if(validServiceTime == false){
					message = message + "INVALID ENTRY IN DESCRIPTION FIELD - (not empty, only digits, DO NOT EXCEED 12 HOURS)\n";
					servicegui.getTimeTextField().setBackground(Color.YELLOW);
				}else
					servicegui.getTimeTextField().setBackground(Color.WHITE);
				
				if(validServicePrice == false){
					message = message + "INVALID ENTRY IN PRICE FIELD - (not empty, only digits) \n";
					servicegui.getPriceTextField().setBackground(Color.YELLOW);
				}else
					servicegui.getPriceTextField().setBackground(Color.WHITE);
				
				

				
			
				if(validServiceName == true && validServiceTime == true && validServicePrice == true )
				{
					
				
				
				serviceservice.open();
				String name = servicegui.getNameTextfield().getText();
				double timeextract = Double.parseDouble(servicegui.getTimeTextField().getText());
				int time = (int) timeextract;
				double price = Double.parseDouble(servicegui.getPriceTextField().getText());
				


				servicemodel.setJob_name(name);
				servicemodel.setJob_price(price);
				servicemodel.setJob_time(time);
				
				

				serviceservice.persist(servicemodel);
				serviceservice.close();
				
				servicegui.getNameTextfield().setText("");
				servicegui.getPriceTextField().setText("");
				servicegui.getTimeTextField().setText("");
				
				}else {
			    	
			    	JOptionPane.showMessageDialog( servicegui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
			}

			} catch (NumberFormatException nfe) {

				System.out.println("ERROR CANNOT BE DONE: " + nfe.getMessage());

			}

		}

	}
    
    
    
	class rowSelectedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {

			try {
				int rowSelected = (int) servicegui.getServiceTable().getValueAt(servicegui.getServiceTable().getSelectedRow(),0);

				for (Service_Model c : service) {

					if (c.getJob_id() == rowSelected) {

						servicegui.getInfoServiceIDTextField().setText(
								Integer.toString(c.getJob_id()));
						servicegui.getInfoServiceNameTextField().setText(
								c.getJob_name());
						servicegui.getInfoServiceTimeTextfield().setText(
								Integer.toString(c.getJob_time()));
						servicegui.getInfoServicePriceTextField().setText(
								Double.toString(c.getJob_price()));
		

					}
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}

	}
    
    
    
    
	class exitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				servicegui.getInfoServiceIDTextField().setText("");
				servicegui.getInfoServiceNameTextField().setText("");
				servicegui.getInfoServicePriceTextField().setText("");
				servicegui.getInfoServiceTimeTextfield().setText("");
				servicegui.getNameTextfield().setText("");
				servicegui.getPriceTextField().setText("");
				servicegui.getTimeTextField().setText("");
				servicegui.getInfoServicePriceTextField().setBackground(Color.WHITE);
				servicegui.getInfoServiceNameTextField().setBackground(Color.WHITE);
				servicegui.getInfoServicePriceTextField().setBackground(Color.WHITE);
				servicegui.getNameTextfield().setBackground(Color.WHITE);
				servicegui.getPriceTextField().setBackground(Color.WHITE);
				servicegui.getTimeTextField().setBackground(Color.WHITE);
				
				service.clear();
				servicegui.dispose();
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
    			 
    			 
    			 servicegui.setVisible(true);
    			 
   				servicetable = new ServiceTable(service);
   				servicegui.getServiceTable().setModel(servicetable);

   				List<Service_Model> c = serviceservice.findAll();

   				for (int x = 0; x < c.size(); x++) {
   					servicemodel = c.get(x);
   					service.add(servicemodel);

   				}	 
     			
    		 } catch (NumberFormatException nfe) {
  			   
                 System.out.println("Not A Number: " + nfe.getMessage());
       
              }
    	
    	
    }
    	
}
}