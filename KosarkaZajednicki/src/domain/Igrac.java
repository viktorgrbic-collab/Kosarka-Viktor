/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Viktor 
 */
public class Igrac extends AbstractDomainObject {

    private Long igracID;
    private String ime;
    private String prezime;
    private double visina;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Visina: " + visina + "cm)";
    }

    public Igrac(Long igracID, String ime, String prezime, double visina) {
        this.igracID = igracID;
        this.ime = ime;
        this.prezime = prezime;
        this.visina = visina;
    }

    public Igrac() {
    }

    @Override
    public String nazivTabele() {
        return " Igrac ";
    }

    @Override
    public String alijas() {
        return " i ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Igrac i = new Igrac(rs.getLong("IgracID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getDouble("visina"));

            lista.add(i);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, visina) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + " " + visina + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " visina = " + visina + " ";
    }

    @Override
    public String uslov() {
        return " igracID = " + igracID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY IGRACID ASC ";
    }

    public Long getIgracID() {
        return igracID;
    }

    public void setIgracID(Long igracID) {
        this.igracID = igracID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

}
