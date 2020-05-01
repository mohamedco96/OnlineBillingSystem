package Database_Tables;

import java.util.Vector;



public class Users 
{
    private int uId;
    private String name;
    private String nid;
    private String dialNumber;
    private String address;
    private String email;
    private String profile;
    private boolean otf;
    private boolean rs;
    private Vector<Users> users;
    
    
    public Users(String name, String nid, String dialNumber, String address, String email, String profile) 
    {
        this.name = name;
        this.nid = nid;
        this.dialNumber = dialNumber;
        this.address = address;
        this.email = email;
        this.profile = profile;
    }     
    
    
     public Users(int uId,String name, String nid, String dialNumber, String address, String email, String profile) 
    {
        this.uId = uId;
        this.name = name;
        this.nid = nid;
        this.dialNumber = dialNumber;
        this.address = address;
        this.email = email;
        this.profile = profile;
    } 
    
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getDialNumber() {
        return dialNumber;
    }

    public void setDialNumber(String dialNumber) {
        this.dialNumber = dialNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isOtf() {
        return otf;
    }

    public void setOtf(boolean otf) {
        this.otf = otf;
    }

    public boolean isRs() {
        return rs;
    }

    public void setRs(boolean rs) {
        this.rs = rs;
    }

    public Vector<Users> getUsers() {
        return users;
    }

    public void setUsers(Vector<Users> users) {
        this.users = users;
    }
    
    public Users()
    {
        users = new Vector();
    }
    
    public Users(int uId)
    {
        this.uId = uId;
    }    
    
   
    
    public Users(Vector<Users> users) 
    {
        this.users = users;
    }

     
    
    
    
    
    
    
   

   
}
