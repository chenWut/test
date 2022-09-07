package operate;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utilToSQLserver.ToSQLserver;

public class Buyflower extends JDialog{
	
	final int WIDTH = 400;
	final int HEIGTH = 800;
	PreparedStatement ps=null;  
	  Connection ct=null;  
	  ResultSet rs=null;  

	public Buyflower(JFrame jf,String title,boolean isModel, String s1, String s2, String s3, String s4, String name) {
		super(jf, title, isModel);
		this.setBounds(450, 200, 700,400);
		
		//组装登录相关元素0
		Box vBox = Box.createVerticalBox();
		
		//组装花卉名称
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("花卉名称:");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(s1);
		fNameArea.setEditable(false);
		
		fNameLabel.setBounds(100,100,100,100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);
		
		//组装花卉种类
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("花卉种类:");
		JTextField fTypeArea = new JTextField(15);
		fTypeArea.setText(s2);
		fTypeArea.setEditable(false);
		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);
		
		//组装花卉库存
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("花卉库存:");
		JTextField fCountArea = new JTextField();
		fCountArea.setText(s3);
		fCountArea.setEditable(false);
		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);
		
		//组装花卉价格
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("花卉价格:");
		JTextField fPriceArea = new JTextField(15);
		fPriceArea.setText(s4);
		fPriceArea.setEditable(false);
		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);
		
		//组装购买花卉数量
		Box fBuyBox = Box.createHorizontalBox();
		JLabel fBuyLabel = new JLabel("购买数量:");
		JTextField fBuyArea = new JTextField(15);
		//fBuyArea.setText(s4);
		//fBuyArea.setEditable(false);
		fBuyBox.add(fBuyLabel);
		fBuyBox.add(Box.createHorizontalStrut(20));
		fBuyBox.add(fBuyArea);
		
		//组装购买花卉日期
		Box fBuyDateBox = Box.createHorizontalBox();
		JLabel fBuyDateLabel = new JLabel("购买数量:");
		JTextField fBuyDateArea = new JTextField(15);
		//fBuyArea.setText(s4);
		//fBuyArea.setEditable(false);
		fBuyDateBox.add(fBuyDateLabel);
		fBuyDateBox.add(Box.createHorizontalStrut(20));
		fBuyDateBox.add(fBuyDateArea);
		
		//组装按钮
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("购买");
		
		
		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				ArrayList<String> inlist = new ArrayList<>();
				String FlowerName = fNameArea.getText();
				String Onum = fBuyArea.getText();
				String price = fPriceArea.getText();
				int a = Integer.parseInt(Onum);
				float r = Float.valueOf(price).floatValue();
				Float Zongjia = a*r;
				String Zj = String.valueOf(Zongjia);
				
				Date d = new Date();
				System.out.println(d);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateNowStr = sdf.format(d);
				System.out.println("格式化后的日期：" + dateNowStr);
				String date = dateNowStr;
				
				String 用户名 = name;

				inlist.add(FlowerName);
				inlist.add(Onum);
				inlist.add(price);
				inlist.add(Zj);
				inlist.add(date);
				inlist.add(用户名);
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					ct=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");
					//花卉库存
					String sql = "select num from Flower where name = '"+inlist.get(0)+"'";
					ps=ct.prepareStatement(sql);  
		             rs=ps.executeQuery();
		             String b = null;
		             if(rs.next()) {
		            	 b = rs.getString(1);
		            	 System.out.println(b);
		             }
		            int Nnum =  Integer.parseInt(b);
		            
		            
		            if(Nnum>=a) {
		            	ToSQLserver conSQL = new ToSQLserver();
				//conSQL.selectFlowerNummethod(inlist);
				
					
					conSQL.insertOrdermethod(inlist);
					conSQL.updateFlowerNummethod(inlist);
					if (conSQL.success == 1) {
						JOptionPane.showMessageDialog(jf, "购买成功");
	                	dispose();  
					} else {
						JOptionPane.showMessageDialog(jf,"购买失败");
					}
		            }else {
						JOptionPane.showMessageDialog(jf, "购买失败，库存不足");
					}
				} catch (Exception e1) {
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
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(fBuyBox);
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
