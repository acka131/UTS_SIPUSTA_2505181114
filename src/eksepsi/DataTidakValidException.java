/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eksepsi;

/**
 *
 * @author acka
 */
public class DataTidakValidException extends SipalabException {
    private final String namaKolom;

    public DataTidakValidException(String namaKolom, String pesan) {
        super(pesan);
        this.namaKolom = namaKolom;
    }

    public String getNamaKolom() {
        return namaKolom;
    }
}

