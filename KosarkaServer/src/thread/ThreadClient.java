/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Drzava;
import domain.Igrac;
import domain.Liga;
import domain.Statisticar;
import domain.Tim;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Viktor 
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                // primiZahtev()
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                // switch case
                Response response = handleRequest(request);
                // posaljiOdgovor()
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_IGRAC:
                    ServerController.getInstance().addIgrac((Igrac) request.getData());
                    break;
                case Operation.ADD_LIGA:
                    ServerController.getInstance().addLiga((Liga) request.getData());
                    break;
                    case Operation.ADD_DRZAVA:
                    ServerController.getInstance().addDrzava((Drzava) request.getData());
                    break;
                case Operation.ADD_TIM:
                    ServerController.getInstance().addTim((Tim) request.getData());
                    break;
                case Operation.DELETE_IGRAC:
                    ServerController.getInstance().deleteIgrac((Igrac) request.getData());
                    break;
                case Operation.DELETE_LIGA:
                    ServerController.getInstance().deleteLiga((Liga) request.getData());
                    break;
                    case Operation.DELETE_DRZAVA:
                    ServerController.getInstance().deleteDrzava((Drzava) request.getData());
                    break;
                case Operation.DELETE_TIM:
                    ServerController.getInstance().deleteTim((Tim) request.getData());
                    break;
                case Operation.UPDATE_IGRAC:
                    ServerController.getInstance().updateIgrac((Igrac) request.getData());
                    break;
                case Operation.UPDATE_TIM:
                    ServerController.getInstance().updateTim((Tim) request.getData());
                    break;
                case Operation.UPDATE_LIGA:
                    ServerController.getInstance().updateLiga((Liga) request.getData());
                    break;
                    case Operation.UPDATE_DRZAVA:
                    ServerController.getInstance().updateDrzava((Drzava) request.getData());
                    break;
                case Operation.GET_ALL_DRZAVA:
                    response.setData(ServerController.getInstance().getAllDrzava((Drzava) request.getData()));
                    break;
                case Operation.GET_ALL_IGRAC:
                    response.setData(ServerController.getInstance().getAllIgrac((Igrac) request.getData()));
                    break;
                case Operation.GET_ALL_LIGA:
                    response.setData(ServerController.getInstance().getAllLiga((Liga) request.getData()));
                    break;
                    
                case Operation.GET_ALL_TIM:
                    response.setData(ServerController.getInstance().getAllTim((Tim) request.getData()));
                    break;
                case Operation.LOGIN:
                    Statisticar statisticar = (Statisticar) request.getData();
                    Statisticar stat = ServerController.getInstance().login(statisticar);
                    response.setData(stat);
                    break;
                case Operation.LOGOUT:
                    Statisticar ulogovani = (Statisticar) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
