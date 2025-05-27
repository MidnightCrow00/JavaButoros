package midnight.co.modell;

import java.util.HashSet;
import java.util.TreeSet;

public class Szek extends Butor{
    private String hattamlaTipusa;

    public Szek(TreeSet<String> gyarto, HashSet<String> anyag, int ar, Kategoria kategoria, String hattamlaTipusa) {
        super(gyarto, anyag, ar, kategoria);
        this.hattamlaTipusa = hattamlaTipusa;
    }

    public String getHattamlaTipusa() {
        return hattamlaTipusa;
    }

    public void setHattamlaTipusa(String hattamlaTipusa) {
        this.hattamlaTipusa = hattamlaTipusa;
    }

    @Override
    public String toString() {
        return "Szek{" +
                "hattamlaTipusa='" + hattamlaTipusa + '\'' +
                '}';
    }
}