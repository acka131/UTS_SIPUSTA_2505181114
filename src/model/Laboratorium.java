/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acka
 */
public class Laboratorium {
    private final String kodeLab;
    private final String nama;
    
    private final List<Alat> koleksiAlat = new ArrayList<>();
    
    public Laboratorium(String kodeLab, String nama) {
        this.kodeLab = kodeLab;
        this.nama = nama;
    }
    
    public void tambahAlat (Alat alat) {
        koleksiAlat.add(alat);
      
    }
    
    public int jumlahAlat(){
        return koleksiAlat.size();
    }
    
    public String getKodeLab() {
    return kodeLab;
    }
}
