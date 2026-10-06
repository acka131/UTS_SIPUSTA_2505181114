package pembanding;

import java.util.Comparator;
import model.Alat;

// Dibantu ChatGPT untuk menerapkan pengurutan nama
// tanpa membedakan huruf besar dan kecil.
public class UrutNama implements Comparator<Alat> {

    @Override
    public int compare(Alat a, Alat b) {
        return a.getNama().compareToIgnoreCase(b.getNama());
    }
}
