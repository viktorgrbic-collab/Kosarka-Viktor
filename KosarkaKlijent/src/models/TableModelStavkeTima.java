/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.Igrac;
import domain.StavkaTima;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Viktor 
 */
public class TableModelStavkeTima extends AbstractTableModel {

    private ArrayList<StavkaTima> lista;
    private String[] kolone = {"Rb", "Igrac", "Broj dresa", "Pozicija"};
    private int rb;

    public TableModelStavkeTima() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeTima(ArrayList<StavkaTima> stavkeTima) {
        lista = stavkeTima;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaTima st = lista.get(row);

        switch (column) {
            case 0:
                return st.getRb();
            case 1:
                return st.getIgrac();
            case 2:
                return st.getBrojNaDresu();
            case 3:
                return st.getPozicija();

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaTima st) {
        rb = lista.size();
        st.setRb(++rb);
        lista.add(st);
        fireTableDataChanged();
    }

    public boolean postojiBrojDresa(int brojDresa) {
        for (StavkaTima st : lista) {
            if (st.getBrojNaDresu() == brojDresa) {
                return true;
            }
        }
        return false;
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaTima stavkaTima : lista) {
            stavkaTima.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public ArrayList<StavkaTima> getLista() {
        return lista;
    }

    public boolean postojiIgrac(Igrac igrac) {
        for (StavkaTima st : lista) {
            if (st.getIgrac().getIgracID().equals(igrac.getIgracID())) {
                return true;
            }
        }
        return false;
    }

}
