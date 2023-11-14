import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ThrowsKeyword {

    public static void main(String[] args) {

        try{
            findFile();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private static void findFile()  throws IOException {
        File newFile = new File("test.txt");
        FileInputStream stream =  new FileInputStream(newFile);
    }

}
