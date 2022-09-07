package operate;

import javax.swing.*;

import utilToSQLserver.ToSQLserver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectFlower extends JFrame {
    //���ڿ����ڰ�ť�¼� SelectAction �ڲ����з���
    private final JPanel jp;
    private final JTextField name;
    //��������ղ�ѯ����������������
    public ArrayList<String> sellist = new ArrayList<>();
    JLabel jlToast = new JLabel("", JLabel.CENTER);
    //���ı����ݶ���ŵ����캯�����棬���ڿ����ڰ�ť�¼� SelectAction �ڲ����з���
    JTextArea textBno1 = new JTextArea();
    JTextArea textVname1 = new JTextArea();
    JTextArea textBauthor1 = new JTextArea();
    JTextArea textBclass1 = new JTextArea();


    public SelectFlower() {
        setSize(550, 350);
        setLocation(500, 200);
        setResizable(false);
        setTitle("��ѯ����");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        jp = new JPanel();//��ѯ���
        JPanel jpText = new JPanel();//��ѯ������
        JPanel jpToast = new JPanel();//��ѯ�Ƿ�ɹ����

        jp.setLayout(new FlowLayout());//��JFrame����Ϊ��ʽ����

        jpText.setLayout(new GridLayout(2, 4, 10, 10));


        JLabel textBno = new JLabel("����", JLabel.CENTER);
        JLabel textBname = new JLabel("����", JLabel.CENTER);
        JLabel textBauthor = new JLabel("���", JLabel.CENTER);
        JLabel textBclass = new JLabel("�۸�", JLabel.CENTER);

        Dimension dimen = new Dimension(120, 30);//����һ����С����

        textBno.setPreferredSize(dimen);
        textBname.setPreferredSize(dimen);
        textBauthor.setPreferredSize(dimen);
        textBclass.setPreferredSize(dimen);
        textBno1.setPreferredSize(dimen);
        textVname1.setPreferredSize(dimen);
        textBauthor1.setPreferredSize(dimen);
        textBclass1.setPreferredSize(dimen);

        jlToast.setPreferredSize(new Dimension(200, 40));//������ʾ���С
        jlToast.setFont(new Font(null, 1, 20));//������ʾ�ı�����
        jlToast.setForeground(Color.RED);//����������ɫ

        JLabel toast = new JLabel("����:");//�ı���
        name = new JTextField();
        JButton select = new JButton("��ѯ");
        //���ñ༭��Ĵ�С
        name.setPreferredSize(new Dimension(250, 30));
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
        jpText.add(textBno1);
        jpText.add(textVname1);
        jpText.add(textBauthor1);
        jpText.add(textBclass1);
        //��ѯ�Ƿ�ɹ����
        jpToast.add(jlToast);

//��Ϊ�������ֱ�Ӽ̳���JFrame������ֱ�ӵ���JFrame�еķ���
        add(jp);
        add(jpText);
        add(jpToast);
        setVisible(true);//��δ���һ�����������ô����Ƿ�ɼ���Ĭ����false
    }

//���ð�ť�����࣬�̳��� ActionListener �ӿ�
    private class SelectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String flowerName = name.getText();

            ToSQLserver conSQL = new ToSQLserver();//ʵ�������ݿ��ѯ��
            sellist = conSQL.selectFlowermethod(flowerName);
            System.out.println(sellist);
            if (sellist.size() != 0) {
                jlToast.setText("��ѯ�ɹ�");
                textBno1.setText(sellist.get(0));
                textVname1.setText(sellist.get(1));
                textBauthor1.setText(sellist.get(2));
                textBclass1.setText(sellist.get(3));
            } else {
                jlToast.setText("��Ǹ��û�����ֻ�");
                textBno1.setText(null);
                textVname1.setText(null);
                textBauthor1.setText(null);
                textBclass1.setText(null);
            }
            //����ѯ�õ���������ӵ� JTextArea ������

            jp.updateUI();//���½�����ʾ��ѯ���
        }
    }
}
