import java.util.ArrayList;
import java.util.Scanner;

public class KrediHesabi extends Hesaplar{
    Scanner scanner = new Scanner(System.in);
    CustomerManager customerManager = new CustomerManager();
    //BankaHesabi bankaHesabi = new BankaHesabi();
/*
    @Override
    public void getInfo() {

    }

 */

    @Override
    public void paraCek(ArrayList<CustomerManager> customers) {
        CustomerManager ben = customers.get(0);
        System.out.println("Çekilecek miktarı giriniz: ");
        double cekMiktar = scanner.nextDouble();
        if (paraCekKontrol(ben, cekMiktar)) { //Yeterli bakiye var mı kontrol edip paraCekDataBase e gidecek
            paraCekDataBaseKredi(ben, cekMiktar); //if içinde olucak
        } else {
            System.out.println("Kredi Kartı Limitiniz Yetersiz");
        }
    }

    @Override
    public boolean paraCekKontrol(CustomerManager ben, double cekMiktar) {
        if (ben.getKrediKartiLimit() >= (cekMiktar + ben.getKrediKartiBorc())) {
            return true;
        } else {
            return false;
        }
    }

    public void paraCekDataBaseKredi(CustomerManager ben, double cekMiktar) {
        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye(),
                ben.getKrediKartiBorc() + cekMiktar,
                ben.getKrediKartiLimit() - cekMiktar,
                ben.getAbdDolari(),
                ben.getEuro(),
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban()); //database kredilimit true

        ben.setKrediKartiLimit(ben.getKrediKartiLimit() - cekMiktar); //arrylist limit
        ben.setKrediKartiBorc(ben.getKrediKartiBorc() + cekMiktar); //arrylist borc
    }

    @Override
    public void paraYatir(ArrayList<CustomerManager> customers) {
        krediBorcOde(customers);
    }
/*
    @Override
    public void krediBorcOde(ArrayList<CustomerManager> customers) {

    }

 */

    @Override
    public void krediBorcOde(ArrayList<CustomerManager> customers) {
        CustomerManager ben = customers.get(0);
        System.out.println("1: Nakit Öde");
        System.out.println("2: Hesaptan Öde");
        int secOde = scanner.nextInt();
        System.out.println("Yatirilacak miktarı giriniz: ");
        double yatirilacakMiktar = scanner.nextDouble();
        if (secOde == 1) {
            borcOdeNakitDataBase(ben, yatirilacakMiktar);
        } else if (secOde == 2) {
            borcOdeBankaDataBase(ben, yatirilacakMiktar);
        } else {
            System.out.println("Hatalı giriş");
        }
    }

    @Override
    public void paraCekDataBase(CustomerManager ben, double cekMiktar) { //Banka hesabı ile aynı ORTAK FONK. bu
        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye(),
                ben.getHesapBakiye() - cekMiktar, 0,
                ben.getAbdDolari(),
                ben.getEuro(),
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban()); //database banka kartı=false
        ben.setHesapBakiye(ben.getHesapBakiye() - cekMiktar); //arrylist

    }


    public void borcOdeNakitDataBase(CustomerManager ben, double yatirilacakMiktar) {
        if (yatirilacakMiktar > ben.getKrediKartiBorc() && yatirilacakMiktar != 0) { //kullanıcı hem borcu kapatıp hem de limit arttırmak isterse veya borcu olmadan limit arttırmak isterse
            double limitArttir = yatirilacakMiktar - ben.getKrediKartiBorc();
            krediLimitArttır(ben, limitArttir);
        } else {
            customerManager.bakiyeGuncelle(
                    ben,
                    ben.getHesapBakiye(),
                    ben.getKrediKartiLimit(),
                    ben.getKrediKartiBorc() - yatirilacakMiktar,
                    ben.getAbdDolari(),
                    ben.getEuro(),
                    ben.getIngilizSterlini(),
                    ben.getKuveytDinari(),
                    ben.getAltin(),
                    ben.getTelefon(),
                    ben.getIban()); //database kredilimit true

            ben.setKrediKartiLimit(ben.getKrediKartiLimit() + yatirilacakMiktar); //arrylist limit
            ben.setKrediKartiBorc(ben.getKrediKartiBorc() - yatirilacakMiktar); //arrylist borc
        }
    }

    public void borcOdeBankaDataBase(CustomerManager ben, double yatirilacakMiktar) {
        double control = ben.getKrediKartiBorc() - yatirilacakMiktar;
        if (yatirilacakMiktar > ben.getKrediKartiBorc()) { //kullanıcı hem borcu kapatıp hem de limit arttırmak isterse veya borcu olmadan limit arttırmak isterse
            double limitArttir = yatirilacakMiktar - ben.getKrediKartiBorc();
            paraCekDataBase(ben, yatirilacakMiktar);
            krediLimitArttır(ben, limitArttir);
        } else if (paraCekKontrol(ben, yatirilacakMiktar) && control > 0) { //hesaptaki para ile kredi kartı borcu ödüyoruz, eşit değilse 0 dememin sebebi üstteki şart sağlanırsa alttakinin çalışmasına gerek kalmayacak
            paraCekDataBase(ben, yatirilacakMiktar); //banka hesabından para çekildi
            customerManager.bakiyeGuncelle(
                    ben,
                    ben.getHesapBakiye(),
                    ben.getKrediKartiLimit(),
                    ben.getKrediKartiBorc() - yatirilacakMiktar,
                    ben.getAbdDolari(),
                    ben.getEuro(),
                    ben.getIngilizSterlini(),
                    ben.getKuveytDinari(),
                    ben.getAltin(),
                    ben.getTelefon(),
                    ben.getIban()); //database kredilimit true
            ben.setKrediKartiBorc(ben.getKrediKartiBorc() - yatirilacakMiktar);
        }

    }

    public void krediLimitArttır(CustomerManager ben, double limitArttir) {
        customerManager.bakiyeGuncelle(
                ben,
                ben.getHesapBakiye(),
                ben.getKrediKartiLimit() + limitArttir, 0,
                ben.getAbdDolari(),
                ben.getEuro(),
                ben.getIngilizSterlini(),
                ben.getKuveytDinari(),
                ben.getAltin(),
                ben.getTelefon(),
                ben.getIban()); //database kredilimit true

        ben.setKrediKartiLimit(ben.getKrediKartiLimit() + limitArttir); //arrylist limit
        ben.setKrediKartiBorc(0); //arrylist borc
    }



}

