package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import utility.UppercaseDocumentFilter;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;

public class Vehicle_GUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane vehicleScroller; 
	private JTable vehicleTable;
    private JLabel serviceNameJLabel;
    private JLabel lblmake;
    private JLabel lblmodel;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnExit;
    private JTextField regTextfield;
    private JTextField makeTextField;
    private JTextField modelTextField;
    private JTextField chassisTextfield;
    private JLabel lblVehicleChassis;
    private JLabel lblLinkToCustomer;
    private JTextField customerTextField;
    private JOptionPane updateDetails;
    private DocumentFilter filter = new UppercaseDocumentFilter();
    private JTextField infoIDTextField;
    private JTextField InfoRegTextField;
    private JTextField InfoMakeTextField;
    private JTextField InfoModelTextField;
    private JTextField infoChassisTextField;
    private JTextField InfoCustomerTextField;
    private JLabel hiddenCustomerIDLbl;


	public Vehicle_GUI() {
		setTitle("Vehicle Section");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel detailsJPanel = new JPanel();
		detailsJPanel.setBorder(new TitledBorder(null, "Vehicle Details", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		detailsJPanel.setBounds(22, 11, 322, 205);
		detailsJPanel.setLayout(null);
		vehicleTable = new JTable(/*data, columns*/);
		vehicleScroller = new JScrollPane(vehicleTable);
		vehicleScroller.setBounds(10, 23, 302, 171);
		detailsJPanel.add(vehicleScroller);
		contentPane.add(detailsJPanel);
		
		
		
		
		JPanel informationJPanel = new JPanel();
		informationJPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add Vehicle", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		informationJPanel.setBounds(363, 11, 322, 205);
		contentPane.add(informationJPanel);
		informationJPanel.setLayout(null);
		
		serviceNameJLabel = new JLabel("Vehicle Reg");
		serviceNameJLabel.setBounds(10, 34, 88, 14);
		informationJPanel.add(serviceNameJLabel);
		
		lblmake = new JLabel("Vehicle Make");
		lblmake.setBounds(10, 72, 88, 14);
		informationJPanel.add(lblmake);
		
		lblmodel = new JLabel("Vehicle Model");
		lblmodel.setBounds(10, 110, 88, 14);
		informationJPanel.add(lblmodel);
		
		regTextfield = new JTextField();
		regTextfield.setColumns(10);
		regTextfield.setBounds(108, 31, 81, 20);
	    ((AbstractDocument) regTextfield.getDocument()).setDocumentFilter(filter);
		informationJPanel.add(regTextfield);
		
		makeTextField = new JTextField();
		makeTextField.setColumns(10);
		makeTextField.setBounds(108, 69, 81, 20);
		informationJPanel.add(makeTextField);
		
		modelTextField = new JTextField();
		modelTextField.setColumns(10);
		modelTextField.setBounds(108, 107, 81, 20);
		informationJPanel.add(modelTextField);
		
		chassisTextfield = new JTextField();
		chassisTextfield.setColumns(10);
		chassisTextfield.setBounds(108, 146, 95, 20);
		 ((AbstractDocument) chassisTextfield.getDocument()).setDocumentFilter(filter);
		informationJPanel.add(chassisTextfield);
		
		lblVehicleChassis = new JLabel("Vehicle Chassis");
		lblVehicleChassis.setBounds(10, 149, 88, 14);
		informationJPanel.add(lblVehicleChassis);
		
		lblLinkToCustomer = new JLabel("Add Customer");
		lblLinkToCustomer.setBounds(10, 177, 88, 14);
		informationJPanel.add(lblLinkToCustomer);
		
		customerTextField = new JTextField();
		customerTextField.setEditable(false);
		customerTextField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		customerTextField.setText("Click To Add Customer");
		customerTextField.setColumns(10);
		customerTextField.setBounds(96, 177, 136, 20);
		informationJPanel.add(customerTextField);
		
		
		
		updateDetails = new JOptionPane();
		updateDetails.setBounds(574, 134, 200,200);	
		informationJPanel.add(updateDetails);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(242, 173, 66, 23);
		informationJPanel.add(btnAdd);
		
		hiddenCustomerIDLbl = new JLabel("");
		hiddenCustomerIDLbl.setBounds(242, 72, 46, 14);
		informationJPanel.add(hiddenCustomerIDLbl);
		
		JPanel VehicleInfoPanel = new JPanel();
		VehicleInfoPanel.setBorder(new TitledBorder(null, "Vehicle Information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		VehicleInfoPanel.setBounds(22, 227, 663, 165);
		contentPane.add(VehicleInfoPanel);
		VehicleInfoPanel.setLayout(null);
		
		JLabel infoRegLbl = new JLabel("Vehicle Reg");
		infoRegLbl.setBounds(10, 61, 88, 14);
		VehicleInfoPanel.add(infoRegLbl);
		
		JLabel infoIDLbl = new JLabel("ID");
		infoIDLbl.setBounds(10, 25, 88, 14);
		VehicleInfoPanel.add(infoIDLbl);
		
		JLabel infoMakeLbl = new JLabel("Vehicle Make");
		infoMakeLbl.setBounds(10, 102, 88, 14);
		VehicleInfoPanel.add(infoMakeLbl);
		
		JLabel InfoModelLbl = new JLabel("Vehicle Model");
		InfoModelLbl.setBounds(10, 140, 88, 14);
		VehicleInfoPanel.add(InfoModelLbl);
		
		JLabel InfoChassisLbl = new JLabel("Vehicle Chassis");
		InfoChassisLbl.setBounds(195, 25, 88, 14);
		VehicleInfoPanel.add(InfoChassisLbl);
		
		JLabel InfoCustomerLbl = new JLabel("Customer Name");
		InfoCustomerLbl.setBounds(195, 102, 88, 14);
		VehicleInfoPanel.add(InfoCustomerLbl);
		
		infoIDTextField = new JTextField();
		infoIDTextField.setEditable(false);
		infoIDTextField.setColumns(10);
		infoIDTextField.setBounds(77, 22, 81, 20);
		VehicleInfoPanel.add(infoIDTextField);
		
		InfoRegTextField = new JTextField();
		InfoRegTextField.setColumns(10);
		InfoRegTextField.setBounds(97, 58, 81, 20);
		VehicleInfoPanel.add(InfoRegTextField);
		
		InfoMakeTextField = new JTextField();
		InfoMakeTextField.setColumns(10);
		InfoMakeTextField.setBounds(97, 99, 81, 20);
		VehicleInfoPanel.add(InfoMakeTextField);
		
		InfoModelTextField = new JTextField();
		InfoModelTextField.setColumns(10);
		InfoModelTextField.setBounds(97, 137, 81, 20);
		VehicleInfoPanel.add(InfoModelTextField);
		
		infoChassisTextField = new JTextField();
		infoChassisTextField.setColumns(10);
		infoChassisTextField.setBounds(301, 22, 143, 20);
		VehicleInfoPanel.add(infoChassisTextField);
		
		InfoCustomerTextField = new JTextField();
		InfoCustomerTextField.setText("Click To Edit Customer");
		InfoCustomerTextField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		InfoCustomerTextField.setEditable(false);
		InfoCustomerTextField.setColumns(10);
		InfoCustomerTextField.setBounds(301, 99, 143, 20);
		VehicleInfoPanel.add(InfoCustomerTextField);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(500, 98, 66, 23);
		VehicleInfoPanel.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(576, 98, 66, 23);
		VehicleInfoPanel.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(576, 136, 66, 23);
		VehicleInfoPanel.add(btnExit);
	}

	



	public JLabel getHiddenCustomerIDLbl() {
		return hiddenCustomerIDLbl;
	}





	public void setHiddenCustomerIDLbl(JLabel hiddenCustomerIDLbl) {
		this.hiddenCustomerIDLbl = hiddenCustomerIDLbl;
	}





	public JTable getVehicleTable() {
		return vehicleTable;
	}


	public void setVehicleTable(JTable vehicleTable) {
		this.vehicleTable = vehicleTable;
	}


	public JButton getBtnAdd() {
		return btnAdd;
	}


	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}


	public JButton getBtnEdit() {
		return btnEdit;
	}


	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}


	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}


	public JButton getBtnExit() {
		return btnExit;
	}


	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}



	public JTextField getRegTextfield() {
		return regTextfield;
	}


	public void setRegTextfield(JTextField regTextfield) {
		this.regTextfield = regTextfield;
	}


	public JTextField getMakeTextField() {
		return makeTextField;
	}


	public void setMakeTextField(JTextField makeTextField) {
		this.makeTextField = makeTextField;
	}


	public JTextField getModelTextField() {
		return modelTextField;
	}


	public void setModelTextField(JTextField modelTextField) {
		this.modelTextField = modelTextField;
	}


	public JTextField getChassisTextfield() {
		return chassisTextfield;
	}


	public void setChassisTextfield(JTextField chassisTextfield) {
		this.chassisTextfield = chassisTextfield;
	}


	public JTextField getCustomerTextField() {
		return customerTextField;
	}


	public void setCustomerTextField(JTextField customerTextField) {
		this.customerTextField = customerTextField;
	}


	public JOptionPane getUpdateDetails() {
		return updateDetails;
	}


	public void setUpdateDetails(JOptionPane updateDetails) {
		this.updateDetails = updateDetails;
	}
	
	
	
	


	public JTextField getInfoIDTextField() {
		return infoIDTextField;
	}


	public void setInfoIDTextField(JTextField infoIDTextField) {
		this.infoIDTextField = infoIDTextField;
	}


	public JTextField getInfoRegTextField() {
		return InfoRegTextField;
	}


	public void setInfoRegTextField(JTextField infoRegTextField) {
		InfoRegTextField = infoRegTextField;
	}


	public JTextField getInfoMakeTextField() {
		return InfoMakeTextField;
	}


	public void setInfoMakeTextField(JTextField infoMakeTextField) {
		InfoMakeTextField = infoMakeTextField;
	}


	public JTextField getInfoModelTextField() {
		return InfoModelTextField;
	}


	public void setInfoModelTextField(JTextField infoModelTextField) {
		InfoModelTextField = infoModelTextField;
	}


	public JTextField getInfoChassisTextField() {
		return infoChassisTextField;
	}


	public void setInfoChassisTextField(JTextField infoChassisTextField) {
		this.infoChassisTextField = infoChassisTextField;
	}


	public JTextField getInfoCustomerTextField() {
		return InfoCustomerTextField;
	}


	public void setInfoCustomerTextField(JTextField infoCustomerTextField) {
		InfoCustomerTextField = infoCustomerTextField;
	}


	public void exitListener(ActionListener listenForButton){
	    
		btnExit.addActionListener(listenForButton);

		
		}
	
	
	
	public void deleteListener(ActionListener listenForButton){
	    
		btnDelete.addActionListener(listenForButton);

		
		}
	
	
	public void editButtonListener(ActionListener listenForButton){
	    
		btnEdit.addActionListener(listenForButton);

		
		}
	
	
	public void addNewVehicleListener(ActionListener listenForButton){
	    
		btnAdd.addActionListener(listenForButton);

		
		}
	
	
	public void tableSelecterListener(ListSelectionListener rowSelected){
	    
		
		vehicleTable.getSelectionModel().addListSelectionListener(rowSelected);
		
		}
	
	
	public void addCustomertoVehicle(MouseInputListener listenForTextfield){
	    
		customerTextField.addMouseListener(listenForTextfield);
		
		
		}
	
	
	public void EditCustomertoVehicle(MouseInputListener listenForTextfield){
	    
		InfoCustomerTextField.addMouseListener(listenForTextfield);
		
		
		}
}
