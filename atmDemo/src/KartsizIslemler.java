import java.util.ArrayList;
import java.util.Scanner;

public class KartsizIslemler implements IOdemeler{

    Scanner scanner = new Scanner(System.in);
    CustomerManager customerManager = new CustomerManager();

    public void basla(ArrayList<CustomerManager> customers){
        System.out.println("1: TC ile yatır");
        System.out.println("2: IBAN ile yatır");
        System.out.println("3: Telefon ile yatır");
        int secKonut = scanner.nextInt();
//1: TC 2:IBAN 3:Telefon
        if (secKonut ==1){
            paraYatirTC(customers);
        } else if (secKonut == 2){
            paraYatirIBAN(customers);
        } else if (secKonut ==3){
            paraYatirTelefon(customers);
        } else {
            System.out.println("Hatalı giriş!");
        }
    }
    @Override
    public void konutKirasi(ArrayList<CustomerManager> customers) {
        basla(customers);
    }

    @Override
    public void isYeriKirasi(ArrayList<CustomerManager> customers) {
        basla(customers);
    }

    @Override
    public void digerKiralar(ArrayList<CustomerManager> customers) {
        basla(customers);
    }

    @Override
    public void eTicaretOdemesi(ArrayList<CustomerManager> customers) {
        basla(customers);
    }

    @Override
    public void egitimOdemeleri(ArrayList<CustomerManager> customers) {
        System.out.println("Eğitim ödemeniz bulunamadı.");
    }

    @Override
    public void digerOdemeler(ArrayList<CustomerManager> customers) {
        basla(customers);
    }

    private void paraYatirTC(ArrayList<CustomerManager> customers) {
        System.out.println("Alıcının TC Kimlik No'sunu giriniz: ");
        String tc = scanner.next();
        if ((kullaniciAraTC(customers, tc))) {//kullanici var mı yok mu onu kontrol eder
            CustomerManager customer = kullaniciBulTC(customers, tc);
            System.out.println("Yatirilacak miktarı giriniz: ");
            double yatirilacakMiktar = scanner.nextDouble();
            paraYatirDataBase(customer, yatirilacakMiktar); //manager1 parayı alan hesap, customers.get(0) biziz
        } else {
            System.out.println("Kullanıcı bulunamadı!");
        }
    }

    private void paraYatirIBAN(ArrayList<CustomerManager> customers) {
        System.out.println("Alıcının IBAN'ını giriniz: ");
        System.out.print("TR");
        String iban = scanner.next();
        if ((kullaniciAraIBAN(customers, iban))) {//kullanici var mı yok mu onu kontrol eder
            CustomerManager manager = kullaniciBulIBAN(customers, iban);
            System.out.println("Yatirilacak miktarı giriniz: ");
            double yatirilacakMiktar = scanner.nextDouble();
            paraYatirDataBase(manager, yatirilacakMiktar); //manager1 parayı alan hesap, customers.get(0) biziz
        } else {
            System.out.println("Kullanıcı bulunamadı!");
        }
    }



    private void paraYatirTelefon(ArrayList<CustomerManager> customers) {
        System.out.println("Alıcının Telefon'unu giriniz: ");
        String telefon = scanner.next();
        if ((kullaniciAraTelefon(customers, telefon))) {//kullanici var mı yok mu onu kontrol eder
            CustomerManager manager = kullaniciBulTelefon(customers, telefon);
            System.out.println("Yatirilacak miktarı giriniz: ");
            double yatirilacakMiktar = scanner.nextDouble();
            paraYatirDataBase(manager, yatirilacakMiktar); //manager1 parayı alan hesap, customers.get(0) biziz
        } else {
            System.out.println("Kullanıcı bulunamadı!");
        }
    }



    private CustomerManager kullaniciBulTC(ArrayList<CustomerManager> customers, String tc) {
        CustomerManager customerDeneme = null;
        for (CustomerManager user : customers) {
            if (user.getTc().equals(tc)) {
                customerDeneme = user;
            }
        }
        return customerDeneme;
    }

    private CustomerManager kullaniciBulIBAN(ArrayList<CustomerManager> customers, String iban) {
        CustomerManager customerDeneme = null;
        for (CustomerManager user : customers) {
            if (user.getIban().equals(iban)) {
                customerDeneme = user;
            }
        }
        return customerDeneme;
    }



    private CustomerManager kullaniciBulTelefon(ArrayList<CustomerManager> customers, String telefon) {
        CustomerManager customerDeneme = null;
        for (CustomerManager user : customers) {
            if (user.getTelefon().equals(telefon)) {
                customerDeneme = user;
            }
        }
        return customerDeneme;
    }



    private boolean kullaniciAraTC(ArrayList<CustomerManager> customers, String tc){
        boolean boolKullaniciAra=false;
        for (CustomerManager user : customers) {
            if (user.getTc().equals(tc)) {
                boolKullaniciAra = true;
            }
        }
        return boolKullaniciAra;
    }

    private boolean kullaniciAraIBAN(ArrayList<CustomerManager> customers, String iban){
        boolean boolKullaniciAra=false;
        for (CustomerManager user : customers) {
            if (user.getIban().equals(iban)) {
                boolKullaniciAra = true;
            }
        }
        return boolKullaniciAra;
    }



    private boolean kullaniciAraTelefon(ArrayList<CustomerManager> customers, String telefon){
        boolean boolKullaniciAra=false;
        for (CustomerManager user : customers) {
            if (user.getTelefon().equals(telefon)) {
                boolKullaniciAra = true;
            }
        }
        return boolKullaniciAra;
    }



    private void paraYatirDataBase(CustomerManager customer, double yatirilacakMiktar) { //bundan iki tane olucak biri kartsız bu kartsız işlem diğeri de kartlı işlem onda bizim hesabımızdan bakiye de düşecek.
        //customer parayı alan
        //customer1 para giden hesap kart ile girdik yani customer1 biziz.
        System.out.println("---------------------------");
        System.out.println("Parayı hazneye yerleştiriniz.");

        customerManager.bakiyeGuncelle(
                customer,
                customer.getHesapBakiye() + yatirilacakMiktar,
                customer.getKrediKartiLimit(),
                customer.getKrediKartiBorc(),
                customer.getAbdDolari(),
                customer.getEuro(),
                customer.getIngilizSterlini(),
                customer.getKuveytDinari(),
                customer.getAltin(),
                customer.getTelefon(),
                customer.getIban());

        customer.setHesapBakiye(customer.getHesapBakiye() + yatirilacakMiktar); //arraylist

    }
}
