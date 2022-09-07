package operate;

import javax.swing.*;

import utilToSQLserver.ToSQLserver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectFlower extends JFrame {
    //便于可以在按钮事件 SelectAction 内部类中访问
    private final JPanel jp;
    private final JTextField name;
    //用数组接收查询结果，方便管理数据
    public ArrayList<String> sellist = new ArrayList<>();
    JLabel jlToast = new JLabel("", JLabel.CENTER);
    //将文本内容对象放到构造函数外面，便于可以在按钮事件 SelectAction 内部类中访问
    JTextArea textBno1 = new JTextArea();
    JTextArea textVname1 = new JTextArea();
    JTextArea textBauthor1 = new JTextArea();
    JTextArea textBclass1 = new JTextArea();


    public SelectFlower() {
        setSize(550, 350);
        setLocation(500, 200);
        setResizable(false);
        setTitle("查询花卉");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        jp = new JPanel();//查询面板
        JPanel jpText = new JPanel();//查询结果面板
        JPanel jpToast = new JPanel();//查询是否成功面板

        jp.setLayout(new FlowLayout());//将JFrame设置为流式布局

        jpText.setLayout(new GridLayout(2, 4, 10, 10));


        JLabel textBno = new JLabel("花名", JLabel.CENTER);
        JLabel textBname = new JLabel("种类", JLabel.CENTER);
        JLabel textBauthor = new JLabel("库存", JLabel.CENTER);
        JLabel textBclass = new JLabel("价格", JLabel.CENTER);

        Dimension dimen = new Dimension(120, 30);//构造一个大小对象

        textBno.setPreferredSize(dimen);
        textBname.setPreferredSize(dimen);
        textBauthor.setPreferredSize(dimen);
        textBclass.setPreferredSize(dimen);
        textBno1.setPreferredSize(dimen);
        textVname1.setPreferredSize(dimen);
        textBauthor1.setPreferredSize(dimen);
        textBclass1.setPreferredSize(dimen);

        jlToast.setPreferredSize(new Dimension(200, 40));//设置提示框大小
        jlToast.setFont(new Font(null, 1, 20));//设置提示文本字体
        jlToast.setForeground(Color.RED);//设置字体颜色

        JLabel toast = new JLabel("花名:");//文本框
        name = new JTextField();
        JButton select = new JButton("查询");
        //设置编辑框的大小
        name.setPreferredSize(new Dimension(250, 30));
        //按钮添加事件
        SelectAction selectAction = new SelectAction();
        select.addActionListener(selectAction);
        //输入查询组件
        jp.add(toast);
        jp.add(name);
        jp.add(select);
        //查询结果显示组件
        jpText.add(textBno);
        jpText.add(textBname);
        jpText.add(textBauthor);
        jpText.add(textBclass);
        jpText.add(textBno1);
        jpText.add(textVname1);
        jpText.add(textBauthor1);
        jpText.add(textBclass1);
        //查询是否成功组件
        jpToast.add(jlToast);

//因为这个类是直接继承自JFrame，所以直接调用JFrame中的方法
        add(jp);
        add(jpText);
        add(jpToast);
        setVisible(true);//这段代码一般放在最后，设置窗口是否可见，默认是false
    }

//设置按钮监听类，继承自 ActionListener 接口
    private class SelectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String flowerName = name.getText();

            ToSQLserver conSQL = new ToSQLserver();//实例化数据库查询类
            sellist = conSQL.selectFlowermethod(flowerName);
            System.out.println(sellist);
            if (sellist.size() != 0) {
                jlToast.setText("查询成功");
                textBno1.setText(sellist.get(0));
                textVname1.setText(sellist.get(1));
                textBauthor1.setText(sellist.get(2));
                textBclass1.setText(sellist.get(3));
            } else {
                jlToast.setText("抱歉，没有这种花");
                textBno1.setText(null);
                textVname1.setText(null);
                textBauthor1.setText(null);
                textBclass1.setText(null);
            }
            //将查询得到的数据添加到 JTextArea 对象上

            jp.updateUI();//更新界面显示查询结果
        }
    }
}
