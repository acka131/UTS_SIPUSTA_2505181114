import model.BukuCetak;
import model.Majalah;
import model.BerkasDigital;
import layanan.KatalogPerpustakaan;
import layanan.UrutLayakLaluJudul;
import layanan.EksporKatalog;
import eksepsi.PustakaException;

public class Main {
    public static void main(String[] args) {
        KatalogPerpustakaan katalog = new KatalogPerpustakaan();

        // 1. INPUT DATA UJI WAJIB (6 Koleksi)
        // BukuCetak
        BukuCetak bk1 = new BukuCetak("BK-001", "Java How to Program", 2022, 620, "A-12", false); // tidak diperbaiki
        BukuCetak bk2 = new BukuCetak("BK-002", "Basis Data Relasional", 2021, 410, "B-04", true);   // sedang diperbaiki

        // Majalah
        Majalah mj1 = new Majalah("MJ-001", "Info Teknologi Edisi 88", 2026, 88, "Agustus", "C-01", true);  // edisi terbaru
        Majalah mj2 = new Majalah("MJ-002", "Info Teknologi Edisi 87", 2026, 87, "Juli", "C-02", false);   // bukan terbaru

        // BerkasDigital
        BerkasDigital bd1 = new BerkasDigital("BD-001", "Panduan Pemrograman Java", 2024, 15.0, "unduh.polmed.ac.id/bd001");
        BerkasDigital bd2 = new BerkasDigital("BD-002", "Jurnal Rekayasa Perangkat", 2025, 8.0, "unduh.polmed.ac.id/bd002");

        // Tambahkan seluruh data uji ke katalog
        katalog.tambahKoleksi(bk1);
        katalog.tambahKoleksi(bk2);
        katalog.tambahKoleksi(mj1);
        katalog.tambahKoleksi(mj2);
        katalog.tambahKoleksi(bd1);
        katalog.tambahKoleksi(bd2);

        // 2. UJI SOAL 3: Cetak Status Awal (Polimorfik tanpa instanceof)
        System.out.println("=== 1. STATUS KATALOG AWAL ===");
        katalog.cetakStatus();
        System.out.println();

        // 3. UJI SOAL 4: Pengurutan (Comparator UrutLayakLaluJudul)
        System.out.println("=== 2. MENGURUTKAN (LAYAK DIPINJAM LEBIH DULU & JUDUL) ===");
        katalog.urut(new UrutLayakLaluJudul());
        katalog.cetakStatus();
        System.out.println();

        // 4. UJI SOAL 5: Pengujian Fitur Peminjaman & Eksepsi
        System.out.println("=== 3. Pengujian PEMINJAMAN & EKSEPSI ===");
        
        // Skenario A: Meminjam koleksi yang LAYAK (BK-001) -> Harus Berhasil
        try {
            katalog.pinjamKoleksi("BK-001");
        } catch (PustakaException e) {
            System.err.println(e.getMessage());
        }

        // Skenario B: Meminjam koleksi yang SEDANG DIPINJAM (BK-001 lagi) -> Throw KoleksiSedangDipinjamException
        try {
            katalog.pinjamKoleksi("BK-001");
        } catch (PustakaException e) {
            System.err.println(e.getMessage());
        }

        // Skenario C: Meminjam koleksi TIDAK LAYAK (BK-002 sedang diperbaiki) -> Throw KoleksiTidakLayakException
        try {
            katalog.pinjamKoleksi("BK-002");
        } catch (PustakaException e) {
            System.err.println(e.getMessage());
        }

        System.out.println();

        // 5. UJI SOAL 6: Ekspor Katalog ke Berkas Teks
        System.out.println("=== 4. EKSPOR KATALOG KE BERKAS TEKS ===");
        EksporKatalog.eksporKeFile(katalog.getDaftarKoleksi(), "katalog.txt");
    }
}