package IO;

import GL.ObjDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class FileSystemTestMockito {
    @Mock
    ObjDatabase mockDB;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadDBValid() {
        FileSystem fs = new FileSystem(mockDB);
        String result = fs.loadDB("JOS");
        assertEquals("db loaded", result);
    }

    @Test
    void testLoadDBInvalid() {
        FileSystem fs = new FileSystem(mockDB);
        String result = fs.loadDB("invalid");
        assertEquals("invalid choice", result);
    }

//    @Test
//    void testLoadDBError() {
//        // Mock a database that throws an exception when switching the object database
//        ObjDatabase mockErrorDB = mock(ObjDatabase.class);
//
//        doThrow(Exception.class).when(mockErrorDB).switchObjDatabase(any());
//
//        FileSystem fs = new FileSystem(mockErrorDB);
//        String result = fs.loadDB("JOS");
//        assertEquals("load jos/jbp error", result);
//    }

}
