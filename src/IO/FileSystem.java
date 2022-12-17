package IO;


import GL.ObjDatabase;
import Gui.Beans.ContentBeanFinal;

import java.beans.XMLDecoder;

import java.beans.XMLEncoder;
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
                case "jos" -> {
                    saveJOS();
                }
                case "jbp" -> {
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
                case "jos" -> {
                    db = loadJOS();
                }
                case "jbp" -> {
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
        XMLDecoder decoder = new XMLDecoder( new BufferedInputStream( in ) );
        return (ObjDatabase) decoder.readObject();
    }

    private void saveJBP() throws Exception {
        FileOutputStream out = getWriteStream( this.jbp );
        XMLEncoder encoder = new XMLEncoder( new BufferedOutputStream( out ) );

        ContentBeanFinal content = new ContentBeanFinal();
        content.herstellerList = oDB.getHerstellerList();
        content.objList = oDB.getObjList();
        content.allergenList = oDB.getAllergenList();
        encoder.writeObject(content);
    }




    public void setJos(String jos) {
        this.jos = jos;
    }

    public void setJbp(String jbp) {
        this.jbp = jbp;
    }
}
