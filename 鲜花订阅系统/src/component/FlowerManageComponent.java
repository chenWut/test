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

import operate.AddFlower;
import operate.DeleteFlower;
import operate.SelectFlower;
import operate.UpdataFlower;


public class FlowerManageComponent extends Box {

	final int WIDTH = 850;
	final int HEIGHT = 600;

	private JTable table;
	private Vector<String> titles;
	private Vector<Vector> tableData;
	private TableModel tableModel;

	// private Vector titlesV = new Vector<>();//�洢����
	// private Vector<Vector> dataV = new Vector<>();//�洢����

	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;

	// �������ݿ���Ҫ��ȫ�ֱ���
	JFrame jf = null;
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	public FlowerManageComponent(JFrame jf) {

		super(BoxLayout.Y_AXIS);
		this.jf = jf;

		// ��װ��ͼ
		JPanel btnPanel = new JPanel();
		Color color = new Color(203, 220, 217);
		btnPanel.setBackground(color);
		btnPanel.setMaximumSize(new Dimension(WIDTH, 40));
		;
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton addBtn = new JButton("���");
		JButton updateBtn = new JButton("�޸�");
		JButton deleteBtn = new JButton("ɾ��");
		JButton selectBtn = new JButton("����");
		btnPanel.add(addBtn);
		btnPanel.add(updateBtn);
		btnPanel.add(deleteBtn);
		btnPanel.add(selectBtn);
		this.add(btnPanel);

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				new AddFlower(jf, "��ӻ���", true);
			}
		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				//int selectRow = jt.getSelectedRow();

				//if (selectRow == -1)
				//	JOptionPane.showMessageDialog(jf, "ѡ��Ҫ�޸ĵ���Ŀ��");

				//new UpdataFlower(jf, "�޸Ļ�����Ϣ", true);
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				//new DeleteFlower(jf, "ɾ������", true);
			}
		});
		
		selectBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				new SelectFlower();
				
			}
		});
		// �������
		columnNames = new Vector();
		// ��������
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("���");
		columnNames.add("�۸�/Ԫ");
		// columnNames.add("����");
		// columnNames.add("ϵ��");

		rowData = new Vector();
		// rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ

		try {
			// ��������
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// �õ�����
			ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB", "sa", "root");

			ps = ct.prepareStatement("select * from flower");

			rs = ps.executeQuery();

			rowData.clear();

			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				// hang.add(rs.getString(5));
				// hang.add(rs.getString(6));

				// ���뵽rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ��ʼ��Jtable
		jt = new JTable(rowData, columnNames);

		jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
//֧���϶���ѡ
					int[] rows = jt.getSelectedRows();

// int[] cols = table.getSelectedColumns();//ѡ�е���
					
					for (int i : rows) {

						System.out.println("\t" + jt.getValueAt(i, 0) + "\t" + jt.getValueAt(i, 1)+ "\t" + jt.getValueAt(i, 2)+ "\t" + jt.getValueAt(i, 3));
						String s1 = (String) jt.getValueAt(i, 0);
						String s2 = (String) jt.getValueAt(i, 1);
						String s3 = (String) jt.getValueAt(i, 2);
						String s4 = (String) jt.getValueAt(i, 3);
						updateBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO �Զ����ɵķ������
								new UpdataFlower(jf, "�޸Ļ���", true,s1,s2,s3,s4);
								//("\t" + jt.getValueAt(i, 0) + "\t" + jt.getValueAt(i, 1)+ "\t" + jt.getValueAt(i, 2)+ "\t" + jt.getValueAt(i, 3))
							}
						});
						deleteBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO �Զ����ɵķ������
								new DeleteFlower(jf, "ɾ������", true,s1,s2,s3,s4);
								//("\t" + jt.getValueAt(i, 0) + "\t" + jt.getValueAt(i, 1)+ "\t" + jt.getValueAt(i, 2)+ "\t" + jt.getValueAt(i, 3))
							}
						});
					}
					
				}

			}

		});
		
		
		// ���ñ���е����ݾ�����ʾ
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        jt.setDefaultRenderer(Object.class,r);
        jt.setAutoCreateRowSorter(true);
		jt.updateUI();

		// ��ʼ�� jsp
		jsp = new JScrollPane(jt);

		
		// ��jsp���뵽jframe
		this.add(jsp);


		this.setSize(400, 300);

	}

}
