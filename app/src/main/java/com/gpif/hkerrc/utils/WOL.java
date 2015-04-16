package com.gpif.hkerrc.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WOL {
    public int port;
    public String ip;
    public String mac;

    public WOL(String _ip, String _mac, String _port) {
        ip   = _ip;
        mac  = _mac;
        port = Integer.parseInt(_port);
    }

    public WOL(String _ip, String _mac) {
        ip  = _ip;
        mac = _mac;
        port = 9;
    }

    public int send() {
        String ipStr = this.ip;
        String macStr = this.mac;

        try {
            byte[] macBytes = getMacBytes(macStr);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }

            InetAddress address = InetAddress.getByName(ipStr);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();

            System.out.println("Wake-on-LAN packet sent.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }

}
