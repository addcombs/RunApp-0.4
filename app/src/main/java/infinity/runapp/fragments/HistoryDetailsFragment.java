package infinity.runapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import infinity.runapp.R;
import infinity.runapp.library.InfinityDBHandler;

/**
 * Created by adc on 3/23/15.
 */
public class HistoryDetailsFragment extends Fragment {

    private Integer historyID;
    private TextView mDate;
    private TextView mTime;
    private TextView mDistance;
    private TextView mSpeed;

    DecimalFormat df = new DecimalFormat("#.00");

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.history_details_layout, container, false);
        Bundle bundle = this.getArguments();

        mDate = (TextView)v.findViewById(R.id.date);
        mTime = (TextView)v.findViewById(R.id.time);
        mDistance = (TextView)v.findViewById(R.id.distance);
        mSpeed = (TextView)v.findViewById(R.id.speed);

        historyID = bundle.getInt("historyID");

        showHistoryDetails();

        return v;
    }

    public void showHistoryDetails() {
        InfinityDBHandler db = new InfinityDBHandler(getActivity(), null, null, 1);

        ArrayList<String> historyDetails = db.historyDetails(historyID);

        Float distance = Float.parseFloat(historyDetails.get(1));
        Integer time = Integer.parseInt(historyDetails.get(2));
        Float speed = distance/time;

        mDate.setText(historyDetails.get(0));
        mTime.setText(time.toString());
        mDistance.setText(df.format(distance));
        mSpeed.setText(df.format(speed));
    }



}
