/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Viktor 
 */
public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_IGRAC = 2;
    public static final int DELETE_IGRAC = 3;
    public static final int UPDATE_IGRAC = 4;
    public static final int GET_ALL_IGRAC = 5;

    public static final int ADD_LIGA = 6;
    public static final int DELETE_LIGA = 7;
    public static final int UPDATE_LIGA = 8;
    public static final int GET_ALL_LIGA = 9;

    public static final int ADD_TIM = 10;
    public static final int DELETE_TIM = 11;
    public static final int UPDATE_TIM = 12;
    public static final int GET_ALL_TIM = 13;

    public static final int ADD_DRZAVA = 14;
    public static final int DELETE_DRZAVA = 15;
   public static final int UPDATE_DRZAVA = 16;
    public static final int GET_ALL_DRZAVA = 17;

}
