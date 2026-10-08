/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eksepsi;

/**
 *
 * @author acka
 */
 public class AlatBelumSiapException extends SipalabException {
    public AlatBelumSiapException(String kodeAlat, String alasan) {
        super("Alat " + kodeAlat + " belum siap dipinjam. " + alasan);
    }
}

