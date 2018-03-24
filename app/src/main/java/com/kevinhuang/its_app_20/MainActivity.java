package com.kevinhuang.its_app_20;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.DrawableRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebView browser;
    private EditText etSendMsg;
    private TextView tvReceiveMsg;
    private ImageView ivRouteImg;
    private final String    DEBUG_TAG  = "SOCKET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        tvReceiveMsg = (TextView) findViewById(R.id.tvReceiveMsg);
        etSendMsg = (EditText) findViewById(R.id.etSendMsg);
        ivRouteImg=(ImageView)findViewById(R.id.ivRouteImg);
        Button btnSendMsg = (Button) findViewById(R.id.btnSendMsg);

        assert btnSendMsg != null;
        btnSendMsg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ivRouteImg.setImageResource(R.drawable.route01);
                Thread thread = new Thread(new ConnetSocket("1675sy9233.iask.in",12009));
                thread.start();
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
/**
 *
 */

//        browser=(WebView)findViewById(R.id.wvMapWeb);
//        browser.loadUrl("https://www.baidu.com/");
//        //http://192.168.43.223:8088/mkepmb/orgBind
//        // 如果页面中链接，如果希望点击链接继续在当前browser中响应，
//        // 而不是新开Android的系统browser中响应该链接，必须覆盖webview的WebViewClient对象
//        browser.getSettings().setJavaScriptEnabled(true);
//        browser.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url)
//            {
//                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
//                view.loadUrl(url);
//                return true;
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    class ConnetSocket extends Thread {
        private int conPort;
        private String ipAddress;
        public ConnetSocket(String ipAddress,int conPort){
            this.conPort = conPort;
            this.ipAddress = ipAddress;
        }
        @Override
        public void run() {
            Socket socket = null;
            String message = etSendMsg.getText().toString();
            try
            {
                //创建Socket
//                socket = new Socket("1675sy9233.iask.in",12009);
                socket = new Socket(ipAddress,conPort);
                //向服务器发送消息
                PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
                out.println(message);
                //接收来自服务器的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = br.readLine();
                String info = "服务器：";
                if ( msg != null )
                {

                    switch (msg) {
                        case "s1d1":
                            ivRouteImg.setImageResource(R.drawable.route01);
                            break;
                        case "s2d2":
                            out.println("p->k->j->y->t");
                            break;
                        default:
                            ivRouteImg.setImageResource(R.drawable.route_err);
                            break;
                    }

                }
                else
                {
                    ivRouteImg.setImageResource(R.drawable.route01);
                }
                if ( msg != null )
                {
                    tvReceiveMsg.setText(info + msg);
                }
                else
                {
                    tvReceiveMsg.setText("数据错误!");
                }
                //关闭流
                out.close();
                br.close();
                //关闭Socket
                socket.close();
            }
            catch (Exception e)
            {
                // TODO: handle exception
                Log.e(DEBUG_TAG, e.toString());
            }
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
