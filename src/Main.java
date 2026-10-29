import java.util.Scanner;
import java.time.LocalDate;
import model.*;
import layanan.InventarisLab;
import transaksi.*;
import koleksi.Keranjang;
import pembanding.UrutNama;
import pembanding.UrutTahun;
import layanan.LayananPeminjaman;
import eksepsi.SipalabException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import berkas.EksporLaporan;
import berkas.PenyimpanBerkas;
import basisdata.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        
        // 1. PENGUJIAN SQL INJECTION (Khusus MySQL)
        try {
            //Kode pemanggil (Main.java) dapat berjalan sama persis tanpa perubahan logika 
            //karena kita menerapkan pola Data Access Object (DAO) menggunakan antarmuka AlatDAO.
            //Lapisan bisnis/pemanggil hanya bergantung pada antarmuka (AlatDAO), bukan pada 
            //implementasi konkretnya (AlatDAOMySQL atau AlatDAOMemori). Hal ini menciptakan 
            //loose coupling (keterikatan yang longgar) sehingga mekanisme penyimpanan data 
            //dapat ditukar dengan sangat mudah tanpa merusak kode program yang lain.
            AlatDAOMySQL daoMySQL = new AlatDAOMySQL(); // Pakai AlatDAOMySQL langsung

            System.out.println("=== UJI PERCOBAAN SQL INJECTION ===");
            daoMySQL.cariNama("kabel' OR '1'='1");

        } catch (Exception e) {
            System.out.println("Error SQL Injection: " + e.getMessage());
        }

       // 2. PENGUJIAN TUGAS MANDIRI (Nomor 1 & 2 - DAO Memori)
        try {
            AlatDAO daoMemori = new AlatDAOMemori(); // Pakai interface AlatDAO dengan AlatDAOMemori

            System.out.println("\n=== PENGUJIAN TUGAS MANDIRI (DAO MEMORI) ===");
            System.out.println("Sumber terpilih  : " + daoMemori.namaSumber());
            System.out.println("Jumlah alat awal : " + daoMemori.semua().size());

            // Menggunakan konstruktor Laptop yang valid di project SIPALAB kamu (8 parameter)
            Laptop laptopBaru = new Laptop("LTP-999", "Laptop Memori Uji", 2024, 16, true, "SN-999", "Lab Memori", true);
            daoMemori.simpan(laptopBaru);
            System.out.println("Setelah simpan   : " + daoMemori.semua().size());

            // Membuat objek baru untuk menguji perbarui (tanpa setNama)
            Laptop laptopEdit = new Laptop("LTP-999", "Laptop Memori Uji (Diubah)", 2024, 16, true, "SN-999", "Lab Memori", true);
            daoMemori.perbarui(laptopEdit);
            
            Alat hasilCari = daoMemori.cariKode("LTP-999");
            System.out.println("Setelah perbarui : " + (hasilCari != null ? hasilCari.getNama() : "-"));

            daoMemori.hapus("LTP-999");
            System.out.println("Setelah hapus    : " + daoMemori.semua().size());

        } catch (Exception e) {
            System.out.println("Error Memori: " + e.getMessage());
        }
        
        // ========================================================
        // 0. UJI KONEKSI DATABASE (TARUH DI SINI, DI DALAM MAIN)
        // ========================================================
        if (KoneksiDB.getInstance().ujiKoneksi()) {
            System.out.println("=== Koneksi berhasil ===");
        } else {
            System.out.println("=== Koneksi gagal, periksa MySQL dan pustaka penghubung ===");
        }

        // ========================================================
        // KODE SIPALAB KAMU YANG LAMA
        // ========================================================
        try {
            tulisTanpaMenutup();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        try {
            tulisDenganAman();
            System.out.println("File uji_aman.txt berhasil ditulis!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("=== PENGUJIAN JOB SHEET 1 - 3 (INVENTARIS & POLIMORFISME) ===");
        
        // 1. Inisialisasi Layanan Inventaris
        InventarisLab inventaris = new InventarisLab();

        // 2. Pembuatan Objek Data
        Laptop lap = new Laptop("LP-002", "Lenovo ThinkPad", 2023, 16, true, "SN12345", "Lab A", true);
        Proyektor pro = new Proyektor("PJ-003", "Epson EB-X51", 2022, 3300, 1500, "SN98765", "Ruang Dosen");
        AlatUkur ukur = new AlatUkur("AU-004", "Multimeter Fluke", 2020, "volt", LocalDate.of(2027, 3, 10));

        Laptop lap1 = new Laptop("LP-001", "Lenovo ThinkPad", 2023, 16, true, "SN-LP-001", "Laboratorium Pemrograman", true);
        Laptop lap2 = new Laptop("LP-002", "Asus Vivobook", 2024, 8, true, "SN-LP-002", "Laboratorium Pemrograman", true);

        Proyektor pro1 = new Proyektor("PJ-001", "Epson EB-X51", 2022, 3300, 1200, "SN-PJ-001", "Ruang U303");
        Proyektor pro2 = new Proyektor("PJ-002", "BenQ", 2023, 3500, 800, "SN-PJ-002", "Ruang U302");

        AlatUkur ukur1 = new AlatUkur("AU-001", "Multimeter Fluke", 2020, "volt", LocalDate.of(2024, 3, 10));

        inventaris.tambah(lap1);
        inventaris.tambah(lap2);
        inventaris.tambah(pro1);
        inventaris.tambah(pro2);
        inventaris.tambah(ukur1);
        inventaris.tambah(lap);
        inventaris.tambah(pro);
        inventaris.tambah(ukur);

        inventaris.cetakStatus();

        System.out.println("\n=== PENGECEKAN KEMAMPUAN KHUSUS (INTERFACE) ===");
        System.out.println(lap.ringkasanLacak());
        System.out.println(pro.ringkasanLacak());
        inventaris.cetakJadwalKalibrasi();

        System.out.println("\n=== TUGAS MANDIRI JOB SHEET 3 ===");
        System.out.println("Total Alat Siap Dipinjam: " + inventaris.hitungSiapDipinjam());
        
        System.out.print("Daftar Alat Tidak Siap: ");
        for (Alat a : inventaris.daftarTidakSiap()) {
            System.out.print("[" + a.getNama() + "] ");
        }
        System.out.println();

        System.out.println("\n=======================================================");
        System.out.println("=== PENGUJIAN JOB SHEET 4 (RELASI CLASS DIAGRAM) ===");
        
        Mahasiswa mhs = new Mahasiswa("2205062001", "Rani Simatupang", "TRPL");
        Petugas ptg = new Petugas("198701012015041002", "Bapak Sutrisno");

        Laboratorium lab = new Laboratorium("LAB-RPL", "Laboratorium Rekayasa PL");
        lab.tambahAlat(lap);
        lab.tambahAlat(pro);
        lab.tambahAlat(ukur);
        System.out.println("Jumlah alat di " + lab.getKodeLab() + ": " + lab.jumlahAlat());

        Peminjaman pinjam = new Peminjaman("PJM-0001", mhs, ptg);
        pinjam.tambahDetail(lap, 1);
        pinjam.tambahDetail(pro, 2);
        
        System.out.println("\nTransaksi Pinjam (No: " + pinjam.getNomorPeminjaman() + ")");
        System.out.println("Peminjam: " + mhs.getNama());
        System.out.println("Total item dipinjam: " + pinjam.totalItem());
        
        for (DetailPeminjaman d : pinjam.getDetail()) {
            System.out.println(" - " + d.baris());
        }

        pinjam.kembalikan();
        System.out.println("\nStatus Pengembalian:");
        long terlambat = pinjam.hitungHariTerlambat();
        
        if (terlambat > 0) {
            System.out.println("Barang terlambat dikembalikan selama: " + terlambat + " hari.");
        } else {
            System.out.println("Barang dikembalikan tepat waktu.");
        }
        
        System.out.println("\n=== JOBSHEET 5: KERANJANG GENERIC ===");

        Keranjang<Laptop> keranjangLaptop = new Keranjang<>(2);
        System.out.println("Tambah laptop pertama: " + keranjangLaptop.tambah(lap1));
        System.out.println("Tambah laptop kedua: " + keranjangLaptop.tambah(lap2));
        System.out.println("Tambah laptop ketiga: " + keranjangLaptop.tambah(lap));

        System.out.println("Jumlah isi: " + keranjangLaptop.ukuran());
        System.out.println("Sisa ruang: " + keranjangLaptop.sisaRuang());
        System.out.println("Keranjang penuh: " + keranjangLaptop.penuh());

        System.out.println("Isi keranjang:");
        for (Laptop laptop : keranjangLaptop) {
            System.out.println(laptop.deskripsi());
        }
        
        System.out.println("\n=== URUT BERDASARKAN NAMA ===");
        for (Alat a : inventaris.urut(new UrutNama())) {
            System.out.println(a.laporanRingkas());
        }

        System.out.println("\n=== URUT STATUS LALU NAMA ===");
        for (Alat a : inventaris.urutBawaan()) {
            System.out.println(a.laporanRingkas());
        }

        System.out.println("\n=== URUTAN ASLI INVENTARIS ===");
        inventaris.cetakStatus();
        
        System.out.println("\n=== PENGUJIAN PENGHAPUSAN ===");
        InventarisLab ujiHapus = new InventarisLab();
        ujiHapus.tambah(lap);
        ujiHapus.tambah(lap1);
        ujiHapus.tambah(pro1);

        System.out.println("Jumlah sebelum dihapus: " + ujiHapus.semuaAlat().size());
        ujiHapus.hapusYangTidakSiap();
        System.out.println("Jumlah setelah dihapus: " + ujiHapus.semuaAlat().size());
        ujiHapus.cetakStatus();
        
        System.out.println("\n=== CARI BERDASARKAN NAMA: kabel ===");
        for (Alat a : inventaris.cari("kabel")) {
            System.out.println(a.laporanRingkas());
        }

        System.out.println("\n=== CARI BERDASARKAN KODE: lp-002 ===");
        for (Alat a : inventaris.cari("lp-002")) {
            System.out.println(a.laporanRingkas());
        }
        
        System.out.println("\n=== URUT TAHUN TERBARU LALU NAMA ===");
        for (Alat a : inventaris.urut(new UrutTahun())) {
            System.out.println(a.getTahunPerolehan() + " | " + a.laporanRingkas());
        }

        System.out.println("\n=== PENCARIAN LANGSUNG BERDASARKAN KODE ===");
        Alat hasilKode = inventaris.cariKode("LP-002");
        if (hasilKode != null) {
            System.out.println("Alat ditemukan:\n" + hasilKode.laporanRingkas());
        } else {
            System.out.println("Kode alat tidak ditemukan.");
        }

        System.out.println("\n=== PENGUJIAN KODE YANG TIDAK ADA ===");
        Alat hasilTidakAda = inventaris.cariKode("XX-999");
        if (hasilTidakAda == null) {
            System.out.println("Kode XX-999 tidak ditemukan.");
        }
        
        System.out.println("\n=== 1. UJI VALIDASI MASUKAN ===");
        ujiTangguh();

        System.out.println("\n=== 2. UJI ATURAN BISNIS PEMINJAMAN ===");
        LayananPeminjaman layanan = new LayananPeminjaman();

        Laptop laptopBelumSiap = new Laptop("LP-002", "Asus V", 2022, 16, false, "A779", "U301", false);
        Komponen kompBelumSiap = new Komponen("KM-002", "Adapter", 2021, 1, 0);
        Laptop laptopSiap = new Laptop("LP-001", "Lenovo", 2023, 8, true, "L876", "N230", true);

        cobaPinjam(layanan, mhs, ptg, laptopBelumSiap);
        cobaPinjam(layanan, mhs, ptg, kompBelumSiap);
        cobaPinjam(layanan, mhs, ptg, laptopSiap);
        cobaPinjam(layanan, mhs, ptg, laptopSiap);

        System.out.println("\n=== E.3: UJI SERIALISASI ===");
        PenyimpanBerkas penyimpanan = new PenyimpanBerkas("inventaris.dat");
        penyimpanan.simpan(inventaris.semuaAlat());
        System.out.println("Data berhasil disimpan ke inventaris.dat");
        System.out.println("Berkas inventaris.dat berhasil dibuat!");
        System.out.println("Berkas uji_aman.txt berhasil dibuat dengan aman!");

        System.out.println("\n--- PENGUJIAN TUGAS MANDIRI ---");
        try {
            List<Alat> alatMuat = penyimpanan.muat();
            System.out.println("Berhasil memuat " + alatMuat.size() + " alat dari inventaris.dat:");
            for (Alat a : alatMuat) {
                System.out.println(" - " + a.deskripsi());
            }

            EksporLaporan ekspor = new EksporLaporan();
            ekspor.tulisCSV("laporan_inventaris.csv", alatMuat);
            System.out.println("Laporan CSV berhasil diekspor ke laporan_inventaris.csv");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Terjadi kesalahan saat memproses berkas: " + e.getMessage());
        }
    } // <--- Kurung kurawal tutup method main

    // ==========================================
    // METODE-METODE PENDUKUNG DI LUAR MAIN
    // ==========================================

    public static void ujiTangguh() {
        Scanner masukan = new Scanner(System.in);
        System.out.print("Masukkan tahun perolehan: ");
        try {
            int tahun = Integer.parseInt(masukan.nextLine());
            System.out.println("Tahun tercatat: " + tahun);
        } catch (NumberFormatException e) {
            System.out.println("Tahun harus berupa angka, contoh 2024.");
        }
    }

    public static void tulisTanpaMenutup() throws IOException {
        FileWriter tulis = new FileWriter("uji_lupa_tutup.txt");
        tulis.write("Baris ini seharusnya tersimpan.");
    }

    private static void cobaPinjam(LayananPeminjaman layanan, Mahasiswa mhs, Petugas ptg, Alat alat) {
        try {
            Peminjaman p = layanan.pinjam(mhs, ptg, alat, 1);
            System.out.println("BERHASIL " + p.getNomorPeminjaman());
        } catch (SipalabException e) {
            System.out.println("DITOLAK " + e.getMessage());
        }
    }

    public static void tulisDenganAman() throws IOException {
        try (FileWriter tulis = new FileWriter("uji_aman.txt")) {
            tulis.write("Baris ini pasti tersimpan.");
        }
    }
} 