package com.kasroudra.kascalc;

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
import java.util.logging.*;
import android.view.*;
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
	private EditText txtCalc = null;
	private Button btnZero = null;
	private Button btnOne = null;
	private Button btnTwo = null;
	private Button btnThree = null;
	private Button btnFour = null;
	private Button btnFive = null;
	private Button btnSix = null;
	private Button btnSeven = null;
	private Button btnEight = null;
	private Button btnNine = null;
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
			btnNine.setTextSize(18);
			btnEight.setTextSize(18);
			btnSeven.setTextSize(18);
			btnSix.setTextSize(18);
			btnFive.setTextSize(18);
			btnFour.setTextSize(18);
			btnThree.setTextSize(18);
			btnTwo.setTextSize(18);
			btnOne.setTextSize(18);
			btnZero.setTextSize(18);
			btnDecimal.setTextSize(18);
			expSQ.setTextSize(18);
			expEX.setTextSize(18);
			nbtn1.setTextSize(18);
			nbtn2.setTextSize(18);
			nbtn3.setTextSize(18);
			nbtn4.setTextSize(18);
			nbtn5.setTextSize(18);
			nbtn6.setTextSize(18);
			nbtn7.setTextSize(18);
		}

		txtCalc.setTextColor(Color.BLACK);
		txtCalc.setKeyListener(null);

		btnZero.setTextColor(Color.WHITE);
		btnOne.setTextColor(Color.WHITE);
		btnTwo.setTextColor(Color.WHITE);
		btnThree.setTextColor(Color.WHITE);
		btnFour.setTextColor(Color.WHITE);
		btnFive.setTextColor(Color.WHITE);
		btnSix.setTextColor(Color.WHITE);
		btnSeven.setTextColor(Color.WHITE);
		btnEight.setTextColor(Color.WHITE);
		btnNine.setTextColor(Color.WHITE);
		btnPM.setTextColor(Color.WHITE);
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

		nbtn1.setTextColor(Color.WHITE);
		nbtn2.setTextColor(Color.WHITE);
		nbtn3.setTextColor(Color.WHITE);
		nbtn4.setTextColor(Color.WHITE);
		nbtn5.setTextColor(Color.WHITE);
		nbtn6.setTextColor(Color.WHITE);
		nbtn7.setTextColor(Color.WHITE);

	}

	private void initControls() {
		txtCalc = (EditText) findViewById(R.id.txtCalc);
		btnZero = (Button) findViewById(R.id.btnZero);
		btnOne = (Button) findViewById(R.id.btnOne);
		btnTwo = (Button) findViewById(R.id.btnTwo);
		btnThree = (Button) findViewById(R.id.btnThree);
		btnFour = (Button) findViewById(R.id.btnFour);
		btnFive = (Button) findViewById(R.id.btnFive);
		btnSix = (Button) findViewById(R.id.btnSix);
		btnSeven = (Button) findViewById(R.id.btnSeven);
		btnEight = (Button) findViewById(R.id.btnEight);
		btnNine = (Button) findViewById(R.id.btnNine);
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
		nbtn1 = (Button) findViewById(R.id.nbtn1);
		nbtn2 = (Button) findViewById(R.id.nbtn2);
		nbtn3 = (Button) findViewById(R.id.nbtn3);
		nbtn4 = (Button) findViewById(R.id.nbtn4);
		nbtn5 = (Button) findViewById(R.id.nbtn5);
		nbtn6 = (Button) findViewById(R.id.nbtn6);
		nbtn7 = (Button) findViewById(R.id.nbtn7);

		nbtn1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(12);

			}

		});

		nbtn2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(7);

			}

		});

		nbtn3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(8);

			}

		});

		nbtn4.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(9);

			}

		});

		nbtn5.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(10);

			}

		});

		nbtn6.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(11);

			}

		});

		nbtn7.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(13);

			}

		});

		btnZero.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(0);

			}

		});
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
		btnOne.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(1);
			}
		});

		btnTwo.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(2);
			}
		});
		btnThree.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(3);
			}
		});
		btnFour.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(4);
			}
		});
		btnFive.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(5);
			}
		});
		btnSix.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(6);
			}
		});
		btnSeven.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(7);
			}
		});
		btnEight.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(8);
			}
		});
		btnNine.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(9);
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
				num = num
						+ Math.sin(Double.parseDouble(txtCalc.getText()
								.toString()));
				break;
			case 8:
				num = num
						+ Math.cos(Double.parseDouble(txtCalc.getText()
								.toString()));
				break;
			case 9:
				num = num
						+ Math.tan(Double.parseDouble(txtCalc.getText()
								.toString()));
				break;
			case 10:
				num = Math
						.log(Double.parseDouble(txtCalc.getText().toString()));
				break;
			case 11:
				double loge = Math.log(Double.parseDouble(txtCalc.getText()
						.toString()));
				num = Math.exp(loge);
				break;
			case 12:
				num = Math.PI;
				break;
			case 13:
				num = Math.E;
				break;
			}

			String txt = Double.toString(num);
			txtCalc.setText(txt);
			txtCalc.setSelection(txt.length());

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

		txt = txt + Integer.toString(num);

		txtCalc.setText(txt);
		txtCalc.setKeyListener(null);
		txtCalc.setSelection(txt.length());

		hasChanged = true;
	}

	private void setValue(String value) {
		if (operator == 0)
			reset();

		if (readyToClear) {
			readyToClear = false;
		}

		txtCalc.setText(value);
		txtCalc.setSelection(value.length());

		hasChanged = true;
	}

	private void handleDecimal() {
		if (operator == 0)
			reset();

		if (readyToClear) {
			txtCalc.setText("0.");
			txtCalc.setSelection(2);
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
				txtCalc.setSelection(txt.length());
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
				txtCalc.setSelection(txt.length());
			}
		}
	}


	private void reset() {
		num = 0;
		txtCalc.setText("0");
		txtCalc.setSelection(1);
		operator = 1;
	}
}
