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
public class Drzava extends AbstractDomainObject {
    
    private Long drzavaID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Drzava(Long drzavaID, String naziv) {
        this.drzavaID = drzavaID;
        this.naziv = naziv;
    }

    public Drzava() {
    }
    
    @Override
    public String nazivTabele() {
        return " drzava ";
    }

    @Override
    public String alijas() {
        return " d ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Drzava d = new Drzava(rs.getLong("drzavaID"),
                    rs.getString("d.naziv"));

            lista.add(d);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "' ";
    }
    
    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "' ";
    }
    
    @Override
    public String uslov() {
        return " drzavaID = " + drzavaID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY DRZAVAID ASC ";
    }

    public Long getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(Long drzavaID) {
        this.drzavaID = drzavaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
