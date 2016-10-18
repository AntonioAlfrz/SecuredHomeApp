package proyectos.antonio.securedhome;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Antonio on 11/10/2016.
 */

public class PreferencesManager{

    private static final String preferencesSet = "securedHome.MyPreferences";

    public static void setNewValue(Context context, String key,String value){
        SharedPreferences mPrefs = context.getSharedPreferences(preferencesSet, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key){
        SharedPreferences mPrefs = context.getSharedPreferences(preferencesSet, Context.MODE_PRIVATE);
        return mPrefs.getString(key,"Default");
    }
}
