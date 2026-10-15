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
import berkas.EksporLaporan;


public class Main {
    public static void main(String[] args) {
        
         try {
    tulisTanpaMenutup();
        } catch (IOException e) {
    e.printStackTrace();
    }
         
         try {
    tulisDenganAman();
        System.out.println("File uji_aman.txt berhasil ditulis!");
    }   catch (IOException e) {
    
        
        e.printStackTrace();
    }
        
        System.out.println("=== PENGUJIAN JOB SHEET 1 - 3 (INVENTARIS & POLIMORFISME) ===");
        
        // 1. Inisialisasi Layanan Inventaris
        InventarisLab inventaris = new InventarisLab();

        // 2. Pembuatan Objek Data (Termasuk Kelas Komponen dari Tugas Mandiri JS3)
        Laptop lap = new Laptop("LP-002", "Lenovo ThinkPad", 2023, 16, true, "SN12345", "Lab A", true);
        Proyektor pro = new Proyektor("PJ-003", "Epson EB-X51", 2022, 3300, 1500, "SN98765", "Ruang Dosen");
        AlatUkur ukur = new AlatUkur("AU-004", "Multimeter Fluke", 2020, "volt", LocalDate.of(2027, 3, 10)); // Kalibrasi valid
        // 2. Membuat objek Laptop
        Laptop lap1 = new Laptop(
                "LP-001",
                "Lenovo ThinkPad",
                2023,
                16,
                true,
                "SN-LP-001",
                "Laboratorium Pemrograman",
                true
        );

        Laptop lap2 = new Laptop(
                "LP-002",
                "Asus Vivobook",
                2024,
                8,
                true,
                "SN-LP-002",
                "Laboratorium Pemrograman",
                true
        );

        // 3. Membuat objek Proyektor
        Proyektor pro1 = new Proyektor(
                "PJ-001",
                "Epson EB-X51",
                2022,
                3300,
                1200,
                "SN-PJ-001",
                "Ruang U303"
                
        );

        Proyektor pro2 = new Proyektor(
                "PJ-002",
                "BenQ",
                2023,
                3500,
                800,
                "SN-PJ-002",
                "Ruang U302"
        );

        // 4. Membuat objek AlatUkur
        AlatUkur ukur1 = new AlatUkur(
                "AU-001",
                "Multimeter Fluke",
                2020,
                "volt",
                LocalDate.of(2024, 3, 10)
        );

        // 3. Memasukkan objek ke Koleksi Heterogen (Upcast otomatis)
        inventaris.tambah(lap1);
        inventaris.tambah(lap2);
        inventaris.tambah(pro1);
        inventaris.tambah(pro2);
        inventaris.tambah(ukur1);
        // Objek pengujian sebelumnya juga menjadi bagian inventaris.
        inventaris.tambah(lap);
        inventaris.tambah(pro);
        inventaris.tambah(ukur);

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
        
        // Dibantu ChatGPT untuk menguji tipe isi, kapasitas,
        // dan perulangan pada Keranjang.
        System.out.println("\n=== JOBSHEET 5: KERANJANG GENERIC ===");

        Keranjang<Laptop> keranjangLaptop = new Keranjang<>(2);

        System.out.println("Tambah laptop pertama: "
                + keranjangLaptop.tambah(lap1));

        System.out.println("Tambah laptop kedua: "
                + keranjangLaptop.tambah(lap2));

        System.out.println("Tambah laptop ketiga: "
                + keranjangLaptop.tambah(lap));

        System.out.println("Jumlah isi: " + keranjangLaptop.ukuran());
        System.out.println("Sisa ruang: " + keranjangLaptop.sisaRuang());
        System.out.println("Keranjang penuh: " + keranjangLaptop.penuh());

        System.out.println("Isi keranjang:");

        for (Laptop laptop : keranjangLaptop) {
            System.out.println(laptop.deskripsi());
        }
        
        // Dibantu ChatGPT untuk membandingkan hasil pengurutan
        // dan memeriksa bahwa urutan asli inventaris tetap terjaga.
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
        
        // Dibantu ChatGPT untuk menyiapkan data uji penghapusan.
        System.out.println("\n=== PENGUJIAN PENGHAPUSAN ===");

        InventarisLab ujiHapus = new InventarisLab();

        ujiHapus.tambah(lap);   // Tidak siap: charger tidak lengkap.
        ujiHapus.tambah(lap1);  // Siap.
        ujiHapus.tambah(pro1);  // Siap.

        System.out.println("Jumlah sebelum dihapus: "
                + ujiHapus.semuaAlat().size());

        ujiHapus.hapusYangTidakSiap();

        System.out.println("Jumlah setelah dihapus: "
                + ujiHapus.semuaAlat().size());

        ujiHapus.cetakStatus();
        
        // Dibantu ChatGPT untuk menguji pencarian nama dan kode alat.
        System.out.println("\n=== CARI BERDASARKAN NAMA: kabel ===");

        for (Alat a : inventaris.cari("kabel")) {
            System.out.println(a.laporanRingkas());
        }

        System.out.println("\n=== CARI BERDASARKAN KODE: lp-002 ===");

        for (Alat a : inventaris.cari("lp-002")) {
            System.out.println(a.laporanRingkas());
        }
        
        // Dibantu ChatGPT untuk menguji urutan tahun dan pencarian
// langsung berdasarkan kunci Map.
        System.out.println("\n=== URUT TAHUN TERBARU LALU NAMA ===");

        for (Alat a : inventaris.urut(new UrutTahun())) {
            System.out.println(
                    a.getTahunPerolehan()
                    + " | "
                    + a.laporanRingkas()
            );
        }

        System.out.println("\n=== PENCARIAN LANGSUNG BERDASARKAN KODE ===");

        Alat hasilKode = inventaris.cariKode("LP-002");

        if (hasilKode != null) {
            System.out.println("Alat ditemukan:");
            System.out.println(hasilKode.laporanRingkas());
        } else {
            System.out.println("Kode alat tidak ditemukan.");
        }

        
        System.out.println("\n=== PENGUJIAN KODE YANG TIDAK ADA ===");

        Alat hasilTidakAda = inventaris.cariKode("XX-999");

        if (hasilTidakAda == null) {
            System.out.println("Kode XX-999 tidak ditemukan.");
        }
        
        System.out.println("=== 1. UJI VALIDASI MASUKAN ===");
        ujiTangguh();

        System.out.println("\n=== 2. UJI ATURAN BISNIS PEMINJAMAN ===");
        LayananPeminjaman layanan = new LayananPeminjaman();

        Laptop laptopBelumSiap = new Laptop("LP-002", "Asus V", 2022, 16, false, "A779", "U301", false);
        Komponen kompBelumSiap = new Komponen("KM-002", "Adapter", 2021, 1, 0);
        Laptop laptopSiap = new Laptop("LP-001", "Lenovo", 2023, 8, true, "L876", "N230", true);

        // Eksekusi 4 kasus uji
        cobaPinjam(layanan, mhs, ptg, laptopBelumSiap); // Kasus 1: DITOLAK
        cobaPinjam(layanan, mhs, ptg, kompBelumSiap);   // Kasus 2: DITOLAK
        cobaPinjam(layanan, mhs, ptg, laptopSiap);      // Kasus 3: BERHASIL
        cobaPinjam(layanan, mhs, ptg, laptopSiap);      // Kasus 4: DITOLAK (Sedang dipinjam)
    }
    
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
    // Sengaja tidak memanggil tulis.close()
    }   
    
    
    private static void cobaPinjam(LayananPeminjaman layanan, Mahasiswa mhs, Petugas ptg, Alat alat) {
        try {
            Peminjaman p = layanan.pinjam(mhs, ptg, alat, 1);
            System.out.println(" BERHASIL " + p.getNomorPeminjaman());
        } catch (SipalabException e) {
            System.out.println(" DITOLAK " + e.getMessage());
        }
    }

    public static void tulisDenganAman() throws IOException {
        try (FileWriter tulis = new FileWriter("uji_aman.txt")) {
        tulis.write("Baris ini pasti tersimpan.");
        }
    }
}
