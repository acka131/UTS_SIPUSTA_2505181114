/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

/**
 *
 * @author acka
 */
public interface reservable {
    boolean reservasi(String nim, String tanggalPinjam);
    boolean batalkanReservasi(String nim);
    boolean isDireservasi();
}
