package com.lexieluv.homeworktenth;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lexieluv.homeworktenth.R;


public class MainActivity extends AppCompatActivity {
    private Button radio,date,checkbox;
    private ButtonListener bl;
    private DatePicker datePicker;

    private TextView tv_title;
    private TextView gethobby;
    private String s = "个人爱好：";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiView();
        bl = new ButtonListener();
        setListener();
        tv_title.setText(s);
    }
    private void intiView(){
        radio = findViewById(R.id.radio);
        date = findViewById(R.id.date);
        checkbox = findViewById(R.id.checkbox);

        gethobby = findViewById(R.id.gethobby);
        tv_title = (TextView)findViewById(R.id.tv_title);
//        tv_title = null;

        datePicker = findViewById(R.id.dp);
    }

    public void setListener(){
        radio.setOnClickListener(bl);
        date.setOnClickListener(bl);
        checkbox.setOnClickListener(bl);
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.radio:
                    //单击单选列表对话框，选择之后对话框消失，并打印吐司；样式由于标题不一样，采用自定义方式。
                    SingleDialog singleDialog = new SingleDialog(MainActivity.this, R.style.myDialog);
                    singleDialog.show();
                    break;
                case R.id.date:
                    //直接定义一个时间选择对话框方法，里面放上设置时间的监听器。
                        DateCreateDialog();
                    break;
                case R.id.checkbox:
                    //不需要toast所以这样
                    //单击多选列表对话框，选择所有之后点击确定对话框消失，不打印吐司，文字显示在左下方；样式由于标题不一样，采用自定义方式。
                    CheckDialog checkDialog = new CheckDialog(MainActivity.this, R.style.myDialog);
                    checkDialog.show();
                    break;
            }
        }
    }
    //实现时间选择对话框的方法。
    private void DateCreateDialog() {
        Calendar cd = Calendar.getInstance();
        final int[] Year = {cd.get(Calendar.YEAR)};
        final int[] Month = {cd.get(Calendar.MONTH)};
        final int[] Day = {cd.get(Calendar.DAY_OF_MONTH)};
        final int Week = cd.get(Calendar.WEEK_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener(){
            public void onDateSet(DatePicker view,final int year,final int month,final int dayOfMonth) {
                Year[0] = year;
                Month[0] = month;
                Day[0] = dayOfMonth;
                final String data =  year + "年" + (month+1) + "月" + dayOfMonth + "日 ";
                Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();
            }
        }, Calendar.YEAR,Calendar.MONTH,Calendar.DATE);
        dialog.show();
    }

    //由于主界面显示的个人爱好没在对话框中，因此就定义了内部自定义对话框类。
    class CheckDialog extends Dialog {

        private CheckBox cb_pro,cb_music,cb_cook,cb_run;
        SetCheckbox scb;
        private Button yes;
        private List<String> lists = new ArrayList<String>();

        public CheckDialog(@NonNull Context context, int themeResId) {
            super(context, themeResId);
            setContentView(R.layout.checkbox_dialog);

            cb_pro = findViewById(R.id.cb_pro);
            cb_music = findViewById(R.id.cb_music);
            cb_cook = findViewById(R.id.cb_cook);
            cb_run = findViewById(R.id.cb_run);
            yes = findViewById(R.id.yes);

            scb = new SetCheckbox();
            cb_pro.setOnCheckedChangeListener(scb);
            cb_music.setOnCheckedChangeListener(scb);
            cb_cook.setOnCheckedChangeListener(scb);
            cb_run.setOnCheckedChangeListener(scb);

            yes.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(!lists.isEmpty()) {
                        //下行这个setText放在这里总是会报空指针，于是我放在开始了，但是不知道应该最开始不显示，然后选择了爱好关闭对话框后才显示？？？？
//                        tv_title.setText(s);
                        gethobby.setText(lists.toString());
                        lists.clear();
                    }
                    dismiss();
                }
            });
        }


        class SetCheckbox implements CompoundButton.OnCheckedChangeListener{
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox checkBox = (CheckBox) buttonView;
                switch (checkBox.getId()){
                    case R.id.cb_pro:
                        lists.add(cb_pro.getText().toString()+"\n");
                        break;
                    case R.id.cb_music:
                        lists.add(cb_music.getText().toString()+"\n");
                        break;
                    case R.id.cb_cook:
                        lists.add(cb_cook.getText().toString()+"\n");
                        break;
                    case R.id.cb_run:
                        lists.add(cb_run.getText().toString()+"\n");
                        break;
                }
            }
        }
    }

}
//试验过程，可以忽略：
//    private void showMultiDialog() {
//        final String[] items = {"编程","编曲","做饭","运动"};
//        final boolean[] checked = {false,false,false,false};
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle("")
//                .setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        Log.e("Log",items[which]);
////                        checked[which] = isChecked;
//                    }
//                })
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String msg = "您的爱好是：";
//                            for (int i = 0; i < checked.length; i++) {
//                                if(checked[i]) {
//                                    msg += items[i]+" ";
//                                }
//                            }
//                            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
//                        }
//                });
//    }

//    private void showArrayDialog() {
//        final String[] items = {"编程", "编曲", "做饭", "运动"};
//        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.title,R.id.text,items);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle("")
//                .setAdapter(adapter,null);
//        builder.show();
//    }

//    private void showNormalDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//            .setTitle("")
//            .setMessage("")
//            .setPositiveButton("Done", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                MainActivity.this.finish();
//            }
//        });
//        builder.show();
//    }

//    public String getToastDate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
//        Date datePick = null;
//        try {
//            datePick = sdf.parse(dp.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String dateString = sdf.format(datePick);
//        return dateString;
//    }



//    private void showDateDialog(){
//        DatePickerDialog dialog = new DatePickerDialog(this).create();
//        AlertDialog dialog = new AlertDialog.Builder(this).create();
//        dialog.setTitle("");
//        dialog.setMessage("");
//        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Done", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//    }


