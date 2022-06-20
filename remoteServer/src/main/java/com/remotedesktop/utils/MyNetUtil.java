package com.remotedesktop.utils;

import cn.hutool.core.net.NetUtil;

public class MyNetUtil extends NetUtil {
    public static boolean isValidMac(String macStr) {
        if (macStr == null || macStr.equals("")) {
            return false;
        }
        String macAddressRule = "([A-Fa-f0-9]{2}[-,:]){5}[A-Fa-f0-9]{2}";
        // 这是真正的MAC地址；正则表达式；
        if (macStr.matches(macAddressRule)) {
            return true;
        } else {
            return false;
        }
    }
}
