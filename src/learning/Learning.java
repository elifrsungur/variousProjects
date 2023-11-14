package learning;

 class Learning {
     public static void main(String[] args) {
         String [] names = new String[]{"elif","fatma","elias"};
         for( String name : names){
             System.out.println(name);
         }

         int[] sayilar = new int[]{1,2,3,4,5};

         boolean varMi = true;
         sayiBul(sayilar,6);
         mesajVer(varMi, 6 );
         sehirler();


     }
     public static void mesajVer(boolean varMi, int aranacak){
         String mesaj = "";
         if(varMi){
             mesaj = "sayi mevcut: " + aranacak;
             System.out.println(mesaj);
         }else{
             mesaj = "sayi yok" + aranacak;
             System.out.println(mesaj);
         }
     }
     public static boolean sayiBul(int[] sayilar, int aranacak){
         boolean varMi = false;
         for (int sayi : sayilar) {
             if(sayi == aranacak)
                 varMi = true;
         }
         return varMi;
     }
     public static void sehirler(){
         String[] sehirler1 = {"sivas","samsun","ankara"};
         String [] sehirler2 = new String[]{"sakarya", "bursa", "istanbul"};
         sehirler1 = sehirler2; // sehirler2 nin adresi sehirler1 in adresine eşitlendi
         sehirler1[0] =" kayseri";
         System.out.println(sehirler2[0]);

         int sayi1= 0;
         int sayi2 = 5;
         sayi1=sayi2;
         sayi2 =7;
         System.out.println(sayi1);

         // integer, boolean double .... sayısal veriler değer tiptir. --stackte
         //array class abstact interface ...... referans tiptir. stackte adres kullanarak heap de değeler var
         //class
     }

}
