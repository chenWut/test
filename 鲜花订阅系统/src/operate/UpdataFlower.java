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

import utilToSQLserver.ToSQLserver;

public class UpdataFlower extends JDialog{
	final int WIDTH = 400;
	final int HEIGTH = 800;
	

	public UpdataFlower(JFrame jf,String title,boolean isModel, String s1, String s2, String s3, String s4) {
		super(jf,title);
		this.setBounds(450, 200, 700,400);
		
		//组装登录相关元素
		Box vBox = Box.createVerticalBox();
		
		//组装花卉名称
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("花卉名称:");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(s1);
		
		fNameLabel.setBounds(100,100,100,100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);
		
		//组装花卉种类
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("花卉种类:");
		JTextField fTypeArea = new JTextField(15);
		fTypeArea.setText(s2);
		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);
		
		//组装花卉库存
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("花卉库存:");
		JTextField fCountArea = new JTextField(15);
		fCountArea.setText(s3);
		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);
		
		//组装花卉价格
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("花卉价格:");
		JTextField fPriceArea = new JTextField(15);
		fPriceArea.setText(s4);
		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);
		
		//组装按钮
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("确认更改");
		
		
		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name = fNameArea.getText();
				String type = fTypeArea.getText();
				String count = fCountArea.getText();
				String price = fPriceArea.getText();
				
				
				ArrayList<String> inlist = new ArrayList<>();
				
				
				inlist.add(name);
				inlist.add(type);
				inlist.add(count);
				inlist.add(price);
				
				ToSQLserver conSQL = new ToSQLserver();
	            conSQL.updateFlowermethod(inlist);
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
						conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");
						System.out.println("连接数据库成功！");
					Statement stt = conn.createStatement();
					String sql = "update flower set type = '"+type+"',num = '"+count+"',price = '"+price+"'where name = '"+name+"'";
					
					JOptionPane.showMessageDialog(jf, "更新成功");
					dispose();
					ResultSet rs = stt.executeQuery(sql);
					//if(rs.next())
						//result = true;
					//System.out.println(sql);
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

	
/*
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}
*/
}
