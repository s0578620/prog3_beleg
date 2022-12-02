package CLI;

import Events.*;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.Scanner;

public class console {
    private EventHandler handler;
    private char mode;
    public console(EventHandler handler){
        this.handler = handler;
    }

    public void run(){
        final String topic = "Prog 3 Beleg - Kuchenautomat - Henrik Jeschkowski 578620\n" +
                                "********************************************************";
        final String help = "*Help Menu* \n :c - Create mode\n :d - Delete mode\n :r - Show mode";
        try (Scanner s = new Scanner(System.in)){
            do{
                write(topic);
                String input = s.nextLine();
                if(input.startsWith(":")){
                    switch (input){
                        case ":c": this.mode = 'c'; // create mode
                            write("entered create mode");
                            break;
                        case ":d": this.mode = 'd'; // delete mode
                            write("entered delete mode");
                            break;
                        case ":r": this.mode = 'r'; // show mode
                            write("entered show mode");
                            break;
                        case ":u":
                            this.mode = 'u';        // update mode
                            write("entered update mode");
                            break;
                        default:
                            write(help);
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

    public void execController(String input){
        if(input.startsWith(":")) {
            switch (input) {
                case ":c":
                    this.mode = 'c';
                    break;
                case ":d":
                    this.mode = 'd';
                    break;
                case ":u":
                    this.mode = 'u';
                    break;
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
            case 'c':
                return createEO(input);
            case 'd':
                return deleteEO(input);
            case 'r':
                return showEO(input);
            case 'u':
                return updateEO(input);
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
                    return new ShowKuchenEvent(input,input);
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
