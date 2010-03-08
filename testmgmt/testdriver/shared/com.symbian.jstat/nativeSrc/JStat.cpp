/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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
* Description: 
*
*/


//Source file that creates the interface between the C++		*
//STAT component and Java.	

#include <iostream> 
#include <String>

#include <windows.h>

#include "com_symbian_jstat_JStat.h"
#include "Stat.h"
#include "StatExp.h"

static const int MAX_PORT_STRING=6;
char ComPort[MAX_PORT_STRING]="";

HMODULE	ihLib =	NULL;
PROC_CONNECT	iptrConnect =	NULL;
PROC_DISCONNECT	iptrDisconnect =	NULL;
PROC_SENDRAWCOMMAND	iptrSendRawCommand =	NULL;
PROC_SETCOMMANDLOGGING	iptrSetCommandLogging =	NULL;
PROC_GETERROR	iptrGetError =	NULL;
PROC_VERSION iptrVersion = NULL;

PROC_GETRECEIVEDDATA iptrGetReceivedData = NULL;

int iType;


void jstring2string( JNIEnv *env, const jstring src, std::string &dest )
{
	const char	*ptr =	env->GetStringUTFChars( src, false );
	dest =	ptr;
	env->ReleaseStringUTFChars( src, ptr );
}

void string2jstring( JNIEnv *env, const char* src, jstring &dest )
{
	dest =	env->NewStringUTF( src );
}


JNIEXPORT jint JNICALL Java_com_symbian_jstat_JStatRunCommand_connect
  (JNIEnv *_env, jobject, jstring _pszPlatformType)
{
	try {
		using std::cout;
		using std::endl;

		int connection=-1;
		std::string aIPAddress;
		std::string	pszPlatformType;

		jstring2string( _env, _pszPlatformType, pszPlatformType );
		
		//***********************************************************************
		//	USB
		//***********************************************************************
		if(strncmp(pszPlatformType.c_str(),"usb",3) == 0)
			{
			strcat(ComPort,pszPlatformType.c_str( )+3);
			iType=SymbianUsb;
			}
	
	
		//***********************************************************************
		//	TCP/IP
		//***********************************************************************
		else if(strncmp(pszPlatformType.c_str(),"tcp",3) == 0)
			{
			aIPAddress = pszPlatformType.substr(4);
			pszPlatformType = pszPlatformType.substr(0,3);
			iType=SymbianSocket;
			}


		//***********************************************************************
		//	Infrared
		//***********************************************************************
		else if(strncmp(pszPlatformType.c_str( ),"ir",2) == 0)
			{
			strcpy(ComPort,"COM");				
			strcat(ComPort,pszPlatformType.c_str( )+2);
			}

		//***********************************************************************
		//	Serial
		//***********************************************************************
		else if(strncmp(pszPlatformType.c_str( ),"serial",6) == 0)
			{
			strcpy(ComPort,"COM");
			strcat(ComPort,pszPlatformType.c_str( )+6);
			iType=SymbianSerial;
			}

		//***********************************************************************
		//	Bluetooth
		//***********************************************************************
		else if(strncmp(pszPlatformType.c_str( ),"bt",2) == 0)
			{
			strcpy(ComPort,"COM");
			strcat(ComPort,pszPlatformType.c_str( )+2);
			iType=SymbianBluetooth;
			}

		// load stat library first from local, if failed from epoc32

		HMODULE	ihLib =	::LoadLibrary( DLLName );

		if( ihLib  == NULL )
			{
			char path[MAX_PATH]=".\\stat.dll";
			sprintf( path, "%s\\%s", DLLFolder, DLLName );
			ihLib =	::LoadLibrary( path );
			}

		//if fails loading stat.dll return -1	
		if( ihLib  == NULL )
			{
			cout << "Could not connect JStat.dll to STAT.dll." << endl;
			return -1;
			}
		
		// load functions
		iptrConnect =	reinterpret_cast<PROC_CONNECT>(::GetProcAddress( ihLib, ProcConnect ));
		iptrDisconnect =	reinterpret_cast<PROC_DISCONNECT>(::GetProcAddress( ihLib, ProcDisconnect ));
		iptrSendRawCommand =	reinterpret_cast<PROC_SENDRAWCOMMAND>(::GetProcAddress( ihLib, ProcSendRawCommand ));
		iptrSetCommandLogging =	reinterpret_cast<PROC_SETCOMMANDLOGGING>(::GetProcAddress( ihLib, ProcSetCommandLogging ));
		iptrGetError =	reinterpret_cast<PROC_GETERROR>(::GetProcAddress( ihLib, ProcGetError ));
		iptrGetReceivedData =	reinterpret_cast<PROC_GETRECEIVEDDATA>(::GetProcAddress( ihLib, ProcGetReceivedData ));
		
		//check all fuunctions were correctly loaded
		if (iptrConnect == NULL)
			{
			cout << "Could not load JStat Function CONNECT." << endl;
			return -1;	  
			}
		if (iptrDisconnect == NULL)
			{
			cout << "Could not load JStat Function DISCONNECT." << endl;
			return -1;	  
			}
		if (iptrSendRawCommand == NULL)
			{
			cout << "Could not load JStat Function SENDRAWCOMMAND." << endl;
			return -1;	  
			}
		if (iptrSetCommandLogging == NULL)
			{
			cout << "Could not load JStat Function SETCOMMANDLOGGING." << endl;
			return -1;	  
			}
		if (iptrGetError == NULL)
			{
			cout << "Could not load JStat Function GETERROR." << endl;
			return -1;	  
			}
		if (iptrGetReceivedData == NULL)
		   {
			cout << "Could not load JStat Function GETRECIEVEDDATA." << endl;
			return -1;	   
		   }
		
		
		//connect using the previously set type
		if(iType==SymbianSocket)
			{
			connection = (iptrConnect)((STATConnectType)iType,aIPAddress.c_str(),NULL,NULL);
			}
		else
			{
			connection = iptrConnect((STATConnectType)iType,ComPort,NULL,NULL);
			}

		
		if(!connection)
			{	
			cout << "Could not connect JStat with transport." << endl;
			return -1;
			}
		
		
		// Use default file name for logging (named according to the current date).
		// Do not append.
		char	*fileName =	NULL;
		bool	append =	true;
		(iptrSetCommandLogging)(connection,fileName,NULL,EVerbose,append,NULL,NULL);
		
		return connection;
	} catch (...) {
		return -1;
	}

}

JNIEXPORT jint JNICALL Java_com_symbian_jstat_JStatRunCommand_disconnect
  (JNIEnv *_env, jobject, jint _handle)
{
	//disconnect from STAT
	try {
		int ret;
		
		ret=(iptrDisconnect)(_handle);
		
		if( ihLib != NULL )
			{
			::FreeLibrary( ihLib );
			ihLib =	NULL;
			iptrConnect =	NULL;
			iptrDisconnect =	NULL;
			iptrSendRawCommand =	NULL;
			iptrGetError =	NULL;
			}

		return ret;
	} catch (...) {
		return -1;
	}

}

JNIEXPORT jint JNICALL Java_com_symbian_jstat_JStatRunCommand_doCommand
  (JNIEnv *_env, jobject, jint _handle, jstring _command )
{
	//send a raw command to stat and returns the returned value
	try {
		int ret;
		
		std::string	command;
		jstring2string( _env, _command, command );
		ret = iptrSendRawCommand(_handle,command.c_str( ),NULL);

		return (ret);
	} catch (...) {
		return -1;
	}
}


JNIEXPORT jstring JNICALL Java_com_symbian_jstat_JStatRunCommand_getError
  (JNIEnv *_env, jobject, jint _handle)
{
	//return the error definition from stat
	jstring error;

	string2jstring( _env, iptrGetError(_handle), error);

	return error;
}


JNIEXPORT jstring JNICALL Java_com_symbian_jstat_JStatRunCommand_getReceivedData
  (JNIEnv *_env, jobject, jint _handle)
{
	//return the exposed buffer from received data

	return (_env->NewStringUTF(iptrGetReceivedData(_handle)));
}

JNIEXPORT jstring JNICALL Java_com_symbian_jstat_JStat_getStatVersion
(JNIEnv *_env, jobject)
{
	// load stat library first from local, if failed from epoc32
	HMODULE	ihLib =	::LoadLibrary( DLLName );

	if( ihLib  == NULL )
		{
		char path[MAX_PATH]=".\\stat.dll";
		sprintf( path, "%s\\%s", DLLFolder, DLLName );
		ihLib =	::LoadLibrary( path );
		}

	//if fails loading stat.dll return -1	
	if( ihLib  == NULL )
		{
		return _env->NewStringUTF("Error loading stat.dll");
		}
	
	// load version function
	iptrVersion =	reinterpret_cast<PROC_VERSION>(::GetProcAddress( ihLib, ProcVersion ));

	//check version function was correctly loaded
	if(iptrVersion ==NULL )
	   {
	   return _env->NewStringUTF("Error loading Version function");	   
	   }
	
	return _env->NewStringUTF(iptrVersion());
}

