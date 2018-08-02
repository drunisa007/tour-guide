package tourguide.lightidea.com.tourguide.activity.Currency;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import tourguide.lightidea.com.tourguide.R;

import static org.slf4j.MDC.clear;

public class CurrencyActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    private TextView one,two,three,four,five,six,seven,eight,nine,zero,ac,clear;

    private TextView data_chi,data_usd,data_bur;

    private int burmese=1439;double chinese=6.79;int english=1;

    // usd
    private double eng_usd=1,chi_usd=6.79,bur_usd=1439;
    //cny==>>chinese
    private double eng_cny=0.15,chi_cny=1,bur_cny=213.21;
    //mmk==>>burmese
    private double eng_mmk=0.00069,chi_mmk=0.0047,bur_mmk=1;

    //for error test
    private  int one_err = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);


        givingId();

        settingToolbar();

        if(readData().equals("chi")){

            data_chi.setTextColor(Color.RED);
            data_chi.setText(String.valueOf(chinese));
            data_usd.setTextColor(Color.BLACK);
            data_bur.setTextColor(Color.BLACK);
        }
        else if(readData().equals("usd")){
            data_chi.setTextColor(Color.BLACK);
            data_usd.setTextColor(Color.RED);
            data_bur.setTextColor(Color.BLACK);
            data_usd.setText(String.valueOf(english));
        }

        else if(readData().equals("bur")){
            data_chi.setTextColor(Color.BLACK);
            data_usd.setTextColor(Color.BLACK);
            data_bur.setTextColor(Color.RED);
            data_bur.setText(String.valueOf(burmese));
        }

        else if (readData().equals("")){
            data_chi.setText(String.valueOf(chinese));
            data_bur.setText(String.valueOf(burmese));
            data_usd.setText(String.valueOf(english));
            data_chi.setTextColor(Color.BLACK);
            data_usd.setTextColor(Color.RED);
            data_bur.setTextColor(Color.BLACK);
        }
        else{
            data_chi.setText(String.valueOf(chinese));
            data_bur.setText(String.valueOf(burmese));
            data_usd.setText(String.valueOf(english));
            data_chi.setTextColor(Color.RED);
            data_usd.setTextColor(Color.BLACK);
            data_bur.setTextColor(Color.BLACK);
        }

        workingForTextChange();


    }

    private void workingForTextChange() {
        data_chi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(readData().equals("chi")) {
                    if (one_err == 1) {
                        one_err = 0;
                    } else {
                        String mother_fucker=data_usd.getText().toString();
                        if (!mother_fucker.startsWith("Infinity")) {

                            String data = data_chi.getText().toString();
                            String testData = data.substring(data.length() - 1);
                            if (testData.equals(".")) {

                            } else {
                                double result = Double.parseDouble(data);
                                double one = result;
                                double two = result * eng_cny;
                                double three = result * bur_cny;

                                one_err = 1;
                                DecimalFormat format = new DecimalFormat("0.#");

                                if (one - Math.round(one) == 0) {
                                    data_chi.setText(format.format(one));
                                } else {
                                    data_chi.setText(String.valueOf(one));
                                }
                                if (two - Math.round(two) == 0) {
                                    data_usd.setText(format.format(two));
                                } else {
                                    data_usd.setText(String.valueOf(two));
                                }

                                if (three - Math.round(three) == 0) {
                                    data_bur.setText(format.format(three));
                                } else {
                                    data_bur.setText(String.valueOf(three));
                                }


                            }
                        }
                        else{
                            data_usd.setText("0");
                            data_chi.setText("0");
                            data_bur.setText("0");
                        }
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        data_usd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("arun",charSequence+"");
                if(readData().equals("usd")) {
                    if (one_err == 1) {
                        one_err = 0;
                    } else {
                        String mother_fucker=data_usd.getText().toString();
                        if (!mother_fucker.startsWith("Infinity")) {
                            String data = data_usd.getText().toString();
                            String testData = data.substring(data.length() - 1);
                            if (testData.equals(".")) {

                            } else {
                                double result = Double.parseDouble(data);
                                double one = result;
                                double two = result * chi_usd;
                                double three = result * bur_usd;
                                one_err = 1;

                                DecimalFormat format = new DecimalFormat("0.#");

                                if (one - Math.round(one) == 0) {
                                    data_usd.setText(format.format(one));
                                } else {
                                    data_usd.setText(String.valueOf(one));
                                }
                                if (two - Math.round(two) == 0) {
                                    data_chi.setText(format.format(two));
                                } else {
                                    data_chi.setText(String.valueOf(two));
                                }

                                if (three - Math.round(three) == 0) {
                                    data_bur.setText(format.format(three));
                                } else {
                                    data_bur.setText(String.valueOf(three));
                                }

                            }
                        }
                        else{
                            data_usd.setText("0");
                            data_chi.setText("0");
                            data_bur.setText("0");
                        }

                    }
                }






            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        data_bur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(readData().equals("bur")){
                    if(one_err==1){
                        one_err=0;
                    }
                    else{
                        String mother_fucker=data_usd.getText().toString();
                        if (!mother_fucker.startsWith("Infinity")) {


                        String data= data_bur.getText().toString();
                        String testData = data.substring(data.length()-1);
                        if(testData.equals(".")){

                        }
                        else{
                            double result= Double.parseDouble(data);
                            double one=result;
                            double two= result*eng_mmk;
                            double three= result*chi_mmk;
                            one_err=1;
                            DecimalFormat format=new DecimalFormat("0.#");

                            if(one-Math.round(one)==0){
                                data_bur.setText(format.format(one));
                            }
                            else{
                                data_bur.setText(String.valueOf(one));
                            }
                            if(two-Math.round(two)==0){
                                data_usd.setText(format.format(two));
                            }
                            else{
                                data_usd.setText(String.valueOf(two));
                            }

                            if(three-Math.round(three)==0){
                                data_chi.setText(format.format(three));
                            }
                            else{
                                data_chi.setText(String.valueOf(three));
                            }
                        }
                    }
                    else{
                            data_usd.setText("0");
                            data_chi.setText("0");
                            data_bur.setText("0");
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void one(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("1");
        }
        else{
            result.setText(data+"1");
        }


    }

    private void two(String data, TextView result) {
        if(data.equals("0.0")){
             result.setText("2");
        }
        else{
            result.setText(data+"2");
        }

    }

    private void three(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("3");
        }
        else{
            result.setText(data+"3");
        }

    }

    private void four(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("4");
        }
        else{
            result.setText(data+"4");
        }

    }

    private void five(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("5");
        }
        else{
            result.setText(data+"5");
        }

    }

    private void six(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("6");
        }
        else{
            result.setText(data+"6");
        }

    }

    private void seven(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("7");
        }
        else{
            result.setText(data+"7");
        }

    }

    private void eight(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("8");
        }
        else{
            result.setText(data+"8");
        }

    }

    private void nine(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("9");
        }
        else{
            result.setText(data+"9");
        }

    }

    private void zero(String data, TextView result) {
        if(data.equals("0.0")){
            result.setText("0");
        }
        else{
            result.setText(data+"0");
        }

    }

    private void ac() {
        data_chi.setText("0");
        data_bur.setText("0");
        data_usd.setText("0");
    }

    private void clears(TextView result) {

          String data = result.getText().toString();
          if(data.length()==1){
              result.setText("0.0");
          }
          else{
              data= data.substring(0,data.length()-1);
              result.setText(data);
          }


    }



    private void settingToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Currency");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void givingId() {
        mToolbar = findViewById(R.id.currency_toolbar);
        //textview click listener

        one = findViewById(R.id.cur_one);
        one.setOnClickListener(this);
        two = findViewById(R.id.cur_two);
        two.setOnClickListener(this);
        three = findViewById(R.id.cur_three);
        three.setOnClickListener(this);
        four = findViewById(R.id.cur_four);
        four.setOnClickListener(this);
        five = findViewById(R.id.cur_five);
        five.setOnClickListener(this);
        six = findViewById(R.id.cur_six);
        six.setOnClickListener(this);
        seven = findViewById(R.id.cur_seven);
        seven.setOnClickListener(this);
        eight = findViewById(R.id.cur_eight);
        eight.setOnClickListener(this);
        nine = findViewById(R.id.cur_nine);
        nine.setOnClickListener(this);
        zero = findViewById(R.id.cur_zero);
        zero.setOnClickListener(this);
        ac = findViewById(R.id.cur_ac);
        ac.setOnClickListener(this);
        clear = findViewById(R.id.cur_clear);
        clear.setOnClickListener(this);

        //giving id to textview DATA

        data_chi  = findViewById(R.id.currency_chi_data);
        data_chi.setOnClickListener(this);
        data_usd = findViewById(R.id.currency_usd_data);
        data_usd.setText("1");
        data_usd.setOnClickListener(this);
        data_bur = findViewById(R.id.currency_bur_data);
        data_bur.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        String data="";
        TextView result;



        if(readData().equals("chi")){

           data= data_chi.getText().toString();
           result=data_chi;
        }
        else if(readData().equals("usd")){
            data=data_usd.getText().toString();
            result=data_usd;
        }
        else{
            data=data_bur.getText().toString();
            result=data_bur;
        }


        int id = view.getId();

        switch (id){
            case  R.id.cur_one:
                one(data,result);
                break;
            case R.id.cur_two:
                two(data,result);
                break;
            case R.id.cur_three:
                three(data,result);
                break;
            case R.id.cur_four:
                four(data,result);
                break;
            case R.id.cur_five:
                five(data,result);
                break;
            case R.id.cur_six:
                six(data,result);
                break;
            case R.id.cur_seven:
                seven(data,result);
                break;
            case R.id.cur_eight:
                eight(data,result);
                break;
            case R.id.cur_nine:
                nine(data,result);
                break;
            case R.id.cur_zero:
                zero(data,result);
                break;
            case R.id.cur_ac:
                ac();
                break;
            case R.id.cur_clear:
                clears(result);
                break;
            case R.id.currency_chi_data:
                data_chi.setTextColor(Color.RED);
                data_usd.setTextColor(Color.BLACK);
                data_bur.setTextColor(Color.BLACK);
                saveData("chi");
                break;
            case R.id.currency_usd_data:
                data_usd.setTextColor(Color.RED);
                data_chi.setTextColor(Color.BLACK);
                data_bur.setTextColor(Color.BLACK);
                saveData("usd");
                break;
            case R.id.currency_bur_data:
                data_bur.setTextColor(Color.RED);
                data_chi.setTextColor(Color.BLACK);
                data_usd.setTextColor(Color.BLACK);
                saveData("bur");
                break;
        }
    }



    private String readData() {
        SharedPreferences sharedPref = getSharedPreferences("cur", Context.MODE_PRIVATE);
        String default_data = "0";
        String value = sharedPref.getString("data", default_data);
        return value;

    }

    private void saveData(String value) {
        SharedPreferences sharedPref = getSharedPreferences("cur", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("data", value);
        editor.commit();
    }
}
