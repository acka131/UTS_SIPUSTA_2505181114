/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eksepsi;

/**
 *
 * @author acka
 */
   public class AlatSedangDipinjamException extends SipalabException {
    public AlatSedangDipinjamException(String kodeAlat) {
        super("Alat " + kodeAlat + " sedang dipinjam dan belum dikembalikan.");
    }
}

