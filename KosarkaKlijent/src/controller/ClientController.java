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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author  Viktor
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Statisticar login(Statisticar statisticar) throws Exception {
        return (Statisticar) sendRequest(Operation.LOGIN, statisticar);
    }

    public void logout(Statisticar ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addIgrac(Igrac igrac) throws Exception {
        sendRequest(Operation.ADD_IGRAC, igrac);
    }//

    public void addLiga(Liga liga) throws Exception {
        sendRequest(Operation.ADD_LIGA, liga);
    }//
    public void addDrzava(Drzava drzava) throws Exception {
        sendRequest(Operation.ADD_DRZAVA, drzava);
    }

    public void addTim(Tim tim) throws Exception {
        sendRequest(Operation.ADD_TIM, tim);
    }

    public void deleteIgrac(Igrac igrac) throws Exception {
        sendRequest(Operation.DELETE_IGRAC, igrac);
    }

    public void deleteLiga(Liga liga) throws Exception {
        sendRequest(Operation.DELETE_LIGA, liga);
    }
    public void deleteDrzava(Drzava drzava) throws Exception {
        sendRequest(Operation.DELETE_DRZAVA, drzava);
    }

    public void deleteTim(Tim tim) throws Exception {
        sendRequest(Operation.DELETE_TIM, tim);
    }

    public void updateIgrac(Igrac igrac) throws Exception {
        sendRequest(Operation.UPDATE_IGRAC, igrac);
    }

    public void updateLiga(Liga liga) throws Exception {
        sendRequest(Operation.UPDATE_LIGA, liga);
    }

    public void updateTim(Tim tim) throws Exception {
        sendRequest(Operation.UPDATE_TIM, tim);
    }
    public void updateDrzava(Drzava drzava) throws Exception {
        sendRequest(Operation.UPDATE_DRZAVA, drzava);
    }

    public ArrayList<Igrac> getAllIgrac(Igrac igrac) throws Exception {
        return (ArrayList<Igrac>) sendRequest(Operation.GET_ALL_IGRAC, igrac);
    }

    public ArrayList<Liga> getAllLiga(Liga liga) throws Exception {
        return (ArrayList<Liga>) sendRequest(Operation.GET_ALL_LIGA, liga);
    }

    public ArrayList<Tim> getAllTim(Tim tim) throws Exception {
        return (ArrayList<Tim>) sendRequest(Operation.GET_ALL_TIM, tim);
    }

    public ArrayList<Drzava> getAllDrzava(Drzava drzava) throws Exception {
        return (ArrayList<Drzava>) sendRequest(Operation.GET_ALL_DRZAVA, drzava);
    }

   public synchronized Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        // Posalji zahtev
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        // Primi odgovor
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

}
