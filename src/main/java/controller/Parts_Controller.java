package controller;



import gui.Main_Menu_GUI;
import gui.PartsTable;
import gui.Parts_GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import service.Parts_Service;
import utility.FieldValidator;
import model.Parts_Model;





public class Parts_Controller{
	

	private Main_Menu_GUI maingui = new Main_Menu_GUI();
	private Parts_GUI partsgui = new Parts_GUI();
	private Parts_Model partsmodel = new Parts_Model();
	private Parts_Service partsservice = new Parts_Service();
	private PartsTable partstable;
	private ArrayList<Parts_Model> parts = new ArrayList<Parts_Model>();
	
	
    public Parts_Controller(Main_Menu_GUI maingui, Parts_GUI partsgui, Parts_Model partsmodel) {
    	        this.maingui = maingui;
    	        this.partsgui= partsgui;
    	        this.partsmodel = partsmodel;
    	         
    	        this.partsgui.exitListener(new exitListener());
    			this.partsgui.tableSelecterListener(new rowSelectedListener());
    			this.partsgui.addNewPartsListener(new addListener());
    			this.partsgui.editButtonListener(new editListener());
    			this.partsgui.deleteListener(new deleteListener());
    	        this.maingui.addpartsListener(new Listener());
    		    
    
    
    }
    
	class deleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {

				double idextract = Double.parseDouble(partsgui
						.getPartInfoIDTextField().getText());
				int id = (int) idextract;

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Delete Parts?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					partsservice.open();
					partsservice.delete(id);
					partsservice.close();

					partsgui.getPartInfoIDTextField().setText("");
					partsgui.getInfoPartPriceTextField().setText("");
					partsgui.getInfoPartNameTextField().setText("");
					partsgui.getInfoPartDescTextField().setText("");
					
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

				boolean validPartName = FieldValidator.checkNameField(partsgui.getInfoPartNameTextField());
				boolean validPartDesc = FieldValidator.checkParts(partsgui.getInfoPartDescTextField());
				boolean validPartPrice = FieldValidator.checkForDouble(partsgui.getInfoPartPriceTextField());			

				
				
				
				message = "";
				
				if(validPartName == false ){
					message = "INVALID ENTRY IN NAME FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					partsgui.getInfoPartNameTextField().setBackground(Color.YELLOW);
				}else
					partsgui.getInfoPartNameTextField().setBackground(Color.WHITE);
				
				if(validPartDesc == false){
					message = message + "INVALID ENTRY IN DESCRIPTION FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					partsgui.getInfoPartDescTextField().setBackground(Color.YELLOW);
				}else
					partsgui.getInfoPartDescTextField().setBackground(Color.WHITE);
				
				if(validPartPrice == false){
					message = message + "INVALID ENTRY IN PRICE FIELD - (not empty, only digits \n";
					partsgui.getInfoPartPriceTextField().setBackground(Color.YELLOW);
				}else
					partsgui.getInfoPartPriceTextField().setBackground(Color.WHITE);
				
				

				
			
				if(validPartName == true && validPartDesc == true && validPartPrice == true )
				{
					
				
				String name = partsgui.getInfoPartNameTextField().getText();
				String descritption = partsgui.getInfoPartDescTextField().getText();
				double idextract = Double.parseDouble(partsgui.getPartInfoIDTextField().getText());
				int id = (int) idextract;
				double price = Double.parseDouble(partsgui.getInfoPartPriceTextField().getText());

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Update Part Information?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					for (Parts_Model c : parts) {

						boolean found = false;
						if (c.getPart_id() == partsservice.findById(id)
								.getPart_id()) {
							System.out.println("Sucess");
							partsservice.open();
							c.setPart_name(name);
							c.setPart_description(descritption);
							c.setPart_price(price);
							
							partsservice.update(c);
							partsservice.close();
							found = true;

							partsgui.getPartInfoIDTextField().setText("");
							partsgui.getInfoPartPriceTextField().setText("");
							partsgui.getInfoPartNameTextField().setText("");
							partsgui.getInfoPartDescTextField().setText("");
						}

						else {

							found = false;
						}

					}

				}
				}else {
			    	
			    	JOptionPane.showMessageDialog( partsgui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
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

				boolean validPartName = FieldValidator.checkNameField(partsgui.getNameTextfield());
				boolean validPartDesc = FieldValidator.checkParts(partsgui.getDescriptionTextField());
				boolean validPartPrice = FieldValidator.checkForDouble(partsgui.getPriceTextField());			

				
				
				
				message = "";
				
				if(validPartName == false ){
					message = "INVALID ENTRY IN NAME FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					partsgui.getNameTextfield().setBackground(Color.YELLOW);
				}else
					partsgui.getNameTextfield().setBackground(Color.WHITE);
				
				if(validPartDesc == false){
					message = message + "INVALID ENTRY IN DESCRIPTION FIELD - (not empty,  not less than 5 characters or more than 30 characters)\n";
					partsgui.getDescriptionTextField().setBackground(Color.YELLOW);
				}else
					partsgui.getDescriptionTextField().setBackground(Color.WHITE);
				
				if(validPartPrice == false){
					message = message + "INVALID ENTRY IN PRICE FIELD - (not empty, only digits \n";
					partsgui.getPriceTextField().setBackground(Color.YELLOW);
				}else
					partsgui.getPriceTextField().setBackground(Color.WHITE);
				
				

				
			
				if(validPartName == true && validPartDesc == true && validPartPrice == true )
				{
				
				
				partsservice.open();
				String name = partsgui.getNameTextfield().getText();
				String descritption = partsgui.getdescriptionTextField().getText();
				double price = Double.parseDouble(partsgui.getPriceTextField().getText());
				

				partsmodel.setPart_name(name);
				partsmodel.setPart_description(descritption);
				partsmodel.setPart_price(price);
				
				

				partsservice.persist(partsmodel);
				partsservice.close();
				
				partsgui.getNameTextfield().setText("");
				partsgui.getPriceTextField().setText("");
				partsgui.getdescriptionTextField().setText("");
				
				}else {
			    	
			    	JOptionPane.showMessageDialog( partsgui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
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
				int rowSelected = (int) partsgui.getPartsTable().getValueAt(partsgui.getPartsTable().getSelectedRow(),0);

				for (Parts_Model c : parts) {

					if (c.getPart_id() == rowSelected) {

	
						partsgui.getPartInfoIDTextField().setText(
								String.valueOf(c.getPart_id()));
						partsgui.getInfoPartNameTextField().setText(
								c.getPart_name());
						partsgui.getInfoPartDescTextField().setText(
								c.getPart_description());
						partsgui.getInfoPartPriceTextField().setText(
								Double.toString(c.getPart_price()));
		

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
				
				partsgui.getNameTextfield().setText("");
				partsgui.getPriceTextField().setText("");
				partsgui.getDescriptionTextField().setText("");
				partsgui.getPartInfoIDTextField().setText("");
				partsgui.getInfoPartPriceTextField().setText("");
				partsgui.getInfoPartNameTextField().setText("");
				partsgui.getInfoPartDescTextField().setText("");
				partsgui.getPriceTextField().setBackground(Color.WHITE);
				partsgui.getNameTextfield().setBackground(Color.WHITE);
				partsgui.getDescriptionTextField().setBackground(Color.WHITE);
				partsgui.getInfoPartPriceTextField().setBackground(Color.WHITE);
				partsgui.getInfoPartNameTextField().setBackground(Color.WHITE);
				partsgui.getInfoPartDescTextField().setBackground(Color.WHITE);
				parts.clear();
				partsgui.dispose();
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
    			 
  				partsgui.setVisible(true);
  				partstable = new PartsTable(parts);
  				partsgui.getPartsTable().setModel(partstable);

  				List<Parts_Model> c = partsservice.findAll();

  				for (int x = 0; x < c.size(); x++) {
  					partsmodel = c.get(x);
  					parts.add(partsmodel);

  				}	 
    			
    			 
    		 
    		 } catch (NumberFormatException nfe) {
  			   
                 System.out.println("Not A Number: " + nfe.getMessage());
       
              }
    	
    	
    }
    	
}
}