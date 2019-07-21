package ����˹����;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Collections;

public class RankingView {
    public void Op() throws Exception{

        // ����������壬ʹ�ñ߽粼��
        JPanel panel = new JPanel(new BorderLayout());

        //���ݿ��������������
        Connection connection = GameFrame.getCon();
        Statement stmt = null;

        //ִ�в�ѯ
        stmt = connection.createStatement();
        //дsql��� ?ռλ��
        String sql = "select id,name,score from dongdong";
        ResultSet rs = stmt.executeQuery(sql);

        // ��ͷ��������
        Object[] columnNames = {"ID","Score"};

        // �������������
        Object[][] rowData = new Object[10][2];

        int i = 0;
        //չ����������ݿ�
        while (rs.next()){
            //ͨ���ֶμ���
            String name = rs.getString("name");
            int score = rs.getInt("score");
            rowData[i][0] = name;
            rowData[i][1] = score;
            i++;
        }

        JFrame jf = new JFrame("���Դ���");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.addWindowListener(new WindowAdapter() {
                                 public void windowClosing(WindowEvent e) {
                                     jf.setVisible(false);
//                                     JOptionPane.showMessageDialog(null,"666");
                                     String[] a = null;
                                     try {
                                         Main.main(a);
                                     }catch (Exception e1){
                                         e1.printStackTrace();
                                     }
                                 }
                             }
        );

        JTable table = new JTable(rowData, columnNames);
        // �� ��ͷ ��ӵ�����������ʹ����ͨ���м�������ӱ��ʱ����ͷ �� ���� ��Ҫ�ֿ���ӣ�
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        // �� ������� ��ӵ���������
        panel.add(table, BorderLayout.CENTER);

        table.setFont(new Font("����",Font.CENTER_BASELINE,20));
        jf.setContentPane(panel);
        jf.pack();
        jf.setLocationRelativeTo(null);
        table.setSize(1024,768);
        panel.setSize(1025,768);
        jf.setSize(1024,768);
        JButton jb = new JButton("back");
        jf.setVisible(true);
    }

    //�������ݿ�ķ���
    public static Connection getCon() throws ClassNotFoundException, SQLException {
        //����һ��������
        Connection con = null;
        //ͨ��class���ҵ�������
        Class.forName("com.mysql.cj.jdbc.Driver");

        //ͨ�������ཨ�����Ӳ���con����ֵ
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dong1?useSSL=false&serverTimezone=UTC","root","dby19991016");

        return con;

    }
}