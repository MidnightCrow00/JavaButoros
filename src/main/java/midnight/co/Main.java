package midnight.co;

import midnight.co.modell.Butor;
import midnight.co.modell.Kategoria;
import midnight.co.modell.Szek;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    TreeSet<String> gyarto;
    HashSet<String> anyag;
    private ArrayList<Butor> butorok;

    public static void main(String[] args) throws CloneNotSupportedException {
        new Main().feladatok();


    }

    private void feladatok() throws CloneNotSupportedException {
        butorok = new ArrayList<>();
        anyag = new HashSet<>();
        gyarto = new TreeSet<>();
        anyag.add("fa");
        gyarto.add("Boss");
        Butor b1 = new Butor(gyarto, anyag, 220000, Kategoria.Luxus);
        Butor b2 = new Butor(new TreeSet<>(Set.of("Karma")), anyag, 100, Kategoria.Luxus);
        Butor b3 = (Butor) b1.clone();
        Butor b4 = new Butor(new TreeSet<>(Set.of("VAnHAn")), new HashSet<>(Set.of("fém")), 5400, Kategoria.Luxus);
        butorok.add(b1);
        butorok.add(b2);
        butorok.add(b3);
        butorok.add(b4);
        Butor szek1 = new Szek(new TreeSet<>(Set.of("IKEA")), new HashSet<>(Set.of("műanyag")), 10000, Kategoria.OLCSO, "Magas");
        butorok.add(szek1);

        Collections.sort(butorok);
        System.out.println("Ár szerint:"+butorok);
        butorok.sort(Butor.rendezGyarto());
//        System.out.println(b1);
        System.out.println("gyarto szerint:"+butorok);
        binarisFajlbaIras();
        binarisFajlbolOlvasas();
        szovegesFajlbaIras();
        for (Butor b : butorok) {
            if (b.getAnyag().contains("fa")){
                System.out.println(b);
            }
        }
    }



    private void szovegesFajlbaIras() {
        List<String> sorok = new ArrayList<>();
        for (Butor b : butorok) {
            sorok.add(b.toString()); // minden bútor toString() sorba
        }

        try {
            Files.write(Path.of("butoros.txt"), sorok); // írás a fájlba
            System.out.println("Fájl sikeresen kiírva.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void binarisFajlbolOlvasas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("butorok.bin"))) {
            ArrayList<Butor> beolvasottButorok = (ArrayList<Butor>) ois.readObject();
            System.out.println("Beolvasott bútorok: " + beolvasottButorok);
        } catch (IOException e) {
            throw new RuntimeException("IO hiba olvasáskor", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Osztály nem található", e);
        }
    }

    private void binarisFajlbaIras() {
        try(ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("butorok.bin"))){
            oos.writeObject(butorok);
            System.out.println("Butor kiirva"+butorok);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}