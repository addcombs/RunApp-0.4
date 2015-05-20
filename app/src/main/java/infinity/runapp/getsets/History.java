package infinity.runapp.getsets;

/**
 * Created by ADC on 3/27/2015.
 */
public class History {
    private Integer mHistoryID;
    private String mDateRan;
    private Integer mTimeRan;
    private Double mDistance;
    private Integer mRanBy;
    private Integer mWorkoutID;

    public History(){

    }

    public History(Integer historyID, String dateRan, Integer timeRan, Double distance, Integer ranBy, Integer workoutID)
    {
        mHistoryID = historyID;
        mDateRan = dateRan;
        mTimeRan = timeRan;
        mDistance = distance;
        mRanBy = ranBy;
        mWorkoutID = workoutID;
    }

    public Integer getmHistoryID() {
        return mHistoryID;
    }

    public void setmHistoryID(Integer mHistoryID) {
        this.mHistoryID = mHistoryID;
    }

    public String getmDateRan() {
        return mDateRan;
    }

    public void setmDateRan(String mDateRan) {
        this.mDateRan = mDateRan;
    }

    public Integer getmTimeRan() {
        return mTimeRan;
    }

    public void setmTimeRan(Integer mTimeRan) {
        this.mTimeRan = mTimeRan;
    }

    public Integer getmRanBy() {
        return mRanBy;
    }

    public void setmRanBy(Integer mRanBy) {
        this.mRanBy = mRanBy;
    }

    public Double getDistance() {
        return mDistance;
    }

    public void setDistance(Double distance) {
        mDistance = distance;
    }

    public Integer getWorkoutID() {
        return mWorkoutID;
    }

    public void setWorkoutID(Integer workoutID) {
        mWorkoutID = workoutID;
    }

    @Override
    public String toString(){
        return mDistance + " meters\t\t\t" + mDateRan;
    }
}
