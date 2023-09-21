package com.kasroudra.calculator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.*;
public class Calculator extends Activity
{

    private final String SDK_VERSION = "1";
    private final int MENUITEM_CLOSE = 1;
    private final int MENUITEM_ABOUT = 2;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;


    /*
     * Edit Text and Button object initialization for simple calculator design.
     */

    private Button[] buttonArray; //button array instead of declaration instead of each one
    //button array represents numbers

    private Button[] functions;
    //button array represents functions like cos, sin ...

    //X ^ y has different id


    private EditText txtCalc = null;
    private Button btnPlus = null;
    private Button btnMinus = null;
    private Button btnMultiply = null;
    private Button btnDivide = null;
    private Button btnEquals = null;
    private Button btnC = null;
    private Button btnDecimal = null;
    private Button btnBS = null;
    private Button btnPerc = null;
    private Button btnSqrRoot = null;
    private Button btnPM = null;
    private Button expSQ = null;
    private Button expEX = null;
    private Button nbtn1 = null;
    private Button nbtn2 = null;
    private Button nbtn3 = null;
    private Button nbtn4 = null;
    private Button nbtn5 = null;
    private Button nbtn6 = null;
    private Button nbtn7 = null;

    private double num = 0;

    private double memNum = 0;
    private int operator = 1;
    // 0 = nothing, 1 = plus, 2 = minus, 3 =
    // multiply, 4 = divide
    private boolean readyToClear = false;
    private boolean hasChanged = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        this.setTitle(" ");

        initControls();
        initScreenLayout();
        reset();

    }

    private void initScreenLayout() {

        /*
         * The following three command lines you can use depending upon the
         * emulator device you are using.
         */

        // 320 x 480 (Tall Display - HVGA-P) [default]
        // 320 x 240 (Short Display - QVGA-L)
        // 240 x 320 (Short Display - QVGA-P)

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // this.showAlert(dm.widthPixels +" "+ dm.heightPixels, dm.widthPixels
        // +" "+ dm.heightPixels, dm.widthPixels +" "+ dm.heightPixels, false);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        if (height < 400 || width < 300) {
            txtCalc.setTextSize(20);
        }

        if (width < 300) {
            btnBS.setTextSize(18);
            btnDivide.setTextSize(18);
            btnPlus.setTextSize(18);
            btnMinus.setTextSize(18);
            btnMultiply.setTextSize(18);
            btnEquals.setTextSize(18);
            btnPM.setTextSize(18);
            btnPerc.setTextSize(18);
            btnC.setTextSize(18);
            btnSqrRoot.setTextSize(18);

            for(int i = 0 ; i < 10 ;i++)
            {
                buttonArray[i].setTextSize(18);
                if(i < 7)
                {
                    functions[i].setTextSize(18);
                }
            }

            btnDecimal.setTextSize(18);
            expSQ.setTextSize(18);
            expEX.setTextSize(18);
        }

        txtCalc.setTextColor(Color.BLACK);
        txtCalc.setKeyListener(null);


        for(int i = 0 ; i < 10 ;i++)
        {
            buttonArray[i].setTextColor(Color.WHITE);
            if( i < 7)
            {
                functions[i].setTextColor(Color.WHITE);
            }
        }

        btnDecimal.setTextColor(Color.WHITE);
        btnBS.setTextColor(Color.WHITE);
        btnC.setTextColor(Color.WHITE);
        btnPerc.setTextColor(Color.WHITE);
        btnSqrRoot.setTextColor(Color.WHITE);
        btnDivide.setTextColor(Color.WHITE);
        btnPlus.setTextColor(Color.WHITE);
        btnMinus.setTextColor(Color.WHITE);
        btnMultiply.setTextColor(Color.WHITE);
        btnEquals.setTextColor(Color.WHITE);
        expSQ.setTextColor(Color.WHITE);
        expEX.setTextColor(Color.WHITE);

    }

    private void initControls() {

        buttonArray = new Button[10]; //Button array for numbers 0-9

        functions = new Button[7]; //Button array for functions

        int buttonId, functionId;

        for(int i = 0 ; i < 10;i++)
        {
            buttonId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            System.out.println(buttonId);
            buttonArray[i] = findViewById(buttonId);

            int index = i;
            buttonArray[i].setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    handleNumber(index);
                }
            });

            if( i < 7 )
            {
                int id = i + 1;
                functionId = getResources().getIdentifier("nbtn"+ id,"id", getPackageName());
                functions[i] = findViewById(functionId);

                functions[i].setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        handleEquals(index + 7);
                    }
                });
            }

        }

        txtCalc = (EditText) findViewById(R.id.txtCalc);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnEquals = (Button) findViewById(R.id.btnEquals);
        btnC = (Button) findViewById(R.id.btnC);
        btnDecimal = (Button) findViewById(R.id.btnDecimal);
        btnBS = (Button) findViewById(R.id.btnBS);
        btnPerc = (Button) findViewById(R.id.btnPerc);
        btnSqrRoot = (Button) findViewById(R.id.btnSqrRoot);
        btnPM = (Button) findViewById(R.id.btnPM);
        expSQ = (Button) findViewById(R.id.expSQ);
        expEX = (Button) findViewById(R.id.expEX);

        expSQ.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                setValue(Double.toString(Math.cbrt(Double.parseDouble(txtCalc
                        .getText().toString()))));
            }

        });
        expEX.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(6);
            }

        });

        btnPlus.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(1);
            }
        });
        btnMinus.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(2);
            }
        });
        btnMultiply.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(3);
            }
        });
        btnDivide.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(4);
            }
        });
        btnEquals.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(0);
            }
        });
        btnC.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                reset();
            }
        });
        btnDecimal.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleDecimal();
            }
        });
        btnPM.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handlePlusMinus();
            }
        });

        btnBS.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleBackspace();
            }
        });
        btnSqrRoot.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                setValue(Double.toString(Math.sqrt(Double.parseDouble(txtCalc
                        .getText().toString()))));
            }
        });
        btnPerc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                setValue(Double.toString(num
                        * (0.01 * Double.parseDouble(txtCalc.getText()
                        .toString()))));
            }
        });

        txtCalc.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int i, android.view.KeyEvent e) {
                if (e.getAction() == KeyEvent.ACTION_DOWN) {
                    int keyCode = e.getKeyCode();

                    // txtCalc.append("["+Integer.toString(keyCode)+"]");

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_0:
                            handleNumber(0);
                            break;

                        case KeyEvent.KEYCODE_1:
                            handleNumber(1);
                            break;

                        case KeyEvent.KEYCODE_2:
                            handleNumber(2);
                            break;

                        case KeyEvent.KEYCODE_3:
                            handleNumber(3);
                            break;

                        case KeyEvent.KEYCODE_4:
                            handleNumber(4);
                            break;

                        case KeyEvent.KEYCODE_5:
                            handleNumber(5);
                            break;

                        case KeyEvent.KEYCODE_6:
                            handleNumber(6);
                            break;

                        case KeyEvent.KEYCODE_7:
                            handleNumber(7);
                            break;

                        case KeyEvent.KEYCODE_8:
                            handleNumber(8);
                            break;

                        case KeyEvent.KEYCODE_9:
                            handleNumber(9);
                            break;

                        case 43:
                            handleEquals(1);
                            break;

                        case KeyEvent.KEYCODE_EQUALS:
                            handleEquals(0);
                            break;

                        case KeyEvent.KEYCODE_MINUS:
                            handleEquals(2);
                            break;

                        case KeyEvent.KEYCODE_PERIOD:
                            handleDecimal();
                            break;

                        case KeyEvent.KEYCODE_C:
                            reset();
                            break;

                        case KeyEvent.KEYCODE_SLASH:
                            handleEquals(4);
                            break;

                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            return false;
                    }
                }

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, MENUITEM_CLOSE, "Close");
        menu.add(1, 2, MENUITEM_ABOUT, "About");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENUITEM_CLOSE:
                Calculator.this.finish();
                break;
            case MENUITEM_ABOUT:
                Toast.makeText(this, "Build with ♥ by Kas", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void handleEquals(int newOperator) {
        if (hasChanged) {
            switch (operator) {
                case 1:
                    num = num + Double.parseDouble(txtCalc.getText().toString());
                    break;
                case 2:
                    num = num - Double.parseDouble(txtCalc.getText().toString());
                    break;
                case 3:
                    num = num * Double.parseDouble(txtCalc.getText().toString());
                    break;
                case 4:
                    num = num / Double.parseDouble(txtCalc.getText().toString());
                    break;
                case 5:
                    num = Math.pow(num, 2);
                    break;
                case 6:
                    num = Math.pow(num,
                            Double.parseDouble(txtCalc.getText().toString()));
                    break;
                case 7:
                    num = Math.PI;
                    break;
                case 8:
                    num = num
                            + Math.sin(Double.parseDouble(txtCalc.getText()
                            .toString()));
                    break;
                case 9:
                    num = num
                            + Math.cos(Double.parseDouble(txtCalc.getText()
                            .toString()));
                    break;
                case 10:
                    num = Math
                            .tan(Double.parseDouble(txtCalc.getText().toString()));
                    break;
                case 11:
                    num = Math.log(Double.parseDouble(txtCalc.getText()
                            .toString()));
                    break;
                case 12:
                    num = Math.exp(Double.parseDouble(txtCalc.getText()
                            .toString()));
                    break;
                case 13:
                    num = Math.E;
                    break;
            }

            String txt = Double.toString(num);
            txtCalc.setText(txt);
//            txtCalc.setSelection(txt.length());

            readyToClear = true;
            hasChanged = false;
        }

        operator = newOperator;
    }

    private void handleNumber(int num) {
        if (operator == 0)
            reset();

        String txt = txtCalc.getText().toString();
        if (readyToClear) {
            txt = "";
            readyToClear = false;
        } else if (txt.equals("0"))
            txt = "";

        txt = txt + (num);

        txtCalc.setText(txt);
        txtCalc.setKeyListener(null);
//        txtCalc.setSelection(txt.length());

        hasChanged = true;
    }

    private void setValue(String value) {
        if (operator == 0)
            reset();

        if (readyToClear) {
            readyToClear = false;
        }

        if(value.length() > 16)
        {
            txtCalc.setText(value.substring(0,15));
        }

        txtCalc.setText(value);
//        txtCalc.setSelection(value.length());

        hasChanged = true;
    }

    private void handleDecimal() {
        if (operator == 0)
            reset();

        if (readyToClear) {
            txtCalc.setText("0.");
//            txtCalc.setSelection(2);
            readyToClear = false;
            hasChanged = true;
        } else {
            String txt = txtCalc.getText().toString();

            if (!txt.contains(".")) {
                txtCalc.append(".");
                hasChanged = true;
            }
        }
    }

    private void handleBackspace() {
        if (!readyToClear) {
            String txt = txtCalc.getText().toString();
            if (txt.length() > 0) {
                txt = txt.substring(0, txt.length() - 1);
                if (txt.equals(""))
                    txt = "0";

                txtCalc.setText(txt);
//                txtCalc.setSelection(txt.length());
            }
        }
    }

    private void handlePlusMinus() {
        if (!readyToClear) {
            String txt = txtCalc.getText().toString();
            if (!txt.equals("0")) {
                if (txt.charAt(0) == '-')
                    txt = txt.substring(1, txt.length());
                else
                    txt = "-" + txt;

                txtCalc.setText(txt);
//                txtCalc.setSelection(txt.length());
            }
        }
    }


    private void reset() {
        num = 0;
        txtCalc.setText("0");
//        txtCalc.setSelection(1);
        operator = 1;
    }
}
