package layanan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Alat;
import model.Kalibrasiable;
import pembanding.UrutStatusLaluNama;

// Dibantu ChatGPT untuk menyesuaikan seluruh method setelah perubahan ke Map.
// Kode alat menjadi kunci sehingga pencarian langsung rata-rata O(1).
// LinkedHashMap juga mempertahankan urutan masuk alat.
public class InventarisLab {

    private final Map<String, Alat> daftarAlat = new LinkedHashMap<>();

    public void tambah(Alat alat) {
        daftarAlat.put(alat.getKodeAlat(), alat);
    }

    public List<Alat> semuaAlat() {
        return new ArrayList<>(daftarAlat.values());
    }

    public Alat cariKode(String kode) {
        return daftarAlat.get(kode);
    }

    public void cetakStatus() {
        for (Alat a : daftarAlat.values()) {
            System.out.println(a.laporanRingkas());
        }
    }

    public void cetakJadwalKalibrasi() {
        for (Alat a : daftarAlat.values()) {
            if (a instanceof Kalibrasiable k) {
                System.out.println(a.getNama() + " -> " + k.statusKalibrasi());
            }
        }
    }

    // Kedua method berikut memakai siapDipinjam() secara polimorfik,
    // sehingga tidak perlu memeriksa jenis alat dengan instanceof.
    public int hitungSiapDipinjam() {
        int jumlah = 0;

        for (Alat a : daftarAlat.values()) {
            if (a.siapDipinjam()) {
                jumlah++;
            }
        }

        return jumlah;
    }

    public List<Alat> daftarTidakSiap() {
        List<Alat> tidakSiap = new ArrayList<>();

        for (Alat a : daftarAlat.values()) {
            if (!a.siapDipinjam()) {
                tidakSiap.add(a);
            }
        }

        return tidakSiap;
    }

    public List<Alat> urut(Comparator<Alat> pembanding) {
        List<Alat> salinan = new ArrayList<>(daftarAlat.values());

        salinan.sort(pembanding);

        return salinan;
    }

    public List<Alat> urutBawaan() {
        return urut(new UrutStatusLaluNama());
    }

    public void hapusYangTidakSiap() {
        daftarAlat.values().removeIf(a -> !a.siapDipinjam());
    }

    public List<Alat> cari(String kata) {
        String k = kata.toLowerCase();
        List<Alat> hasil = new ArrayList<>();

        for (Alat a : daftarAlat.values()) {
            if (a.getNama().toLowerCase().contains(k)
                    || a.getKodeAlat().toLowerCase().contains(k)) {

                hasil.add(a);
            }
        }

        return hasil;
    }
}