package common;

public class Authentication {

    protected String username;
    protected String password;
    protected String U_ID;
    protected UserRegistry DB;

    public void main(String[] args) {
        DB = new UserRegistry();

    }
    public boolean login(String username, String password) {
        boolean flag = false;
        int checkdigit=UserRegistry.getInstance().checkUser(username, password);
        switch (checkdigit) {
            case 1:
                System.out.print("welcome admin");
                flag=true;
                break;
            case 0:
                System.out.println("incorrect password or username");
                break;
            default:
                System.out.println("welcome Staff user");
                flag=true;
                break;
        }
        return flag;
    }
public void addUser(String Username,String Surname, String Password,boolean admin)
{
UserRegistry.getInstance().addUser(Username,Surname,Password,admin);
}
public void deleteUser(int id){
   UserRegistry.getInstance().deleteUser(id);
   }  
public void updatePassword(String username,String password, String newPassword){
UserRegistry.getInstance().updatePassword(username,password,newPassword);
}
        }
