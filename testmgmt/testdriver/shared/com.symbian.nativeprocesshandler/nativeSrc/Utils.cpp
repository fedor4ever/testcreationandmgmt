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


#include "Utils.h"
#include <windows.h>
#include <stdio.h>
#include <tchar.h>
#include "psapi.h"
#include <string.h>

#define MAX_PROCESSES 1024


/*
 * Class:     Utils
 * Method:    getPidList
 * Signature: (Ljava/lang/String;)[I
 */
JNIEXPORT jintArray JNICALL Java_com_symbian_nativeprocesshandler_ProcessHandler_getPidList(JNIEnv *env, jobject obj, jstring executable)
{
	
	DWORD aProcesses[MAX_PROCESSES], cbNeeded, cProcesses, foundProcesses[MAX_PROCESSES];
    int index=0;
	
	const char *nativeExecutable = env->JNIEnv_::GetStringUTFChars(executable,0);
	
    if ( !EnumProcesses( aProcesses, sizeof(aProcesses), &cbNeeded ) )
		{
		return NULL;
		}	

    // Calculate how many process identifiers were returned.
    cProcesses = cbNeeded / sizeof(DWORD);


	TCHAR szProcessName[MAX_PATH] = TEXT("<unknown>");
	

	//loop until you look at all running processes
	for(DWORD j=0;j<cProcesses; j++)
		{

		// Get a handle to the process.
		HANDLE hProcess = OpenProcess( PROCESS_QUERY_INFORMATION | PROCESS_VM_READ, FALSE, aProcesses[j] );

		// Get the process name.
		if (NULL != hProcess )
			{
			HMODULE hMod;
			DWORD cbNeeded;

			if ( EnumProcessModules( hProcess, &hMod, sizeof(hMod), &cbNeeded) )
				{
				GetModuleBaseName( hProcess, hMod, szProcessName, sizeof(szProcessName)/sizeof(TCHAR) );
				}
			}

		CloseHandle( hProcess );
		
		// comapares given name with related 2 pid
		if(strcmp(szProcessName,nativeExecutable) == 0 && index < MAX_PROCESSES)
			{
			foundProcesses[index]=aProcesses[j];
			index++;
			}
	
		}

	// protect overflow
	if(index >= MAX_PROCESSES)
		{
		return NULL;
		}


	//creates a jintArray with the needed length 2 store all found pids
	jintArray customFoundProcesses=env->JNIEnv_::NewIntArray(index);

	//copies WORD array to jintArray
	env->JNIEnv_::SetIntArrayRegion(customFoundProcesses,0,index,(const long *)foundProcesses);
	
	//releases used String
	env->JNIEnv_::ReleaseStringUTFChars(executable, nativeExecutable);

	return customFoundProcesses;


}

/*
 * Class:     Utils
 * Method:    kill
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_symbian_nativeprocesshandler_ProcessHandler_kill(JNIEnv *env, jobject obj, jint pid)
{

	HANDLE process;
	//DWORD PID;
	
	//if ( 2 != argc ) {
	//	fprintf(stderr, "\nUsage: kill <PID>");
	//	return 1;
	//}
	
	//PID = strtoul(pid, NULL, 0);	// base 0 allows 0x prefix for hex input...

	process = OpenProcess(PROCESS_TERMINATE, 0, pid);
	TerminateProcess(process, (unsigned)-1);
	
}
