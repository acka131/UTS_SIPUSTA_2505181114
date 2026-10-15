package berkas;

import java.io.*;
import java.util.List;
import model.Alat;

public class PenyimpanBerkas {
    private final String namaBerkas;

    public PenyimpanBerkas(String namaBerkas) {
        this.namaBerkas = namaBerkas;
    }

    // Method untuk menyimpan (serialisasi) list objek ke file .dat
    public void simpan(List<Alat> daftarAlat) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(namaBerkas))) {
            oos.writeObject(daftarAlat);
        }
    }

    // Method untuk memuat (deserialisasi) kembali data dari file .dat (Tugas Mandiri)
    @SuppressWarnings("unchecked")
    public List<Alat> muat() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(namaBerkas))) {
            return (List<Alat>) ois.readObject();
        }
    }
}
