/*
 * class2.h
 *
 *  Created on: 2009-10-23
 *      Author: y183zhan
 */

#ifndef CLASS2_H_
#define CLASS2_H_

#include <e32def.h> 

class CClass2
    {
public:
    CClass2();
    ~CClass2();
public:
    TInt PubFoo1(TInt a, TInt b);
    TInt PubFoo2();
    static TInt staFoo4();
private:
    void Foo3();
    
    };

#endif /* CLASS2_H_ */
