package Library;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * The Dialog to add new user. Can enter user infomation here.
 * @author Sen Li
 * add 2014.8.14
 * edit by Li Huang 2014.8.14: change its base class to JDialog. 
 *   Set it default visible = false .
 */
public class FrmAddUsers extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton addUser, close;
	private PanelUserInfo infoPanel;
	private boolean bool_isActionAdd;

	FrmAddUsers() {
		
		this.setTitle("Add Users");

		addUser = new JButton("Add");
		addUser.setBounds(75, 500, 100, 30);
		close = new JButton("Cancel");
		close.setBounds(325, 500, 100, 30);
		infoPanel = new PanelUserInfo();
		infoPanel.setBounds(20, 20, 400, 400);
	
		this.add(addUser);
		this.add(close);
		this.add(infoPanel);

		this.setSize(500, 600);
		this.setLayout(null);
		//this.setVisible(true); //del by Li Huang 2014.8.14

		addUser.addMouseListener(new MouseAdapter() // add new user
		{
			public void mouseClicked(MouseEvent me) {

				Validator validator = new Validator();
				if (validator.isUserIdValid(infoPanel.getIdText())
						&& validator.isUserNameValid(infoPanel.getNameText())
						&& validator.isUserPasswordValid(infoPanel.getPasswordText())
						&& validator.isUserPhoneNoValid(infoPanel.getPhoneNoText())

				) {
					bool_isActionAdd = true;
					JOptionPane.showMessageDialog(FrmAddUsers.this,
							"New user added.", "OK", JOptionPane.PLAIN_MESSAGE);
					//FrmAddUsers.this.setVisible(false); //modify by Li Huang 2014.8.14: dirctedly dispose this window
					FrmAddUsers.this.dispose();

				}

				else {
					JOptionPane.showMessageDialog(FrmAddUsers.this,
							"Invaild user information!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}// mouse clicked
		});

		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				bool_isActionAdd = false;
				//FrmAddUsers.this.setVisible(false); //modify by Li Huang 2014.8.14: dirctedly dispose this window
				FrmAddUsers.this.dispose();
			}
		});
	}

	public static void main(String[] args) {
		new FrmAddUsers();
	}

	public boolean isActionAdd() {

		return bool_isActionAdd;

	}

	public User getUser() { //return a user with info in the panel.
		User tempUser = new User();
		infoPanel.WriteTo(tempUser);
		return tempUser;
	}

}
