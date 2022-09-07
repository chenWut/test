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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import operate.AddUsers;
import operate.Buyflower;
import operate.DelectUser;
import operate.SelectUser;
import operate.UpdateUser;

public class AnalyseManageComponent extends Box {

	
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
	    
	    
	
	public AnalyseManageComponent(JFrame jf) {
		
		super(BoxLayout.Y_AXIS);
		this.jf = jf;
		
		//��װ��ͼ
		JPanel btnPanel = new JPanel();
		Color color = new Color(203,220,217);
		btnPanel.setBackground(color);
		btnPanel.setMaximumSize(new Dimension(WIDTH, 10000));;
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
		this.add(btnPanel);
		
		
		//�������
		 columnNames=new Vector();  
         //��������  
         columnNames.add("�û���");  
         columnNames.add("������");  
         columnNames.add("�ܻ���");  
           
         rowData = new Vector();  
         //rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ  
         
         
         
         try {
        	//��������  
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
             //�õ�����  
             ct=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");  
             String sql = "SELECT left(�û���,10) as name, count(Ono) as order_count"
             		+ ", Sum(DISTINCT Zongjia) as order_value FROM Orderinformation  GROUP BY left(�û���,10)";
             ps=ct.prepareStatement(sql);  
               
             rs=ps.executeQuery();  
             System.out.println(sql);
             rowData.clear();
               
             while(rs.next()){  
                 //rowData���Դ�Ŷ���  
                 Vector hang=new Vector();  
                 hang.add(rs.getString(1));  
                 hang.add(rs.getString(2));  
                 hang.add(rs.getString(3));  
                 //hang.add(rs.getString(4));  
                 //hang.add(rs.getString(5));  
                // hang.add(rs.getString(6));  
                   
                 //���뵽rowData  
                 rowData.add(hang);  
             }
        	 /*
             //��������  
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             //�õ�����  
             ct=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");  
             String sql1 = "SELECT COUNT(*) AS NumberOfOrders FROM �û���Ϣ��";
             String sql2 = "SELECT COUNT(*) AS NumberOfOrders FROM Orderinformation";
             String sql3 = "SELECT SUM( Zongjia) FROM Orderinformation";
             
             rowData.clear();
             ps=ct.prepareStatement(sql1);  
             rs=ps.executeQuery();
             
             Object b = null,c = null,d = null;
             if(rs.next()) {
            	 b = rs.getString(1);
            	 System.out.println(b);
             }
             ps=ct.prepareStatement(sql2);  
             rs=ps.executeQuery();
             if(rs.next()) {
            	 c = rs.getString(1);
            	 System.out.println(c);
             }
             ps=ct.prepareStatement(sql3);  
             rs=ps.executeQuery();
             if(rs.next()) {
            	 d = rs.getString(1);
            	 System.out.println(d);
             }
             
               
             for(int i = 0;i<1;i++){  
                 //rowData���Դ�Ŷ���  
                 Vector hang=new Vector();  
                 hang.add(b);
                 hang.add(c);
                 hang.add(d);
                 
                 //���뵽rowData  
                 rowData.add(hang);  
                 //System.out.println(rs.getString(1));
             }
             */
             System.out.println();
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
         
         
         // ���ñ���е����ݾ�����ʾ
         DefaultTableCellRenderer r=new DefaultTableCellRenderer();
         r.setHorizontalAlignment(SwingConstants.CENTER);
         jt.setDefaultRenderer(Object.class,r);
         jt.setRowHeight(35);
         jt.setAutoCreateRowSorter(true);
         jt.updateUI();
         
         //��ʼ�� jsp  
         jsp = new JScrollPane(jt);  
         
         //��jsp���뵽jframe  
         this.add(jsp);  
           this.setSize(400, 300);

	
	}
	
}
