package com.ticketevent.util;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token){
        return "Hello " + name + ",\n\n" +
                "Your new account has been created. Please click on de link below to verify your account.\n\n" +
                getVerificationUrl(host, token) + "\n\nThe Support Team";

    }
    private static String getVerificationUrl(String host, String token) {
        return "http://" + host + "/verify/account?token=" + token;
    }




    public static String getResetPasswordMessage(String name, String host, String token){
        return "Hello " + name + ",\n\n" +
                "Your password has been changed. Please click on de link below to reset your password.\n\n" +
                getVerificationPasswordUrl(host, token) + "\n\nThe Support Team";

    }

    private static String getVerificationPasswordUrl(String host, String token) {
        return "http://" + host + "/verify/password?token=" + token;
    }
}
