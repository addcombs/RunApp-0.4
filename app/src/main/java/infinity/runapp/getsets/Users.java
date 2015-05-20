package infinity.runapp.getsets;

/**
 * Created by ADC on 3/27/2015.
 */
public class Users {
    private Integer mUserID;
    private String mEmail;
    private String mFname;
    private String mLname;
    private String mPic;

    public Users(){

    }

    public Users(Integer userID, String userEmail, String fName, String lName, String userPic)
    {
        mUserID = userID;
        mEmail = userEmail;
        mFname = fName;
        mLname = lName;
        mPic = userPic;
    }

    public Integer getUserID(){
        return mUserID;
    }

    public void setUserID(Integer userID){
        mUserID = userID;
    }

    public String getEmail(){
        return mEmail;
    }

    public void setEmail(String email){
        mEmail = email;
    }

    public String getFname() {
        return mFname;
    }

    public void setFname(String mFname) {
        this.mFname = mFname;
    }

    public String getLname() {
        return mLname;
    }

    public void setLname(String mLname) {
        this.mLname = mLname;
    }

    public String getmPic() { return mPic; }

    public void setmPic(String mPic) { this.mPic = mPic; }
}
