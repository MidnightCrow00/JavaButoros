package midnight.co.modell;

import java.util.HashSet;
import java.util.TreeSet;

public class Asztal extends Butor{
    private int ferohelyekSzama;

    public Asztal(TreeSet<String> gyarto, HashSet<String> anyag, int ar, Kategoria kategoria, int ferohelyekSzama) {
        super(gyarto, anyag, ar, kategoria);
        this.ferohelyekSzama = ferohelyekSzama;
    }

    public int getFerohelyekSzama() {
        return ferohelyekSzama;
    }

    public void setFerohelyekSzama(int ferohelyekSzama) {
        this.ferohelyekSzama = ferohelyekSzama;
    }

    @Override
    public String toString() {
        return "Asztal{" +
                "ferohelyekSzama=" + ferohelyekSzama +
                '}';
    }
}
