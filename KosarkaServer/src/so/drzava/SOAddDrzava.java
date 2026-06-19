/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.drzava;


import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Drzava;

import java.util.ArrayList;

import so.AbstractSO;

/**
 *
 * @author Viktor 
 */
public class SOAddDrzava extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Drzava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Drzava!");
        }

       Drzava novaDrzava = (Drzava) ado;

        ArrayList<Drzava> drzave = (ArrayList<Drzava>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Drzava drzava : drzave) {
            if (drzava.getNaziv().equals(novaDrzava.getNaziv())) {
                throw new Exception("Vec postoji ta drzava!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
