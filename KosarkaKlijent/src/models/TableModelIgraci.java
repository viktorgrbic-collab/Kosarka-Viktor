/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Igrac;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Viktor
 */
public class TableModelIgraci extends AbstractTableModel implements Runnable {

    private ArrayList<Igrac> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Visina"};
    private String parametarIme = "";
    private String parametarPrezime = "";
    private Igrac igrac = new Igrac(null, "", "", 0);

    public TableModelIgraci() {
        try {
            lista = ClientController.getInstance()
                    .getAllIgrac(new Igrac(null, "", "", 0));
        } catch (Exception ex) {
            Logger.getLogger(TableModelIgraci.class.getName()).log(Level.SEVERE, null, ex);
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
        Igrac i = lista.get(row);

        switch (column) {
            case 0:
                return i.getIgracID();
            case 1:
                return i.getIme();
            case 2:
                return i.getPrezime();
            case 3:
                return i.getVisina();

            default:
                return null;
        }
    }

    public Igrac getSelectedIgrac(int row) {
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
            Logger.getLogger(TableModelIgraci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarIme(String ime) {
        this.parametarIme = ime;
        refreshTable();
    }

    public void setParametarPrezime(String prezime) {
        this.parametarPrezime = prezime;
        refreshTable();
    }

    public void refreshTable() {
        try {

            igrac.setIme(parametarIme.toLowerCase());
            igrac.setPrezime(parametarPrezime.toLowerCase());

            lista = ClientController.getInstance().getAllIgrac(igrac);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Igrac> getLista() {
        return lista;
    }
}
