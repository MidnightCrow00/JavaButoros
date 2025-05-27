package midnight.co.modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GuiKonfigModell implements Serializable {
    private List<String> comboSzovekeg;
    private List<String> listSzovegek;
    private boolean chbMozgat;

    public GuiKonfigModell(List<String> comboSzovekeg, List<String> listSzovegek, boolean chbMozgat) {
        this.comboSzovekeg = new ArrayList<>();
        this.listSzovegek = new ArrayList<>();
        this.chbMozgat = chbMozgat;
    }

    public List<String> getComboSzovekeg() {
        return comboSzovekeg;
    }

    public List<String> getListSzovegek() {
        return listSzovegek;
    }

    public boolean isChbMozgat() {
        return chbMozgat;
    }

    public void setComboSzovekeg(List<String> comboSzovekeg) {
        this.comboSzovekeg = comboSzovekeg;
    }

    public void setListSzovegek(List<String> listSzovegek) {
        this.listSzovegek = listSzovegek;
    }

    public void setChbMozgat(boolean chbMozgat) {
        this.chbMozgat = chbMozgat;
    }
}
