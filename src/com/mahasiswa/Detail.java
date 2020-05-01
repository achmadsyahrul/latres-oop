package com.mahasiswa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Detail extends JFrame implements ActionListener {
    private Connection con;
    private Statement statement;
    private ResultSet result;
    private String nim,nama,alamat,jk,agama,prodi,angkatan;
    private JLabel labelNama, labelNIM, labelTL, labelJK, labelAlamat, labelAgama, labelProdi, labelAngkatan;
    private JButton btnBack, btnUpdate, btnDelete;
    private Date tgl_lahir;

    public Detail(){

    }

    public Detail(String nim){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahasiswa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            statement = con.createStatement();
            String query = "select * from mahasiswa where nim = " + nim;
            result = statement.executeQuery(query);
            while(result.next()){
                this.nim = result.getString("nim");
                nama = result.getString("nama");
                alamat = result.getString("alamat");
                jk = result.getString("jk");
                prodi = result.getString("prodi");
                angkatan = result.getString("angkatan");
                tgl_lahir = result.getDate("tgl_lahir");
                agama = result.getString("agama");
            }
            setTitle("Data : " + nama);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(350,300);
            labelNama = new JLabel(" Nama Lengkap : " + nama);
            labelNIM = new JLabel(" NIM : " + this.nim);
            String tgl = new SimpleDateFormat("dd MMMM yyyy").format(tgl_lahir);
            labelTL = new JLabel(" Tanggal Lahir : " + tgl);
            labelJK = new JLabel(" Jenis Kelamin : " + jk);
            labelAlamat = new JLabel(" Alamat : " + alamat);
            labelAgama = new JLabel(" Agama : " + agama);
            labelProdi = new JLabel(" Prodi : " + prodi);
            labelAngkatan = new JLabel(" Angkatan : " + angkatan);
            btnBack = new JButton("Kembali");
            btnUpdate = new JButton("Edit");
            btnDelete = new JButton("Hapus");
            setLayout(null);
            add(labelNama);
            add(labelNIM);
            add(labelTL);
            add(labelJK);
            add(labelAlamat);
            add(labelAgama);
            add(labelProdi);
            add(labelAngkatan);
            add(btnBack);
            add(btnUpdate);
            add(btnDelete);
            labelNama.setBounds(10,10,320,20);
            labelNIM.setBounds(10,35,320,20);
            labelTL.setBounds(10,60,320,20);
            labelJK.setBounds(10,85,320,20);
            labelAlamat.setBounds(10,110,320,20);
            labelAgama.setBounds(10,135,320,20);
            labelProdi.setBounds(10,160,320,20);
            labelAngkatan.setBounds(10,185,320,20);
            btnBack.setBounds(10,225,90,20);
            btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnBack.addActionListener(this);
            btnUpdate.setBounds(110,225,90,20);
            btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnUpdate.setBackground(Color.blue);
            btnUpdate.setForeground(Color.white);
            btnUpdate.addActionListener(this);
            btnDelete.setBounds(210,225,90,20);
            btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnDelete.setBackground(Color.red);
            btnDelete.setForeground(Color.white);
            btnDelete.addActionListener(this);
            setResizable(false);
            setLocation(450,200);
            setVisible(true);
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            dispose();
            Mahasiswa m = new Mahasiswa();
        }
        else if(e.getSource()==btnUpdate){
            dispose();
            Update u = new Update(nim);
        }
        else if(e.getSource()==btnDelete){
            dispose();
            Form f = new Delete(nim);
            Mahasiswa m = new Mahasiswa();
        }
    }
}
