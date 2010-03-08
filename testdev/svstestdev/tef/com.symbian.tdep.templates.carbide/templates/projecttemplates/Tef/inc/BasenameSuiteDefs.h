/**
 * @file $(baseName)SuitDefs.h
 * @internalTechnology
 *
 * $(copyright)
 * This file define all the common values thoughout your test project
 */
#if (!defined __$(baseNameUpper)_SUIT_DEFS_H__)
#define __$(baseNameUpper)_SUIT_DEFS_H__

// Please modify below value with your project and must match with your configuration ini file which is required to be modified as well
_LIT(K$(baseNameUpper)String,"TheString");
_LIT(K$(baseNameUpper)Int,"TheInt");
_LIT(K$(baseNameUpper)Bool,"TheBool");

// For test step panics
_LIT(K$(baseNameUpper)Panic,"$(baseName)Suite");

#endif
