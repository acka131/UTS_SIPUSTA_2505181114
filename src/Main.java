///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//import model.Alat;
//import model.Laptop;
//import model.Proyektor;
//import model.AlatUkur;
//import java.time.LocalDate;
//
//
//
///**
// *
// * @author acka
// */
//public class Main {
//    
//    public static void main(String[] args) {
//        Alat umum = new Alat("AL-001", "Kabel HDMI", 2021) {};
//        Laptop lap = new Laptop("LP-002", "Lenovo ThinkPad L14 G1", 2021, 8, true, "SN-12345", "Lab Komputer 1");
//        Proyektor pro = new Proyektor("PJ-003", "Epson EB-X51", 2022, 3300, true);
//        AlatUkur alatukur = new AlatUkur("AU-004", "Multimeter", 2020, "Volt", LocalDate.of(2025, 6, 1));
//
//        System.out.println(umum.deskripsi() + " | siap: " + umum.siapDipinjam());
//        System.out.println(lap.deskripsi() + " | siap: " + lap.siapDipinjam());
//        System.out.println(pro.deskripsi() + " | siap: " + pro.siapDipinjam());
//        System.out.println(alatukur.deskripsi() + " | siap: " + alatukur.siapDipinjam());
//        
//        // Memanggil fungsi reservasi
//        lap.reservasi("2505181114", "2026-09-20");
//        System.out.println("Status reservasi: " + lap.isDireservasi());
//    }
//}
//    
//

import java.time.LocalDate;
import model.*;
import layanan.InventarisLab;

public class Main {
    public static void main(String[] args) {
        InventarisLab inventaris = new InventarisLab();

        // 1. Pembuatan Objek
        Laptop lap = new Laptop("LP-002", "Lenovo ThinkPad", 2023, 16, true, "SN12345", "Lab A");
        Proyektor pro = new Proyektor("PJ-003", "Epson EB-X51", 2022, 3300, 2500, "SN98765", "Ruang Dosen");
        AlatUkur ukur = new AlatUkur("AU-004", "Multimeter Fluke", 2020, "volt", LocalDate.of(2026, 3, 10));
        Komponen kom1 = new Komponen("KM-001", "Kabel HDMI 2 meter", 2021, 10, 2); // Siap
        Komponen kom2 = new Komponen("KM-002", "Adaptor USB-C", 2022, 1, 3); // Tidak siap

        // 2. Memasukkan objek ke Koleksi Polimorfik
        inventaris.tambah(lap);
        inventaris.tambah(pro);
        inventaris.tambah(ukur);
        inventaris.tambah(kom1);
        inventaris.tambah(kom2);

        // 3. Menguji Keseluruhan Sistem 
        System.out.println("=== STATUS SELURUH INVENTARIS ===");
        inventaris.cetakStatus(); // Tanpa menggunakan percabangan tipe (instanceof)
        
        System.out.println("\n=== PENGECEKAN KEMAMPUAN PELACAKAN (INTERFACE) ===");
        System.out.println(lap.ringkasanLacak());
        System.out.println(pro.ringkasanLacak());

        System.out.println("\n=== PENGECEKAN STATUS KALIBRASI ===");
        inventaris.cetakJadwalKalibrasi();

        System.out.println("\n=== TUGAS MANDIRI JOB SHEET 3 ===");
        System.out.println("Total Alat Siap Dipinjam: " + inventaris.hitungSiapDipinjam());
        
        System.out.println("Daftar Alat Tidak Siap:");
        for (Alat a : inventaris.daftarTidakSiap()) {
            System.out.println("- " + a.getNama());
        }
    }
}