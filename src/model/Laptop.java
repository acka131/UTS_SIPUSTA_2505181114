/////*
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//// */
////package model;
////
/////**
//// *
//// * @author acka
//// */
////
////
////    
/////**
//// *
//// * @author acka
//// */
//////public class Laptop extends Alat implements Terlacak {
////    
//////    private int ramGB;
//////    private boolean chargerLengkap;
//////    
////    
//////    public Laptop(String kodeAlat, String nama, int tahunPerolehan,
//////       int ramGB, boolean chargerLengkap) {
//////        super(kodeAlat, nama, tahunPerolehan);
//////        this.ramGB = ramGB;
//////        this.chargerLengkap = chargerLengkap;
//////}
//////    
//////@Override
//////public String deskripsi() {
//////return super.deskripsi() + " RAM " + ramGB + " GB";
//////}
//////
//////@Override
//////public boolean siapDipinjam() {
//////return chargerLengkap;
//////}
//////  }
////   
//////    private static final long serialVersionUID = 1L;
//////    
//////    private int ramGB;
//////    private boolean chargerLengkap;
//////    private String nomorSeri;
//////    private String lokasiTerakhir;
//////    
//////    public Laptop (String kodeAlat, String nama, int tahun, int ramGB, boolean charger,
//////            String nomorSeri, String lokasiTerakhir) {
//////        super(kodeAlat, nama, tahun);
//////        this.ramGB = ramGB;
//////        this.chargerLengkap = charger;
//////        this.nomorSeri = nomorSeri;
//////        this.lokasiTerakhir = lokasiTerakhir;
//////      
//////    }
//////    @Override public String deskripsi () {
//////        return super.deskripsi() + "RAM" + ramGB + "GB"; 
//////    }
//////   
//////    @Override public String nomorSeri() {
//////        return nomorSeri;
//////    }
//////    @Override public String lokasiTerakhir() {
//////        return lokasiTerakhir;
//////    }
//////    
//////    public int getRamGB() {
//////        return ramGB;
//////    }
//////    public boolean isChargerLengkap() {
//////        return chargerLengkap;
//////    }
//////    public void setChargerLengkap(boolean v){
//////        this.chargerLengkap = v;
//////    }
//////    public void setlokasiTerakhir(String l) {
//////        this.lokasiTerakhir = l;
//////    }
//////    
//////}
////
////public class Laptopp extends Alat implements reservable {
////
////    private int ramGB;
////    private boolean charger;
////    private String nomorSeri;
////    private String lokasiTerakhir;
////
////    private boolean direservasi = false;
////    private String pemesanNIM;
////
////    public Laptopp(String kodeAlat, String nama, int tahun, int ramGB, boolean charger, String nomorSeri, String lokasiTerakhir) {
////        super(kodeAlat, nama, tahun);
////        this.ramGB = ramGB;
////        this.charger = charger;
////        this.nomorSeri = nomorSeri;
////        this.lokasiTerakhir = lokasiTerakhir;
////    }
////
////    // Override dari kelas Alat (sesuaikan jika di kelas Alat sudah ada)
////    @Override
////    public String deskripsi() {
////        return super.deskripsi() + " | RAM: " + ramGB + "GB";
////    }
////
////    @Override
////    public boolean siapDipinjam() {
////        return charger && !direservasi;
////    }
////
////    // Implementasi interface Reservable
////    @Override
////    public boolean reservasi(String nim, String tanggalPinjam) {
////        if (!direservasi && siapDipinjam()) {
////            this.direservasi = true;
////            this.pemesanNIM = nim;
////            System.out.println("Laptop " + getNama() + " berhasil direservasi oleh NIM: " + nim + " untuk tanggal " + tanggalPinjam);
////            return true;
////        }
////        System.out.println("Laptop " + getNama() + " tidak dapat direservasi.");
////        return false;
////    }
////
////    @Override
////    public boolean batalkanReservasi(String nim) {
////        if (direservasi && pemesanNIM != null && pemesanNIM.equals(nim)) {
////            this.direservasi = false;
////            this.pemesanNIM = null;
////            System.out.println("Reservasi laptop " + getNama() + " berhasil dibatalkan.");
////            return true;
////        }
////        return false;
////    }
////
////    @Override
////    public boolean isDireservasi() {
////        return this.direservasi;
////    }
////
////    // Getter dan Setter tambahan
////    public int getRamGB() {
////        return ramGB;
////    }
////
////    public boolean isCharger() {
////        return charger;
////    }
////
////    public String getNomorSeri() {
////        return nomorSeri;
////    }
////
////    public String getLokasiTerakhir() {
////        return lokasiTerakhir;
////    }
////    
////}
////
//
//package model;
//
//public class Laptop extends Alat implements reservable {
//
//    private int ramGB;
//    private boolean charger;
//    private String nomorSeri;
//    private String lokasiTerakhir;
//
//    private boolean direservasi = false;
//    private String pemesanNIM;
//
//    public Laptop(String kodeAlat, String nama, int tahun, int ramGB,
//                   boolean charger, String nomorSeri, String lokasiTerakhir) {
//        super(kodeAlat, nama, tahun);
//        this.ramGB = ramGB;
//        this.charger = charger;
//        this.nomorSeri = nomorSeri;
//        this.lokasiTerakhir = lokasiTerakhir;
//    }
//
//    @Override
//    public String deskripsi() {
//        return super.deskripsi() + " | RAM: " + ramGB + "GB";
//    }
//
//    @Override
//    public boolean siapDipinjam() {
//        return charger && !direservasi;
//    }
//
//    // ===== Implementasi interface reservable =====
//    @Override
//    public boolean reservasi(String nim, String tanggalPinjam) {
//        if (!direservasi && siapDipinjam()) {
//            this.direservasi = true;
//            this.pemesanNIM = nim;
//            System.out.println("Laptop " + getNama() + " berhasil direservasi oleh NIM: "
//                    + nim + " untuk tanggal " + tanggalPinjam);
//            return true;
//        }
//        System.out.println("Laptop " + getNama() + " tidak dapat direservasi.");
//        return false;
//    }
//
//    @Override
//    public boolean batalkanReservasi(String nim) {
//        if (direservasi && pemesanNIM != null && pemesanNIM.equals(nim)) {
//            this.direservasi = false;
//            this.pemesanNIM = null;
//            System.out.println("Reservasi laptop " + getNama() + " berhasil dibatalkan.");
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isDireservasi() {   // ← sesuaikan dengan interface
//        return this.direservasi;
//    }
//
//    // ===== Getter & Setter =====
//    public int getRamGB() { return ramGB; }
//    public boolean isCharger() { return charger; }
//    public String getNomorSeri() { return nomorSeri; }
//    public String getLokasiTerakhir() { return lokasiTerakhir; }
//    public String getPemesanNIM() { return pemesanNIM; }
//}

package model;

public class Laptop extends Alat implements Terlacak {
    private int ramGB;
    private boolean chargerLengkap;
    private String noSeri;
    private String lokasi;

    public Laptop(String kodeAlat, String nama, int tahunPerolehan, int ramGB, boolean chargerLengkap, String noSeri, String lokasi,
                    boolean izinKeluarKampus) {
        super(kodeAlat, nama, tahunPerolehan);
        this.ramGB = ramGB;
        this.chargerLengkap = chargerLengkap;
        this.noSeri = noSeri;
        this.lokasi = lokasi;
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " RAM " + ramGB + " GB";
    }

    @Override
    public boolean siapDipinjam() {
        return chargerLengkap;
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