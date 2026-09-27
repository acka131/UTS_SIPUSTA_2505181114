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
//
//    public class Proyektor extends Alat {
//    
//    
//    private int resolusi; // resolusi proyektor dalam piksel
//    private boolean konektivitasLengkap; // memastikan kabel proyektor lengkap
//
//    public Proyektor(String kodeAlat, String nama, int tahunPerolehan, int resolusi, boolean konektivitasLengkap) {
//        super(kodeAlat, nama, tahunPerolehan);
//        this.resolusi = resolusi;
//        this.konektivitasLengkap = konektivitasLengkap;
//    }
//
//    @Override // menyesuaikan prilaku metode dari kelas induk
//    public String deskripsi() {
//        return super.deskripsi() + " - Resolusi: " + resolusi + " px, Konektivitas Lengkap: " + (konektivitasLengkap ? "Ya" : "Tidak");
//    }
//
//    @Override
//    public boolean siapDipinjam() {
//        return konektivitasLengkap;
//    }
//} //dibantu gemini untuk menyusun sudah benar belum strukturnya
//

package model;

public class Proyektor extends Alat implements Terlacak {
    private int lumen;
    private int jamPakaiLampu;
    private String noSeri;
    private String lokasi;

    public Proyektor(String kodeAlat, String nama, int tahunPerolehan, int lumen, int jamPakaiLampu, String noSeri, String lokasi) {
        super(kodeAlat, nama, tahunPerolehan);
        this.lumen = lumen;
        this.jamPakaiLampu = jamPakaiLampu;
        this.noSeri = noSeri;
        this.lokasi = lokasi;
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " " + lumen + " lumen";
    }

    @Override
    public boolean siapDipinjam() {
        return jamPakaiLampu <= 2000;
    }

    @Override
    public String nomorSeri() {
        return noSeri;
    }

    @Override
    public String lokasiTerakhir() {
        return lokasi;
    }
}