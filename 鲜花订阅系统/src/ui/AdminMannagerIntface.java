package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import component.FlowerManageComponent;
import component.OrderManageComponent;
import operate.SelectFlower;
import component.AdminManagerComponent;
import component.AnalyseManageComponent;
//import test.JDBCJTable;

public class AdminMannagerIntface {
	
	JFrame jf = new JFrame("花店系统欢迎你...");

	final int WIDTH = 1000;
	final int HEIGHT = 600;
	
	// 组装视图
		public void init() {
			
			// 设置窗口相关的属性
			jf.setBounds(280, 140, WIDTH, HEIGHT);
			jf.setResizable(true);
			
			JMenuBar jmb = new JMenuBar();
			JMenu jMenu = new JMenu("设置");
			//JMenu jMenu2 = new JMenu("ooo设置");
			JMenuItem m1 = new JMenuItem("切换账号");
			JMenuItem m2 = new JMenuItem("退出程序");
			
			m1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					new MainIntface().init();
					jf.dispose();
				}
			});
			m2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					System.exit(0);
				}
			});
			
			jMenu.add(m1);
			jMenu.add(m2);
			jmb.add(jMenu);
			//jmb.add(jMenu2);
			
			jf.setJMenuBar(jmb);
			
			//设置分割面板
			JSplitPane sp = new JSplitPane();
			
			//支持连续布局
			sp.setContinuousLayout(true);
			sp.setDividerLocation(150);
			sp.setDividerSize(7);
			
			//设置左侧内容
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
			DefaultMutableTreeNode userManage = new DefaultMutableTreeNode("用户管理");
			DefaultMutableTreeNode flowerManager = new DefaultMutableTreeNode("花卉管理");
			DefaultMutableTreeNode saleManager = new DefaultMutableTreeNode("交易管理");
			DefaultMutableTreeNode analyze = new DefaultMutableTreeNode("分析");
			
			
			root.add(userManage);
			root.add(flowerManager);
			root.add(saleManager);
			root.add(analyze);
			
			JTree tree = new JTree(root);
			Color color = new Color(203,220,217);
			tree.setBackground(color);
			
			//设置当前tree默认选中花卉管理
			tree.setSelectionRow(2);
			tree.addTreeSelectionListener(new TreeSelectionListener() {
				//当条目选中变化后，这个方法会执行
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					// TODO 自动生成的方法存根
					
					//得到当前选中的节点对象
					Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
					
					if(userManage.equals(lastPathComponent)) {
						sp.setRightComponent(new AdminManagerComponent(jf));
						sp.setDividerLocation(150);
					}else if(flowerManager.equals(lastPathComponent)) {
						sp.setRightComponent(new FlowerManageComponent(jf));
						//sp.setRightComponent(new Fm());
						sp.setDividerLocation(150);
					}if(saleManager.equals(lastPathComponent)) {
						sp.setRightComponent(new OrderManageComponent(jf));//new JLabel("这里进行交易管理...")
						sp.setDividerLocation(150);
					}if(analyze.equals(lastPathComponent)) {
						sp.setRightComponent(new AnalyseManageComponent(jf));////new JLabel("这里进行分析...")
						sp.setDividerLocation(150);
					}
				}
			});
			
			sp.setRightComponent(new FlowerManageComponent(jf));//JLabel("这里进行花卉管理...")
			
			sp.setLeftComponent(tree);
			jf.add(sp);
			
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
		new AdminMannagerIntface().init();
	}

}
