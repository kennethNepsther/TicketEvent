package com.ticketevent.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
public class UrlUtils {

    public static String getVerificationUrl(String host, String token) {
        return "http://" + host + "/user/verify/account?token=" + token;
    }

    public static String getVerificationPasswordUrl(String host, String token) {
        return "http://" + host + "/verify/password?token=" + token;
    }

    public static String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public static URI addIdToCurrentUrlPath(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
