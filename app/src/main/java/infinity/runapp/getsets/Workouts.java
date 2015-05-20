package infinity.runapp.getsets;

/**
 * Created by ADC on 3/27/2015.
 */
public class Workouts {
    private Integer mWorkoutID;
    private Double mDistance;
    private Integer mCreatedBy;
    private String mWorkoutName;
    private String mExprDate;

    public Workouts(){

    }

    public Workouts(Integer workoutID, Double distance, Integer createdBy, String workoutName)
    {
        mWorkoutID = workoutID;
        mDistance = distance;
        mCreatedBy = createdBy;
        mWorkoutName = workoutName;

    }

    public Workouts(String workoutName){
        mWorkoutName = workoutName;
    }

    public Integer getmWorkoutID() {
        return mWorkoutID;
    }

    public void setmWorkoutID(Integer mWorkoutID) {
        this.mWorkoutID = mWorkoutID;
    }

    public Double getmDistance() {
        return mDistance;
    }

    public void setmDistance(Double mDistance) {
        this.mDistance = mDistance;
    }

    public Integer getmCreatedBy() {
        return mCreatedBy;
    }

    public void setmCreatedBy(Integer mCreatedBy) {
        this.mCreatedBy = mCreatedBy;
    }

    public String getmWorkoutName() {
        return mWorkoutName;
    }

    public void setmWorkoutName(String mWorkoutName) {
        this.mWorkoutName = mWorkoutName;
    }

    @Override
    public String toString(){
        return mWorkoutName + " - " + mDistance + " meters";
    }
}
