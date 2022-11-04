package GL;
import CLI.Observable;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
public class objDatabase implements Observable {

    public int getCapacityMax() {
        return capacityMax;
    }

    private int capacityMax = 10;
    private int capacityAct;
    private LinkedList<Hersteller> herstellerList = new LinkedList<>();
    private LinkedList<obj> objList = new LinkedList<>();
    private LinkedList<Allergen> allergenList = new LinkedList<>();
    private LinkedList<CLI.Observer> observerList = new LinkedList<>();

    public void attachObserver(CLI.Observer o) { this.observerList.add( o ); }
    public void detachObserver(CLI.Observer o) { this.observerList.remove( o ); }

    @Override
    public void notifyObservers(int capacity) {
        for (CLI.Observer o : observerList) {
            o.update(capacity);
        }
    }


    public boolean addHersteller(String hersteller){
        Hersteller a = new Hersteller(hersteller);
        if(herstellerList.stream().anyMatch(h -> h.getName().equals(hersteller))){
            return false;
        }else {
            herstellerList.add(a);
            return true;
        }
    }

    public boolean removeHersteller(String hersteller){
        Hersteller a = new Hersteller(hersteller);
        for(Hersteller h : herstellerList){
            if(h.getName().equals(a.getName())){
                for(obj o : objList){
                    if(o.getHersteller().getName().equals(h.getName())){
                        removeObj(o.getFachnummer());
                    }
                }
                herstellerList.remove(h);
                return true;
            }
        }
        return false;
    }

    public boolean addObj(String Kuchentyp, String hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Collection<Allergen> Allergene, String Topping) {
        if(capacityAct < capacityMax) {
            for (Hersteller value : herstellerList) {
                if (value.getName().equals(hersteller)) {
                    Hersteller her = new Hersteller(hersteller);
                    Date y = new Date();
                    if (Kuchentyp.equals("Kremkuchen")) {
                        obj a = new Kremkuchen(Kuchentyp, her, Preis, Naehrwert, Haltbarkeit, y, Allergene, Topping, y);
                        objList.add(setFachnummer(a));      // TODO add -> help method for all cakes
                        readInAllergens(Allergene);
                        capacityAct += 1;
                        notifyObservers(capacityAct);
                        return true;
                    }
                    if (Kuchentyp.equals("Obstkuchen")) {
                        obj a = new Obstkuchen(Kuchentyp, her, Preis, Naehrwert, Haltbarkeit, y, Allergene, Topping, y);
                        objList.add(setFachnummer(a));
                        readInAllergens(Allergene);
                        capacityAct += 1;
                        notifyObservers(capacityAct);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean addObj(String Kuchentyp, String hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Collection<Allergen> Allergene, String Kremsorte,String Obstsorte){
        if(capacityAct < capacityMax) {
            for (Hersteller value : herstellerList) {
                if (value.getName().equals(hersteller)) {
                    if (Kuchentyp.equals("Obsttorte")) {
                        Hersteller her = new Hersteller(hersteller);
                        Date y = new Date();
                        obj a = new Obsttorte(Kuchentyp, her, Preis, Naehrwert, Haltbarkeit, y, Allergene, Kremsorte, Obstsorte, y);
                        objList.add(setFachnummer(a));
                        readInAllergens(Allergene);
                        capacityAct += 1;
                        notifyObservers(capacityAct);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean updateInsp(int Fachnummer){
        try {
            objList.get(Fachnummer).setInspektionsdatum(new Date());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // TODO remove ALLERGENE  bzw aktualisiert ausgeben ||  oder duplicates abspeichern und remove ausführen
    public boolean removeObj(int Fachnummer){
        if((!objList.isEmpty()) && (objList.size() > Fachnummer)) {
                objList.remove(Fachnummer);
                objList.stream().filter(obj -> obj.getFachnummer() > Fachnummer).forEach(o -> o.setFachnummer(o.getFachnummer() - 1));
                capacityAct -= 1;
                return true;
        }else{
            return false;
        }
    }

    public List<String> showAllCakesSortedByHersteller(){
        List<String> showlist = new LinkedList<>();
        showlist = objList.stream().map(obj -> "Result -- " + obj.getHersteller().getName() + " " + obj.getKuchentyp()).sorted().collect(Collectors.toList());
        for (Hersteller h: herstellerList){
            showlist.add("Hersteller: "+ h.getName() +" besitzt Kuchen: " + showlist.stream().filter(obj -> obj.contains(h.getName())).count());
        }
        return showlist;
    }

    public List<String> showKuchenAll(){
        List<String> showList = new LinkedList<>();
        if(!objList.isEmpty()){
            showList = objList.stream().map(this::stringForShowKuchen).collect(Collectors.toList());
        }
        return showList;
    }

    public List<String> showKuchen(String kuchentyp) {
        List<String> showList = new LinkedList<>();
        if(!objList.isEmpty()){
            showList = objList.stream().filter(o -> o.getKuchentyp().equals(kuchentyp)).map(this::stringForShowKuchen).collect(Collectors.toList());
        }
        return showList;
    }

    public List<String> showAllergene(boolean lever) {      // true = vorhandene Allergene
        List<String> showList = new LinkedList<>();
        if(lever){
            allergenList.forEach(allergen -> showList.add(allergen.name()));
            return showList;
        }else {
            for(Allergen a : Allergen.values()){
                if(allergenList.contains(a)){
                    continue;
                } else {
                    showList.add(a.name());
                }
            }
            return showList;
        }
    }

    // Hilfsmethoden //
    public String format(Collection<?> c) {
        String s = c.stream().map(Object::toString).collect(Collectors.joining(" "));
        return String.format("["+s+"]");
    }

    public obj setFachnummer(obj o){
        if (objList.isEmpty()) {
            o.setFachnummer(0);
        } else {
            o.setFachnummer(objList.size());
        }
        return o;
    }

    public void readInAllergens(Collection<Allergen> Allergens){
        for (Allergen a : Allergens) {
            if (allergenList.contains(a)) {
                continue;
            } else {
                allergenList.add(a);
            }
        }
    }

    public String stringForShowKuchen(obj o){
        return "Typ: " + o.getKuchentyp()
                + "| Hersteller: " + o.getHersteller().getName()
                + "| Allergene: " + format(o.getAllergene())
                + "| Nährwert: " + o.getNaehrwert()
                + "| Haltbarkeit: " + o.getHaltbarkeit();
    }

    public LinkedList<Hersteller> getHerstellerList() {
        return herstellerList;
    }

    public LinkedList<obj> getObjList() {
        return objList;
    }

    public LinkedList<Allergen> getAllergenList() {
        return allergenList;
    }
}
