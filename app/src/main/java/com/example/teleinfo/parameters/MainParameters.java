package com.example.teleinfo.parameters;

import android.widget.Switch;

//sector Class
public interface MainParameters {

    String SHARED_PREFERENCES = "SharedPreferences";

    String CURRENT_THEME = "CurrentTheme";
    String CURRENT_COLOR_APP_BAR = "CurentColorAppBar";
    String CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT = "CurentColorTabLayoutNonSelectedText";
    String CURRENT_THEME_APP_BAR = "CurrentThemeAppBar";
    //endSector



    String TIME_OF_LAST_BLOCKED_READER = "TimeOfLastBlockedReader";
    String TIME_OF_LAST_BLOCKED_PIN = "TimeOfLastBlockedPin";

    String FINGERPRINT_HARDWARE_IS_DETECTED = "FingerprintHardwareIsDetected";




    String DEVICE_IS_PAIRED = "DeviceIsPaired";



    String USER_EMAIL_LOGGED = "UserEmailLogged";
    String USER_PASSWORD_LOGGED = "UserPasswordLogged";
    String USER_TIME_TABLE = "UserTimeTable";
    String USER_ROLE = "UserRole";

    String AUTH_PRIORITY = "AuthPriority";
    String AUTH_PIN_OR_PASSWORD = "AuthPinOrPassword";

    public static final int NUMBER_OF_DEFAULT_PINS = 4;








    public static final int LOGIN_BY_FINGERPRINT = 1; // přihlášení otiskem prstu
    public static final int LOGIN_BY_PIN = 2; // přihlášení pinem
    public static final int LOGIN_BY_CREDENTIALS = 3; // přihlášení údaji


    // ---------------------------------------------------------------------------------


    public static final int IS_ONLINE = 0;  // nternet je dostupný
    public static final int ERROR_CODE_NO_WIFI_NO_DATA = 1; // wifi ani data nejsou zapnuty
    public static final int ERROR_CODE_NO_CONNECTIVITY = 2; // wifi nebo data jsou zapnuty, ale internet není přístupný

    public static final int INIT = 0;  // prvotní inicializace
    public static final int NO_INTERNET = 1;  // bez internetu
    public static final int NO_DATA = 2; // žádná data v databázi
    public static final int DATA = 3; // data připravena k vizualizaci
    public static final int NO_DATA_AFTER_FILTERING = 4; // žádná data nepodléhají kriteriim filtrace




    public static final int ADMINISTRATION_NEW = 0;  // nový objekt
    public static final int ADMINISTRATION_EDIT = 1;  // editovat objekt

    public static final int ADMINISTRATION_TEACHERS = 0;  // Učitelé
    public static final int ADMINISTRATION_SUBJECTS = 1;  // Předměty
    public static final int ADMINISTRATION_SCHOOL_ROOMS = 2;  // Učebny


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



    String SHOW_BREAKS = "ShowBreaks";
    String SHOW_TIME_LINE = "ShowTimeLine";
    String SHOW_EVEN_AND_ODD_COLUMNS = "ShowEvenAndOddColumns";
    String SHOW_CANCELED_CLASSED = "ShowCanceledClassed";
    String HIDE_LEARNED_DAYS = "HideLearnedDays";
    String SHOW_CONSULTATION_HOUR = "ShowConsultationHour";
    String SHOW_CURRENT_DAY_HIGHLIGHTED = "ShowCurrentDayHighlighted";
    String SHOW_LUNCH_IN_SCHEDULE = "ShowLunchInShedule";







    String BREAKFAST_SHOW = "BreakfastShow";
    String BRUNCH_SHOW = "BrunchShow";
    String LUNCH_SHOW = "LunchShow";
    String SNACK_SHOW = "SnackShow";
    String DINNER_SHOW = "DinnerShow";
    String DINNER_SECOND_SHOW = "DinnerSecondShow";

    String MY_ALERGENS = "MyAlergens";




}
//endSector
