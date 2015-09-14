package com.luckyluke.cals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

/**
 * Created by Lucky Luke on 20/08/2015.
 */
public class AdvancedKeypadFragment extends Fragment implements View.OnClickListener {

    Button abs, ran, leftParen, rightParen, min, max, exp, sin, cos, arcsin, arccos, tan, arctan, sqrt, log, ln, pi;
    private TextView output;

    private EditText input;
    ImageButton equal, backspace, clearAll;

    Evaluator mEvaluator = new Evaluator();

    String mArithmeticStr;

    String result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.advance_keypad, container, false);

        //Find id
        abs = (Button) v.findViewById(R.id.abs);
        ran = (Button) v.findViewById(R.id.ran);

        sin = (Button) v.findViewById(R.id.sin);
        cos = (Button) v.findViewById(R.id.cos);
        arcsin = (Button) v.findViewById(R.id.asin);
        arccos = (Button) v.findViewById(R.id.acos);
        tan = (Button) v.findViewById(R.id.tan);
        arctan = (Button) v.findViewById(R.id.atan);

        log = (Button) v.findViewById(R.id.log);
        ln = (Button) v.findViewById(R.id.ln);

        leftParen = (Button) v.findViewById(R.id.leftParen);
        rightParen = (Button) v.findViewById(R.id.rightParen);

        min = (Button) v.findViewById(R.id.min);
        max = (Button) v.findViewById(R.id.max);

        exp = (Button) v.findViewById(R.id.exp);
        pi = (Button) v.findViewById(R.id.pi);


        backspace = (ImageButton) v.findViewById(R.id.backspace);
        clearAll = (ImageButton) v.findViewById(R.id.clearAll);

        sqrt = (Button) v.findViewById(R.id.sqrt);
        equal = (ImageButton) v.findViewById(R.id.equal);

        //Onclick listener

        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        arcsin.setOnClickListener(this);
        arccos.setOnClickListener(this);
        arctan.setOnClickListener(this);

        ran.setOnClickListener(this);
        abs.setOnClickListener(this);

        pi.setOnClickListener(this);
        exp.setOnClickListener(this);

        sqrt.setOnClickListener(this);
        equal.setOnClickListener(this);

        log.setOnClickListener(this);
        ln.setOnClickListener(this);
        leftParen.setOnClickListener(this);
        rightParen.setOnClickListener(this);

        clearAll.setOnClickListener(this);
        backspace.setOnClickListener(this);
        min.setOnClickListener(this);
        max.setOnClickListener(this);

        return v;
    }

    public static AdvancedKeypadFragment newInstance(String text) {

        AdvancedKeypadFragment f = new AdvancedKeypadFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onClick(View v) {
// TODO Auto-generated method stub
        input = (EditText) getActivity().findViewById(R.id.input);
        output = (TextView) getActivity().findViewById(R.id.output);


        int start = input.getSelectionStart(); //this is to get the the cursor position
        int curPostion;

        switch (v.getId()) {

            case R.id.abs:
                input.getText().insert(start, " abs(");
                break;

            case R.id.ran:
                input.getText().insert(start, " random()");
                break;

            case R.id.sin:
                input.getText().insert(start, " sin(");
                break;

            case R.id.cos:
                input.getText().insert(start, " cos(");
                break;

            case R.id.asin:
                input.getText().insert(start, " asin(");
                break;

            case R.id.acos:
                input.getText().insert(start, " acos(");
                break;

            case R.id.tan:
                input.getText().insert(start, " tan(");
                break;

            case R.id.atan:
                input.getText().insert(start, " atan(");
                break;


            case R.id.min:
                input.getText().insert(start, "min(,)");
                break;

            case R.id.max:
                input.getText().insert(start, "max(,)");
                break;

            case R.id.log:
                input.getText().insert(start, " log(");
                break;

            case R.id.ln:
//                input.getText().insert(start, " ln(");
                break;

            case R.id.pi:
                input.getText().insert(start, " 3.14");
                break;

            case R.id.exp:
                input.getText().insert(start, " exp(");
                break;

            case R.id.sqrt:
                input.getText().insert(start, " sqrt(");
                break;

            //Modify setup
            case R.id.clearAll:
                input.setText("");
                output.setText("");
                break;

            case R.id.backspace:

                try {
                    curPostion = input.getSelectionEnd();

                    //getting the selected Text
                    SpannableStringBuilder selectedStr = new SpannableStringBuilder(input.getText());

                    //replacing the selected text with empty String and setting it to EditText
                    selectedStr.replace(curPostion - 1, curPostion, "");

                    input.setText(selectedStr);
                    input.setSelection(selectedStr.length());
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Nothing to del", Toast.LENGTH_LONG).show();
                }
                //getting cursor position

                break;

            case R.id.leftParen:
                input.getText().insert(start, " (");
                break;

            case R.id.rightParen:
                input.getText().insert(start, ") ");
                break;

            case R.id.equal:
                try {
                    mArithmeticStr = input.getText().toString();
                    result = mEvaluator.evaluate(mArithmeticStr);
                    output.setText(result);
                } catch (EvaluationException e) {
                    Toast.makeText(getActivity(), "Not able to compute! Invalid Arithmethic", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
