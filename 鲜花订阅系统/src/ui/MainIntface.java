package ui;

import java.awt.Dimension;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utilToSQLserver.ToSQLserver;

public class MainIntface {

	JFrame jf = new JFrame("�ʻ�����ϵͳ");
	
	final int WIDTH = 500;
	final int HEIGHT = 230;
	
	
	//��װ��ͼ
	public void init() {
		//���ô�����ص�����
		jf.setBounds(400, 200, WIDTH, HEIGHT);
		jf.setResizable(true);
		
		//��װ��¼���Ԫ��
		Box vBox = Box.createVerticalBox();
		
		//��װ�û���
		Box uBox = Box.createHorizontalBox();
		JLabel uLabel = new JLabel("�û���:");
		JTextField uField = new JTextField(15);
		
		uBox.add(uLabel);
		uBox.add(Box.createHorizontalStrut(20));
		uBox.add(uField);
		
		//��װ����
		Box pBox = Box.createHorizontalBox();
		JLabel pLabel = new JLabel("��    ��:");
		//JTextField 
		JPasswordField pField = new JPasswordField(10);
		//pField.setEchoChar('#');
		
		pBox.add(pLabel);
		pBox.add(Box.createHorizontalStrut(20));
		pBox.add(pField);
		
		
		
		//��װ��ť
		Box btnBox = Box.createHorizontalBox();
		JButton loginBtn = new JButton("��¼");
		JButton AdminloginBtn = new JButton("����Ա��¼");
		JButton registBtn = new JButton("ע��");
		
		btnBox.add(loginBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		btnBox.add(registBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		btnBox.add(AdminloginBtn);
		//�û���¼
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String name = uField.getText();
				String password = pField.getText();
				
				ArrayList<String> inlist = new ArrayList<>();
				
				
				if(0==name.length()) {
					JOptionPane.showMessageDialog(jf, "�˺Ų���Ϊ��");
					uField.grabFocus();
					return;
				}
				if(0==password.length()) {
					JOptionPane.showMessageDialog(jf, "���벻��Ϊ��");
					pField.grabFocus();
					return;
				}
				
				inlist.add(name);
				inlist.add(password);
				
				ToSQLserver conSQL = new ToSQLserver();
	           // ;
	            
	            if (conSQL.loginmethod(inlist)) {
	                
					JOptionPane.showMessageDialog(jf,name+"  ��ӭ���٣�");
	                new UserMannagerIntface().init(name);
	                jf.dispose();  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"��¼ʧ��");
	            }
	            
			}
		});
		
		//��������Ա��¼
		AdminloginBtn.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String name = uField.getText();
				String password = pField.getText();
				ArrayList<String> inlist = new ArrayList<>();
				if(0==name.length()) {
					JOptionPane.showMessageDialog(jf, "�˺Ų���Ϊ��");
					uField.grabFocus();
					return;
				}
				if(0==password.length()) {
					JOptionPane.showMessageDialog(jf, "���벻��Ϊ��");
					pField.grabFocus();
					return;
				}
				
				inlist.add(name);
				inlist.add(password);
				
				ToSQLserver conSQL = new ToSQLserver();
	            conSQL.Adminloginmethod(inlist);
	            
	            if (conSQL.Adminloginmethod(inlist)) {
	                JOptionPane.showMessageDialog(jf, "��¼�ɹ�");
	                new AdminMannagerIntface().init();
	                jf.dispose();  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"��¼ʧ��");
	            }
				
			}
		});
		
		//����ע��
		registBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				new RegisterInterface().init();
				jf.dispose();
			}
		});
		
		
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(uBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(pBox);
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(btnBox);
		
		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(vBox);
		hBox.add(Box.createHorizontalStrut(20));
		
		jf.add(hBox);
		
		jf.addWindowListener(new WindowAdapter() {
      		 //�������ر�ʱ����
           @Override
           public void windowClosing(WindowEvent e) {
           	//��������
               System.exit(0);
           }
       });
		
		jf.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new MainIntface().init();
	}
}
