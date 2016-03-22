package com.example.hipsre720.hostcardmode;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {
    TextView tv;
    CardEmulation CE;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tv = new TextView(this));
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(30);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            NfcManager manager = (NfcManager) getSystemService(Context.NFC_SERVICE);
            NfcAdapter adapter = manager.getDefaultAdapter();
            if (adapter != null && adapter.isEnabled()) {
                PackageManager pm = getPackageManager();
                boolean hasNfcHce = pm.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);
                if(hasNfcHce){
                    tv.setText("功能開啟");
                }else{
                    tv.setText("此裝置不能夠模擬成卡片");
                }
            }else if(adapter !=null && !adapter.isEnabled()){
                tv.setText("請開啟NFC");
            }else{
                tv.setText("此裝置無NFC功能");
            }
        }else{
            tv.setText("請使用Android 4.4以上");
        }
        CardEmulation cardEmulationManager = CardEmulation.getInstance(NfcAdapter.getDefaultAdapter(this));
        ComponentName paymentServiceComponent = new ComponentName(getApplicationContext(), HCEService.class.getCanonicalName());
    }

    protected void onResume() {
        super.onResume();

    }

    protected void onPause() {
        super.onPause();

    }
}
