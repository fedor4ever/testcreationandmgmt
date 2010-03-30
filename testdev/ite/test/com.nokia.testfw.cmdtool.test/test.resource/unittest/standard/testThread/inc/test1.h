#ifndef TEST1_H_
#define TEST1_H_

#include <e32base.h>

class CTest1
{

public:
	CTest1();
	~CTest1();

public:
	static TInt threadFunc(TAny*);
	void Start();

};

#endif /*TEST1_H_*/
