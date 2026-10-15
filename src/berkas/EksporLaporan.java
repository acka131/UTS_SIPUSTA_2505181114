
package berkas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import model.Alat;

public class EksporLaporan {

    // Atribusi AI: bantuan untuk memahami dan menyusun
    // method ekspor laporan inventaris ke berkas teks.
    public void tulisInventaris(String namaBerkas, List<Alat> daftar)
            throws IOException {

        try (PrintWriter tulis = new PrintWriter(
                new BufferedWriter(new FileWriter(namaBerkas)))) {

            tulis.println("LAPORAN INVENTARIS ALAT LABORATORIUM");
            tulis.println("Tanggal cetak: " + LocalDate.now());
            tulis.println();

            tulis.printf("%-10s %-26s %-14s %-14s%n",
                    "KODE", "NAMA", "JENIS", "STATUS");

            tulis.println("-".repeat(68));

            
        for (Alat a : daftar) {
            tulis.printf("%-10s %-26s %-14s %-14s%n",
            a.getKodeAlat(),
            a.getNama(),
            "-",
            a.siapDipinjam() ? "SIAP" : "TIDAK SIAP");
}


            tulis.println();
            tulis.println("Jumlah alat: " + daftar.size());
        }
    }
}
