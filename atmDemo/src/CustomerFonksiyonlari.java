import java.util.ArrayList;
import java.util.Scanner;

public class CustomerFonksiyonlari {


    Scanner scanner = new Scanner(System.in);
    //CustomerManager customerManager = new CustomerManager();
    BankaHesabi bankaHesabi = new BankaHesabi();


    public boolean sifreKontrol(String girilenSifre, String sifre) { //burarayı static bir değer ile 3 e kadar kontrol edebiliriz. şimdilik while ile yapacağım
        boolean boolKontrol = false;
        if (girilenSifre.equals(sifre)) {
            boolKontrol = true;
        }
        return boolKontrol;
    }

    public void sifreSor(ArrayList<CustomerManager> customers) {
        int sayac = 0;
        System.out.println("Şifrenizi giriniz: ");
        String sifre = scanner.next();
        while (!sifreKontrol(sifre, customers.get(0).getSifre())) {
            System.out.println("Hatalı Giriş!");
            sayac++;
            if (sayac > 3) {
                System.exit(0);
            }
            System.out.println("Şifrenizi giriniz: ");
            sifre = scanner.next();
        }
        secKart(customers); // çalıştır a gidecek banka kartı mı kredi kartı mı onu belirleyecek

    }

    public void secKart(ArrayList<CustomerManager> customers) {
        int sec;
        System.out.println("1: Kredi Kartı");
        System.out.println("2: Banka Kartı");
        System.out.println("8: Çıkış");
        sec = scanner.nextInt();
        if (sec == 1) {
            calistirKredi(customers);
        } else if (sec == 2) {
            calistir(customers);
        } else if (sec == 8) {
            System.exit(0);
        } else {
            System.out.println("Hatalı Giriş");
            System.exit(0);
        }
    }


    public void basla(ArrayList<CustomerManager> customers) {
        System.out.println("1: Kartlı İşlemler");
        System.out.println("2: Kartsız İşlemler");
        System.out.println("8: Çıkış");
        int secİslem = scanner.nextInt();
        if (secİslem == 1) {
            sifreSor(customers); //sifre doğru ise çalıştır fonksiyonunu çalıştıracak
        } else if (secİslem == 2) {
            KartsizIslemler kartsizIslemler = new KartsizIslemler();
            System.out.println("1: Konut kitası ödemesi");
            System.out.println("2: İş yeri kirası ödemesi");
            System.out.println("3: Diğer kira ödemeleri");
            System.out.println("4: e-Ticaret ödemesi");
            System.out.println("5: Eğitim ödemeleri");
            System.out.println("6: Diğer ödemeler");
            System.out.println("7: Geri");
            System.out.println("8: Çıkış");
            int secOde = scanner.nextInt();
            switch (secOde) {
                case 1:
                    kartsizIslemler.konutKirasi(customers);
                    basla(customers);
                case 2:
                    kartsizIslemler.isYeriKirasi(customers);
                    basla(customers);
                case 3:
                    kartsizIslemler.digerKiralar(customers);
                    basla(customers);
                case 4:
                    kartsizIslemler.eTicaretOdemesi(customers);
                    basla(customers);
                case 5:
                    kartsizIslemler.egitimOdemeleri(customers);
                    basla(customers);
                case 6:
                    kartsizIslemler.digerOdemeler(customers);
                    basla(customers);
                case 7:
                    basla(customers);
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Hatalı giriş!");
                    break;
            }

        } else if (secİslem == 8) {
            System.exit(0);
        } else {
            System.out.println("Hatalı giriş!");
        }
    }

    public void calistir(ArrayList<CustomerManager> customers) { //burada normalde çalıştır olucak başlat veri çek i çağıracak

        System.out.println("Yapılacak işlemi seciniz: ");
        System.out.println("1: Para Çekme"); //tamam
        System.out.println("2: Para Yatırma"); //kartlı ve kartsız olucak //kendi hesabıma ve başka hesaba olmalı
        System.out.println("3: Kredi Kartı İşlemleri"); //para çekme / para yatırma / bireysel ihtiyaç kredisi başvurusu/ kredi kartı borcu ödeme/ fatura ödeme/ kontör yükleme/ diğer ödemeler
        System.out.println("4: Yatırım İşlemleri"); //burada döviz hesabı / altın hesabı falan olucak. ----- //ABDDolari / Euro / IngilizSterlini / Kuveyt Dinarı
        System.out.println("5: Para Transferi"); //Iban ile müşteri no ile tc kimlik ile emin olmak için telefon girsin
        System.out.println("6: Ödemeler");  //konut kirası / işyeri kirası/ diğer kiralar/ e-ticaret ödemesi/ diğer ödemeler
        System.out.println("7: Geri"); // kartlı kartsız işlemler
        System.out.println("8: Çıkış");

        int secim = scanner.nextInt();
        switch (secim) {
            case 1:
                bankaHesabi.paraCek(customers);
                calistir(customers);
            case 2:
                bankaHesabi.paraYatir(customers);
                calistir(customers);
            case 3:
                bankaHesabi.krediBorcOde(customers);
                calistir(customers);
            case 4:
                yatirimIslemleri(customers);
                calistir(customers);
            case 5:
                paraTransferleri(customers);
                calistir(customers);
            case 6:
                Odemeler(customers);
                calistir(customers);
            case 7:
                calistir(customers);
            case 8:
                System.exit(0);
        }


    }


    public void yatirimIslemleri(ArrayList<CustomerManager> customers) {
        System.out.println("1: ABD Doları Hesabı İşlemleri");
        System.out.println("2: Euro Hesabı İşlemleri");
        System.out.println("3: İngiliz Sterlini Hesabı İşlemleri");
        System.out.println("4: Kuveyt Dinarı Hesabı İşlemleri");
        System.out.println("5: Altın Hesabı İşlemleri");
        System.out.println("7: Geri");
        System.out.println("8: Çıkış");
        int sec = scanner.nextInt();
        switch (sec) {
            case 1:
                ABDDolariHesabi abdDolariHesabi = new ABDDolariHesabi();
                abdDolariHesabi.kullaniciArayuzuABDDolari(customers);
                calistir(customers);
            case 2:
                EuroHesabi euroHesabi = new EuroHesabi();
                euroHesabi.kullaniciArayuzuEuroHesabi(customers);
                calistir(customers);

            case 3:
                IngilizSterliniHesabi ingilizSterliniHesabi = new IngilizSterliniHesabi();
                ingilizSterliniHesabi.kullaniciArayuzuSterlin(customers);
                calistir(customers);
            case 4:
                KuveytDinariHesabi kuveytDinariHesabi = new KuveytDinariHesabi();
                kuveytDinariHesabi.kullaniciArayuzuKuveytDinari(customers);
                calistir(customers);
            case 5:
                AltinHesabi altinHesabi = new AltinHesabi();
                altinHesabi.kullaniciArayuzuAltin(customers);
                calistir(customers);
            case 7:
                calistir(customers);
            case 8:
                System.exit(0);
            default:
                System.out.println("Hatalı giriş!");
                calistir(customers);

        }
    }


        public void paraTransferleri (ArrayList < CustomerManager > customers) {
            AltinHesabi altinHesabi = new AltinHesabi();
            System.out.println("1: Banka Hesabına Para Aktar(Altından TL'ye)");
            System.out.println("2: Para Yatır");
            System.out.println("3: Kredi Borç Ödemesi");
            System.out.println("7: Geri");
            System.out.println("8: Çıkış");
            int sec5 = scanner.nextInt();


            switch (sec5) {
                case 1:
                    altinHesabi.paraCek(customers);
                    calistir(customers); //hatalı
                case 2:
                    altinHesabi.paraYatir(customers);
                    calistir(customers); //hatalı
                case 3:
                    altinHesabi.krediBorcOde(customers);
                    calistir(customers); //hatalı
                case 7:
                    calistir(customers);
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Hatalı giriş!");
                    calistir(customers);
            }
        }

        public void Odemeler (ArrayList < CustomerManager > customers) {
            BankaHesabi bankaHesabi = new BankaHesabi();
            System.out.println("1: Konut kitası ödemesi");
            System.out.println("2: İş yeri kirası ödemesi");
            System.out.println("3: Diğer kira ödemeleri");
            System.out.println("4: e-Ticaret ödemesi");
            System.out.println("5: Eğitim ödemeleri");
            System.out.println("6: Diğer ödemeler");
            System.out.println("7: Geri");
            System.out.println("8: Çıkış");
            int sec6 = scanner.nextInt();
            switch (sec6) {
                case 1:
                    bankaHesabi.konutKirasi(customers);
                    calistir(customers);
                case 2:
                    //kartsizIslemler.isYeriKirasi(customers);
                    basla(customers);
                case 3:
                    //kartsizIslemler.digerKiralar(customers);
                    basla(customers);
                case 4:
                    //artsizIslemler.eTicaretOdemesi(customers);
                    basla(customers);
                case 5:
                    //kartsizIslemler.egitimOdemeleri(customers);
                    basla(customers);
                case 6:
                    //kartsizIslemler.digerOdemeler(customers);
                    basla(customers);
                case 7:
                    basla(customers);
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Hatalı giriş!");
                    calistir(customers);
            }
        }


        public void calistirKredi (ArrayList < CustomerManager > customers) {
            KrediHesabi krediHesabi = new KrediHesabi();
            System.out.println("Yapılacak işlemi seciniz: ");
            System.out.println("1: Kredi Para Çekme");
            System.out.println("2: Kredi Borc Odeme");
            System.out.println("3: Para Yatir");
            System.out.println("4: Size Özel Krediler"); //Yapım aşamasında
            System.out.println("7: Geri");
            System.out.println("8: Çıkış");
            int secimKredi = scanner.nextInt();
            switch (secimKredi) {
                case 1:
                    krediHesabi.paraCek(customers);
                    calistirKredi(customers);
                case 2:
                    krediHesabi.krediBorcOde(customers);
                    calistirKredi(customers);
                case 3:
                    krediHesabi.paraYatir(customers); //burada borçları göstersin ve eğer borcu var ise yatıracağı paranın borcu kapatmaya gideceğini uyarı versin.
                    calistirKredi(customers);
                case 7:
                    secKart(customers);
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Hatalı giriş!");
                    calistir(customers);

            }


        }


    }
