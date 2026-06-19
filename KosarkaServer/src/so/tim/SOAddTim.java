/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tim;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaTima;
import domain.Tim;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Viktor
 */
public class SOAddTim extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Tim)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Tim!");
        }

        Tim t = (Tim) ado;

        if (t.getStavkeTima().size() < 3 || t.getStavkeTima().size() > 20) {
            throw new Exception("Tim mora imati izmedju 3 i 20 igraca!");
        }

        ArrayList<Tim> timovi = (ArrayList<Tim>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Tim tim : timovi) {
            if (tim.getNaziv().equals(t.getNaziv())) {
                throw new Exception("Tim sa tim nazivom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
      
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

       
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long noviTimID = tableKeys.getLong(1);

       
        Tim noviTim = (Tim) ado;
        noviTim.setTimID(noviTimID);


        for (StavkaTima stavkaTima : noviTim.getStavkeTima()) {
            stavkaTima.setTim(noviTim);//pre je bilo null 
            DBBroker.getInstance().insert(stavkaTima);
        }

    }

}
