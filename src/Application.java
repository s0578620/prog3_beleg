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
        Gui.capacity = capacity;
        new Gui().run();
    }
}
