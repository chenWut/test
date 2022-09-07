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
		
		//��װ��¼���Ԫ��0
		Box vBox = Box.createVerticalBox();
		
		//��װ��������
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("��������:");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(s1);
		fNameArea.setEditable(false);
		
		fNameLabel.setBounds(100,100,100,100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);
		
		//��װ��������
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("��������:");
		JTextField fTypeArea = new JTextField(15);
		fTypeArea.setText(s2);
		fTypeArea.setEditable(false);
		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);
		
		//��װ���ܿ��
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("���ܿ��:");
		JTextField fCountArea = new JTextField();
		fCountArea.setText(s3);
		fCountArea.setEditable(false);
		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);
		
		//��װ���ܼ۸�
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("���ܼ۸�:");
		JTextField fPriceArea = new JTextField(15);
		fPriceArea.setText(s4);
		fPriceArea.setEditable(false);
		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);
		
		//��װ���򻨻�����
		Box fBuyBox = Box.createHorizontalBox();
		JLabel fBuyLabel = new JLabel("��������:");
		JTextField fBuyArea = new JTextField(15);
		//fBuyArea.setText(s4);
		//fBuyArea.setEditable(false);
		fBuyBox.add(fBuyLabel);
		fBuyBox.add(Box.createHorizontalStrut(20));
		fBuyBox.add(fBuyArea);
		
		//��װ���򻨻�����
		Box fBuyDateBox = Box.createHorizontalBox();
		JLabel fBuyDateLabel = new JLabel("��������:");
		JTextField fBuyDateArea = new JTextField(15);
		//fBuyArea.setText(s4);
		//fBuyArea.setEditable(false);
		fBuyDateBox.add(fBuyDateLabel);
		fBuyDateBox.add(Box.createHorizontalStrut(20));
		fBuyDateBox.add(fBuyDateArea);
		
		//��װ��ť
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("����");
		
		
		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
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
				System.out.println("��ʽ��������ڣ�" + dateNowStr);
				String date = dateNowStr;
				
				String �û��� = name;

				inlist.add(FlowerName);
				inlist.add(Onum);
				inlist.add(price);
				inlist.add(Zj);
				inlist.add(date);
				inlist.add(�û���);
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					ct=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");
					//���ܿ��
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
						JOptionPane.showMessageDialog(jf, "����ɹ�");
	                	dispose();  
					} else {
						JOptionPane.showMessageDialog(jf,"����ʧ��");
					}
		            }else {
						JOptionPane.showMessageDialog(jf, "����ʧ�ܣ���治��");
					}
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
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
