package Events;

import GL.ObjDatabase;
import java.util.EventObject;
import java.util.List;

public class ODBEventListener implements EventListener{

    private ObjDatabase oDB;

    public ODBEventListener(ObjDatabase oDB){
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
                this.oDB.removeObj(((RemoveKuchenEvent)event).getFachnummer());
                break;
            case "show Hersteller":
                List<String> showlist = this.oDB.showAllCakesSortedByHersteller();
                showlist.forEach(System.out::println);      // TODO add -> better show (write) method
                break;
            case "show Kuchen":
                List<String> showlist1 = this.oDB.showKuchenAll();
                showlist1.forEach(System.out::println);
                break;
            case "show KuchenTyp":
                List<String> showlist2 = this.oDB.showKuchen(((ShowKuchenTypEvent)event).getTyp());
                showlist2.forEach(System.out::println);
                break;
        }
    }
}
