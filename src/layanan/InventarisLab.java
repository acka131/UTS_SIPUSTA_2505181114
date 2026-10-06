///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package layanan;
//import java.util.ArrayList;
//import java.util.List;
//import model.Alat;
//
//
///**
// *
// * @author acka
// */
//public class inventarislab {
//    
//    private final List<Alat> daftarAlat = new ArrayList<>();
//    
//    public void tambah(Alat alat) {
//        daftarAlat.add(alat);
//    }
//    
//    public List<Alat> semuaAlat() {
//        return daftarAlat;
//    }
//}
//
////public void cetakStatusBercabang() {
////    for (Alat a : daftarAlat) {
////        if (a instanceof Laptopp) {
////            Laptopp 1 = (Laptopp) a ;
////            System.out.println(1.getNama() + " " + 1.siapDipinjam());
////        }else if (a instanceof proyektor) {
////            proyektor p = (proyektor) a;
////            System.out.println(p.getNama() + " " + p.siapDipinjam());
////        }else if (a instancoef AlatUkur) {
////        AlatUkur u = (AlatUkur) a;
////        System.out.println(u.getNama() + " " + u.siapDipinjam());
////    }
////  }
////}
////cara panjang nya, yang di bawah cara pendek nyaa
//
//public void cetakStatus() {
//    for (Alat a : daftarAlat) {
//        System.out.println(a.laporanRingkas());
//    }
//}
//
//
//
//

package layanan;
import java.util.ArrayList;
import java.util.List;
import model.Alat;
import model.Kalibrasiable;
import java.util.Comparator;
import pembanding.UrutStatusLaluNama;

public class InventarisLab {
    private final List<Alat> daftarAlat = new ArrayList<>();

    public void tambah(Alat alat) {
        daftarAlat.add(alat);
    }

    public List<Alat> semuaAlat() {
        return daftarAlat;
    }

    public void cetakStatus() {
        for (Alat a : daftarAlat) {
            System.out.println(a.laporanRingkas());
        }
    }

    public void cetakJadwalKalibrasi() {
        for (Alat a : daftarAlat) {
            // Pattern matching for instanceof (berlaku di Java 16/17+)
            if (a instanceof Kalibrasiable k) {
                System.out.println(a.getNama() + " -> " + k.statusKalibrasi());
            }
        }
    }

    /* 
     * TUGAS MANDIRI JOB SHEET 3:
     * Method ini tidak perlu mengetahui jenis alat (tanpa instanceof)
     * karena kita memanfaatkan Polimorfisme. JVM otomatis mencari
     * versi siapDipinjam() di kelas turunan masing-masing saat program berjalan.
     */
    public int hitungSiapDipinjam() {
        int siap = 0;
        for (Alat a : daftarAlat) {
            if (a.siapDipinjam()) {
                siap++;
            }
        }
        return siap;
    }

    public List<Alat> daftarTidakSiap() {
        List<Alat> alatTidakSiap = new ArrayList<>();
        for (Alat a : daftarAlat) {
            if (!a.siapDipinjam()) {
                alatTidakSiap.add(a);
            }
        }
        return alatTidakSiap;
    }
    
    // Dibantu ChatGPT untuk mengurutkan salinan koleksi
    // agar urutan asli inventaris tetap terjaga.
    public List<Alat> urut(Comparator<Alat> pembanding) {
        List<Alat> salinan = new ArrayList<>(daftarAlat);

        salinan.sort(pembanding);

        return salinan;
    }

    public List<Alat> urutBawaan() {
        return urut(new UrutStatusLaluNama());
    }
    
    // Dibantu ChatGPT untuk memperbaiki penghapusan elemen
    // menggunakan removeIf sesuai Jobsheet 5.
    public void hapusYangTidakSiap() {
        daftarAlat.removeIf(a -> !a.siapDipinjam());
    }
}