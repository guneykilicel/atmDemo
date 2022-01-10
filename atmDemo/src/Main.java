import java.sql.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {



        //Scanner scanner = new Scanner(System.in);
        CustomerManager customerManager= new CustomerManager();
        CustomerFonksiyonlari manager = new CustomerFonksiyonlari();

        //ArrayList<CustomerManager> customers = customerManager.getData();
        ArrayList<CustomerManager> customers = customerManager.getArrayList(); //verileri 'CustomerManager da' çektik ve arraylist döndürüp bu arrayliste atadık. try cath yapıldı

        //customerManager.getInfoCustomers(customers,0);
        //-------------İşlem Menüsü-----------------
        manager.basla(customers);


    }


}
