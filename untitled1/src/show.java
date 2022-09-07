import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class show {
    public show (){

    }

    public void init(String num, String name, String data) {
        final JFrame jf = new JFrame(name + "     你的信息如下...");
        final int WIDTH = 300;
        final int HEIGHT = 100;
        jf.setBounds(400, 200, 500, 300);
        jf.setResizable(true);
        JLabel j1 = new JLabel("学号：" + num);
        JLabel j2 = new JLabel("姓名：" + name);
        JLabel j3 = new JLabel("生日：" + data);
        Box vBox = Box.createVerticalBox();

        Box uBox = Box.createHorizontalBox();
        uBox.add(j1);
        uBox.add(Box.createHorizontalStrut(20));

        Box nBox = Box.createHorizontalBox();
        nBox.add(j2);
        nBox.add(Box.createHorizontalStrut(20));

        Box pBox = Box.createHorizontalBox();
        final JPasswordField pField = new JPasswordField(10);
        pBox.add(j3);
        pBox.add(Box.createHorizontalStrut(20));
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));
        jf.add(hBox);
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        jf.setVisible(true);
    }
}
