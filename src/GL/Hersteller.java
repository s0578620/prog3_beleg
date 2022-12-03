package GL;

import java.io.Serializable;

public class Hersteller implements vertrag.Hersteller, Serializable {

    private String name;

    public Hersteller(String name){
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }
}
