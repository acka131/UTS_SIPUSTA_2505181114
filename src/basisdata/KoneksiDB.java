/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basisdata;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author acka
 */
public class KoneksiDB {
    private static KoneksiDB instance;
    
    private String url = "jdbc:mysql://localhost:3306/sipalab" 
                            + "?serverTimezone=Asia/Jakarta";
    private String pengguna = "root"; 
    private String sandi = ""; 
    private Connection koneksi;
    
    private KoneksiDB() { }
    
    public static synchronized KoneksiDB getInstance() { 
        if (instance == null) {
            instance = new KoneksiDB();
        }
        return instance; 
    }
    
    public void atur(String url, String pengguna, String sandi) { 
        this.url = url;
        this.pengguna = pengguna;
        this.sandi = sandi;
         this.koneksi = null;
    }
    
    public Connection getKoneksi() throws SQLException {
           if (koneksi == null || koneksi.isClosed()) {
               koneksi = DriverManager.getConnection(url, pengguna, sandi); 
           }
            return koneksi;
    }
    
    public boolean ujiKoneksi() { 
        try {
            return getKoneksi() != null && !getKoneksi().isClosed(); 
        } catch (SQLException e) {
    return false;
        }
        
    }
}
