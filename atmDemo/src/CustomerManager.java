import java.sql.*;
import java.util.ArrayList;

public class CustomerManager {
    private String musteriNo;
    private String tc;
    private String sifre;
    private double hesapBakiye;
    private double krediKartiBorc;
    private double krediKartiLimit;
    private double abdDolari;
    private double euro;
    private double ingilizSterlini;
    private double kuveytDinari;
    private double altin;
    private String telefon;
    private String iban;


    public CustomerManager(String musteriNo, String tc, String sifre, double hesapBakiye, double krediKartiBorc, double krediKartiLimit, double abdDolari, double euro, double ingilizSterlini, double kuveytDinari, double altin, String telefon, String iban) {
        this.setMusteriNo(musteriNo);
        this.setTc(tc);
        this.setSifre(sifre);
        this.setHesapBakiye(hesapBakiye);
        this.setKrediKartiBorc(krediKartiBorc);
        this.setKrediKartiLimit(krediKartiLimit);
        this.setAbdDolari(abdDolari);
        this.setEuro(euro);
        this.setIngilizSterlini(ingilizSterlini);
        this.setKuveytDinari(kuveytDinari);
        this.setAltin(altin);
        this.setTelefon(telefon);
        this.setIban(iban);

    }

    public CustomerManager() {

    }

    public ArrayList<CustomerManager> getData() throws SQLException {
        // mian deki veri çekme yeri olucak
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement = null; //SQL cümlelerimiz ile ilgili işlemleri yapacak.
        ResultSet resultSet; //sql kontrolünde gelen sonuç
        try {
            connection = helper.getConnection();
            statement = connection.createStatement(); //bu statament'ı elimdeki bağlantıya oluşturacağım birden fazla database ile çalışırken gerekli
            resultSet = statement.executeQuery("SELECT musteriNo,TC,Sifre,HesapBakiye,KrediKartiBorc,KrediKartiLimit,ABDDolari,Euro,IngilizSterlini,KuveytDinari,Altin,Telefon,Iban FROM atm_bank_customer_data.customer_data;"); //statement çalıştırır resultset e aktarır.

            ArrayList<CustomerManager> customers = new ArrayList<CustomerManager>();

            while (resultSet.next()) {
                //tek tek geziyoruz
                //System.out.println(resultSet.getString("TC")); TC leri ekrana basar
                customers.add(new CustomerManager(
                        resultSet.getString("musteriNo"),
                        resultSet.getString("TC"),
                        resultSet.getString("Sifre"),
                        resultSet.getDouble("HesapBakiye"), //verileri arraylistemize ekliyoruz.
                        resultSet.getDouble("KrediKartiBorc"),
                        resultSet.getDouble("KrediKartiLimit"),
                        resultSet.getDouble("ABDDolari"),
                        resultSet.getDouble("Euro"),
                        resultSet.getDouble("IngilizSterlini"),
                        resultSet.getDouble("KuveytDinari"),
                        resultSet.getDouble("Altin"),
                        resultSet.getString("Telefon"),
                        resultSet.getString("Iban")));
            }
            return customers;

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

        return null;
    }


    public ArrayList<CustomerManager> getArrayList() {
        ArrayList<CustomerManager> customers = null;
        try {
            customers = getData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    //TEST İÇİN KOYULDU ÇALIŞTIRILIP BURAYA ALINACAK
    public void bakiyeGuncelle(
            CustomerManager customer,
            double bakiye,
            double krediKartiLimit,
            double krediKartiBorc,
            double abdDolari,
            double euro,
            double ingilizSterlini,
            double kuveytDinari,
            double altin,
            String telefon,
            String iban)
    {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null; //Prepared geldi onu dinle tekrar "insert te"
        ResultSet resultSet; //sql kontrolünde gelen sonuç
        try {
            String sql = null;
            connection = helper.getConnection();

            sql = "update atm_bank_customer_data.customer_data set HesapBakiye=" + bakiye +
                    ", KrediKartiBorc= " + krediKartiBorc +
                    ", KrediKartiLimit= " + krediKartiLimit +
                    ", ABDDolari= " + abdDolari +
                    ", euro= " + euro +
                    ", IngilizSterlini= " + ingilizSterlini +
                    ", KuveytDinari= " + kuveytDinari +
                    ", Altin= " + altin +
                    ", Telefon= " + telefon +
                    ", Iban= " + iban +
                    " where musteriNo= " + customer.getMusteriNo();


            statement = connection.prepareStatement(sql); //bu statament'ı elimdeki bağlantıya oluşturacağım birden fazla database ile çalışırken gerekli
            resultSet = statement.executeQuery("SELECT musteriNo,TC,Sifre,HesapBakiye,ABDDolari,Euro,IngilizSterlini, KuveytDinari, Altin, Telefon, Iban FROM atm_bank_customer_data.customer_data;"); //statement çalıştırır resultset e aktarır.

            int result = statement.executeUpdate();
            System.out.println("İşlem Tamamlandı");

        } catch (
                SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void getInfoCustomers(ArrayList<CustomerManager> customers, int git) {
        System.out.println("Müşteri No: " + customers.get(git).getMusteriNo());
        System.out.println("TC: " + customers.get(git).getTc());
        System.out.println("Şifre: " + customers.get(git).getSifre());
        System.out.println("Hesap Bakiyesi: " + customers.get(git).getHesapBakiye());
        System.out.println("Kredi Kartı Borcu: " + customers.get(git).getKrediKartiBorc());
        System.out.println("Kredi Kartı Limiti: " + customers.get(git).getKrediKartiLimit());
        System.out.println("ABD Doları: " + customers.get(git).getAbdDolari());
        System.out.println("Euro: " + customers.get(git).getEuro());
        System.out.println("İngiliz Sterlini: " + customers.get(git).getIngilizSterlini());
        System.out.println("Kuveyt Dinarı: " + customers.get(git).getKuveytDinari());
        System.out.println("Altın: " + customers.get(0).getAltin());
        System.out.println("Telefon: " + customers.get(0).getTelefon());
        System.out.println("IBAN: "+customers.get(0).getIban());


    }


    public String getMusteriNo() {
        return musteriNo;
    }

    public void setMusteriNo(String musteriNo) {
        this.musteriNo = musteriNo;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public double getHesapBakiye() {
        return hesapBakiye;
    }

    public void setHesapBakiye(double hesapBakiye) {
        this.hesapBakiye = hesapBakiye;
    }

    public double getKrediKartiBorc() {
        return krediKartiBorc;
    }

    public void setKrediKartiBorc(double krediKartiBorc) {
        this.krediKartiBorc = krediKartiBorc;
    }

    public double getKrediKartiLimit() {
        return krediKartiLimit;
    }

    public void setKrediKartiLimit(double krediKartiLimit) {
        this.krediKartiLimit = krediKartiLimit;
    }

    public double getAbdDolari() {
        return abdDolari;
    }

    public void setAbdDolari(double abdDolari) {
        this.abdDolari = abdDolari;
    }

    public double getEuro() {
        return euro;
    }

    public void setEuro(double euro) {
        this.euro = euro;
    }

    public double getIngilizSterlini() {
        return ingilizSterlini;
    }

    public void setIngilizSterlini(double ingilizSterlini) {
        this.ingilizSterlini = ingilizSterlini;
    }

    public double getKuveytDinari() {
        return kuveytDinari;
    }

    public void setKuveytDinari(double kuveytDinari) {
        this.kuveytDinari = kuveytDinari;
    }

    public double getAltin() {
        return altin;
    }

    public void setAltin(double altin) {
        this.altin = altin;
    }


    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
