/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transaksi;
import model.Alat;

/**
 *
 * @author acka
 */
public class DetailPeminjaman {
    private final Alat alat;
    private final int jumlah; // Atribut jumlah berada pada DetailPeminjaman, BUKAN pada Alat

    public DetailPeminjaman(Alat alat, int jumlah) {
        this.alat = alat;
        this.jumlah = jumlah;
    }

    public Alat getAlat() {
        return alat;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String baris() {
        return jumlah + " x " + alat.getNama();
    }
}
