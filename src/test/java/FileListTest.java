import org.junit.Test;
import static org.junit.Assert.*;
import music.FileList;
public class FileListTest{

    @Test
    public void testFileListGetList(){
        FileList filelist = new FileList();
        assertNotNull(filelist.getList());

    }
}
