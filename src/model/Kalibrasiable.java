///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
// */
//package model;
//import java.time.LocalDate;
//
///**
// *
// * @author acka
// */
//public interface Kalibrasiable {
//   
//    boolean perluKalibrasi();
//   
//    LocalDate jatuhTempoKalibrasi();
//    
//default String statusKalibrasi() {
//  if (perluKalibrasi()) {
//      return "PERLU KALIBRASI sejak " + jatuhTempoKalibrasi();
//  }
//  return "LAYAK PAKAI sampai " + jatuhTempoKalibrasi();
// }
//}
//
package model;
import java.time.LocalDate;

public interface Kalibrasiable {
    boolean perluKalibrasi();
    LocalDate jatuhTempoKalibrasi();
    
    default String statusKalibrasi() {
        if (perluKalibrasi()) {
            return "PERLU KALIBRASI sejak " + jatuhTempoKalibrasi();
        }
        return "LAYAK PAKAI sampai " + jatuhTempoKalibrasi();
    }
}