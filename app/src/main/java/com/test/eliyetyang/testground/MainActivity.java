package com.test.eliyetyang.testground;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.guangyao.wohai.activity.room.RoomActivity;
import com.guangyao.wohai.listener.AccountListener;
import com.guangyao.wohai.utils.PublicUtils;
import com.guangyao.wohai.utils.WoHaiSdk;
import com.plattysoft.leonids.ParticleSystem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.test.eliyetyang.testground.adapter.RecyclerAdapter;
import com.test.eliyetyang.testground.aidl.IMyRemoteService;
import com.test.eliyetyang.testground.voice.VoiceInputListener;
import com.test.eliyetyang.testground.widget.LoopTextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayList<Fragment> mFragments;
    private RadioGroup mTabGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//        testLoopTextView();
//        testDrawableLayout();
//        testRemoteService();
//        testVoice();
//        testWohaiSdk();
//        testToolbar();
//        testTimePicker();
//        testNumberPicker();
//        testCardView();
        testParticles();
    }

    ImageView mParticlesImage;

    private void testParticles() {
        setContentView(R.layout.particles_layout);
//        new ParticleSystem(this, 80, R.drawable.ic_launcher, 10000)
//                .setSpeedModuleAndAngleRange(0f, 0.3f, 180, 180)
//                .setRotationSpeed(144)
//                .setAcceleration(0.00005f, 90)
//                .emit(findViewById(R.id.particles_ground), 8);
        mParticlesImage = (ImageView) findViewById(R.id.particles_ground);
        findViewById(R.id.particles_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Picasso.with(MainActivity.this).load("http://test1.wohai.com/images/gift/png/3333.png").into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                        new ParticleSystem(MainActivity.this, 1, bitmapDrawable, 10000)
                                .setSpeedModuleAndAngleRange(0f, 0.8f, 265, 273)
                                .setRotationSpeed(0)
                                .setAcceleration(0, 0).setFadeOut(2000)
                                .oneShot(mParticlesImage, 1);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
//                new ParticleSystem(MainActivity.this, 1, R.drawable.heart_ic, 10000)
//                        .setSpeedModuleAndAngleRange(0f, 0.8f, 265, 273)
//                        .setRotationSpeed(0)
//                        .setAcceleration(0, 0)
//                        .oneShot(findViewById(R.id.particles_ground), 1);
//                new ParticleSystem(MainActivity.this, 1, R.drawable.heart_ic, 10000)
//                        .setSpeedModuleAndAngleRange(0f, 0.8f, 265, 273)
//                        .setRotationSpeed(0)
//                        .setAcceleration(0, 0)
//                        .emitWithGravity(findViewById(R.id.particles_ground), Gravity.TOP, 1000);
//                new ParticleSystem(MainActivity.this, 1, R.drawable.fengche, 10000)
//                        .setSpeedModuleAndAngleRange(0f, 0.8f, 265, 273)
//                        .setRotationSpeed(0)
//                        .setAcceleration(0, 0)
//                        .emitWithGravity(findViewById(R.id.particles_ground), Gravity.TOP, 1000);
//                new ParticleSystem(MainActivity.this, 1, R.drawable.start, 10000)
//                        .setSpeedModuleAndAngleRange(0f, 0.8f, 265, 273)
//                        .setRotationSpeed(0)
//                        .setAcceleration(0, 0)
//                        .emitWithGravity(findViewById(R.id.particles_ground), Gravity.TOP, 1000);
            }
        });
    }

    private ArrayList<String> mDataSet;

    private void testCardView() {
        setContentView(R.layout.card_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_layout_recycler_view);
        recyclerView.setHasFixedSize(true);

        mDataSet = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            mDataSet.add("第 " + i + " 项");
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(mDataSet));
    }

    NumberPicker numberPicker1, numberPicker2, numberPicker3;

    private void testNumberPicker() {
        setContentView(R.layout.number_picke);

        numberPicker1 = (NumberPicker) findViewById(R.id.number1);
        numberPicker2 = (NumberPicker) findViewById(R.id.number2);
        numberPicker3 = (NumberPicker) findViewById(R.id.number3);

        numberPicker1.setMaxValue(100);
        numberPicker1.setMinValue(0);
        numberPicker2.setMaxValue(100);
        numberPicker2.setMinValue(0);
        numberPicker3.setMaxValue(100);
        numberPicker3.setMinValue(0);

        findViewById(R.id.get_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "v1 = " + numberPicker1.getValue());
                Log.d("Tag", "v2 = " + numberPicker2.getValue());
                Log.d("Tag", "v3 = " + numberPicker3.getValue());
            }
        });

    }

    private void testTimePicker() {
        setContentView(R.layout.time_picker);
    }

    private void testToolbar() {
        setContentView(R.layout.toolbar_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_test_tb);
        setSupportActionBar(toolbar);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void testWohaiSdk() {
        setContentView(R.layout.wohai_sdk);
        WoHaiSdk.getInstance().setAccountListener(new AccountListener() {
            @Override
            public void onToAccount(RoomActivity roomActivity) {
                roomActivity.getWoHaiUserInfo(MainActivity.this, "author4", PublicUtils.md5("111111"));
            }
        });
        android.support.v4.app.Fragment fragment = WoHaiSdk.getInstance().getMainAnchorListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.wohai_sdk_main, fragment, "anchor").show(fragment).commit();
    }

    private VoiceInputListener mVoiceInputListener;
    private Button mVoiceStart;
    private EditText mVoiceEdit;
    private Gson mGson;

    private void testVoice() {
        setContentView(R.layout.test_voice_main);
        mVoiceStart = (Button) findViewById(R.id.test_voice_start);
        mVoiceEdit = (EditText) findViewById(R.id.test_voice_content);
        mGson = new Gson();
        mVoiceInputListener = new VoiceInputListener(this, mGson, mVoiceStart, mVoiceEdit);
        mVoiceInputListener.start();
    }

    private IMyRemoteService myRemoteService;
    private ServiceConnection mServiceConnection;

    private void testRemoteService() {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myRemoteService = IMyRemoteService.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myRemoteService = null;
            }
        };
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void testDrawableLayout() {
        setContentView(R.layout.drawable_layout_test);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                Log.e("tag", "onDrawerSlide slideOffset = " + slideOffset);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                Log.e("tag", "onDrawerOpened");
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                Log.e("tag", "onDrawerClosed");
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//                Log.e("tag", "onDrawerStateChanged newState = " + newState);
//            }
//        };
//        drawerLayout.setDrawerListener(drawerListener);

        CheckBox show = (CheckBox) findViewById(R.id.show_cb);
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void testLoopTextView() {
        LoopTextView loopTextView1 = (LoopTextView) findViewById(R.id.text1);
        LoopTextView loopTextView2 = (LoopTextView) findViewById(R.id.text2);
        ArrayList<LoopTextView.LoopItem> loopItems1 = new ArrayList<>();
        ArrayList<LoopTextView.LoopItem> loopItems2 = new ArrayList<>();
        LoopTextView.LoopItem loopItem1 = new LoopTextView.LoopItem(R.drawable.cfdj_1fu_pic, "1富");
        LoopTextView.LoopItem loopItem2 = new LoopTextView.LoopItem(R.drawable.cfdj_gongjue2_1_pic, "一等");
        LoopTextView.LoopItem loopItem3 = new LoopTextView.LoopItem(R.drawable.cfdj_gongjue2_2_pic, "公爵");
        loopItems1.add(loopItem1);
        loopItems2.add(loopItem2);
        loopItems2.add(loopItem3);
        loopTextView1.setLoopResIds(loopItems1);
        loopTextView2.setLoopResIds(loopItems2);
    }

//    private void test1() {
//        final FragmentManager fragmentManager = getFragmentManager();
//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        mFragments = new ArrayList<>();
//        mFragments.add(new ListFragmentFirst());
//        mFragments.add(new FragmentSecond());
//        fragmentTransaction.add(R.id.main_content, new ListFragmentFirst(), "first");
//        fragmentTransaction.add(R.id.main_content, mFragments.get(1), "second");
//        fragmentTransaction.show(mFragments.get(0));
//        fragmentTransaction.hide(mFragments.get(1));
//        fragmentTransaction.commit();
//
//        mTabGroup = (RadioGroup) findViewById(R.id.tab_group);
//        mTabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                int curTagIndex = 0;
//                switch (checkedId) {
//                    case R.id.tab_first:
//                        curTagIndex = 0;
//                        break;
//                    case R.id.tab_second:
//                        curTagIndex = 1;
//                        break;
//                    default:
//                        break;
//                }
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.show(mFragments.get(curTagIndex));
//                for (int i = 0; i < mFragments.size(); i++) {
//                    if (i != curTagIndex) {
//                        fragmentTransaction.hide(mFragments.get(i));
//                    }
//                }
//                fragmentTransaction.commit();
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
