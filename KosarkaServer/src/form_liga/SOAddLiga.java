/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form_liga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Liga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Viktor 
 */
public class SOAddLiga extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Liga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Liga!");
        }

        Liga novaLiga = (Liga) ado;
//kolekcija  objekata nepoznatog tipa wildcard
        ArrayList<Liga> lige = (ArrayList<Liga>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Liga ligaIzBaze : lige) {
            if (ligaIzBaze.getNaziv().equals(novaLiga.getNaziv())
                    && ligaIzBaze.getDrzava().getNaziv().equals(novaLiga.getDrzava().getNaziv())) {
                throw new Exception("Vec postoji ta liga u toj drzavi!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
