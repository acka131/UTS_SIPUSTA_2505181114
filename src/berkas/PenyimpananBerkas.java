/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berkas;
import java.io.*;
import java.util.List;
import model.Alat;


/**
 *
 * @author acka
 */
public class PenyimpananBerkas {
    public class PenyimpanBerkas {
    private String namaBerkas;

    public PenyimpanBerkas(String namaBerkas) {
        this.namaBerkas = namaBerkas;
    }

    // Method untuk Menyimpan Objek (Serialisasi)
    public void simpan(List<Alat> daftarAlat) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(namaBerkas))) {
            oos.writeObject(daftarAlat);
            System.out.println("Data berhasil disimpan ke " + namaBerkas);
        }
    }

    // Method untuk Membaca Kembali Objek (Deserialisasi)
    @SuppressWarnings("unchecked")
    public List<Alat> muat() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(namaBerkas))) {
            return (List<Alat>) ois.readObject();
        }
    }
    
    public void tulisCSV(String namaBerkasCSV, List<Alat> daftar) throws IOException {
    try (PrintWriter pw = new PrintWriter(new FileWriter(namaBerkasCSV))) {
        pw.println("KodeAlat,Nama,TahunPerolehan,BeratKg,Status");
        
        // Menulis Data Baris demi Baris
        for (Alat a : daftar) {
            String status = a.siapDipinjam() ? "Tersedia" : "Dipinjam";
            pw.printf("%s,%s,%d,%.2f,%s%n",
                    a.getKodeAlat(),
                    a.getNama(),
                    a.getTahunPerolehan(),
                    a.getBeratKg(),
                    status);
        }
        System.out.println("Laporan CSV berhasil diekspor ke " + namaBerkasCSV);
    }
}
}
}
