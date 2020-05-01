package com.mahasiswa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class Delete extends Form{
    private Connection con;
    public Delete(String nim){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahasiswa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

            String query = " delete from mahasiswa where nim = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, nim);

            preparedStmt.execute();

            con.close();

            setMessage("Delete Berhasil");

        }
        catch (Exception ex){
            setMessage(ex.toString());
        }

    }

    public void reset(){
        setMessage("Tidak ada yang perlu direset");
    }

}
