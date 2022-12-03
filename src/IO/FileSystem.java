package IO;

import GL.ObjDatabase;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class FileSystem {

    private ObjDatabase oDB;
    private String jos = "oDB.jos";
    private String jbp = "oDB.jbp";

    public FileSystem(ObjDatabase oDB){
        this.oDB = oDB;
    }

    public FileOutputStream getWriteStream(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return new FileOutputStream(path);
    }

    public FileInputStream getReadStream(String path) throws FileNotFoundException, IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("can't read file");
        }
        return new FileInputStream(path);
    }

    private void saveJOS() throws Exception {
        FileOutputStream out = getWriteStream( this.jos );
        ObjectOutputStream stream = new ObjectOutputStream( out );
        stream.writeObject(oDB);
        stream.close();
    }
    private ObjDatabase loadJOS() throws Exception {
        FileInputStream in = getReadStream( this.jos );
        ObjectInputStream stream = new ObjectInputStream( in );
        ObjDatabase oDB = (ObjDatabase) stream.readObject();
        stream.close();
        return oDB;
    }

    public String saveDB(String protocol) {
        try {
            switch (protocol) {
                case "jos" -> {
                    saveJOS();
                }
                case "jbp" -> {
                    saveJBP();
                }
                default -> {
                    return "invalid protocol for writing db: " + protocol;
                }
            }
            return "db saved";
        } catch (Exception e) {
            System.err.println("io error");
            e.printStackTrace();
            return "io error";
        }
    }

    public String loadDB(String protocol) {
        try {
            ObjDatabase db;
            switch (protocol) {
                case "jos" -> {
                    db = loadJOS();
                }
                case "jbp" -> {
                    db = loadJBP();
                }
                default -> {
                    return "invalid protocol for writing db: " + protocol;
                }
            }
            this.oDB.switchObjDatabase(db);
            this.oDB.notifyObservers();
            return "db loaded";
        } catch ( Exception e ) {
            System.err.println("io error");
            e.printStackTrace();
            return "io error";
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
        // TODO JBP ????
        encoder.writeObject(oDB);
    }



    public void setJos(String jos) {
        this.jos = jos;
    }

    public void setJbp(String jbp) {
        this.jbp = jbp;
    }
}
