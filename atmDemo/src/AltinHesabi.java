import java.util.ArrayList;
import java.util.Scanner;

public class AltinHesabi extends Hesaplar{

    Scanner scanner = new Scanner(System.in);
    CustomerManager customerManager = new CustomerManager();
    KrediHesabi krediHesabi = new KrediHesabi();
/*
    @Override
    public void getInfo() {

    }

 */


    CustomerFonksiyonlari customerFonksiyonlari = new CustomerFonksiyonlari();

    public void kullaniciArayuzuAltin(ArrayList<CustomerManager> customers) {
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
        double cekMiktar = scanner.nextDouble(); //şu anki gr altın fiyatı= 877.22 20/12/2021 22:00
        double kalan = ben.getAltin()-cekMiktar/877.22;
        if (paraCekKontrol(ben, cekMiktar)) { //Yeterli bakiye var mı kontrol edip paraCekDataBase e gidecek
            paraCekDataBase(ben, cekMiktar); //if içinde olucak
            System.out.println("Çekilen miktar: "+cekMiktar);
            System.out.println("Kalan Altın: "+kalan);
        } else {
            System.out.println("Yetersiz Bakiye");
        }
    }


    @Override
    public boolean paraCekKontrol(CustomerManager ben, double cekMiktar) {
        if ((ben.getAltin()*877.22) >= cekMiktar) {
            return true;
        } else {
            return false;
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
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin()-cekMiktar/877.22,
                ben.getTelefon(),
                ben.getIban());
        ben.setHesapBakiye(ben.getHesapBakiye() + cekMiktar);
        ben.setAltin(ben.getAltin()-cekMiktar/877.22);
    }

    /*
    public void paraCekDataBaseAltin(CustomerManager ben, double kalan, double cekMiktar) {
        customerManager.bakiyeGuncelle(ben, ben.getHesapBakiye()+ cekMiktar , ben.getKrediKartiLimit(), ben.getKrediKartiBorc(), false,
                ben.getAbdDolari(), ben.getEuro(), ben.getIngilizSterlini(),
                ben.getKuveytDinari(), kalan);
        ben.setHesapBakiye(ben.getHesapBakiye() + cekMiktar);
        ben.setAltin(kalan);
    }

     */

    @Override
    public void paraYatir(ArrayList<CustomerManager> customers) {
        CustomerManager ben = customers.get(0);
        System.out.println("1: Nakit Yatır");
        System.out.println("2: Banka Hesabından Yatır");
        int secYatir = scanner.nextInt();
        System.out.println("Yatırılacak parayı giriniz: ");
        double yatirilacakMiktar = scanner.nextDouble();
        if (secYatir == 1){
            paraYatirNakitAltin(ben,yatirilacakMiktar);
        } else if (secYatir ==2){
            paraYatirBankadanAltin(ben,yatirilacakMiktar);
        } else {
            System.out.println("Hatalı giriş!");
        }
        //CustomerManager customer, double bakiye, double krediKartiBorc, double krediKartiLimit, boolean secKart, double abdDolari, double euro,double ingilizSterlini, double kuveytDinari, double altin

    }

    // BANKA HESABIMDAN BURAYA AKTARIM DA YAPACAĞIM ŞEKİLDE YATIRIM YAPMALIYIM
    public void paraYatirBankadanAltin(CustomerManager ben, double yatirilacakMiktar) { //bundan iki tane olucak biri kartsız bu kartsız işlem diğeri de kartlı işlem onda bizim hesabımızdan bakiye de düşecek.
        //customer parayı alan
        //customer1 para giden hesap kart ile girdik yani customer1 biziz.

        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye()-yatirilacakMiktar,
                ben.getKrediKartiLimit(),
                ben.getKrediKartiBorc(),
                ben.getAbdDolari(),
                ben.getEuro(),
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin()+ yatirilacakMiktar/877.22,
                ben.getTelefon(),
                ben.getIban()); //database false=banka kartı
        ben.setHesapBakiye(ben.getHesapBakiye() - yatirilacakMiktar);
        ben.setAltin(ben.getAltin() + yatirilacakMiktar/877.22);
    }

    public void paraYatirNakitAltin(CustomerManager ben,double yatirilacakMiktar){
        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye() ,
                ben.getKrediKartiLimit(),
                ben.getKrediKartiBorc(),
                ben.getAbdDolari(),
                ben.getEuro(),
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin()+yatirilacakMiktar/877.22,
                ben.getTelefon(),
                ben.getIban());

        ben.setAltin(ben.getAltin() + yatirilacakMiktar/877.22);
    }






    @Override
    public void krediBorcOde(ArrayList<CustomerManager> customers) {
        krediHesabi.krediBorcOde(customers);
    } //fonksiyon içerisinde tanımla nesneyi sonra yapıştır abi.

}
