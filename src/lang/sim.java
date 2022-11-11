package lang;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class sim implements Runnable{

    LinkedList<Runnable> thread_List = new LinkedList<>();



    @Override
    public void run() {
        int counter = 0;
        do{
//            System.out.println("simONE gestartet");
//            simAdd s = new simAdd();
//            simRemove s2 = new simRemove();
//            Thread addThread = new Thread(s);
//            Thread remThread = new Thread(s2);
//
//            if(counter < 1){
//                addThread.start();
//
//            }
//            counter +=1;
//            if(counter > 4){
//                try {
//                    remThread.join(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            System.out.println(counter);
//            try {
//                sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }while (true);


    }
}
