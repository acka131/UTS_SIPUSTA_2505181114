package model;

public class Komponen extends Alat {
    private static final long serialVersionUID = 1L;
    private int jumlahStok;
    private int jumlahMinimum;

    public Komponen(String kodeAlat, String nama, int tahunPerolehan,
                     int jumlahStok, int jumlahMinimum) {
        super(kodeAlat, nama, tahunPerolehan);
        this.jumlahStok = jumlahStok;
        this.jumlahMinimum = jumlahMinimum;
    }

    @Override
    public boolean siapDipinjam() {
        return jumlahStok > jumlahMinimum;
    }

    public String jenis() {
        return "Komponen";
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " Stok " + jumlahStok + "/" + jumlahMinimum;
    }

    public int getJumlahStok() {
        return jumlahStok;
    }

    public int getJumlahMinimum() {
        return jumlahMinimum;
    }

    public void setJumlahStok(int jumlahStok) {
        this.jumlahStok = jumlahStok;
    }
}