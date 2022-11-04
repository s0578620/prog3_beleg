package GL;

public class Hersteller implements vertrag.Hersteller {

    private String name;

    public Hersteller(String name){
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }
}
