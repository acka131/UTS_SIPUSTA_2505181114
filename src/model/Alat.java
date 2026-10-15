//package model;
//
//public abstract class Alat {
//    protected String kodeAlat;
//    protected String nama;
//    protected int tahunPerolehan;
//
//    public Alat(String kodeAlat, String nama, int tahunPerolehan) {
//        this.kodeAlat = kodeAlat;
//        this.nama = nama;
//        this.tahunPerolehan = tahunPerolehan;
//    }
//
//    public String deskripsi() {
//        return kodeAlat + " - " + nama + " (" + tahunPerolehan + ")";
//    }
//
//    public abstract boolean siapDipinjam();
//
//    
//        public String laporanRingkas() {
//        String status = siapDipinjam() ? "SIAP" : "TIDAK SIAP";
//        return String.format("%-8s%-22s%s", kodeAlat, nama, status);
//    }
//        
//    public String getNama() {
//        return nama;
//    }
//    
//    
//    public String getKodeAlat() {
//        return kodeAlat;
//    }
//    
//    // Dibantu ChatGPT untuk menyediakan tahun perolehan
//    // yang diperlukan oleh pembanding UrutTahun.
//    public int getTahunPerolehan() {
//        return tahunPerolehan;
//    }
//    
//    
//    
//    }

package model;
import java.io.Serializable;

public abstract class Alat implements Serializable {
    protected String kodeAlat;
    protected String nama;
    protected int tahunPerolehan;
    
    // Dibantu ChatGPT untuk menyimpan status awal peminjaman alat.
    private StatusAlat status = StatusAlat.TERSEDIA;

    public Alat(String kodeAlat, String nama, int tahunPerolehan) {
        this.kodeAlat = kodeAlat;
        this.nama = nama;
        this.tahunPerolehan = tahunPerolehan;
    }

    public String deskripsi() {
        return kodeAlat + " - " + nama + " (" + tahunPerolehan + ")";
    }

    public abstract boolean siapDipinjam();

       
    public String laporanRingkas() {

        String status = siapDipinjam()
                ? "SIAP"
                : "TIDAK SIAP";

        return String.format(
                "%-8s %-22s %s",
                kodeAlat,
                nama,
                status
        );
    }
    
    public String getNama() {
        return nama;
    }
    
    // Dibantu ChatGPT untuk menyediakan akses kode alat
    // yang diperlukan oleh pencarian pada Jobsheet 5.
    public String getKodeAlat() {
        return kodeAlat;
    }
    
    
    
    // Dibantu ChatGPT untuk menyediakan tahun perolehan
    // yang diperlukan oleh pembanding UrutTahun.
    public int getTahunPerolehan() {
        return tahunPerolehan;
    }
    
    // Dibantu ChatGPT untuk menyediakan akses dan perubahan status peminjaman.
    public StatusAlat getStatus() {
        return status;
    }

    public void setStatus(StatusAlat status) {
        this.status = status;
    }
   
    public double getBeratKg() {
        double beratKg = 0;
    return beratKg;
}
    
}