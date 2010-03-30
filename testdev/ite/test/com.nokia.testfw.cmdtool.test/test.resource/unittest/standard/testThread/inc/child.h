#ifndef CHILD_H_
#define CHILD_H_

#include "parent.h"

class CChild : public MParent2, public MParent
{
public:
	CChild();
	~CChild();
	void Add();
	void Del();
};
#endif /*CHILD_H_*/
