package com.mahasiswa;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Mahasiswa extends JFrame implements ActionListener{
    private Connection con;
    private Statement statement;
    private ResultSet result;
    private int n;
    private Object[][] data = new Object[0][0];
    private JTable table;
    private JButton btnBack;

    public Mahasiswa(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahasiswa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            n = numRows();
            statement = con.createStatement();
            String query = "select * from mahasiswa";
            result = statement.executeQuery(query);

            final String[] tableTitle = {"NIM", "Nama", "Alamat","Gender","Prodi","Angkatan"};
            data = new Object[n][6];
            int row = 0;
            while(result.next()){
                data[row][0] = result.getString("nim");
                data[row][1] = result.getString("nama");
                data[row][2] = result.getString("alamat");
                data[row][3] = result.getString("jk");
                data[row][4] = result.getString("prodi");
                data[row][5] = result.getString("angkatan");
                row++;
            }
            statement.close();
            con.close();

            setTitle("Data Mahasiswa JTI");
            setSize(600,250);

            btnBack = new JButton(" Kembali ");
            table = new JTable(data,tableTitle);
            table.setBounds(30,40,400,600);
            JScrollPane sp=new JScrollPane(table);
            sp.setPreferredSize(new Dimension(500,80));
            this.getContentPane().add(BorderLayout.CENTER, sp);

            this.getContentPane().add(BorderLayout.SOUTH, btnBack);
            btnBack.addActionListener(this);
            btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            table.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    dispose();
                    Detail detail = new Detail(table.getValueAt(table.getSelectedRow(), 0).toString());
                }
            });
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(350,200);
            setVisible(true);
        }catch (Exception e){
            System.out.println("Error : " + e);
        }
    }

    private int numRows(){
        try{
            String query = "select count(*) from mahasiswa";
            statement = con.createStatement();
            result = statement.executeQuery(query);
            while(result.next()){
                int rows = result.getInt("count(*)");
                return rows;
            }
        }catch (Exception e){
            System.out.println("Error : " + e);
        }
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            dispose();
            new Menu();
        }
    }
}
