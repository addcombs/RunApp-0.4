package infinity.runapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import infinity.runapp.MainActivity;
import infinity.runapp.R;
import infinity.runapp.library.InfinityDBHandler;

/**
 * Created by ADC on 2/9/2015.
 */
public class AssignWorkoutFragment extends Fragment
{
    Spinner groupSpinner;
    String workoutName;
    Integer userID;
    ArrayList<String> spinnerArray;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.assign_workout,container,false);
        groupSpinner = (Spinner) v.findViewById(R.id.groupSpinner);

        InfinityDBHandler db = new InfinityDBHandler(getActivity(), null, null, 1);
        Bundle bundle = this.getArguments();

        userID = ((MainActivity)getActivity()).getUserID();
        workoutName = bundle.getString("workoutName");

        ArrayList<String> groups = db.groupAdmin(userID);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_items, R.id.list_item, groups);

        groupSpinner.setAdapter(adapter);

        return v;
    }
}