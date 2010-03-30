/*
* Copyright (c) 2005 Nokia Corporation and/or its subsidiary(-ies).
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of "Eclipse Public License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.eclipse.org/legal/epl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description:  Helper class containing HTI related constant used in 
*                other classes.
*
*/

package com.nokia.hti.common;

public class HtiConstants
{
//==============================================================================
//Public constants

    // Application UID's
    public static final int APP_UID_HTIADMIN  = 0x1020DEB5;
    public static final int APP_UID_PHONEBOOK = 0x101F4CCE;
    public static final int APP_UID_DUMMY     = 0x1030DEB6;
    public static final int APP_UID_GALLERY   = 0x101f8599;

    // service UID's
    public static final int SERVICE_UID_HTI			= 0x1020DEB6;
    public static final int SERVICE_UID_ECHO		= 0x1020DEBF;
    public static final int SERVICE_UID_SCREEN		= 0x1020DEC3;
    public static final int SERVICE_UID_KEYEVENT	= 0x1020DEC1;
    public static final int SERVICE_UID_FTP			= 0x1020DEC5;
    public static final int SERVICE_UID_APP			= 0x1020DEC7;
    public static final int SERVICE_UID_STIF		= 0x10210CC3;
    public static final int SERVICE_UID_SYSINFO		= 0x10210CC7;
    public static final int SERVICE_UID_AUDIO		= 0x10210CCB;
    public static final int SERVICE_UID_PIM			= 0x10210CCD;
    public static final int SERVICE_UID_MESSAGES	= 0x10210CCF;
    public static final int SERVICE_UID_ISIMSG		= 0x10210CD1;
    public static final int SERVICE_UID_IPPROXY		= 0x10210CD3;
    public static final int SERVICE_UID_SUT			= 0x10210CD7;

    // platform versions
    public static final double PLATFORM_VERSION_S60_2_6 = 2.6;
    public static final double PLATFORM_VERSION_S60_2_8 = 2.8;
    public static final double PLATFORM_VERSION_S60_3_0 = 2.9;
    public static final double PLATFORM_VERSION_S60_3_1 = 3.1;
    public static final double PLATFORM_VERSION_S60_3_2 = 3.2;
    public static final double PLATFORM_VERSION_S60_5_0 = 5.0;
    public static final double PLATFORM_VERSION_S60_5_1 = 5.1;
    public static final double PLATFORM_VERSION_S60_5_2 = 5.2;
    
    // service names
    public static final String SERVICE_NAME_HTI = "HTI system service";
    public static final String SERVICE_NAME_ECHO = "Echo(ping) service";
    public static final String SERVICE_NAME_SCREEN = "Screenshot service";
    public static final String SERVICE_NAME_KEYEVENT = "Key event service";
    public static final String SERVICE_NAME_FTP = "FTP service";
    public static final String SERVICE_NAME_APP = "Application control service";
    public static final String SERVICE_NAME_STIF = "STIF TF Control Service";
    public static final String SERVICE_NAME_SYSINFO = "SysInfo service";
    public static final String SERVICE_NAME_AUDIO = "Audio Control Service";
    public static final String SERVICE_NAME_PIM = "Personal information " +
      "manager service";
    public static final String SERVICE_NAME_MESSAGES = "Message management " +
       "service (inbox etc.)";
    public static final String SERVICE_NAME_ISIMSG = "ISI message service";
    public static final String SERVICE_NAME_IPPROXY = "IP-Proxy service";

    // HTI error codes
    public static final int ERROR_HTI_MSG_TOO_BIG =       0x01;
    public static final int ERROR_HTI_OUT_OF_MEMORY =     0x02;
    public static final int ERROR_HTI_SERVICE_NOT_FOUND = 0x03;
    public static final int ERROR_HTI_SERVICE_ERROR =     0x04;
    public static final int ERROR_HTI_NOT_AUTHORIZED =    0x05;
    public static final int ERROR_HTI_FAILED_UNWRAP =     0x06;
    
    // HTI system service commands
    public static final int CMD_HTI_AUTH =         0x01;
    public static final int CMD_HTI_VERSION =      0x02;
    public static final int CMD_HTI_SERVICE_LIST = 0x03;
    public static final int CMD_HTI_STOP =         0x04;
    public static final int CMD_HTI_REBOOT =       0x05;
    public static final int CMD_HTI_FORMAT =       0x06;
    public static final int CMD_HTI_RESET =        0x07;
    public static final int CMD_HTI_SHOW_CONSOLE = 0x08;
    public static final int CMD_HTI_HIDE_CONSOLE = 0x09;
    public static final int CMD_HTI_INSTANCE_ID =  0x0A;
    public static final int CMD_HTI_DEBUG_PRINT =  0x0B;
    public static final int CMD_HTI_ERROR =        0xFF;
    
    // SCREEN service commands
    public static final int CMD_SCREEN_SCREEN =            0x01;
    public static final int CMD_SCREEN_REGION =            0x02;
    public static final int CMD_SCREEN_SCREEN_ZIP =        0x03;
    public static final int CMD_SCREEN_REGION_ZIP =        0x04;
    public static final int CMD_SCREEN_TEXT_RECOG =        0x10;
    public static final int CMD_SCREEN_TEXT_RECOG_U =      0x11;
    public static final int CMD_SCREEN_TEXT_BITMAP =       0x12;
    public static final int CMD_SCREEN_TEXT_BITMAP_U =     0x13;
    public static final int CMD_SCREEN_SCREEN_SERIES =     0x21;
    public static final int CMD_SCREEN_REGION_SERIES =     0x22;
    public static final int CMD_SCREEN_SCREEN_ZIP_SERIES = 0x23;
    public static final int CMD_SCREEN_REGION_ZIP_SERIES = 0x24;
    public static final int CMD_SCREEN_SELECT =            0x30;
    public static final int CMD_SCREEN_MODE =              0x3A;
    public static final int CMD_SCREEN_DELTA =             0x81;
    public static final int CMD_SCREEN_REGION_DELTA =      0x82;
    public static final int CMD_SCREEN_DELTA_ZIP =         0x83;
    public static final int CMD_SCREEN_REGION_DELTA_ZIP =  0x84;
    public static final int CMD_SCREEN_DELTA_RESET =       0x85;
    
    // Font attributes
    public static final int FONT_BOLD =         0x01;
    public static final int FONT_ITALIC =       0x02;
    public static final int FONT_BITMAP_GLYPH = 0x04;
    public static final int FONT_SUPERSCRIPT =  0x08;
    public static final int FONT_SUBSCRIPT =    0x18;
    
    // Text recognition responses
    public static final int TEXT_RECOG_OK        = 0xF0;
    public static final int TEXT_RECOG_NOT_FOUND = 0xF1;
    
    // Color depth used in screen capture commands
    public static final int COLOR_DEPTH_NONE =       0x00;
    public static final int COLOR_DEPTH_GRAY_2 =     0x01;
    public static final int COLOR_DEPTH_GRAY_4 =     0x02;
    public static final int COLOR_DEPTH_GRAY_16 =    0x03;
    public static final int COLOR_DEPTH_GRAY_256 =   0x04;
    public static final int COLOR_DEPTH_COLOR_16 =   0x05;
    public static final int COLOR_DEPTH_COLOR_256 =  0x06;
    public static final int COLOR_DEPTH_COLOR_64K =  0x07;
    public static final int COLOR_DEPTH_COLOR_16M =  0x08;
    public static final int COLOR_DEPTH_RGB =        0x09;
    public static final int COLOR_DEPTH_COLOR_4K =   0x0A;
    public static final int COLOR_DEPTH_COLOR_16MU = 0x0B;
    public static final int COLOR_DEPTH_COLOR_16MA = 0x0C;
    
    // KEYEVENT service commands
    public static final int CMD_KEYEVENT_PRESSKEY =       0x01;
    public static final int CMD_KEYEVENT_KEYDOWN =        0x02;
    public static final int CMD_KEYEVENT_KEYUP =          0x03;
    public static final int CMD_KEYEVENT_TYPETEXT =       0x04;
    public static final int CMD_KEYEVENT_LONGKEYPRESS =   0x05;
    public static final int CMD_KEYEVENT_TYPETEXTPW =     0x06;
    public static final int CMD_KEYEVENT_KEY_SEQUENCE =   0x07;
    public static final int CMD_POINTEREVENT_TAP      =   0x10;
    public static final int CMD_POINTEREVENT_TAP_DRAG =   0x11;
    public static final int CMD_POINTEREVENT_MULTIPOINT = 0x12;
    public static final int CMD_POINTEREVENT_DOWN =       0x13;
    public static final int CMD_POINTEREVENT_UP =         0x14;
    public static final int CMD_KEYEVENT_OK =             0xFF;
    
    // KEYEVENT error codes
    public static final int ERROR_KEYEVENT_NO_COMMAND =       0x01;
    public static final int ERROR_KEYEVENT_INVALID_COMMAND =  0x02;
    public static final int ERROR_KEYEVENT_NOT_READY =        0x03;
    public static final int ERROR_KEYEVENT_INVALID_SCANCODE = 0x80;
    public static final int ERROR_KEYEVENT_NO_TEXT =          0x90;
    public static final int ERROR_KEYEVENT_INVALID_TEXT =     0x91;
    
    // FTP service commands
    public static final int CMD_FTP_STOR =         0x02;
    public static final int CMD_FTP_STOR_U =       0x03;
    public static final int CMD_FTP_RETR =         0x04;
    public static final int CMD_FTP_RETR_U =       0x05;
    public static final int CMD_FTP_LIST =         0x06;
    public static final int CMD_FTP_LIST_U =       0x07;
    public static final int CMD_FTP_MKD =          0x08;
    public static final int CMD_FTP_MKD_U =        0x09;
    public static final int CMD_FTP_RMD =          0x0A;
    public static final int CMD_FTP_RMD_U =        0x0B;
    public static final int CMD_FTP_DELE =         0x0C;
    public static final int CMD_FTP_DELE_U =       0x0D;
    public static final int CMD_FTP_CANCEL =       0x0E;
    public static final int CMD_FTP_FILESIZE =     0x0F;
    public static final int CMD_FTP_LISTDIR =      0x10;
    public static final int CMD_FTP_LISTDIR_U =    0x11;
    public static final int CMD_FTP_LISTSIZES =    0x12;
    public static final int CMD_FTP_LISTSIZES_U =  0x13;
    public static final int CMD_FTP_LISTDRIVES =   0x14;
    public static final int CMD_FTP_LISTDRIVES_U = 0x15;
    public static final int CMD_FTP_RENAME =       0x16;
    public static final int CMD_FTP_RENAME_U =     0x17;
    public static final int CMD_FTP_COPY =         0x18;
    public static final int CMD_FTP_COPY_U =       0x19;
    public static final int CMD_FTP_MOVE =         0x1A;
    public static final int CMD_FTP_MOVE_U =       0x1B;
    public static final int CMD_FTP_SETFORCE =     0x20;
    public static final int CMD_FTP_CHECKSUM =     0x30;
    public static final int CMD_FTP_CHECKSUM_U =   0x31;
    public static final int CMD_FTP_FORMAT =       0x40;
    public static final int CMD_FTP_OK =           0xF0;
    
    // FTP checksum algorithms
    public static final int CHECKSUM_ALGORITHM_MD5 = 0x01;
    
    // APP service commands
    public static final int CMD_APP_START_PROCESS =     0x02;
    public static final int CMD_APP_START_PROCESS_U =   0x03;
    public static final int CMD_APP_STATUS_PROCESS =    0x04;
    public static final int CMD_APP_STATUS_PROCESS_U =  0x05;
    public static final int CMD_APP_STATUS_PROCESS_ID = 0x06;
    public static final int CMD_APP_STOP_PROCESS =      0x08;
    public static final int CMD_APP_STOP_PROCESS_U =    0x09;
    public static final int CMD_APP_STOP_PROCESS_ID =   0x0A;
    public static final int CMD_APP_LIST_PROCESSES =    0x0C;
    public static final int CMD_APP_LIST_PROCESSES_U =  0x0D;
    
    public static final int CMD_APP_START_PROCESS_STORE_HANDLE   =  0x0E;
    public static final int CMD_APP_START_PROCESS_STORE_HANDLE_U =  0x07;
    public static final int CMD_APP_GET_PROCESS_EXIT_CODE        =  0x0B;
    
    public static final int CMD_APP_START_APP =        0x10;
    public static final int CMD_APP_START_APP_U =      0x11;
    public static final int CMD_APP_START_APP_UID =    0x12;
    public static final int CMD_APP_START_APP_UID_U =  0x13;
    public static final int CMD_APP_START_DOC =        0x14;
    public static final int CMD_APP_START_DOC_U =      0x15;
    public static final int CMD_APP_STATUS_APP =       0x16;
    public static final int CMD_APP_STATUS_APP_U =     0x17;
    public static final int CMD_APP_STATUS_DOC =       0x18;
    public static final int CMD_APP_STATUS_DOC_U =     0x19;
    public static final int CMD_APP_STATUS_APP_UID =   0x1A;
    public static final int CMD_APP_STOP_APP =         0x1C;
    public static final int CMD_APP_STOP_APP_U =       0x1D;
    public static final int CMD_APP_STOP_DOC =         0x1E;
    public static final int CMD_APP_STOP_DOC_U =       0x1F;
    public static final int CMD_APP_STOP_APP_UID =     0x20;
    public static final int CMD_APP_LIST_APPS =        0x24;
    public static final int CMD_APP_LIST_APPS_U =      0x25;
    public static final int CMD_APP_LIST_INSTALLED =   0x26;
    public static final int CMD_APP_LIST_INSTALLED_U = 0x27;
    public static final int CMD_APP_INSTALL =          0x30;
    public static final int CMD_APP_INSTALL_U =        0x31;
    public static final int CMD_APP_UNINSTALL =        0x32;
    public static final int CMD_APP_UNINSTALL_NAME_U = 0x33;
    public static final int CMD_APP_UNINSTALL_NAME =   0x34;
    
    // APP service responses
    public static final int RESP_APP_OK =              0xF0;
    public static final int RESP_APP_NOT_FOUND =       0xF1;
    public static final int RESP_APP_ALREADY_RUNNING = 0xF2;
    public static final int RESP_APP_RUNNING =         0xF4;
    
    // STIF service commands
    public static final int CMD_STIF_OPEN =             0x01;
    public static final int CMD_STIF_CLOSE =            0x02;
    public static final int CMD_STIF_LOAD_MODULE =      0x03;
    public static final int CMD_STIF_UNLOAD_MODULE =    0x04;
    public static final int CMD_STIF_LIST_CASES =       0x05;
    public static final int CMD_STIF_START_CASE =       0x06;
    public static final int CMD_STIF_CANCEL_CASE =      0x07;
    public static final int CMD_STIF_PAUSE_CASE =       0x08;
    public static final int CMD_STIF_RESUME_CASE =      0x09;
    public static final int CMD_STIF_ADD_CASE_FILE =    0x0A;
    public static final int CMD_STIF_REMOVE_CASE_FILE = 0x0B;
    public static final int CMD_STIF_CASE_MESSAGE =     0x0C;
    public static final int CMD_STIF_SET_DEVICEID =     0x0D;
    public static final int CMD_STIF_TEST_COMPLETED =   0x0E;
    public static final int CMD_STIF_ATS_MESSAGE =      0x0F;
    
    // SUT service commands
    public static final int CMD_SUT_OPEN			= 0x01;
    public static final int CMD_SUT_CLOSE			= 0x02;
    public static final int CMD_SUT_LIST_CASES		= 0x03;
    public static final int CMD_SUT_START_CASE		= 0x04;
    public static final int CMD_SUT_START_ALL_CASES	= 0x05;
    public static final int CMD_SUT_TEST_COMPLETE	= 0x06;
    
    // SYSINFO service commands
    public static final int CMD_SYSINFO_HAL =                  0x01;
    public static final int CMD_SYSINFO_IMEI =                 0x02;
    public static final int CMD_SYSINFO_SWVERSION =            0x03;
    public static final int CMD_SYSINFO_LANGVERSION =          0x04;
    public static final int CMD_SYSINFO_SWLANGVERSION =        0x05;
    public static final int CMD_SYSINFO_UASTRING =             0x06;
    public static final int CMD_SYSINFO_FREE_RAM =             0x07;
    public static final int CMD_SYSINFO_USED_RAM =             0x08;
    public static final int CMD_SYSINFO_TOTAL_RAM =            0x09;
    public static final int CMD_SYSINFO_EAT_RAM =              0x0A;
    public static final int CMD_SYSINFO_RELEASE_RAM =          0x0B;
    public static final int CMD_SYSINFO_FREE_DISK_SPACE =      0x0C;
    public static final int CMD_SYSINFO_USED_DISK_SPACE =      0x0D;
    public static final int CMD_SYSINFO_TOTAL_DISK_SPACE =     0x0E;
    public static final int CMD_SYSINFO_EAT_DISK_SPACE =       0x0F;
    public static final int CMD_SYSINFO_RELEASE_DISK_SPACE =   0x10;
    public static final int CMD_SYSINFO_SET_HOMETIME =         0x20;
    public static final int CMD_SYSINFO_GET_HOMETIME =         0x21;
    public static final int CMD_SYSINFO_SET_DATE_TIME_FORMAT = 0x22;
    public static final int CMD_SYSINFO_LIGHT_STATUS =         0x30;
    public static final int CMD_SYSINFO_LIGHT_ON =             0x31;
    public static final int CMD_SYSINFO_LIGHT_OFF =            0x32;
    public static final int CMD_SYSINFO_LIGHT_BLINK =          0x33;
    public static final int CMD_SYSINFO_LIGHT_RELEASE =        0x3A;
    public static final int CMD_SYSINFO_SCREENSAVER_DISABLE =  0x40;
    public static final int CMD_SYSINFO_SCREENSAVER_ENABLE =   0x41;
    public static final int CMD_SYSINFO_SCREENSAVER_TIMEOUT =  0x42;
    public static final int CMD_SYSINFO_GET_NETWORKMODE           = 0x50;
    public static final int CMD_SYSINFO_SET_NETWORKMODE           = 0x51;
    public static final int CMD_SYSINFO_SET_NETWORKMODE_NO_REBOOT = 0x52;
    public static final int CMD_SYSINFO_HSDPA_ENABLE_DISABLE = 0x53;
    public static final int CMD_SYSINFO_IR_ACTIVATE =          0x5A;
    public static final int CMD_SYSINFO_BT_POWER =             0x5B;
    public static final int CMD_SYSINFO_BT_SETTINGS =          0x5C;
    public static final int CMD_SYSINFO_KEYLOCK_TOGGLE =       0x60;
    public static final int CMD_SYSINFO_AUTO_KEYLOCK_TIME =    0x61;
    public static final int CMD_SYSINFO_EMPTY_DRM_RIGHTS_DB =  0x65;
    public static final int CMD_SYSINFO_BATTERY_STATUS =       0x70;
    public static final int CMD_SYSINFO_SIGNAL_STRENGTH =      0x71;
    public static final int CMD_SYSINFO_UPDATE_MEDIA_GALLERY = 0x7A;
    public static final int CMD_SYSINFO_ACTIVATE_SKIN =        0x80;
    
    public static final int LIGHT_TARGET_PRIMARY_DISPLAY    = 0x01;
    public static final int LIGHT_TARGET_PRIMARY_KEYBOARD   = 0x02;
    public static final int LIGHT_TARGET_SECONDARY_DISPLAY  = 0x04;
    public static final int LIGHT_TARGET_SECONDARY_KEYBOARD = 0x08;
    
    public static final int LIGHT_STATUS_UNKNOWN = 0x00;
    public static final int LIGHT_STATUS_ON      = 0x01;
    public static final int LIGHT_STATUS_OFF     = 0x02;
    public static final int LIGHT_STATUS_BLINK   = 0x03;
    
    // AUDIO service commands
    public static final int CMD_AUDIO_LIST =         0x01;
    public static final int CMD_AUDIO_PLAY_FILE =    0x02;
    public static final int CMD_AUDIO_PLAY_TONE =    0x03;
    public static final int CMD_AUDIO_PLAY_DTMF =    0x04;
    public static final int CMD_AUDIO_STOP =         0x05;
    public static final int CMD_AUDIO_GET_DURATION = 0x06;
    public static final int CMD_AUDIO_GET_MAX_VOL =  0x07;
    public static final int CMD_AUDIO_SET_VOL =      0x08;
    
    // PIM service commands
    public static final int CMD_PIM_IMPORT_VCARD               = 0x01;
    public static final int CMD_PIM_IMPORT_VCALENDAR           = 0x02;
    public static final int CMD_PIM_DEL_CONTACT_ENTRIES        = 0x03;
    public static final int CMD_PIM_DEL_CALENDAR_ENTRIES       = 0x04;
    public static final int CMD_PIM_ADD_NOTEPAD_MEMO           = 0x05;
    public static final int CMD_PIM_ADD_NOTEPAD_MEMO_FROM_FILE = 0x06;
    public static final int CMD_PIM_DEL_ALL_NOTEPAD_MEMOS      = 0x07;
    public static final int CMD_PIM_SIM_CARD_INFO              = 0x10;
    public static final int CMD_PIM_IMPORT_SIM_CONTACT         = 0x11;
    public static final int CMD_PIM_DELETE_SIM_CONTACT         = 0x12;
    public static final int CMD_PIM_CREATE_BOOKMARK            = 0x1A;
    public static final int CMD_PIM_DELETE_BOOKMARK            = 0x1B;
    public static final int CMD_PIM_OK                         = 0xFF;
    
    public static final int SIM_FIELD_TYPE_NAME              = 0x01;
    public static final int SIM_FIELD_TYPE_2ND_NAME          = 0x02;
    public static final int SIM_FIELD_TYPE_PHONE_NUMBER      = 0x03;
    public static final int SIM_FIELD_TYPE_EMAIL             = 0x04;
    public static final int SIM_FIELD_TYPE_ADDITIONAL_NUMBER = 0x05;
    
    // MESSAGES service commands
    public static final int CMD_MSGS_ADD_SMS                 = 0x01;
    public static final int CMD_MSGS_ADD_MMS                 = 0x02;
    public static final int CMD_MSGS_ADD_EMAIL               = 0x03;
    public static final int CMD_MSGS_ADD_IRDA_MSG            = 0x04;
    public static final int CMD_MSGS_ADD_BT_MSG              = 0x05;
    public static final int CMD_MSGS_ADD_SMART_MSG           = 0x06;
    public static final int CMD_MSGS_ADD_AUDIO_MSG           = 0x07;
    public static final int CMD_MSGS_DELETE_MESSAGE          = 0x10;
    public static final int CMD_MSGS_DELETE_FOLDER_CONTENT   = 0x11;
    public static final int CMD_MSGS_CREATE_MAILBOX          = 0x20;
    public static final int CMD_MSGS_DELETE_MAILBOX          = 0x21;
    public static final int CMD_MSGS_CREATE_ACCESSPOINT      = 0x30;    
    public static final int CMD_MSGS_DELETE_ACCESSPOINT      = 0x31;
    public static final int CMD_MSGS_CREATE_DESTINATION      = 0x32;
    public static final int CMD_MSGS_DELETE_DESTINATION      = 0x33;
    public static final int CMD_MSGS_ADD_TO_DESTINATION      = 0x34;
    public static final int CMD_MSGS_REMOVE_FROM_DESTINATION = 0x35;
    public static final int CMD_MSGS_SET_DEFAULT_CONNECTION  = 0x36;
    public static final int CMD_MSGS_SET_DEFAULT_SMS_CENTER  = 0x40;
    public static final int CMD_MSGS_DELETE_SMS_CENTER       = 0x41;
    public static final int CMD_MSGS_SET_SMS_SETTINGS        = 0x42;
    public static final int CMD_MSGS_SET_MMS_SETTINGS        = 0x45;
    public static final int CMD_MSGS_OK                      = 0xFF;
    
    // MESSAGES folders
    public static final int MESSAGES_FOLDER_ALL    = 0x00;
    public static final int MESSAGES_FOLDER_INBOX  = 0x01;
    public static final int MESSAGES_FOLDER_DRAFTS = 0x02;
    public static final int MESSAGES_FOLDER_SENT   = 0x03;
    public static final int MESSAGES_FOLDER_OUTBOX = 0x04;
    
    // MESSAGES message types
    public static final int MESSAGES_MSG_TYPE_ALL           = 0x00;
    public static final int MESSAGES_MSG_TYPE_SMS           = 0x01;
    public static final int MESSAGES_MSG_TYPE_MMS           = 0x02;
    public static final int MESSAGES_MSG_TYPE_SMART_MESSAGE = 0x03;
    public static final int MESSAGES_MSG_TYPE_EMAIL         = 0x04;
    public static final int MESSAGES_MSG_TYPE_IR_MESSAGE    = 0x05;
    public static final int MESSAGES_MSG_TYPE_BT_MESSAGE    = 0x06;
    public static final int MESSAGES_MSG_TYPE_AUDIO_MESSAGE = 0x07;
    
    // MESSAGES service error codes
    public static final int ERROR_MSGS_MISSING_COMMANDS     = 0x01;
    public static final int ERROR_MSGS_UNRECOGNIZED_COMMAND = 0x02;
    public static final int ERROR_MSGS_INVALID_PARAMETERS   = 0x03;
    public static final int ERROR_MSGS_INVALID_ID           = 0x04;
    public static final int ERROR_MSGS_INVALID_FOLDER       = 0x05;
    public static final int ERROR_MSGS_SMS_BODY_TOO_LONG    = 0x06;
    
    // SMS settings related enumerations
    public static final int SMS_CHAR_SUPPORT_REDUCED = 0x00;
    public static final int SMS_CHAR_SUPPORT_FULL    = 0x01;
    
    public static final int SMS_VALIDITY_PERIOD_MAX = 0x00;
    public static final int SMS_VALIDITY_PERIOD_1H  = 0x01;
    public static final int SMS_VALIDITY_PERIOD_6H  = 0x02;
    public static final int SMS_VALIDITY_PERIOD_24H = 0x03;
    public static final int SMS_VALIDITY_PERIOD_3D  = 0x04;
    public static final int SMS_VALIDITY_PERIOD_1W  = 0x05;
    
    public static final int SMS_CONVERSION_NONE   = 0x00;
    public static final int SMS_CONVERSION_FAX    = 0x01;
    public static final int SMS_CONVERSION_PAGING = 0x02;
    public static final int SMS_CONVERSION_EMAIL  = 0x03;

    public static final int SMS_PREFERRED_CONNECTION_GSM    = 0x00;
    public static final int SMS_PREFERRED_CONNECTION_PACKET = 0x01;
    
    // MMS settings related enumerations
    public static final int MMS_CREATION_MODE_RESTRICTED = 0x00;
    public static final int MMS_CREATION_MODE_GUIDED     = 0x01;
    public static final int MMS_CREATION_MODE_FREE       = 0x02;
    
    public static final int MMS_IMAGE_SIZE_SMALL    = 0x00;
    public static final int MMS_IMAGE_SIZE_LARGE    = 0x01;
    public static final int MMS_IMAGE_SIZE_ORIGINAL = 0x02;
    
    public static final int MMS_RECEPTION_AUTOMATIC_ALWAYS          = 0x00;
    public static final int MMS_RECEPTION_AUTOMATIC_IN_HOME_NETWORK = 0x01;
    public static final int MMS_RECEPTION_MANUAL_ALWAYS             = 0x02;
    public static final int MMS_RECEPTION_DISABLED                  = 0x03;
    
    public static final int MMS_VALIDITY_PERIOD_MAX = 0x00;
    public static final int MMS_VALIDITY_PERIOD_1H  = 0x01;
    public static final int MMS_VALIDITY_PERIOD_6H  = 0x02;
    public static final int MMS_VALIDITY_PERIOD_24H = 0x03;
    public static final int MMS_VALIDITY_PERIOD_3D  = 0x04;
    public static final int MMS_VALIDITY_PERIOD_1W  = 0x05;

    
    // MAILBOX related stuff
    public static final int MAILBOX_TYPE_POP3 = 0;
    public static final int MAILBOX_TYPE_IMAP = 1;
    
    public static final int MAILBOX_IN_SECURITY_OFF = 0;
    public static final int MAILBOX_IN_SECURITY_START_TLS = 1;
    public static final int MAILBOX_IN_SECURITY_SSL_TLS = 2;
    
    public static final int MAILBOX_OUT_SECURITY_OFF = 0;
    public static final int MAILBOX_OUT_SECURITY_START_TLS = 1;
    public static final int MAILBOX_OUT_SECURITY_SSL_TLS = 2;
    
    public static final int MAILBOX_SEND_MSG_IMMEDIATELY = 0;
    public static final int MAILBOX_SEND_MSG_ON_NEXT_CONN = 1;
    public static final int MAILBOX_SEND_MSG_ON_REQUEST = 2;
    
    public static final int MAILBOX_COPY_TO_OWN_NONE = 0;
    public static final int MAILBOX_COPY_TO_OWN_TO = 1;
    public static final int MAILBOX_COPY_TO_OWN_CC = 2;
    public static final int MAILBOX_COPY_TO_OWN_BCC = 3;
    
    public static final int MAILBOX_RETRIEVE_ONLY_HEADER = 0;
    public static final int MAILBOX_RETRIEVE_SIZE_LIMIT = 1;
    public static final int MAILBOX_RETRIEVE_BODY_AND_ATTACHMENT = 2;
    
    public static final int MAILBOX_AUTO_UPDATE_ALWAYS_ON = 0;
    public static final int MAILBOX_AUTO_UPDATE_IN_HOME_NETWORK = 1;
    public static final int MAILBOX_AUTO_UPDATE_NOT_ENABLED = 2;
    
    // Test SIM numbers
    public static final String HTI_TEST_SIM_1 = "+3584544108884";
    public static final String HTI_TEST_SIM_2 = "+3584544108885";
    public static final String HTI_TEST_SIM_3 = "+3584544108886";
    
    public static final String TOOLS_TEST_SIM_1 ="+3584544267199";
    public static final String TOOLS_TEST_SIM_2 ="+3584544267200";
    public static final String TOOLS_TEST_SIM_3 ="+3584544267201";
    public static final String TOOLS_TEST_SIM_4 ="+3584544267202";
    
    // Capabilities of our NTN SIM cards
    public static final int SIMCARD_MAXNUMOFSECONDNAMES			= 1;
    public static final int SIMCARD_MAXNUMOFADDITIONALNUMS		= 1;
    public static final int SIMCARD_MAXNUMOFEMAILS				= 1;
    public static final int SIMCARD_MAXLENGTHOFNAME				= 24;
    public static final int SIMCARD_MAXLENGTHOFNUMBER			= 50;
    public static final int SIMCARD_MAXLENGTHOFSECONDNAME		= 24;
    public static final int SIMCARD_MAXLENGTHOFADDITIONALNUM	= 50;
    public static final int SIMCARD_MAXLENGHTOFEMAIL			= 40;
    public static final int SIMCARD_TOTALSLOTS					= 50;
 
    // SIS install related constants
    public static final String DUMMY_SIS_UPLOAD_PATH = "C:\\Dummy.sisx";
    public static final int INSTALLER_MIME_TYPE_SISX = 0;
    public static final int INSTALLER_MIME_TYPE_SIS  = 1;
    public static final int INSTALLER_MIME_TYPE_PIP  = 2;
    public static final int INSTALLER_MIME_TYPE_JAD  = 3;
    public static final int INSTALLER_MIME_TYPE_JAR  = 4;
    public static final int INSTALLER_MIME_TYPE_JAVA = 5;
    public static final int INSTALLER_MIME_TYPE_JARX = 6;
    
    // Screen sizes
    public static final int SCREEN_WIDTH_LEGACY = 176;
    public static final int SCREEN_HEIGHT_LEGACY = 208;
    public static final int SCREEN_WIDTH_QVGA = 240;
    public static final int SCREEN_HEIGHT_QVGA = 320;
    public static final int SCREEN_WIDTH_DOUBLE = 352;
    public static final int SCREEN_HEIGHT_DOUBLE = 416;
    public static final int SCREEN_WIDTH_VGA = 480;
    public static final int SCREEN_HEIGHT_VGA = 640;
    public static final int SCREEN_WIDTH_WVGA = 360;
    public static final int SCREEN_HEIGHT_WVGA = 640;    
    
    // Memory card format options
    public static final int FTP_FORMAT_OPTION_FULL =  0;
    public static final int FTP_FORMAT_OPTION_QUICK = 1;

    // Machine UID numbers for detecting hardware
    public static final int MACHINE_UID_CHARLIE      = 0x101fbb55;
    public static final int MACHINE_UID_MARS         = 0x10274cd3;
    public static final int MACHINE_UID_MARS_S5_3    = 0x20001856;
    public static final int MACHINE_UID_ZEUS_S4_1    = 0x20001857;
    public static final int MACHINE_UID_ZEUS         = 0x10274cd5;
    public static final int MACHINE_UID_THUNDER      = 0x200005f8;
    public static final int MACHINE_UID_SCARFE       = 0x10208558;
    public static final int MACHINE_UID_ROSETTA      = 0x20000606;
    public static final int MACHINE_UID_AALTO_B2_B3  = 0x2000060B;
    public static final int MACHINE_UID_SCHNAPPI_B2  = 0x2000060A;
    public static final int MACHINE_UID_DEVLON       = 0x101FB3E8;
    public static final int MACHINE_UID_VEGA         = 0x20002d7b;
    public static final int MACHINE_UID_ARIANE_B4    = 0x019CC530;
    public static final int MACHINE_UID_MERLIN       = 0x20002D81;
    public static final int MACHINE_UID_LUMIERE      = 0x20002D82;
    public static final int MACHINE_UID_GADGET       = 0x20002D86;
    public static final int MACHINE_UID_TUBE         = 0x2000Da56;
    public static final int MACHINE_UID_IVALO        = 0x20014DDD;
    public static final int MACHINE_UID_COROLLA      = 0x20014DDF;
    public static final int MACHINE_UID_VASCO        = 0x101FB3E8; // == Devlon, not final?
    public static final int MACHINE_UID_WINS         = 0x10005f62;

    
    /*
     * Common constants for test cases
     * These can be overridden in platform and/or hardware specific constants
     */
    public static final String FTP_UPLOAD_DESTINATION = "C:\\testfile.hti";
    public static final String MESSAGE_ATTACHMENT_PATH = "C:\\hti.jpg";
    public static final String AUDIO_ATTACHMENT_PATH = "C:\\hti.amr";
    public static final String GALLERY_IMAGE_PATH = "C:\\Data\\Images\\";
    public static final int UPLOAD_FILE_SIZE_BYTES = 20 * 1024;
    public static final int PRESS_MANY_KEYS_CYCLES = 50;
    
    /*
     * Platform specific constants for test cases
     * These can be overridden in hardware specific constants
     */
    
    // S60 2.6
    public static final String S60_2_6_EXE_PATH = "C:\\System\\Programs\\dummy.exe";
    public static final String S60_2_6_APP_PATH = "C:\\System\\Apps\\HtiAdmin\\HtiAdmin.app";
    public static final String S60_2_6_APP_NO_PATH = "htiadmin.app";
    public static final String S60_2_6_APP_NAME = "HtiAdmin";
    public static final String S60_2_6_DOC_PATH = "Z:\\Nokia\\Sounds\\Digital\\Nokia tune.mid";
    public static final String S60_2_6_MIDI_FILE_PATH = "Z:\\Nokia\\Sounds\\Digital\\Message.mid";
    public static final String S60_2_6_RNG_FILE_PATH = "Z:\\Nokia\\Sounds\\Simple\\Desk phone.rng";
    public static final String S60_2_6_AUDIO_FILE_LIST_PATH = "Z:\\Nokia\\Sounds\\Digital\\";
    public static final String S60_2_6_CONTACT_DB_PATH = "C:\\System\\Data\\Contacts.cdb";
    public static final String S60_2_6_APP_CAPTION_FONT_TYPEFACE = "LatinBold19";
    public static final int    S60_2_6_APP_CAPTION_FONT_HEIGHT = 0;
    public static final int    S60_2_6_APP_CAPTION_FONT_STYLE = 0;
    public static final String S60_2_6_SMS_TEXT_FONT_TYPEFACE = "LatinBold12";
    public static final int    S60_2_6_SMS_TEXT_FONT_HEIGHT = 0;
    public static final int    S60_2_6_SMS_TEXT_FONT_STYLE = 0;
    
    // S60 3.0
    public static final String S60_3_0_EXE_PATH = "C:\\sys\\bin\\dummy.exe";
    public static final String S60_3_0_APP_PATH = "C:\\sys\\bin\\htiadmin.exe";
    public static final String S60_3_0_APP_NO_PATH = "htiadmin.exe";
    public static final String S60_3_0_APP_NAME = "HtiAdmin";
    public static final String S60_3_0_DOC_PATH = "Z:\\Nokia\\Images\\Pictures\\1.ota";
    public static final String S60_3_0_MIDI_FILE_PATH = "Z:\\data\\Sounds\\Digital\\Message.mid";
    public static final String S60_3_0_RNG_FILE_PATH = "Z:\\data\\Sounds\\Simple\\Intro.rng";
    public static final String S60_3_0_AUDIO_FILE_LIST_PATH = "Z:\\data\\Sounds\\Digital\\";
    public static final String S60_3_0_CONTACT_DB_PATH = "C:\\private\\100012a5\\DBS_100065FF_Contacts.cdb";
    public static final String S60_3_0_APP_CAPTION_FONT_TYPEFACE = "Series 60 Sans TitleSmBd";
    public static final int    S60_3_0_APP_CAPTION_FONT_HEIGHT = 970;
    public static final int    S60_3_0_APP_CAPTION_FONT_STYLE = 0;
    public static final String S60_3_0_SMS_TEXT_FONT_TYPEFACE = "Series 60 Sans";
    public static final int    S60_3_0_SMS_TEXT_FONT_HEIGHT = 136;
    public static final int    S60_3_0_SMS_TEXT_FONT_STYLE = FONT_BOLD;
    
    // S60 3.1
    public static final String S60_3_1_EXE_PATH = "C:\\sys\\bin\\dummy.exe";
    public static final String S60_3_1_APP_PATH = "C:\\sys\\bin\\htiadmin.exe";
    public static final String S60_3_1_APP_NO_PATH = "htiadmin.exe";
    public static final String S60_3_1_APP_NAME = "HtiAdmin";
    public static final String S60_3_1_DOC_PATH = "Z:\\resource\\apps\\About.mbm";
    public static final String S60_3_1_MIDI_FILE_PATH = "Z:\\data\\sounds\\digital\\Clock alert 2.mid";
    public static final String S60_3_1_RNG_FILE_PATH = "Z:\\data\\Sounds\\Simple\\Beep.rng";
    public static final String S60_3_1_AUDIO_FILE_LIST_PATH = "Z:\\data\\Sounds\\Digital\\";
    public static final String S60_3_1_CONTACT_DB_PATH = "C:\\private\\100012a5\\DBS_100065FF_Contacts.cdb";
    public static final String S60_3_1_APP_CAPTION_FONT_TYPEFACE = "Series 60 Hindi TitleSmB";
    public static final int    S60_3_1_APP_CAPTION_FONT_HEIGHT = 21;
    public static final int    S60_3_1_APP_CAPTION_FONT_STYLE = 0;
    public static final String S60_3_1_SMS_TEXT_FONT_TYPEFACE = "Series 60 Hindi";
    public static final int    S60_3_1_SMS_TEXT_FONT_HEIGHT = 16;
    public static final int    S60_3_1_SMS_TEXT_FONT_STYLE = FONT_BOLD;
    
    // S60 3.2
    public static final String S60_3_2_EXE_PATH = "C:\\sys\\bin\\dummy.exe";
    public static final String S60_3_2_APP_PATH = "Z:\\sys\\bin\\about.exe";
    public static final String S60_3_2_APP_NO_PATH = "htiadmin.exe";
    public static final String S60_3_2_APP_NAME = "HtiAdmin";
    public static final String S60_3_2_DOC_PATH = "Z:\\resource\\apps\\About.mbm";
    public static final String S60_3_2_MIDI_FILE_PATH = "Z:\\data\\sounds\\digital\\Nokia tune.aac";
    public static final String S60_3_2_RNG_FILE_PATH = "Z:\\data\\Sounds\\Simple\\Beep.rng";
    public static final String S60_3_2_AUDIO_FILE_LIST_PATH = "Z:\\data\\Sounds\\Digital\\";
    public static final String S60_3_2_CONTACT_DB_PATH = "C:\\private\\10003a73\\contacts.cdb";
    public static final String S60_3_2_APP_CAPTION_FONT_TYPEFACE = "Series 60 Sans TitleSmBd";
    public static final int    S60_3_2_APP_CAPTION_FONT_HEIGHT = 21;
    public static final int    S60_3_2_APP_CAPTION_FONT_STYLE = 0;
    public static final String S60_3_2_SMS_TEXT_FONT_TYPEFACE = "Series 60 Sans"; 
    public static final int    S60_3_2_SMS_TEXT_FONT_HEIGHT = 16;
    public static final int    S60_3_2_SMS_TEXT_FONT_STYLE = FONT_BOLD;
    
    // S60 5.0
    public static final String S60_5_0_EXE_PATH = "C:\\sys\\bin\\dummy.exe";
    public static final String S60_5_0_APP_PATH = "z:\\sys\\bin\\about.exe";
    public static final String S60_5_0_APP_NO_PATH = "htiadmin.exe";
    public static final String S60_5_0_APP_NAME = "HtiAdmin";
    public static final String S60_5_0_DOC_PATH = "Z:\\resource\\apps\\About.mbm";
    public static final String S60_5_0_MIDI_FILE_PATH = "Z:\\data\\sounds\\digital\\Message 1.aac";
    public static final String S60_5_0_RNG_FILE_PATH = "Z:\\data\\Sounds\\Digital\\Beep.rng";
    public static final String S60_5_0_AUDIO_FILE_LIST_PATH = "Z:\\data\\Sounds\\Digital\\";
    public static final String S60_5_0_CONTACT_DB_PATH = "C:\\private\\10003a73\\contacts.cdb";
    public static final String S60_5_0_APP_CAPTION_FONT_TYPEFACE = "Series 60 Sans TitleSmBd";
    public static final int    S60_5_0_APP_CAPTION_FONT_HEIGHT = 27;
    public static final int    S60_5_0_APP_CAPTION_FONT_STYLE = 0;
    public static final String S60_5_0_SMS_TEXT_FONT_TYPEFACE = "Series 60 Sans"; 
    public static final int    S60_5_0_SMS_TEXT_FONT_HEIGHT = 21;
    public static final int    S60_5_0_SMS_TEXT_FONT_STYLE = FONT_BOLD;
    
    // S60 5.1
    public static final String S60_5_1_EXE_PATH = "C:\\sys\\bin\\dummy.exe";
    public static final String S60_5_1_APP_PATH = "z:\\sys\\bin\\about.exe";
    public static final String S60_5_1_APP_NO_PATH = "htiadmin.exe";
    public static final String S60_5_1_APP_NAME = "HtiAdmin";
    public static final String S60_5_1_DOC_PATH = "Z:\\resource\\apps\\About.mbm";
    public static final String S60_5_1_MIDI_FILE_PATH = "Z:\\data\\sounds\\digital\\Message 1.aac";
    public static final String S60_5_1_RNG_FILE_PATH = "Z:\\data\\sounds\\simple\\Beep.rng";
    public static final String S60_5_1_AUDIO_FILE_LIST_PATH = "Z:\\data\\Sounds\\Digital\\";
    public static final String S60_5_1_CONTACT_DB_PATH = "C:\\private\\10003a73\\contacts.cdb";
    public static final String S60_5_1_APP_CAPTION_FONT_TYPEFACE = "Series 60 Sans TitleSmBd";
    public static final int    S60_5_1_APP_CAPTION_FONT_HEIGHT = 38;
    public static final int    S60_5_1_APP_CAPTION_FONT_STYLE = 0;
    public static final String S60_5_1_SMS_TEXT_FONT_TYPEFACE = "Series 60 Sans"; 
    public static final int    S60_5_1_SMS_TEXT_FONT_HEIGHT = 29;
    public static final int    S60_5_1_SMS_TEXT_FONT_STYLE = FONT_BOLD;
    
    // S60 5.2
    public static final String S60_5_2_EXE_PATH = "C:\\sys\\bin\\dummy.exe";
    public static final String S60_5_2_APP_PATH = "z:\\sys\\bin\\about.exe";
    public static final String S60_5_2_APP_NO_PATH = "htiadmin.exe";
    public static final String S60_5_2_APP_NAME = "HtiAdmin";
    public static final String S60_5_2_DOC_PATH = "Z:\\resource\\apps\\About.mbm";
    public static final String S60_5_2_MIDI_FILE_PATH = "Z:\\data\\sounds\\digital\\Message 1.aac";
    public static final String S60_5_2_RNG_FILE_PATH = "Z:\\data\\sounds\\simple\\Beep.rng";
    public static final String S60_5_2_AUDIO_FILE_LIST_PATH = "Z:\\data\\Sounds\\Digital\\";
    public static final String S60_5_2_CONTACT_DB_PATH = "C:\\private\\10003a73\\SQLite__contacts.cdb";
    public static final String S60_5_2_APP_CAPTION_FONT_TYPEFACE = "Series 60 Sans TitleSmBd";
    public static final int    S60_5_2_APP_CAPTION_FONT_HEIGHT = 27;
    public static final int    S60_5_2_APP_CAPTION_FONT_STYLE = 0;
    public static final String S60_5_2_SMS_TEXT_FONT_TYPEFACE = "Series 60 Sans"; 
    public static final int    S60_5_2_SMS_TEXT_FONT_HEIGHT = 21;
    public static final int    S60_5_2_SMS_TEXT_FONT_STYLE = FONT_BOLD;
    
    /*
     * Hardware specific constants for test cases
     * These can override the common and/or platform specific constants
     */
    
    // CHARLIE specific constants
    public static final int CHARLIE_REBOOT_DELAY_MS = 45 * 1000;
    public static final int CHARLIE_SCREEN_WIDTH = SCREEN_WIDTH_LEGACY;
    public static final int CHARLIE_SCREEN_HEIGHT = SCREEN_HEIGHT_LEGACY;

    // MARS specific constants
    public static final int MARS_REBOOT_DELAY_MS = 50 * 1000;
    public static final int MARS_SCREEN_WIDTH = SCREEN_WIDTH_DOUBLE;
    public static final int MARS_SCREEN_HEIGHT = SCREEN_HEIGHT_DOUBLE;
    
    // ZEUS specific constants
    public static final int ZEUS_REBOOT_DELAY_MS = 50 * 1000;
    public static final int ZEUS_SCREEN_WIDTH = SCREEN_WIDTH_DOUBLE;
    public static final int ZEUS_SCREEN_HEIGHT = SCREEN_HEIGHT_DOUBLE;

    // THUNDER specific constants
    public static final int THUNDER_REBOOT_DELAY_MS = 45 * 1000;
    public static final int THUNDER_SCREEN_WIDTH = SCREEN_WIDTH_LEGACY;
    public static final int THUNDER_SCREEN_HEIGHT = SCREEN_HEIGHT_LEGACY;
    
    // SCARFE specific constants
    public static final int SCARFE_REBOOT_DELAY_MS = 60 * 1000;
    public static final int SCARFE_SCREEN_WIDTH = SCREEN_WIDTH_QVGA ;
    public static final int SCARFE_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;

    // ROSETTA specific constants
    public static final int ROSETTA_REBOOT_DELAY_MS = 50 * 1000;
    public static final int ROSETTA_SCREEN_WIDTH = SCREEN_WIDTH_QVGA;
    public static final int ROSETTA_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;

    // VEGA specific constants
    public static final int VEGA_REBOOT_DELAY_MS = 50 * 1000;
    public static final int VEGA_SCREEN_WIDTH = SCREEN_WIDTH_QVGA;
    public static final int VEGA_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;

    // AALTO specific constants
    public static final int AALTO_REBOOT_DELAY_MS = 60 * 1000;
    public static final int AALTO_SCREEN_WIDTH = SCREEN_WIDTH_QVGA;
    public static final int AALTO_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;

    // DEVLON specific constants
    public static final int DEVLON_REBOOT_DELAY_MS = 50 * 1000;
    public static final int DEVLON_SCREEN_WIDTH = 360;
    public static final int DEVLON_SCREEN_HEIGHT = 640;

    // ARIANE specific constants
    public static final int ARIANE_REBOOT_DELAY_MS = 50 * 1000;
    public static final int ARIANE_SCREEN_WIDTH = SCREEN_WIDTH_QVGA;
    public static final int ARIANE_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;
    
    // MERLIN specific constants
    public static final int MERLIN_REBOOT_DELAY_MS = 50 * 1000;
    public static final int MERLIN_SCREEN_WIDTH = SCREEN_WIDTH_QVGA;
    public static final int MERLIN_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;
    
    // LUMIERE specific constants
    public static final int LUMIERE_REBOOT_DELAY_MS = 50 * 1000;
    public static final int LUMIERE_SCREEN_WIDTH = SCREEN_WIDTH_QVGA;
    public static final int LUMIERE_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;
    
    // GADGET specific constants
    public static final int GADGET_REBOOT_DELAY_MS = 50 * 1000;
    public static final int GADGET_SCREEN_WIDTH = SCREEN_WIDTH_QVGA;
    public static final int GADGET_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;
    
    // TUBE specific constants
    public static final int TUBE_REBOOT_DELAY_MS = 50 * 1000;
    public static final int TUBE_SCREEN_WIDTH = SCREEN_WIDTH_WVGA;
    public static final int TUBE_SCREEN_HEIGHT = SCREEN_HEIGHT_WVGA;
    
    // IVALO specific constants
    public static final int IVALO_REBOOT_DELAY_MS = 50 * 1000;
    public static final int IVALO_SCREEN_WIDTH = SCREEN_WIDTH_WVGA;
    public static final int IVALO_SCREEN_HEIGHT = SCREEN_HEIGHT_WVGA;
    
    // COROLLA specific constants
    public static final int COROLLA_REBOOT_DELAY_MS = 50 * 1000;
    public static final int COROLLA_SCREEN_WIDTH = SCREEN_WIDTH_VGA;
    public static final int COROLLA_SCREEN_HEIGHT = SCREEN_HEIGHT_VGA;

    // VASCO specific constants
    public static final int VASCO_REBOOT_DELAY_MS = 50 * 1000;
    public static final int VASCO_SCREEN_WIDTH = SCREEN_WIDTH_WVGA;
    public static final int VASCO_SCREEN_HEIGHT = SCREEN_HEIGHT_WVGA;

    // WINS specific constants
    public static final int WINS_SCREEN_WIDTH = SCREEN_WIDTH_QVGA;
    public static final int WINS_SCREEN_HEIGHT = SCREEN_HEIGHT_QVGA;
    
    /*
     * Data for files used in test cases
     */
        
    // Data of the jpg image file used as an attachment in message plugin tests
    public static final byte[] HTI_JPG_DATA = new byte[] { (byte) 0xff,
            (byte) 0xd8, (byte) 0xff, (byte) 0xe0, (byte) 0x00, (byte) 0x10,
            (byte) 0x4a, (byte) 0x46, (byte) 0x49, (byte) 0x46, (byte) 0x00,
            (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x60,
            (byte) 0x00, (byte) 0x60, (byte) 0x00, (byte) 0x00, (byte) 0xff,
            (byte) 0xdb, (byte) 0x00, (byte) 0x43, (byte) 0x00, (byte) 0x08,
            (byte) 0x06, (byte) 0x06, (byte) 0x07, (byte) 0x06, (byte) 0x05,
            (byte) 0x08, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x09,
            (byte) 0x09, (byte) 0x08, (byte) 0x0a, (byte) 0x0c, (byte) 0x14,
            (byte) 0x0d, (byte) 0x0c, (byte) 0x0b, (byte) 0x0b, (byte) 0x0c,
            (byte) 0x19, (byte) 0x12, (byte) 0x13, (byte) 0x0f, (byte) 0x14,
            (byte) 0x1d, (byte) 0x1a, (byte) 0x1f, (byte) 0x1e, (byte) 0x1d,
            (byte) 0x1a, (byte) 0x1c, (byte) 0x1c, (byte) 0x20, (byte) 0x24,
            (byte) 0x2e, (byte) 0x27, (byte) 0x20, (byte) 0x22, (byte) 0x2c,
            (byte) 0x23, (byte) 0x1c, (byte) 0x1c, (byte) 0x28, (byte) 0x37,
            (byte) 0x29, (byte) 0x2c, (byte) 0x30, (byte) 0x31, (byte) 0x34,
            (byte) 0x34, (byte) 0x34, (byte) 0x1f, (byte) 0x27, (byte) 0x39,
            (byte) 0x3d, (byte) 0x38, (byte) 0x32, (byte) 0x3c, (byte) 0x2e,
            (byte) 0x33, (byte) 0x34, (byte) 0x32, (byte) 0xff, (byte) 0xdb,
            (byte) 0x00, (byte) 0x43, (byte) 0x01, (byte) 0x09, (byte) 0x09,
            (byte) 0x09, (byte) 0x0c, (byte) 0x0b, (byte) 0x0c, (byte) 0x18,
            (byte) 0x0d, (byte) 0x0d, (byte) 0x18, (byte) 0x32, (byte) 0x21,
            (byte) 0x1c, (byte) 0x21, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
            (byte) 0x32, (byte) 0x32, (byte) 0xff, (byte) 0xc0, (byte) 0x00,
            (byte) 0x11, (byte) 0x08, (byte) 0x00, (byte) 0x16, (byte) 0x00,
            (byte) 0x20, (byte) 0x03, (byte) 0x01, (byte) 0x22, (byte) 0x00,
            (byte) 0x02, (byte) 0x11, (byte) 0x01, (byte) 0x03, (byte) 0x11,
            (byte) 0x01, (byte) 0xff, (byte) 0xc4, (byte) 0x00, (byte) 0x1f,
            (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x05, (byte) 0x01,
            (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x02,
            (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
            (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, (byte) 0xff,
            (byte) 0xc4, (byte) 0x00, (byte) 0xb5, (byte) 0x10, (byte) 0x00,
            (byte) 0x02, (byte) 0x01, (byte) 0x03, (byte) 0x03, (byte) 0x02,
            (byte) 0x04, (byte) 0x03, (byte) 0x05, (byte) 0x05, (byte) 0x04,
            (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x7d,
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x00, (byte) 0x04,
            (byte) 0x11, (byte) 0x05, (byte) 0x12, (byte) 0x21, (byte) 0x31,
            (byte) 0x41, (byte) 0x06, (byte) 0x13, (byte) 0x51, (byte) 0x61,
            (byte) 0x07, (byte) 0x22, (byte) 0x71, (byte) 0x14, (byte) 0x32,
            (byte) 0x81, (byte) 0x91, (byte) 0xa1, (byte) 0x08, (byte) 0x23,
            (byte) 0x42, (byte) 0xb1, (byte) 0xc1, (byte) 0x15, (byte) 0x52,
            (byte) 0xd1, (byte) 0xf0, (byte) 0x24, (byte) 0x33, (byte) 0x62,
            (byte) 0x72, (byte) 0x82, (byte) 0x09, (byte) 0x0a, (byte) 0x16,
            (byte) 0x17, (byte) 0x18, (byte) 0x19, (byte) 0x1a, (byte) 0x25,
            (byte) 0x26, (byte) 0x27, (byte) 0x28, (byte) 0x29, (byte) 0x2a,
            (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38,
            (byte) 0x39, (byte) 0x3a, (byte) 0x43, (byte) 0x44, (byte) 0x45,
            (byte) 0x46, (byte) 0x47, (byte) 0x48, (byte) 0x49, (byte) 0x4a,
            (byte) 0x53, (byte) 0x54, (byte) 0x55, (byte) 0x56, (byte) 0x57,
            (byte) 0x58, (byte) 0x59, (byte) 0x5a, (byte) 0x63, (byte) 0x64,
            (byte) 0x65, (byte) 0x66, (byte) 0x67, (byte) 0x68, (byte) 0x69,
            (byte) 0x6a, (byte) 0x73, (byte) 0x74, (byte) 0x75, (byte) 0x76,
            (byte) 0x77, (byte) 0x78, (byte) 0x79, (byte) 0x7a, (byte) 0x83,
            (byte) 0x84, (byte) 0x85, (byte) 0x86, (byte) 0x87, (byte) 0x88,
            (byte) 0x89, (byte) 0x8a, (byte) 0x92, (byte) 0x93, (byte) 0x94,
            (byte) 0x95, (byte) 0x96, (byte) 0x97, (byte) 0x98, (byte) 0x99,
            (byte) 0x9a, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5,
            (byte) 0xa6, (byte) 0xa7, (byte) 0xa8, (byte) 0xa9, (byte) 0xaa,
            (byte) 0xb2, (byte) 0xb3, (byte) 0xb4, (byte) 0xb5, (byte) 0xb6,
            (byte) 0xb7, (byte) 0xb8, (byte) 0xb9, (byte) 0xba, (byte) 0xc2,
            (byte) 0xc3, (byte) 0xc4, (byte) 0xc5, (byte) 0xc6, (byte) 0xc7,
            (byte) 0xc8, (byte) 0xc9, (byte) 0xca, (byte) 0xd2, (byte) 0xd3,
            (byte) 0xd4, (byte) 0xd5, (byte) 0xd6, (byte) 0xd7, (byte) 0xd8,
            (byte) 0xd9, (byte) 0xda, (byte) 0xe1, (byte) 0xe2, (byte) 0xe3,
            (byte) 0xe4, (byte) 0xe5, (byte) 0xe6, (byte) 0xe7, (byte) 0xe8,
            (byte) 0xe9, (byte) 0xea, (byte) 0xf1, (byte) 0xf2, (byte) 0xf3,
            (byte) 0xf4, (byte) 0xf5, (byte) 0xf6, (byte) 0xf7, (byte) 0xf8,
            (byte) 0xf9, (byte) 0xfa, (byte) 0xff, (byte) 0xc4, (byte) 0x00,
            (byte) 0x1f, (byte) 0x01, (byte) 0x00, (byte) 0x03, (byte) 0x01,
            (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01,
            (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01,
            (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06,
            (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b,
            (byte) 0xff, (byte) 0xc4, (byte) 0x00, (byte) 0xb5, (byte) 0x11,
            (byte) 0x00, (byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x04,
            (byte) 0x04, (byte) 0x03, (byte) 0x04, (byte) 0x07, (byte) 0x05,
            (byte) 0x04, (byte) 0x04, (byte) 0x00, (byte) 0x01, (byte) 0x02,
            (byte) 0x77, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
            (byte) 0x11, (byte) 0x04, (byte) 0x05, (byte) 0x21, (byte) 0x31,
            (byte) 0x06, (byte) 0x12, (byte) 0x41, (byte) 0x51, (byte) 0x07,
            (byte) 0x61, (byte) 0x71, (byte) 0x13, (byte) 0x22, (byte) 0x32,
            (byte) 0x81, (byte) 0x08, (byte) 0x14, (byte) 0x42, (byte) 0x91,
            (byte) 0xa1, (byte) 0xb1, (byte) 0xc1, (byte) 0x09, (byte) 0x23,
            (byte) 0x33, (byte) 0x52, (byte) 0xf0, (byte) 0x15, (byte) 0x62,
            (byte) 0x72, (byte) 0xd1, (byte) 0x0a, (byte) 0x16, (byte) 0x24,
            (byte) 0x34, (byte) 0xe1, (byte) 0x25, (byte) 0xf1, (byte) 0x17,
            (byte) 0x18, (byte) 0x19, (byte) 0x1a, (byte) 0x26, (byte) 0x27,
            (byte) 0x28, (byte) 0x29, (byte) 0x2a, (byte) 0x35, (byte) 0x36,
            (byte) 0x37, (byte) 0x38, (byte) 0x39, (byte) 0x3a, (byte) 0x43,
            (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x48,
            (byte) 0x49, (byte) 0x4a, (byte) 0x53, (byte) 0x54, (byte) 0x55,
            (byte) 0x56, (byte) 0x57, (byte) 0x58, (byte) 0x59, (byte) 0x5a,
            (byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x67,
            (byte) 0x68, (byte) 0x69, (byte) 0x6a, (byte) 0x73, (byte) 0x74,
            (byte) 0x75, (byte) 0x76, (byte) 0x77, (byte) 0x78, (byte) 0x79,
            (byte) 0x7a, (byte) 0x82, (byte) 0x83, (byte) 0x84, (byte) 0x85,
            (byte) 0x86, (byte) 0x87, (byte) 0x88, (byte) 0x89, (byte) 0x8a,
            (byte) 0x92, (byte) 0x93, (byte) 0x94, (byte) 0x95, (byte) 0x96,
            (byte) 0x97, (byte) 0x98, (byte) 0x99, (byte) 0x9a, (byte) 0xa2,
            (byte) 0xa3, (byte) 0xa4, (byte) 0xa5, (byte) 0xa6, (byte) 0xa7,
            (byte) 0xa8, (byte) 0xa9, (byte) 0xaa, (byte) 0xb2, (byte) 0xb3,
            (byte) 0xb4, (byte) 0xb5, (byte) 0xb6, (byte) 0xb7, (byte) 0xb8,
            (byte) 0xb9, (byte) 0xba, (byte) 0xc2, (byte) 0xc3, (byte) 0xc4,
            (byte) 0xc5, (byte) 0xc6, (byte) 0xc7, (byte) 0xc8, (byte) 0xc9,
            (byte) 0xca, (byte) 0xd2, (byte) 0xd3, (byte) 0xd4, (byte) 0xd5,
            (byte) 0xd6, (byte) 0xd7, (byte) 0xd8, (byte) 0xd9, (byte) 0xda,
            (byte) 0xe2, (byte) 0xe3, (byte) 0xe4, (byte) 0xe5, (byte) 0xe6,
            (byte) 0xe7, (byte) 0xe8, (byte) 0xe9, (byte) 0xea, (byte) 0xf2,
            (byte) 0xf3, (byte) 0xf4, (byte) 0xf5, (byte) 0xf6, (byte) 0xf7,
            (byte) 0xf8, (byte) 0xf9, (byte) 0xfa, (byte) 0xff, (byte) 0xda,
            (byte) 0x00, (byte) 0x0c, (byte) 0x03, (byte) 0x01, (byte) 0x00,
            (byte) 0x02, (byte) 0x11, (byte) 0x03, (byte) 0x11, (byte) 0x00,
            (byte) 0x3f, (byte) 0x00, (byte) 0xf7, (byte) 0xfa, (byte) 0xcb,
            (byte) 0x1e, (byte) 0x25, (byte) 0xd0, (byte) 0x4d, (byte) 0xbd,
            (byte) 0xbd, (byte) 0xc0, (byte) 0xd6, (byte) 0xf4, (byte) 0xdf,
            (byte) 0x22, (byte) 0xe1, (byte) 0x25, (byte) 0x78, (byte) 0x24,
            (byte) 0xfb, (byte) 0x52, (byte) 0x6d, (byte) 0x95, (byte) 0x63,
            (byte) 0x04, (byte) 0xc8, (byte) 0x54, (byte) 0xe7, (byte) 0x04,
            (byte) 0x28, (byte) 0x04, (byte) 0xb1, (byte) 0x1d, (byte) 0x31,
            (byte) 0xce, (byte) 0x2b, (byte) 0x52, (byte) 0xbc, (byte) 0x1f,
            (byte) 0xc0, (byte) 0x5e, (byte) 0x19, (byte) 0xd7, (byte) 0x74,
            (byte) 0xfb, (byte) 0xcf, (byte) 0x86, (byte) 0x53, (byte) 0xde,
            (byte) 0x5b, (byte) 0x6a, (byte) 0xa2, (byte) 0x3b, (byte) 0x7f,
            (byte) 0xed, (byte) 0x5f, (byte) 0xb4, (byte) 0x41, (byte) 0x3d,
            (byte) 0xa8, (byte) 0x44, (byte) 0xb1, (byte) 0xdc, (byte) 0x18,
            (byte) 0x2e, (byte) 0x48, (byte) 0x40, (byte) 0xcb, (byte) 0xbf,
            (byte) 0x20, (byte) 0xfc, (byte) 0xe4, (byte) 0xe7, (byte) 0xf8,
            (byte) 0x70, (byte) 0x38, (byte) 0xa0, (byte) 0x0f, (byte) 0x5c,
            (byte) 0x8f, (byte) 0xc6, (byte) 0x9e, (byte) 0x15, (byte) 0x99,
            (byte) 0x26, (byte) 0x78, (byte) 0xbc, (byte) 0x4b, (byte) 0xa3,
            (byte) 0x3a, (byte) 0x42, (byte) 0x9b, (byte) 0xe5, (byte) 0x65,
            (byte) 0xbf, (byte) 0x88, (byte) 0x84, (byte) 0x5d, (byte) 0xc1,
            (byte) 0x72, (byte) 0xdf, (byte) 0x37, (byte) 0x03, (byte) 0x73,
            (byte) 0x28, (byte) 0xc9, (byte) 0xee, (byte) 0x40, (byte) 0xef,
            (byte) 0x5b, (byte) 0x95, (byte) 0xe1, (byte) 0xfa, (byte) 0xb6,
            (byte) 0x85, (byte) 0xac, (byte) 0x49, (byte) 0xf0, (byte) 0xb7,
            (byte) 0xe2, (byte) 0x3d, (byte) 0x9c, (byte) 0x7a, (byte) 0x55,
            (byte) 0xf3, (byte) 0xdd, (byte) 0x5d, (byte) 0xf8, (byte) 0x96,
            (byte) 0x69, (byte) 0xed, (byte) 0xa1, (byte) 0x5b, (byte) 0x77,
            (byte) 0x2f, (byte) 0x34, (byte) 0x66, (byte) 0x78, (byte) 0x48,
            (byte) 0x74, (byte) 0x5c, (byte) 0x65, (byte) 0x97, (byte) 0x00,
            (byte) 0x9c, (byte) 0x8e, (byte) 0x38, (byte) 0x35, (byte) 0xee,
            (byte) 0x14, (byte) 0x00, (byte) 0x51, (byte) 0x45, (byte) 0x14,
            (byte) 0x00, (byte) 0x51, (byte) 0x45, (byte) 0x14, (byte) 0x01,
            (byte) 0xff, (byte) 0xd9 };
    
    // Data for the SISX file containing Dummy.exe
    public static final byte[] DUMMY_SISX_DATA = {
            (byte) 0x7a, (byte) 0x1a, (byte) 0x20, (byte) 0x10, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xb6, (byte) 0xde, 
            (byte) 0x30, (byte) 0x10, (byte) 0xd2, (byte) 0x49, (byte) 0xc5, 
            (byte) 0xbc, (byte) 0x0c, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x44, (byte) 0x0a, (byte) 0x00, (byte) 0x00, (byte) 0x22, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0xee, (byte) 0xde, (byte) 0x00, 
            (byte) 0x00, (byte) 0x23, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x28, 
            (byte) 0xf3, (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, 
            (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x3c, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x78, (byte) 0x9c, 
            (byte) 0xe3, (byte) 0x65, (byte) 0x60, (byte) 0x60, (byte) 0x30, 
            (byte) 0x61, (byte) 0x63, (byte) 0x60, (byte) 0xe0, (byte) 0x03, 
            (byte) 0xd2, (byte) 0x7d, (byte) 0x40, (byte) 0xcc, (byte) 0x09, 
            (byte) 0xc4, (byte) 0x2c, (byte) 0x40, (byte) 0xbc, (byte) 0xed, 
            (byte) 0x9e, (byte) 0x81, (byte) 0x00, (byte) 0x23, (byte) 0x90, 
            (byte) 0xe6, (byte) 0x02, (byte) 0x62, (byte) 0x3f, (byte) 0x86, 
            (byte) 0x7c, (byte) 0x86, (byte) 0x6c, (byte) 0x86, (byte) 0x4c, 
            (byte) 0x86, (byte) 0x44, (byte) 0x20, (byte) 0x9b, (byte) 0x09, 
            (byte) 0x88, (byte) 0x45, (byte) 0x80, (byte) 0x18, (byte) 0x26, 
            (byte) 0xe7, (byte) 0xc2, (byte) 0x50, (byte) 0xca, (byte) 0x90, 
            (byte) 0x0b, (byte) 0x84, (byte) 0x95, (byte) 0x58, (byte) 0xe4, 
            (byte) 0x90, (byte) 0xf5, (byte) 0x81, (byte) 0xcc, (byte) 0xe4, 
            (byte) 0x81, (byte) 0xca, (byte) 0xc1, (byte) 0x00, (byte) 0x07, 
            (byte) 0x10, (byte) 0x4b, (byte) 0x00, (byte) 0x31, (byte) 0x1b, 
            (byte) 0x54, (byte) 0xfe, (byte) 0x06, (byte) 0x3b, (byte) 0xa7, 
            (byte) 0x28, (byte) 0x3b, (byte) 0x90, (byte) 0x66, (byte) 0x06, 
            (byte) 0xa9, (byte) 0x15, (byte) 0x62, (byte) 0x03, (byte) 0xab, 
            (byte) 0x11, (byte) 0x80, (byte) 0xea, (byte) 0x63, (byte) 0x82, 
            (byte) 0xaa, (byte) 0x51, (byte) 0x04, (byte) 0x62, (byte) 0x7e, 
            (byte) 0xa8, (byte) 0x3d, (byte) 0x4c, (byte) 0x50, (byte) 0x39, 
            (byte) 0x6e, (byte) 0xa8, (byte) 0x1c, (byte) 0xc8, (byte) 0x6c, 
            (byte) 0x41, (byte) 0x20, (byte) 0xae, (byte) 0x80, (byte) 0xca, 
            (byte) 0xa5, (byte) 0x00, (byte) 0xb1, (byte) 0x10, (byte) 0x10, 
            (byte) 0xc7, (byte) 0x20, (byte) 0xf9, (byte) 0x2b, (byte) 0xb1, 
            (byte) 0x52, (byte) 0x5e, (byte) 0x80, (byte) 0x15, (byte) 0xaa, 
            (byte) 0x1f, (byte) 0xe6, (byte) 0x26, (byte) 0x64, (byte) 0x00, 
            (byte) 0xd2, (byte) 0xa7, (byte) 0x03, (byte) 0x35, (byte) 0x4b, 
            (byte) 0x09, (byte) 0x88, (byte) 0x83, (byte) 0x19, (byte) 0x52, 
            (byte) 0x19, (byte) 0x8a, (byte) 0x80, (byte) 0x7e, (byte) 0x48, 
            (byte) 0x65, (byte) 0x28, (byte) 0x66, (byte) 0x30, (byte) 0x63, 
            (byte) 0x30, (byte) 0x60, (byte) 0x08, (byte) 0x00, (byte) 0xf2, 
            (byte) 0xf2, (byte) 0x81, (byte) 0x26, (byte) 0x97, (byte) 0x32, 
            (byte) 0x24, (byte) 0x33, (byte) 0x94, (byte) 0x30, (byte) 0x78, 
            (byte) 0x02, (byte) 0xfd, (byte) 0x8f, (byte) 0x70, (byte) 0x1b, 
            (byte) 0xc8, (byte) 0x2e, (byte) 0x61, (byte) 0x34, (byte) 0xf7, 
            (byte) 0x82, (byte) 0xec, (byte) 0x91, (byte) 0x01, (byte) 0xe2, 
            (byte) 0x0d, (byte) 0x50, (byte) 0xb1, (byte) 0x09, (byte) 0x50, 
            (byte) 0x3f, (byte) 0x77, (byte) 0x40, (byte) 0xed, (byte) 0xd0, 
            (byte) 0x00, (byte) 0xfb, (byte) 0xc9, (byte) 0x0a, (byte) 0xe8, 
            (byte) 0xc6, (byte) 0x60, (byte) 0x60, (byte) 0x18, (byte) 0x16, 
            (byte) 0x03, (byte) 0x69, (byte) 0x27, (byte) 0xa0, (byte) 0x6d, 
            (byte) 0x79, (byte) 0x40, (byte) 0x1a, (byte) 0x11, (byte) 0xb2, 
            (byte) 0x7a, (byte) 0x40, (byte) 0xdb, (byte) 0x2b, (byte) 0x80, 
            (byte) 0x18, (byte) 0x16, (byte) 0x76, (byte) 0x9a, (byte) 0x50, 
            (byte) 0xb3, (byte) 0xff, (byte) 0xfd, (byte) 0xe7, (byte) 0x67, 
            (byte) 0x90, (byte) 0x04, (byte) 0xd2, (byte) 0x0a, (byte) 0x50, 
            (byte) 0xb3, (byte) 0x54, (byte) 0xa1, (byte) 0xf6, (byte) 0xe5, 
            (byte) 0xe8, (byte) 0xef, (byte) 0x4b, (byte) 0x69, (byte) 0x16, 
            (byte) 0x73, (byte) 0x3a, (byte) 0xb9, (byte) 0xfe, (byte) 0xf6, 
            (byte) 0xea, (byte) 0xf5, (byte) 0xad, (byte) 0xcf, (byte) 0xd3, 
            (byte) 0x9e, (byte) 0x7c, (byte) 0x95, (byte) 0x52, (byte) 0x28, 
            (byte) 0x86, (byte) 0xe9, (byte) 0x7d, (byte) 0xce, (byte) 0x8a, 
            (byte) 0x4a, (byte) 0xc3, (byte) 0xfc, (byte) 0x0c, (byte) 0x32, 
            (byte) 0x8f, (byte) 0x17, (byte) 0x89, (byte) 0x2d, (byte) 0x05, 
            (byte) 0xc4, (byte) 0xea, (byte) 0x20, (byte) 0xbf, (byte) 0xb0, 
            (byte) 0x40, (byte) 0xc4, (byte) 0x4e, (byte) 0x00, (byte) 0xb1, 
            (byte) 0x0a, (byte) 0x10, (byte) 0x1f, (byte) 0x00, (byte) 0x62, 
            (byte) 0x35, (byte) 0x20, (byte) 0x36, (byte) 0x40, (byte) 0x72, 
            (byte) 0xbf, (byte) 0x21, (byte) 0xd0, (byte) 0x85, (byte) 0x46, 
            (byte) 0x40, (byte) 0x6c, (byte) 0xc1, (byte) 0x60, (byte) 0x02, 
            (byte) 0x14, (byte) 0xd7, (byte) 0x03, (byte) 0xf2, (byte) 0x0d, 
            (byte) 0x19, (byte) 0x8c, (byte) 0x19, (byte) 0x4c, (byte) 0x81, 
            (byte) 0x3c, (byte) 0x4b, (byte) 0x30, (byte) 0x0f, (byte) 0x82, 
            (byte) 0x4d, (byte) 0xc1, (byte) 0xee, (byte) 0x6b, (byte) 0x00, 
            (byte) 0x85, (byte) 0x07, (byte) 0x53, (byte) 0x42, (byte) 0xa2, 
            (byte) 0x70, (byte) 0xf0, (byte) 0x5d, (byte) 0xb7, (byte) 0xd4, 
            (byte) 0xaa, (byte) 0xf4, (byte) 0x47, (byte) 0x0c, (byte) 0x25, 
            (byte) 0x73, (byte) 0xb5, (byte) 0x32, (byte) 0x6d, (byte) 0x1d, 
            (byte) 0xed, (byte) 0x6f, (byte) 0x31, (byte) 0xec, (byte) 0x5a, 
            (byte) 0x79, (byte) 0xe1, (byte) 0x8a, (byte) 0xb2, (byte) 0x96, 
            (byte) 0x98, (byte) 0xdf, (byte) 0x22, (byte) 0x55, (byte) 0x21, 
            (byte) 0x0e, (byte) 0x4d, (byte) 0x9f, (byte) 0xc4, (byte) 0x6f, 
            (byte) 0x52, (byte) 0xf5, (byte) 0x87, (byte) 0x0e, (byte) 0x6c, 
            (byte) 0xf8, (byte) 0x63, (byte) 0x9b, (byte) 0xb0, (byte) 0xe9, 
            (byte) 0xbe, (byte) 0x53, (byte) 0xf1, (byte) 0x79, (byte) 0xb1, 
            (byte) 0xc3, (byte) 0x66, (byte) 0x0a, (byte) 0x1f, (byte) 0x0f, 
            (byte) 0xf5, (byte) 0xef, (byte) 0x12, (byte) 0x9e, (byte) 0x2d, 
            (byte) 0x70, (byte) 0x66, (byte) 0xf5, (byte) 0x3a, (byte) 0x0d, 
            (byte) 0xb7, (byte) 0x7a, (byte) 0x05, (byte) 0xe7, (byte) 0x55, 
            (byte) 0xbb, (byte) 0x56, (byte) 0x9a, (byte) 0x37, (byte) 0x6a, 
            (byte) 0x6f, (byte) 0x3f, (byte) 0xb2, (byte) 0xce, (byte) 0x47, 
            (byte) 0x25, (byte) 0x20, (byte) 0xec, (byte) 0x7c, (byte) 0x90, 
            (byte) 0x5f, (byte) 0x45, (byte) 0x9f, (byte) 0xe7, (byte) 0xb9, 
            (byte) 0x0b, (byte) 0x65, (byte) 0x2a, (byte) 0x32, (byte) 0xa2, 
            (byte) 0xba, (byte) 0x13, (byte) 0x5f, (byte) 0x07, (byte) 0xef, 
            (byte) 0x9c, (byte) 0xeb, (byte) 0xa7, (byte) 0xfa, (byte) 0x97, 
            (byte) 0xeb, (byte) 0xe8, (byte) 0xdd, (byte) 0xea, (byte) 0x15, 
            (byte) 0x29, (byte) 0xed, (byte) 0x9b, (byte) 0xf5, (byte) 0x9f, 
            (byte) 0x89, (byte) 0x70, (byte) 0xf6, (byte) 0x5b, (byte) 0xc8, 
            (byte) 0x6b, (byte) 0x2e, (byte) 0xf6, (byte) 0x7f, (byte) 0x70, 
            (byte) 0x77, (byte) 0xf3, (byte) 0xe1, (byte) 0xdd, (byte) 0xc6, 
            (byte) 0x62, (byte) 0x40, (byte) 0x37, (byte) 0x98, (byte) 0x30, 
            (byte) 0x43, (byte) 0xc2, (byte) 0x4b, (byte) 0x07, (byte) 0x48, 
            (byte) 0x1b, (byte) 0x34, (byte) 0x31, (byte) 0x6b, (byte) 0x18, 
            (byte) 0x34, (byte) 0x31, (byte) 0x4d, (byte) 0x5c, (byte) 0xc0, 
            (byte) 0xcc, (byte) 0xc4, (byte) 0xc8, (byte) 0xc4, (byte) 0xc4, 
            (byte) 0xc8, (byte) 0x60, (byte) 0xc0, (byte) 0xcb, (byte) 0xc6, 
            (byte) 0xa9, (byte) 0xd5, (byte) 0xe6, (byte) 0xd1, (byte) 0xf6, 
            (byte) 0x9d, (byte) 0x97, (byte) 0x91, (byte) 0x91, (byte) 0x95, 
            (byte) 0x95, (byte) 0xc1, (byte) 0x20, (byte) 0xd7, (byte) 0x90, 
            (byte) 0xdb, (byte) 0x80, (byte) 0x93, (byte) 0x8d, (byte) 0x39, 
            (byte) 0x94, (byte) 0x85, (byte) 0x4d, (byte) 0x98, (byte) 0xc9, 
            (byte) 0xcd, (byte) 0xd3, (byte) 0x50, (byte) 0xc0, (byte) 0x80, 
            (byte) 0x0f, (byte) 0xc4, (byte) 0xe1, (byte) 0x10, (byte) 0x66, 
            (byte) 0x77, (byte) 0xcb, (byte) 0xcc, (byte) 0xcb, (byte) 0x49, 
            (byte) 0xcc, (byte) 0x4b, (byte) 0x81, (byte) 0x89, (byte) 0xb0, 
            (byte) 0x0b, (byte) 0xb3, (byte) 0x87, (byte) 0x24, (byte) 0xe6, 
            (byte) 0x16, (byte) 0xa4, (byte) 0x16, (byte) 0xa5, (byte) 0x1a, 
            (byte) 0xf2, (byte) 0x19, (byte) 0xf0, (byte) 0x80, (byte) 0x44, 
            (byte) 0xb8, (byte) 0x84, (byte) 0x59, (byte) 0xfd, (byte) 0xf2, 
            (byte) 0xb3, (byte) 0x33, (byte) 0x13, (byte) 0x0d, (byte) 0x85, 
            (byte) 0x0c, (byte) 0x04, (byte) 0x40, (byte) 0x7c, (byte) 0x6e, 
            (byte) 0x61, (byte) 0xce, (byte) 0xe0, (byte) 0xd4, (byte) 0xa2, 
            (byte) 0xcc, (byte) 0xd4, (byte) 0x62, (byte) 0x05, (byte) 0x33, 
            (byte) 0x03, (byte) 0x43, (byte) 0x31, (byte) 0x03, (byte) 0x11, 
            (byte) 0x90, (byte) 0x18, (byte) 0xb3, (byte) 0x08, (byte) 0x2f, 
            (byte) 0x5c, (byte) 0x4c, (byte) 0x21, (byte) 0x48, (byte) 0xcd, 
            (byte) 0xc5, (byte) 0x40, (byte) 0x4e, (byte) 0x9c, (byte) 0xd7, 
            (byte) 0xc0, (byte) 0xd4, (byte) 0xc0, (byte) 0xc4, (byte) 0x10, 
            (byte) 0x08, (byte) 0x8c, (byte) 0x4c, (byte) 0x0d, (byte) 0x0c, 
            (byte) 0xa2, (byte) 0xc4, (byte) 0x79, (byte) 0x8d, (byte) 0x8d, 
            (byte) 0x0c, (byte) 0x2c, (byte) 0x8c, (byte) 0xcc, (byte) 0xa1, 
            (byte) 0x5c, (byte) 0x7a, (byte) 0x3b, (byte) 0xa7, (byte) 0x71, 
            (byte) 0x3e, (byte) 0x72, (byte) 0x78, (byte) 0x30, (byte) 0xb2, 
            (byte) 0x32, (byte) 0x30, (byte) 0x37, (byte) 0xf6, (byte) 0x32, 
            (byte) 0x18, (byte) 0x34, (byte) 0x76, (byte) 0x32, (byte) 0x35, 
            (byte) 0x36, (byte) 0x32, (byte) 0xec, (byte) 0xf7, (byte) 0x31, 
            (byte) 0xfe, (byte) 0xad, (byte) 0xb1, (byte) 0x48, (byte) 0xaa, 
            (byte) 0x7e, (byte) 0xfe, (byte) 0xba, (byte) 0xe8, (byte) 0xe6, 
            (byte) 0xe6, (byte) 0x5b, (byte) 0xaa, (byte) 0x91, (byte) 0x3b, 
            (byte) 0x9d, (byte) 0x99, (byte) 0x6e, (byte) 0xae, (byte) 0x9f, 
            (byte) 0x54, (byte) 0xc9, (byte) 0xf2, (byte) 0xb8, (byte) 0x7e, 
            (byte) 0xb5, (byte) 0xf6, (byte) 0x94, (byte) 0xe5, (byte) 0x7c, 
            (byte) 0xb3, (byte) 0x1f, (byte) 0xac, (byte) 0xb4, (byte) 0xb9, 
            (byte) 0xfa, (byte) 0x7c, (byte) 0xc7, (byte) 0xe6, (byte) 0xb4, 
            (byte) 0xff, (byte) 0xc7, (byte) 0xef, (byte) 0x0a, (byte) 0xd5, 
            (byte) 0xf4, (byte) 0x29, (byte) 0x3d, (byte) 0x09, (byte) 0xb7, 
            (byte) 0x15, (byte) 0x5a, (byte) 0xb6, (byte) 0x4a, (byte) 0xda, 
            (byte) 0x74, (byte) 0xbe, (byte) 0x53, (byte) 0xca, (byte) 0x11, 
            (byte) 0xc7, (byte) 0xf6, (byte) 0x53, (byte) 0x85, (byte) 0xdc, 
            (byte) 0x11, (byte) 0x47, (byte) 0xde, (byte) 0x69, (byte) 0x96, 
            (byte) 0x5d, (byte) 0x38, (byte) 0x7e, (byte) 0x65, (byte) 0x22, 
            (byte) 0x43, (byte) 0xaa, (byte) 0xf8, (byte) 0x79, (byte) 0xcd, 
            (byte) 0x7b, (byte) 0x52, (byte) 0xf5, (byte) 0x1d, (byte) 0x2a, 
            (byte) 0x37, (byte) 0xcf, (byte) 0xed, (byte) 0x79, (byte) 0xd4, 
            (byte) 0x1e, (byte) 0xc5, (byte) 0xd4, (byte) 0xb4, (byte) 0xe8, 
            (byte) 0xe9, (byte) 0xb7, (byte) 0xd6, (byte) 0x4f, (byte) 0x36, 
            (byte) 0xeb, (byte) 0x6e, (byte) 0xca, (byte) 0xae, (byte) 0x98, 
            (byte) 0x95, (byte) 0x35, (byte) 0xe3, (byte) 0x9d, (byte) 0xd5, 
            (byte) 0x25, (byte) 0xc6, (byte) 0x35, (byte) 0x46, (byte) 0x3c, 
            (byte) 0x76, (byte) 0xd3, (byte) 0x8e, (byte) 0xdd, (byte) 0x3b, 
            (byte) 0x99, (byte) 0x33, (byte) 0xed, (byte) 0x5b, (byte) 0xe4, 
            (byte) 0x7d, (byte) 0x7d, (byte) 0xa9, (byte) 0x7d, (byte) 0x29, 
            (byte) 0x7a, (byte) 0x6f, (byte) 0x56, (byte) 0x32, (byte) 0x31, 
            (byte) 0x33, (byte) 0x32, (byte) 0x30, (byte) 0x2e, (byte) 0x6e, 
            (byte) 0xbc, (byte) 0x6e, (byte) 0xd0, (byte) 0x78, (byte) 0xc5, 
            (byte) 0x40, (byte) 0x16, (byte) 0xe8, (byte) 0x7a, (byte) 0x59, 
            (byte) 0x3e, (byte) 0x16, (byte) 0x31, (byte) 0x16, (byte) 0x91, 
            (byte) 0x92, (byte) 0xdc, (byte) 0x79, (byte) 0x8d, (byte) 0x17, 
            (byte) 0xbf, (byte) 0x31, (byte) 0x35, (byte) 0x9b, (byte) 0x7e, 
            (byte) 0x9f, (byte) 0x91, (byte) 0xa0, (byte) 0xb2, (byte) 0xdd, 
            (byte) 0xea, (byte) 0xcf, (byte) 0x5b, (byte) 0x27, (byte) 0x8b, 
            (byte) 0x5a, (byte) 0x83, (byte) 0xc6, (byte) 0xe9, (byte) 0x20, 
            (byte) 0x79, (byte) 0x65, (byte) 0x96, (byte) 0xc6, (byte) 0x7e, 
            (byte) 0x83, (byte) 0xc6, (byte) 0x9e, (byte) 0x06, (byte) 0xac, 
            (byte) 0x6a, (byte) 0x16, (byte) 0x16, (byte) 0x2e, (byte) 0xc9, 
            (byte) 0xa7, (byte) 0x73, (byte) 0xe8, (byte) 0x36, (byte) 0x01, 
            (byte) 0x53, (byte) 0x1b, (byte) 0x48, (byte) 0xb3, (byte) 0xac, 
            (byte) 0x30, (byte) 0x0b, (byte) 0xab, (byte) 0x01, (byte) 0x33, 
            (byte) 0x23, (byte) 0xe3, (byte) 0x7f, (byte) 0x03, (byte) 0x6e, 
            (byte) 0x10, (byte) 0x8f, (byte) 0x9f, (byte) 0x85, (byte) 0x85, 
            (byte) 0x99, (byte) 0x89, (byte) 0xa9, (byte) 0x05, (byte) 0x2d, 
            (byte) 0x21, (byte) 0x32, (byte) 0x03, (byte) 0x43, (byte) 0x9c, 
            (byte) 0x7f, (byte) 0x71, (byte) 0x67, (byte) 0xc5, (byte) 0x42, 
            (byte) 0xc1, (byte) 0x7c, (byte) 0xe9, (byte) 0xa9, (byte) 0x4b, 
            (byte) 0x77, (byte) 0xa6, (byte) 0x7d, (byte) 0x7d, (byte) 0xfb, 
            (byte) 0xed, (byte) 0xaf, (byte) 0x68, (byte) 0x19, (byte) 0xc7, 
            (byte) 0xe4, (byte) 0x1f, (byte) 0x39, (byte) 0x1e, (byte) 0xd6, 
            (byte) 0x96, (byte) 0xe1, (byte) 0xca, (byte) 0x0f, (byte) 0x73, 
            (byte) 0xec, (byte) 0x85, (byte) 0x76, (byte) 0x66, (byte) 0xd5, 
            (byte) 0xee, (byte) 0x4a, (byte) 0xba, (byte) 0x27, (byte) 0x37, 
            (byte) 0xd5, (byte) 0xf1, (byte) 0x8f, (byte) 0xf8, (byte) 0x93, 
            (byte) 0xff, (byte) 0xf9, (byte) 0xd3, (byte) 0x6c, (byte) 0x4d, 
            (byte) 0x59, (byte) 0xd5, (byte) 0x72, (byte) 0x0e, (byte) 0x3f, 
            (byte) 0x98, (byte) 0x78, (byte) 0x90, (byte) 0xf1, (byte) 0x44, 
            (byte) 0xc9, (byte) 0x8e, (byte) 0x83, (byte) 0x12, (byte) 0xdb, 
            (byte) 0x0a, (byte) 0x4e, (byte) 0xaf, (byte) 0x56, (byte) 0xbc, 
            (byte) 0xa2, (byte) 0xcc, (byte) 0xf2, (byte) 0xcd, (byte) 0xf9, 
            (byte) 0xc5, (byte) 0x03, (byte) 0xb7, (byte) 0xeb, (byte) 0x4f, 
            (byte) 0xbd, (byte) 0x5e, (byte) 0x1e, (byte) 0xdc, (byte) 0x74, 
            (byte) 0xcc, (byte) 0x95, (byte) 0xfd, (byte) 0x84, (byte) 0xb0, 
            (byte) 0xa8, (byte) 0x7e, (byte) 0xe8, (byte) 0xb1, (byte) 0x62, 
            (byte) 0x75, (byte) 0x11, (byte) 0x5d, (byte) 0xf9, (byte) 0x63, 
            (byte) 0x1b, (byte) 0x7b, (byte) 0x5e, (byte) 0x76, (byte) 0x67, 
            (byte) 0x4b, (byte) 0xe4, (byte) 0xc9, (byte) 0xad, (byte) 0x16, 
            (byte) 0xb1, (byte) 0x73, (byte) 0x9c, (byte) 0x15, (byte) 0x1d, 
            (byte) 0x37, (byte) 0x95, (byte) 0x77, (byte) 0x52, (byte) 0xc3, 
            (byte) 0x82, (byte) 0x8b, (byte) 0x9f, (byte) 0x97, (byte) 0xa6, 
            (byte) 0x4e, (byte) 0x4b, (byte) 0x16, (byte) 0xa9, (byte) 0x3b, 
            (byte) 0x76, (byte) 0xe1, (byte) 0x8f, (byte) 0x81, (byte) 0xa7, 
            (byte) 0x06, (byte) 0xb4, (byte) 0x7c, (byte) 0x00, (byte) 0x01, 
            (byte) 0x00, (byte) 0xb1, (byte) 0x7d, (byte) 0x6a, (byte) 0xec, 
            (byte) 0x1e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x1c, 
            (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x14, (byte) 0x06, (byte) 0x00, 
            (byte) 0x00, (byte) 0x1f, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x0c, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x02, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x06, 
            (byte) 0x00, (byte) 0x00, (byte) 0x20, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0xfc, (byte) 0x05, (byte) 0x00, (byte) 0x00, 
            (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf3, 
            (byte) 0x05, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0xe7, (byte) 0x05, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x7a, (byte) 0x00, (byte) 0x00, (byte) 0x10, (byte) 0x8d, 
            (byte) 0x00, (byte) 0x00, (byte) 0x10, (byte) 0xb6, (byte) 0xde, 
            (byte) 0x30, (byte) 0x10, (byte) 0xd8, (byte) 0xa5, (byte) 0x20, 
            (byte) 0x63, (byte) 0x45, (byte) 0x50, (byte) 0x4f, (byte) 0x43, 
            (byte) 0xb0, (byte) 0xbd, (byte) 0x94, (byte) 0xaf, (byte) 0x00, 
            (byte) 0x00, (byte) 0x0a, (byte) 0x00, (byte) 0xfc, (byte) 0x7a, 
            (byte) 0x1f, (byte) 0x10, (byte) 0x02, (byte) 0x01, (byte) 0x40, 
            (byte) 0x02, (byte) 0x80, (byte) 0x53, (byte) 0x6b, (byte) 0x4f, 
            (byte) 0x76, (byte) 0x37, (byte) 0xe1, (byte) 0x00, (byte) 0x2a, 
            (byte) 0x00, (byte) 0x00, (byte) 0x12, (byte) 0x40, (byte) 0x07, 
            (byte) 0x00, (byte) 0x00, (byte) 0x18, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x10, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x10, (byte) 0x00, (byte) 0x00, 
            (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x02, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x40, (byte) 0x07, (byte) 0x00, (byte) 0x00, 
            (byte) 0x9c, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xdc, 
            (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0xf4, (byte) 0x07, 
            (byte) 0x00, (byte) 0x00, (byte) 0xec, (byte) 0x08, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x5e, (byte) 0x01, (byte) 0x00, (byte) 0x20, (byte) 0x74, 
            (byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0xb6, (byte) 0xde, 
            (byte) 0x30, (byte) 0x10, (byte) 0x57, (byte) 0xb6, (byte) 0x1f, 
            (byte) 0x10, (byte) 0xfe, (byte) 0xff, (byte) 0x0f, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x5d, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0xde, (byte) 0xbb, (byte) 0x19, (byte) 0xe5, 
            (byte) 0x1f, (byte) 0x69, (byte) 0xec, (byte) 0xdf, (byte) 0x94, 
            (byte) 0xc4, (byte) 0xee, (byte) 0xd6, (byte) 0xb6, (byte) 0x75, 
            (byte) 0x68, (byte) 0x8e, (byte) 0x63, (byte) 0x75, (byte) 0x0c, 
            (byte) 0xdd, (byte) 0xe8, (byte) 0xd2, (byte) 0xe5, (byte) 0xc0, 
            (byte) 0xc7, (byte) 0x73, (byte) 0x4c, (byte) 0x2d, (byte) 0x33, 
            (byte) 0x03, (byte) 0x37, (byte) 0x5c, (byte) 0x32, (byte) 0x2d, 
            (byte) 0xb4, (byte) 0x66, (byte) 0x14, (byte) 0x77, (byte) 0x32, 
            (byte) 0xe6, (byte) 0xe5, (byte) 0x4d, (byte) 0xdd, (byte) 0xdd, 
            (byte) 0x5b, (byte) 0xb5, (byte) 0x1b, (byte) 0x5c, (byte) 0xd6, 
            (byte) 0xe5, (byte) 0x4d, (byte) 0xab, (byte) 0x68, (byte) 0x94, 
            (byte) 0x45, (byte) 0xad, (byte) 0xa5, (byte) 0x2d, (byte) 0x76, 
            (byte) 0xe3, (byte) 0xa5, (byte) 0x34, (byte) 0xc2, (byte) 0xe2, 
            (byte) 0x6d, (byte) 0xc5, (byte) 0xdb, (byte) 0xb9, (byte) 0x6a, 
            (byte) 0xe5, (byte) 0xcb, (byte) 0x98, (byte) 0xe5, (byte) 0x8d, 
            (byte) 0x8a, (byte) 0x5e, (byte) 0xb3, (byte) 0x31, (byte) 0x56, 
            (byte) 0xaa, (byte) 0xb5, (byte) 0xde, (byte) 0x2e, (byte) 0xad, 
            (byte) 0xbc, (byte) 0x79, (byte) 0xbc, (byte) 0xb7, (byte) 0x69, 
            (byte) 0x99, (byte) 0x4e, (byte) 0x02, (byte) 0x5b, (byte) 0xbe, 
            (byte) 0xfe, (byte) 0x15, (byte) 0xdd, (byte) 0x4e, (byte) 0x74, 
            (byte) 0x23, (byte) 0x20, (byte) 0xef, (byte) 0x79, (byte) 0xdb, 
            (byte) 0xe9, (byte) 0xbd, (byte) 0xf3, (byte) 0xc8, (byte) 0x8b, 
            (byte) 0xcb, (byte) 0xd3, (byte) 0x93, (byte) 0x33, (byte) 0x17, 
            (byte) 0xa1, (byte) 0xd5, (byte) 0xe3, (byte) 0xbb, (byte) 0xa8, 
            (byte) 0x11, (byte) 0x67, (byte) 0x64, (byte) 0x24, (byte) 0x5a, 
            (byte) 0xc9, (byte) 0x04, (byte) 0x45, (byte) 0x2c, (byte) 0x83, 
            (byte) 0x93, (byte) 0x28, (byte) 0xb6, (byte) 0xf3, (byte) 0x6b, 
            (byte) 0x45, (byte) 0xb7, (byte) 0xca, (byte) 0x24, (byte) 0x5c, 
            (byte) 0x24, (byte) 0xc4, (byte) 0x3a, (byte) 0x12, (byte) 0x29, 
            (byte) 0xe9, (byte) 0xb2, (byte) 0x8b, (byte) 0x1d, (byte) 0x67, 
            (byte) 0xf7, (byte) 0x52, (byte) 0x5e, (byte) 0x14, (byte) 0xe4, 
            (byte) 0x8a, (byte) 0x95, (byte) 0xae, (byte) 0xb3, (byte) 0x09, 
            (byte) 0x2d, (byte) 0x65, (byte) 0x97, (byte) 0xa9, (byte) 0x5b, 
            (byte) 0x7f, (byte) 0x98, (byte) 0x9b, (byte) 0x8d, (byte) 0x16, 
            (byte) 0x3a, (byte) 0x6b, (byte) 0xfd, (byte) 0xb2, (byte) 0x33, 
            (byte) 0xa4, (byte) 0x58, (byte) 0xeb, (byte) 0x2f, (byte) 0xeb, 
            (byte) 0x61, (byte) 0x26, (byte) 0xfe, (byte) 0xeb, (byte) 0xa6, 
            (byte) 0xf1, (byte) 0xff, (byte) 0x32, (byte) 0x42, (byte) 0x6c, 
            (byte) 0xe9, (byte) 0x63, (byte) 0xb4, (byte) 0xd5, (byte) 0xd4, 
            (byte) 0xa6, (byte) 0x7d, (byte) 0x52, (byte) 0x27, (byte) 0xfb, 
            (byte) 0xc2, (byte) 0x24, (byte) 0xa2, (byte) 0x36, (byte) 0x4c, 
            (byte) 0xee, (byte) 0xe8, (byte) 0xae, (byte) 0xe4, (byte) 0xd2, 
            (byte) 0x3f, (byte) 0xd1, (byte) 0xb0, (byte) 0x45, (byte) 0x52, 
            (byte) 0x6a, (byte) 0x9c, (byte) 0xc9, (byte) 0x09, (byte) 0xdd, 
            (byte) 0x0b, (byte) 0xfb, (byte) 0xb9, (byte) 0x34, (byte) 0x35, 
            (byte) 0x37, (byte) 0x72, (byte) 0x54, (byte) 0x2d, (byte) 0x64, 
            (byte) 0x7a, (byte) 0x48, (byte) 0x97, (byte) 0xee, (byte) 0x88, 
            (byte) 0xae, (byte) 0x24, (byte) 0xbe, (byte) 0x6b, (byte) 0x4b, 
            (byte) 0x1d, (byte) 0x37, (byte) 0xf6, (byte) 0x78, (byte) 0x46, 
            (byte) 0xd3, (byte) 0xc7, (byte) 0x69, (byte) 0xa9, (byte) 0xe1, 
            (byte) 0x0e, (byte) 0x51, (byte) 0xbd, (byte) 0xfe, (byte) 0xa8, 
            (byte) 0x99, (byte) 0x9a, (byte) 0x6e, (byte) 0x96, (byte) 0x07, 
            (byte) 0xb1, (byte) 0x85, (byte) 0x3e, (byte) 0x24, (byte) 0xd6, 
            (byte) 0xbe, (byte) 0x26, (byte) 0x6d, (byte) 0xa5, (byte) 0xad, 
            (byte) 0x7e, (byte) 0x63, (byte) 0x60, (byte) 0x0c, (byte) 0x3b, 
            (byte) 0xe2, (byte) 0xa1, (byte) 0xb6, (byte) 0x8c, (byte) 0x4c, 
            (byte) 0xd7, (byte) 0xcb, (byte) 0x26, (byte) 0x5e, (byte) 0xcb, 
            (byte) 0x54, (byte) 0x92, (byte) 0xa2, (byte) 0x48, (byte) 0xa6, 
            (byte) 0xef, (byte) 0x09, (byte) 0x9c, (byte) 0xe4, (byte) 0xb3, 
            (byte) 0x27, (byte) 0x96, (byte) 0x74, (byte) 0x89, (byte) 0x2c, 
            (byte) 0xa8, (byte) 0x26, (byte) 0xad, (byte) 0xe8, (byte) 0xc9, 
            (byte) 0xd8, (byte) 0x7e, (byte) 0x66, (byte) 0x59, (byte) 0x93, 
            (byte) 0xd1, (byte) 0x96, (byte) 0xee, (byte) 0xe6, (byte) 0x6b, 
            (byte) 0x83, (byte) 0xc7, (byte) 0xd5, (byte) 0xbf, (byte) 0x9e, 
            (byte) 0x7e, (byte) 0xb2, (byte) 0x4f, (byte) 0x5c, (byte) 0x74, 
            (byte) 0x5f, (byte) 0x13, (byte) 0xae, (byte) 0x07, (byte) 0x77, 
            (byte) 0x82, (byte) 0xee, (byte) 0xf6, (byte) 0x60, (byte) 0x47, 
            (byte) 0x9d, (byte) 0x0b, (byte) 0x4c, (byte) 0x67, (byte) 0xe5, 
            (byte) 0x95, (byte) 0xa1, (byte) 0xb7, (byte) 0x1e, (byte) 0x2c, 
            (byte) 0x07, (byte) 0x70, (byte) 0xad, (byte) 0x29, (byte) 0x75, 
            (byte) 0x3c, (byte) 0xf2, (byte) 0xa0, (byte) 0xfa, (byte) 0xa9, 
            (byte) 0xb8, (byte) 0x95, (byte) 0x13, (byte) 0x21, (byte) 0x9d, 
            (byte) 0xd5, (byte) 0x61, (byte) 0x30, (byte) 0x8f, (byte) 0x25, 
            (byte) 0xb8, (byte) 0xa2, (byte) 0xe1, (byte) 0xf1, (byte) 0x14, 
            (byte) 0x0a, (byte) 0x7a, (byte) 0xec, (byte) 0x24, (byte) 0x4f, 
            (byte) 0x06, (byte) 0x89, (byte) 0x1e, (byte) 0x77, (byte) 0xba, 
            (byte) 0xc7, (byte) 0x32, (byte) 0x58, (byte) 0xe3, (byte) 0x93, 
            (byte) 0xdc, (byte) 0xd2, (byte) 0xfc, (byte) 0x3e, (byte) 0x22, 
            (byte) 0x41, (byte) 0xfe, (byte) 0x9a, (byte) 0x35, (byte) 0xd9, 
            (byte) 0x63, (byte) 0x8a, (byte) 0x7c, (byte) 0xfd, (byte) 0x24, 
            (byte) 0x67, (byte) 0x94, (byte) 0xc1, (byte) 0x70, (byte) 0x1f, 
            (byte) 0x11, (byte) 0xaa, (byte) 0x2f, (byte) 0x4f, (byte) 0x35, 
            (byte) 0x27, (byte) 0x8b, (byte) 0x46, (byte) 0x87, (byte) 0x7e, 
            (byte) 0x6f, (byte) 0x77, (byte) 0x43, (byte) 0x2d, (byte) 0xdd, 
            (byte) 0xeb, (byte) 0x90, (byte) 0x7e, (byte) 0xf6, (byte) 0x0a, 
            (byte) 0x80, (byte) 0xc7, (byte) 0xf1, (byte) 0x60, (byte) 0xcc, 
            (byte) 0xb9, (byte) 0xd9, (byte) 0x71, (byte) 0x20, (byte) 0x29, 
            (byte) 0xa0, (byte) 0x77, (byte) 0x9b, (byte) 0x77, (byte) 0x0b, 
            (byte) 0xf8, (byte) 0xa6, (byte) 0xb4, (byte) 0xa5, (byte) 0xd3, 
            (byte) 0x73, (byte) 0xcc, (byte) 0xa7, (byte) 0x53, (byte) 0x8e, 
            (byte) 0xe6, (byte) 0xf4, (byte) 0xc6, (byte) 0x98, (byte) 0x53, 
            (byte) 0xdc, (byte) 0x2e, (byte) 0x6d, (byte) 0xf7, (byte) 0xae, 
            (byte) 0xc1, (byte) 0x68, (byte) 0x9d, (byte) 0x2f, (byte) 0x6c, 
            (byte) 0x7a, (byte) 0x4f, (byte) 0xc0, (byte) 0x29, (byte) 0x2e, 
            (byte) 0x78, (byte) 0x46, (byte) 0xa5, (byte) 0xf2, (byte) 0x18, 
            (byte) 0xf6, (byte) 0xfc, (byte) 0x37, (byte) 0x37, (byte) 0x3a, 
            (byte) 0xfa, (byte) 0x65, (byte) 0x6d, (byte) 0x89, (byte) 0x9c, 
            (byte) 0x16, (byte) 0x0d, (byte) 0xb1, (byte) 0xc5, (byte) 0x3a, 
            (byte) 0x7f, (byte) 0xf1, (byte) 0xb7, (byte) 0xe5, (byte) 0x9f, 
            (byte) 0x79, (byte) 0x9f, (byte) 0x3f, (byte) 0xcb, (byte) 0xfe, 
            (byte) 0x8c, (byte) 0x3b, (byte) 0xbd, (byte) 0x4c, (byte) 0x1e, 
            (byte) 0x00, (byte) 0xac, (byte) 0x98, (byte) 0xf0, (byte) 0x8f, 
            (byte) 0x59, (byte) 0xe8, (byte) 0xb1, (byte) 0x57, (byte) 0x01, 
            (byte) 0xd5, (byte) 0x19, (byte) 0xb9, (byte) 0x1d, (byte) 0x63, 
            (byte) 0xab, (byte) 0x57, (byte) 0x1d, (byte) 0x53, (byte) 0xd5, 
            (byte) 0x5d, (byte) 0x64, (byte) 0xd5, (byte) 0x7e, (byte) 0x66, 
            (byte) 0x0d, (byte) 0xc0, (byte) 0xed, (byte) 0x58, (byte) 0x56, 
            (byte) 0xe7, (byte) 0xde, (byte) 0xc3, (byte) 0xd7, (byte) 0x40, 
            (byte) 0xdd, (byte) 0xf8, (byte) 0x93, (byte) 0x47, (byte) 0x5b, 
            (byte) 0xff, (byte) 0x8e, (byte) 0xb7, (byte) 0xad, (byte) 0x8c, 
            (byte) 0xa1, (byte) 0xd6, (byte) 0xfe, (byte) 0xb8, (byte) 0xdd, 
            (byte) 0x95, (byte) 0xbe, (byte) 0xfb, (byte) 0x0a, (byte) 0x70, 
            (byte) 0x55, (byte) 0xbc, (byte) 0x56, (byte) 0xe0, (byte) 0x99, 
            (byte) 0xf2, (byte) 0x58, (byte) 0x89, (byte) 0x87, (byte) 0xe1, 
            (byte) 0x40, (byte) 0x5b, (byte) 0xb6, (byte) 0x5b, (byte) 0xd0, 
            (byte) 0x42, (byte) 0xde, (byte) 0x8e, (byte) 0x4a, (byte) 0xdd, 
            (byte) 0xf6, (byte) 0x4a, (byte) 0xdd, (byte) 0x53, (byte) 0x15, 
            (byte) 0xb8, (byte) 0x7d, (byte) 0xfc, (byte) 0x09, (byte) 0xc3, 
            (byte) 0xad, (byte) 0xd0, (byte) 0x1d, (byte) 0x6f, (byte) 0xd1, 
            (byte) 0x8c, (byte) 0xdb, (byte) 0x27, (byte) 0xe9, (byte) 0xc9, 
            (byte) 0x5b, (byte) 0x60, (byte) 0x7e, (byte) 0x2f, (byte) 0x6a, 
            (byte) 0x74, (byte) 0xe4, (byte) 0x63, (byte) 0x19, (byte) 0x2c, 
            (byte) 0xd8, (byte) 0x37, (byte) 0x56, (byte) 0xf6, (byte) 0xba, 
            (byte) 0xac, (byte) 0x99, (byte) 0xd9, (byte) 0x23, (byte) 0xe9, 
            (byte) 0x73, (byte) 0xcc, (byte) 0x2b, (byte) 0xc1, (byte) 0x4c, 
            (byte) 0xd6, (byte) 0x4e, (byte) 0xc7, (byte) 0x7d, (byte) 0x86, 
            (byte) 0x68, (byte) 0x7a, (byte) 0xe6, (byte) 0x35, (byte) 0x18, 
            (byte) 0x6c, (byte) 0xdf, (byte) 0x59, (byte) 0x46, (byte) 0x34, 
            (byte) 0x59, (byte) 0x82, (byte) 0x9d, (byte) 0x78, (byte) 0x6d, 
            (byte) 0x12, (byte) 0x66, (byte) 0x90, (byte) 0xfd, (byte) 0x5e, 
            (byte) 0xf9, (byte) 0x8a, (byte) 0x0a, (byte) 0x82, (byte) 0x67, 
            (byte) 0x1e, (byte) 0x51, (byte) 0x68, (byte) 0x0a, (byte) 0x88, 
            (byte) 0x99, (byte) 0x9e, (byte) 0x60, (byte) 0xad, (byte) 0x9a, 
            (byte) 0xc4, (byte) 0xc0, (byte) 0xc8, (byte) 0x54, (byte) 0x8a, 
            (byte) 0xe4, (byte) 0x99, (byte) 0xcc, (byte) 0xb1, (byte) 0xdc, 
            (byte) 0x60, (byte) 0x93, (byte) 0x35, (byte) 0x4c, (byte) 0x1a, 
            (byte) 0x1a, (byte) 0x84, (byte) 0xe7, (byte) 0x04, (byte) 0xdc, 
            (byte) 0x5c, (byte) 0x99, (byte) 0xef, (byte) 0x58, (byte) 0x39, 
            (byte) 0xd1, (byte) 0x39, (byte) 0x5d, (byte) 0xe4, (byte) 0x2d, 
            (byte) 0x4c, (byte) 0x27, (byte) 0x85, (byte) 0x69, (byte) 0x4e, 
            (byte) 0x5a, (byte) 0x57, (byte) 0x2b, (byte) 0xd2, (byte) 0x07, 
            (byte) 0xe9, (byte) 0x0c, (byte) 0xfe, (byte) 0x8d, (byte) 0x25, 
            (byte) 0xde, (byte) 0x96, (byte) 0x2d, (byte) 0xfb, (byte) 0xe0, 
            (byte) 0x26, (byte) 0x60, (byte) 0x6c, (byte) 0xde, (byte) 0x3a, 
            (byte) 0xaa, (byte) 0xf3, (byte) 0xde, (byte) 0x3b, (byte) 0xcd, 
            (byte) 0xba, (byte) 0xaa, (byte) 0x82, (byte) 0x74, (byte) 0xda, 
            (byte) 0x9d, (byte) 0x2c, (byte) 0x0e, (byte) 0x90, (byte) 0xdb, 
            (byte) 0x4a, (byte) 0x72, (byte) 0xde, (byte) 0x7b, (byte) 0x4e, 
            (byte) 0x45, (byte) 0xec, (byte) 0x97, (byte) 0x38, (byte) 0x5c, 
            (byte) 0x7f, (byte) 0x80, (byte) 0x5b, (byte) 0x92, (byte) 0xdb, 
            (byte) 0x0f, (byte) 0x5e, (byte) 0x80, (byte) 0xb4, (byte) 0x9d, 
            (byte) 0x99, (byte) 0x79, (byte) 0xc5, (byte) 0xb7, (byte) 0x2d, 
            (byte) 0xc0, (byte) 0x4a, (byte) 0xe2, (byte) 0x82, (byte) 0x6b, 
            (byte) 0xe2, (byte) 0x0e, (byte) 0xd5, (byte) 0x8c, (byte) 0xa6, 
            (byte) 0x8b, (byte) 0xb6, (byte) 0x2a, (byte) 0xc0, (byte) 0x6b, 
            (byte) 0x8a, (byte) 0xb4, (byte) 0x48, (byte) 0xc1, (byte) 0x1f, 
            (byte) 0xda, (byte) 0xb2, (byte) 0xaf, (byte) 0x2d, (byte) 0xa0, 
            (byte) 0x56, (byte) 0x69, (byte) 0x5f, (byte) 0x20, (byte) 0x65, 
            (byte) 0xad, (byte) 0x2b, (byte) 0x03, (byte) 0xc7, (byte) 0xda, 
            (byte) 0x08, (byte) 0xfa, (byte) 0x72, (byte) 0x7c, (byte) 0x22, 
            (byte) 0xe9, (byte) 0x0f, (byte) 0xeb, (byte) 0xfc, (byte) 0x75, 
            (byte) 0xe8, (byte) 0x8f, (byte) 0xbd, (byte) 0xf6, (byte) 0xf2, 
            (byte) 0x21, (byte) 0x9b, (byte) 0x4b, (byte) 0x95, (byte) 0x22, 
            (byte) 0x88, (byte) 0xd3, (byte) 0x5d, (byte) 0x9f, (byte) 0xf3, 
            (byte) 0xf0, (byte) 0xef, (byte) 0x94, (byte) 0x29, (byte) 0xa1, 
            (byte) 0xcd, (byte) 0xbd, (byte) 0x49, (byte) 0x44, (byte) 0xf5, 
            (byte) 0x3e, (byte) 0x7c, (byte) 0x58, (byte) 0x51, (byte) 0xe1, 
            (byte) 0x68, (byte) 0x46, (byte) 0x54, (byte) 0xe5, (byte) 0x0b, 
            (byte) 0x75, (byte) 0x95, (byte) 0x0e, (byte) 0xfc, (byte) 0x8b, 
            (byte) 0x10, (byte) 0x60, (byte) 0x2e, (byte) 0xf1, (byte) 0x7f, 
            (byte) 0x17, (byte) 0x09, (byte) 0xc5, (byte) 0x4c, (byte) 0x58, 
            (byte) 0xe5, (byte) 0x55, (byte) 0x16, (byte) 0x39, (byte) 0xb0, 
            (byte) 0xd6, (byte) 0x94, (byte) 0x38, (byte) 0xb1, (byte) 0xf6, 
            (byte) 0xa4, (byte) 0x5f, (byte) 0x99, (byte) 0x16, (byte) 0xb6, 
            (byte) 0x43, (byte) 0x81, (byte) 0x87, (byte) 0xff, (byte) 0x34, 
            (byte) 0x3f, (byte) 0x96, (byte) 0x03, (byte) 0xad, (byte) 0x90, 
            (byte) 0xd1, (byte) 0xf4, (byte) 0xc9, (byte) 0xfc, (byte) 0x53, 
            (byte) 0x43, (byte) 0x99, (byte) 0x98, (byte) 0x0a, (byte) 0xf4, 
            (byte) 0xe2, (byte) 0x86, (byte) 0x6c, (byte) 0x5d, (byte) 0xdc, 
            (byte) 0x34, (byte) 0xd7, (byte) 0xf2, (byte) 0x21, (byte) 0xc7, 
            (byte) 0x4c, (byte) 0x3f, (byte) 0x52, (byte) 0x15, (byte) 0xce, 
            (byte) 0x0c, (byte) 0x33, (byte) 0xfc, (byte) 0x8b, (byte) 0x45, 
            (byte) 0x91, (byte) 0xde, (byte) 0x07, (byte) 0xf5, (byte) 0x60, 
            (byte) 0x26, (byte) 0x15, (byte) 0xf2, (byte) 0x57, (byte) 0xdf, 
            (byte) 0xe1, (byte) 0x32, (byte) 0x30, (byte) 0x8f, (byte) 0xd2, 
            (byte) 0x1c, (byte) 0xdf, (byte) 0x9c, (byte) 0x36, (byte) 0xdd, 
            (byte) 0x9f, (byte) 0x64, (byte) 0x31, (byte) 0xee, (byte) 0x25, 
            (byte) 0x6a, (byte) 0x86, (byte) 0xbd, (byte) 0x39, (byte) 0xa4, 
            (byte) 0xd9, (byte) 0xce, (byte) 0xc9, (byte) 0xfd, (byte) 0x9c, 
            (byte) 0x8f, (byte) 0xc0, (byte) 0x60, (byte) 0xe2, (byte) 0xa0, 
            (byte) 0xec, (byte) 0x13, (byte) 0x6c, (byte) 0xec, (byte) 0x50, 
            (byte) 0x7c, (byte) 0xc4, (byte) 0x1b, (byte) 0x44, (byte) 0x1b, 
            (byte) 0x74, (byte) 0x17, (byte) 0xaa, (byte) 0x36, (byte) 0x68, 
            (byte) 0xd0, (byte) 0x7e, (byte) 0xb5, (byte) 0x5b, (byte) 0x3c, 
            (byte) 0x92, (byte) 0x6c, (byte) 0xe5, (byte) 0x26, (byte) 0x1b, 
            (byte) 0x3c, (byte) 0xc2, (byte) 0x46, (byte) 0xba, (byte) 0x0c, 
            (byte) 0xd3, (byte) 0xa6, (byte) 0xcc, (byte) 0xda, (byte) 0x0a, 
            (byte) 0xe8, (byte) 0x87, (byte) 0x9e, (byte) 0x46, (byte) 0x7f, 
            (byte) 0x89, (byte) 0x26, (byte) 0xcf, (byte) 0xc6, (byte) 0x83, 
            (byte) 0x4e, (byte) 0xe1, (byte) 0xb3, (byte) 0x05, (byte) 0x07, 
            (byte) 0x6e, (byte) 0x83, (byte) 0xea, (byte) 0x20, (byte) 0xef, 
            (byte) 0x50, (byte) 0x6d, (byte) 0x50, (byte) 0x7d, (byte) 0x74, 
            (byte) 0x1b, (byte) 0xa4, (byte) 0x18, (byte) 0xa8, (byte) 0x3c, 
            (byte) 0xb4, (byte) 0x71, (byte) 0xf9, (byte) 0x28, (byte) 0x3d, 
            (byte) 0x44, (byte) 0x1e, (byte) 0x1a, (byte) 0x2b, (byte) 0xf7, 
            (byte) 0x65, (byte) 0xfd, (byte) 0xfe, (byte) 0x7f, (byte) 0xe5, 
            (byte) 0x41, (byte) 0xe5, (byte) 0x20, (byte) 0xd4, (byte) 0xa3, 
            (byte) 0xe9, (byte) 0xec, (byte) 0x91, (byte) 0xbf, (byte) 0xc0, 
            (byte) 0x41, (byte) 0xe7, (byte) 0x10, (byte) 0xfd, (byte) 0x36, 
            (byte) 0x4b, (byte) 0x5f, (byte) 0x4d, (byte) 0x51, (byte) 0xb5, 
            (byte) 0x89, (byte) 0xf8, (byte) 0x75, (byte) 0x8d, (byte) 0xfa, 
            (byte) 0x5d, (byte) 0xac, (byte) 0x4d, (byte) 0x60, (byte) 0x29, 
            (byte) 0x14, (byte) 0xcc, (byte) 0x2d, (byte) 0xec, (byte) 0x24, 
            (byte) 0x9e, (byte) 0x5a, (byte) 0xd2, (byte) 0x92, (byte) 0xe4, 
            (byte) 0xf2, (byte) 0x1d, (byte) 0x3b, (byte) 0xaf, (byte) 0x75, 
            (byte) 0xc5, (byte) 0x91, (byte) 0x59, (byte) 0xa5, (byte) 0xf0, 
            (byte) 0xc6, (byte) 0x8d, (byte) 0x18, (byte) 0xf6, (byte) 0x6f, 
            (byte) 0x1b, (byte) 0x8e, (byte) 0x51, (byte) 0x33, (byte) 0x44, 
            (byte) 0xf4, (byte) 0xbe, (byte) 0xcf, (byte) 0xe9, (byte) 0xa6, 
            (byte) 0xbe, (byte) 0xe1, (byte) 0x9d, (byte) 0x0f, (byte) 0xd8, 
            (byte) 0xb4, (byte) 0x1c, (byte) 0xb0, (byte) 0xef, (byte) 0x1e, 
            (byte) 0xb7, (byte) 0x21, (byte) 0xab, (byte) 0x16, (byte) 0xe5, 
            (byte) 0xf4, (byte) 0x51, (byte) 0x28, (byte) 0x07, (byte) 0x7b, 
            (byte) 0x67, (byte) 0xf2, (byte) 0x1b, (byte) 0x97, (byte) 0x4c, 
            (byte) 0x76, (byte) 0x0d, (byte) 0xfa, (byte) 0x23, (byte) 0xa8, 
            (byte) 0x5d, (byte) 0xbd, (byte) 0xc3, (byte) 0x9a, (byte) 0x38, 
            (byte) 0xb2, (byte) 0xd2, (byte) 0xf1, (byte) 0xcb, (byte) 0xc1, 
            (byte) 0x2f, (byte) 0x18, (byte) 0xf4, (byte) 0x3f, (byte) 0x73, 
            (byte) 0xd3, (byte) 0x22, (byte) 0x5b, (byte) 0x19, (byte) 0xab, 
            (byte) 0x1b, (byte) 0x36, (byte) 0x9a, (byte) 0xb1, (byte) 0xdd, 
            (byte) 0x02, (byte) 0x90, (byte) 0xd3, (byte) 0xc0, (byte) 0x06, 
            (byte) 0x34, (byte) 0x6b, (byte) 0xc8, (byte) 0x9c, (byte) 0xd3, 
            (byte) 0x1e, (byte) 0x36, (byte) 0x56, (byte) 0x26, (byte) 0x58, 
            (byte) 0x54, (byte) 0xec, (byte) 0x06, (byte) 0xbd, (byte) 0x47, 
            (byte) 0x60, (byte) 0xd0, (byte) 0xf8, (byte) 0xd1, (byte) 0x2a, 
            (byte) 0x81, (byte) 0xdf, (byte) 0x0c, (byte) 0xeb, (byte) 0xf6, 
            (byte) 0x1b, (byte) 0x5f, (byte) 0x42, (byte) 0xd3, (byte) 0x6b, 
            (byte) 0xdf, (byte) 0x34, (byte) 0xde, (byte) 0x7f, (byte) 0xf5, 
            (byte) 0x77, (byte) 0xee, (byte) 0x69, (byte) 0xd8, (byte) 0x19, 
            (byte) 0xf2, (byte) 0x15, (byte) 0x30, (byte) 0xd8, (byte) 0x9d, 
            (byte) 0x01, (byte) 0xe0, (byte) 0x06, (byte) 0xb2, (byte) 0x77, 
            (byte) 0xc0, (byte) 0x52, (byte) 0x28, (byte) 0x2e, (byte) 0xac, 
            (byte) 0x0a, (byte) 0x85, (byte) 0x07, (byte) 0x81, (byte) 0x20, 
            (byte) 0x6b, (byte) 0xc0, (byte) 0xaa, (byte) 0x02, (byte) 0x10, 
            (byte) 0x10, (byte) 0xc0, (byte) 0xdd, (byte) 0x28, (byte) 0x2f, 
            (byte) 0x18, (byte) 0x0b, (byte) 0x40, (byte) 0x2d, (byte) 0x94, 
            (byte) 0x18, (byte) 0x54, (byte) 0x50, (byte) 0x78, (byte) 0x52, 
            (byte) 0x05, (byte) 0xe2, (byte) 0x83, (byte) 0x90, (byte) 0xc0, 
            (byte) 0xe5, (byte) 0x28, (byte) 0x27, (byte) 0xe0, (byte) 0x73, 
            (byte) 0x40, (byte) 0x5c, (byte) 0x0e, (byte) 0x80, (byte) 0x18, 
            (byte) 0x20, (byte) 0x4a, (byte) 0x50, (byte) 0x5e, (byte) 0xf0, 
            (byte) 0x18, (byte) 0xa6, (byte) 0xe4, (byte) 0xc1, (byte) 0xd8, 
            (byte) 0x37, (byte) 0x4a, (byte) 0x8a, (byte) 0x5a, (byte) 0xa2, 
            (byte) 0x96, (byte) 0xa8, (byte) 0xa5, (byte) 0x81, (byte) 0xf9, 
            (byte) 0x10, (byte) 0xd3, (byte) 0x33, (byte) 0x4d, (byte) 0x50, 
            (byte) 0xd3, (byte) 0x54, (byte) 0x34, (byte) 0xd5, (byte) 0x0d, 
            (byte) 0x35, (byte) 0x43, (byte) 0x4d, (byte) 0x50, (byte) 0xc7, 
            (byte) 0x54, (byte) 0x78, (byte) 0xf2, (byte) 0x07, (byte) 0xce, 
            (byte) 0x04, (byte) 0x1d, (byte) 0xce, (byte) 0xe2, (byte) 0x0d, 
            (byte) 0x7e, (byte) 0xed, (byte) 0xf3, (byte) 0xe7, (byte) 0xd5, 
            (byte) 0x6f, (byte) 0x83, (byte) 0xbd, (byte) 0x2d, (byte) 0x85, 
            (byte) 0x18, (byte) 0x4e, (byte) 0xe7, (byte) 0x51, (byte) 0x07, 
            (byte) 0x4d, (byte) 0xe5, (byte) 0x69, (byte) 0x2b, (byte) 0x2b, 
            (byte) 0xab, (byte) 0x8a, (byte) 0xb2, (byte) 0xbe, (byte) 0xc2, 
            (byte) 0xae, (byte) 0x0d, (byte) 0x5e, (byte) 0xca, (byte) 0xb7, 
            (byte) 0xb5, (byte) 0x20, (byte) 0x78, (byte) 0x41, (byte) 0xd3, 
            (byte) 0xed, (byte) 0xf9, (byte) 0xa8, (byte) 0x5d, (byte) 0x97, 
            (byte) 0xf6, (byte) 0x2b, (byte) 0x55, (byte) 0x2d, (byte) 0x58, 
            (byte) 0xb4, (byte) 0x25, (byte) 0xa3, (byte) 0x2d, (byte) 0xc9, 
            (byte) 0x5b, (byte) 0x03, (byte) 0x3f, (byte) 0xcc, (byte) 0xcb, 
            (byte) 0xf5, (byte) 0xe7, (byte) 0x3a, (byte) 0xb9, (byte) 0x9f, 
            (byte) 0xfb, (byte) 0x00 };
    
    // Data for AMR audio file used in audio message case
    // AMR format specification: http://www.ietf.org/rfc/rfc3267.txt
    public static final byte[] HTI_AMR_DATA = new byte[] {
            (byte) 0x23, (byte) 0x21, (byte) 0x41, (byte) 0x4d, (byte) 0x52, 
            (byte) 0x0a, (byte) 0x0c, (byte) 0x1f, (byte) 0xb9, (byte) 0x67, 
            (byte) 0xf7, (byte) 0xf1, (byte) 0xfd, (byte) 0xf5, (byte) 0x47, 
            (byte) 0xbf, (byte) 0x2e, (byte) 0x61, (byte) 0xc0, (byte) 0x60, 
            (byte) 0x0c, (byte) 0x1f, (byte) 0xb9, (byte) 0x67, (byte) 0xf7, 
            (byte) 0xf1, (byte) 0xfd, (byte) 0xf5, (byte) 0x47, (byte) 0xbf, 
            (byte) 0x2e, (byte) 0x61, (byte) 0xc0, (byte) 0x60, (byte) 0x0c, 
            (byte) 0x85, (byte) 0x8e, (byte) 0xc7, (byte) 0xff, (byte) 0xf0, 
            (byte) 0xf7, (byte) 0xb1, (byte) 0xff, (byte) 0xff, (byte) 0x3e, 
            (byte) 0x20, (byte) 0xc4, (byte) 0x66, (byte) 0x0c, (byte) 0x40, 
            (byte) 0x42, (byte) 0xcf, (byte) 0xc2, (byte) 0x6b, (byte) 0xdd, 
            (byte) 0x35, (byte) 0x7d, (byte) 0xa7, (byte) 0x06, (byte) 0xa6, 
            (byte) 0xda, (byte) 0x8c, (byte) 0x0c, (byte) 0x06, (byte) 0x08, 
            (byte) 0x1d, (byte) 0x6f, (byte) 0x7e, (byte) 0x22, (byte) 0x0f, 
            (byte) 0x4e, (byte) 0xa1, (byte) 0x7e, (byte) 0x64, (byte) 0x11, 
            (byte) 0x18, (byte) 0x0c, (byte) 0x1f, (byte) 0xae, (byte) 0xbb, 
            (byte) 0x04, (byte) 0x71, (byte) 0x29, (byte) 0x7a, (byte) 0xba, 
            (byte) 0x15, (byte) 0x39, (byte) 0x15, (byte) 0x79, (byte) 0x30, 
            (byte) 0x0c, (byte) 0xcb, (byte) 0x39, (byte) 0x99, (byte) 0xdb, 
            (byte) 0x75, (byte) 0x83, (byte) 0xde, (byte) 0xe5, (byte) 0xa1, 
            (byte) 0x27, (byte) 0x00, (byte) 0x41, (byte) 0x40, (byte) 0x0c, 
            (byte) 0x1f, (byte) 0x8c, (byte) 0x3f, (byte) 0xfd, (byte) 0xef, 
            (byte) 0xf8, (byte) 0xd4, (byte) 0x67, (byte) 0xbf, (byte) 0x15, 
            (byte) 0x9d, (byte) 0x07, (byte) 0x56, (byte) 0x0c, (byte) 0x08, 
            (byte) 0xe8, (byte) 0xb6, (byte) 0xaf, (byte) 0x77, (byte) 0x66, 
            (byte) 0xbe, (byte) 0x42, (byte) 0x8b, (byte) 0xa6, (byte) 0x87, 
            (byte) 0x69, (byte) 0x96, (byte) 0x0c, (byte) 0xc4, (byte) 0x18, 
            (byte) 0xc2, (byte) 0xf3, (byte) 0xf2, (byte) 0x30, (byte) 0x99, 
            (byte) 0xf8, (byte) 0x4a, (byte) 0xcf, (byte) 0x0a, (byte) 0x8d, 
            (byte) 0xc2, (byte) 0x0c, (byte) 0x00, (byte) 0x8a, (byte) 0xda, 
            (byte) 0x72, (byte) 0x4f, (byte) 0x14, (byte) 0xbf, (byte) 0xe5, 
            (byte) 0xa5, (byte) 0xa7, (byte) 0x34, (byte) 0x0b, (byte) 0x0e, 
            (byte) 0x0c, (byte) 0x07, (byte) 0x7f, (byte) 0x6c, (byte) 0xdf, 
            (byte) 0x7f, (byte) 0xc0, (byte) 0x5f, (byte) 0x64, (byte) 0x94, 
            (byte) 0x94, (byte) 0x32, (byte) 0x02, (byte) 0x16, (byte) 0x0c, 
            (byte) 0xb4, (byte) 0x16, (byte) 0x75, (byte) 0xbf, (byte) 0xe7, 
            (byte) 0x50, (byte) 0x58, (byte) 0xf9, (byte) 0x7f, (byte) 0x0f, 
            (byte) 0x4e, (byte) 0x81, (byte) 0x42, (byte) 0x0c, (byte) 0x44, 
            (byte) 0x36, (byte) 0xb2, (byte) 0xbb, (byte) 0x77, (byte) 0x58, 
            (byte) 0x34, (byte) 0x69, (byte) 0x8b, (byte) 0x0e, (byte) 0xff, 
            (byte) 0xeb, (byte) 0x76, (byte) 0x0c, (byte) 0xdc, (byte) 0x36, 
            (byte) 0x73, (byte) 0xfb, (byte) 0x5f, (byte) 0x50, (byte) 0xbf, 
            (byte) 0xc1, (byte) 0x86, (byte) 0x0a, (byte) 0x0b, (byte) 0x68, 
            (byte) 0x80, (byte) 0x0c, (byte) 0x08, (byte) 0x8a, (byte) 0x06, 
            (byte) 0x7f, (byte) 0x7f, (byte) 0x4d, (byte) 0xea, (byte) 0xf8, 
            (byte) 0x14, (byte) 0x3e, (byte) 0x57, (byte) 0x0f, (byte) 0x78 };
    
// ==============================================================================
// Public methods

// ==============================================================================
// Protected methods

// ==============================================================================
// Private methods

// ==============================================================================
// Protected attributes

// ==============================================================================
// Private attributes

// ==============================================================================
// Static initialization block

//==============================================================================
//Inner classes
}
