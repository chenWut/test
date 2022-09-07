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

public class DeleteFlower extends JDialog {

	final int WIDTH = 400;
	final int HEIGTH = 800;

	public DeleteFlower(JFrame jf, String title, boolean isModel, String s1, String s2, String s3, String s4) {
		super(jf, title, isModel);
		this.setBounds(450, 200, 500, 350);

		// ��װ��¼���Ԫ��0
		Box vBox = Box.createVerticalBox();

		// ��װ��������
		Box fNameBox = Box.createHorizontalBox();
		JLabel fNameLabel = new JLabel("��������:");
		JTextField fNameArea = new JTextField(15);
		fNameArea.setText(s1);
		fNameArea.setEditable(false);
		fNameLabel.setBounds(100, 100, 100, 100);
		fNameBox.add(fNameLabel);
		fNameBox.add(Box.createHorizontalStrut(20));
		fNameBox.add(fNameArea);

		// ��װ��������
		Box fTypeBox = Box.createHorizontalBox();
		JLabel fTypeLabel = new JLabel("��������:");
		JTextField fTypeArea = new JTextField(15);
		fTypeArea.setText(s2);
		fTypeArea.setEditable(false);
		fTypeBox.add(fTypeLabel);
		fTypeBox.add(Box.createHorizontalStrut(20));
		fTypeBox.add(fTypeArea);

		// ��װ���ܿ��
		Box fCountBox = Box.createHorizontalBox();
		JLabel fCountLabel = new JLabel("���ܿ��:");
		JTextField fCountArea = new JTextField(15);
		fCountArea.setText(s3);
		fCountArea.setEditable(false);
		fCountBox.add(fCountLabel);
		fCountBox.add(Box.createHorizontalStrut(20));
		fCountBox.add(fCountArea);

		// ��װ���ܼ۸�
		Box fPriceBox = Box.createHorizontalBox();
		JLabel fPriceLabel = new JLabel("���ܼ۸�:");
		JTextField fPriceArea = new JTextField(15);
		fPriceArea.setText(s4);
		fPriceArea.setEditable(false);
		fPriceBox.add(fPriceLabel);
		fPriceBox.add(Box.createHorizontalStrut(20));
		fPriceBox.add(fPriceArea);

		// ��װ��ť
		Box btnBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("ɾ��");

		btnBox.add(addBtn);
		btnBox.add(Box.createHorizontalStrut(50));

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				ArrayList<String> inlist = new ArrayList<>();
				String name = fNameArea.getText();
				inlist.add(name);

				ToSQLserver conSQL = new ToSQLserver();
				conSQL.deleteFlowermethod(inlist);

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
