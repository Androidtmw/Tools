package com.tmw.tools;

import android.app.Activity;
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

/**
 * 注册页面的Activity
 */
public class RegisterActivity extends Activity implements OnClickListener {

	private EditText unEditText;
	private EditText pwEditText;
	private MyDataBase myDataBase;
	private SQLiteDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		setFinishOnTouchOutside(false);
		
		Button regiserButton=(Button) findViewById(R.id.regiser_button);
		Button cancelButton=(Button) findViewById(R.id.register_cancel);
		
		unEditText = (EditText) findViewById(R.id.un);
		pwEditText = (EditText) findViewById(R.id.pw);
		
		regiserButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);

		myDataBase = new MyDataBase(this,"tools.db");
		database = myDataBase.getReadableDatabase();


	}

	@Override
	public void onClick(View v) {
		
		if (v.getId()==R.id.regiser_button) {
			
			if (TextUtils.isEmpty(unEditText.getText())||TextUtils.isEmpty(pwEditText.getText())) {
				Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
			}else{

				String un = unEditText.getText().toString();
				String pwd = pwEditText.getText().toString();

				if (notRepeated(un)){

					Intent intent = getIntent();
					intent.putExtra("username", un);
					intent.putExtra("password", pwd);

					setResult(1, intent);
					Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
					RegisterActivity.this.finish();
				}else {
					Toast.makeText(this, "该用户名已存在", Toast.LENGTH_SHORT).show();
				}
			}
			
		}else if (v.getId()==R.id.register_cancel) {
			
			RegisterActivity.this.finish();
		}
		
	}

	//判断用户名是否重复的方法
	private boolean notRepeated(String name){

		Cursor cursor = database.rawQuery("select username from userinfo", null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()){
			int columnIndex = cursor.getColumnIndex("username");
			String uName = cursor.getString(columnIndex);

			if (uName.equals(name)){
				return false;
			}

			cursor.moveToNext();
		}
		return true;
	}
}
