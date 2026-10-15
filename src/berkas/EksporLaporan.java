package berkas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import model.Alat;

public class EksporLaporan {
    
    public void tulisInventaris(String namaBerkas, List<Alat> daftar) throws IOException {
        try (PrintWriter tulis = new PrintWriter(
                new BufferedWriter(new FileWriter(namaBerkas)))) {
            tulis.println("LAPORAN INVENTARIS ALAT LABORATORIUM");
            tulis.println("Tanggal cetak: " + LocalDate.now());
            tulis.println();
            tulis.printf("%-10s %-26s %-14s %-14s%n", "KODE", "NAMA", "JENIS", "STATUS");
            tulis.println("-".repeat(68));
            for (Alat a : daftar) {
                tulis.printf("%-10s %-26s %-14s %-14s%n",
                        a.getKodeAlat(), 
                        a.getNama(), 
                        a.getClass().getSimpleName(), 
                        (a.siapDipinjam() ? "Tersedia" : "Dipinjam"));
            }
        }
    }

    // Method tulisCSV untuk Tugas Mandiri
    public void tulisCSV(String namaBerkasCSV, List<Alat> daftar) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(namaBerkasCSV))) {
            pw.println("KodeAlat,Nama,TahunPerolehan,BeratKg,Status");
            for (Alat a : daftar) {
                String status = a.siapDipinjam() ? "Tersedia" : "Dipinjam";
                pw.printf("%s,%s,%d,%.2f,%s%n",
                        a.getKodeAlat(),
                        a.getNama(),
                        a.getTahunPerolehan(),
                        a.getBeratKg(),
                        status);
            }
        }
    }
}
