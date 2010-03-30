/*
 * class1.h
 *
 *  Created on: 2009-10-23
 *      Author: y183zhan
 */

#ifndef CLASS1_H_
#define CLASS1_H_

#include <e32def.h> 

class CClass1
    {
public:
    CClass1();
    ~CClass1();
public:
    TInt PubFoo1(TInt a);
protected:
    TInt protFoo2();
private:
    void Foo3();
    
    };

#endif /* CLASS1_H_ */
