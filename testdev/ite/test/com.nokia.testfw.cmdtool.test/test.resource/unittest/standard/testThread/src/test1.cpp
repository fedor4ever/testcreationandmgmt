#include "test1.h"
#include <e32base.h>
#include <e32std.h>
#include <e32cons.h>			// Console

CTest1::CTest1()
	{
	//nothing
	}

CTest1::~CTest1()
	{
	
	}

TInt CTest1::threadFunc(TAny*)
	{
	for(TInt i=0;i<10;++i)
		{
		User::InfoPrint(_L("Thread..."));
		User::After(4000000);
		}
	return 0;
	}

void CTest1::Start()
	{
	RThread thd;
	User::LeaveIfError(thd.Create(_L("MyThread"), threadFunc , KDefaultStackSize,NULL, NULL));
	thd.Resume();
	}

