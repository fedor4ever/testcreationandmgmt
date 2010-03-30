#include "child.h"
#include <e32std.h>

CChild::CChild()
	{
	//nothing
	}

CChild::~CChild()
	{
	//nothing
	}

void CChild::Add()
	{
	User::InfoPrint(_L("Child.Add"));
	}

void CChild::Del()
	{
	User::InfoPrint(_L("Child.Del"));
	}