package magang.project.firestore.sharedprefernce;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHandle  {

    public static final String SP_PENILAIAN_KARYWAN = "spMahasiswaApp";

    public static final String SP_USER_NAME = "spNama";
    public static final String SP_USER_PASSWORD = "spEmail";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefHandle(Context context){
        sp = context.getSharedPreferences(SP_PENILAIAN_KARYWAN, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void setSpUserName(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public String getSpUserName(){
        return sp.getString(SP_USER_NAME, "");
    }

    public void setSpUserPassword(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public String getSpUserPassword(){
        return sp.getString(SP_USER_PASSWORD, "");
    }

    public void  setSpSudahLogin(String keySp, boolean value){
        spEditor.putBoolean(keySp,value);
        spEditor.commit();
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }


}
