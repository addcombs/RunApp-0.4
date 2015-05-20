package infinity.runapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import infinity.runapp.MainActivity;
import infinity.runapp.R;
import infinity.runapp.getsets.ActiveUser;
import infinity.runapp.getsets.AssignedWorkouts;
import infinity.runapp.getsets.History;
import infinity.runapp.library.InfinityDBHandler;

/**
 * Created by ADC on 2/9/2015.
 */

public class HomeFragment extends Fragment
{
    ActiveUser myActiveUser;
    TextView mWelcome;
    ListView mHistoryView;
    ListView mWorkoutsView;
    ListView mMessagesView;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_layout,container,false);

        // get User Info
        String fname = getUserFName();
        // set Welcome message on Home Fragment
        mWelcome = (TextView) v.findViewById(R.id.welcome);
        mHistoryView = (ListView) v.findViewById(R.id.recentHistory);
        mWorkoutsView = (ListView) v.findViewById(R.id.recentWorkouts);
//        mMessagesView = (ListView) v.findViewById(R.id.newMessages);

        mWelcome.setText("Welcome, " + fname);

        getHistory();
//        getMessages();
        getUpcoming();
        return v;
    }

    public String getUserFName(){
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        myActiveUser = dbHandler.setUser();
        return String.valueOf(myActiveUser.getFname());
    }

    public void getHistory(){
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        myActiveUser = dbHandler.setUser();
        Integer userID = myActiveUser.getUserID();

        History [] newHistory = dbHandler.history(userID, 3);

        ArrayAdapter<History> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_items, R.id.list_item, newHistory);

        mHistoryView.setAdapter(adapter);
        mHistoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).goHistory(v);
            }
        });
    }

//    public void getMessages(){
//        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);
//
//        myActiveUser = dbHandler.setUser();
//
//        Integer userID = myActiveUser.getUserID();
//
//        RecentMessages[] newMessages = dbHandler.messages(userID, 10);
//
//        ArrayAdapter<RecentMessages> adapter = new ArrayAdapter<>(getActivity(),
//                R.layout.list_items, R.id.list_item, newMessages);
//
//        mMessagesView.setAdapter(adapter);
//        mMessagesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ((MainActivity)getActivity()).goMessages(v);
//            }
//        });
//
//    }

    public void getUpcoming(){
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        myActiveUser = dbHandler.setUser();

        Integer userID = myActiveUser.getUserID();

        AssignedWorkouts[] newWorkouts = dbHandler.workouts(userID, 3);

        ArrayAdapter<AssignedWorkouts> adapter = new ArrayAdapter<AssignedWorkouts>(getActivity(),
                R.layout.list_items, R.id.list_item, newWorkouts);

        mWorkoutsView.setAdapter(adapter);
        mWorkoutsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).goWorkouts(v);
            }
        });
    }


}
