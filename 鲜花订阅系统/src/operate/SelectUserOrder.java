package operate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import utilToSQLserver.ToSQLserver;

public class SelectUserOrder extends JFrame{
	
	//���ڿ����ڰ�ť�¼� SelectAction �ڲ����з���
    private final JPanel jp;
    private final JTextField name;
    //��������ղ�ѯ����������������
    public ArrayList<String> sellist = new ArrayList<>();
    JLabel jlToast = new JLabel("", JLabel.CENTER);
    //���ı����ݶ���ŵ����캯�����棬���ڿ����ڰ�ť�¼� SelectAction �ڲ����з���
    JTextField textBno1 = new JTextField();
    JTextArea textVname1 = new JTextArea();
    JTextArea textBauthor1 = new JTextArea();
    JTextArea textBclass1 = new JTextArea();
    JTextArea textBclass19 = new JTextArea();


    public SelectUserOrder() {
        setSize(850, 350);
        setLocation(200, 100);
        setResizable(false);
        setTitle("��ѯ����");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        jp = new JPanel();//��ѯ���
        JPanel jpText = new JPanel();//��ѯ������
        JPanel jpToast = new JPanel();//��ѯ�Ƿ�ɹ����

        jp.setLayout(new FlowLayout());//��JFrame����Ϊ��ʽ����
        
        jpText.setLayout(new GridLayout(2, 5, 5, 10));


        JLabel textBno = new JLabel("�������", JLabel.CENTER);
        JLabel textBname = new JLabel("��������", JLabel.CENTER);
        JLabel textBauthor = new JLabel("����", JLabel.CENTER);
        JLabel textBclass = new JLabel("���ۣ�Ԫ/����", JLabel.CENTER);
        JLabel dateJLabel = new JLabel("�ܼ�", JLabel.CENTER);
        //JLabel dateJLabel = new JLabel("����", JLabel.CENTER);

        Dimension dimen = new Dimension(120, 30);//����һ����С����

        textBno.setPreferredSize(dimen);
        textBname.setPreferredSize(dimen);
        textBauthor.setPreferredSize(dimen);
        textBclass.setPreferredSize(dimen);
        dateJLabel.setPreferredSize(dimen);
        textBno1.setPreferredSize(dimen);
        textVname1.setPreferredSize(dimen);
        textBauthor1.setPreferredSize(dimen);
        textBclass1.setPreferredSize(dimen);
        textBclass19.setPreferredSize(dimen);

        jlToast.setPreferredSize(new Dimension(200, 40));//������ʾ���С
        jlToast.setFont(new Font(null, 10, 20));//������ʾ�ı�����
        jlToast.setForeground(Color.RED);//����������ɫ

        JLabel toast = new JLabel("��������:");//�ı���
        name = new JTextField();
        JButton select = new JButton("��ѯ");
        //���ñ༭��Ĵ�С
        name.setPreferredSize(new Dimension(100, 30));
        //��ť����¼�
        SelectAction selectAction = new SelectAction();
        select.addActionListener(selectAction);
        //�����ѯ���
        jp.add(toast);
        jp.add(name);
        jp.add(select);
        //��ѯ�����ʾ���
        jpText.add(textBno);
        jpText.add(textBname);
        jpText.add(textBauthor);
        jpText.add(textBclass);
        jpText.add(dateJLabel);
        jpText.add(textBno1);
        jpText.add(textVname1);
        jpText.add(textBauthor1);
        jpText.add(textBclass1);
        jpText.add(textBclass19);
        //��ѯ�Ƿ�ɹ����
        jpToast.add(jlToast);

//��Ϊ�������ֱ�Ӽ̳���JFrame������ֱ�ӵ���JFrame�еķ���
        add(jp);
        add(jpText);
        add(jpToast);
        pack();
        setVisible(true);//��δ���һ�����������ô����Ƿ�ɼ���Ĭ����false
    }

//���ð�ť�����࣬�̳��� ActionListener �ӿ�
    private class SelectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String flowerName = name.getText();
            
            ToSQLserver conSQL = new ToSQLserver();//ʵ�������ݿ��ѯ��
            sellist = conSQL.selectUserDDmethod(flowerName);
            System.out.println(sellist);
            if (sellist.size() != 0) {
                jlToast.setText("��ѯ�ɹ�");
                textBno1.setText(sellist.get(0));
                textBno1.setEditable(false);
                textVname1.setText(sellist.get(1));
                
                //String numString= sellist.get(2);
                //int numint = Integer.parseInt(numString);
                //System.out.print(numint);
                //String priceString= sellist.get(3);
                //int priceint = Integer.parseInt(priceString);
                //float r = Float.valueOf(priceString).floatValue();
               // System.out.print(r);
                
                
                textBauthor1.setText(sellist.get(2));
                textBclass1.setText(sellist.get(3));
                textBclass19.setText(sellist.get(4));
            } else {
                jlToast.setText("��Ǹ��û���������");
                textBno1.setText(null);
                textVname1.setText(null);
                textBauthor1.setText(null);
                textBclass1.setText(null);
            }
            //����ѯ�õ���������ӵ� JTextArea ������

            jp.updateUI();//���½�����ʾ��ѯ���
        }
    }
    public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new SelectUserOrder();
	}
    
}
