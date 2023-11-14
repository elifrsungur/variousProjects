package denemeler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

class Surface extends JPanel implements ActionListener {
    private final int DELAY = 70;
    private double width;
    private double height;
    private double topX = 0;
    private double topY = 0;
    private double açı = 60;
    private double ilkHız = 8;
    private double yHızı;
    private double xHızı;
    private double zaman = 0;
    private int tekrar = 3;
    private boolean basla = false;
    private double cap;
    private Timer timer;
    private JTextField açıTextBox;
    private JTextField hızTextBox;
    private JTextField tekrarTextBox;
    private JLabel açıLabel;
    private JLabel hızLabel;
    private JLabel tekrarLabel;
    private JButton başlatButton;
    //    cizgi sinifin degiskenini tanimla
    private Shadow shadow;
    //    liste olustur
    private ArrayList<Shadow> shadowList = new ArrayList<Shadow>();

    public Surface() {
        initTimer();
        initAdd();
    }

    private void initAdd() {
//      hiz text blogun nesnesini oluşturdum ve icinde parametre olarak boyutunu belirledim
        hızTextBox = new JTextField(8);
        açıTextBox = new JTextField(8);
        tekrarTextBox = new JTextField(8);
//        aci etiketin nesnesini oluşturdum ve icinde string tipinde ismini atadım.
        açıLabel = new JLabel("Açı");
        tekrarLabel = new JLabel("Tekrar");
        hızLabel = new JLabel("Hız");
//        basla butunun nesnesini turettim ve başla string tipinde parametre atadim
        başlatButton = new JButton("Başla");
//        hız yazısı ekle
        add(hızLabel);
//        hız kutusunu ekle
        add(hızTextBox);

        add(açıLabel);
        add(açıTextBox);

        add(tekrarLabel);
        add(tekrarTextBox);
//        baslat butununu ekle
        add(başlatButton);

        başlatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hızTextBox.getText().isEmpty() && !açıTextBox.getText().isEmpty() && !tekrarTextBox.getText().isEmpty()) {
                    açı = Integer.valueOf(açıTextBox.getText());
                    ilkHız = Integer.valueOf(hızTextBox.getText());
                    tekrar = Integer.valueOf(tekrarTextBox.getText());
                    //                 başla false if in koşulu true ya çevirdi ve içine girdi.
                    if (!basla) {
                        basla = true;
                        timer.start();
                    }
                }
            }

  /*            } else if (!açıTextBox.getText().isEmpty()) {
                    açı = Integer.parseInt(açıTextBox.getText());
                } else if (!hızTextBox.getText().isEmpty()) {
                    ilkHız = Integer.parseInt(hızTextBox.getText());
                } else if (!tekrarTextBox.getText().isEmpty()) {
                    tekrar = Integer.parseInt(hızTextBox.getText());
                }
                if (!basla) {
                    basla = true;
                    timer.start();
                }
            }

   */
        });
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
    }

    private void doDrawing(Graphics g) {
        width = getWidth();
        height = getHeight();
        Graphics2D g2d = (Graphics2D) g;
//      topumuzun kenarlarının pürüzsüz olmasını sağlar;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);


//        topun çapı
        cap = height * 0.08;
//        ilk basta konumlar sifir olunca calisir, olmazsa her seferinde  yine ayni konumu verir ve bizim top hareket etmez
        if (topY == 0 && topX == 0) {
//          topun ilk x konumunu belirler
            topX = width * 0.03;
//          topun ilk y konumunu belirler
            topY = height * 0.97 - cap;
        }
//        vson y = vilk*sin(açı)-g.t
        yHızı = ilkHız * Math.sin(Math.PI / 180 * açı) - (9.81 * zaman);
//        vson x= vilk*cos(açı)
        xHızı = ilkHız * Math.cos(Math.PI / 180 * açı);
//      koordinat çizgileri
        g2d.drawLine((int) (width * 0.03), (int) (height * 0.03), (int) (width * 0.03), (int) (height * 0.97));
        g2d.drawLine((int) (width * 0.03), (int) (height * 0.97), (int) (width * 0.97), (int) (height * 0.97));
//        top çizimi
        g2d.fillOval((int) (topX), (int) (topY), (int) (cap), (int) (cap));
//        topY ilk basta y konumu tutar her tetiklediginde birim saniye icinde alan hiz kadar dikey yol alir
        topY -= yHızı;
//        topX basta X Konum tutar her tetiklediginde birim saniye icinde yatayda yol alir
        topX += xHızı;
//        ilk basta yukariya dogru firlatilan topun 0. saniyeyi tutar ver her tekrarda 0.01  saniye artarak y yol hizini azaltir.
        zaman += 0.01;

        //        yeni eklendi
//        ciginin nesnesini turettim ve parametre olarak topun x ve y konumunu attim
       shadow = new Shadow(topX, topY, Math.sin(Math.PI / 180 * açı));
//    //    opaq istemiyorum saydan olsun
        shadow.setOpaque(false);
//    //    panelin boyutu, icindeki panelin boyutu
        shadow.setBounds(0, 0, getWidth(), getHeight());
//    //    panelli ekle, panelin icinde
       add(shadow);
//    //    liste icinde paneli ekle
        shadowList.add(shadow);

    }

    //   bu metod her tekrarda calisir ver baslangic konumuna geri doner
    private void basaDon() {
        topY = 0;
        topX = 0;
//        hiz saniye belirleyici basa doner
        zaman = 0;

        //        yeni eklendi
//        cigzileri sil
        for (Shadow temp : shadowList) {
            remove(temp);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
//       basla = false'dir ve start butunu basilincaya kadar calismaz ve bu if blok icinde girmez
        if (basla) {
//            topun yere dedigi konumundan buyuk olup olmadigini kontrol eder
//            topun aldigi konum arti capi bizim belirledigimiz eksen cizgisinden cikti mi?
            if (topY + cap > height * 0.97) {
//                tekrarimiz sonlandi mi kontrolu
                if (tekrar == 1) {
//                    eger tekrarimiz bittiyse basla'yi false yap ve durdur
                    basla = false;
                } else {
//                    eger terkrarimiz bitmediyse tekrari azalt
                    tekrar--;
//                    her seyi sifir yap
                    basaDon();
                }
            }
            super.paintComponent(g);
            doDrawing(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (basla) {
            repaint();
        }
    }

    // yeni eklendi
// cizgi sinifi
    class Shadow extends JPanel {
        private double ballX;
        private double ballY;
        private double hizAci;

        //   yapilandirici metod paramatre aliyor
        public Shadow(double ballX, double ballY, double hizAci) {
//        alinan parametreler degiskenlere atandi
            this.ballX = ballX;
            this.ballY = ballY;
            this.hizAci = hizAci;
        }

        private void doDrawing(Graphics2D g) {
//        kucuk kucuk cigzi cizer
            Line2D line2D = new Line2D.Double(ballX + getHeight() * 0.05, ballY + getHeight() * 0.05,
                    ballX + getHeight() * 0.05, ballY + getHeight() * 0.05 - (hizAci % 2));
            g.draw(line2D);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            doDrawing((Graphics2D) g);
        }
    }

    public static class Top extends JFrame {
        public Top() {
            initUI();

        }
        private void initUI() {
            Surface surface = new Surface();
            add(surface);
            setTitle("Grafik");
            setSize(900, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public static void main(String[] args) {
            Top st = new Top();
            st.setVisible(true);


        }
    }
}
