package infinity.runapp.getsets;

/**
 * Created by ADC on 3/27/2015.
 */
public class RecentMessages {
    private Integer mMessageID;
    private Integer mSender;
    private String mSenderName;
    private Integer mReceiver;
    private String mSubject;
    private String mMessage;
    private String mSendingDate;
    private Integer mIsRead;
    private Integer mIsDeleted;
    private Integer mGroupRequest;

    public RecentMessages(){

    }

    public RecentMessages(Integer messageID, Integer sender, Integer receiver, String subject, String message, String sendingDate, Integer isRead, Integer isDeleted, Integer groupRequest)
    {
        mMessageID = messageID;
        mSender = sender;
        mReceiver = receiver;
        mSubject = subject;
        mMessage = message;
        mSendingDate = sendingDate;
        mIsRead = isRead;
        mIsDeleted = isDeleted;
        mGroupRequest = groupRequest;

    }

    public Integer getmMessageID() {
        return mMessageID;
    }

    public void setmMessageID(Integer mMessageID) {
        this.mMessageID = mMessageID;
    }

    public Integer getmSender() {
        return mSender;
    }

    public void setmSender(Integer mSender) {
        this.mSender = mSender;
    }

    public Integer getmReceiver() {
        return mReceiver;
    }

    public void setmReceiver(Integer mReceiver) {
        this.mReceiver = mReceiver;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmSendingDate() {
        return mSendingDate;
    }

    public void setmSendingDate(String mSendingDate) {
        this.mSendingDate = mSendingDate;
    }

    public Integer getmIsRead() {
        return mIsRead;
    }

    public void setmIsRead(Integer mIsRead) {
        this.mIsRead = mIsRead;
    }

    public Integer getmIsDeleted() {
        return mIsDeleted;
    }

    public void setmIsDeleted(Integer mIsDeleted) {
        this.mIsDeleted = mIsDeleted;
    }

    public Integer getmGroupRequest() {
        return mGroupRequest;
    }

    public void setmGroupRequest(Integer mGroupRequest) {
        this.mGroupRequest = mGroupRequest;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    @Override
    public String toString(){
        return mSender + "\t" + mSubject + "\t" + mSendingDate;
    }
}
