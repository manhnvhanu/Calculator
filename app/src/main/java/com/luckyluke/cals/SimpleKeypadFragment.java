package com.luckyluke.cals;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
public class SimpleKeypadFragment extends Fragment implements View.OnClickListener {

    Button plus;
    Button sub;
    Button mul;
    Button div;
    ImageButton backspace;
    Button leftParen;
    Button rightParen;
    ImageButton clearAll;
    ImageButton equals;

    Button one, two, three, four, five, six, seven, eight, nine, zero, dot;

    Evaluator mEvaluator = new Evaluator();

    String mArithmeticStr;

    String result;

    EditText input;

    TextView output;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.simple_keypad, container, false);

        // Find button ID
        zero = (Button) v.findViewById(R.id.digit0);
        one = (Button) v.findViewById(R.id.digit1);
        two = (Button) v.findViewById(R.id.digit2);
        three = (Button) v.findViewById(R.id.digit3);
        four = (Button) v.findViewById(R.id.digit4);
        five = (Button) v.findViewById(R.id.digit5);
        six = (Button) v.findViewById(R.id.digit6);
        seven = (Button) v.findViewById(R.id.digit7);
        eight = (Button) v.findViewById(R.id.digit8);
        nine = (Button) v.findViewById(R.id.digit9);
        dot = (Button) v.findViewById(R.id.dot);

        //Operation ID
        plus = (Button) v.findViewById(R.id.plus);
        sub = (Button) v.findViewById(R.id.sub);
        mul = (Button) v.findViewById(R.id.mul);
        div = (Button) v.findViewById(R.id.div);
        leftParen = (Button) v.findViewById(R.id.leftParen);
        rightParen = (Button) v.findViewById(R.id.rightParen);
        equals = (ImageButton) v.findViewById(R.id.equal);


        //Clear input
        backspace = (ImageButton) v.findViewById(R.id.backspace);
        clearAll = (ImageButton) v.findViewById(R.id.clearAll);


        //Set on Click ListenSer
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        dot.setOnClickListener(this);

        plus.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);

        clearAll.setOnClickListener(this);
        backspace.setOnClickListener(this);

        leftParen.setOnClickListener(this);
        rightParen.setOnClickListener(this);

        equals.setOnClickListener(this);


        return v;
    }

    public static SimpleKeypadFragment newInstance(String text) {

        SimpleKeypadFragment f = new SimpleKeypadFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onClick(View v) {
//Find id
        final EditText input = (EditText) getActivity().findViewById(R.id.input);
        final TextView output = (TextView) getActivity().findViewById(R.id.output);

        int start = input.getSelectionStart(); //this is to get the the cursor position
        int curPostion;

        switch (v.getId()) {

            case R.id.dot:
                input.getText().insert(start, ".");
                break;

            case R.id.digit0:
                input.getText().insert(start, "0");
                break;
            case R.id.digit1:
                input.getText().insert(start, "1");
                break;

            case R.id.digit2:
                input.getText().insert(start, "2");
                break;

            case R.id.digit3:
                input.getText().insert(start, "3");
                break;

            case R.id.digit4:
                input.getText().insert(start, "4");
                break;

            case R.id.digit5:
                input.getText().insert(start, "5");
                break;

            case R.id.digit6:
                input.getText().insert(start, "6");
                break;

            case R.id.digit7:
                input.getText().insert(start, "7");
                break;

            case R.id.digit8:
                input.getText().insert(start, "8");
                break;

            case R.id.digit9:
                input.getText().insert(start, "9");
                break;

            //Operation set up
            case R.id.plus:
                input.getText().insert(start, " + ");
                break;

            case R.id.sub:
                input.getText().insert(start, " - ");
                break;

            case R.id.mul:
                input.getText().insert(start, "*");
                break;

            case R.id.div:
                input.getText().insert(start, "/");
                break;

            case R.id.leftParen:
                input.getText().insert(start, "(");
                break;

            case R.id.rightParen:
                input.getText().insert(start, ")");
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

                    int afterDelPosition = curPostion - 1;

                    input.setText(selectedStr);

                    input.setSelection(afterDelPosition);

                } catch (Exception ignored) {

                }
                //getting cursor position

                break;

            case R.id.equal:
                try {
                    mArithmeticStr = input.getText().toString();
                    result = mEvaluator.evaluate(mArithmeticStr);
                    output.setText(result);
                } catch (EvaluationException e) {
                    Toast.makeText(getActivity(), "Invalid Arithmethic", Toast.LENGTH_LONG).show();
                }

                break;

        }
    }
}
