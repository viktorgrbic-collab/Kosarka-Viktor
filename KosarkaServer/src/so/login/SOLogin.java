/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Statisticar;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Viktor 
 */
public class SOLogin extends AbstractSO {

    Statisticar ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Statisticar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Statisticar!");
        }

        Statisticar s = (Statisticar) ado;
        
    
            
       

        for (Statisticar statisticar : ServerController.getInstance().getUlogovaniStatisticari()) {
            if (statisticar.getUsername().equals(s.getUsername())) {
                throw new Exception("Ovaj statisticar je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Statisticar s = (Statisticar) ado;

        ArrayList<Statisticar> statisticari
                = (ArrayList<Statisticar>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Statisticar statisticar : statisticari) {
            if (statisticar.getUsername().equals(s.getUsername())
                    && statisticar.getPassword().equals(s.getPassword())) {
                ulogovani = statisticar;
                ServerController.getInstance().getUlogovaniStatisticari().add(statisticar);
                return;
            }
        }

        throw new Exception("Korisnicko ime  i lozinka nisu ispravni");
    }

    public Statisticar getUlogovani() {
        return ulogovani;
    }

}
