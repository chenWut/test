package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterInterface {

	JFrame jf = new JFrame("ע����桪������ύ����Զ���ת...");

	final int WIDTH = 500;
	final int HEIGHT = 400;

	// ��װ��ͼ
	public void init() {

		// ��װ��¼���Ԫ��
		Box vBox = Box.createVerticalBox();

		// ���ô�����ص�����
		jf.setBounds(500, 200, WIDTH, HEIGHT);
		jf.setResizable(true);

		// ��װ�û���
		Box uBox = Box.createHorizontalBox();
		JLabel uLabel = new JLabel("�û���:");
		JTextField uField = new JTextField(15);

		uBox.add(uLabel);
		uBox.add(Box.createHorizontalStrut(20));
		uBox.add(uField);

		// ��װ����
		Box pBox = Box.createHorizontalBox();
		JLabel pLabel = new JLabel("��    ��:");
		JTextField pField = new JTextField(10);

		pBox.add(pLabel);
		pBox.add(Box.createHorizontalStrut(20));
		pBox.add(pField);

		//��װ�Ա�
		Box gBox = Box.createHorizontalBox();
		JLabel gLabel = new JLabel("��    ��:");
		JRadioButton manBtn = new JRadioButton("��",true);
		JRadioButton femanBtn = new JRadioButton("Ů",false);
		
		//ʵ�ֵ�ѡ��Ч��
		ButtonGroup bg = new ButtonGroup();
		bg.add(manBtn);
		bg.add(femanBtn);
		
		
		gBox.add(gLabel);
		gBox.add(Box.createHorizontalStrut(20));
		gBox.add(manBtn);
		gBox.add(femanBtn);
		gBox.add(Box.createHorizontalStrut(20));

		
		// ��װ��ַ
		Box addressBox = Box.createHorizontalBox();
		JLabel addressLabel = new JLabel("��	    ַ:");
		JTextField addressField = new JTextField(10);

		addressBox.add(addressLabel);
		addressBox.add(Box.createHorizontalStrut(20));
		addressBox.add(addressField);

		// ��װ�ֻ���
		Box tBox = Box.createHorizontalBox();
		JLabel tLabel = new JLabel("�ֻ���:");
		JTextField tField = new JTextField(10);

		tBox.add(tLabel);
		tBox.add(Box.createHorizontalStrut(20));
		tBox.add(tField);

		// ��װ��ť
		Box btnBox = Box.createHorizontalBox();
		JButton registBtn = new JButton("ע��");
		// JButton AdminloginBtn = new JButton("����Ա��¼");
		JButton cancelregistBtn = new JButton("ȡ��ע��");

		btnBox.add(registBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		btnBox.add(cancelregistBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		// btnBox.add(AdminloginBtn);

		// ����ע����Ϊ
		registBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String name=uField.getText();
				String password = pField.getText();
				String address=addressField.getText();
				String phoneNum=tField.getText();
				if(Register(name,password,address,phoneNum)) {
					
				}
				JOptionPane.showMessageDialog(jf, "ע��ɹ�");
				new MainIntface().init();
				jf.dispose();
			}
		});
		

		// ����ȡ��ע����Ϊ
		cancelregistBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				new MainIntface().init();
				jf.dispose();
			}
		});
		
		
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(uBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(pBox);
		vBox.add(Box.createHorizontalStrut(20));
		vBox.add(gBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(addressBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(tBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(btnBox);
		
		
		jf.add(vBox);
		// jf.pack();
		jf.setVisible(true);
	}

	// ����¼
	public boolean Register(String name, String password,String address,String phoneNum) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

		boolean result = false;
		String sql = "insert into �û���Ϣ��(name,password,address,phone) values(?,?,?,?)";
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDb", "sa",
					"root");
			System.out.println("�������ݿ�ɹ���");
			//Statement stt = conn.createStatement();
			
			PreparedStatement pstt = conn.prepareStatement(sql);
			
			pstt.setString(1,name);
			pstt.setString(2, password);
			pstt.setString(3, address);
			pstt.setString(4, phoneNum);
			
			pstt.executeUpdate();
			
			/*ResultSet rs = pstt.getGeneratedKeys();
			if (rs.next())
				result = true;
			System.out.println(sql);*/
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * public static void main(String[] args) { // TODO �Զ����ɵķ������ new
	 * RegisterInterface().init(); }
	 */
}
