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
 * @author  Viktor
 */
public class SOGetAllLiga extends AbstractSO {

    private ArrayList<Liga> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Liga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Liga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaLiga = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Liga>) (ArrayList<?>) listaLiga;
    }

    public ArrayList<Liga> getLista() {
        return lista;
    }

}
