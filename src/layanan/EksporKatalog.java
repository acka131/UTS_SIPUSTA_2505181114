package layanan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Koleksi;

public class EksporKatalog {

    public static void eksporKeFile(List<Koleksi> daftarKoleksi, String namaFile) {
        // Menggunakan try-with-resources agar stream otomatis ditutup dengan aman
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(namaFile)))) {
            
            // Header tabel agar kolom sejajar rapi
            writer.println("=======================================================================================================================");
            writer.printf("%-10s | %-32s | %-6s | %-15s | %-15s%n", 
                    "KODE", "JUDUL", "TAHUN", "KELAYAKAN", "STATUS PINJAM");
            writer.println("=======================================================================================================================");

            // Menulis baris koleksi dengan format lebar kolom fixed
            for (Koleksi k : daftarKoleksi) {
                String kelayakan = k.isLayakDipinjam() ? "LAYAK" : "TIDAK LAYAK";
                String statusPinjam = k.isSedangDipinjam() ? "DIPINJAM" : "TERSEDIA";

                // Pembatasan/formatting judul agar rapi jika terlalu panjang
                String judulFormatted = k.getJudul();
                if (judulFormatted.length() > 30) {
                    judulFormatted = judulFormatted.substring(0, 27) + "...";
                }

                writer.printf("%-10s | %-32s | %-6d | %-15s | %-15s%n",
                        k.getKodeKoleksi(),
                        judulFormatted,
                        k.getTahunTerbit(),
                        kelayakan,
                        statusPinjam);
            }

            writer.println("=======================================================================================================================");
            System.out.println("Berhasil mengimpor/ekspor katalog ke berkas: " + namaFile);

        } catch (IOException e) {
            System.err.println("Gagal mengekspor katalog: " + e.getMessage());
        }
    }
}