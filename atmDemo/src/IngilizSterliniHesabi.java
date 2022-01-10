import java.util.ArrayList;
import java.util.Scanner;

public class IngilizSterliniHesabi extends Hesaplar{

    Scanner scanner = new Scanner(System.in);
    CustomerManager customerManager = new CustomerManager();
    KrediHesabi krediHesabi = new KrediHesabi();
/*
    @Override
    public void getInfo() {

    }

 */

    CustomerFonksiyonlari customerFonksiyonlari = new CustomerFonksiyonlari();

    public void kullaniciArayuzuSterlin(ArrayList<CustomerManager> customers) {
        System.out.println("1: Banka Hesabına Para Aktar(İngiliz Sterlin'inden TL'ye)");
        System.out.println("2: Para Yatır");
        System.out.println("3: Kredi Borç Ödemesi");
        System.out.println("7: Geri");
        System.out.println("8: Çıkış");
        int sec1 = scanner.nextInt();


        switch (sec1) {

            case 1:
                paraCek(customers);
                customerFonksiyonlari.calistir(customers);
            case 2:
                paraYatir(customers);
                customerFonksiyonlari.calistir(customers);
            case 3:
                krediBorcOde(customers);
                customerFonksiyonlari.calistir(customers);
            case 7:
                customerFonksiyonlari.calistir(customers);
            case 8:
                System.exit(0);
        }
    }

    @Override
    public void paraCek(ArrayList<CustomerManager> customers) {
        CustomerManager ben = customers.get(0);
        System.out.println("Aktarılacak miktarı giriniz(TL): ");
        double cekMiktar = scanner.nextDouble(); //şu anki sterlin fiyatı= 18,18 05/01/2022 14:11
        double kalan = ben.getIngilizSterlini()-cekMiktar/18.18;
        if (paraCekKontrol(ben, cekMiktar)) { //Yeterli bakiye var mı kontrol edip paraCekDataBase e gidecek
            paraCekDataBase(ben, cekMiktar); //if içinde olucak
            System.out.println("Çekilen miktar: "+cekMiktar);
            System.out.println("Kalan İngiliz Sterlin'i: "+kalan);
        } else {
            System.out.println("Yetersiz Bakiye");
        }
    }

    @Override
    public boolean paraCekKontrol(CustomerManager ben, double cekMiktar) {
        if ((ben.getIngilizSterlini()*18.18) >= cekMiktar) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void paraYatir(ArrayList<CustomerManager> customers) {
        CustomerManager ben = customers.get(0);
        System.out.println("1: Nakit Yatır");
        System.out.println("2: Banka Hesabından Yatır");
        int secYatir = scanner.nextInt();
        System.out.println("Yatırılacak parayı giriniz: ");
        double yatirilacakMiktar = scanner.nextDouble();
        if (secYatir == 1){
            paraYatirNakitSterlin(ben,yatirilacakMiktar);
        } else if (secYatir ==2){
            paraYatirBankadanSterlin(ben,yatirilacakMiktar);
        } else {
            System.out.println("Hatalı giriş!");
        }
    }

    @Override
    public void paraCekDataBase(CustomerManager ben, double cekMiktar) {
        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye()+ cekMiktar ,
                ben.getKrediKartiLimit(),
                ben.getKrediKartiBorc(),
                ben.getAbdDolari(),
                ben.getEuro(),
                ben.getIngilizSterlini()-cekMiktar/18.18,
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban());
        ben.setHesapBakiye(ben.getHesapBakiye() + cekMiktar);
        ben.setIngilizSterlini(ben.getIngilizSterlini()-cekMiktar/18.18);
    }

    public void paraYatirBankadanSterlin(CustomerManager ben, double yatirilacakMiktar) { //bundan iki tane olucak biri kartsız bu kartsız işlem diğeri de kartlı işlem onda bizim hesabımızdan bakiye de düşecek.
        //customer parayı alan
        //customer1 para giden hesap kart ile girdik yani customer1 biziz.

        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye()-yatirilacakMiktar,
                ben.getKrediKartiLimit(),
                ben.getKrediKartiBorc(),
                ben.getAbdDolari(),
                ben.getEuro(),
                ben.getIngilizSterlini()+ yatirilacakMiktar/18.18,
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban()); //database false=banka kartı
        ben.setHesapBakiye(ben.getHesapBakiye() - yatirilacakMiktar);
        ben.setIngilizSterlini(ben.getIngilizSterlini() + yatirilacakMiktar/18.18);
    }

    public void paraYatirNakitSterlin(CustomerManager ben,double yatirilacakMiktar){
        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye() ,
                ben.getKrediKartiLimit(),
                ben.getKrediKartiBorc(),
                ben.getAbdDolari(),
                ben.getEuro(),
                ben.getIngilizSterlini()+ yatirilacakMiktar/18.18,
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban());

        ben.setIngilizSterlini(ben.getIngilizSterlini() + yatirilacakMiktar/18.18);
    }

    @Override
    public void krediBorcOde(ArrayList<CustomerManager> customers) {
        krediHesabi.krediBorcOde(customers);
    }
}

