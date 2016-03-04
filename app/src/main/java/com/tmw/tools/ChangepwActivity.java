package com.tmw.tools;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tmw.tools.database.MyDataBase;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.ShareUtil;

/**
 * 修改密码Activity
 */
public class ChangepwActivity extends Activity implements OnClickListener {

	private EditText pwEditText;
	private EditText changeEditText;
	private EditText sureEditText;

	private MyDataBase myDataBase;
	private SQLiteDatabase database;
	private String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change);
		
		setFinishOnTouchOutside(false);//设置不能点击空白
		
		Button sureButton=(Button) findViewById(R.id.sure_button);
		Button cancelButton=(Button) findViewById(R.id.change_cancel);

		pwEditText = (EditText) findViewById(R.id.pwchange);
		changeEditText = (EditText) findViewById(R.id.pwnew);
		sureEditText = (EditText) findViewById(R.id.pwnewsure);
		
		sureButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);

		myDataBase = new MyDataBase(this,"tools.db");
		database = myDataBase.getReadableDatabase();

		username = getIntent().getStringExtra(Constant.KEY.UN);//接收传递过来的用户名

	}

	@Override
	public void onClick(View v) {

		//查询当前用户名所对应的密码
		Cursor cursor = database.rawQuery("select * from userinfo where username=?",new String[]{username});
		cursor.moveToFirst();

		int num = cursor.getColumnIndex("password");
		String pw = cursor.getString(num);

		if (v.getId()==R.id.sure_button) {
			
			if (TextUtils.isEmpty(pwEditText.getText())||TextUtils.isEmpty(changeEditText.getText())||TextUtils.isEmpty(sureEditText.getText())) {
				Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
			}else{
				String oldPw = pwEditText.getText().toString();
				String newPw = changeEditText.getText().toString();
				String surePw = sureEditText.getText().toString();

				if (!(oldPw.equals(pw))){
					Toast.makeText(this,"密码输入错误",Toast.LENGTH_SHORT).show();
				}else if (!(newPw.equals(surePw))){
					Toast.makeText(this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
				}else {

					ContentValues values = new ContentValues();
					values.put("password", changeEditText.getText().toString());
					database.update("userinfo",
							values, //新的值
							"username=?", //条件
							new String[]{username});//对应的字段名

					database.close();

					//注销时清除保留的记录
					ShareUtil.putString(null, Constant.KEY.PWD);
					ShareUtil.putBoolean("isRemember", false);
					ShareUtil.putBoolean("isAuto", false);

					Toast.makeText(this,"修改成功，请重新登录",Toast.LENGTH_SHORT).show();

					startActivity(new Intent(this, LoginActivity.class));

					finish();
				}
			}
			
		}else if (v.getId()==R.id.change_cancel) {
			
			finish();
		}
		
	}

}
