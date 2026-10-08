package model;

// Dibantu ChatGPT untuk menyediakan status peminjaman yang diperlukan E.3.
public enum StatusAlat {
    TERSEDIA,
    DIPINJAM;

    public boolean sedangDiluar() {
        return this == DIPINJAM;
    }
}