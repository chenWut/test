package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import operate.AddUsers;
import operate.DelectUser;
import operate.UpdateUser;



public class UserManagerComponent extends Box {

	
	final int WIDTH = 850;
	final int HEIGHT = 600;
	
	private JTable table;
	private Vector<String> titles;
	private Vector<Vector> tableData;
	private TableModel tableModel;
	
	//private Vector titlesV = new Vector<>();//�洢����
	//private Vector<Vector>  dataV = new Vector<>();//�洢����
	
	  Vector rowData,columnNames;  
	  JTable jt=null;  
	  JScrollPane jsp=null;  
	  
	  //�������ݿ���Ҫ��ȫ�ֱ��� 
	  JFrame jf = null;
	  PreparedStatement ps=null;  
	  Connection ct=null;  
	  ResultSet rs=null;  
	  String nameString ;
	    
	
	public UserManagerComponent(JFrame jf,String name) {
		
		
		super(BoxLayout.Y_AXIS);
		this.jf = jf;
		
		//��װ��ͼ
		JPanel btnPanel = new JPanel();
		Color color = new Color(203,220,217);
		btnPanel.setBackground(color);
		btnPanel.setMaximumSize(new Dimension(WIDTH, 40));;
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		nameString = name;
		JButton updateBtn = new JButton("�޸�");
		
		btnPanel.add(updateBtn);
		
		this.add(btnPanel);
		
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				new UpdateUser(jf, "�޸��û���Ϣ", true,nameString);
			}
		});
		
		
		//�������
		 columnNames=new Vector();  
         //��������  
         columnNames.add("�˺�");  
         columnNames.add("����");  
         columnNames.add("��ַ");  
         columnNames.add("�绰����");  
         //columnNames.add("����");  
         //columnNames.add("ϵ��");  
           
         rowData = new Vector();  
         //rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ  
           
         try {  
             //��������  
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
             //�õ�����  
             ct=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");  
               
             ps=ct.prepareStatement("select * from �û���Ϣ�� where name = '"+name+"'");  //"select * from �û���Ϣ�� "
               
             rs=ps.executeQuery();  
             
             rowData.clear();
               
             while(rs.next()){  
                 //rowData���Դ�Ŷ���  
                 Vector hang=new Vector();  
                 hang.add(rs.getString(1));  
                 hang.add(rs.getString(2));  
                 hang.add(rs.getString(3));  
                 hang.add(rs.getString(4));  
                 //hang.add(rs.getString(5));  
                // hang.add(rs.getString(6));  
                   
                 //���뵽rowData  
                 rowData.add(hang);  
             }  
         } catch (Exception e) {  
             e.printStackTrace();  
         } finally{  
               
                 try {  
                     if(rs!=null){  
                     rs.close();  
                     }  
                     if(ps!=null){  
                         ps.close();  
                     }  
                     if(ct!=null){  
                         ct.close();  
                     }  
                 } catch (SQLException e) {  
                     e.printStackTrace();  
                 }  
         }  
           
                       
         //��ʼ��Jtable  
         jt = new JTable(rowData,columnNames);  
         
         jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      // ���ñ���е����ݾ�����ʾ
         DefaultTableCellRenderer r=new DefaultTableCellRenderer();
         r.setHorizontalAlignment(SwingConstants.CENTER);
         jt.setDefaultRenderer(Object.class,r);
         jt.setAutoCreateRowSorter(true);
         jt.updateUI();
         
         //��ʼ�� jsp  
         jsp = new JScrollPane(jt);  
         
         //��jsp���뵽jframe  
         this.add(jsp);  
           this.setSize(400, 300);
         
	
	}
	
}

