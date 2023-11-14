
import java.util.*;
public class SafeVarargs {

    private void displayList(List<String>...lists){
        for( List<String> list : lists){
            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        SafeVarargs obj = new SafeVarargs();
        List<String> universityList = Arrays.asList("1","2");

        obj.displayList(universityList);

        List<String> prog = Arrays.asList("java","c");
        obj.displayList(universityList, prog);
    }
}
