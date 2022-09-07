package operate;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utilToSQLserver.ToSQLserver;

public class DelectUser extends JDialog{

	
	final int WIDTH = 400;
	final int HEIGTH = 800;
	

	public DelectUser(JFrame jf,String title,boolean isModel, String s1, String s2, String s3, String s4) {
		super(jf, title, isModel);
		this.setBounds(450, 200, 500, 350);
		
		//组装登录相关元素0
		Box vBox = Box.createVerticalBox();
		
		//组装用户名称
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("用户名称:");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(s1);
		fNameArea.setEditable(false);
		fNameLabel.setBounds(100,100,100,100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);
		
		//组装用户密码
		Box fpwdBox = Box.createHorizontalBox();
		JLabel fpwdLabel = new JLabel("用户密码:");
		JTextField fpwdArea = new JTextField(15);
		fpwdArea.setText(s2);
		fpwdArea.setEditable(false);
		fpwdBox.add(fpwdLabel);
		fpwdBox.add(Box.createHorizontalStrut(20));
		fpwdBox.add(fpwdArea);
		
		//组装用户地址
		Box faddressBox = Box.createHorizontalBox();
		JLabel faddressLabel = new JLabel("用户地址:");
		JTextField faddressArea = new JTextField(15);
		faddressArea.setText(s3);
		faddressArea.setEditable(false);
		faddressBox.add(faddressLabel);
		faddressBox.add(Box.createHorizontalStrut(20));
		faddressBox.add(faddressArea);
		
		//组装用户手机号
		Box phoneBox = Box.createHorizontalBox();
		JLabel phoneLabel = new JLabel("手机号码:");
		JTextField phoneArea = new JTextField(15);
		phoneArea.setText(s4);
		phoneArea.setEditable(false);
		phoneBox.add(phoneLabel);
		phoneBox.add(Box.createHorizontalStrut(20));
		phoneBox.add(phoneArea);
		
		
		//组装按钮
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("删除");
		
		
		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				ArrayList<String> inlist = new ArrayList<>();
				String name = fNameArea.getText();
				inlist.add(name);
				
				ToSQLserver conSQL = new ToSQLserver();
	            conSQL.deleteUsermethod(inlist);
				
	            if (conSQL.success == 1) {
	                JOptionPane.showMessageDialog(jf, "删除成功");
	                dispose();  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"删除失败");
	            }
			
			
			
			}
		});
		
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(fNameBox);
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(fpwdBox);
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(faddressBox);
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(phoneBox);
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(btnBox);
		
		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(vBox);
		hBox.add(Box.createHorizontalStrut(20));
		
		this.add(hBox);
		this.setVisible(true);
		
	}
	

}
