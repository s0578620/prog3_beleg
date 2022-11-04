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
        final String help = "*Help Menu* \n :c - Create mode\n :d - Delete mode\n :r - Remove mode";
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
//                        case ":u": this.mode = 'u'; // update mode
//                            write("entered update mode");
//                            break;
                        default:
                            write(help);
                            break;
                    }
                } else {
                    try {
                        EventObject event = this.parseToEvent(input);
                        if (event != null){
                            this.handler.handle(event);
                            write("after handler action");  // TODO add -> better feedback
                        }
                    } catch (IllegalArgumentException e) {
                        write(e.toString());
                    }
                }
            } while(true);
        }
    }

    private EventObject parseToEvent(String input){
        switch ( this.mode ) {
            case 'c':
                return createEO(input);
            case 'd':
                return deleteEO(input);
            case 'r':
                return showEO(input);
//            case 'u':                             // TODO add -> case updateEO
//                return updateEO(input);
            default:
                return null;
        }
    }



    public EventObject createEO(String input ){
        String[] inputList = input.split( " " );
        switch (inputList.length){
            case 1:
                return new AddHerstellerEvent(input,input);
            case 7:
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
                return new AddKuchenEvent(inputList[0],inputList[0],inputList[1],preis,Integer.parseInt(inputList[3]),Integer.parseInt(inputList[4]),list, inputList[6]);
//            case 8:                   // TODO implement Torte (add -> addKuchenEvent)
//                return null;
            default:
                write("Invalid Command");
                return null;
        }
    }

    public EventObject deleteEO(String input ) {
        String[] inputList = input.split(" ");          // TODO add -> RemoveHerstellerEvent && activate lever
//        if(inputList[0].matches(".*[a-z].*")){
//            return new removeHerstellerEvent();
//        }else{
            return new removeKuchenEvent(input,Integer.parseInt(input));
//      }
    }

    public EventObject showEO(String input){
        String[] inputList = input.split( " " );

        switch (inputList[0]){
            case "hersteller":
                return new ShowHerstellerEvent(input,input);
            default:
                write("Invalid Command");
                return null;
        }
    }

    public void write(String text){
        System.out.println(text);
    }
}
