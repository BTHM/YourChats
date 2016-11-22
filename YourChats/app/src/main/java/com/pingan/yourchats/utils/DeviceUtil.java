package com.pingan.yourchats.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.pingan.yourchats.BaseApplication;

import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 设备信息工具类,用以获取屏幕分辨率,设备识别号等硬件信息
 */
public class DeviceUtil {
    private static int screenWidth = 0;
    private static int screenHeight = 0;
    private static int statusBarHeight = 0;

    public static boolean isTablet = false;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context,float dpValue) {
        LogUtils.i("BaseApplication="+BaseApplication.getInstance());
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    // 将px值转换为sp值，保证文字大小不变
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 获得状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            Class<?> c;
            Object obj;
            Field field;
            int x;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = BaseApplication.getInstance().getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
//
//            View view = activity.getWindow().getDecorView();
//            Rect rect = new Rect();
//            view.getWindowVisibleDisplayFrame(rect);
//            statusBarHeight = rect.top;
        }
        return statusBarHeight;
    }

    public static int getScreenWidth() {
        if (screenWidth == 0) {
            screenWidth = BaseApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
        }
        return screenWidth;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return BaseApplication.getInstance().getResources().getDisplayMetrics();
    }

    public static int getScreenHeight() {
        if (screenHeight == 0) {
            screenHeight = BaseApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
        }
        return screenHeight;
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 判断是否平板
     *
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
        double screenSize = diagonalPixels / (160 * dm.density);
        if (screenSize >= 6) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断sdk版本是否高于11.目前关系到属性动画的使用
     *
     * @return
     */
    public static boolean isHigherThanSDK11() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return true;
        }
        return false;
    }

    /**
     * 获取手机厂商
     *
     * @return
     */
    public static String getDeviceManufacturers() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);

    }
    /**
     * 获取屏幕长宽比
     * @param context
     * @return
     */
    public static float getScreenRate(Context context){
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H/W);
    }

    /**
     * 获取应用包名
     * @return
     */
    public static String getPackageName(){
        return BaseApplication.getInstance().getPackageName();
    }
    /**
     * 获取设备的IMEI
     */
    public static String getIMEI(Context context) {
        if (null == context) {
            return "";
        }
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return imei;
    }

    /**
     * 获取mac地址
     * @return
     *//*
    public static String getMacAddress() {

        String macAddress = null, ip = null;
        WifiManager wifiMgr = (WifiManager) BaseApplication.getInstance().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
        if (null != info) {
            macAddress = info.getMacAddress();
            ip = int2ip(info.getIpAddress());
        }
        System.out.println("mac:" + macAddress + ",ip:" + ip);
        return macAddress;
    }
*/

    /**
     * 获取手机ip地址
     *
     * @return
     */
    public static String getPhoneIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        // if (!inetAddress.isLoopbackAddress() && inetAddress
                        // instanceof Inet6Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }
    public static long ip2int(String ip) {
        String[] items = ip.split("\\.");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16
                | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }
    public static String int2ip(long ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }
}
