package learning;

public class Program {
    public static void main(String[] args) {
        ciftBulma(1,34);
    }

     static void ciftBulma(int baslangic, int bitis) {

        if(baslangic<bitis){
            for ( int i = baslangic; i<= bitis ; i++){
                if(i%2==0){
                    System.out.println(i);
                }
            }
        }

        if (bitis<baslangic){
            for (int i = bitis ; i <= baslangic ; i ++){
                if(i%2 == 0){
                    System.out.println(i);
                }
            }
        }

    }

}
