package IO;


import GL.Hersteller;
import GL.Obj;
import GL.ObjDatabase;
import GL.Obstkuchen;
import vertrag.Kremkuchen;
import java.beans.*;
import java.io.*;


public class FileSystem {

    private ObjDatabase oDB;
    private String jos = "oDB.jos";
    private String jbp = "oDB.xml";

    public FileSystem(ObjDatabase oDB){
        this.oDB = oDB;
    }

    public FileOutputStream getWriteStream(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        return new FileOutputStream(filename);
    }

    public FileInputStream getReadStream(String filename) throws FileNotFoundException, IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        return new FileInputStream(filename);
    }

    private void saveJOS() throws Exception {
        ObjectOutputStream stream = new ObjectOutputStream(getWriteStream(this.jos));
        stream.writeObject(oDB);
        stream.close();
    }
    private ObjDatabase loadJOS() throws Exception {
        ObjectInputStream stream = new ObjectInputStream(getReadStream(this.jos));
        ObjDatabase oDB = (ObjDatabase) stream.readObject();
        stream.close();
        return oDB;
    }

    public String saveDB(String filename) {
        try {
            switch (filename) {
                case "JOS" -> {
                    saveJOS();
                }
                case "JBP" -> {
                    saveJBP();
                }
                default -> {
                    return "invalid choice";
                }
            }
            return "db saved";
        } catch (Exception e) {
            return "save jos/jbp error";
        }
    }

    public String loadDB(String filename) {
        try {
            ObjDatabase db;
            switch (filename) {
                case "JOS" -> {
                    db = loadJOS();
                }
                case "JBP" -> {
                    db = loadJBP();
                }
                default -> {
                    return "invalid choice";
                }
            }
            this.oDB.switchObjDatabase(db);
            this.oDB.notifyObservers();
            return "db loaded";
        } catch ( Exception e ) {

            return "load jos/jbp error";
        }
    }

    private ObjDatabase loadJBP() throws Exception {
        FileInputStream in = getReadStream( this.jbp );
        //XMLDecoder decoder = new XMLDecoder( new BufferedInputStream( in ) );
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(this.jbp)))) {
           // decoder.setPersistenceDelegate(Obj.class, new DefaultPersistenceDelegate(new String[]{"kuchentyp", "hersteller", "preis", "naehrwert", "haltbarkeit", "inspektionsdatum", "allergene", "insertDate"}));
            return (ObjDatabase) decoder.readObject();
        }
    }

    private void saveJBP() throws Exception {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(this.jbp)))) {



            //encoder.setPersistenceDelegate(Obstkuchen.class, new DefaultPersistenceDelegate(new String[] { "kuchentyp", "hersteller", "preis", "naehrwert", "haltbarkeit", "inspektionsdatum", "allergene", "insertDate" }));
            encoder.setPersistenceDelegate(Hersteller.class, new DefaultPersistenceDelegate(new String[]{"name"}));
            encoder.setPersistenceDelegate(ObjDatabase.class, new DefaultPersistenceDelegate(new String[]{"capacity", "herstellerList","objList"}));
            encoder.setPersistenceDelegate(Kremkuchen.class, new DefaultPersistenceDelegate(new String[]{"Kuchentyp", "Hersteller", "Preis", "Naehrwert", "Haltbarkeit", "Inspektionsdatum", "Allergene", "Kremsorte", "insertDate"}));
            encoder.setPersistenceDelegate(Obj.class, new DefaultPersistenceDelegate(new String[]{"Kuchentyp", "Hersteller", "Preis", "Naehrwert", "Haltbarkeit", "Inspektionsdatum", "Allergens", "insertDate"}));
            encoder.setPersistenceDelegate(Obstkuchen.class, new DefaultPersistenceDelegate(new String[]{"kuchentyp", "hersteller", "preis", "naehrwert", "haltbarkeit", "inspektionsdatum", "allergene", "fruchtsorte", "insertDate"}));
//            ContentBeanFinal content = new ContentBeanFinal();
//            content.herstellerList = oDB.getHerstellerList();
//        content.objList = oDB.getObjList();
//        content.allergenList = oDB.getAllergenList();
            encoder.writeObject(oDB);
        }
    }




    public void setJos(String jos) {
        this.jos = jos;
    }

    public void setJbp(String jbp) {
        this.jbp = jbp;
    }
}
