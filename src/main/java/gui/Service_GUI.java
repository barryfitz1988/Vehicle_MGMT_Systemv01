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

public class Service_GUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane serviceScroller; 
	private JTable serviceTable;
	private JOptionPane updateDetails;
    private JLabel serviceNameJLabel;
    private JLabel lblServiceTime;
    private JLabel lblServicePrice;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnExit;
    private JTextField nameTextfield;
    private JTextField timeTextField;
    private JTextField priceTextField;
    private JPanel ServiceInfoJPanel;
    private JLabel lblInfoServiceID;
    private JLabel lblInfoServiceName;
    private JLabel lblInfoServiceTime;
    private JLabel lblInfoServicePrice;
    private JTextField InfoServiceIDTextField;
    private JTextField InfoServiceNameTextField;
    private JTextField InfoServiceTimeTextfield;
    private JTextField InfoServicePriceTextField;


	public Service_GUI() {
		setTitle("Service Section");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel detailsJPanel = new JPanel();
		detailsJPanel.setBorder(new TitledBorder(null, "Service Details", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		detailsJPanel.setBounds(22, 11, 322, 181);
		detailsJPanel.setLayout(null);
		serviceTable = new JTable();
		serviceScroller = new JScrollPane(serviceTable);
		serviceScroller.setBounds(10, 23, 302, 147);
		detailsJPanel.add(serviceScroller);
		contentPane.add(detailsJPanel);
		
		
		
		
		JPanel informationJPanel = new JPanel();
		informationJPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add Service", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		informationJPanel.setBounds(363, 11, 322, 181);
		contentPane.add(informationJPanel);
		informationJPanel.setLayout(null);
		
		serviceNameJLabel = new JLabel("Service Name");
		serviceNameJLabel.setBounds(23, 40, 88, 14);
		informationJPanel.add(serviceNameJLabel);
		
		lblServiceTime = new JLabel("Service Time");
		lblServiceTime.setBounds(23, 84, 88, 14);
		informationJPanel.add(lblServiceTime);
		
		lblServicePrice = new JLabel("Service Price");
		lblServicePrice.setBounds(23, 125, 88, 14);
		informationJPanel.add(lblServicePrice);
		
		nameTextfield = new JTextField();
		nameTextfield.setColumns(10);
		nameTextfield.setBounds(105, 37, 136, 20);
		informationJPanel.add(nameTextfield);
		
		timeTextField = new JTextField();
		timeTextField.setColumns(10);
		timeTextField.setBounds(105, 81, 136, 20);
		informationJPanel.add(timeTextField);
		
		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		priceTextField.setBounds(105, 122, 136, 20);
		informationJPanel.add(priceTextField);
		
		updateDetails = new JOptionPane();
		updateDetails.setBounds(574, 134, 200,200);	
		informationJPanel.add(updateDetails);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(226, 152, 66, 23);
		informationJPanel.add(btnAdd);
		
		ServiceInfoJPanel = new JPanel();
		ServiceInfoJPanel.setBorder(new TitledBorder(null, "Service Information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		ServiceInfoJPanel.setBounds(22, 203, 663, 189);
		contentPane.add(ServiceInfoJPanel);
		ServiceInfoJPanel.setLayout(null);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(554, 155, 66, 23);
		ServiceInfoJPanel.add(btnExit);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(483, 119, 66, 23);
		ServiceInfoJPanel.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(554, 119, 66, 23);
		ServiceInfoJPanel.add(btnDelete);
		
		lblInfoServiceID = new JLabel("Service ID");
		lblInfoServiceID.setBounds(10, 26, 88, 14);
		ServiceInfoJPanel.add(lblInfoServiceID);
		
		lblInfoServiceName = new JLabel("Service Name");
		lblInfoServiceName.setBounds(10, 70, 88, 14);
		ServiceInfoJPanel.add(lblInfoServiceName);
		
		lblInfoServiceTime = new JLabel("Service Time");
		lblInfoServiceTime.setBounds(10, 112, 88, 14);
		ServiceInfoJPanel.add(lblInfoServiceTime);
		
		lblInfoServicePrice = new JLabel("Service Price");
		lblInfoServicePrice.setBounds(10, 159, 88, 14);
		ServiceInfoJPanel.add(lblInfoServicePrice);
		
		InfoServiceIDTextField = new JTextField();
		InfoServiceIDTextField.setEditable(false);
		InfoServiceIDTextField.setColumns(10);
		InfoServiceIDTextField.setBounds(136, 23, 136, 20);
		ServiceInfoJPanel.add(InfoServiceIDTextField);
		
		InfoServiceNameTextField = new JTextField();
		InfoServiceNameTextField.setColumns(10);
		InfoServiceNameTextField.setBounds(136, 67, 136, 20);
		ServiceInfoJPanel.add(InfoServiceNameTextField);
		
		InfoServiceTimeTextfield = new JTextField();
		InfoServiceTimeTextfield.setColumns(10);
		InfoServiceTimeTextfield.setBounds(136, 109, 136, 20);
		ServiceInfoJPanel.add(InfoServiceTimeTextfield);
		
		InfoServicePriceTextField = new JTextField();
		InfoServicePriceTextField.setColumns(10);
		InfoServicePriceTextField.setBounds(136, 156, 136, 20);
		ServiceInfoJPanel.add(InfoServicePriceTextField);
	}
	
	public JOptionPane getUpdateDetails() {
		return updateDetails;
	}

	public void setUpdateDetails(JOptionPane updateDetails) {
		this.updateDetails = updateDetails;
	}

	public JTable getServiceTable() {
		return serviceTable;
	}

	public void setServiceTable(JTable serviceTable) {
		this.serviceTable = serviceTable;
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


	
	
	public JTextField getInfoServiceIDTextField() {
		return InfoServiceIDTextField;
	}

	public void setInfoServiceIDTextField(JTextField infoServiceIDTextField) {
		InfoServiceIDTextField = infoServiceIDTextField;
	}

	public JTextField getInfoServiceNameTextField() {
		return InfoServiceNameTextField;
	}

	public void setInfoServiceNameTextField(JTextField infoServiceNameTextField) {
		InfoServiceNameTextField = infoServiceNameTextField;
	}

	public JTextField getInfoServiceTimeTextfield() {
		return InfoServiceTimeTextfield;
	}

	public void setInfoServiceTimeTextfield(JTextField infoServiceTimeTextfield) {
		InfoServiceTimeTextfield = infoServiceTimeTextfield;
	}

	public JTextField getInfoServicePriceTextField() {
		return InfoServicePriceTextField;
	}

	public void setInfoServicePriceTextField(JTextField infoServicePriceTextField) {
		InfoServicePriceTextField = infoServicePriceTextField;
	}

	public JTextField getNameTextfield() {
		return nameTextfield;
	}

	public void setNameTextfield(JTextField nameTextfield) {
		this.nameTextfield = nameTextfield;
	}

	public JTextField getTimeTextField() {
		return timeTextField;
	}

	public void setTimeTextField(JTextField timeTextField) {
		this.timeTextField = timeTextField;
	}

	public JTextField getPriceTextField() {
		return priceTextField;
	}

	public void setPriceTextField(JTextField priceTextField) {
		this.priceTextField = priceTextField;
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
	
	
	public void addNewServiceListener(ActionListener listenForButton){
	    
		btnAdd.addActionListener(listenForButton);

		
		}
	
	
	public void tableSelecterListener(ListSelectionListener rowSelected){
	    
		
		serviceTable.getSelectionModel().addListSelectionListener(rowSelected);
		
		}

}
