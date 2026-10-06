///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package model; 
//
///**
// *
// * @author acka
// */
//public abstract class Alat {
//    protected String kodeAlat;
//protected String nama;
//protected int tahunPerolehan;
//
//public Alat(String kodeAlat, String nama, int tahunPerolehan) {
//      this.kodeAlat = kodeAlat;
//      this.nama = nama;
//      this.tahunPerolehan = tahunPerolehan;
//}
//public String deskripsi() {
//    return kodeAlat + " - " + nama + " (" + tahunPerolehan + ")";
//}
//
//public boolean siapDipinjam() {
//    return true;
//}
//
////public abstract boolean siapDipinjam();
//
//public String getNama() {
//    return nama;
//}
//  }
//
//public String laporanRingkasan() {
//    String status = siapDipinjam() ? "SIAP" : "TIDAK SIAP";
//    return String.format("%-8s %-22 %s", kodeAlat, nama, status);
//}

package model;

public abstract class Alat {
    protected String kodeAlat;
    protected String nama;
    protected int tahunPerolehan;

    public Alat(String kodeAlat, String nama, int tahunPerolehan) {
        this.kodeAlat = kodeAlat;
        this.nama = nama;
        this.tahunPerolehan = tahunPerolehan;
    }

    public String deskripsi() {
        return kodeAlat + " - " + nama + " (" + tahunPerolehan + ")";
    }

    public abstract boolean siapDipinjam();

    
        public String laporanRingkas() {
        String status = siapDipinjam() ? "SIAP" : "TIDAK SIAP";
        return String.format("%-8s%-22s%s", kodeAlat, nama, status);
    }
        
    public String getNama() {
        return nama;
    }
    
    }