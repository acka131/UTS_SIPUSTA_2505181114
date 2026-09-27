/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acka
 */
public class Petugas {
    private String nip;
    private String nama;
    
    public Petugas(String nip, String nama){
        this.nip = nip;
        this.nama = nama;
    }
    public String getNama(){
        return nama;
    }
}
