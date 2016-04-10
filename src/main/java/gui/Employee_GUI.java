package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.UIManager;
import java.awt.Color;

public class Employee_GUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane serviceScroller; 
	private JTable employeeTable;
	private JTable timeside;
	private JOptionPane updateDetails;
    private JLabel FirstNamelbl;
    private JLabel Surname;
    private JLabel phoneNumberlbl;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnExit;
    private JTextField nameTextfield;
    private JTextField surnameTextField;
    private JTextField phoneTextField;
    private JPanel InfoEmployeePanel;
    private JLabel lblInfoID;
    private JLabel lblInfoFirstName;
    private JLabel lblInfoSurname;
    private JLabel lblInfoPhone;
    private JTextField InfoIDTextField;
    private JTextField InfoFirstnameTextField;
    private JTextField InfoSurnameTextField;
    private JTextField InfoPhoneTextField;


	public Employee_GUI() {
		setTitle("Employee Section");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel detailsJPanel = new JPanel();
		detailsJPanel.setBorder(new TitledBorder(null, "Employee Details", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		detailsJPanel.setBounds(22, 11, 322, 187);
		detailsJPanel.setLayout(null);
		employeeTable = new JTable();
		serviceScroller = new JScrollPane(employeeTable);
		serviceScroller.setBounds(10, 23, 302, 141);
		detailsJPanel.add(serviceScroller);
		contentPane.add(detailsJPanel);
		
		
		
		
		JPanel informationJPanel = new JPanel();
		informationJPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add New Employee", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		informationJPanel.setBounds(363, 11, 322, 187);
		contentPane.add(informationJPanel);
		informationJPanel.setLayout(null);
		
		FirstNamelbl = new JLabel("First Name");
		FirstNamelbl.setBounds(23, 38, 88, 14);
		informationJPanel.add(FirstNamelbl);
		
		Surname = new JLabel("Surname");
		Surname.setBounds(23, 90, 88, 14);
		informationJPanel.add(Surname);
		
		phoneNumberlbl = new JLabel("Phone Number\r\n");
		phoneNumberlbl.setBounds(23, 137, 88, 14);
		informationJPanel.add(phoneNumberlbl);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(246, 153, 66, 23);
		informationJPanel.add(btnAdd);
		//idTextField.setEditable(false);
		
		nameTextfield = new JTextField();
		nameTextfield.setColumns(10);
		nameTextfield.setBounds(105, 35, 136, 20);
		informationJPanel.add(nameTextfield);
		
		surnameTextField = new JTextField();
		surnameTextField.setColumns(10);
		surnameTextField.setBounds(105, 87, 136, 20);
		informationJPanel.add(surnameTextField);
		
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(105, 134, 136, 20);
		informationJPanel.add(phoneTextField);
		
		
		updateDetails = new JOptionPane();
		updateDetails.setBounds(574, 134, 200,200);	
		informationJPanel.add(updateDetails);
		
		InfoEmployeePanel = new JPanel();
		InfoEmployeePanel.setBorder(new TitledBorder(null, "Employee Information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		InfoEmployeePanel.setBounds(22, 209, 663, 183);
		contentPane.add(InfoEmployeePanel);
		InfoEmployeePanel.setLayout(null);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(554, 103, 66, 23);
		InfoEmployeePanel.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(554, 137, 66, 23);
		InfoEmployeePanel.add(btnExit);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(474, 103, 66, 23);
		InfoEmployeePanel.add(btnEdit);
		
		lblInfoID = new JLabel("ID");
		lblInfoID.setBounds(10, 28, 88, 14);
		InfoEmployeePanel.add(lblInfoID);
		
		lblInfoFirstName = new JLabel("First Name");
		lblInfoFirstName.setBounds(10, 71, 88, 14);
		InfoEmployeePanel.add(lblInfoFirstName);
		
		lblInfoSurname = new JLabel("Surname");
		lblInfoSurname.setBounds(10, 107, 88, 14);
		InfoEmployeePanel.add(lblInfoSurname);
		
		lblInfoPhone = new JLabel("Phone Number\r\n");
		lblInfoPhone.setBounds(10, 146, 88, 14);
		InfoEmployeePanel.add(lblInfoPhone);
		
		InfoIDTextField = new JTextField();
		InfoIDTextField.setEditable(false);
		InfoIDTextField.setColumns(10);
		InfoIDTextField.setBounds(108, 25, 136, 20);
		InfoEmployeePanel.add(InfoIDTextField);
		
		InfoFirstnameTextField = new JTextField();
		InfoFirstnameTextField.setColumns(10);
		InfoFirstnameTextField.setBounds(108, 68, 136, 20);
		InfoEmployeePanel.add(InfoFirstnameTextField);
		
		InfoSurnameTextField = new JTextField();
		InfoSurnameTextField.setColumns(10);
		InfoSurnameTextField.setBounds(108, 104, 136, 20);
		InfoEmployeePanel.add(InfoSurnameTextField);
		
		InfoPhoneTextField = new JTextField();
		InfoPhoneTextField.setColumns(10);
		InfoPhoneTextField.setBounds(108, 140, 136, 20);
		InfoEmployeePanel.add(InfoPhoneTextField);
	}
	
	public JOptionPane getUpdateDetails() {
		return updateDetails;
	}

	public void setUpdateDetails(JOptionPane updateDetails) {
		this.updateDetails = updateDetails;
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




	public JTextField getNameTextfield() {
		return nameTextfield;
	}

	public void setNameTextfield(JTextField nameTextfield) {
		this.nameTextfield = nameTextfield;
	}

	public JTextField getSurnameTextField() {
		return surnameTextField;
	}

	public void setSurnameTextField(JTextField surnameTextField) {
		this.surnameTextField = surnameTextField;
	}

	public JTextField getPhoneTextField() {
		return phoneTextField;
	}

	public void setPhoneTextField(JTextField phoneTextField) {
		this.phoneTextField = phoneTextField;
	}

	
	
	

	public JTextField getInfoIDTextField() {
		return InfoIDTextField;
	}

	public void setInfoIDTextField(JTextField infoIDTextField) {
		InfoIDTextField = infoIDTextField;
	}

	public JTextField getInfoFirstnameTextField() {
		return InfoFirstnameTextField;
	}

	public void setInfoFirstnameTextField(JTextField infoFirstnameTextField) {
		InfoFirstnameTextField = infoFirstnameTextField;
	}

	public JTextField getInfoSurnameTextField() {
		return InfoSurnameTextField;
	}

	public void setInfoSurnameTextField(JTextField infoSurnameTextField) {
		InfoSurnameTextField = infoSurnameTextField;
	}

	public JTextField getInfoPhoneTextField() {
		return InfoPhoneTextField;
	}

	public void setInfoPhoneTextField(JTextField infoPhoneTextField) {
		InfoPhoneTextField = infoPhoneTextField;
	}

	public JTable getEmployeeTable() {
		return employeeTable;
	}

	public void setEmployeeTable(JTable employeeTable) {
		this.employeeTable = employeeTable;
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
	
	
	public void addNewEmployeeListener(ActionListener listenForButton){
	    
		btnAdd.addActionListener(listenForButton);

		
		}
	
	
	public void tableSelecterListener(ListSelectionListener rowSelected){
	    
		
		employeeTable.getSelectionModel().addListSelectionListener(rowSelected);
		
		}

}
