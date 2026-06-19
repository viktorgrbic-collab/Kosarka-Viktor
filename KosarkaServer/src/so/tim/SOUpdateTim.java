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
import java.util.ArrayList;
import java.util.HashMap;
import so.AbstractSO;

/**
 *
 * @author Viktor
 */
public class SOUpdateTim extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Tim)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Tim!");
        }

        Tim t = (Tim) ado;

        if (t.getBrojPobeda() > t.getBrojUtakmica()) {
            throw new Exception("Broj pobeda mora biti manji ili jednak broju utakmica!!");
        }

        if (t.getStavkeTima().size() < 3 || t.getStavkeTima().size() > 20) {
            throw new Exception("Tim mora imati izmedju 3 i 20 igraca!");
        }

        ArrayList<Tim> timovi = (ArrayList<Tim>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Tim tim : timovi) {
            if (!tim.getTimID().equals(t.getTimID())) {
                if (tim.getNaziv().equals(t.getNaziv())) {
                    throw new Exception("Tim sa tim nazivom vec postoji!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Tim tim = (Tim) ado;

     
        DBBroker.getInstance().update(tim);

       
        ArrayList<StavkaTima> stareStavke
                = (ArrayList<StavkaTima>) (ArrayList<?>) DBBroker.getInstance()
                        .select(new StavkaTima(tim, 0, 0, null, null));

        HashMap<Integer, StavkaTima> mapaStarih = new HashMap<>();
        for (StavkaTima st : stareStavke) {
            mapaStarih.put(st.getRb(), st);
        }

    
        HashMap<Integer, StavkaTima> mapaNovih = new HashMap<>();
        for (StavkaTima nova : tim.getStavkeTima()) {
            mapaNovih.put(nova.getRb(), nova);
        }

      
        for (StavkaTima stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) {
                DBBroker.getInstance().delete(stara);
            }
        }

        
        for (StavkaTima nova : tim.getStavkeTima()) {
            if (mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().update(nova);
            }
        }

        
        for (StavkaTima nova : tim.getStavkeTima()) {
            if (!mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().insert(nova);
            }
        }

    }

}
