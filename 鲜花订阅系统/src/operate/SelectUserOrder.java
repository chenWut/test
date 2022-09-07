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
	
	//便于可以在按钮事件 SelectAction 内部类中访问
    private final JPanel jp;
    private final JTextField name;
    //用数组接收查询结果，方便管理数据
    public ArrayList<String> sellist = new ArrayList<>();
    JLabel jlToast = new JLabel("", JLabel.CENTER);
    //将文本内容对象放到构造函数外面，便于可以在按钮事件 SelectAction 内部类中访问
    JTextField textBno1 = new JTextField();
    JTextArea textVname1 = new JTextArea();
    JTextArea textBauthor1 = new JTextArea();
    JTextArea textBclass1 = new JTextArea();
    JTextArea textBclass19 = new JTextArea();


    public SelectUserOrder() {
        setSize(850, 350);
        setLocation(200, 100);
        setResizable(false);
        setTitle("查询订单");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        jp = new JPanel();//查询面板
        JPanel jpText = new JPanel();//查询结果面板
        JPanel jpToast = new JPanel();//查询是否成功面板

        jp.setLayout(new FlowLayout());//将JFrame设置为流式布局
        
        jpText.setLayout(new GridLayout(2, 5, 5, 10));


        JLabel textBno = new JLabel("订单编号", JLabel.CENTER);
        JLabel textBname = new JLabel("花卉名称", JLabel.CENTER);
        JLabel textBauthor = new JLabel("数量", JLabel.CENTER);
        JLabel textBclass = new JLabel("单价（元/束）", JLabel.CENTER);
        JLabel dateJLabel = new JLabel("总价", JLabel.CENTER);
        //JLabel dateJLabel = new JLabel("日期", JLabel.CENTER);

        Dimension dimen = new Dimension(120, 30);//构造一个大小对象

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

        jlToast.setPreferredSize(new Dimension(200, 40));//设置提示框大小
        jlToast.setFont(new Font(null, 10, 20));//设置提示文本字体
        jlToast.setForeground(Color.RED);//设置字体颜色

        JLabel toast = new JLabel("花卉名称:");//文本框
        name = new JTextField();
        JButton select = new JButton("查询");
        //设置编辑框的大小
        name.setPreferredSize(new Dimension(100, 30));
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
        jpText.add(dateJLabel);
        jpText.add(textBno1);
        jpText.add(textVname1);
        jpText.add(textBauthor1);
        jpText.add(textBclass1);
        jpText.add(textBclass19);
        //查询是否成功组件
        jpToast.add(jlToast);

//因为这个类是直接继承自JFrame，所以直接调用JFrame中的方法
        add(jp);
        add(jpText);
        add(jpToast);
        pack();
        setVisible(true);//这段代码一般放在最后，设置窗口是否可见，默认是false
    }

//设置按钮监听类，继承自 ActionListener 接口
    private class SelectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String flowerName = name.getText();
            
            ToSQLserver conSQL = new ToSQLserver();//实例化数据库查询类
            sellist = conSQL.selectUserDDmethod(flowerName);
            System.out.println(sellist);
            if (sellist.size() != 0) {
                jlToast.setText("查询成功");
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
                jlToast.setText("抱歉，没有这个订单");
                textBno1.setText(null);
                textVname1.setText(null);
                textBauthor1.setText(null);
                textBclass1.setText(null);
            }
            //将查询得到的数据添加到 JTextArea 对象上

            jp.updateUI();//更新界面显示查询结果
        }
    }
    public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new SelectUserOrder();
	}
    
}
