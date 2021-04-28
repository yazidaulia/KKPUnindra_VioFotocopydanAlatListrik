package Form;
public class UserID {
    private static String id;
    
    public static void setUserLogin(String kode){
        id = kode;
    }
    public static String getUserLogin(){
        return id;
    }
}
