/**
 * @file $(baseNameLower)defs.h
 * @internalTechnology
 *
 * $(copyright)
 *
 * This file define all the common values thoughout your test project
 */
#if (!defined __$(baseNameUpper)DEFS_H__)
#define __$(baseNameUpper)DEFS_H__

//TODO:modify below value with your project and must match with your configuration ini file which is required to be modified as well
_LIT(K$(baseNameCapital)String,"TheString");
_LIT(K$(baseNameCapital)Int,"TheInt");
_LIT(K$(baseNameCapital)Bool,"TheBool");

// For test step panics
_LIT(K$(baseNameCapital)Panic,"$(baseNameCapital)Suite");

#endif
