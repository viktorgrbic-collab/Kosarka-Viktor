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
public class Liga extends AbstractDomainObject {

    private Long ligaID;
    private String naziv;
    private Drzava drzava;

    @Override
    public String toString() {
        return naziv + " (Drzava: " + drzava + ")";
    }

    public Liga(Long ligaID, String naziv, Drzava drzava) {
        this.ligaID = ligaID;
        this.naziv = naziv;
        this.drzava = drzava;
    }

    public Liga() {
    }

    @Override
    public String nazivTabele() {
        return " Liga ";
    }

    @Override
    public String alijas() {
        return " l ";
    }

    @Override
    public String join() {
        return " JOIN DRZAVA D ON (D.DRZAVAID = L.DRZAVAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Drzava d = new Drzava(rs.getLong("drzavaID"),
                    rs.getString("d.naziv"));

            Liga l = new Liga(rs.getLong("ligaID"), rs.getString("l.naziv"), d);

            lista.add(l);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, drzavaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', " + drzava.getDrzavaID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', drzavaID = " + drzava.getDrzavaID() + " ";
    }

    @Override
    public String uslov() {
        return " ligaID = " + ligaID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(L.NAZIV) LIKE '%" + naziv + "%' "
                + "AND LOWER(D.NAZIV) LIKE '%" + drzava.getNaziv() + "%'";
    }

    @Override
    public String orderBy() {
        return " ORDER BY LIGAID ASC ";
    }

    public Long getLigaID() {
        return ligaID;
    }

    public void setLigaID(Long ligaID) {
        this.ligaID = ligaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

}
