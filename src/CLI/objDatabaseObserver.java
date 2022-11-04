package CLI;

import GL.objDatabase;

import java.util.Observable;
import java.util.Observer;

public class objDatabaseObserver implements Observer {

    private objDatabase oDB;

    public objDatabaseObserver(objDatabase oDB){
        this.oDB = oDB;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("90% capacity reached");
    }
}
