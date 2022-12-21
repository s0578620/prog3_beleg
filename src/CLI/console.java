package CLI;

import Routing.Events.*;
import Routing.Handler.Handler;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.Scanner;

public class console {
    private Handler handler;
    public console(Handler handler){
        this.handler = handler;
    }
    private Mode mode;
    public enum Mode {
        CREATE, DELETE, SHOW, UPDATE, PERSISTENCE;
    }

    public void run(){
        final String topic = "Prog 3 Beleg - Kuchenautomat - Henrik Jeschkowski 578620\n" +
                                "********************************************************";
        try (Scanner s = new Scanner(System.in)){
            do{
                write(topic);
                String input = s.nextLine();
                if(input.startsWith(":") || input.startsWith("?") || input.startsWith("help")){
                    switch (input){
                        case ":c": this.mode = Mode.CREATE;
                            write("*** You are now in: create mode ***\n");
                            break;
                        case ":d": this.mode = Mode.DELETE;
                            write("*** You are now in: delete mode ***\n");
                            break;
                        case ":r": this.mode = Mode.SHOW;
                            write("*** You are now in: show mode ***\n");
                            break;
                        case ":u": this.mode = Mode.UPDATE;
                            write("*** You are now in: update mode ***\n");
                            break;
                        case ":p": this.mode = Mode.PERSISTENCE;
                            write("*** You are now in: persistence mode ***\n");
                        default:
                            write(getCorrectHelpMenu());
                            break;
                    }
                } else {
                    try {
                        EventObject event = this.getCorrectEO(input);
                        if (event != null){
                            this.handler.handle(event);
                        }
                    } catch (IllegalArgumentException e) {
                        write(e.toString());
                    }
                }
            } while(true);
        }
    }
    public String getCorrectHelpMenu(){
        final String help = "*** Help Menu *** \n :c - Create mode\n :d - Delete mode\n :r - Show mode\n :p - Persistence mode\n";
        final String helpCREATE = "*** Help Menu *** \n" +
                " [Herstellername] - add Hersteller\n " +
                "[Kuchen-Typ] [Herstellername] [Preis] [Nährwert] [Haltbarkeit] [kommaseparierte Allergene, einzelnes Komma für keine] [[Obstsorte]] [[Kremsorte]] - add Kuchen\n";
        final String helpDELETE = "*** Help Menu *** \n [Herstellername] - delete Hersteller\n [Fachnummer] - delete cake\n";
        final String helpSHOW = "*** Help Menu *** \n hersteller - show Hersteller (with Cake count)\n kuchen [[Typ]] - show Kuchen (-typ)\n allergene [i/e] - show Allergene (i = Inclusive / e = Exclusive)\n";
        final String helpUPDATE = "*** Help Menu *** \n [Fachnummer] - update Inspektionsdatum\n";
        final String helpPERSISTENCE = "*** Help Menu *** \n safe [jos/jbp] - safe via jos/jbp\n load [jos/jbp] - load via jos/jbp\n";
        switch (mode){
            case CREATE: return helpCREATE;
            case DELETE: return helpDELETE;
            case SHOW: return helpSHOW;
            case UPDATE: return helpUPDATE;
            case PERSISTENCE: return helpPERSISTENCE;
            default: return help;
        }
    }

    public void execController(String input){
        if(input.startsWith(":")) {
            switch (input) {
                case ":c":
                    this.mode = Mode.CREATE;
                    break;
                case ":d":
                    this.mode = Mode.DELETE;
                    break;
                case ":u":
                    this.mode = Mode.UPDATE;
                    break;
                case ":p":
                    this.mode = Mode.PERSISTENCE;
                default:
                    break;
            }
        }else {
            try {
                EventObject event = this.getCorrectEO(input);
                if (event != null){
                    this.handler.handle(event);
                }
            } catch (IllegalArgumentException e) {
                write(e.toString());
            }
        }
    }

    private EventObject getCorrectEO(String input){
        switch ( this.mode ) {
            case CREATE:
                return createEO(input);
            case DELETE:
                return deleteEO(input);
            case SHOW:
                return showEO(input);
            case UPDATE:
                return updateEO(input);
            case PERSISTENCE:
                return persistenzEO(input);
            default:
                return null;
        }
    }

    private EventObject persistenzEO(String input) {
        String[] inputList = input.split(" ");
        switch (inputList[0]) {
            case "save":
                return new SaveFileEvent(this, inputList[1]);
            case "load":
                return new LoadFileEvent(this, inputList[1]);
            default:
                return null;
        }
    }

    public EventObject createEO(String input ){
        String[] inputList = input.split( " " );
        switch (inputList.length){
            case 1:
                return new AddHerstellerEvent(input,input);
            case 7,8:
                LinkedList<Allergen> list = new LinkedList<>();
                String[] allergenList = inputList[5].split(",");
                for(String s : allergenList){
                    for(Allergen a : Allergen.values()){
                        if(a.name().equals(s)){
                            list.add(a);
                        }
                    }
                }
                BigDecimal preis = BigDecimal.valueOf(Double.parseDouble(inputList[2].replace(",",".")));
                if(inputList.length > 7){
                    return new AddTorteEvent(inputList[0],inputList[0],inputList[1],preis,Integer.parseInt(inputList[3]),Integer.parseInt(inputList[4]),list, inputList[6],inputList[7]);
                }else {
                    return new AddKuchenEvent(inputList[0],inputList[0],inputList[1],preis,Integer.parseInt(inputList[3]),Integer.parseInt(inputList[4]),list, inputList[6]);
                }
            default:
                write("Invalid Command");
                return null;
        }
    }

    public EventObject deleteEO(String input ) {
        String[] inputList = input.split(" ");
        if(inputList[0].matches(".*[a-z].*")){
            return new RemoveHerstellerEvent(input,input);
        }else{
            return new RemoveKuchenEvent(input,Integer.parseInt(input));
      }
    }

    public EventObject showEO(String input){
        String[] inputList = input.split( " " );

        switch (inputList[0]){
            case "hersteller":
                return new ShowHerstellerEvent(input,input);
            case "kuchen":
                if(inputList.length > 1){
                    return new ShowKuchenTypEvent(inputList[0],inputList[0],inputList[1]);
                }else {
                    return new ShowKuchenEvent(input);
                }
            case "allergene":
                switch (inputList[1]){
                    case "i":
                        return new ShowAllergeneEventInclusive(inputList[0],inputList[0]);
                    case "e":
                        return new ShowAllergeneEventExclusive(inputList[0],inputList[0]);
                }
            default:
                write("Invalid Command");
                return null;
        }
    }

    public EventObject updateEO(String input){
        return new UpdateInspEvent(input,Integer.parseInt(input));
    }

    public void write(String text){
        System.out.println(text);
    }
}
