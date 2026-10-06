
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Dibantu ChatGPT untuk menyusun pengukuran sederhana
// pencarian data pada ArrayList dan HashSet.
public class UjiKoleksi {

    public static void main(String[] args) {
        int jumlah = 100000;

        List<String> daftar = new ArrayList<>();
        Set<String> himpunan = new HashSet<>();

        for (int i = 0; i < jumlah; i++) {
            String kode = "AL-" + i;

            daftar.add(kode);
            himpunan.add(kode);
        }

        String dicari = "AL-" + (jumlah - 1);

        long mulai = System.nanoTime();
        boolean ditemukanList = daftar.contains(dicari);
        long waktuList = System.nanoTime() - mulai;

        mulai = System.nanoTime();
        boolean ditemukanSet = himpunan.contains(dicari);
        long waktuSet = System.nanoTime() - mulai;

        System.out.println("Kode yang dicari: " + dicari);

        System.out.println("Ditemukan pada ArrayList: "
                + ditemukanList);

        System.out.println("Ditemukan pada HashSet: "
                + ditemukanSet);

        System.out.println("ArrayList: "
                + waktuList + " nanodetik");

        System.out.println("HashSet: "
                + waktuSet + " nanodetik");
    }
}
