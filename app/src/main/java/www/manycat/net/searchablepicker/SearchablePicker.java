package www.manycat.net.searchablepicker;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2016/9/28.
 */
public class SearchablePicker extends LinearLayout implements CycleWheelView.WheelItemClickListener, CycleWheelView.WheelItemSelectedListener, TextWatcher{

    private List<String> labels, tmpLabels;

    private View searchablePicker;

    private Context context;

    private CycleWheelView picker;

    private EditText query;

    private OnPickerItemClickListener mPickerItemClickListener;

    private OnPickerItemSelectedListener mPickerItemSelectedListener;

    public SearchablePicker(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public SearchablePicker(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public SearchablePicker(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    // 設定picker list內容
    public void setLabels( List<String> labels)
    {
        this.labels = labels;
        picker.setLabels(labels);
    }

    // 設置picker顯示項目個數,必須為奇數且大於3
    public void setWheelSize( int WheelSize)
    {
        try
        {
            picker.setWheelSize(WheelSize);
        }
        catch (CycleWheelView.CycleWheelViewException e)
        {
            e.printStackTrace();
        }
    }

    // 設定選中項目
    public void setPickerSelection(final int position)
    {
        picker.setSelection(position);
    }

    // 取得選中項目
    public int getPickerSelection()
    {
        return picker.getSelection();
    }

    // 設置未選中項目文字顏色
    public void setPickerLabelColor(int labelColor)
    {
        picker.setLabelColor(labelColor);
    }

    // 設置選中項目文字顏色
    public void setPickerLabelSelectColor(int labelSelectColor)
    {
        picker.setLabelSelectColor(labelSelectColor);
    }

    // 設置項目透明度漸層值
    public void setAlphaGradual(float alphaGradual)
    {
        picker.setAlphaGradual(alphaGradual);
    }

    /**
     * 设置块的颜色
     * @param unselectedSolidColor 未选中的块的颜色
     * @param selectedSolidColor 选中的块的颜色
     */
    public void setPickerSolid(int unselectedSolidColor, int selectedSolidColor)
    {
        picker.setSolid( unselectedSolidColor, selectedSolidColor);
    }

    /**
     * 设置分割线样式
     * @param dividerColor  分割线颜色
     * @param dividerHeight 分割线高度(px)
     */
    public void setPickerDivider(int dividerColor, int dividerHeight)
    {
        picker.setDivider( dividerColor, dividerHeight);
    }

    // 設定搜尋框文字顏色
    public void setSearchbarTextColor(int SearchbarTextColor)
    {
        query.setTextColor(SearchbarTextColor);
    }

    // 設定搜尋框背景顏色
    public void setSearchbarBackgroundColor(int SearchbarBackgroundColor)
    {
        query.setBackgroundColor(SearchbarBackgroundColor);
    }

    // 設定搜尋框hint文字
    public void setSearchbarHintText(String hint)
    {
        query.setHint(hint);
    }

    // 設定搜尋框hint文字
    public void setSearchbarHintTextColor(int SearchbarHintColor)
    {
        query.setHintTextColor(SearchbarHintColor);
    }

    private void init()
    {
        LayoutInflater inflater = LayoutInflater.from(context);

        searchablePicker = inflater.inflate(R.layout.searchable_picker, this, true);

        // Picker
        picker = (CycleWheelView)searchablePicker.findViewById(R.id.picker);
        picker.setAlphaGradual(0.5f);
        picker.setOnWheelItemSelectedListener(this);
        picker.setOnWheelItemClickListener(this);

        // SearchBar
        query = (EditText) searchablePicker.findViewById(R.id.query);
        query.addTextChangedListener(this);
    }

    @Override
    public void onItemClick(int position, String label)
    {
        if (mPickerItemClickListener != null)
        {
            mPickerItemClickListener.onPickerItemClick( position, label);
        }
    }

    @Override
    public void onItemSelected(int position, String label)
    {
        if (mPickerItemSelectedListener != null)
        {
            mPickerItemSelectedListener.onPickerItemSelected( position, label);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    // List字串搜尋
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        tmpLabels = new ArrayList<>();

        if( ! s.equals("") )
        {
            for( int i=0; i < labels.size(); i++)
            {
                if (labels.get(i).toString().toUpperCase().contains(s.toString().toUpperCase()))
                {
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
    public void afterTextChanged(Editable s)
    {

    }

    public interface OnPickerItemClickListener
    {
        public void onPickerItemClick(int position, String label);
    }

    public void setOnPickerItemClickListener(OnPickerItemClickListener mPickerItemClickListener)
    {
        this.mPickerItemClickListener = mPickerItemClickListener;
    }

    public interface OnPickerItemSelectedListener
    {
        public void onPickerItemSelected(int position, String label);
    }

    public void setOnPickerItemSelectedListener(OnPickerItemSelectedListener mPickerItemSelectedListener)
    {
        this.mPickerItemSelectedListener = mPickerItemSelectedListener;
    }
}
