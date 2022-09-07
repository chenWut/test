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
	
	JFrame jf = new JFrame("����ϵͳ��ӭ��...");

	final int WIDTH = 1000;
	final int HEIGHT = 600;
	
	// ��װ��ͼ
		public void init() {
			
			// ���ô�����ص�����
			jf.setBounds(280, 140, WIDTH, HEIGHT);
			jf.setResizable(true);
			
			JMenuBar jmb = new JMenuBar();
			JMenu jMenu = new JMenu("����");
			//JMenu jMenu2 = new JMenu("ooo����");
			JMenuItem m1 = new JMenuItem("�л��˺�");
			JMenuItem m2 = new JMenuItem("�˳�����");
			
			m1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					new MainIntface().init();
					jf.dispose();
				}
			});
			m2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					System.exit(0);
				}
			});
			
			jMenu.add(m1);
			jMenu.add(m2);
			jmb.add(jMenu);
			//jmb.add(jMenu2);
			
			jf.setJMenuBar(jmb);
			
			//���÷ָ����
			JSplitPane sp = new JSplitPane();
			
			//֧����������
			sp.setContinuousLayout(true);
			sp.setDividerLocation(150);
			sp.setDividerSize(7);
			
			//�����������
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("ϵͳ����");
			DefaultMutableTreeNode userManage = new DefaultMutableTreeNode("�û�����");
			DefaultMutableTreeNode flowerManager = new DefaultMutableTreeNode("���ܹ���");
			DefaultMutableTreeNode saleManager = new DefaultMutableTreeNode("���׹���");
			DefaultMutableTreeNode analyze = new DefaultMutableTreeNode("����");
			
			
			root.add(userManage);
			root.add(flowerManager);
			root.add(saleManager);
			root.add(analyze);
			
			JTree tree = new JTree(root);
			Color color = new Color(203,220,217);
			tree.setBackground(color);
			
			//���õ�ǰtreeĬ��ѡ�л��ܹ���
			tree.setSelectionRow(2);
			tree.addTreeSelectionListener(new TreeSelectionListener() {
				//����Ŀѡ�б仯�����������ִ��
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					// TODO �Զ����ɵķ������
					
					//�õ���ǰѡ�еĽڵ����
					Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
					
					if(userManage.equals(lastPathComponent)) {
						sp.setRightComponent(new AdminManagerComponent(jf));
						sp.setDividerLocation(150);
					}else if(flowerManager.equals(lastPathComponent)) {
						sp.setRightComponent(new FlowerManageComponent(jf));
						//sp.setRightComponent(new Fm());
						sp.setDividerLocation(150);
					}if(saleManager.equals(lastPathComponent)) {
						sp.setRightComponent(new OrderManageComponent(jf));//new JLabel("������н��׹���...")
						sp.setDividerLocation(150);
					}if(analyze.equals(lastPathComponent)) {
						sp.setRightComponent(new AnalyseManageComponent(jf));////new JLabel("������з���...")
						sp.setDividerLocation(150);
					}
				}
			});
			
			sp.setRightComponent(new FlowerManageComponent(jf));//JLabel("������л��ܹ���...")
			
			sp.setLeftComponent(tree);
			jf.add(sp);
			
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
		new AdminMannagerIntface().init();
	}

}
