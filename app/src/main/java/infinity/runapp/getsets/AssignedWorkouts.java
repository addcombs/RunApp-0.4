package infinity.runapp.getsets;

/**
 * Created by ADC on 3/31/2015.
 */
public class AssignedWorkouts {
    private Integer mWorkoutID;
    private String mWorkoutName;
    private String mExprDate;
    private Double mDistance;
    private Integer mUserID;

    public AssignedWorkouts(){

    }

    public AssignedWorkouts(Integer workoutID, String workoutName, Double distance, String exprDate){
        mWorkoutID = workoutID;
        mWorkoutName = workoutName;
        mDistance = distance;
        mExprDate = exprDate;
    }

    public AssignedWorkouts(Integer workoutID, Integer userID, String exprDate){
        mWorkoutID = workoutID;
        mUserID = userID;
        mExprDate = exprDate;
    }


    public Integer getmWorkoutID() {
        return mWorkoutID;
    }

    public void setmWorkoutID(Integer mWorkoutID) {
        this.mWorkoutID = mWorkoutID;
    }

    public String getmExprDate() {
        return mExprDate;
    }

    public void setmExprDate(String mExprDate) {
        this.mExprDate = mExprDate;
    }

    public Double getmDistance() {
        return mDistance;
    }

    public void setmDistance(Double mDistance) {
        this.mDistance = mDistance;
    }

    public String getmWorkoutName() {
        return mWorkoutName;
    }

    public void setmWorkoutName(String mWorktoutName) {
        this.mWorkoutName = mWorktoutName;
    }

    public Integer getmUserID() {
        return mUserID;
    }

    public void setmUserID(Integer mUserID) {
        this.mUserID = mUserID;
    }

    @Override
    public String toString(){
        return mWorkoutName + "\t" + " Expires: " + mExprDate;
    }
}
