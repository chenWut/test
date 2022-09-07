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

	// private Vector titlesV = new Vector<>();//存储标题
	// private Vector<Vector> dataV = new Vector<>();//存储数据

	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;

	// 定义数据库需要的全局变量
	JFrame jf = null;
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	public FlowerManageComponent(JFrame jf) {

		super(BoxLayout.Y_AXIS);
		this.jf = jf;

		// 组装视图
		JPanel btnPanel = new JPanel();
		Color color = new Color(203, 220, 217);
		btnPanel.setBackground(color);
		btnPanel.setMaximumSize(new Dimension(WIDTH, 40));
		;
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton addBtn = new JButton("添加");
		JButton updateBtn = new JButton("修改");
		JButton deleteBtn = new JButton("删除");
		JButton selectBtn = new JButton("查找");
		btnPanel.add(addBtn);
		btnPanel.add(updateBtn);
		btnPanel.add(deleteBtn);
		btnPanel.add(selectBtn);
		this.add(btnPanel);

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new AddFlower(jf, "添加花卉", true);
			}
		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				//int selectRow = jt.getSelectedRow();

				//if (selectRow == -1)
				//	JOptionPane.showMessageDialog(jf, "选择要修改的条目！");

				//new UpdataFlower(jf, "修改花卉信息", true);
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				//new DeleteFlower(jf, "删除花卉", true);
			}
		});
		
		selectBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new SelectFlower();
				
			}
		});
		// 建立表格
		columnNames = new Vector();
		// 设置列名
		columnNames.add("花名");
		columnNames.add("种类");
		columnNames.add("库存");
		columnNames.add("价格/元");
		// columnNames.add("籍贯");
		// columnNames.add("系别");

		rowData = new Vector();
		// rowData可以存放多行,开始从数据库里取

		try {
			// 加载驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 得到连接
			ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB", "sa", "root");

			ps = ct.prepareStatement("select * from flower");

			rs = ps.executeQuery();

			rowData.clear();

			while (rs.next()) {
				// rowData可以存放多行
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				// hang.add(rs.getString(5));
				// hang.add(rs.getString(6));

				// 加入到rowData
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

		// 初始化Jtable
		jt = new JTable(rowData, columnNames);

		jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
//支持拖动多选
					int[] rows = jt.getSelectedRows();

// int[] cols = table.getSelectedColumns();//选中的列
					
					for (int i : rows) {

						System.out.println("\t" + jt.getValueAt(i, 0) + "\t" + jt.getValueAt(i, 1)+ "\t" + jt.getValueAt(i, 2)+ "\t" + jt.getValueAt(i, 3));
						String s1 = (String) jt.getValueAt(i, 0);
						String s2 = (String) jt.getValueAt(i, 1);
						String s3 = (String) jt.getValueAt(i, 2);
						String s4 = (String) jt.getValueAt(i, 3);
						updateBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO 自动生成的方法存根
								new UpdataFlower(jf, "修改花卉", true,s1,s2,s3,s4);
								//("\t" + jt.getValueAt(i, 0) + "\t" + jt.getValueAt(i, 1)+ "\t" + jt.getValueAt(i, 2)+ "\t" + jt.getValueAt(i, 3))
							}
						});
						deleteBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO 自动生成的方法存根
								new DeleteFlower(jf, "删除花卉", true,s1,s2,s3,s4);
								//("\t" + jt.getValueAt(i, 0) + "\t" + jt.getValueAt(i, 1)+ "\t" + jt.getValueAt(i, 2)+ "\t" + jt.getValueAt(i, 3))
							}
						});
					}
					
				}

			}

		});
		
		
		// 设置表格中的数据居中显示
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        jt.setDefaultRenderer(Object.class,r);
        jt.setAutoCreateRowSorter(true);
		jt.updateUI();

		// 初始化 jsp
		jsp = new JScrollPane(jt);

		
		// 把jsp放入到jframe
		this.add(jsp);


		this.setSize(400, 300);

	}

}
