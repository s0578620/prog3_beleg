package IO;


import GL.Hersteller;
import GL.Obj;
import GL.ObjDatabase;
import GL.Obstkuchen;
import vertrag.Allergen;
import vertrag.Kremkuchen;
import java.beans.*;
import java.io.*;
import java.math.BigDecimal;


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
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(this.jbp)))) {
            return (ObjDatabase) decoder.readObject();
        }
    }

    private void saveJBP() throws Exception {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(this.jbp)))) {



            encoder.setPersistenceDelegate(Hersteller.class, new DefaultPersistenceDelegate(new String[]{"name"}));
            encoder.setPersistenceDelegate(ObjDatabase.class, new DefaultPersistenceDelegate(new String[]{"capacity", "herstellerList","objList","allergenList"}));
            encoder.setPersistenceDelegate(GL.Kremkuchen.class, new DefaultPersistenceDelegate(new String[]{"kuchentyp", "hersteller", "preis", "naehrwert", "haltbarkeit", "inspektionsdatum", "allergene", "kremsorte", "insertDate"}));
            encoder.setPersistenceDelegate(GL.Obstkuchen.class, new DefaultPersistenceDelegate(new String[]{"kuchentyp", "hersteller", "preis", "naehrwert", "haltbarkeit", "inspektionsdatum", "allergene", "obstsorte", "insertDate"}));
            encoder.setPersistenceDelegate(GL.Obsttorte.class, new DefaultPersistenceDelegate(new String[]{"kuchentyp", "hersteller", "preis", "naehrwert", "haltbarkeit", "inspektionsdatum", "allergene", "obstsorte", "kremsorte", "insertDate"}));
            encoder.setPersistenceDelegate(BigDecimal.class, new DefaultPersistenceDelegate() {
                        protected Expression instantiate(Object oldInstance, Encoder out) {
                            BigDecimal bd = (BigDecimal) oldInstance;
                            return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[]{
                                    bd.toString()
                            });
                        }
                        protected boolean mutatesTo(Object oldInstance, Object newInstance) {
                            return oldInstance.equals(newInstance);
                        }
                    }
            );
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
