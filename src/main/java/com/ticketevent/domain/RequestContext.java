package com.ticketevent.domain;

import java.util.UUID;

public class RequestContext {
    private  static final ThreadLocal<UUID> USER_ID = new ThreadLocal<>();
    private RequestContext() {}

    private static void start() {
        USER_ID.remove();

    }

    public static UUID getUserId() {
        return USER_ID.get();
    }
    public static void setUserId(UUID userId) {
        USER_ID.set(userId);
    }
}
