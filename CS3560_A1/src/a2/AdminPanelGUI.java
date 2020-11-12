/***************************************************************
* file: CS3560_A1
* author: Ricky Fok
* class: Answer
*
* assignment: A2
* date last modified: 11/12/2020
*
* purpose: SINGLETON IMPLEMENTATION so that this can only be created once.
* 		   GUI implementation for the Admin Console
****************************************************************/
package a2;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

abstract class AdminPanelGUI implements GUI_Interface 
{
	protected JFrame admnConsole;
	protected JTree tree;
	protected JButton btnOpenUserView;
	protected JButton btnAddUser;
	protected JButton btnAddGroup;
	protected JButton btnShowUserTotal;
	protected JButton btnShowPositivePercentage;
	protected JButton btnShowMessagesTotal;
	protected JButton btnShowGroupTotal;
	protected JTextArea txtrTextareaUserid;
	protected JTextArea txtrTextareaGroupId;
	protected JTextArea txtrTextareaButton;
	protected JLabel lblNewLabel;
	protected DefaultTreeModel model;
	
	protected DefaultMutableTreeNode root;
	
	protected static final String USERID_TEXT = "TextArea - UserID";
	protected static final String GROUPID_TEXT = "TextArea- Group ID";
	

	public AdminPanelGUI() 
	{
		root = new DefaultMutableTreeNode("Default Root");
		initialize();
	}
	
	public void initialize() {
		admnConsole = new JFrame();
		admnConsole.setTitle("Admin Panel");
		admnConsole.setBackground(Color.BLACK);
		admnConsole.setBounds(100, 100, 1003, 567);
		admnConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admnConsole.getContentPane().setLayout(null);
		
		tree = new JTree(root);
		model = (DefaultTreeModel)tree.getModel();	//allows us to invoke reload() method so we can update JTree
		tree.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tree.setBounds(21, 21, 223, 433);
		admnConsole.getContentPane().add(tree);
		
		btnOpenUserView = new JButton("Open User View");
		btnOpenUserView.setBounds(349, 149, 517, 75);
		admnConsole.getContentPane().add(btnOpenUserView);
		
		btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(618, 21, 248, 59);
		admnConsole.getContentPane().add(btnAddUser);
		
		btnAddGroup = new JButton("Add Group");
		btnAddGroup.setBounds(618, 86, 248, 59);
		admnConsole.getContentPane().add(btnAddGroup);
		
		btnShowUserTotal = new JButton("Show User Total");
		btnShowUserTotal.setBounds(349, 330, 248, 59);
		admnConsole.getContentPane().add(btnShowUserTotal);
		
		btnShowPositivePercentage = new JButton("Show Positive %");
		btnShowPositivePercentage.setBounds(618, 395, 248, 59);
		admnConsole.getContentPane().add(btnShowPositivePercentage);
		
		btnShowMessagesTotal = new JButton("Show Messages Total");
		btnShowMessagesTotal.setBounds(349, 395, 248, 59);
		admnConsole.getContentPane().add(btnShowMessagesTotal);
		
		btnShowGroupTotal = new JButton("Show Group Total");
		btnShowGroupTotal.setBounds(618, 330, 248, 59);
		admnConsole.getContentPane().add(btnShowGroupTotal);
		
		txtrTextareaUserid = new JTextArea();
		txtrTextareaUserid.setFont(new Font("Monospaced", Font.PLAIN, 21));
		txtrTextareaUserid.setText(USERID_TEXT);
		txtrTextareaUserid.setBounds(349, 21, 248, 59);
		admnConsole.getContentPane().add(txtrTextareaUserid);
		
		txtrTextareaGroupId = new JTextArea();
		txtrTextareaGroupId.setFont(new Font("Monospaced", Font.PLAIN, 21));
		txtrTextareaGroupId.setText("TextArea- Group ID");
		txtrTextareaGroupId.setBounds(349, 86, 248, 59);
		admnConsole.getContentPane().add(txtrTextareaGroupId);
		
		txtrTextareaButton = new JTextArea();
		txtrTextareaButton.setFont(new Font("Monospaced", Font.PLAIN, 25));
		txtrTextareaButton.setText("TextArea - Button Outputs");
		txtrTextareaButton.setBounds(402, 261, 415, 59);
		admnConsole.getContentPane().add(txtrTextareaButton);
		
		lblNewLabel = new JLabel("Please Select Node");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(21, 466, 223, 26);
		admnConsole.getContentPane().add(lblNewLabel);
		
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() 
		{
            @Override
            public void valueChanged(TreeSelectionEvent e) 
            {
            	if(e.getNewLeadSelectionPath() != null)	//to make sure you are selecting some node and not nothing
            	{
                DefaultMutableTreeNode selectedNode = getLastPath();
                lblNewLabel.setText(selectedNode.getUserObject().toString());	//setting textbox to string of object hovering
            	}
            }
        });
	}
	
	public DefaultMutableTreeNode getLastPath()
	{
		return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	}
	
	public JFrame getAdmnConsole()
	{
		return admnConsole;
	}
	
	public JTree getTree()
	{
		return tree;
	}
	
	public JButton getOpenUserView()
	{
		return btnOpenUserView;
	}
	
	public JButton getBtnAddUser() {
		return btnAddUser;
	}

	public void setBtnAddUser(JButton btnAddUser) {
		this.btnAddUser = btnAddUser;
	}

	public JButton getBtnAddGroup() {
		return btnAddGroup;
	}

	public void setBtnAddGroup(JButton btnAddGroup) {
		this.btnAddGroup = btnAddGroup;
	}

	public JButton getBtnShowUserTotal() {
		return btnShowUserTotal;
	}

	public JButton getBtnShowPositivePercentage() {
		return btnShowPositivePercentage;
	}

	public JButton getBtnShowMessagesTotal() {
		return btnShowMessagesTotal;
	}

	public JButton getBtnShowGroupTotal() {
		return btnShowGroupTotal;
	}

	public JTextArea getTxtrTextareaUserid() {
		return txtrTextareaUserid;
	}

	public JTextArea getTxtrTextareaGroupId() {
		return txtrTextareaGroupId;
	}

	public JTextArea getTxtrTextareaButton() {
		return txtrTextareaButton;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	
	public void setTree(JTree tree) {
		this.tree = tree;
	}
	
	public void setTxtrTextareaUserid(JTextArea txtrTextareaUserid) {
		this.txtrTextareaUserid = txtrTextareaUserid;
	}
	
	public void setTxtrTextareaGroupId(JTextArea txtrTextareaGroupId) {
		this.txtrTextareaGroupId = txtrTextareaGroupId;
	}
	
	public void setTxtrTextareaButton(JTextArea txtrTextareaButton) {
		this.txtrTextareaButton = txtrTextareaButton;
	}

	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public DefaultTreeModel getModel() {
		return model;
	}

//	public void update(TreeEntry obj)
//	{
//		model.reload(root);
//	}
	
}
