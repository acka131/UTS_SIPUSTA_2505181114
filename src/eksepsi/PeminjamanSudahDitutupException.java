package eksepsi;

public class PeminjamanSudahDitutupException extends SipalabException {
    public PeminjamanSudahDitutupException(String nomorPeminjaman, String tglPengembalian) {
        super("Peminjaman " + nomorPeminjaman + " sudah ditutup pada tanggal " + tglPengembalian + ".");
    }
}