package model;

public class BerkasDigital extends Koleksi {
    private double ukuranMB;
    private String tautanUnduh;

    public BerkasDigital(String kodeKoleksi, String judul, int tahunTerbit, double ukuranMB, String tautanUnduh) {
        super(kodeKoleksi, judul, tahunTerbit);
        this.ukuranMB = ukuranMB;
        this.tautanUnduh = tautanUnduh;
    }

    @Override
    public boolean isLayakDipinjam() {
        return true; // Selalu layak dipinjam
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + String.format(" - Digital (%.1f MB), URL: %s", ukuranMB, tautanUnduh);
    }
}