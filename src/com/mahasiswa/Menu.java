package com.mahasiswa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private JLabel labelMenu;
    private JButton create;
    private JButton read;

    public Menu(){
        setTitle("Program Mahasiswa");
        labelMenu = new JLabel("Main Menu");
        labelMenu.setHorizontalAlignment(SwingConstants.CENTER);
        create = new JButton(" Tambah Data ");
        read = new JButton(" Manage Data ");
        setLayout(new GridLayout(3,1));
        add(labelMenu);
        add(create);
        add(read);
        pack();
        setResizable(false);
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        read.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        create.addActionListener(this);
        read.addActionListener(this);

        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==create){
            dispose();
            Form f = new Form();
            f.openForm();
        }
        else{
            dispose();
            Mahasiswa m = new Mahasiswa();
        }
    }
}
