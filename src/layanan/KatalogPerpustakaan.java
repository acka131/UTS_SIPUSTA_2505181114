package layanan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Koleksi;
import eksepsi.KoleksiSedangDipinjamException;
import eksepsi.KoleksiTidakLayakException;
import eksepsi.PustakaException;

public class KatalogPerpustakaan {
    private List<Koleksi> daftarKoleksi;

    public KatalogPerpustakaan() {
        this.daftarKoleksi = new ArrayList<>();
    }

    // Menambahkan koleksi ke dalam daftar
    public void tambahKoleksi(Koleksi koleksi) {
        daftarKoleksi.add(koleksi);
    }

    // Soal 3: Cetak status seluruh koleksi secara polimorfik tanpa `instanceof`
    public void cetakStatus() {
        System.out.println("=== DAFTAR KATALOG PERPUSTAKAAN ===");
        for (Koleksi k : daftarKoleksi) {
            String statusKelayakan = k.isLayakDipinjam() ? "[LAYAK DIPINJAM]" : "[TIDAK LAYAK]";
            String statusPinjam = k.isSedangDipinjam() ? "(Sedang Dipinjam)" : "(Tersedia)";
            
            // Pemanggilan deskripsi() secara polimorfik
            System.out.println(k.deskripsi() + " | " + statusKelayakan + " " + statusPinjam);
        }
    }

    // Soal 4: Method urut yang menerima Comparator dari luar
    public void urut(Comparator<Koleksi> comparator) {
        daftarKoleksi.sort(comparator);
    }

    // Soal 5: Method pinjam yang melempar eksepsi sesuai kriteria
    public void pinjamKoleksi(String kodeKoleksi) throws PustakaException {
        Koleksi target = null;
        
        // Cari koleksi berdasarkan kode
        for (Koleksi k : daftarKoleksi) {
            if (k.getKodeKoleksi().equalsIgnoreCase(kodeKoleksi)) {
                target = k;
                break;
            }
        }

        // Jika kode tidak ditemukan
        if (target == null) {
            throw new PustakaException("Gagal meminjam: Kode koleksi \"" + kodeKoleksi + "\" tidak ditemukan dalam sistem.");
        }

        // Cek 1: Apakah sedang dipinjam?
        if (target.isSedangDipinjam()) {
            throw new KoleksiSedangDipinjamException(target.getJudul());
        }

        // Cek 2: Apakah layak dipinjam?
        if (!target.isLayakDipinjam()) {
            throw new KoleksiTidakLayakException(target.getJudul(), "koleksi dalam status perbaikan atau edisi terbaru");
        }

        // Jika lolos semua validasi
        target.setSedangDipinjam(true);
        System.out.println("Berhasil meminjam koleksi: \"" + target.getJudul() + "\"");
    }

    // Getter untuk daftar koleksi (nanti berguna untuk ekspor dan pencarian)
    public List<Koleksi> getDaftarKoleksi() {
        return daftarKoleksi;
    }
}