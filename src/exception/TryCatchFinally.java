package exception;

import java.io.FileWriter;
import java.io.PrintWriter;

class ListOfNumbers {
    private int [] list  = { 5,6,8,9,2};
    public void writeList(){
        PrintWriter out = null;

        try{
            System.out.println("entering try statement");
            out = new PrintWriter( new FileWriter("output.txt"));
            for ( int i = 0 ; i < 7 ; i++){
                out.println(i + list[i]);
            }
        }
        catch (Exception e){
            System.out.println("ex -- >" + e.getMessage());
        }

        finally {
            if( out != null){
                System.out.println("closing printwriter");
                out.close();
            }
            else {
                System.out.println("printwriter not open");
            }
        }
    }
}

public class TryCatchFinally {
    public static void main(String[] args) {
        ListOfNumbers list = new ListOfNumbers();
        list.writeList();
    }
}
