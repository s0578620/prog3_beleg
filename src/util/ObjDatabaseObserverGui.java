package util;

import GL.ObjDatabase;
import Gui.Controller;

public class ObjDatabaseObserverGui implements Observer{

    private ObjDatabase oDB;
    private Controller controller;



    public ObjDatabaseObserverGui(ObjDatabase oDB, Controller controller){
        this.oDB = oDB;
        this.controller = controller;
    }

    @Override
    public void update() {
        controller.updateObsList(oDB.getHerstellerList(),oDB.getObjList(),oDB.getAllergenList());
    }
}
