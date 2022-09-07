package operate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utilToSQLserver.ToSQLserver;

public class DelectOrder extends JDialog {

	final int WIDTH = 400;
	final int HEIGTH = 800;

	public DelectOrder(JFrame jf, String title, boolean isModel, String s1, String s2, String s3, String s4, String s5,
			String s6, String s7) {
		super(jf, title);
		this.setBounds(450, 200, 500, 500);

		// ��װ��¼���Ԫ��0
		Box vBox = Box.createVerticalBox();

		// ��װ��������
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("���� ���       :");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(s1);
		fNameArea.setEditable(false);

		fNameLabel.setBounds(100, 100, 100, 100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);

		// ��װ�����л�������
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("���� ����       :");
		JTextField fTypeArea = new JTextField(15);
		fTypeArea.setText(s2);
		fTypeArea.setEditable(false);
		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);

		// ��װ�����л�������
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("��         ��       :");// ���ܼ۸�Ԫ/��:
		JTextField fCountArea = new JTextField(15);
		fCountArea.setText(s3);
		fCountArea.setEditable(false);
		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);

		// ��װ���ܼ۸�
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("���ܼ۸�Ԫ/��:");
		JTextField fPriceArea = new JTextField(15);
		fPriceArea.setText(s4);
		fPriceArea.setEditable(false);
		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);

		// ��װ�������
		Box PriceBox = Box.createHorizontalBox();
		JLabel PriceLabel = new JLabel("���� ���        :");
		JTextField PriceArea = new JTextField(15);
		PriceArea.setText(s5);
		PriceArea.setEditable(false);
		PriceBox.add(PriceLabel);
		PriceBox.add(Box.createHorizontalStrut(20));
		PriceBox.add(PriceArea);

		// ��װ��������
		Box dateBox = Box.createHorizontalBox();
		JLabel dateLabel = new JLabel("���� ����         ");
		JTextField dateArea = new JTextField(15);
		dateArea.setText(s6);
		dateArea.setEditable(false);
		dateBox.add(dateLabel);
		dateBox.add(Box.createHorizontalStrut(20));
		dateBox.add(dateArea);

		// ��װ������
		Box BuyerBox = Box.createHorizontalBox();
		JLabel BuyerLabel = new JLabel("  �� �� ��         ");
		JTextField BuyerArea = new JTextField(15);
		BuyerArea.setText(s7);
		BuyerArea.setEditable(false);
		BuyerBox.add(BuyerLabel);
		BuyerBox.add(Box.createHorizontalStrut(20));
		BuyerBox.add(BuyerArea);

		// ��װ��ť
		Box btnBox = Box.createHorizontalBox();
		JButton delectBtn = new JButton("ɾ��");

		btnBox.add(delectBtn);
		btnBox.add(Box.createHorizontalStrut(50));

		delectBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������

				// JOptionPane.showMessageDialog(jf, "һ��ɾ����������ת��������� �� ȡ����");

				ArrayList<String> inlist = new ArrayList<>();
				String name = fNameArea.getText();
				inlist.add(name);

				ToSQLserver conSQL = new ToSQLserver();
				conSQL.deleteOrdermethod(inlist);

				if (conSQL.success == 1) {
					JOptionPane.showMessageDialog(jf, "ɾ���ɹ�");
					dispose();
				} else {
					JOptionPane.showMessageDialog(jf, "ɾ��ʧ��");
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
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(PriceBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(dateBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(BuyerBox);
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
