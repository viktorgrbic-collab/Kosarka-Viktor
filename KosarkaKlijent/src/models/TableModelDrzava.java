/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Drzava;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Viktor
 */
public class TableModelDrzava extends AbstractTableModel implements Runnable {

    private ArrayList<Drzava> lista;
    private String[] kolone = {"ID", "Naziv",};
  
    private String parametarNazivDrzave = "";
    private Drzava drzava = new Drzava(null, "");

    public TableModelDrzava() {
        try {
            lista = ClientController.getInstance().getAllDrzava(new Drzava(null,""));
        } catch (Exception ex) {
            Logger.getLogger(TableModelDrzava.class.getName()).log(Level.SEVERE, null, ex);
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
        Drzava drzava = lista.get(row);

        switch (column) {
            case 0:
                return drzava.getDrzavaID();
            case 1:
                return drzava.getNaziv() ;
           

            default:
                return null;
        }
    }

    public Drzava getSelectedDrzava(int row) {
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
            Logger.getLogger(TableModelDrzava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarNazivDrzave(String nazivDrzave) {
        this.parametarNazivDrzave = nazivDrzave;
        refreshTable();
    }

   
    public void refreshTable() {
        try {

            drzava.setNaziv(parametarNazivDrzave.toLowerCase());
           

            lista = ClientController.getInstance().getAllDrzava(drzava);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Drzava> getLista() {
        return lista;
    }
}
