package infinity.runapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import infinity.runapp.R;
import infinity.runapp.getsets.ActiveUser;
import infinity.runapp.library.InfinityDBHandler;

/**
 * Created by ADC on 2/9/2015.
 */
public class WorkoutsFragment extends Fragment
{
    private ListView mListView;

    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.workouts_layout,container,false);

        showWorkouts();

        return v;
    }

    public String getUserID(){
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        ActiveUser myActiveUser = dbHandler.setUser();

        return String.valueOf(myActiveUser.getUserID());
    }

    public void showWorkouts(){
        mListView = (ListView) v.findViewById(R.id.workoutList);
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        ActiveUser myActiveUser = dbHandler.setUser();

        Integer userID = myActiveUser.getUserID();

        ArrayList<String> workouts = dbHandler.workoutList(userID);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_items, R.id.list_item, workouts);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String workoutName = (String) mListView.getItemAtPosition(position);

                Fragment workoutDetails = new WorkoutDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("workoutName", workoutName);
                workoutDetails.setArguments(bundle);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, workoutDetails)
                        .commit();
            }
        });
    }

}
