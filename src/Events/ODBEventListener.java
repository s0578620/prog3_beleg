package Events;

import GL.objDatabase;
import java.util.EventObject;
import java.util.List;

public class ODBEventListener implements EventListener{

    private objDatabase oDB;

    public ODBEventListener(objDatabase oDB){
        this.oDB = oDB;
    }

    @Override
    public void onEvent(EventObject event) {
        switch (event.toString()){
            case "add Hersteller":
                this.oDB.addHersteller(((AddHerstellerEvent)event).getHersteller());
                break;
            case "add Kuchen":
                this.oDB.addObj(((AddKuchenEvent)event).getKuchentyp(),
                        ((AddKuchenEvent)event).getHersteller(),
                        ((AddKuchenEvent)event).getPreis(),
                        ((AddKuchenEvent)event).getNaehrwert(),
                        ((AddKuchenEvent)event).getHaltbarkeit(),
                        ((AddKuchenEvent)event).getAllergene(),
                        ((AddKuchenEvent)event).getTopping());
                break;
            case "add Torte":
                this.oDB.addObj(((AddTorteEvent)event).getKuchentyp(),
                        ((AddTorteEvent)event).getHersteller(),
                        ((AddTorteEvent)event).getPreis(),
                        ((AddTorteEvent)event).getNaehrwert(),
                        ((AddTorteEvent)event).getHaltbarkeit(),
                        ((AddTorteEvent)event).getAllergene(),
                        ((AddTorteEvent)event).getKremsorte(),
                        ((AddTorteEvent)event).getObstsorte());
                break;
            case "remove Kuchen":
                this.oDB.removeObj(((removeKuchenEvent)event).getFachnummer());
                break;
            case "show Hersteller":
                List<String> showlist = this.oDB.showAllCakesSortedByHersteller();
                showlist.forEach(System.out::println);      // TODO add -> better show (write) method
                break;
        }
    }
}
