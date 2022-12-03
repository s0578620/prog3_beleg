package util;

import GL.ObjDatabase;
import Gui.Controller;


import java.util.Observable;
import java.util.Observer;

public class ObjDatabaseObserverGui implements Observer {

    private ObjDatabase oDB;
    private Controller controller;



    public ObjDatabaseObserverGui(ObjDatabase oDB, Controller controller){
        this.oDB = oDB;
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        controller.updateObsList(oDB.getHerstellerList(),oDB.getObjList(),oDB.getAllergenList());
    }
}
