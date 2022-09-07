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

	JFrame jf = new JFrame("鲜花订阅系统");
	
	final int WIDTH = 500;
	final int HEIGHT = 230;
	
	
	//组装视图
	public void init() {
		//设置窗口相关的属性
		jf.setBounds(400, 200, WIDTH, HEIGHT);
		jf.setResizable(true);
		
		//组装登录相关元素
		Box vBox = Box.createVerticalBox();
		
		//组装用户名
		Box uBox = Box.createHorizontalBox();
		JLabel uLabel = new JLabel("用户名:");
		JTextField uField = new JTextField(15);
		
		uBox.add(uLabel);
		uBox.add(Box.createHorizontalStrut(20));
		uBox.add(uField);
		
		//组装密码
		Box pBox = Box.createHorizontalBox();
		JLabel pLabel = new JLabel("密    码:");
		//JTextField 
		JPasswordField pField = new JPasswordField(10);
		//pField.setEchoChar('#');
		
		pBox.add(pLabel);
		pBox.add(Box.createHorizontalStrut(20));
		pBox.add(pField);
		
		
		
		//组装按钮
		Box btnBox = Box.createHorizontalBox();
		JButton loginBtn = new JButton("登录");
		JButton AdminloginBtn = new JButton("管理员登录");
		JButton registBtn = new JButton("注册");
		
		btnBox.add(loginBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		btnBox.add(registBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		btnBox.add(AdminloginBtn);
		//用户登录
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name = uField.getText();
				String password = pField.getText();
				
				ArrayList<String> inlist = new ArrayList<>();
				
				
				if(0==name.length()) {
					JOptionPane.showMessageDialog(jf, "账号不能为空");
					uField.grabFocus();
					return;
				}
				if(0==password.length()) {
					JOptionPane.showMessageDialog(jf, "密码不能为空");
					pField.grabFocus();
					return;
				}
				
				inlist.add(name);
				inlist.add(password);
				
				ToSQLserver conSQL = new ToSQLserver();
	           // ;
	            
	            if (conSQL.loginmethod(inlist)) {
	                
					JOptionPane.showMessageDialog(jf,name+"  欢迎光临！");
	                new UserMannagerIntface().init(name);
	                jf.dispose();  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"登录失败");
	            }
	            
			}
		});
		
		//监听管理员登录
		AdminloginBtn.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name = uField.getText();
				String password = pField.getText();
				ArrayList<String> inlist = new ArrayList<>();
				if(0==name.length()) {
					JOptionPane.showMessageDialog(jf, "账号不能为空");
					uField.grabFocus();
					return;
				}
				if(0==password.length()) {
					JOptionPane.showMessageDialog(jf, "密码不能为空");
					pField.grabFocus();
					return;
				}
				
				inlist.add(name);
				inlist.add(password);
				
				ToSQLserver conSQL = new ToSQLserver();
	            conSQL.Adminloginmethod(inlist);
	            
	            if (conSQL.Adminloginmethod(inlist)) {
	                JOptionPane.showMessageDialog(jf, "登录成功");
	                new AdminMannagerIntface().init();
	                jf.dispose();  
	            } else {
	            	JOptionPane.showMessageDialog(jf,"登录失败");
	            }
				
			}
		});
		
		//监听注册
		registBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
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
      		 //窗体点击关闭时，做
           @Override
           public void windowClosing(WindowEvent e) {
           	//结束程序
               System.exit(0);
           }
       });
		
		jf.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new MainIntface().init();
	}
}
