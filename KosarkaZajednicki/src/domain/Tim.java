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
public class Tim extends AbstractDomainObject {

    private Long timID;
    private String naziv;
    private int brojIgraca;
    private int brojUtakmica;
    private int brojPobeda;
    private Liga liga;
    private Statisticar statisticar;
    private ArrayList<StavkaTima> stavkeTima;

    public Tim(Long timID, String naziv, int brojIgraca, int brojUtakmica, int brojPobeda, Liga liga, Statisticar statisticar, ArrayList<StavkaTima> stavkeTima) {
        this.timID = timID;
        this.naziv = naziv;
        this.brojIgraca = brojIgraca;
        this.brojUtakmica = brojUtakmica;
        this.brojPobeda = brojPobeda;
        this.liga = liga;
        this.statisticar = statisticar;
        this.stavkeTima = stavkeTima;
    }

    public Tim() {
    }

    @Override
    public String nazivTabele() {
        return " Tim ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN LIGA L ON (L.LIGAID = T.LIGAID)\n"
                + "JOIN DRZAVA D ON (D.DRZAVAID = L.DRZAVAID)\n"
                + "JOIN STATISTICAR S ON (S.STATISTICARID = T.STATISTICARID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Statisticar s = new Statisticar(rs.getLong("statisticarID"),
                    rs.getString("s.Ime"), rs.getString("s.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Drzava d = new Drzava(rs.getLong("drzavaID"),
                    rs.getString("d.naziv"));

            Liga l = new Liga(rs.getLong("ligaID"), rs.getString("l.naziv"), d);

            Tim t = new Tim(rs.getLong("timID"), rs.getString("naziv"), rs.getInt("brojIgraca"),
                    rs.getInt("brojUtakmica"), rs.getInt("brojPobeda"), l, s,
                    new ArrayList<>());

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, brojIgraca, brojUtakmica, brojPobeda, ligaID, statisticarID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', " + brojIgraca + ", "
                + " " + brojUtakmica + ", " + brojPobeda + ", " + liga.getLigaID()
                + ", " + statisticar.getStatisticarID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', brojIgraca = " + brojIgraca + ", "
                + "brojUtakmica = " + brojUtakmica + ", brojPobeda = " + brojPobeda + ", "
                + "ligaID = " + liga.getLigaID();
    }

    @Override
    public String uslov() {
        return " timID = " + timID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(T.NAZIV) LIKE '%" + naziv + "%' "
                + "AND LOWER(S.IME) LIKE '%" + statisticar.getIme() + "%' "
                + "AND LOWER(S.PREZIME) LIKE '%" + statisticar.getPrezime() + "%' "
                + "AND LOWER(L.NAZIV) LIKE '%" + liga.getNaziv() + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY TIMID ASC ";
    }

    public Long getTimID() {
        return timID;
    }

    public void setTimID(Long timID) {
        this.timID = timID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojIgraca() {
        return brojIgraca;
    }

    public void setBrojIgraca(int brojIgraca) {
        this.brojIgraca = brojIgraca;
    }

    public int getBrojUtakmica() {
        return brojUtakmica;
    }

    public void setBrojUtakmica(int brojUtakmica) {
        this.brojUtakmica = brojUtakmica;
    }

    public int getBrojPobeda() {
        return brojPobeda;
    }

    public void setBrojPobeda(int brojPobeda) {
        this.brojPobeda = brojPobeda;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public Statisticar getStatisticar() {
        return statisticar;
    }

    public void setStatisticar(Statisticar statisticar) {
        this.statisticar = statisticar;
    }

    public ArrayList<StavkaTima> getStavkeTima() {
        return stavkeTima;
    }

    public void setStavkeTima(ArrayList<StavkaTima> stavkeTima) {
        this.stavkeTima = stavkeTima;
    }

}
