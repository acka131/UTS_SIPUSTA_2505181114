package eksepsi;

public class KoleksiSedangDipinjamException extends PustakaException {
    public KoleksiSedangDipinjamException(String judulKoleksi) {
        super("Gagal meminjam: Koleksi \"" + judulKoleksi + "\" saat ini sedang dipinjam oleh pengguna lain. Silakan pilih koleksi lain.");
    }
}