package model;

public abstract class Koleksi {
    private String kodeKoleksi;
    private String judul;
    private int tahunTerbit;
    private boolean sedangDipinjam; // Diperlukan untuk logika peminjaman & eksepsi

    public Koleksi(String kodeKoleksi, String judul, int tahunTerbit) {
        this.kodeKoleksi = kodeKoleksi;
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
        this.sedangDipinjam = false;
    }

    // Abstract method wajib
    public abstract boolean isLayakDipinjam();

    // Method deskripsi yang nantinya di-override & diperluas pakai super
    public String deskripsi() {
        return String.format("[%s] %s (%d)", kodeKoleksi, judul, tahunTerbit);
    }

    // Getter & Setter
    public String getKodeKoleksi() { return kodeKoleksi; }
    public String getJudul() { return judul; }
    public int getTahunTerbit() { return tahunTerbit; }
    public boolean isSedangDipinjam() { return sedangDipinjam; }
    public void setSedangDipinjam(boolean sedangDipinjam) { this.sedangDipinjam = sedangDipinjam; }
}