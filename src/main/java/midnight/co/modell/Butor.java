package midnight.co.modell;

import java.io.Serializable;
import java.text.Collator;
import java.util.*;

public class Butor implements Cloneable, Comparable<Butor>, Serializable {
    private TreeSet<String> gyarto;
    private HashSet<String> anyag;
    private int ar;
    private Kategoria kategoria;


    public Butor(TreeSet<String> gyarto, HashSet<String> anyag, int ar, Kategoria kategoria) {
        this.gyarto = new TreeSet<>(gyarto);
        this.anyag = new HashSet<>(anyag);
        this.ar = ar;
        this.kategoria = kategoria;
    }

    public TreeSet<String> getGyarto() {
        return new TreeSet<>(gyarto);
    }

    public HashSet<String> getAnyag() {
        return new HashSet<>(anyag);
    }

    public int getAr() {
        return ar;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setGyarto(TreeSet<String> gyarto) {
        this.gyarto = gyarto;
    }

    public void setAnyag(HashSet<String> anyag) {
        this.anyag = anyag;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Butor masik) {
        return this.ar-masik.ar;
    }

    private  static class GyartoComparator implements Comparator<Butor>{
        @Override
        public int compare(Butor egyik, Butor masik){
            Collator coll = Collator.getInstance();
            String egyikStr = String.join(",", egyik.gyarto);
            String masikStr = String.join(",", masik.gyarto);
            return coll.compare(egyikStr, masikStr);
        }
    }
    public static GyartoComparator rendezGyarto(){
        return new GyartoComparator();
    }

    @Override
    public String toString() {
        return "Butor{" +
                "gyarto=" + gyarto +
                ", anyag=" + anyag +
                ", ar=" + ar +
                ", kategoria=" + kategoria +
                '}';
    }
}
