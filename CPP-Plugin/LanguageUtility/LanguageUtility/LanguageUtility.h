#pragma once
#include <windows.h>

#ifndef LANGUAGE_UTILITY_H
#define LANGUAGE_UTILITY_H

#ifdef __cplusplus
extern "C" {
#endif

#define DllExport  __declspec(dllexport)

DllExport bool isEnglish(HWND h_wnd);

#ifdef __cplusplus
}
#endif

#endif // LANGUAGE_UTILITY_H