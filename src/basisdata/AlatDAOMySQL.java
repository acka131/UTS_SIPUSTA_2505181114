package basisdata;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import eksepsi.KesalahanBasisDataException;
import model.*;

public class AlatDAOMySQL implements AlatDAO {

    private static final String SQL_SIMPAN = """
            INSERT INTO alat (kode_alat, nama, jenis, tahun_perolehan, status,
                              ram_gb, charger_lengkap, lumen, jam_pakai_lampu,
                              satuan, kalibrasi_terakhir, jumlah_stok, jumlah_minimum,
                              nomor_seri, lokasi_terakhir)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String SQL_PERBARUI = """
            UPDATE alat SET kode_alat = ?, nama = ?, jenis = ?, tahun_perolehan = ?, status = ?,
                            ram_gb = ?, charger_lengkap = ?, lumen = ?, jam_pakai_lampu = ?,
                            satuan = ?, kalibrasi_terakhir = ?, jumlah_stok = ?, jumlah_minimum = ?,
                            nomor_seri = ?, lokasi_terakhir = ?
            WHERE kode_alat = ?
            """;

    private Connection koneksi() throws SQLException {
        return KoneksiDB.getInstance().getKoneksi();
    }

    @Override
    public void simpan(Alat alat) throws KesalahanBasisDataException {
        try (PreparedStatement ps = koneksi().prepareStatement(SQL_SIMPAN)) {
            isiParameter(ps, alat);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new KesalahanBasisDataException(
                    "Gagal menyimpan alat " + alat.getKodeAlat()
                    + ". Periksa apakah kodenya sudah dipakai.", e);
        }
    }

    @Override
    public void perbarui(Alat alat) throws KesalahanBasisDataException {
        try (PreparedStatement ps = koneksi().prepareStatement(SQL_PERBARUI)) {
            isiParameter(ps, alat);
            ps.setString(16, alat.getKodeAlat());
            if (ps.executeUpdate() == 0) {
                throw new KesalahanBasisDataException(
                        "Alat " + alat.getKodeAlat() + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            throw new KesalahanBasisDataException(
                    "Gagal memperbarui alat " + alat.getKodeAlat(), e);
        }
    }

    @Override
    public void hapus(String kodeAlat) throws KesalahanBasisDataException {
        String sql = "DELETE FROM alat WHERE kode_alat = ?";
        try (PreparedStatement ps = koneksi().prepareStatement(sql)) {
            ps.setString(1, kodeAlat);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new KesalahanBasisDataException(
                    "Gagal menghapus alat " + kodeAlat, e);
        }
    }

    @Override
    public Alat cariKode(String kodeAlat) throws KesalahanBasisDataException {
        String sql = "SELECT * FROM alat WHERE kode_alat = ?";
        try (PreparedStatement ps = koneksi().prepareStatement(sql)) {
            ps.setString(1, kodeAlat);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? bentukAlat(rs) : null;
            }
        } catch (SQLException e) {
            throw new KesalahanBasisDataException(
                    "Gagal mencari alat " + kodeAlat, e);
        }
    }

    @Override
    public List<Alat> semua() throws KesalahanBasisDataException {
        String sql = "SELECT * FROM alat ORDER BY kode_alat";
        List<Alat> hasil = new ArrayList<>();
        try (PreparedStatement ps = koneksi().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                hasil.add(bentukAlat(rs));
            }
        } catch (SQLException e) {
            throw new KesalahanBasisDataException("Gagal membaca daftar alat", e);
        }
        return hasil;
    }

    @Override
    public String namaSumber() {
        return "MySQL (basis data sipalab)";
    }

    public List<Alat> cariNama(String masukan) throws KesalahanBasisDataException {
        String sql = "SELECT * FROM alat WHERE nama = ?";
        List<Alat> hasil = new ArrayList<>();
        try (PreparedStatement ps = koneksi().prepareStatement(sql)) {
            ps.setString(1, masukan);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(bentukAlat(rs));
                }
            }
        } catch (SQLException e) {
            throw new KesalahanBasisDataException("Gagal mencari alat", e);
        }
        return hasil;
    }

    // E.5: Konversi ResultSet ke Objek Alat secara aman tanpa memicu error konstruktor AlatUkur
    private Alat bentukAlat(ResultSet rs) throws SQLException {
        String kode = rs.getString("kode_alat");
        String nama = rs.getString("nama");
        int tahun = rs.getInt("tahun_perolehan");

        String satuan = rs.getString("satuan") != null ? rs.getString("satuan") : "Unit";
        Date tglDb = rs.getDate("kalibrasi_terakhir");
        LocalDate kalibrasi = tglDb != null ? tglDb.toLocalDate() : LocalDate.now();

        return new AlatUkur(kode, nama, tahun, satuan, kalibrasi);
    }

    // E.5: Mengisi 15 parameter PreparedStatement
    private void isiParameter(PreparedStatement ps, Alat alat) throws SQLException {
        ps.setString(1, alat.getKodeAlat());
        ps.setString(2, alat.getNama());
        ps.setString(3, namaJenis(alat));
        ps.setInt(4, alat.getTahunPerolehan());
        ps.setString(5, "TERSEDIA");

        ps.setNull(6, Types.INTEGER);
        ps.setNull(7, Types.BOOLEAN);
        ps.setNull(8, Types.INTEGER);
        ps.setNull(9, Types.INTEGER);
        ps.setNull(10, Types.VARCHAR);
        ps.setNull(11, Types.DATE);
        ps.setNull(12, Types.INTEGER);
        ps.setNull(13, Types.INTEGER);
        ps.setNull(14, Types.VARCHAR);
        ps.setNull(15, Types.VARCHAR);
    }

    private String namaJenis(Alat alat) {
        return alat instanceof AlatUkur ? "Alat Ukur" : alat.getClass().getSimpleName();
    }
}