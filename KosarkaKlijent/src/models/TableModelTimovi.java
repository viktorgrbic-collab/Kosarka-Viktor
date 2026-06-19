/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Drzava;
import domain.Liga;
import domain.Statisticar;
import domain.Tim;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Viktor
 */
public class TableModelTimovi extends AbstractTableModel implements Runnable {

    private ArrayList<Tim> lista;
    private String[] kolone = {"ID", "Naziv", "Broj igraca", "Broj utakmica",
        "Broj pobeda", "Liga"};
    private String parametarNazivTima = "";
    private String parametarImeStatisticara = "";
    private String parametarPrezimeStatisticara = "";
    private String parametarNazivLige = "";
    private Tim tim = new Tim(null, "", 0, 0, 0,
            new Liga(null, "", new Drzava(null, "")),
            new Statisticar(null, "", "", "", ""),
            null);

    public TableModelTimovi() {
        try {
            lista = ClientController.getInstance()
                    .getAllTim(new Tim(null, "", 0, 0, 0,
                            new Liga(null, "", new Drzava(null, "")),
                            new Statisticar(null, "", "", "", ""),
                            null));
        } catch (Exception ex) {
            Logger.getLogger(TableModelTimovi.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Tim t = lista.get(row);

        switch (column) {
            case 0:
                return t.getTimID();
            case 1:
                return t.getNaziv();
            case 2:
                return t.getBrojIgraca();
            case 3:
                return t.getBrojUtakmica();
            case 4:
                return t.getBrojPobeda();
            case 5:
                return t.getLiga();

            default:
                return null;
        }
    }

    public Tim getSelectedTim(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelTimovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarNazivTima(String nazivTima) {
        this.parametarNazivTima = nazivTima;
        refreshTable();
    }

    public void setParametarNazivLige(String nazivLige) {
        this.parametarNazivLige = nazivLige;
        refreshTable();
    }

    public void setParametarIme(String ime) {
        this.parametarImeStatisticara = ime;
        refreshTable();
    }

    public void setParametarPrezime(String prezime) {
        this.parametarPrezimeStatisticara = prezime;
        refreshTable();
    }

    public void refreshTable() {
        try {

            tim.setNaziv(parametarNazivTima.toLowerCase());
            tim.getLiga().setNaziv(parametarNazivLige.toLowerCase());
            tim.getStatisticar().setIme(parametarImeStatisticara.toLowerCase());
            tim.getStatisticar().setPrezime(parametarPrezimeStatisticara.toLowerCase());

            lista = ClientController.getInstance().getAllTim(tim);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Tim> getLista() {
        return lista;
    }

}
