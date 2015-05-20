package infinity.runapp.getsets;

/**
 * Created by ADC on 3/27/2015.
 */
public class Group {
    private Integer mGroupID;
    private String mGroupName;
    private String mTagline;
    private String mBulletin;
    private Integer mIsClosed;
    private Integer mCreatedBy;

    public Group(){

    }

    public Group(Integer groupID, String groupName, String tagline, String bulletin, Integer isClosed, Integer createdBy)
    {
        mGroupID = groupID;
        mGroupName = groupName;
        mTagline = tagline;
        mBulletin = bulletin;
        mIsClosed = isClosed;
        mCreatedBy = createdBy;
    }

    public Group(Integer groupID, String groupName){
        mGroupID = groupID;
        mGroupName = groupName;
    }

    public Group(String groupName){
        mGroupName = groupName;
    }

    public Integer getmGroupID() {
        return mGroupID;
    }

    public void setmGroupID(Integer mGroupID) {
        this.mGroupID = mGroupID;
    }

    public String getmGroupName() {
        return mGroupName;
    }

    public void setmGroupName(String mGroupName) {
        this.mGroupName = mGroupName;
    }

    public String getmTagline() {
        return mTagline;
    }

    public void setmTagline(String mTagline) {
        this.mTagline = mTagline;
    }

    public String getmBulletin() {
        return mBulletin;
    }

    public void setmBulletin(String mBulletin) {
        this.mBulletin = mBulletin;
    }

    public Integer getmIsClosed() {
        return mIsClosed;
    }

    public void setmIsClosed(Integer mIsClosed) {
        this.mIsClosed = mIsClosed;
    }

    @Override
    public String toString(){
        return mGroupName;
    }

    public Integer getmCreatedBy() {
        return mCreatedBy;
    }

    public void setmCreatedBy(Integer mCreatedBy) {
        this.mCreatedBy = mCreatedBy;
    }
}
