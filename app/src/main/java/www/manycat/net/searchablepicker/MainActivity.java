package www.manycat.net.searchablepicker;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;
    private CycleWheelView picker;
    private EditText query;
    private List<String> labels, tmpLabels;
    private View searchablePicker;
    private AlertDialog dialig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);

        buildSearchablePicker();

        btn.setOnClickListener(this);
    }

    private void buildSearchablePicker()
    {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

        searchablePicker = inflater.inflate(R.layout.searchable_picker, null);

        labels = new ArrayList<>();
        labels.add("師速列車");
        labels.add("美國隊長");
        labels.add("豬羅機工原");
        labels.add("鬼來電");
        labels.add("麗英宅");
        labels.add("特力屋");
        labels.add("復仇者聯盟");
        labels.add("次機1995");
        labels.add("1995");
        labels.add("動物方程式");


        picker = (CycleWheelView)searchablePicker.findViewById(R.id.picker);
        picker.setLabels(labels);
        try {
            picker.setWheelSize(5);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }
        picker.setSelection(2);
//        picker.setCycleEnable(true);
        picker.setAlphaGradual(0.5f);
        picker.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, String label) {
                Log.d("test", label);
            }
        });

        picker.setOnWheelItemClickListener(new CycleWheelView.WheelItemClickListener() {
            @Override
            public void onItemClick(int position, String label) {
                Log.d("test", label);
            }
        });

        picker.setOnWheelItemClickListener(new CycleWheelView.WheelItemClickListener() {
            @Override
            public void onItemClick(int position, String label) {
                dialig.hide();
            }
        });

        query = (EditText) searchablePicker.findViewById(R.id.query);
        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tmpLabels = new ArrayList<>();

                if( ! s.equals("") )
                {
                    for( int i=0; i < labels.size(); i++)
                    {
                        if (labels.get(i).toString().toUpperCase().contains(s.toString().toUpperCase())) {
                            tmpLabels.add(labels.get(i).toString());
                        }
                    }
                }
                else
                {
                    tmpLabels = labels;
                }
                picker.setLabels(tmpLabels);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        if( dialig == null )
        {
            dialig = new AlertDialog.Builder(MainActivity.this)
                    .setView(searchablePicker)
                    .show();
        }
        else
        {
            dialig.show();
        }
    }
}
