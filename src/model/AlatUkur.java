///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package model;
//import java.time.LocalDate;
//
///**
// *
// * @author acka
// */
//public class AlatUkur extends Alat implements Kalibrasiable {
//    private String satuan;
//    private LocalDate kalibrasiTerakhir;
//    
//    public AlatUkur(String kodeAlat, String nama, int tahunPerolehan,
//            String satuan, LocalDate kalibrasiTerakhir) {
//        super(kodeAlat, nama, tahunPerolehan);
//        this.satuan = satuan;
//        this.kalibrasiTerakhir = kalibrasiTerakhir;
//}
//@Override
//public LocalDate jatuhTempoKalibrasi() {
//return kalibrasiTerakhir.plusMonths(12);
//}
//@Override
//public boolean perluKalibrasi() {
//return LocalDate.now().isAfter(jatuhTempoKalibrasi());
//}
//@Override
//public boolean siapDipinjam() {
//return !perluKalibrasi();
//}
//@Override
//public String deskripsi() {
//return super.deskripsi() + " satuan " + satuan;
//}
//}

package model;
import java.time.LocalDate;

public class AlatUkur extends Alat implements Kalibrasiable {
    private String satuan;
    private LocalDate kalibrasiTerakhir;

    public AlatUkur(String kodeAlat, String nama, int tahunPerolehan, String satuan, LocalDate kalibrasiTerakhir) {
        super(kodeAlat, nama, tahunPerolehan);
        this.satuan = satuan;
        this.kalibrasiTerakhir = kalibrasiTerakhir;
    }

    @Override
    public LocalDate jatuhTempoKalibrasi() {
        return kalibrasiTerakhir.plusMonths(12);
    }

    @Override
    public boolean perluKalibrasi() {
        return LocalDate.now().isAfter(jatuhTempoKalibrasi());
    }

    @Override
    public boolean siapDipinjam() {
        return !perluKalibrasi();
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " satuan " + satuan;
    }
}