package huytranq.template.views.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.HashMap;

import huytranq.template.R;
import huytranq.template.presenters.utilities.Resource;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private String title;

    public static HashMap<Integer , BaseFragment> mapFragment(Context context) {
        HashMap<Integer , BaseFragment> result = new HashMap<>();
        String[] tags = Resource.from(context).getStringArray(Resource.FRAGMENT_TAGS);
        for (int i = 0; i < tags.length; ++i) {
            int id = Resource.from(context).getResourceId(tags[i] , Resource.Type.ID);
            String title = Resource.from(context).getString(tags[i]);
            BaseFragment fragment = buildFragment(id);
            fragment.title = title;
            result.put(id , fragment);
        }
        return result;
    }

    private static BaseFragment buildFragment(int id) {
        switch (id) {
            case R.id.hello:
                return new HomeFragment();
            case R.id.fuck_you:
                return new FuckFragment();
        }
        return null;
    }

    public String getTitle() {
        return  title;
    }

    public BaseFragment() {
        // Required empty public constructor
    }

}
