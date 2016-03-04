package com.tmw.tools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends Activity {
	
	private String first="";
    private String second="";
    private String sum="";
    private int fuhao=0;
	private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        textView=(TextView) findViewById(R.id.content);
    }
    
    public void print(View view){
    	
    	if(!sum.equals("")&&fuhao==0){
    		first="";
    		second="";
    		sum="";
    		Button button=(Button) findViewById(view.getId());
    		first+=(String) button.getText();
    		textView.setText(first);
    	}else{
    		if(fuhao==0){
        		Button button=(Button) findViewById(view.getId());
        		first+=(String) button.getText();
        		textView.setText(first);
        	}else{
        		Button button=(Button) findViewById(view.getId());
        		second+=(String) button.getText();
        		textView.setText(second);
        	}
    	}
    }  	
    	public void print1(View View){
        	
        	switch (View.getId()) {
				case R.id.cheng:
					if(fuhao!=0&&!first.equals("")&&!second.equals("")){
						print2(View);
					}
					fuhao = 3;
					break;
				case R.id.chu:
					if(fuhao!=0&&!first.equals("")&&!second.equals("")){
						print2(View);
					}
					fuhao = 4;
					break;
				case R.id.jian:
					if(fuhao!=0&&!first.equals("")&&!second.equals("")){
						print2(View);
					}
					fuhao = 2;
					break;
				case R.id.plus:
					if(fuhao!=0&&!first.equals("")&&!second.equals("")){
						print2(View);
					}
					fuhao = 1;
					break;
				
				case R.id.del:
					first = "";
					second = "";
					sum = "";
					fuhao = 0;
					textView.setText(null);
					break;
				case R.id.point:
					if (fuhao == 0) {
						first += ".";
						textView.setText(first);
					} else {
						second += ".";
						textView.setText(second);
					}
					break;
				case R.id.hehe:
					Toast.makeText(CalculatorActivity.this, "哈哈哈哈哈", Toast.LENGTH_SHORT)
							.show();
					break;
				default:
					break;
			}
    	}
    	
    	public void print2(View View){
			if (second.equals("")) {
				
			}else if(first.equals("")){
				String temp=new String(second);
				first=temp;
				second="";
				fuhao=0;
			}else {
				if (fuhao == 1) {
					double value = Double.valueOf(first)
							+ Double.valueOf(second);
					sum = String.valueOf(value);
					first = String.valueOf(value);
					second = "";
				} else if (fuhao == 2) {
					double value = Double.valueOf(first)
							- Double.valueOf(second);
					sum = String.valueOf(value);
					first = String.valueOf(value);
					second = "";
				} else if (fuhao == 3) {
					double value = Double.valueOf(first)
							* Double.valueOf(second);
					sum = String.valueOf(value);
					first = String.valueOf(value);
					second = "";
				} else if (fuhao == 4) {
					double value = Double.valueOf(first)
							/ Double.valueOf(second);
					sum = String.valueOf(value);
					first = String.valueOf(value);
					second = "";
				} else {
					sum = "";
				}
				if(sum.endsWith(".0")){
					sum=sum.substring(0, sum.indexOf("."));
				}
				textView.setText(String.valueOf(sum));
				fuhao=0;
			}

    	}
}
