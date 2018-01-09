package e.hubertkowalski.myday;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_PROFILE = 1;
    public static final int DRAWER_MENU_CAMERA = 2;
    public static final int DRAWER_MENU_GALLERY = 3;
    public static final int DRAWER_MENU_ITEM_WEATHER = 4;
    public static final int DRAWER_MENU_ITEM_LOGOUT = 5;

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
        setDrawerCallBack(new DrawerCallBack());
    }


    @Resolve
    private void onResolved() {
        try {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                itemNameTxt.setText("Zmień hasło");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_account_circle_black_18dp));
                break;
            case DRAWER_MENU_CAMERA:
                itemNameTxt.setText("Aparat");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_add_a_photo_black_18dp));
                break;
            case DRAWER_MENU_GALLERY:
                itemNameTxt.setText("Galeria");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_compare_arrows_black_18dp));
                break;
            case DRAWER_MENU_ITEM_WEATHER:
                itemNameTxt.setText("Pogoda");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_work_black_18dp));
                break;

            case DRAWER_MENU_ITEM_LOGOUT:
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_exit_to_app_black_18dp));
                itemNameTxt.setText("Wyloguj się");
                break;
        }}catch (Exception e) {
            Log.e("DRAWER MENU ITEM E", "onCreateView", e);
            throw e;}
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                if(mCallBack != null)mCallBack.onProfileMenuSelected();
                break;
            case DRAWER_MENU_CAMERA:
                Toast.makeText(mContext, "Aparat", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onCameraSelected();
                break;
            case DRAWER_MENU_GALLERY:
                Toast.makeText(mContext, "Galeria", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onGallerySelected();
                break;
            case DRAWER_MENU_ITEM_WEATHER:
                Toast.makeText(mContext, "Pogoda", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onWeatherMenuSelected();
                break;

            case DRAWER_MENU_ITEM_LOGOUT:
                Toast.makeText(mContext, "Wylogowano", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onLogoutMenuSelected();
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }


    public class DrawerCallBack{
        void onProfileMenuSelected(){
            Profile profile = Profile.getCurrentProfile();
            if (profile == null) {
                Intent intent = new Intent(mContext, EditProfile.class);
                LoginManager.getInstance().logOut();
                mContext.startActivity(intent);
            }
            else Toast.makeText(mContext, "Zalogowano poprzez Facebook'a", Toast.LENGTH_SHORT).show();
        }

        void onCameraSelected(){
            Intent intent = new Intent(mContext, CameraActivity.class);
            mContext.startActivity(intent);
        }

        void onGallerySelected(){
            Intent intent = new Intent(mContext, MyGalleryView.class);
            mContext.startActivity(intent);
        }

        void onWeatherMenuSelected(){
            Intent intent = new Intent(mContext, WeatherActivity.class);
            mContext.startActivity(intent);
        }

        void onLogoutMenuSelected(){
            Intent intent = new Intent(mContext, FacebookLogin.class);
            LoginManager.getInstance().logOut();
            mContext.startActivity(intent);
            ((Activity)(mContext)).finish();
        }
    }
}