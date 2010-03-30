/*
 * class1.cpp
 *
 *  Created on: 2009-10-23
 *      Author: y183zhan
 */

#include "class1.h"

CClass1::CClass1()
    {
    //nothing
    }

CClass1::~CClass1()
    {
    //nothing
    }

TInt CClass1::PubFoo1(TInt a)
    {
    return a++;
    }


TInt CClass1::protFoo2()
    {
    return 0;
    }

void CClass1::Foo3()
    {
    //nothing
    }
