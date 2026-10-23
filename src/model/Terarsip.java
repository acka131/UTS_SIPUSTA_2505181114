package model;

public interface Terarsip {
    String nomorRak();
    int lokasiLantai(); // Misalnya mengembalikan lantai berdasarkan kode rak

    default String ringkasanArsip() {
        return "Lokasi Fisik: Rak " + nomorRak() + " (Lantai " + lokasiLantai() + ")";
    }
}