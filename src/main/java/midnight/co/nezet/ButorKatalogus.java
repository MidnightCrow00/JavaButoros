package midnight.co.nezet;


import midnight.co.modell.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ButorKatalogus {
    private JFrame frame;
    private JPanel pnlMain;
    private JComboBox comboBox1;
    private JList list1;
    private JCheckBox mozgatasChb;
    private JButton ujSzekBtn;
    private JButton masolasButton;
    private JButton felveszButton;
    private JTextField textField;
    private JMenuItem mnuMent;
    private JMenuItem mnuBetolt;
    private JMenuItem mnuKilepes;

    private Butor butor;
    private List<Butor> butorok=new ArrayList<>();
    public ButorKatalogus() {
        ini();
    }

    private void ini() {
        frame = new JFrame("Bútor Katalógus");
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Menü
        JMenuBar mnuBar = new JMenuBar();
        JMenu mnuPrg = new JMenu("Menu");
        mnuMent = new JMenuItem("Mentés");
        mnuBetolt = new JMenuItem("Betöltés");
        mnuKilepes = new JMenuItem("Kilépés");

        DefaultListModel<String> dlm = new DefaultListModel<>();
        list1.setModel(dlm);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                kilepes();
            }
        });

        mnuPrg.add(mnuMent);
        mnuPrg.add(mnuBetolt);
        mnuPrg.add(new JSeparator());
        mnuPrg.add(mnuKilepes);
        mnuBar.add(mnuPrg);
        frame.setJMenuBar(mnuBar);
        frame.setVisible(true);
        frame.pack(); // pontosan illeszkedjen a gui elemek méretéhez

        mnuKilepes.addActionListener(e -> kilepes());

        mnuBetolt.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir")));
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fajl = jfc.getSelectedFile();
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fajl))) {
                    Butor betoltott = (Butor) ois.readObject();  // Egyetlen Butor objektum beolvasása
                    butor = betoltott;
                    comboBox1.removeAllItems();
                    if (butor instanceof Asztal) {
                        comboBox1.addItem(butor.getGyarto() + " (asztal)");
                    } else if (butor instanceof Szek) {
                        comboBox1.addItem(butor.getGyarto() + " (szék)");
                    } else {
                        comboBox1.addItem(butor.getGyarto() + " (bútor)");
                    }

                } catch (FileNotFoundException ex) {
                    System.err.println("Betöltés: Fájl nem található: " + ex.getMessage());
                } catch (IOException ex) {
                    System.err.println("Betöltés: I/O hiba: " + ex.getMessage());
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        mnuMent.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir")));
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fajl = jfc.getSelectedFile();
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fajl))) {
                    oos.writeObject(butor);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        ujSzekBtn.addActionListener(e -> {
            Szek sz = new Szek(new TreeSet<>(Set.of("Ismeretlen")),new HashSet<>(Set.of("fa")),2000, Kategoria.Luxus,  "emelheto");
            butorok.add(sz);
            comboBox1.addItem(sz.getGyarto() + " (szék)");
        });

        masolasButton.addActionListener(e -> {
            String elem = (String) comboBox1.getSelectedItem();
            int i = comboBox1.getSelectedIndex();
            if (i > -1) {
                DefaultListModel<String> lm = (DefaultListModel<String>) list1.getModel();
                lm.addElement(elem);
                if (mozgatasChb.isSelected()) {
                    comboBox1.removeItem(elem);
                    if (comboBox1.getItemCount() > 0)
                        comboBox1.setSelectedIndex(0);
                }
            }
        });

        // 📝 Elem felvitele comboba
        felveszButton.addActionListener(e -> {
            String elem = textField.getText().isBlank()
                    ? "elem: " + (int)(Math.random() * 100)
                    : textField.getText();
            comboBox1.addItem(elem);
            textField.setText("");
        });

        // 🧹 Dupla klikk törlés listából
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    DefaultListModel<String> lm = (DefaultListModel<String>) list1.getModel();
                    lm.removeElement(list1.getSelectedValue());
                }
            }
        });

    }


    private void kilepes() {
        String msg = "Biztos kilépsz?";
        int opt = JOptionPane.showConfirmDialog(null, msg, "Kilépés", JOptionPane.OK_CANCEL_OPTION);
        if (opt == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ButorKatalogus();
    }
}
