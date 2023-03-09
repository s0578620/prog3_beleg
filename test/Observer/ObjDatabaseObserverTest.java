package Observer;


import GL.ObjDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ObjDatabaseObserver;
import java.io.PrintStream;
import static org.mockito.Mockito.*;

public class ObjDatabaseObserverTest {



    @Test
    void testCapacityReached(){
        PrintStream output = System.out;
        try {
            PrintStream out = mock(PrintStream.class);
            System.setOut(out);
            ObjDatabase oDB = mock(ObjDatabase.class);
            when(oDB.getCapacityAct()).thenReturn(8).thenReturn(9);
            ObjDatabaseObserver obs = new ObjDatabaseObserver(oDB);

            obs.update(oDB,"");

            verify(out).println("90% capacity reached");
        }finally {
            System.setOut(output);
        }
    }

}
