package pembanding;

import java.util.Comparator;
import model.Alat;

// Dibantu ChatGPT untuk mengurutkan tahun terbaru lebih dahulu,
// kemudian nama jika tahun perolehannya sama.
public class UrutTahun implements Comparator<Alat> {

    @Override
    public int compare(Alat a, Alat b) {
        int bandingTahun = Integer.compare(
                b.getTahunPerolehan(),
                a.getTahunPerolehan()
        );

        if (bandingTahun != 0) {
            return bandingTahun;
        }

        return a.getNama().compareToIgnoreCase(b.getNama());
    }
}
