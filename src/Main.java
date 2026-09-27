import java.time.LocalDate;
import model.*;
import layanan.InventarisLab;
import transaksi.*;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("=== PENGUJIAN JOB SHEET 1 - 3 (INVENTARIS & POLIMORFISME) ===");
        
        // 1. Inisialisasi Layanan Inventaris
        InventarisLab inventaris = new InventarisLab();

        // 2. Pembuatan Objek Data (Termasuk Kelas Komponen dari Tugas Mandiri JS3)
        Laptop lap = new Laptop("LP-002", "Lenovo ThinkPad", 2023, 16, true, "SN12345", "Lab A");
        Proyektor pro = new Proyektor("PJ-003", "Epson EB-X51", 2022, 3300, 1500, "SN98765", "Ruang Dosen");
        AlatUkur ukur = new AlatUkur("AU-004", "Multimeter Fluke", 2020, "volt", LocalDate.of(2027, 3, 10)); // Kalibrasi valid
        Komponen kom1 = new Komponen("KM-001", "Kabel HDMI 2 meter", 2021, 10, 2); // Siap dipinjam
        Komponen kom2 = new Komponen("KM-002", "Adaptor USB-C", 2022, 1, 3); // Tidak siap dipinjam

        // 3. Memasukkan objek ke Koleksi Heterogen (Upcast otomatis)
        inventaris.tambah(lap);
        inventaris.tambah(pro);
        inventaris.tambah(ukur);
        inventaris.tambah(kom1);
        inventaris.tambah(kom2);

        // 4. Mencetak Status Inventaris (Pengujian Polimorfisme)
        inventaris.cetakStatus();

        System.out.println("\n=== PENGECEKAN KEMAMPUAN KHUSUS (INTERFACE) ===");
        // Terlacak (Laptop dan Proyektor saja)
        System.out.println(lap.ringkasanLacak());
        System.out.println(pro.ringkasanLacak());
        
        // Kalibrasiable (Menggunakan instanceof Pattern Matching)
        inventaris.cetakJadwalKalibrasi();

        System.out.println("\n=== TUGAS MANDIRI JOB SHEET 3 ===");
        System.out.println("Total Alat Siap Dipinjam: " + inventaris.hitungSiapDipinjam());
        
        System.out.print("Daftar Alat Tidak Siap: ");
        for (Alat a : inventaris.daftarTidakSiap()) {
            System.out.print("[" + a.getNama() + "] ");
        }
        System.out.println(); // Pindah baris

        System.out.println("\n=======================================================");
        System.out.println("=== PENGUJIAN JOB SHEET 4 (RELASI CLASS DIAGRAM) ===");
        
        // 1. Asosiasi Sederhana
        Mahasiswa mhs = new Mahasiswa("2205062001", "Rani Simatupang", "TRPL");
        Petugas ptg = new Petugas("198701012015041002", "Bapak Sutrisno");

        // 2. Agregasi (Laboratorium menampung Alat yang sudah dibuat di luar)
        Laboratorium lab = new Laboratorium("LAB-RPL", "Laboratorium Rekayasa PL");
        lab.tambahAlat(lap);
        lab.tambahAlat(pro);
        lab.tambahAlat(ukur);
        System.out.println("Jumlah alat di " + lab.getKodeLab() + ": " + lab.jumlahAlat());

        // 3. Komposisi & Asosiasi Transaksi
        Peminjaman pinjam = new Peminjaman("PJM-0001", mhs, ptg);
        
        // Menambahkan Detail Peminjaman (Komposisi terjadi di dalam Peminjaman)
        pinjam.tambahDetail(lap, 1);
        pinjam.tambahDetail(pro, 2);
        
        System.out.println("\nTransaksi Pinjam (No: " + pinjam.getNomorPeminjaman() + ")");
        System.out.println("Peminjam: " + mhs.getNama());
        System.out.println("Total item dipinjam: " + pinjam.totalItem());
        
        // Mencetak baris detail
        for (DetailPeminjaman d : pinjam.getDetail()) {
            System.out.println(" - " + d.baris());
        }

        // 4. Menguji Tugas Mandiri Job Sheet 4
        pinjam.kembalikan(); // Mengeset tanggal kembali ke hari ini
        System.out.println("\nStatus Pengembalian:");
        long terlambat = pinjam.hitungHariTerlambat();
        
        if (terlambat > 0) {
            System.out.println("Barang terlambat dikembalikan selama: " + terlambat + " hari.");
        } else {
            System.out.println("Barang dikembalikan tepat waktu.");
        }
    }
}