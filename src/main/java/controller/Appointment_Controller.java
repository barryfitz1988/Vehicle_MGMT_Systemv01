package controller;


import gui.AddAppointment_GUI;
import gui.AppointmentTable;
import gui.Appointment_Desicion_GUI;
import gui.Appointment_GUI;
import gui.Appointment_Parts_Table;
import gui.BlankTable_Frame;
import gui.Current_Appointment_GUI;
import gui.CustomerTable;
import gui.Invoice_GUI;
import gui.Main_Menu_GUI;
import gui.PartsTable;
import gui.VehicleTable;

import java.awt.Color;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.dynamicreports.report.exception.DRException;

import org.apache.activemq.filter.function.regexMatchFunction;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;

import service.Appointment_Service;
import service.Customer_Service;
import service.Employee_Service;
import service.Invoice_Service;
import service.Parts_Service;
import service.Service_Service;
import service.Vehicle_Service;
import utility.Header_Render;
import utility.InvoiceTemplate;
import model.Appointment_Model;
import model.Appointment_Parts_Model;
import model.Customer_Model;
import model.Customers_Vehicle_Model;
import model.Employee_Model;
import model.Invoice_Item_Model;
import model.Invoice_Model;
import model.Items_Model;
import model.Parts_Model;
import model.Service_Model;
import model.Vehicle_Model;

public class Appointment_Controller {

	private Main_Menu_GUI maingui = new Main_Menu_GUI();
	private Appointment_GUI appointmentgui = new Appointment_GUI();
	private Appointment_Model appointmentmodel = new Appointment_Model();
	private AddAppointment_GUI addappointmentgui = new AddAppointment_GUI();
	private Appointment_Service appointmentservice  = new Appointment_Service();
	private ArrayList<Appointment_Model> appointments = new ArrayList<Appointment_Model>();
	private Current_Appointment_GUI currentappointmentgui =  new Current_Appointment_GUI();
	private ArrayList<Parts_Model> currentparts =  new ArrayList<Parts_Model>();
	private Appointment_Desicion_GUI appointmentdescion = new Appointment_Desicion_GUI();
	private BlankTable_Frame partsFrame = new BlankTable_Frame();



	private Invoice_Service  invoiceservice = new Invoice_Service();
	private Invoice_GUI invoicegui = new Invoice_GUI();
	
	
	private Vehicle_Service vehicleservice = new Vehicle_Service();
	private Customers_Vehicle_Model ownersCar =  new Customers_Vehicle_Model();
	private ArrayList<Customers_Vehicle_Model> ownersCarList = new ArrayList<Customers_Vehicle_Model>();
	
	private Service_Model servivemodel = new Service_Model();
	private Service_Service serviceservice = new Service_Service();
	private ArrayList<Service_Model> services = new ArrayList<Service_Model>();
	
	private Parts_Model partsmodel = new Parts_Model();
	private Parts_Service partsservice = new Parts_Service();
	private ArrayList<Parts_Model> parts = new ArrayList<Parts_Model>();
	
	private Appointment_Parts_Model appointment_parts = new Appointment_Parts_Model();
	private ArrayList<Appointment_Parts_Model> appointment_partsList = new ArrayList<Appointment_Parts_Model>();
	
	
	private Employee_Service emservice = new Employee_Service();
	private Employee_Model employeemode = new Employee_Model();
	private ArrayList<Employee_Model> employees = new ArrayList<Employee_Model>();
	
	private DefaultTableModel appointmenttablemodel;
	private PartsTable partstablemodel;
	private PartsTable partstable;

	

	

	private int ownerId;
	private String date;
	private String tech;
	private String hour;
	private String reg;
	private String selectedService;
	private String customer;
	private String[] servicesname;
	private int counter = 0;
	private double price = 0;
	private int length = 0;
	
	public Appointment_Controller(Main_Menu_GUI maingui,AddAppointment_GUI addappointmentgui,
			Appointment_GUI appointmentgui, Appointment_Model appointmentmodel, Invoice_GUI invoicegui) {
		
		this.maingui = maingui;
		this.appointmentgui = appointmentgui;
		this.appointmentmodel = appointmentmodel;
		this.addappointmentgui = addappointmentgui;
		this.invoicegui = invoicegui;
		this.addappointmentgui.CancelandReturn(new escapeListener());
		this.appointmentgui.exitListener(new exitListener());
		this.appointmentgui.selectCellonTable(new cellListener());
		this.appointmentgui.MyDateListener(new theDateListener());
		this.addappointmentgui.searchforReg(new registrationSearcher());
		this.addappointmentgui.saveAppointment(new saveListener());
		this.partsFrame.tableSelecterListener(new PartsSelectedListener());
		this.currentappointmentgui.DeletePart(new DeletePartListener());
		this.currentappointmentgui.AddParttoJob(new partAddedListener());
		this.currentappointmentgui.tableSelecterListener(new rowSelectedListener());
		this.currentappointmentgui.InvoiceJob(new InvoiceListener());
		this.appointmentdescion.UpdateAppointment(new UpdateListener());
		this.appointmentdescion.DeleteAppointment(new DeleteListener());
        this.addappointmentgui.disposeOnClose(new DisposeOnClose());
		
		this.maingui.addappointmentListener(new Listener());
	

	}

class DisposeOnClose extends WindowAdapter{

    @Override
    public void windowClosing(WindowEvent e){

        addappointmentgui.getjFrame().dispose();
        //appointments.clear();
        appointment_partsList.clear();
        currentparts.clear();
        services.clear();
        ownersCarList.clear();
        servicesname = new String[servicesname.length];
        counter = 0;
        price = 0;

    }
}


	
	
	public void refreshPartsTable() {

		appointment_partsList.clear();
		List<Appointment_Parts_Model> parts = appointmentservice.findAllParts();

		for (int x = 0; x < parts.size(); x++) {
			appointment_parts = parts.get(x);
			appointment_partsList.add(appointment_parts);

		}
	}
	


    public void refreshTable() {

        appointments.clear();


        List<Appointment_Model> app = appointmentservice.findAll();

        for (int x = 0; x < app.size(); x++) {
            appointmentmodel = app.get(x);
            appointments.add(appointmentmodel);

        }
        //to clear table
        for (int i = 0; i < appointmentgui.getAppointmentTable().getRowCount(); i++) {
            for (int j = 0; j < appointmentgui.getAppointmentTable().getColumnCount(); j++) {
                appointmentgui.getAppointmentTable().setValueAt("", i, j);
            }
        }


        //to populate table
        for(Appointment_Model a : appointments){


            int serviceDuration = a.getService_length();
            String employeeName = a.getEmployee_name();


            if(date.equals(a.getDate()) /*&& hour.equals(a.getTime())*/){


                for(int x = 0; x<appointmentgui.getAppointmentTable().getColumnCount();x++) {

                    if(appointmentgui.getAppointmentTable().getTableHeader().getColumnModel().getColumn(x).getHeaderValue()
                            .equals(employeeName)){

                        for(int y = 0; y<appointmentgui.getRowtable().getRowCount(); y++) {

                            if(appointmentgui.getRowtable().getValueAt(y,y).equals(a.getTime())){

                                appointmentgui.getAppointmentTable().setValueAt(a.getCustomer_name(), y, x);


                                if(serviceDuration > 1){

                                    for(int s =0; s < serviceDuration; s++){
                                        appointmentgui.getAppointmentTable().setValueAt(a.getCustomer_name(), y + s, x);
                                    }
                                }
                            }

                        }
                    }
                }

            }

        }

        appointmenttablemodel.fireTableDataChanged();



    }



	
	
	class InvoiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			double price = 0;
			Invoice_Model invoice  = new Invoice_Model();
			ArrayList<Items_Model> items = new ArrayList<Items_Model>();
			
			
			
			for(Service_Model s: services){
				if(s.getJob_name().equals(currentappointmentgui.getJobOutputLbl().getText())){
					price = s.getJob_price();
					
				}
			}
			
			
			Items_Model item = new Items_Model(currentappointmentgui.getJobOutputLbl().getText().toString(),1,
					price,invoice.getId());
			
			invoice.setDate(currentappointmentgui.getDateOutputLbl().getText());
			invoice.setCustomername(currentappointmentgui.getCustomerOutputLbl().getText());
			invoice.setVehiclereg(currentappointmentgui.getLblVehicleRegOutput().getText());
			invoice.setPrice(currentappointmentgui.getLblPriceOutPut().getText());
			invoice.setService(currentappointmentgui.getJobOutputLbl().getText());
			items.add(item);
			
			
			invoiceservice.open();
			invoiceservice.persist(invoice);
			invoiceservice.close();

			

			
			for(int x = 0; x < currentappointmentgui.getPartstable().getRowCount();x++){
				//System.out.println(currentappointmentgui.getPartstable().getValueAt(x, 0));
				
				for(Parts_Model p : currentparts){
					if(p.getPart_id() == (int) currentappointmentgui.getPartstable().getValueAt(x, 0)){
						
						Items_Model newItem = new Items_Model(p.getPart_name(),1,p.getPart_price(),invoice.getId());
						items.add(newItem);
						invoiceservice.open();
						invoiceservice.persistItem(newItem);
						invoiceservice.close();
						break;
					}
				
					
				}
				
				
			}
			
			
			invoice.setItems(items);
			appointmentservice.open();
			appointmentservice.delete(Integer.valueOf(currentappointmentgui.getIdlbl().getText()));
			appointmentservice.close();

			int selectedOption = JOptionPane
					.showConfirmDialog(null, "JOB HAS BEEN INVOICED \n"
							+ "APPOINTMENT HAS NOW BEEN DELETED \n"
							+ "DO YOU WISH TO VIEW INVOICE",
							"Warning!",
							JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				maingui.setVisible(true);
				try {
					InvoiceTemplate newTempelate = new InvoiceTemplate(invoice);
				} catch (DRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				currentappointmentgui.setVisible(false);
				appointmentgui.getAppointmentTable().setVisible(false);
				appointmentgui.setVisible(false);
                servicesname = new String[servicesname.length];
                counter = 0;
				employees.clear();
				refreshTable();
				
				
				
			}else{
				currentappointmentgui.setVisible(false);
				
				appointmentgui.setVisible(false);
				maingui.setVisible(true);
				employees.clear();
                servicesname = new String[servicesname.length];
                counter = 0;
				refreshTable();
				
			}

		}

	}
	
	class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			currentappointmentgui.setVisible(true);
			appointmentdescion.setVisible(false);
			 counter = 0;

			 
		}


	
	}
	
	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			int id  = Integer.valueOf(currentappointmentgui.getIdlbl().getText());
			

			int selectedOption = JOptionPane
					.showConfirmDialog(null, "Delete Appointment?",
							"Warning!",
							JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				

						appointmentservice.open();
						appointmentservice.delete(id);
						appointmentservice.close();



			}
			
			appointmentdescion.setVisible(false);
			refreshTable();
		}


	
	}
	
	
	class rowSelectedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {

			try {
				int rowSelected = (int) currentappointmentgui.getPartstable().getValueAt(currentappointmentgui.getPartstable().getSelectedRow(),0);
				
				
				
				for (Appointment_Parts_Model c : appointment_partsList) {
					
					if(c.getAppointment().getAppointment_id() == Integer.valueOf(currentappointmentgui.getIdlbl().getText())){
				
						if(c.getPart().getPart_id() == rowSelected){
														
							ownerId = c.getId();
			
							
						}	
						

					}

				}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}

	}
	
	
	
	
	class DeletePartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Delete Part?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
		

							appointmentservice.open();
							appointmentservice.deleteParts(ownerId);
							appointmentservice.close();
							refreshPartsTable();
				}

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}
			
			
			
		}
		
	}
	
	
	class PartsSelectedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
		
			try {
				int rowSelected = (int) partsFrame.getBlankTable().getValueAt(partsFrame.getBlankTable().getSelectedRow(),0);
				
			
				for (Parts_Model c : parts) {

					if (c.getPart_id() == rowSelected) {
						
						for(Appointment_Model a : appointments){
						
							if(a.getAppointment_id() == Integer.valueOf(currentappointmentgui.getIdlbl().getText())){
						appointmentservice.open();
						appointment_parts.setAppointment(a);
						appointment_parts.setPart(c);
                                currentparts.add(c);
                                partstable.fireTableDataChanged();
                                partstablemodel.fireTableDataChanged();
                                price = price+ c.getPart_price();
                                currentappointmentgui.getLblPriceOutPut().setText(String.valueOf(price));

						appointmentservice.persistPart(appointment_parts);
						appointmentservice.close();
							}
						}
					}

				}

				parts.clear();
				refreshPartsTable();
				partsFrame.dispose();

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}

	}
	
	
	
	class saveListener implements ActionListener {

		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			date = addappointmentgui.getDateLbl().getText();
			tech = addappointmentgui.getTechLbl().getText();
			hour = addappointmentgui.getTimeLbl().getText();
			customer = addappointmentgui.getLblCustomersName().getText();
			reg = addappointmentgui.getLblReg().getText();
			selectedService =String.valueOf(addappointmentgui.getServiceComboBox().getSelectedItem());
			
			for(Service_Model sv : services){
				
				if(selectedService.equals(sv.getJob_name())){
					length = sv.getJob_time();
					
				}
				
			}
	
			int selectedRow = appointmentgui.getAppointmentTable().getSelectedRow();
			int selectedColumn = appointmentgui.getAppointmentTable().getSelectedColumn();
			boolean nextCellAvailable = false;
			
			System.out.println("SELECTED COLUMN LENGTH" + selectedRow);
			
			if(selectedRow + length < 12){
			if(appointmentgui.getAppointmentTable().getModel().getValueAt(selectedRow, selectedColumn).toString().equals("")
					&& appointmentgui.getAppointmentTable().getModel().getValueAt(selectedRow + length, selectedColumn).toString().equals("")
					&& appointmentgui.getAppointmentTable().getModel().getValueAt(selectedRow + length /2 , selectedColumn).toString().equals("")
					&& appointmentgui.getAppointmentTable().getModel().getValueAt(selectedRow + length /2 + 1 , selectedColumn).toString().equals("")
					&& appointmentgui.getAppointmentTable().getModel().getValueAt(selectedRow + length - 1 , selectedColumn).toString().equals("")
					&& appointmentgui.getAppointmentTable().getModel().getValueAt(selectedRow + 1 , selectedColumn).toString().equals("")	){
				
			
					if(customer.equals("") || reg.equals("")){


                        JOptionPane.showMessageDialog(partsFrame, "NO VALID REGISTRATION ENTERED" ," ERROR "
                                , JOptionPane.WARNING_MESSAGE);


                    }

                    else {
                        appointmentservice.open();

                        appointmentmodel.getAppointment_id();
                        appointmentmodel.setCustomer_name(customer);
                        appointmentmodel.setDate(date);
                        appointmentmodel.setVehicle_reg(reg);
                        appointmentmodel.setEmployee_name(tech);
                        appointmentmodel.setJob_name(selectedService);
                        appointmentmodel.setTime(hour);
                        appointmentmodel.setService_length(length);
                        appointmentservice.persist(appointmentmodel);
                        appointmentservice.close();
                        appointments.add(appointmentmodel);
                        appointmenttablemodel.fireTableDataChanged();
                        refreshTable();

                        JOptionPane.showMessageDialog(partsFrame, " FOR " + customer +" ON "
                                        + date +" AND TIME " + hour ," APPOINTMENT ADDED TO SYSTEM "
                                , JOptionPane.WARNING_MESSAGE);

                    }

				}		
				
				else{
				
				JOptionPane.showMessageDialog(partsFrame," THIS JOB TAKES APPROXIMETLY " + String.valueOf(length)+" HOURS  \n"
						+ " PLEASE ALLOW FOR TEST DRIVE AND PREPERATION FOR NEXT JOB \n "
						+ " SELECT ANOTHER SLOT, ", "ERROR"
									,JOptionPane.WARNING_MESSAGE);
				
				

			}
			
			
		}else{
			JOptionPane.showMessageDialog(partsFrame, "CLOSE OF BUSINESS DAY \n BOOKING EXCEEDS TIME \n PLEASE"
					+ " PLEASE RESCHEDULE " ," ERROR"
					, JOptionPane.WARNING_MESSAGE);
		
	}			
						
						System.out.println("DATE--->" + date);
						addappointmentgui.getjFrame().dispose();
						services.clear();
						ownersCarList.clear();
						servicesname = new String[servicesname.length];
						counter = 0;
			
			
		}
		
		
		
	}
	
	class partAddedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
				
			
				partsFrame.setVisible(true);
				partstable = new PartsTable(parts);
				partsFrame.getBlankTable().setModel(partstable);
				
				List<Parts_Model> c = partsservice.findAll();

				for (int x = 0; x < c.size(); x++) {
					partsmodel = c.get(x);
					parts.add(partsmodel);

				}
			
			
			
		}
		

		
		
		
		
		
	}
	
	class registrationSearcher implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String reg = addappointmentgui.getSearchTextfield().getText();

			for (Customers_Vehicle_Model c : ownersCarList) {
				String customerName = c.getCustomer().getFirst_name() + " "
						+ c.getCustomer().getSurname();
				String makeModel = c.getVehicle().getVehicle_make() + " "
						+ c.getVehicle().getVehicle_model();
				String registration = c.getVehicle().getVehicle_reg();
				
				boolean found = false;

				if (c.getVehicle().getVehicle_reg().equals(reg)) {
					System.out.println("Success");

					addappointmentgui.getLblCustomersName().setText(
							customerName);
					addappointmentgui.getLblMakeModel().setText(makeModel);
					addappointmentgui.getLblReg().setText(registration);

				}

				else {
					System.out.println("Fail");

					found = false;

				}

			}

		}

	}
	
	
	
	class escapeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			addappointmentgui.getjFrame().dispose();
			//appointments.clear();
			appointment_partsList.clear();
			currentparts.clear();
			services.clear();
			ownersCarList.clear();
			servicesname = new String[servicesname.length];
			counter = 0;
			price = 0;

		}

	}
	
	class theDateListener implements DateListener{

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  
		@Override 
		public void dateChanged(DateEvent e){
			
			Calendar c = e.getSelectedDate();
			appointmentgui.getAppointmentTable().setVisible(true);
			
			if (c != null) {
				
				date = dateFormat.format(c.getTime()); 

				
				addappointmentgui.getDateLbl().setText(date);
				
				//to clear table
				 for (int i = 0; i < appointmentgui.getAppointmentTable().getRowCount(); i++) {
				        for (int j = 0; j < appointmentgui.getAppointmentTable().getColumnCount(); j++) {
				        	appointmentgui.getAppointmentTable().setValueAt("", i, j);
				        }
				    }

			
			//to populate table
			for(Appointment_Model a : appointments){
				
				
				int serviceDuration = a.getService_length();
				String employeeName = a.getEmployee_name();

				
			if(date.equals(a.getDate()) /*&& hour.equals(a.getTime())*/){

				
				for(int x = 0; x<appointmentgui.getAppointmentTable().getColumnCount();x++) {

					if(appointmentgui.getAppointmentTable().getTableHeader().getColumnModel().getColumn(x).getHeaderValue()
							.equals(employeeName)){	
					
					for(int y = 0; y<appointmentgui.getRowtable().getRowCount(); y++) {
	
						if(appointmentgui.getRowtable().getValueAt(y,y).equals(a.getTime())){
							
							appointmentgui.getAppointmentTable().setValueAt(a.getCustomer_name(), y, x);
							
							
							if(serviceDuration > 1){
								
								for(int s =0; s < serviceDuration; s++){
								appointmentgui.getAppointmentTable().setValueAt(a.getCustomer_name(), y + s, x);
											}
										}
									}

								}
							}
						}

					}

				}

			} else {

				System.out.println("No time selected.");
			}
		}

	}

	class cellListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent evt ) {
			
				partstablemodel = new PartsTable(currentparts);
				currentappointmentgui.getPartstable().setModel(partstablemodel);
			
			List<Service_Model> c = serviceservice.findAll();	
			
			for (int x = 0; x < c.size(); x++) {

				servivemodel = c.get(x);
				services.add(servivemodel);
				servicesname = new String[services.size()];
				
			}
			
			
			for(int x = 0; x <services.size();x++){
				
			servicesname[counter] = services.get(x).getJob_name();	
			counter++;
			

			}
				
			
			List<Customers_Vehicle_Model> cus = vehicleservice.findAllOwners();	
			
				for (int x = 0; x < cus.size(); x++) {

				ownersCar = cus.get(x);
				ownersCarList.add(ownersCar);
			}	


	    	int row = appointmentgui.getAppointmentTable().rowAtPoint(evt.getPoint());
	        int col = appointmentgui.getAppointmentTable().columnAtPoint(evt.getPoint());
	        
	        String time = (String)appointmentgui.getRowtable().getValueAt(row, 0);

	        	
	        
	     if(appointmentgui.getAppointmentTable().getValueAt(row, col)== "") {
	    	 System.out.println(appointmentgui.getAppointmentTable().getValueAt(row, col));
	    	
	    	 
	    	 
	    	 
	    	 
	    	 
			String employeeName = (String)appointmentgui.getAppointmentTable().getTableHeader().getColumnModel()
						.getColumn(col).getHeaderValue();
	    	 
		
	    	 addappointmentgui.getTechLbl().setText(employeeName);
	    	 addappointmentgui.getTimeLbl().setText(time);
	    	 addappointmentgui.getServiceComboBox().setModel(new DefaultComboBoxModel(servicesname));
	    	 addappointmentgui.getjFrame().setVisible(true);
	     
	     }else{
				

	    	 for(Appointment_Model a : appointments){

	    		 
	    	if(a.getCustomer_name() == appointmentgui.getAppointmentTable().getValueAt(row, col)){ 
	    	 currentappointmentgui.getDateOutputLbl().setText(date);
	    	 
	    	 for(Service_Model s : services){
	    		 
	    	if(a.getJob_name().equals(s.getJob_name())){
	    		 price = s.getJob_price();
	    		 currentappointmentgui.getLblPriceOutPut().setText(String.valueOf(price));
	    	}
	    	 }
	    	 
	    	 
	    	 currentappointmentgui.getIdlbl().setText(String.valueOf(a.getAppointment_id()));
	    	 currentappointmentgui.getTechOutputLbl().setText(a.getEmployee_name());
	    	 currentappointmentgui.getCustomerOutputLbl().setText(a.getCustomer_name());
	    	 currentappointmentgui.getJobOutputLbl().setText(a.getJob_name());
	    	 currentappointmentgui.getLblVehicleRegOutput().setText(a.getVehicle_reg());

	    	}
	    	
	    	 }
	    	 
	    	 	
	    		partstablemodel = new PartsTable(currentparts);
   				currentappointmentgui.getPartstable().setModel(partstablemodel);
   				currentparts.clear();

				
   			
   				for(Appointment_Parts_Model parts : appointment_partsList){
   	
					if(parts.getAppointment().getAppointment_id() == Integer.valueOf(currentappointmentgui.getIdlbl().getText())){

						
					
						
							
							currentparts.add(parts.getPart());
							price = price + parts.getPart().getPart_price();
							System.out.println("PRICE"+ price);
							currentappointmentgui.getLblPriceOutPut().setText(String.valueOf(price));
							
						
						
					}
				
				}

	    	 //currentappointmentgui.setVisible(true);
   				appointmentdescion.setVisible(true);
	    	 counter = 0;
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
		
	}
	
	
	
	class exitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {

				
				appointments.clear();
				appointment_partsList.clear();
				services.clear();
				currentparts.clear();
				ownersCarList.clear();
				//servicesname = new String[servicesname.length];
				counter = 0;
				//price = 0;
				employees.clear();
				appointmentgui.dispose();
				maingui.setVisible(true);

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}

		}

	}

	class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			try {

				maingui.dispose();
				
				List<Employee_Model> c = emservice.findAll();

				for (int x = 0; x < c.size(); x++) {
					employeemode = c.get(x);
					employees.add(employeemode);

				}
				
				
				List<Appointment_Model> a = appointmentservice.findAll();

				for (int x = 0; x < a.size(); x++) {
					appointmentmodel = a.get(x);
					appointments.add(appointmentmodel);

				}
				
				

				
				List<Appointment_Parts_Model> p = appointmentservice.findAllParts();

				for (int x = 0; x < p.size(); x++) {
					appointment_parts = p.get(x);
					appointment_partsList.add(appointment_parts);

				}
				
				
				appointmenttablemodel = (DefaultTableModel)appointmentgui.getAppointmentTable().getModel();
				appointmenttablemodel.setRowCount(12);
				appointmenttablemodel.setColumnCount(employees.size());
				
			


				for (int i = 0; i < appointmentgui.getAppointmentTable().getColumnCount(); i++) {
					TableColumn column1 = appointmentgui.getAppointmentTable()
							.getTableHeader().getColumnModel().getColumn(i);

					for (Employee_Model em : employees) {
						System.out.println(em.getEmp_firstname()
								+ em.getEmp_surname());
						// column1.setHeaderValue(employees.get(i).getEmp_firstname()
						// + " " + employees.get(i).getEmp_surname());
					}
					column1.setHeaderValue(employees.get(i).getEmp_firstname()
							+ " " + employees.get(i).getEmp_surname());
					


				}

				for (int j = 0; j < (appointmentgui.getColumnModel()
						.getColumnCount()); j++) {
					appointmentgui.getColumnModel().getColumn(j)
							.setPreferredWidth(150);
				}

				appointmentgui.getAppointmentTable().getTableHeader()
						.setDefaultRenderer(new Header_Render());
				
				
				
				appointmentgui.setVisible(true);

			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}

		}

	}

}
