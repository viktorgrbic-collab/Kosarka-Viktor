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
 * @author  Viktor
 */
public class StavkaTima extends AbstractDomainObject {

    private Tim tim;
    private int rb;
    private int brojNaDresu;
    private String pozicija;
    private Igrac igrac;

    public StavkaTima(Tim tim, int rb, int brojNaDresu, String pozicija, Igrac igrac) {
        this.tim = tim;
        this.rb = rb;
        this.brojNaDresu = brojNaDresu;
        this.pozicija = pozicija;
        this.igrac = igrac;
    }

    public StavkaTima() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaTima ";
    }

    @Override
    public String alijas() {
        return " st ";
    }

    @Override
    public String join() {
        return " JOIN TIM T ON (T.TIMID = ST.TIMID)\n"
                + "JOIN LIGA L ON (L.LIGAID = T.LIGAID)\n"
                + "JOIN DRZAVA D ON (D.DRZAVAID = L.DRZAVAID)\n"
                + "JOIN STATISTICAR S ON (S.STATISTICARID = T.STATISTICARID)\n"
                + "JOIN IGRAC I ON (I.IGRACID = ST.IGRACID) ";
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
                    rs.getInt("brojUtakmica"), rs.getInt("brojPobeda"), l, s, null);

            Igrac i = new Igrac(rs.getLong("IgracID"),
                    rs.getString("i.Ime"), rs.getString("i.Prezime"),
                    rs.getDouble("visina"));

            StavkaTima st = new StavkaTima(t, rs.getInt("rb"),
                    rs.getInt("brojNaDresu"),
                    rs.getString("pozicija"), i);

            lista.add(st);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (timID, rb, brojNaDresu, pozicija, igracID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + tim.getTimID() + ", " + rb + ", "
                + brojNaDresu + ", '" + pozicija + "', " + igrac.getIgracID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " brojNaDresu = " + brojNaDresu + ", "
                + "pozicija = '" + pozicija + "', "
                + "igracID = " + igrac.getIgracID();
    }

    @Override
    public String uslov() {
        return " timID = " + tim.getTimID() + " AND rb = " + rb;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE T.TIMID = " + tim.getTimID() + "  ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY RB ASC ";
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public int getBrojNaDresu() {
        return brojNaDresu;
    }

    public void setBrojNaDresu(int brojNaDresu) {
        this.brojNaDresu = brojNaDresu;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

}
