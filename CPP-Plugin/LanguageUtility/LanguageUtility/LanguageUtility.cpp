#include "LanguageUtility.h"

/**
 * @brief Check if the input window's IME (Input Method Editor) is set to English mode.
 *
 * This function checks the conversion status of the input window's IME and
 * returns true if the IME is set to English mode (IME_CMODE_ALPHANUMERIC),
 * otherwise returns false.
 *
 * @param h_wnd The handle to the input window.
 * @return Returns true if the IME is set to English mode, otherwise false.
 */
bool isEnglish(const HWND h_wnd)
{
    const HIMC h_imc = ImmGetContext(h_wnd);
    DWORD conversion;
    DWORD sentence;
    
    if (ImmGetConversionStatus(h_imc, &conversion, &sentence))
    {
        if (conversion == IME_CMODE_ALPHANUMERIC)
            return true;
    }
    
    return false;
}