package www.manycat.net.searchablepicker;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private SearchablePicker searchable_picker;
    private SearchablePicker searchablePicker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);

        searchable_picker = (SearchablePicker)findViewById(R.id.searchable_picker);

        labels = new ArrayList<>();
        labels.add("屍速列車");
        labels.add("美國隊長");
        labels.add("美國隊長2");
        labels.add("美國隊長3");
        labels.add("鬼來電");
        labels.add("厲陰宅");
        labels.add("復仇者聯盟");
        labels.add("鬼來電2");
        labels.add("厲陰宅2");
        labels.add("復仇者聯盟2");
        labels.add("動物方程式");
        labels.add("魔獸-崛起");
        labels.add("惡靈古堡");
        labels.add("惡靈古堡2");
        labels.add("惡靈古堡3");
        labels.add("惡靈古堡4");
        labels.add("惡靈古堡5");

        searchable_picker.setLabels(labels);
        searchable_picker.setWheelSize(3);
        searchable_picker.setOnPickerItemSelectedListener(new SearchablePicker.OnPickerItemSelectedListener() {
            @Override
            public void onPickerItemSelected(int position, String label) {
                Log.i( "David", " label == " + label);
            }
        });

        searchable_picker.setOnPickerItemClickListener(new SearchablePicker.OnPickerItemClickListener() {
            @Override
            public void onPickerItemClick(int position, String label) {
                Log.i( "David", " label == " + label);
            }
        });

        searchable_picker.setPickerLabelColor(Color.parseColor("#ff0000"));
        searchable_picker.setPickerLabelSelectColor(Color.parseColor("#ff00ff"));
        searchable_picker.setPickerSolid( Color.parseColor("#aaaaaa"), Color.parseColor("#00ff00"));
        searchable_picker.setSearchbarTextColor(Color.parseColor("#aaaaaa"));
        searchable_picker.setSearchbarBackgroundColor(Color.parseColor("#00ff00"));
        searchable_picker.setSearchbarHintText("預設文字");
        searchable_picker.setSearchbarHintTextColor(Color.parseColor("#000000"));


        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if( dialig == null )
        {
            searchablePicker2 = new SearchablePicker(this);

            List<String> labels2 = new ArrayList<>();
            labels2.add("蜘蛛人");
            labels2.add("蜘蛛人2");
            labels2.add("蜘蛛人3");
            labels2.add("海底總動員");
            labels2.add("海底總動員2");
            labels2.add("鋼鐵人");
            labels2.add("鋼鐵人2");
            labels2.add("鋼鐵人3");
            labels2.add("雷神索爾");
            labels2.add("雷神索爾2");
            labels2.add("特種部隊");
            labels2.add("特種部隊2");

            searchablePicker2.setLabels(labels2);
            searchablePicker2.setWheelSize(5);

            dialig = new AlertDialog.Builder(MainActivity.this)
                    .setView(searchablePicker2)
                    .show();
        }
        else
        {
            dialig.show();
        }
    }
}
