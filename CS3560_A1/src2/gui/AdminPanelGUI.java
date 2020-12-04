package gui;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import treeobj.*;
import visitors.*;

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
	protected JLabel lblNewLabel;	//label that shows selected entity in tree
	protected DefaultTreeModel model;
	
	protected JButton btnUserVerification;
	protected JButton btnLastUpdatedUser;
	
	protected DefaultMutableTreeNode root;
	
	protected static final String USERID_TEXT = "TextArea - UserID";
	protected static final String GROUPID_TEXT = "TextArea- Group ID";
	protected static final String OPENUSERVIEW_TEXT = "Open User View";
	protected static final String ADDUSER_TEXT = "Add User";
	protected static final String ADDGROUP_TEXT = "Add Group";
	protected static final String USERTOTAL_TEXT = "Show User Total";
	protected static final String GROUPTOTAL_TEXT = "Show Group Total";
	protected static final String MESSAGESTOTAL_TEXT = "Show Messages Total";
	protected static final String POSITVEPERC_TEXT = "Show Positive %";
	
	protected static final String VERIFY_TEXT = "Verify";
	protected static final String LASTUPDATED_TEXT = "L.U";
	
	public AdminPanelGUI() 
	{
		root = new DefaultMutableTreeNode("Default Root");
		initialize();
	}
	
	public void initialize() //creates panel
	{
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
		
		btnOpenUserView = new JButton(OPENUSERVIEW_TEXT);
		btnOpenUserView.setBounds(349, 149, 517, 75);
		admnConsole.getContentPane().add(btnOpenUserView);
		
		btnAddUser = new JButton(ADDUSER_TEXT);
		btnAddUser.setBounds(618, 21, 248, 59);
		admnConsole.getContentPane().add(btnAddUser);
		
		btnAddGroup = new JButton(ADDGROUP_TEXT );
		btnAddGroup.setBounds(618, 86, 248, 59);
		admnConsole.getContentPane().add(btnAddGroup);
		
		btnShowUserTotal = new JButton(USERTOTAL_TEXT);
		btnShowUserTotal.setBounds(349, 330, 248, 59);
		admnConsole.getContentPane().add(btnShowUserTotal);
		
		btnShowPositivePercentage = new JButton(POSITVEPERC_TEXT);
		btnShowPositivePercentage.setBounds(618, 395, 248, 59);
		admnConsole.getContentPane().add(btnShowPositivePercentage);
		
		btnShowMessagesTotal = new JButton(MESSAGESTOTAL_TEXT);
		btnShowMessagesTotal.setBounds(349, 395, 248, 59);
		admnConsole.getContentPane().add(btnShowMessagesTotal);
		
		btnShowGroupTotal = new JButton(GROUPTOTAL_TEXT);
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
		
		btnUserVerification = new JButton(VERIFY_TEXT);
		btnUserVerification.setBounds(880, 21, 70, 100);
		admnConsole.getContentPane().add(btnUserVerification);
		
		btnLastUpdatedUser = new JButton(LASTUPDATED_TEXT);
		btnLastUpdatedUser.setBounds(880, 330, 70, 100);
		admnConsole.getContentPane().add(btnLastUpdatedUser);
		
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() 
		{
            @Override
            public void valueChanged(TreeSelectionEvent e) 
            {
            	if(e.getNewLeadSelectionPath() != null)	//to make sure you are selecting some node and not nothing
            	{
            		String temp = "";
	                DefaultMutableTreeNode selectedNode = getLastPath();
	                if(selectedNode.getUserObject() instanceof TwitterUserGroup)
	                {
	                	TwitterUserGroup tempUG = (TwitterUserGroup)selectedNode.getUserObject();
	                	temp = selectedNode.getUserObject().toString() + ": " + tempUG.getStrTime();
	                	lblNewLabel.setText(temp);	//setting textbox to string of object hovering
	                }
	                else if(selectedNode.getUserObject() instanceof TwitterUser)
	                {
	                	TwitterUser tempU = (TwitterUser)selectedNode.getUserObject();
	                	temp = selectedNode.getUserObject().toString() + ": " + tempU.getStrTime();
	                	lblNewLabel.setText(temp);	//setting textbox to string of object hovering
	                }

	                
            	}
            }
        });
	}
	
	public DefaultMutableTreeNode getLastPath()	//returns selected tree item
	{
		return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	}
	
	public JFrame getAdmnConsole()	//returns panel
	{
		return admnConsole;
	}
	
	public JTree getTree()	//returns tree 
	{
		return tree;
	}
	
	public JButton getOpenUserView()	//returns button to open user view
	{
		return btnOpenUserView;
	}
	
	public JButton getBtnAddUser() //returns button to add user
	{
		return btnAddUser;
	}

	public void setBtnAddUser(JButton btnAddUser) {
		this.btnAddUser = btnAddUser;
	}

	public JButton getBtnAddGroup() //returns button to add group
	{
		return btnAddGroup;
	}

	public JButton getBtnShowUserTotal()	//returns button to show user total
	{
		return btnShowUserTotal;
	}

	public JButton getBtnShowPositivePercentage() //returns button to show positive message percentage
	{
		return btnShowPositivePercentage;
	}

	public JButton getBtnShowMessagesTotal() ////returns button to show total # of messages
	{
		return btnShowMessagesTotal;
	}

	public JButton getBtnShowGroupTotal() //returns button to show total # of groups
	{
		return btnShowGroupTotal;
	}

	public JTextArea getTxtrTextareaUserid() //returns text area for user id
	{
		return txtrTextareaUserid;
	}

	public JTextArea getTxtrTextareaGroupId() //returns text area for user group id
	{
		return txtrTextareaGroupId;
	}

	public JTextArea getTxtrTextareaButton()	//returns text area for text display area (for the 4 btm buttons)
	{
		return txtrTextareaButton;
	}

	public JLabel getLblNewLabel()	//returns new label
	{
		return lblNewLabel;
	}
	
	public void setTree(JTree tree) //sets the JTree to tree
	{
		this.tree = tree;
	}
	
	public void setTxtrTextareaUserid(JTextArea txtrTextareaUserid) //sets User ID Text Area
	{
		this.txtrTextareaUserid = txtrTextareaUserid;
	}
	
	public void setTxtrTextareaGroupId(JTextArea txtrTextareaGroupId) //sets Group ID Text Area
	{
		this.txtrTextareaGroupId = txtrTextareaGroupId;
	}
	
	public void setTxtrTextareaButton(JTextArea txtrTextareaButton) //sets Text Display Area
	{
		this.txtrTextareaButton = txtrTextareaButton;
	}

	public DefaultMutableTreeNode getRoot() //returns root
	{
		return root;
	}

	public DefaultTreeModel getModel() //returns model
	{
		return model;
	}
	
	public JButton getBtnUserVerification()	//returns Verify Button
	{
		return btnUserVerification;
	}
	
	public void setBtnUserVerification(JButton temp)
	{
		btnUserVerification = temp;
	}
	
	public JButton getBtnLastUpdatedUser()
	{
		return btnLastUpdatedUser;
	}
	
	public void setBtnLastUpdatedUser(JButton temp)
	{
		btnLastUpdatedUser = temp;
	}

	public void update(TreeEntry obj)	//update panel MAY OR MAY NOT USE
	{
		if(obj instanceof UserGroup)
		{
			model.reload(root);	
		}
	}
	
}
