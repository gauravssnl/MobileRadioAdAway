package com.gauravssnl.mobileradioadaway;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Xposed implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpParam) {
        hookMobileRadioClassMethods(lpParam);
    }

    private void hookMobileRadioClassMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String packageName = "com.vb.mirchiplay";
        XposedBridge.log("Trying to find package :: " + packageName + " and hook class methods");
        if (!lpParam.packageName.equals(packageName)) {
            XposedBridge.log("Unable to find the package :: " + packageName + " to hook");
            return;
        }
        hookAppOpenManagerMethods(lpParam);
        hookMainActivityMethods(lpParam);
        XposedBridge.log("All hooking completed for package :: " + packageName);
    }

    private void hookAppOpenManagerMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.vb.mirchiplay.AppOpenManager";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedHelpers.findAndHookMethod(clazz, "getAdRequest", XC_MethodReplacement.DO_NOTHING);
        XposedHelpers.findAndHookMethod(clazz, "fetchAd", XC_MethodReplacement.DO_NOTHING);
        XposedHelpers.findAndHookMethod(clazz, "isAdAvailable", XC_MethodReplacement.returnConstant(false));
        XposedHelpers.findAndHookMethod(clazz, "showAdIfAvailable", XC_MethodReplacement.DO_NOTHING);
        XposedHelpers.findAndHookMethod(clazz, "getAdRequest", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);
    }

    private void hookMainActivityMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.vb.mirchiplay.MainActivity";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "showads", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "loadad", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);
    }

}
