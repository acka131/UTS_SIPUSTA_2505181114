/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eksepsi;

/**
 *
 * @author acka
 */
public class KuotaPeminjamanHabisException extends SipalabException {
    public KuotaPeminjamanHabisException(String nama, int kuota) {
        super("Kuota peminjaman " + nama + " sudah penuh, batasnya " + kuota + " alat.");
    }
}

