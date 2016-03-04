package com.tmw.tools.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmw.tools.bean.Advertisement;
import com.tmw.tools.bean.City;
import com.tmw.tools.bean.HomeNews;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 解析json的工具类
 */
public class ParseUtil {

    private static String letters[] ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    //解析城市json的方法，返回城市对象的集合
    public static List<City> parseJson(String jsonString){

        List<City> cityList = new ArrayList<City>();

        try {

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONObject cityJson =jsonObject.getJSONObject("cities");

            for (int i=0;i<letters.length;i++) {

                if (!cityJson.isNull(letters[i])) {//判断Json中是否包含某个字段

                    JSONArray jsonArray = cityJson.getJSONArray(letters[i]);

                    if (jsonArray != null) {

                        City city = new City(letters[i],0);

                        cityList.add(city);//先将标签字母加入到集合中

                        TypeToken<List<City>> tt = new TypeToken<List<City>>() {};

                        List<City> list = new Gson().fromJson(jsonArray.toString(), tt.getType());

                        cityList.addAll(list);
                    }

                }
            }
            return cityList;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    //解析首页新闻的方法
    public static List<HomeNews> parseNews(String jsonString){

        List<HomeNews> newsList = null;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            if (jsonObject.getInt("retcode")==0){

                JSONArray jsonArray = jsonObject.getJSONArray("data");

                TypeToken<List<HomeNews>> tt = new TypeToken<List<HomeNews>>() {};

                newsList = new Gson().fromJson(jsonArray.toString(), tt.getType());
            }

            return  newsList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Advertisement> parseAdvertisement(String jsonString){
        List<Advertisement> advertisementList = null;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            if (jsonObject.getInt("retcode")==0){

                JSONArray jsonArray = jsonObject.getJSONArray("data");

                TypeToken<List<Advertisement>> tt = new TypeToken<List<Advertisement>>() {};

                advertisementList = new Gson().fromJson(jsonArray.toString(), tt.getType());
            }

            return  advertisementList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static List<String> getNewsByJSON(String json){
        try {
            if (json!=null) {
                JSONObject jsonObject = new JSONObject(json);
                String state = jsonObject.getString("errorMessage");
                if (state.equals("success")) {
                    // jsonObject = jsonObject.getJSONObject("paramz");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    TypeToken<List<String>> tt = new TypeToken<List<String>>() {
                    };
                    return new Gson().fromJson(jsonArray.toString(),
                            tt.getType());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
