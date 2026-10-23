package model;

public class BukuCetak extends Koleksi implements Terarsip {
    private int jumlahHalaman;
    private String nomorRak;
    private boolean sedangDiperbaiki;

    public BukuCetak(String kodeKoleksi, String judul, int tahunTerbit, int jumlahHalaman, String nomorRak, boolean sedangDiperbaiki) {
        super(kodeKoleksi, judul, tahunTerbit);
        this.jumlahHalaman = jumlahHalaman;
        this.nomorRak = nomorRak;
        this.sedangDiperbaiki = sedangDiperbaiki;
    }

    @Override
    public boolean isLayakDipinjam() {
        return !sedangDiperbaiki; // Layak jika tidak sedang diperbaiki
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + String.format(" - Buku Cetak, %d hal, Rak: %s, Status: %s", 
                jumlahHalaman, nomorRak, sedangDiperbaiki ? "Sedang Diperbaiki" : "Baik");
    }

    @Override
    public String nomorRak() { return nomorRak; }

    @Override
    public int lokasiLantai() { return 1; } // Contoh penetapan lantai
}