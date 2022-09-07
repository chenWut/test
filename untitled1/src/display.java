import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class display {
    final int WIDTH = 500;
    final int HEIGHT = 230;
    JFrame jf = new JFrame("显示信息");
    public display() {
    }

    public static void main(String[] args) {
        (new display()).init();
    }
    public void init() {
        this.jf.setBounds(400, 200, 500, 300);
        this.jf.setResizable(true);

        Box vBox = Box.createVerticalBox();

        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("学   号:");
        final JTextField uField = new JTextField(15);
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);


        Box nBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("姓   名:");
        final JTextField nField = new JTextField(10);
        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(20));
        nBox.add(nField);

        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("生   日:");
        final JPasswordField pField = new JPasswordField(10);
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("确认");

        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(50));

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String num = uField.getText();
                String name = nField.getText();
                String data = pField.getText();
                //JOptionPane.showMessageDialog(jf,"成功");
                new show().init(num,name,data);
            }
        });


        vBox.add(Box.createVerticalStrut(40));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));
        this.jf.add(hBox);
        this.jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.jf.setVisible(true);
    }
}
