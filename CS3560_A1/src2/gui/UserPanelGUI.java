package gui;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import treeobj.TreeEntry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class UserPanelGUI implements GUI_Interface
{
	protected JFrame frmUserPanel;
	protected JList<String> listFollowingTemp;
	protected JScrollPane listFollowing;
	protected DefaultListModel<String> followingModel;
	protected JTextArea txtrUserFollow;
	protected JButton btnFollowUser;
	protected JTextArea txtrTweetMsg;
	protected JButton btnButtonPost;
	protected DefaultListModel<String> feedModel;
	protected DefaultListModel<String> feedModelFollowing;
	protected JList<String> listFeedTemp;
	protected JScrollPane listFeed;
	protected JButton btnClosePanel;
	protected JLabel lblLastUpdated;
	
	protected static final String TWEET_TEXT = "Text Area - Tweet Message";
	protected static final String USERID_TEXT = "Text Area - UserID";
	
	public UserPanelGUI() 
	{
		initialize();
	}

	public void initialize() //starts up the panel
	{
		frmUserPanel = new JFrame();
		frmUserPanel.setTitle("User Panel");
		frmUserPanel.setBounds(100, 100, 710, 700);
		frmUserPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUserPanel.getContentPane().setLayout(null);
		
		followingModel = new DefaultListModel<String>();
		followingModel.addElement("Currently Following");
		listFollowingTemp = new JList<>(followingModel);
		listFollowing = new JScrollPane(listFollowingTemp);
		listFollowing.setBounds(21, 107, 638, 170);
		frmUserPanel.getContentPane().add(listFollowing);
		
		txtrUserFollow = new JTextArea();
		txtrUserFollow.setFont(new Font("Monospaced", Font.PLAIN, 20));
		txtrUserFollow.setText(USERID_TEXT);
		txtrUserFollow.setBounds(21, 20, 292, 66);
		frmUserPanel.getContentPane().add(txtrUserFollow);
		
		btnFollowUser = new JButton("Follow User");
		btnFollowUser.setBounds(334, 20, 208, 66);
		frmUserPanel.getContentPane().add(btnFollowUser);
		
		txtrTweetMsg = new JTextArea();
		txtrTweetMsg.setText(TWEET_TEXT);
		txtrTweetMsg.setBounds(21, 298, 380, 66);
		frmUserPanel.getContentPane().add(txtrTweetMsg);
		
		btnButtonPost = new JButton("Post Tweet");
		btnButtonPost.setBounds(439, 298, 220, 66);
		frmUserPanel.getContentPane().add(btnButtonPost);
		
		feedModel = new DefaultListModel<String>();
		feedModelFollowing = new DefaultListModel<String>();
		feedModel.addElement("Twitter Feed");
		listFeedTemp = new JList<>(feedModel);
		listFeed = new JScrollPane(listFeedTemp);
		listFeed.setBounds(21, 371, 638, 170);
		frmUserPanel.getContentPane().add(listFeed);
		
		btnClosePanel = new JButton("Close Panel");
		btnClosePanel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClosePanel.setBounds(563, 39, 96, 35);
		frmUserPanel.getContentPane().add(btnClosePanel);
		
		lblLastUpdated = new JLabel("Last Updated:");
		lblLastUpdated.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLastUpdated.setBounds(21, 600, 160, 25);
		frmUserPanel.getContentPane().add(lblLastUpdated);
	}
	
	public JFrame getFrmUserPanel() //returns panel
	{
		return frmUserPanel;
	}
	
	public void setFrmUserPanel(String name) //sets panel name
	{
		frmUserPanel.setTitle(name);
	}

	public JList<String> getListFollowing() //returns list of followers
	{
		return listFollowingTemp;
	}

	public JTextArea getTxtrUserFollow() //returns text area for user's following
	{
		return txtrUserFollow;
	}

	public JButton getBtnFollowUser() //returns button for follow user
	{
		return btnFollowUser;
	}

	public JTextArea getTxtrTweetMsg() //returns text area for tweet
	{
		return txtrTweetMsg;
	}

	public JButton getBtnButtonPost() //return button for posting
	{
		return btnButtonPost;
	}

	public JList<String> getListFeed() //returns feed
	{
		return listFeedTemp;
	}
	
	public DefaultListModel<String> getFeedModelFollowing()	//returns the feedModel
	{
		return feedModelFollowing;
	}
	
	public void setLblLastUpdated(long time)	//sets label time
	{
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		lblLastUpdated.setText("Last Updated: "+ sdf.format(new Date(time)));
	}
	
	public void update(TreeEntry user)	// leaves update up to implementations
	{
		
	}
}
