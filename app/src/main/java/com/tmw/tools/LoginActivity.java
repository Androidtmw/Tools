package com.tmw.tools;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.tmw.tools.base.BaseActivity;
import com.tmw.tools.database.MyDataBase;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.ShareUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 登录页面的Activity
 * 增加密码验证功能
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private CheckBox rememberCheckBox;
    private CheckBox autoCheckBox;
    private Button button;
    private EditText unEditText;
    private EditText pwEditText;

    private MyDataBase myDataBase;
    private Cursor cursor;
    private SQLiteDatabase database;

    @Override
    protected int contentView() {

        return R.layout.activity_login;
    }

    @Override
    protected void init() {

        rememberCheckBox = (CheckBox) findViewById(R.id.cb_remember);
        autoCheckBox = (CheckBox) findViewById(R.id.cb_auto);
        autoCheckBox.setOnCheckedChangeListener(this);

        button = (Button) findViewById(R.id.login_button);
        button.setOnClickListener(this);

        unEditText = (EditText) findViewById(R.id.un_text);
        pwEditText = (EditText) findViewById(R.id.pw_text);

        myDataBase = new MyDataBase(this,"tools.db");
    }


    @Override
    protected void loadData() {
        //初始化时创建或打开一个数据库对象
        database = myDataBase.getReadableDatabase();

        //读取共享参数,包括记住密码，自动登录等信息
        String name = ShareUtil.getString(Constant.KEY.UN);
        String password = ShareUtil.getString(Constant.KEY.PWD);
        boolean isRemember = ShareUtil.getBoolean("isRemember");
        boolean isAuto = ShareUtil.getBoolean("isAuto");

        if (name!=null){
            unEditText.setText(name);
        }
        if (password!=null){
            pwEditText.setText(password);
        }

        if (isRemember){
            rememberCheckBox.setChecked(true);
        }
        if (isAuto){
            autoCheckBox.setChecked(true);
            login();//如果选择了自动登录，就直接调用登录方法
        }
    }

    /**
     * 登录按钮的回调方法，判断后登录
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(unEditText.getText())||TextUtils.isEmpty(pwEditText.getText())){

            Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();

        }else if (isRight()&&rememberCheckBox.isChecked()&&autoCheckBox.isChecked()){

            ShareUtil.putString(unEditText.getText().toString(),Constant.KEY.UN);
            ShareUtil.putString(pwEditText.getText().toString(),Constant.KEY.PWD);
            ShareUtil.putBoolean("isRemember", true);
            ShareUtil.putBoolean("isAuto", true);
            login();

        }else if (isRight()&&rememberCheckBox.isChecked()){
            ShareUtil.putString(unEditText.getText().toString(),Constant.KEY.UN);
            ShareUtil.putString(pwEditText.getText().toString(),Constant.KEY.PWD);
            ShareUtil.putBoolean("isRemember", true);
            ShareUtil.putBoolean("isAuto", false);
            login();

        }else if (isRight()){
            ShareUtil.putString(unEditText.getText().toString(),Constant.KEY.UN);
            ShareUtil.putString(null, Constant.KEY.PWD);
            ShareUtil.putBoolean("isRemember", false);
            ShareUtil.putBoolean("isAuto", false);
            login();
        }else {
            Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            ShareUtil.putBoolean("isRemember", false);
            ShareUtil.putBoolean("isAuto", false);
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            rememberCheckBox.setChecked(true);
        }
    }

    /**
     * 判断用户名和密码是否正确的方法
     * @return
     */
    private boolean isRight(){

        //查询数据库返回cursor对象
        cursor = database.rawQuery("select * from userinfo", null);

        String str1 = unEditText.getText().toString();
        String str2 = pwEditText.getText().toString();

        if (str1.equals("admin")&&str2.equals("admin")){

            return true;
        }

        //循环读取数据并判断
        if (cursor!=null){
            cursor.moveToFirst();
            //循环读取数据
            while (!cursor.isAfterLast()) {
                //获得列的下标
                int nameIndex = cursor.getColumnIndex("username");

                //参数是:要查询的列的下标
                String name = cursor.getString(nameIndex);

                //获得列的下标
                int pwIndex = cursor.getColumnIndex("password");

                //参数是:要查询的列的下标：
                String pw = cursor.getString(pwIndex);

                if (str1.equals(name)&&str2.equals(pw)){
                    return true;
                }
                //移动游标到下一条
                cursor.moveToNext();
            }
        }

        cursor.close();

        return false;
    }

    //登录方法
    private void login(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constant.KEY.UN,unEditText.getText().toString());
        startActivity(intent);
        Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
        database.close();
        finish();
    }

    //注册事件的回调方法
    public void regiser(View view) {

        Intent intent=new Intent(this, RegisterActivity.class);

        startActivityForResult(intent, 1);

    }

    //接收注册成功后返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data!=null) {
            //接收注册的用户名和密码
            String un=data.getStringExtra(Constant.KEY.UN);
            String pw=data.getStringExtra(Constant.KEY.PWD);

            //将用户名和密码添加至本地数据库
            ContentValues values = new ContentValues();
            values.put("username",un);
            values.put("password",pw);

            database.insert("userinfo",null,values);

            //另一种方式，用流写入本地文件
            /*try {
                //将用户名和密码写入本地存储
                OutputStream os=openFileOutput("userinfo.txt", Context.MODE_PRIVATE);
                DataOutputStream dos=new DataOutputStream(os);
                dos.writeUTF(un);
                dos.writeUTF(pw);
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //读取用户名和密码
            try {
                InputStream is=openFileInput("userinfo.txt");
                DataInputStream dis=new DataInputStream(is);

                unString = dis.readUTF();
                pwString = dis.readUTF();
            }  catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}
