import GL.ObjDatabase;
import Gui.Gui;

public class Application {
    public static void main(String[] args) {
        int capacity = 0;
        if (args.length > 0) {
            for (String arg : args) {
                if (arg.matches("\\d+")) {
                    capacity = Integer.parseInt(arg);
                }
            }
        }
        ObjDatabase oDB = new ObjDatabase(capacity);
        Gui.oDB = oDB;

        new Gui().run();
    }
}
