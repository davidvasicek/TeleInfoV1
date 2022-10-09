package com.example.teleinfo.parameters;

//sector Class
public interface MainParameters {

    String SHARED_PREFERENCES = "SharedPreferences";

    String CURRENT_THEME = "CurrentTheme";
    String CURRENT_COLOR_APP_BAR = "CurentColorAppBar";
    String CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT = "CurentColorTabLayoutNonSelectedText";
    String CURRENT_THEME_APP_BAR = "CurrentThemeAppBar";
    //endSector


    // MAIN LIGHTS ---------------------------------------------------------------------------------

    String ON_HEADERS_OF_LOCATIONS_MAIN_LIGHTS = "onHeadersOfLocationsMainLights";
    String ON_DESCRIPTION_MAIN_LIGHTS = "onDescriptionMainLights";
    String ON_SAVE_SORTING_MAIN_LIGHTS = "onSaveSortingMainLights";
    String ON_SAVE_SORTING_VALUE_MAIN_LIGHTS = "onSaveSortingValueMainLights";
    String ON_SHOW_ATTRIBUTES_MAIN_LIGHTS = "onShowAttributesMainLights";

    public static final int IS_ONLINE = 0;  // nternet je dostupný
    public static final int ERROR_CODE_NO_WIFI_NO_DATA = 1; // wifi ani data nejsou zapnuty
    public static final int ERROR_CODE_NO_CONNECTIVITY = 2; // wifi nebo data jsou zapnuty, ale internet není přístupný

    public static final int INIT = 0;  // prvotní inicializace
    public static final int NO_INTERNET = 1;  // bez internetu
    public static final int NO_DATA = 2; // žádná data v databázi
    public static final int DATA = 3; // data připravena k vizualizaci
    public static final int NO_DATA_AFTER_FILTERING = 4; // žádná data nepodléhají kriteriim filtrace



    // ----------------------- rozvrh ---------------------------------

    public static final int CLASSIC_HOUR_IN_MINUTES = 45;  // běžná hodina
    public static final int SIXTH_HOUR_IN_MINUTES = 70;  // běžná hodina


    public static final int TEACHERS = 1;  //
    public static final int DAILY_BY_TEACHERS = 2;  //
    public static final int WEEKLY_BY_TEACHERS = 3;  //

    public static final int SCHOOL_ROOM = 4;  // bě
    public static final int DAILY_BY_SCHOOL_ROOM = 5;  //
    public static final int WEEKLY_BY_SCHOOL_ROOM = 6;  //

    public static final int SCHOOL_CLASS = 7;  //
    public static final int DAILY_BY_SCHOOL_CLASS = 8;  //
    public static final int WEEKLY_BY_SCHOOL_CLASS = 9;  //

    String MQTT_PROTOCOL = "mqttProtocol";
    String MQTT_ADDRESS = "mqttAddress";
    String MQTT_PORT = "mqttPort";




    String SHOW_BREAKS = "showBreaks";
    String SHOW_TIME_LINE = "showTimeLine";



    String COLOR_COLUMN_HEADER = "ColorColumnHeader";
    String COLOR_ROW_HEADER_HOUR = "ColorRowHeaderHour";
    String COLOR_ROW_HEADER_BREAK = "ColorRowHeaderBreak";
    String COLOR_CLASSIC_HOUR = "ColorClassicHour";
    String COLOR_CLASSIC_SUPERVISION = "ColorClassicSupervision";
    String COLOR_MORNING_SUPERVISION = "ColorMorningSupervision";
    String COLOR_PORTER_SUPERVISION = "ColorPorterSupervision";
    String COLOR_PPP_SUPERVISION = "ColorPPPSupervision";
    String COLOR_DINNINGROOM_SUPERVISION = "ColorDinningroomSupervision";
    String COLOR_MIDDAY_SUPERVISION = "ColorMiddaySupervision";
    String COLOR_CLASS_LESSON = "ColorClassLesson";
    String COLOR_CONSULTATION_HOUR = "ColorConsultationHour";
    String COLOR_SCHOOL_ACTIONS = "ColorSchoolActions";
    String COLOR_LUNCH = "ColorLunch";

    String BREAKFAST_SHOW = "BreakfastShow";
    String BRUNCH_SHOW = "BrunchShow";
    String LUNCH_SHOW = "LunchShow";
    String SNACK_SHOW = "SnackShow";
    String DINNER_SHOW = "DinnerShow";
    String DINNER_SECOND_SHOW = "DinnerSecondShow";


    String LOGGED_USER = "LoggedUser";
    String LOGGED_USER_PASSWORD = "LoggedUserPassword";  // později se smaže

    String AUTH_PREFERENCE = "AuthPreference";

    String FINGERPRINT_AUTH = "FingerPrintAuth";
    String TIME_OF_LAST_BLOCKED_READER = "TimeOfLastBlockedReader";
    String FINGERPRINT_HARDWARE_IS_DETECTED = "FingerprintHardwareIsDetected";
    public static final int NUMBER_OF_DEFAULT_PINS = 6;
    String PIN_AUTH = "PinAuth";
    String PIN = "Pin";







}
//endSector
