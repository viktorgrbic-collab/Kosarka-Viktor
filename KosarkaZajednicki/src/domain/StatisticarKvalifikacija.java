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
public class StatisticarKvalifikacija extends AbstractDomainObject {
    
    private Kvalifikacija kvalifikacija;
    private Statisticar statisticar;
    private String opis;
    
    public StatisticarKvalifikacija(Kvalifikacija kvalifikacija, Statisticar statisticar, String opis) {
        this.kvalifikacija = kvalifikacija;
        this.statisticar = statisticar;
        this.opis = opis;
    }
    
    public StatisticarKvalifikacija() {
    }
    
    @Override
    public String nazivTabele() {
        return " StatisticarKvalifikacija ";
    }
    
    @Override
    public String alijas() {
        return " sk ";
    }
    
    @Override
    public String join() {
        return " JOIN STATISTICAR S ON (S.STATISTICARID = SK.STATISTICARID)\n"
                + "JOIN KVALIFIKACIJA K ON (K.KVALIFIKACIJAID = SK.KVALIFIKACIJAID)";
    }
    
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while (rs.next()) {
            Statisticar s = new Statisticar(rs.getLong("statisticarID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Kvalifikacija k = new Kvalifikacija(rs.getLong("KvalifikacijaID"),
                    rs.getString("k.naziv"));
            
            StatisticarKvalifikacija sk = new StatisticarKvalifikacija(k, s, rs.getString("opis"));
            
            lista.add(sk);
        }
        
        rs.close();
        return lista;
    }
    
    @Override
    public String koloneZaInsert() {
        return " (KvalifikacijaID, statisticarID, opis) ";
    }
    
    @Override
    public String vrednostiZaInsert() {
        return " " + kvalifikacija.getKvalifikacijaID() + ", "
                + " " + statisticar.getStatisticarID() + ", "
                + "'" + opis + "' ";
    }
    
    @Override
    public String vrednostiZaUpdate() {
        return "";
    }
    
    @Override
    public String uslov() {
        return " statisticarID = " + statisticar.getStatisticarID();
    }
    
    @Override
    public String dodatniUslov() {
        return "";
    }

    @Override
    public String orderBy() {
        return "";
    }
    
    public Kvalifikacija getKvalifikacija() {
        return kvalifikacija;
    }
    
    public void setKvalifikacija(Kvalifikacija kvalifikacija) {
        this.kvalifikacija = kvalifikacija;
    }
    
    public Statisticar getStatisticar() {
        return statisticar;
    }
    
    public void setStatisticar(Statisticar statisticar) {
        this.statisticar = statisticar;
    }
    
    public String getOpis() {
        return opis;
    }
    
    public void setOpis(String opis) {
        this.opis = opis;
    }
    
}
