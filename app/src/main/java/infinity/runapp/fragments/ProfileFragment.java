package infinity.runapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import infinity.runapp.library.InfinityDBHandler;
import infinity.runapp.R;
import infinity.runapp.getsets.ActiveUser;

/**
 * Created by ADC on 2/9/2015.
 */
public class ProfileFragment extends Fragment
{
    TextView mName;
    TextView mEmail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.profile_layout,container,false);
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        ActiveUser myActiveUser = dbHandler.setUser();

        String fname = String.valueOf(myActiveUser.getFname());
        String lname = String.valueOf(myActiveUser.getLname());
        String email = String.valueOf(myActiveUser.getEmail()).toLowerCase();

        mName = (TextView)v.findViewById(R.id.name);
        mEmail = (TextView)v.findViewById(R.id.email);

        mName.setText(fname + " " + lname );
        mEmail.setText(email);

        return v;
    }

}
