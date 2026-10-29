package basisdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eksepsi.KesalahanBasisDataException;
import model.Alat;

public class AlatDAOMemori implements AlatDAO {

    // Menyimpan data alat di dalam Map (sebagai pengganti tabel basis data)
    private final Map<String, Alat> dataAlat = new HashMap<>();

    @Override
    public void simpan(Alat alat) throws KesalahanBasisDataException {
        if (dataAlat.containsKey(alat.getKodeAlat())) {
            throw new KesalahanBasisDataException("Alat dengan kode " + alat.getKodeAlat() + " sudah ada!", null);
        }
        dataAlat.put(alat.getKodeAlat(), alat);
    }

    @Override
    public void perbarui(Alat alat) throws KesalahanBasisDataException {
        if (!dataAlat.containsKey(alat.getKodeAlat())) {
            throw new KesalahanBasisDataException("Alat dengan kode " + alat.getKodeAlat() + " tidak ditemukan!", null);
        }
        dataAlat.put(alat.getKodeAlat(), alat);
    }

    @Override
    public void hapus(String kodeAlat) throws KesalahanBasisDataException {
        dataAlat.remove(kodeAlat);
    }

    @Override
    public Alat cariKode(String kodeAlat) throws KesalahanBasisDataException {
        return dataAlat.get(kodeAlat);
    }

    @Override
    public List<Alat> semua() throws KesalahanBasisDataException {
        return new ArrayList<>(dataAlat.values());
    }

    @Override
    public String namaSumber() {
        return "Memori (Penyimpanan Sementara/RAM)";
    }
}