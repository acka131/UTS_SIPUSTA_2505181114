///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
// */
//package model;
//
//
///**
// *
// * @author acka
// */
//public interface Terlacak {
//    String nomorSeri();
//    String lokasiTerakhir();
//    
//    default String rigkasanLacak(){
//        return "perangkat dengan nomor seri: " + nomorSeri() + " saat ini berada di " + lokasiTerakhir() + "." ;
//        
//    }
//    
//}
package model;

public interface Terlacak {
    String nomorSeri();
    String lokasiTerakhir();
    
    default String ringkasanLacak() {
        return nomorSeri() + " berada di " + lokasiTerakhir();
    }
}