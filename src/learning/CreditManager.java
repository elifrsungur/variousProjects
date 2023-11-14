package learning;

import java.util.ArrayList;

class VehicleManager implements CreditManager{

    public void calculate(){
        System.out.println(" arac kredi hesaplandı");

    }

}
class MortageManager implements CreditManager{

    public void calculate(){
        System.out.println("ev kredi hesaplandı");

    }

}

interface CreditManager {
      void calculate();

}
class Main2 {
    public static void main(String[] args) {
        ArrayList<CreditManager> credits = new ArrayList<CreditManager>();
        credits.add(new VehicleManager());
        credits.add(new MortageManager());

        for (CreditManager credit : credits) {
            credit.calculate();
        }
    }
}

