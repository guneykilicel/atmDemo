import java.util.ArrayList;

public abstract class Hesaplar{
    /*
    CustomerManager customerManager = new CustomerManager();
    ArrayList<CustomerManager> customers = customerManager.getArrayList();

     */

    //public abstract void getInfo();


    //---------------------------PARA YATIR------------------------------
    public abstract void paraCek(ArrayList<CustomerManager> customers);
    public abstract void paraYatir(ArrayList<CustomerManager> customers);
    public abstract void paraCekDataBase(CustomerManager ben, double cekMiktar);
    public abstract void krediBorcOde(ArrayList<CustomerManager> customers);

    public CustomerManager kullaniciBul(ArrayList<CustomerManager> customers, String tc) {
        CustomerManager customerDeneme = null;
        for (CustomerManager user : customers) {
            if (user.getTc().equals(tc)) {
                customerDeneme = user;
            }
        }
        return customerDeneme;
    }

    public boolean kullaniciAra(ArrayList<CustomerManager> customers, String tc){
        boolean boolKullaniciAra=false;
        for (CustomerManager user : customers) {
            if (user.getTc().equals(tc)) {
                boolKullaniciAra = true;
            }
        }
        return boolKullaniciAra;
    }

    public boolean paraCekKontrol(CustomerManager ben, double cekMiktar) {
        if (ben.getHesapBakiye() >= cekMiktar) {
            return true;
        } else {
            return false;
        }
    }
}
