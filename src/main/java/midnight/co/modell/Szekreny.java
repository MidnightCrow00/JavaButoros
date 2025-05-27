package midnight.co.modell;

import java.util.HashSet;
import java.util.TreeSet;

public class Szekreny extends Butor{
    private int fiokokSzama;

    public Szekreny(TreeSet<String> gyarto, HashSet<String> anyag, int ar, Kategoria kategoria, int fiokokSzama) {
        super(gyarto, anyag, ar, kategoria);
        this.fiokokSzama = fiokokSzama;
    }
}
