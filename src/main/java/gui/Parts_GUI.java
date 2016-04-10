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

public class Parts_GUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane partsScroller; 
	private JTable partsTable;
	private JOptionPane updateDetails;
    private JLabel serviceNameJLabel;
    private JLabel lblServiceTime;
    private JLabel lblServicePrice;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnExit;
    private JTextField nameTextfield;
    private JTextField descriptionTextField;
    private JTextField priceTextField;
    private JTextField InfoPartNameTextField;
    private JTextField InfoPartDescTextField;
    private JTextField InfoPartPriceTextField;
    private JLabel PartInfoIDLbl;
    private JTextField PartInfoIDTextField;


	public Parts_GUI() {
		setTitle("Parts Section");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel detailsJPanel = new JPanel();
		detailsJPanel.setBorder(new TitledBorder(null, "Parts Details", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		detailsJPanel.setBounds(22, 11, 322, 215);
		detailsJPanel.setLayout(null);
		
		partsTable = new JTable(/*data, columns*/);
		partsScroller = new JScrollPane(partsTable);
		partsScroller.setBounds(10, 23, 302, 181);
		detailsJPanel.add(partsScroller);
		contentPane.add(detailsJPanel);
		
		
		
		
		JPanel informationJPanel = new JPanel();
		informationJPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add Part", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		informationJPanel.setBounds(363, 11, 322, 215);
		contentPane.add(informationJPanel);
		informationJPanel.setLayout(null);
		
		serviceNameJLabel = new JLabel("Part Name");
		serviceNameJLabel.setBounds(23, 34, 88, 14);
		informationJPanel.add(serviceNameJLabel);
		
		lblServiceTime = new JLabel("Part Description");
		lblServiceTime.setBounds(23, 84, 88, 14);
		informationJPanel.add(lblServiceTime);
		
		lblServicePrice = new JLabel("Part Price");
		lblServicePrice.setBounds(23, 132, 88, 14);
		informationJPanel.add(lblServicePrice);
		
		nameTextfield = new JTextField();
		nameTextfield.setColumns(10);
		nameTextfield.setBounds(115, 31, 136, 20);
		informationJPanel.add(nameTextfield);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(115, 81, 136, 20);
		informationJPanel.add(descriptionTextField);
		
		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		priceTextField.setBounds(115, 129, 136, 20);
		informationJPanel.add(priceTextField);
		
		
		updateDetails = new JOptionPane();
		updateDetails.setBounds(574, 134, 200,200);	
		informationJPanel.add(updateDetails);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(225, 181, 66, 23);
		informationJPanel.add(btnAdd);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Part Information", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(22, 237, 663, 155);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(503, 82, 75, 23);
		panel.add(btnDelete);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(415, 82, 78, 23);
		panel.add(btnEdit);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(503, 116, 78, 23);
		panel.add(btnExit);
		
		JLabel lblInfoPartName = new JLabel("Part Name");
		lblInfoPartName.setBounds(10, 55, 88, 14);
		panel.add(lblInfoPartName);
		
		JLabel lblInfoPartDesc = new JLabel("Part Description");
		lblInfoPartDesc.setBounds(10, 86, 88, 14);
		panel.add(lblInfoPartDesc);
		
		JLabel lblInfoPartPrice = new JLabel("Part Price");
		lblInfoPartPrice.setBounds(10, 125, 88, 14);
		panel.add(lblInfoPartPrice);
		
		InfoPartNameTextField = new JTextField();
		InfoPartNameTextField.setColumns(10);
		InfoPartNameTextField.setBounds(110, 52, 136, 20);
		panel.add(InfoPartNameTextField);
		
		InfoPartDescTextField = new JTextField();
		InfoPartDescTextField.setColumns(10);
		InfoPartDescTextField.setBounds(110, 83, 136, 20);
		panel.add(InfoPartDescTextField);
		
		InfoPartPriceTextField = new JTextField();
		InfoPartPriceTextField.setColumns(10);
		InfoPartPriceTextField.setBounds(110, 117, 136, 20);
		panel.add(InfoPartPriceTextField);
		
		PartInfoIDLbl = new JLabel("Part ID");
		PartInfoIDLbl.setBounds(10, 22, 88, 14);
		panel.add(PartInfoIDLbl);
		
		PartInfoIDTextField = new JTextField();
		PartInfoIDTextField.setEditable(false);
		PartInfoIDTextField.setColumns(10);
		PartInfoIDTextField.setBounds(110, 19, 136, 20);
		panel.add(PartInfoIDTextField);
	}
	
	public JOptionPane getUpdateDetails() {
		return updateDetails;
	}

	public void setUpdateDetails(JOptionPane updateDetails) {
		this.updateDetails = updateDetails;
	}

	public JTextField getDescriptionTextField() {
		return descriptionTextField;
	}

	public void setDescriptionTextField(JTextField descriptionTextField) {
		this.descriptionTextField = descriptionTextField;
	}

	public JTable getPartsTable() {
		return partsTable;
	}

	public void setPartsTable(JTable partsTable) {
		this.partsTable = partsTable;
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

	public JTextField getdescriptionTextField() {
		return descriptionTextField;
	}

	public void setdescriptionTextField(JTextField descriptionTextField) {
		this.descriptionTextField = descriptionTextField;
	}

	public JTextField getPriceTextField() {
		return priceTextField;
	}

	public void setPriceTextField(JTextField priceTextField) {
		this.priceTextField = priceTextField;
	}
	
	

	public JTextField getInfoPartNameTextField() {
		return InfoPartNameTextField;
	}

	public void setInfoPartNameTextField(JTextField infoPartNameTextField) {
		InfoPartNameTextField = infoPartNameTextField;
	}

	public JTextField getInfoPartDescTextField() {
		return InfoPartDescTextField;
	}

	public void setInfoPartDescTextField(JTextField infoPartDescTextField) {
		InfoPartDescTextField = infoPartDescTextField;
	}

	public JTextField getInfoPartPriceTextField() {
		return InfoPartPriceTextField;
	}

	public void setInfoPartPriceTextField(JTextField infoPartPriceTextField) {
		InfoPartPriceTextField = infoPartPriceTextField;
	}
	
	

	public JTextField getPartInfoIDTextField() {
		return PartInfoIDTextField;
	}

	public void setPartInfoIDTextField(JTextField partInfoIDTextField) {
		PartInfoIDTextField = partInfoIDTextField;
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
	
	
	public void addNewPartsListener(ActionListener listenForButton){
	    
		btnAdd.addActionListener(listenForButton);

		
		}
	
	
	public void tableSelecterListener(ListSelectionListener rowSelected){
	    
		
		partsTable.getSelectionModel().addListSelectionListener(rowSelected);
		
		}
}

