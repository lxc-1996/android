package com.lxc.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.ListenerUtil;

import com.lxc.base.R;

public class LeftTextEditText extends RelativeLayout {
    private EditText editText;

    private TextView tvValue;

    private boolean isEdit = true;

    public LeftTextEditText(Context context) {
        this(context, null);
    }

    public LeftTextEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftTextEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.layouy_left_text_edit_text, this, true);
        editText = findViewById(R.id.et_value);
        tvValue = findViewById(R.id.tv_value);
        TextView tvKey = findViewById(R.id.tv_key);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LeftTextEditText);
        String text = typedArray.getString(R.styleable.LeftTextEditText_text);
        String hint = typedArray.getString(R.styleable.LeftTextEditText_left_view_hint);
        tvKey.setText(text);
        editText.setHint(hint);
        typedArray.recycle();

    }

    public void setText(String text) {
        if (!getText().equals(text)) {
            editText.setText(text);
            tvValue.setText(": "+text);
        }

    }

    public String getText() {
        Editable text = editText.getText();
        if (text != null) {
            return text.toString();
        } else {
            return "";
        }

    }

    private void addTextWatch(TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    private void removeTextWatch(TextWatcher textWatcher) {
        editText.removeTextChangedListener(textWatcher);
    }

    @BindingAdapter("isEdit")
    public static void setEdit(LeftTextEditText leftTextEditText, Boolean isEdit) {
        leftTextEditText.setEdit(isEdit);
    }

    private void setEdit(Boolean isEdit) {
        this.isEdit = isEdit;
        if (isEdit){
            tvValue.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
        } else {
            tvValue.setVisibility(View.VISIBLE);
            editText.setVisibility(View.GONE);
        }
    }

    private boolean isEdit() {
        return isEdit;
    }

    @BindingAdapter("app:value")
    public static void setText(LeftTextEditText leftTextEditText, String text) {
        leftTextEditText.setText(text);
    }

    @InverseBindingAdapter(attribute = "app:value", event = "app:textAttrChanged")
    public static String getText(LeftTextEditText leftTextEditText) {
        return leftTextEditText.getText();
    }

    @BindingAdapter(value = "app:textAttrChanged", requireAll = false)
    public static void setListener(LeftTextEditText leftTextEditText, final InverseBindingListener listener) {
        if (listener != null) {
            TextWatcher newTextWatch = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    listener.onChange();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };
            TextWatcher oldTextWatch = ListenerUtil.trackListener(leftTextEditText, newTextWatch, R.id.textWatcher);
            if (oldTextWatch != null) {
                leftTextEditText.removeTextWatch(oldTextWatch);
            }
            leftTextEditText.addTextWatch(newTextWatch);
        }
    }
}