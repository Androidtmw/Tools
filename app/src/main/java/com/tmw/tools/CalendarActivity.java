package com.tmw.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


public class CalendarActivity extends Activity{

	private TextView textView[]=new TextView[42];
	private String year;
	private String month;
	private TextView textYear;
	private TextView textMonth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_calendar);
		
		textYear = (TextView) findViewById(R.id.year_text);
		textMonth = (TextView) findViewById(R.id.spinner_month);
		
		textView[0] = (TextView) findViewById(R.id.da_1);
		textView[1] = (TextView) findViewById(R.id.da_2);
		textView[2] = (TextView) findViewById(R.id.da_3);
		textView[3] = (TextView) findViewById(R.id.da_4);
		textView[4] = (TextView) findViewById(R.id.da_5);
		textView[5] = (TextView) findViewById(R.id.da_6);
		textView[6] = (TextView) findViewById(R.id.da_7);
		textView[7] = (TextView) findViewById(R.id.da_8);
		textView[8] = (TextView) findViewById(R.id.da_9);
		textView[9] = (TextView) findViewById(R.id.da_10);
		textView[10] = (TextView) findViewById(R.id.da_11);
		textView[11] = (TextView) findViewById(R.id.da_12);
		textView[12] = (TextView) findViewById(R.id.da_13);
		textView[13] = (TextView) findViewById(R.id.da_14);
		textView[14] = (TextView) findViewById(R.id.da_15);
		textView[15] = (TextView) findViewById(R.id.da_16);
		textView[16] = (TextView) findViewById(R.id.da_17);
		textView[17] = (TextView) findViewById(R.id.da_18);
		textView[18] = (TextView) findViewById(R.id.da_19);
		textView[19] = (TextView) findViewById(R.id.da_20);
		textView[20] = (TextView) findViewById(R.id.da_21);
		textView[21] = (TextView) findViewById(R.id.da_22);
		textView[22] = (TextView) findViewById(R.id.da_23);
		textView[23] = (TextView) findViewById(R.id.da_24);
		textView[24] = (TextView) findViewById(R.id.da_25);
		textView[25] = (TextView) findViewById(R.id.da_26);
		textView[26] = (TextView) findViewById(R.id.da_27);
		textView[27] = (TextView) findViewById(R.id.da_28);
		textView[28] = (TextView) findViewById(R.id.da_29);
		textView[29] = (TextView) findViewById(R.id.da_30);
		textView[30] = (TextView) findViewById(R.id.da_31);
		textView[31] = (TextView) findViewById(R.id.da_32);
		textView[32] = (TextView) findViewById(R.id.da_33);
		textView[33] = (TextView) findViewById(R.id.da_34);
		textView[34] = (TextView) findViewById(R.id.da_35);
		textView[35] = (TextView) findViewById(R.id.da_36);
		textView[36] = (TextView) findViewById(R.id.da_37);
		textView[37] = (TextView) findViewById(R.id.da_38);
		textView[38] = (TextView) findViewById(R.id.da_39);
		textView[39] = (TextView) findViewById(R.id.da_40);
		textView[40] = (TextView) findViewById(R.id.da_41);
		textView[41] = (TextView) findViewById(R.id.da_42);
		
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		year = sdf.format(date);
		SimpleDateFormat sdf2=new SimpleDateFormat("MM");
		month = sdf2.format(date);

		int monthInt = Integer.valueOf(month);
		
		textYear.setText(year);
		textMonth.setText(monthInt+"");
		
		show(year, month);
				
	}

	
	public boolean judge(int x){
		if(x%4==0&&x%100!=0||x%400==0){
			return true;
		}else{
			return false;
		}
	}
	//此方法用来确认指定月份的天数（不考虑闰年）
	public int monthDay(int x){
		if(x==0){
			return 0;
		}else if(x==2){
			return 28;
		}else if(x==4||x==6||x==9||x==11){
			return 30;
		}else{
			return 31;
		}
	}
	
	
	public void show(String str1,String str2) {
		if(TextUtils.isEmpty(str1)){
			Toast.makeText(this, "年份不能为空", Toast.LENGTH_SHORT).show();
		}else{
			int year=Integer.valueOf(str1);
			
			int month=Integer.valueOf(str2);

			int length=textView.length;
			for(int i=0;i<length;i++){
				textView[i].setText("");
			}
			
			int dayOfMonth=0;//
			//判断输入的月份有多少天
			if(month==2){
				if(judge(year)){
					dayOfMonth=29;
				}else{
					dayOfMonth=28;
				}
				
			}else if(month==4||month==6||month==9||month==11){
				dayOfMonth=30;
			}else{
				dayOfMonth=31;
			}

			int count=0;//用来接收闰年的个数

			for(int i=1900;i<year;i++){//这里犯了一个错误，<=哈哈。。。
				if(judge(i)){
					count++;
				}
			}
			//计算从1900年到用户输入年份之间共有多少天
			int sumDay1=366*count+(year-1900-count)*365;
			//计算从用户输入年份的第一天到本月之间共有多少天
			int sumDay2=0;
			for (int i=0;i<month;i++){
				sumDay2=sumDay2+monthDay(i);
			}
			if(month>2&&judge(year)==true){
				sumDay2=sumDay2+1;
			}
			int sumDay=sumDay1+sumDay2;//计算总天数
			int startDay=sumDay%7;//计算用户输入月份的第一天是星期几
			//输出用户输入月份的日历
			if(startDay!=6){
				for(int i=0;i<=startDay;i++){
					textView[i].setText("");
				}	
				for (int i = 1; i <= dayOfMonth; i++) {
					textView[startDay+i].setText(i + "");
				}
			}else{
				for (int i = 1; i <= dayOfMonth; i++) {
					textView[i-1].setText(i + "");
				}
			}
			
		}	
	}
	
	public void yearChange1(View v) {
		
		int x=Integer.valueOf(year)+1;
		year=String.valueOf(x);
		textYear.setText(year);
		show(year, month);			
	}
	public void yearChange2(View v) {
		int x=Integer.valueOf(year)-1;
		year=String.valueOf(x);
		textYear.setText(year);
		show(year, month);
	}
	public void monthChange1(View v) {
		
		int x=Integer.valueOf(month);
		
		if(x==12){
			x=1;
			int y=Integer.valueOf(year)+1;
			year=String.valueOf(y);
			textYear.setText(year);
		}else{
			x++;
		}
		month=String.valueOf(x);
		textMonth.setText(month);
		show(year, month);
	}
	
	public void monthChange2(View v){
		
		int x=Integer.valueOf(month);
		
		if(x==1){
			x=12;
			int y=Integer.valueOf(year)-1;
			year=String.valueOf(y);
			textYear.setText(year);
		}else{
			x--;
		}
		month=String.valueOf(x);
		textMonth.setText(month);
		show(year, month);
	}
}
