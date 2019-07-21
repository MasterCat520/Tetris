package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class RankingView extends JFrame{

    RankingView()throws Exception{
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

//        JFrame jf = new JFrame("���Դ���");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTable table = new JTable(rowData, columnNames);
        // �� ��ͷ ��ӵ�����������ʹ����ͨ���м�������ӱ��ʱ����ͷ �� ���� ��Ҫ�ֿ���ӣ�
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        // �� ������� ��ӵ���������
        panel.add(table, BorderLayout.CENTER);
        table.setFont(new Font("����",Font.CENTER_BASELINE,15));
        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1024,768);
        table.setSize(960,640);
        panel.setSize(960,640);
        this.setSize(1024,768);
        JButton jb = new JButton("back");
//        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null,"�����½��뱾ϵͳ");
            }
        });
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