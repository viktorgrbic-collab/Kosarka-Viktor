/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Drzava;
import domain.Liga;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Viktor 
 */
public class TableModelLige extends AbstractTableModel implements Runnable {

    private ArrayList<Liga> lista;
    private String[] kolone = {"ID", "Naziv", "Drzava"};
    private String parametarNazivLige = "";
    private String parametarNazivDrzave = "";
    private Liga liga = new Liga(null, "", new Drzava(null, ""));

    public TableModelLige() {
        try {
            lista = ClientController.getInstance()
                    .getAllLiga(new Liga(null, "", new Drzava(null, "")));
        } catch (Exception ex) {
            Logger.getLogger(TableModelLige.class.getName()).log(Level.SEVERE, null, ex);
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
        Liga liga = lista.get(row);

        switch (column) {
            case 0:
                return liga.getLigaID();
            case 1:
                return liga.getNaziv();
            case 2:
                return liga.getDrzava();

            default:
                return null;
        }
    }

    public Liga getSelectedLiga(int row) {
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
            Logger.getLogger(TableModelLige.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarNazivLige(String nazivLige) {
        this.parametarNazivLige = nazivLige;
        refreshTable();
    }

    public void setParametarNazivDrzave(String nazivDrzave) {
        this.parametarNazivDrzave = nazivDrzave;
        refreshTable();
    }

    public void refreshTable() {
        try {

            liga.setNaziv(parametarNazivLige.toLowerCase());
            liga.getDrzava().setNaziv(parametarNazivDrzave.toLowerCase());

            lista = ClientController.getInstance().getAllLiga(liga);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Liga> getLista() {
        return lista;
    }
}
