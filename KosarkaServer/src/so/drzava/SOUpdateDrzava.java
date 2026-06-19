/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.drzava;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Drzava;


import so.AbstractSO;

/**
 *
 * @author Viktor 
 */
public class SOUpdateDrzava extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
         if (!(ado instanceof Drzava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Drzava!");
        }

       Drzava novaDrzava = (Drzava) ado;

        if (novaDrzava.getNaziv().isEmpty()) {
            throw new Exception("Drzava mora imati naziv!");
        }


    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
