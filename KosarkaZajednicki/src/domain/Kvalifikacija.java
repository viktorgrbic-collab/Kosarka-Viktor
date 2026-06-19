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
public class Kvalifikacija extends AbstractDomainObject {
    
    private Long kvalifikacijaID;
    private String naziv;
    @Override
    public String toString() {
        return naziv;
    }

    public Kvalifikacija(Long kvalifikacijaID, String naziv) {
        this.kvalifikacijaID = kvalifikacijaID;
        this.naziv = naziv;
    }

    public Kvalifikacija() {
    }
    
    @Override
    public String nazivTabele() {
        return " Kvalifikacija ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Kvalifikacija k = new Kvalifikacija(rs.getLong("KvalifikacijaID"),
                    rs.getString("k.naziv"));

            lista.add(k);
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
        return " kvalifikacijaID = " + kvalifikacijaID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY KVALIFIKACIJAID ASC ";
    }

    public Long getKvalifikacijaID() {
        return kvalifikacijaID;
    }

    public void setKvalifikacijaID(Long kvalifikacijaID) {
        this.kvalifikacijaID = kvalifikacijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
