/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form_igrac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Igrac;

import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Viktor 
 */
public class SOGetAllIgrac extends AbstractSO {

    private ArrayList<Igrac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Igrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Igrac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaIgraca = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Igrac>) (ArrayList<?>) listaIgraca;
    }

    public ArrayList<Igrac> getLista() {
        return lista;
    }

}
