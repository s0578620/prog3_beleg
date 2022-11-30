package GL;
import util.Observable;
import util.Observer;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
public class ObjDatabase implements Observable {

    private int capacityMax;
    private int capacityAct;
    private LinkedList<Hersteller> herstellerList = new LinkedList<>();
    private LinkedList<Obj> objList = new LinkedList<>();
    private LinkedList<Allergen> allergenList = new LinkedList<>();
    private LinkedList<Observer> observerList = new LinkedList<>();

    public void attachObserver(Observer o) { this.observerList.add(o); }
    public void detachObserver(Observer o) { this.observerList.remove(o); }

    @Override
    public void notifyObservers() {
        for (Observer o : observerList) {
            o.update();
        }
    }

    public ObjDatabase(int capacity){
        this.capacityMax = capacity;
    }

    public int getCapacityMax() {
        return capacityMax;
    }

    public boolean addHersteller(String hersteller){
        Hersteller a = new Hersteller(hersteller);
        if(herstellerList.stream().anyMatch(h -> h.getName().equals(hersteller))){
            return false;
        }else {
            herstellerList.add(a);
            notifyObservers();
            return true;
        }
    }

    public boolean removeHersteller(String hersteller){
        Hersteller a = new Hersteller(hersteller);
        for(Hersteller h : herstellerList){
            if(h.getName().equals(a.getName())){
                for(Obj o : objList){
                    if(o.getHersteller().getName().equals(h.getName())){
                        removeObj(o.getFachnummer());
                        notifyObservers();
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
                        Obj a = new Kremkuchen(Kuchentyp, her, Preis, Naehrwert, Haltbarkeit, y, Allergene, Topping, y);
                        objList.add(setFachnummer(a));
                        readInAllergens(Allergene);
                        capacityAct += 1;
                        notifyObservers();
                        return true;
                    }
                    if (Kuchentyp.equals("Obstkuchen")) {
                        Obj a = new Obstkuchen(Kuchentyp, her, Preis, Naehrwert, Haltbarkeit, y, Allergene, Topping, y);
                        objList.add(setFachnummer(a));
                        readInAllergens(Allergene);
                        capacityAct += 1;
                        notifyObservers();
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
                        Obj a = new Obsttorte(Kuchentyp, her, Preis, Naehrwert, Haltbarkeit, y, Allergene, Kremsorte, Obstsorte, y);
                        objList.add(setFachnummer(a));
                        readInAllergens(Allergene);
                        capacityAct += 1;
                        notifyObservers();
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
            notifyObservers();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean removeObj(int Fachnummer){
        if((!objList.isEmpty()) && (objList.size() > Fachnummer)) {
                objList.remove(Fachnummer);
                objList.stream().filter(obj -> obj.getFachnummer() > Fachnummer).forEach(o -> o.setFachnummer(o.getFachnummer() - 1));
                capacityAct -= 1;
                refreshAllergeneList();
                notifyObservers();
                return true;
        }else{
            return false;
        }
    }

    public void refreshAllergeneList(){
        allergenList.clear();
        objList.stream().forEach(obj -> readInAllergens(obj.getAllergene()));
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

    public Obj setFachnummer(Obj o){
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

    public String stringForShowKuchen(Obj o){
        return "Typ: " + o.getKuchentyp()
                + "| Hersteller: " + o.getHersteller().getName()
                + "| Allergene: " + format(o.getAllergene())
                + "| Nährwert: " + o.getNaehrwert()
                + "| Haltbarkeit: " + o.getHaltbarkeit().toMinutes();       // TODO CHECKPOINT HALTBARKEIT DARSTELLUNG
    }

    public LinkedList<Hersteller> getHerstellerList() {
        return herstellerList;
    }

    public LinkedList<Obj> getObjList() {
        return objList;
    }

    public LinkedList<Allergen> getAllergenList() {
        return allergenList;
    }
}
