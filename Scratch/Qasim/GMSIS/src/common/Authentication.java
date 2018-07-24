package common;

public class Authentication {

    protected String username;
    protected String password;
    protected String U_ID;
    protected UserRegistry DB;

    public void main(String[] args) {
        DB = new UserRegistry();

    }
    public String login(String username, String password) {
        boolean flag = false;
        int checkdigit=UserRegistry.getInstance().checkUser(username, password);
        switch (checkdigit) {
            case 1:             
                return username+"-"+password+"-"+Integer.toString(checkdigit);
            case 0:
                System.out.println("incorrect password or username");
                break;
            default:
              return username+"-"+password+"-"+Integer.toString(checkdigit);
        }
        return "false";
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
