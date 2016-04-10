package controller;

import gui.CustomerTable;
import gui.Customer_GUI;
import gui.Main_Menu_GUI;
import gui.SalesTable;
import gui.Sales_GUI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

import service.Sales_Service;
import utility.FieldValidator;
import model.Customer_Model;
import model.Sales_Model;



public class Sales_Controller {
	
	
	
	private Main_Menu_GUI maingui = new Main_Menu_GUI();
	private Sales_GUI salesgui = new Sales_GUI();
	private Sales_Model salesmodel = new Sales_Model();
	private Sales_Service salesservice = new Sales_Service();
	private ArrayList<Sales_Model> sales = new ArrayList<Sales_Model>();
	private SalesTable salestable;
	
	private static final int IMG_WIDTH = 100;
	private static final int IMG_HEIGHT = 100;

	public Sales_Controller(Main_Menu_GUI maingui, Sales_GUI salesgui,
			Sales_Model salesmodel) {
		this.maingui = maingui;
		this.salesgui = salesgui;
		this.salesmodel = salesmodel;

		this.salesgui.tableSelecterListener(new rowSelectedListener());
		this.salesgui.exitListener(new exitListener());
		this.salesgui.addNewSalesListener(new addListener());
		this.salesgui.editButtonListener(new editListener());
		this.salesgui.deleteListener(new deleteListener());
		this.maingui.addSalesListener(new Listener());
		this.salesgui.SearchforImage(new ImageSearchListener());

	}
	
	
	
	
	class editListener implements ActionListener {

		private String message;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {

				boolean validMake = FieldValidator.checkNameField(salesgui.getDetailmakeTextField());
				boolean validModel = FieldValidator.checkNameField(salesgui.getDetailmodeltextfield());
				boolean validPrice = FieldValidator.checkForDouble(salesgui.getPriceInfotextField());			
				boolean validDescription = FieldValidator.checkInfoHistory(salesgui.getDetaildescriptiontextfield());

				
				
				
				message = "";
				
				if(validMake == false ){
					message = "INVALID ENTRY IN MAKE FIELD - (not empty, only letters, not more than 30 characters)\n";
					salesgui.getDetailmakeTextField().setBackground(Color.YELLOW);
				}else
					salesgui.getDetailmakeTextField().setBackground(Color.WHITE);
				
				if(validModel == false){
					message = message + "INVALID ENTRY IN MODEL FIELD - (not empty, only letters, not more than 30 characters)\n";
					salesgui.getDetailmodeltextfield().setBackground(Color.YELLOW);
				}else
					salesgui.getDetailmodeltextfield().setBackground(Color.WHITE);
				
				if(validPrice == false){
					message = message + "INVALID ENTRY PRICE FIELD - (not empty, only digits)\n";
					salesgui.getPriceInfotextField().setBackground(Color.YELLOW);
				}else
					salesgui.getPriceInfotextField().setBackground(Color.WHITE);
				
				
				if(validDescription == false){
					message = message + "INVALID ENTRY IN DESCRIPTION FIELD - (not more than 500 characters) \n";
					salesgui.getDetaildescriptiontextfield().setBackground(Color.YELLOW);
				}else
					salesgui.getDetaildescriptiontextfield().setBackground(Color.WHITE);
				

			
				if(validMake == true && validModel == true && validPrice == true && 
						validDescription == true)
				{
				
				
				
				String make = salesgui.getDetailmakeTextField().getText();
				String model = salesgui.getDetailmodeltextfield().getText();
				String description = salesgui.getDetaildescriptiontextfield().getText();
				double price = Double.parseDouble(salesgui.getPriceInfotextField().getText());
				double idextract = Double.parseDouble(salesgui.getDetailidtextfield().getText());
				int id = (int) idextract;

				int selectedOption = JOptionPane
						.showConfirmDialog(null,
								"Save Update to  Sales Information?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					for (Sales_Model c : sales) {

		
						boolean found = false;
						if (c.getId() == salesservice.findById(id)
								.getId()) {
							System.out.println("Sucess");
							salesservice.open();
							
							c.setMake(make);
							c.setModel(model);
							c.setDescription(description);
							c.setPrice(price);
							
							salesservice.update(c);
							salesservice.close();
							found = true;

							salesgui.getDetailidtextfield().setText("");
							salesgui.getDetailregtextfield().setText("");
							salesgui.getDetailmakeTextField().setText("");
							salesgui.getDetailmodeltextfield().setText("");
							salesgui.getDetaildescriptiontextfield().setText("");
							salesgui.getPriceInfotextField().setText("");
						
							//salesgui.getLblDBImageLbl().setText("");
							salesgui.getLblDBImageLbl().setIcon(null);
							//refreshTable();
						}
					
						else {

							found = false;
						}

					}

				}
				}else {
			    	
			    	JOptionPane.showMessageDialog( salesgui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
				
				
			} catch (NumberFormatException nfe) {

				System.out.println("Not A Number: " + nfe.getMessage());

			}

		}
	}
	
	
	
	
	
	class deleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {

				double idextract = Double.parseDouble(salesgui.getDetailidtextfield().getText());
				int id = (int) idextract;

				int selectedOption = JOptionPane
						.showConfirmDialog(null, "Delete Sales Car?",
								"Warning!",
								JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					salesservice.open();
					salesservice.delete(id);
					salesservice.close();
					//refreshTable();
					salesgui.getDetailidtextfield().setText("");
					salesgui.getDetailregtextfield().setText("");
					salesgui.getDetailmakeTextField().setText("");
					salesgui.getDetailmodeltextfield().setText("");
					salesgui.getDetaildescriptiontextfield().setText("");
					salesgui.getPriceInfotextField().setText("");
					salesgui.getLblDBImageLbl().setIcon(null);
			
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

				int rowSelected = (int) salesgui.getSalestable().getValueAt(salesgui.getSalestable().getSelectedRow(),0);
				//int rowSelected = (int) customergui.getCustomertable().getValueAt(customergui.getCustomertable().getSelectedRow(),0);
				
				for (Sales_Model c : sales) {

					if (c.getId() == rowSelected) {

						ImageIcon i = new ImageIcon(c.getImage());
				       
			
						
						salesgui.getDetailidtextfield().setText(Integer.toString(c.getId()));
						salesgui.getDetailregtextfield().setText(c.getReg());
						salesgui.getDetailmakeTextField().setText(c.getMake());
						salesgui.getDetailmodeltextfield().setText(c.getModel());
						salesgui.getDetaildescriptiontextfield().setText(c.getDescription());
						salesgui.getPriceInfotextField().setText(Double.toString(c.getPrice()));
						salesgui.getLblDBImageLbl().setIcon(i);
						

					}
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}

	}
	
	
	public static BufferedImage resizeImage(Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
	
	
	
	class addListener implements ActionListener {

		private String message;
		public void actionPerformed(ActionEvent e) {
			
			
			try {

				boolean validMake = FieldValidator.checkNameField(salesgui.getMaketextField());
				boolean validModel = FieldValidator.checkNameField(salesgui.getModeltextField());
				boolean validPrice = FieldValidator.checkForDouble(salesgui.getPriceTextfield());			
				boolean validDescription = FieldValidator.checkInfoHistory(salesgui.getDescriptionTextfield());
				boolean validRegistration = FieldValidator.checkRegistration(salesgui.getRegTextfield());
				boolean validImage= FieldValidator.checkCustomerField(salesgui.getImageTextField());
				
				
				
				message = "";
				
				if(validMake == false ){
					message = "INVALID ENTRY IN MAKE FIELD - (not empty, only letters, not more than 30 characters)\n";
					salesgui.getMaketextField().setBackground(Color.YELLOW);
				}else
					salesgui.getMaketextField().setBackground(Color.WHITE);
				
				if(validModel == false){
					message = message + "INVALID ENTRY IN MODEL FIELD - (not empty, only letters, not more than 30 characters)\n";
					salesgui.getModeltextField().setBackground(Color.YELLOW);
				}else
					salesgui.getModeltextField().setBackground(Color.WHITE);
				
				if(validPrice == false){
					message = message + "INVALID ENTRY PRICE FIELD - (not empty, only digits)\n";
					salesgui.getPriceTextfield().setBackground(Color.YELLOW);
				}else
					salesgui.getPriceTextfield().setBackground(Color.WHITE);
				
				
				if(validDescription == false){
					message = message + "INVALID ENTRY IN DESCRIPTION FIELD - (not more than 500 characters) \n";
					salesgui.getDescriptionTextfield().setBackground(Color.YELLOW);
				}else
					salesgui.getDescriptionTextfield().setBackground(Color.WHITE);
				
				if(validRegistration == false){
					message = message + "INVALID ENTRY IN REGISTRATION FIELD - (not empty, letters & digits , not more than 30 characters)\n";
					salesgui.getRegTextfield().setBackground(Color.YELLOW);
				}else{
					salesgui.getRegTextfield().setBackground(Color.WHITE);
				}
				
				if(validImage == false){
					message = message + "INVALID ENTRY IN IMAGE FIELD - (Please Click and Select an Image)\n";
					salesgui.getImageTextField().setBackground(Color.YELLOW);
				}else{
					salesgui.getImageTextField().setBackground(Color.WHITE);
				}
				
			
				if(validMake == true && validModel == true && validPrice == true && 
						validDescription == true && validRegistration == true && validImage == true)
				{
				
				
				salesservice.open();
				String reg = salesgui.getRegTextfield().getText();
				String make = salesgui.getMaketextField().getText();
				String model = salesgui.getModeltextField().getText();
				String description = salesgui.getDescriptionTextfield().getText();
				double price = Double.parseDouble(salesgui.getPriceTextfield().getText());


				//save image into database
		    	File file = new File(salesgui.getImageTextField().getText());
		        byte[] bFile = new byte[(int) file.length()];

		        
		        try {
		   	     FileInputStream fileInputStream = new FileInputStream(file);
		   	     //convert file into array of bytes
		   	     fileInputStream.read(bFile);
		   	     fileInputStream.close();
		           } catch (Exception f) {
		   	     f.printStackTrace();
		           }
		        
		        
		

				salesmodel.setMake(make);
				salesmodel.setModel(model);
				salesmodel.setDescription(description);
				salesmodel.setPrice(price);
				salesmodel.setReg(reg);
				salesmodel.setImage(bFile);
				//customers.add(customers.size(),customermodel);
				salesservice.persist(salesmodel);
				salesservice.close();
				

				salesgui.getRegTextfield().setText("");
				salesgui.getMaketextField().setText("");
				salesgui.getModeltextField().setText("");
				salesgui.getDescriptionTextfield().setText("");
				salesgui.getPriceTextfield().setText("");
				salesgui.getImageTextField().setText("Please click to select Image");
				}else {
			    	
			    	JOptionPane.showMessageDialog( salesgui, message, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
				
			} catch (NumberFormatException nfe) {

				System.out.println("ERROR CANNOT BE DONE: " + nfe.getMessage());

			}

		}

	}
	
	
	class ImageSearchListener implements MouseListener {

		   JFileChooser chooser;
		   String choosertitle;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			
			    int result;
			        
			    chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("C:\\Users\\Barry\\Documents\\College Work 2016\\Final Project Pictures"));
			    chooser.setDialogTitle(choosertitle);
			    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(salesgui) == JFileChooser.APPROVE_OPTION) { 
			      System.out.println("getCurrentDirectory(): " 
			         +  chooser.getCurrentDirectory());
			      System.out.println("getSelectedFile() : " 
			         +  chooser.getSelectedFile());
			      
			      salesgui.getImageTextField().setText(chooser.getSelectedFile().toString());
			      
			      }
			    else {
			    salesgui.getImageTextField().setText("No Selection");
			      System.out.println("No Selection ");
			      }
			     }
			   
			  public Dimension getPreferredSize(){
			    return new Dimension(200, 200);
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
				

				salesgui.getRegTextfield().setText("");
				salesgui.getMaketextField().setText("");
				salesgui.getModeltextField().setText("");
				salesgui.getDescriptionTextfield().setText("");
				salesgui.getPriceTextfield().setText("");
				salesgui.getDetailidtextfield().setText("");
				salesgui.getDetailregtextfield().setText("");
				salesgui.getDetailmakeTextField().setText("");
				salesgui.getDetailmodeltextfield().setText("");
				salesgui.getDetaildescriptiontextfield().setText("");
				salesgui.getPriceInfotextField().setText("");
				salesgui.getLblDBImageLbl().setIcon(null);
				salesgui.getImageTextField().setText("Please click to select Image");
				salesgui.getRegTextfield().setBackground(Color.WHITE);
				salesgui.getMaketextField().setBackground(Color.WHITE);
				salesgui.getModeltextField().setBackground(Color.WHITE);
				salesgui.getDescriptionTextfield().setBackground(Color.WHITE);
				salesgui.getPriceTextfield().setBackground(Color.WHITE);
				salesgui.getDetailidtextfield().setBackground(Color.WHITE);
				salesgui.getDetailregtextfield().setBackground(Color.WHITE);
				salesgui.getDetailmakeTextField().setBackground(Color.WHITE);
				salesgui.getDetailmodeltextfield().setBackground(Color.WHITE);
				salesgui.getDetaildescriptiontextfield().setBackground(Color.WHITE);
				salesgui.getPriceInfotextField().setBackground(Color.WHITE);
				salesgui.getImageTextField().setBackground(Color.WHITE);

				sales.clear();
				salesgui.dispose();
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
    			
    			salesgui.setVisible(true);
 				salestable = new SalesTable(sales);
 				salesgui.getSalestable().setModel(salestable);
 		
 				List<Sales_Model> c = salesservice.findAll();

 				for (int x = 0; x < c.size(); x++) {
 					salesmodel = c.get(x);
 					sales.add(salesmodel);

 				}
    			 
    			 
    			 
    		
    			 
 
    					
    			 
    		 
    		 } catch (NumberFormatException nfe) {
  			   
                 System.out.println("Not A Number: " + nfe.getMessage());
       
              }
    	
    	
    }
    	
}
	

}
