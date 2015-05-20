package infinity.runapp.getsets;

/**
 * Created by ADC on 3/27/2015.
 */
public class GroupDetails {
    private Integer mGroupID;
    private Integer mUserID;
    private Integer mHasAlerts;
    private Integer mIsAdmin;

    public GroupDetails(){

    }

    public GroupDetails(Integer groupID, Integer userID, Integer hasAlerts, Integer isAdmin)
    {
        mGroupID = groupID;
        mUserID = userID;
        mHasAlerts = hasAlerts;
        mIsAdmin = isAdmin;
    }


    public Integer getGroupID() {
        return mGroupID;
    }

    public void setGroupID(Integer groupID) {
        mGroupID = groupID;
    }

    public Integer getUserID() {
        return mUserID;
    }

    public void setUserID(Integer userID) {
        mUserID = userID;
    }

    public Integer getHasAlerts() {
        return mHasAlerts;
    }

    public void setHasAlerts(Integer hasAlerts) {
        mHasAlerts = hasAlerts;
    }

    public Integer getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        mIsAdmin = isAdmin;
    }
}
