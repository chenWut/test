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

public class UpdateOrder extends JDialog{
	final int WIDTH = 400;
	final int HEIGTH = 800;
	

	public UpdateOrder(JFrame jf,String title,boolean isModel, String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
		super(jf,title);
		this.setBounds(450, 200, 700,500);
		
		//��װ��¼���Ԫ��
		Box vBox = Box.createVerticalBox();
		
		//��װ�������
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("���� ���       :");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(s1);
		fNameArea.setEditable(false);
		fNameLabel.setBounds(100,100,100,100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);
		
		//��װ�����л�������
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("���� ����       :");
		JTextField fTypeArea = new JTextField(15);
		fTypeArea.setText(s2);
		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);
		
		//��װ�����л�������
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("��         ��       :");//���ܼ۸�Ԫ/��:
		JTextField fCountArea = new JTextField(15);
		fCountArea.setText(s3);
		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);
		
		//��װ���ܼ۸�
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("���ܼ۸�Ԫ/��:");
		JTextField fPriceArea = new JTextField(15);
		fPriceArea.setText(s4);
		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);
		
		//��װ�������
		Box PriceBox = Box.createHorizontalBox();
		JLabel PriceLabel = new JLabel("���� ���        :");
		JTextField PriceArea = new JTextField(15);
		PriceArea.setText(s5);
		PriceBox.add(PriceLabel);
		PriceBox.add(Box.createHorizontalStrut(20));
		PriceBox.add(PriceArea);
		
		//��װ��������
		Box dateBox = Box.createHorizontalBox();
		JLabel dateLabel = new JLabel("���� ����         ");
		JTextField dateArea = new JTextField(15);
		dateArea.setText(s6);
		dateBox.add( dateLabel);
		dateBox.add(Box.createHorizontalStrut(20));
		dateBox.add(dateArea);
		
		//��װ������
		Box BuyerBox = Box.createHorizontalBox();
		JLabel BuyerLabel = new JLabel("  �� �� ��         ");
		JTextField BuyerArea = new JTextField(15);
		BuyerArea.setText(s7);
		BuyerBox.add( BuyerLabel);
		BuyerBox.add(Box.createHorizontalStrut(20));
		BuyerBox.add(BuyerArea);
		
		//��װ��ť
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("ȷ�ϸ���");
		
		
		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String name = fNameArea.getText();
				String type = fTypeArea.getText();
				String count = fCountArea.getText();
				String price = fPriceArea.getText();
				String Zprice = PriceArea.getText();
				String date = dateArea.getText();
				String Buyer = BuyerArea.getText();
				
				
				ArrayList<String> inlist = new ArrayList<>();
				
				
				inlist.add(name);
				inlist.add(type);
				inlist.add(count);
				inlist.add(price);
				inlist.add(Zprice);
				inlist.add(date);
				inlist.add(Buyer);
				
				ToSQLserver conSQL = new ToSQLserver();
	            conSQL.updateOrdermethod(inlist);
				dispose();
	            if (conSQL.success == 1) {
	                JOptionPane.showMessageDialog(jf, "���³ɹ�");
	                  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"����ʧ��");
	            }
				/*
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e2) {
					// TODO �Զ����ɵ� catch ��
					e2.printStackTrace();
				}
				
				boolean result = false;
				
					Connection conn;
					try {
						conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");
						System.out.println("�������ݿ�ɹ���");
					Statement stt = conn.createStatement();
					String sql = "update flower set type = '"+type+"',num = '"+count+"',price = '"+price+"'where name = '"+name+"'";
					
					JOptionPane.showMessageDialog(jf, "���³ɹ�");
					dispose();
					ResultSet rs = stt.executeQuery(sql);
					//if(rs.next())
						//result = true;
					//System.out.println(sql);
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
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
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(PriceBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(dateBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(BuyerBox);
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
		// TODO �Զ����ɵķ������

	}
*/


}
