package com.example.springbootweb.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

@Slf4j
public class IpUtil {

    public static String getHostIP() {
        String ipStr = "";
        InetAddress ip = null;
        try {
            boolean isFindIP = false;
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                if (isFindIP) {
                    break;
                }
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = ips.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                        isFindIP = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        if (null != ipStr) {
            ipStr = ip.getHostAddress();
        }
        return ipStr;
    }
}
