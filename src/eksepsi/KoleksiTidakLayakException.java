package eksepsi;

public class KoleksiTidakLayakException extends PustakaException {
    public KoleksiTidakLayakException(String judulKoleksi, String alasan) {
        super("Gagal meminjam: Koleksi \"" + judulKoleksi + "\" tidak layak untuk dipinjam saat ini (" + alasan + ").");
    }
}