package operate;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

import component.UserManagerComponent;
import utilToSQLserver.ToSQLserver;

public class UpdateUser extends JDialog {
	final int WIDTH = 400;
	final int HEIGTH = 800;
	public ArrayList<String> sellist = new ArrayList<>();

	
	// 用户更改信息
	
	public UpdateUser(JFrame jf, String title, boolean isModel, String name) {
		super(jf, title, isModel);
		this.setBounds(450, 200, 700, 400);

		// 组装登录相关元素
		Box vBox = Box.createVerticalBox();

		// 组装花卉名称
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("账号名称:");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(name);
		fNameArea.setEditable(false);
		fNameLabel.setBounds(100, 100, 100, 100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);

		ToSQLserver conSQL = new ToSQLserver();// 实例化数据库查询类
		sellist = conSQL.selectUsermethod(name);
		System.out.println(sellist);
		if (sellist.size() != 0) {

			// 组装花卉种类
			Box fTypeBox = Box.createHorizontalBox();
			JLabel fTypeLabel = new JLabel("账号密码:");
			JTextField fTypeArea = new JTextField(15);
			fTypeArea.setText(sellist.get(1));
			

			fTypeBox.add(fTypeLabel);
			fTypeBox.add(Box.createHorizontalStrut(20));
			fTypeBox.add(fTypeArea);

			// 组装花卉库存
			Box fCountBox = Box.createHorizontalBox();
			JLabel fCountLabel = new JLabel("用户地址:");
			JTextField fCountArea = new JTextField(15);
			fCountArea.setText(sellist.get(2));

			fCountBox.add(fCountLabel);
			fCountBox.add(Box.createHorizontalStrut(20));
			fCountBox.add(fCountArea);

			// 组装花卉价格
			Box fPriceBox = Box.createHorizontalBox();
			JLabel fPriceLabel = new JLabel("用户电话:");
			JTextField fPriceArea = new JTextField(15);
			fPriceArea.setText(sellist.get(3));

			fPriceBox.add(fPriceLabel);
			fPriceBox.add(Box.createHorizontalStrut(20));
			fPriceBox.add(fPriceArea);

			// 组装按钮
			Box btnBox = Box.createHorizontalBox();
			JButton addBtn = new JButton("确认更改");

			btnBox.add(addBtn);
			btnBox.add(Box.createHorizontalStrut(50));

			addBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					String name = fNameArea.getText();
					String password = fTypeArea.getText();
					String address = fCountArea.getText();
					String phone = fPriceArea.getText();

					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					} catch (ClassNotFoundException e2) {
						// TODO 自动生成的 catch 块
						e2.printStackTrace();
					}

					boolean result = false;

					Connection conn;
					try {
						conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB", "sa",
								"root");
						System.out.println("连接数据库成功！");
						Statement stt = conn.createStatement();
						String sql = "update 用户信息表 set password = '" + password + "',address = '" + address
								+ "',phone = '" + phone + "'where name = '" + name + "'";
						
						JOptionPane.showMessageDialog(jf, "更新成功");
						//new Userinformation(jf, name);
						dispose();
						stt.executeUpdate(sql);
						//if(rs.next())
						 //result = true;
						// System.out.println(sql);
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
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

	

	// 管理员修改用户信息
	
	public UpdateUser(JFrame jf, String title, boolean b, String s1, String s2, String s3, String s4) {
		// TODO 自动生成的构造函数存根
		super(jf,title);
		this.setBounds(450, 200, 700, 400);

		// 组装登录相关元素
		Box vBox = Box.createVerticalBox();

		// 组装花卉名称
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("账号名称:");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(s1);
		fNameArea.setEditable(false);
		
		fNameLabel.setBounds(100, 100, 100, 100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);

		// 组装花卉种类
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("账号密码:");
		JTextField fTypeArea = new JTextField(15);
		fTypeArea.setText(s2);

		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);

		// 组装花卉库存
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("用户地址:");
		JTextField fCountArea = new JTextField(15);
		fCountArea.setText(s3);

		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);

		// 组装花卉价格
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("用户电话:");
		JTextField fPriceArea = new JTextField(15);
		fPriceArea.setText(s4);

		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);

		// 组装按钮
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("确认更改");

		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name = fNameArea.getText();
				String password = fTypeArea.getText();
				String address = fCountArea.getText();
				String phone = fPriceArea.getText();
				
				ArrayList<String> inlist = new ArrayList<>();
				
				
				inlist.add(name);
				inlist.add(password);
				inlist.add(address);
				inlist.add(phone);
				
				ToSQLserver conSQL = new ToSQLserver();
	            conSQL.updateUsermethod(inlist);
				dispose();
	            if (conSQL.success == 1) {
	                JOptionPane.showMessageDialog(jf, "更新成功");
	                  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"更新失败");
	            }
			
			
				/*
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}

				boolean result = false;

				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB", "sa",
							"root");
					System.out.println("连接数据库成功！");
					Statement stt = conn.createStatement();
					String sql = "update 用户信息表 set password = '" + password + "',address = '" + address + "',phone = '"
							+ phone + "'where name = '" + name + "'";
					
					PreparedStatement pstmt=conn.prepareStatement(sql);
					
					
					int n=pstmt.executeUpdate();
					
					if(n==1) {
						JOptionPane.showMessageDialog(jf, "更新成功");
					}
						
					else {
						JOptionPane.showMessageDialog(jf, "更新失败");
					}
					dispose();

				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
	*/
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
