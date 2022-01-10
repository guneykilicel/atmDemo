import java.util.ArrayList;
import java.util.Scanner;

public class ABDDolariHesabi extends Hesaplar{
    Scanner scanner = new Scanner(System.in);
    CustomerManager customerManager = new CustomerManager();
    KrediHesabi krediHesabi = new KrediHesabi();
/*
    @Override
    public void getInfo() {

    }

 */
    public void kullaniciArayuzuABDDolari(ArrayList<CustomerManager> customers) {
        System.out.println("1: Banka Hesabına Para Aktar(Dolardan TL'ye)");
        System.out.println("2: Para Yatır");
        System.out.println("3: Kredi Borç Ödemesi");
        System.out.println("7: Geri");
        System.out.println("8: Çıkış");
        int sec1 = scanner.nextInt();


        switch (sec1) {
            case 1:
                paraCek(customers);
            case 2:
                paraYatir(customers);

            case 3:
                krediBorcOde(customers);

            case 7:
                CustomerFonksiyonlari customerFonksiyonlari = new CustomerFonksiyonlari();
                customerFonksiyonlari.calistir(customers);
            case 8:
                System.exit(0);
        }
    }

    @Override
    public void paraCek(ArrayList<CustomerManager> customers) {
        CustomerManager ben = customers.get(0);
        System.out.println("Aktarılacak miktarı giriniz(TL): ");
        double cekMiktar = scanner.nextDouble(); //şu anki gr dolar fiyatı= 12,99 21/12/2021 22:06
        double kalan = ben.getAbdDolari()-cekMiktar/12.99;
        if (paraCekKontrol(ben, cekMiktar)) { //Yeterli bakiye var mı kontrol edip paraCekDataBase e gidecek
            paraCekDataBase(ben, cekMiktar); //if içinde olucak
            System.out.println("Çekilen miktar: "+cekMiktar);
            System.out.println("Kalan Dolar: "+kalan);
        } else {
            System.out.println("Yetersiz Bakiye");
        }
    }

    @Override
    public boolean paraCekKontrol(CustomerManager ben, double cekMiktar) {
        if ((ben.getAbdDolari()*12.99) >= cekMiktar) {
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
                ben.getAbdDolari()-cekMiktar/12.99,
                ben.getEuro(),
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban());
        ben.setHesapBakiye(ben.getHesapBakiye() + cekMiktar);
        ben.setAbdDolari(ben.getAbdDolari()-cekMiktar/12.99);
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
            paraYatirNakitDolar(ben,yatirilacakMiktar);
        } else if (secYatir ==2){
            paraYatirBankadanDolar(ben,yatirilacakMiktar);
        } else {
            System.out.println("Hatalı giriş!");
        }
    }

    public void paraYatirBankadanDolar(CustomerManager ben, double yatirilacakMiktar) { //bundan iki tane olucak biri kartsız bu kartsız işlem diğeri de kartlı işlem onda bizim hesabımızdan bakiye de düşecek.
        //customer parayı alan
        //customer1 para giden hesap kart ile girdik yani customer1 biziz.

        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye()-yatirilacakMiktar,
                ben.getKrediKartiLimit(),
                ben.getKrediKartiBorc(),
                ben.getAbdDolari()+ yatirilacakMiktar/12.99,
                ben.getEuro(),
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban()); //database false=banka kartı
        ben.setHesapBakiye(ben.getHesapBakiye() - yatirilacakMiktar);
        ben.setAbdDolari(ben.getAbdDolari() + yatirilacakMiktar/12.99);
    }

    public void paraYatirNakitDolar(CustomerManager ben,double yatirilacakMiktar){
        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye() ,
                ben.getKrediKartiLimit(),
                ben.getKrediKartiBorc(),
                ben.getAbdDolari()+yatirilacakMiktar/12.99,
                ben.getEuro(),
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban());

        ben.setAbdDolari(ben.getAbdDolari() + yatirilacakMiktar/12.99);
    }

    @Override
    public void krediBorcOde(ArrayList<CustomerManager> customers) {
        krediHesabi.krediBorcOde(customers);
    }
}
