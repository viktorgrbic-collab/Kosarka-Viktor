/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form_igrac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Igrac;
import so.AbstractSO;

/**
 *
 * @author Viktor 
 */
public class SOAddIgrac extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Igrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Igrac!");
        }

        Igrac noviIgrac = (Igrac) ado;

        if (noviIgrac.getVisina() < 150 || noviIgrac.getVisina() > 250) {
            throw new Exception("Visina igraca mora biti izmedju 150cm i 250cm!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
