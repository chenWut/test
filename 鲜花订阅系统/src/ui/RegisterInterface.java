package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterInterface {

	JFrame jf = new JFrame("注册界面——完成提交后会自动跳转...");

	final int WIDTH = 500;
	final int HEIGHT = 400;

	// 组装视图
	public void init() {

		// 组装登录相关元素
		Box vBox = Box.createVerticalBox();

		// 设置窗口相关的属性
		jf.setBounds(500, 200, WIDTH, HEIGHT);
		jf.setResizable(true);

		// 组装用户名
		Box uBox = Box.createHorizontalBox();
		JLabel uLabel = new JLabel("用户名:");
		JTextField uField = new JTextField(15);

		uBox.add(uLabel);
		uBox.add(Box.createHorizontalStrut(20));
		uBox.add(uField);

		// 组装密码
		Box pBox = Box.createHorizontalBox();
		JLabel pLabel = new JLabel("密    码:");
		JTextField pField = new JTextField(10);

		pBox.add(pLabel);
		pBox.add(Box.createHorizontalStrut(20));
		pBox.add(pField);

		//组装性别
		Box gBox = Box.createHorizontalBox();
		JLabel gLabel = new JLabel("性    别:");
		JRadioButton manBtn = new JRadioButton("男",true);
		JRadioButton femanBtn = new JRadioButton("女",false);
		
		//实现单选的效果
		ButtonGroup bg = new ButtonGroup();
		bg.add(manBtn);
		bg.add(femanBtn);
		
		
		gBox.add(gLabel);
		gBox.add(Box.createHorizontalStrut(20));
		gBox.add(manBtn);
		gBox.add(femanBtn);
		gBox.add(Box.createHorizontalStrut(20));

		
		// 组装地址
		Box addressBox = Box.createHorizontalBox();
		JLabel addressLabel = new JLabel("地	    址:");
		JTextField addressField = new JTextField(10);

		addressBox.add(addressLabel);
		addressBox.add(Box.createHorizontalStrut(20));
		addressBox.add(addressField);

		// 组装手机号
		Box tBox = Box.createHorizontalBox();
		JLabel tLabel = new JLabel("手机号:");
		JTextField tField = new JTextField(10);

		tBox.add(tLabel);
		tBox.add(Box.createHorizontalStrut(20));
		tBox.add(tField);

		// 组装按钮
		Box btnBox = Box.createHorizontalBox();
		JButton registBtn = new JButton("注册");
		// JButton AdminloginBtn = new JButton("管理员登录");
		JButton cancelregistBtn = new JButton("取消注册");

		btnBox.add(registBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		btnBox.add(cancelregistBtn);
		btnBox.add(Box.createHorizontalStrut(50));
		// btnBox.add(AdminloginBtn);

		// 监听注册行为
		registBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name=uField.getText();
				String password = pField.getText();
				String address=addressField.getText();
				String phoneNum=tField.getText();
				if(Register(name,password,address,phoneNum)) {
					
				}
				JOptionPane.showMessageDialog(jf, "注册成功");
				new MainIntface().init();
				jf.dispose();
			}
		});
		

		// 监听取消注册行为
		cancelregistBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new MainIntface().init();
				jf.dispose();
			}
		});
		
		
		vBox.add(Box.createVerticalStrut(40));
		vBox.add(uBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(pBox);
		vBox.add(Box.createHorizontalStrut(20));
		vBox.add(gBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(addressBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(tBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(btnBox);
		
		
		jf.add(vBox);
		// jf.pack();
		jf.setVisible(true);
	}

	// 检查登录
	public boolean Register(String name, String password,String address,String phoneNum) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		boolean result = false;
		String sql = "insert into 用户信息表(name,password,address,phone) values(?,?,?,?)";
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDb", "sa",
					"root");
			System.out.println("连接数据库成功！");
			//Statement stt = conn.createStatement();
			
			PreparedStatement pstt = conn.prepareStatement(sql);
			
			pstt.setString(1,name);
			pstt.setString(2, password);
			pstt.setString(3, address);
			pstt.setString(4, phoneNum);
			
			pstt.executeUpdate();
			
			/*ResultSet rs = pstt.getGeneratedKeys();
			if (rs.next())
				result = true;
			System.out.println(sql);*/
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * public static void main(String[] args) { // TODO 自动生成的方法存根 new
	 * RegisterInterface().init(); }
	 */
}
