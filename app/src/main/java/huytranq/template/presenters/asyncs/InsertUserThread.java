package huytranq.template.presenters.asyncs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import huytranq.template.models.User;
import huytranq.template.presenters.utilities.UserDatabase;

/**
 * Created by huytr on 13-01-2016.
 */
public class InsertUserThread extends AsyncTask<Void , Void , Void> {

    private Context context;
    private User user;
    private ProgressDialog progress;

    public InsertUserThread(Context context ,
                            User user) {
        this.context = context;
        this.user = user;
        progress = new ProgressDialog(context);
        progress.setMessage("Inserting user...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (UserDatabase.getInstance(context).insert(user))
            Log.d("debug" , "Insert is successful");
        else
            Log.d("debug" , "Insert is failed");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progress.dismiss();
    }
}
