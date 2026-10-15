/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transaksi;
import eksepsi.PeminjamanSudahDitutupException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Alat;
import model.Mahasiswa;
import model.Petugas;

/**
 *
 * @author acka
 */
public class Peminjaman {
    
    private final String nomorPeminjaman;
    private final LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;

    // ASOSIASI: mahasiswa dan petugas hidup mandiri, hanya dirujuk melalui konstruktor
    private final Mahasiswa peminjam;
    private final Petugas penyetuju;

    // KOMPOSISI: detail dibuat menggunakan "new" di dalam kelas ini dan ikut lenyap bersamanya
    private final List<DetailPeminjaman> detail = new ArrayList<>();
    private String tglPengembalianPertama;
    private boolean statusKembali;

    public Peminjaman(String nomorPeminjaman, Mahasiswa peminjam, Petugas penyetuju) {
        this.nomorPeminjaman = nomorPeminjaman;
        this.peminjam = peminjam;
        this.penyetuju = penyetuju;
        this.tanggalPinjam = LocalDate.now();
    }

    public void tambahDetail(Alat alat, int jumlah) {
        detail.add(new DetailPeminjaman(alat, jumlah)); // Pembuatan objek untuk komposisi
    }

    public List<DetailPeminjaman> getDetail() {
        return Collections.unmodifiableList(detail);
    }

    public int totalItem() {
        int total = 0;
        for (DetailPeminjaman d : detail) {
            total += d.getJumlah();
        }
        return total;
    }

//    // --- TUGAS MANDIRI F.1 ---
//    public void kembalikan() {
//        this.tanggalKembali = LocalDate.now();
//    }
    
    // Method pengembalian dengan eksepsi
    public void kembalikan(String tglPengembalian) throws PeminjamanSudahDitutupException {
        if (this.sudahKembali()) {
            throw new PeminjamanSudahDitutupException(this.nomorPeminjaman, this.tglPengembalianPertama);
        }
        // Set status kembali dan catat tanggal pengembalian
        this.statusKembali = true;
        this.tglPengembalianPertama = tglPengembalian;
    }

// Method menghitung denda (Rp2.000 / hari keterlambatan)
    public int hitungDenda(int hariKeterlambatan) {
        if (hariKeterlambatan <= 0) {
            return 0;
        }
        return hariKeterlambatan * 2000;
    }

    // --- TUGAS MANDIRI F.2 ---
    public long hitungHariTerlambat() {
        if (tanggalKembali == null) {
            return 0; // Jika belum dikembalikan, abaikan/belum dihitung denda
        }
        long selisihHari = ChronoUnit.DAYS.between(tanggalPinjam, tanggalKembali);
        if (selisihHari > 7) {
            return selisihHari - 7;
        }
        return 0;
    }
    
    public String getNomorPeminjaman() {
    return nomorPeminjaman;
    }
    
    public Mahasiswa getPeminjam() {
        return peminjam;
    }

    public boolean sudahKembali() {
        return tanggalKembali != null;
    }

    
    public void kembalikan() {
        this.tanggalKembali = java.time.LocalDate.now();
    }

    
    }
