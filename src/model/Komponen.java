///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
// */
//package model;
//
///**
// *
// * @author acka
// */
//public interface Komponen {
//    
//}

package model;

public class Komponen extends Alat {
    private int jumlahStok;
    private int jumlahMinimum;

    public Komponen(String kodeAlat, String nama, int tahunPerolehan, int jumlahStok, int jumlahMinimum) {
        super(kodeAlat, nama, tahunPerolehan);
        this.jumlahStok = jumlahStok;
        this.jumlahMinimum = jumlahMinimum;
    }

    @Override
    public boolean siapDipinjam() {
        return jumlahStok > jumlahMinimum;
    }
}