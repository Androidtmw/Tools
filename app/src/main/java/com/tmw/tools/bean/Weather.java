package com.tmw.tools.bean;

import java.util.List;

/**
 * Created by tmw on 2016/2/19.
 */
public class Weather {


    /**
     * desc : OK
     * status : 1000
     * data : {"wendu":"3","ganmao":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。","forecast":[{"fengxiang":"北风","fengli":"3-4级","high":"高温 8℃","type":"多云","low":"低温 -4℃","date":"19日星期五"},{"fengxiang":"无持续风向","fengli":"3-4级","high":"高温 4℃","type":"晴","low":"低温 -5℃","date":"20日星期六"},{"fengxiang":"无持续风向","fengli":"微风级","high":"高温 6℃","type":"晴","low":"低温 -2℃","date":"21日星期天"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 9℃","type":"晴","low":"低温 -2℃","date":"22日星期一"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 4℃","type":"晴","low":"低温 -3℃","date":"23日星期二"}],"yesterday":{"fl":"4-5级","fx":"北风","high":"高温 9℃","type":"晴","low":"低温 -2℃","date":"18日星期四"},"aqi":"40","city":"北京"}
     */

    private String desc;
    private int status;
    /**
     * wendu : 3
     * ganmao : 昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。
     * forecast : [{"fengxiang":"北风","fengli":"3-4级","high":"高温 8℃","type":"多云","low":"低温 -4℃","date":"19日星期五"},{"fengxiang":"无持续风向","fengli":"3-4级","high":"高温 4℃","type":"晴","low":"低温 -5℃","date":"20日星期六"},{"fengxiang":"无持续风向","fengli":"微风级","high":"高温 6℃","type":"晴","low":"低温 -2℃","date":"21日星期天"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 9℃","type":"晴","low":"低温 -2℃","date":"22日星期一"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 4℃","type":"晴","low":"低温 -3℃","date":"23日星期二"}]
     * yesterday : {"fl":"4-5级","fx":"北风","high":"高温 9℃","type":"晴","low":"低温 -2℃","date":"18日星期四"}
     * aqi : 40
     * city : 北京
     */

    private DataEntity data;

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String wendu;
        private String ganmao;
        /**
         * fl : 4-5级
         * fx : 北风
         * high : 高温 9℃
         * type : 晴
         * low : 低温 -2℃
         * date : 18日星期四
         */

        private YesterdayEntity yesterday;
        private String aqi;
        private String city;
        /**
         * fengxiang : 北风
         * fengli : 3-4级
         * high : 高温 8℃
         * type : 多云
         * low : 低温 -4℃
         * date : 19日星期五
         */

        private List<ForecastEntity> forecast;

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public void setYesterday(YesterdayEntity yesterday) {
            this.yesterday = yesterday;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setForecast(List<ForecastEntity> forecast) {
            this.forecast = forecast;
        }

        public String getWendu() {
            return wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public YesterdayEntity getYesterday() {
            return yesterday;
        }

        public String getAqi() {
            return aqi;
        }

        public String getCity() {
            return city;
        }

        public List<ForecastEntity> getForecast() {
            return forecast;
        }

        public static class YesterdayEntity {
            private String fl;
            private String fx;
            private String high;
            private String type;
            private String low;
            private String date;

            public void setFl(String fl) {
                this.fl = fl;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getFl() {
                return fl;
            }

            public String getFx() {
                return fx;
            }

            public String getHigh() {
                return high;
            }

            public String getType() {
                return type;
            }

            public String getLow() {
                return low;
            }

            public String getDate() {
                return date;
            }
        }

        public static class ForecastEntity {
            private String fengxiang;
            private String fengli;
            private String high;
            private String type;
            private String low;
            private String date;

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public String getFengli() {
                return fengli;
            }

            public String getHigh() {
                return high;
            }

            public String getType() {
                return type;
            }

            public String getLow() {
                return low;
            }

            public String getDate() {
                return date;
            }
        }
    }
}
