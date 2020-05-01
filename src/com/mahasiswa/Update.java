package com.mahasiswa;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Update extends Form implements ActionListener {

    private JTextField fieldNama, fieldNIM, fieldAlamat;
    private JLabel labelNama, labelNIM, labelTL, labelJK, labelAlamat, labelAgama;
    private JDateChooser tglLahir;
    private JRadioButton lakilaki, perempuan;
    private JComboBox cmbAgama;
    private String[] namaAgama =
            {"","Islam","Kristen","Katolik","Hindu","Budha","Konghucu"};
    private ButtonGroup btnJK;
    private JButton btnSubmit, btnReset, btnBack;

    private Connection con;
    private Statement statement;
    private ResultSet result;

    private String nim,nama,alamat,jk,agama;
    private Date tgl_lahir;
    private int id;

    public Update(){

    }

    public Update(String nim) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahasiswa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            statement = con.createStatement();
            String query = "select * from mahasiswa where nim = " + nim;
            result = statement.executeQuery(query);
            while(result.next()){
                id = result.getInt("id");
                this.nim = result.getString("nim");
                nama = result.getString("nama");
                alamat = result.getString("alamat");
                jk = result.getString("jk");
                tgl_lahir = result.getDate("tgl_lahir");
                agama = result.getString("agama");
            }
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }
        setTitle("Update " + nama);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,275);

        fieldNama = new JTextField(nama, 10);
        fieldNIM = new JTextField(this.nim, 10);
        labelNama = new JLabel(" Nama Lengkap ");
        labelNIM = new JLabel(" NIM ");
        labelTL = new JLabel(" Tanggal Lahir ");
        tglLahir = new JDateChooser();
        tglLahir.setDate(tgl_lahir);
        labelJK = new JLabel(" Jenis Kelamin ");
        lakilaki = new JRadioButton(" Laki-Laki ");
        perempuan = new JRadioButton(" Perempuan ");
        if(jk.equals("Perempuan")){
            perempuan.setSelected(true);
        }
        else{
            lakilaki.setSelected(true);
        }
        labelAlamat = new JLabel(" Alamat ");
        fieldAlamat = new JTextField(alamat,10);
        labelAgama = new JLabel(" Agama ");
        cmbAgama = new JComboBox(namaAgama);
        if(agama.equals("Islam")){
            cmbAgama.setSelectedIndex(1);
        }
        else if(agama.equals("Kristen")){
            cmbAgama.setSelectedIndex(2);
        }
        else if(agama.equals("Katolik")){
            cmbAgama.setSelectedIndex(3);
        }
        else if(agama.equals("Hindu")){
            cmbAgama.setSelectedIndex(4);
        }
        else if(agama.equals("Budha")){
            cmbAgama.setSelectedIndex(5);
        }
        else if(agama.equals("Konghucu")){
            cmbAgama.setSelectedIndex(6);
        }
        else{
            cmbAgama.setSelectedIndex(0);
        }
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        btnJK = new ButtonGroup();
        btnJK.add(lakilaki);
        btnJK.add(perempuan);
        setLayout(null);
        add(labelNama);
        add(fieldNama);
        add(labelNIM);
        add(fieldNIM);
        add(labelTL);
        add(tglLahir);
        add(labelJK);
        add(lakilaki);
        add(perempuan);
        add(labelAlamat);
        add(fieldAlamat);
        add(labelAgama);
        add(cmbAgama);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelNama.setBounds(10,10,120,20);
        fieldNama.setBounds(130,10,190,20);
        labelNIM.setBounds(10,35,120,20);
        fieldNIM.setBounds(130,35,190,20);
        labelTL.setBounds(10,60,120,20);
        tglLahir.setBounds(130,60,190,20);
        tglLahir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelJK.setBounds(10,85,120,20);
        lakilaki.setBounds(130,85,100,20);
        lakilaki.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        perempuan.setBounds(230,85,100,20);
        perempuan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelAlamat.setBounds(10,110,150,20);
        fieldAlamat.setBounds(130,110,190,20);
        labelAgama.setBounds(10,135,150,20);
        cmbAgama.setBounds(130,135,190,20);
        btnSubmit.setBounds(75,175,120,20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnReset.setBounds(200,175,120,20);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(this);
        btnBack.setBounds(10,210,320,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit) {

            if(fieldNama.getText().equals("")){
                setMessage("Nama harus diisi");
            }
            if(fieldNIM.getText().equals("")){
                setMessage("NIM harus diisi");
            }
            if(tglLahir.getDate() == null){
                setMessage("Tanggal lahir harus diisi");
            }
            if(btnJK.getSelection()==null){
                setMessage("Jenis kelamin harus diisi");
            }
            if(fieldAlamat.getText().equals("")){
                setMessage("Alamat harus diisi");
            }
            if(cmbAgama.getSelectedIndex()==0){
                setMessage("Agama harus diisi");
            }
            else{
                try {

                    java.util.Date tanggal = tglLahir.getDate();
                    String tgl = new SimpleDateFormat("yyyy-MM-dd").format(tanggal);
                    String jk;
                    if(lakilaki.isSelected()){
                        jk = "Laki-laki";
                    }
                    else{
                        jk = "Perempuan";
                    }
                    String agama = cmbAgama.getSelectedItem().toString();
                    String prodi,angkatan;
                    prodi = fieldNIM.getText().charAt(0)+""+fieldNIM.getText().charAt(1)+""+fieldNIM.getText().charAt(2);
                    angkatan = "2"+fieldNIM.getText().charAt(5)+fieldNIM.getText().charAt(3)+""+fieldNIM.getText().charAt(4)+"";
                    if(prodi.equals("123")){
                        prodi = "Informatika";
                    }
                    else if(prodi.equals("124")){
                        prodi = "Sistem Informasi";
                    }
                    else{
                        prodi = "Unknown";
                    }

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahasiswa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

                    String query = " update mahasiswa set nim = ?, nama = ?, alamat = ?, jk = ?, tgl_lahir = ?, agama = ?, prodi = ?, angkatan = ? where id = ?";

                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString (1, fieldNIM.getText());
                    preparedStmt.setString (2, fieldNama.getText());
                    preparedStmt.setString (3, fieldAlamat.getText());
                    preparedStmt.setString (4, jk);
                    preparedStmt.setString (5, tgl);
                    preparedStmt.setString (6, agama);
                    preparedStmt.setString (7, prodi);
                    preparedStmt.setString (8, angkatan);
                    preparedStmt.setInt (9, id);

                    preparedStmt.execute();

                    con.close();

                    setMessage("Update Berhasil");

                }
                catch (Exception ex){
                    setMessage(ex.toString());
                }
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else if(e.getSource()==btnBack){
            dispose();
            Mahasiswa m = new Mahasiswa();
        }
    }
}

