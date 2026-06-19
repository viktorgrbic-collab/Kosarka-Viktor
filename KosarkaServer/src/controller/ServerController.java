/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Drzava;
import domain.Igrac;
import domain.Liga;
import domain.Statisticar;
import domain.Tim;
import form_igrac.SOAddIgrac;
import form_igrac.SODeleteIgrac;
import form_igrac.SOGetAllIgrac;
import form_igrac.SOUpdateIgrac;
import form_liga.SOAddLiga;
import form_liga.SODeleteLiga;
import form_liga.SOGetAllLiga;
import form_liga.SOUpdateLiga;
import java.util.ArrayList;
import so.drzava.SOAddDrzava;
import so.drzava.SODeleteDrzava;
import so.drzava.SOGetAllDrzava;
import so.drzava.SOUpdateDrzava;
import so.login.SOLogin;
import so.tim.SOAddTim;
import so.tim.SODeleteTim;
import so.tim.SOGetAllTim;
import so.tim.SOUpdateTim;

/**
 *
 * @author  Viktor
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Statisticar> ulogovaniStatisticari = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Statisticar> getUlogovaniStatisticari() {
        return ulogovaniStatisticari;
    }

    public void setUlogovaniStatisticari(ArrayList<Statisticar> ulogovaniStatisticari) {
        this.ulogovaniStatisticari = ulogovaniStatisticari;
    }

    public Statisticar login(Statisticar statisticar) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(statisticar);
        return so.getUlogovani();
    }

    public void addIgrac(Igrac igrac) throws Exception {
        (new SOAddIgrac()).templateExecute(igrac);
    }

    public void addLiga(Liga liga) throws Exception {
        (new SOAddLiga()).templateExecute(liga);
    }
    
    public void addDrzava(Drzava drzava) throws Exception {
        (new SOAddDrzava()).templateExecute(drzava);
    }

    public void addTim(Tim tim) throws Exception {
        (new SOAddTim()).templateExecute(tim);
    }

    public void deleteIgrac(Igrac igrac) throws Exception {
        (new SODeleteIgrac()).templateExecute(igrac);
    }

    public void deleteLiga(Liga liga) throws Exception {
        (new SODeleteLiga()).templateExecute(liga);
    }
    public void deleteDrzava(Drzava drzava) throws Exception {
        (new SODeleteDrzava()).templateExecute(drzava);
    }

    public void deleteTim(Tim tim) throws Exception {
        (new SODeleteTim()).templateExecute(tim);
    }

    public void updateIgrac(Igrac igrac) throws Exception {
        (new SOUpdateIgrac()).templateExecute(igrac);
    }

    public void updateLiga(Liga liga) throws Exception {
        (new SOUpdateLiga()).templateExecute(liga);
    }
    public void updateDrzava(Drzava drzava) throws Exception {
        (new SOUpdateDrzava()).templateExecute(drzava);
    }

    public void updateTim(Tim tim) throws Exception {
        (new SOUpdateTim()).templateExecute(tim);
    }

    public ArrayList<Igrac> getAllIgrac(Igrac igrac) throws Exception {
        SOGetAllIgrac so = new SOGetAllIgrac();
        so.templateExecute(igrac);
        return so.getLista();
    }

    public ArrayList<Liga> getAllLiga(Liga liga) throws Exception {
        SOGetAllLiga so = new SOGetAllLiga();
        so.templateExecute(liga);
        return so.getLista();
    }

    public ArrayList<Tim> getAllTim(Tim tim) throws Exception {
        SOGetAllTim so = new SOGetAllTim();
        so.templateExecute(tim);
        return so.getLista();
    }

    public ArrayList<Drzava> getAllDrzava(Drzava drzava) throws Exception {
        SOGetAllDrzava so = new SOGetAllDrzava();
        so.templateExecute(drzava);
        return so.getLista();
    }

    public void logout(Statisticar ulogovani) {
        ulogovaniStatisticari.remove(ulogovani);
    }

}
