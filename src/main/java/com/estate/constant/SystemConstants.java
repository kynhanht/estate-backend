package com.estate.constant;

public class SystemConstants {

    public static final int ACTIVE_STATUS = 1;
    public static final int INACTIVE_STATUS = 0;

    public static final long ACCESS_TOKEN_VALIDITY_MILLISECONDS = 3600000L * 24;//24h
    public static final String SIGNING_KEY = "kynhanht";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";

    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String MANAGER_ROLE = "ROLE_MANAGER";
    public static final String STAFF_ROLE = "ROLE_STAFF";
    public static final String MANAGER = "MANAGER";
    public static final String STAFF = "STAFF";
    public static final String HOME = "/home";
    public static final String ADMIN_HOME = "/admin/home";
    public static final String PASSWORD_DEFAULT = "123456";


    // File
    public static final String UPLOAD_BUILDING_FILE_DIR = "/home/kynhanht/images/buildings/";
    public static final String LOAD_FILE_DIR = "/upload/";

}
