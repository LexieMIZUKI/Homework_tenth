package com.lexieluv.homeworktenth;


import android.app.Dialog;
import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SingleDialog extends Dialog{
    private RadioGroup rg_gender;
    private RadioButton rb_male,rb_female,rb_unknown,rb_guess;
    Context context;//利用context方式来实现在非活动界面中进行吐司打印
    public SingleDialog(final Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        setContentView(R.layout.single_dialog);

        rg_gender = findViewById(R.id.rg_gender);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);
        rb_unknown = findViewById(R.id.rb_unknown);
        rb_guess = findViewById(R.id.rb_guess);

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_male:
                        Toast.makeText(context, "您选择了：男性。", Toast.LENGTH_SHORT).show();
                        dismiss();
                        break;
                    case R.id.rb_guess:
                        Toast.makeText(context, "您选择了：你猜！。", Toast.LENGTH_SHORT).show();
                        dismiss();
                        break;
                    case R.id.rb_unknown:
                        Toast.makeText(context, "您选择了：性别未知！", Toast.LENGTH_SHORT).show();
                        dismiss();
                        break;
                    case R.id.rb_female:
                        Toast.makeText(context, "您选择了：女性。", Toast.LENGTH_SHORT).show();
                        dismiss();
                        break;
                }
            }

        });
    }
}
