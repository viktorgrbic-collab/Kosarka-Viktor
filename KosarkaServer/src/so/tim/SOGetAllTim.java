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
import so.AbstractSO;

/**
 *
 * @author Viktor
 */
public class SOGetAllTim extends AbstractSO {

    private ArrayList<Tim> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Tim)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Tim!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        ArrayList<AbstractDomainObject> timovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Tim>) (ArrayList<?>) timovi;

       
        for (Tim trenutniTim : lista) {

           
            StavkaTima stavkaTima = new StavkaTima();
            stavkaTima.setTim(trenutniTim);

           
            ArrayList<StavkaTima> stavkeTrenutnogTima
                    = (ArrayList<StavkaTima>) (ArrayList<?>) DBBroker.getInstance().select(stavkaTima);

          
            trenutniTim.setStavkeTima(stavkeTrenutnogTima);
        }
    }

    public ArrayList<Tim> getLista() {
        return lista;
    }

}
