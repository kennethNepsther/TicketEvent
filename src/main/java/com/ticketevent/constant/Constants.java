package com.ticketevent.constant;

public class Constants {

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTHORITY_DELIMITER = ",";

    public static final String ADMIN_AUTHORITY = "user:create, user:read, user:update, user:delete,event:create,event:read,event:update,event:delete";
    public static final String PARTICIPANT_AUTHORITY = "event:read";
    public static final String ORGANIZER_AUTHORITY = "event:create,event:read,event:update,event:delete";



}
