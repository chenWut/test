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
		
		//��װ��¼���Ԫ��
		Box vBox = Box.createVerticalBox();
		
		//��װ��������
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("�û�����:");
		JTextField fNameArea = new JTextField(15);
		fNameLabel.setBounds(100,100,100,100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);
		
		//��װ��������
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("��¼����:");
		JTextField fTypeArea = new JTextField(15);
		
		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);
		
		//��װ���ܿ��
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("�ͻ���ַ:");
		JTextField fCountArea = new JTextField(15);
		
		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);
		
		//��װ���ܼ۸�
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("�绰����:");
		JTextField fPriceArea = new JTextField(15);
		
		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);
		
		//��װ��ť
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("���");
		
		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		
		
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
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
	                JOptionPane.showMessageDialog(jf, "��ӳɹ�");
	                dispose();  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"���ʧ��");
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
