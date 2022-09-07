package operate;


import java.awt.Dialog;
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
import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import utilToSQLserver.ToSQLserver;

public class AddUsers extends JDialog{
	
	final int WIDTH = 400;
	final int HEIGTH = 800;
	

	public AddUsers(JFrame jf,String title,boolean isModel) {
		super(jf, title, isModel);
		this.setBounds(450, 200, 700,400);
		
		//组装登录相关元素
		Box vBox = Box.createVerticalBox();
		
		//组装花卉名称
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("用户名称:");
		JTextField fNameArea = new JTextField(15);
		fNameLabel.setBounds(100,100,100,100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);
		
		//组装花卉种类
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("登录密码:");
		JTextField fTypeArea = new JTextField(15);
		
		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);
		
		//组装花卉库存
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("送货地址:");
		JTextField fCountArea = new JTextField(15);
		
		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);
		
		//组装花卉价格
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("电话号码:");
		JTextField fPriceArea = new JTextField(15);
		
		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);
		
		//组装按钮
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("添加");
		
		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		
		
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				ArrayList<String> inlist = new ArrayList<>();
				String name = fNameArea.getText();
				String type = fTypeArea.getText();
				String count = fCountArea.getText();
				String price = fPriceArea.getText();
				
				inlist.add(name);
				inlist.add(type);
				inlist.add(count);
				inlist.add(price);
				
				ToSQLserver conSQL = new ToSQLserver();
	            conSQL.insertUsermethod(inlist);
				
	            if (conSQL.success == 1) {
	                JOptionPane.showMessageDialog(jf, "添加成功");
	                dispose();  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"添加失败");
	            }
			
			}
		});
		
		
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(fNameBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(fTypeBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(fCountBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(fPriceBox);
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
