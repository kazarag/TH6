package com.example.th6;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Cau1Activity extends AppCompatActivity {
    Handler handlerMain;
    AtomicBoolean atomic=null;
    LinearLayout layoutdevebutton;
    Button btnOk;
    EditText edtOk;
    String[] colors = {"#FF0000", "#000000", "#0000FF"};
    int sizehalf=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau1);

        //lấy LinearLayout chứa Button ra
        layoutdevebutton=(LinearLayout) findViewById(R.id.layout_draw_button);
        final Random rd=new Random();
        btnOk=(Button) findViewById(R.id.btnDrawButton);
        edtOk=(EditText) findViewById(R.id.editNumber);

        handlerMain=new Handler()
        {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                //Nhận nhãn của Button được gửi về từ tiến trình con
                int v=rd.nextInt(100);
                String nhan_button=v+"";
                //khởi tạo 1 Button
                View vv=null;
                if(msg.arg1%2==0)
                {
                    vv=new Button(Cau1Activity.this);
                    ((Button) vv).setText(nhan_button);
                }
                else
                {
                    Random random = new Random();
                    int randomIndex = random.nextInt(colors.length);
                    String selectedColor = colors[randomIndex];

                    vv=new EditText(Cau1Activity.this);
                    ((EditText) vv).setText(nhan_button);
                    ((EditText) vv).setTextColor(Color.parseColor(selectedColor));

                }

                LinearLayout.LayoutParams params=new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                vv.setLayoutParams(params);

                //đưa Button vào layoutdevebutton
                layoutdevebutton.addView(vv);

            }
        };
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doStart();
            }
        });
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        sizehalf=size.x/2;
    }
    private boolean isPrime(int n)
    {
        if(n<2)return false;
        for(int i=2;i<=Math.sqrt(n);i++)
            if(n%i==0)return false;
        return true;
    }
    private void doStart()
    {
        layoutdevebutton.removeAllViews();
        atomic=new AtomicBoolean(false);
        final int sobutton=Integer.parseInt(edtOk.getText()+"");
        Thread thCon=new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                for(int i=0;i<sobutton && atomic.get();i++)
                {
                    //nghỉ 200 mili second
                    SystemClock.sleep(200);
                    //lấy message từ Main Thread
                    Message msg=handlerMain.obtainMessage();
                    //gán dữ liệu cho msg Mainthread, lưu vào biến obj
                    //chú ý ta có thể lưu bất kỳ kiểu dữ liệu nào vào obj
                    msg.arg1=i;
                    //gửi trả lại message cho Mainthread
                    handlerMain.sendMessage(msg);
                }
            }
        });
        atomic.set(true);
        //thực thi tiến trình
        thCon.start();
    }
}