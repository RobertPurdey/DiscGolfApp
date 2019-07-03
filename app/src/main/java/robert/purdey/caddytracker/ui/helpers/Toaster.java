package robert.purdey.caddytracker.ui.helpers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import robert.purdey.caddytracker.R;

public class Toaster
{
    public static void quickFailureToast(Context context, int msg, int toastLength)
    {
        Toast toast = Toast.makeText(context, msg, toastLength);
        View view   = toast.getView();

        view.setBackgroundResource(R.color.error_red);
        toast.show();
    }

    public static void quickSuccessToast(Context context, int msg, int toastLength)
    {
        Toast toast = Toast.makeText(context, msg, toastLength);
        View view   = toast.getView();

        view.setBackgroundResource(R.color.success_green);
        toast.show();
    }
}
