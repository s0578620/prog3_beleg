package IO;

import GL.ObjDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
