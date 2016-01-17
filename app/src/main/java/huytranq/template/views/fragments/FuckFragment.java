package huytranq.template.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import huytranq.template.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuckFragment extends BaseFragment {


    public FuckFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("debug" , "Fuck is recreated");
        return inflater.inflate(R.layout.fragment_fuck, container, false);
    }

}
