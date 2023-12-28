package com.gauravssnl.mobileradioadaway;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Xposed implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpParam) {
        XposedBridge.log("Trying to hook for blocking Ads");
        hookMobileRadioClassMethods(lpParam);
        // Below methods can work for many apps, so adding them here
        hookFacebookAdViewMethods(lpParam);
        hookFacebookInterstitialAdMethods(lpParam);
        hookFacebookNativeAdAdMethods(lpParam);
        hookGoogleAdLoaderMethods(lpParam);
        hookGoogleMobileAdsMethods(lpParam);
        hookGoogleAdViewMethods(lpParam);
        hookGoogleFacebookAdapterMethods(lpParam);
        hookGoogleAdManagerAdViewMethods(lpParam);
        hookGoogleAdCustomEventAdapterMethods(lpParam);
        hookGoogleNativeAdViewHolderMethods(lpParam);
        XposedBridge.log("All hooks for blocking Ads successful");
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

    private void hookGoogleAdViewMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.google.android.gms.ads.AdView";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");

        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "loadAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "isLoading", XC_MethodReplacement.returnConstant(false));
        XposedBridge.log("All method hooking done for class :: " + className);

    }

    private void hookGoogleAdLoaderMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.google.android.gms.ads.AdLoader";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "loadAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "loadAds", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "isLoading", XC_MethodReplacement.returnConstant(false));
        XposedBridge.log("All method hooking done for class :: " + className);
    }

    private void hookFacebookAdViewMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.facebook.ads.AdView";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");

        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "loadAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);

    }

    private void hookFacebookInterstitialAdMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.facebook.ads.InterstitialAd";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "loadAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "isAdLoaded", XC_MethodReplacement.returnConstant(false));
        XposedBridge.hookAllMethods(clazz, "show", XC_MethodReplacement.returnConstant(false));
        XposedBridge.log("All method hooking done for class :: " + className);

    }

    private void hookFacebookNativeAdAdMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.facebook.ads.NativeAd";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "loadAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "isAdLoaded", XC_MethodReplacement.returnConstant(false));
        XposedBridge.hookAllMethods(clazz, "downloadMedia", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);
    }

    private void hookGoogleFacebookAdapterMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.google.ads.mediation.facebook.FacebookAdapter";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "requestBannerAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "requestInterstitialAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "showInterstitial", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "requestNativeAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "buildAdRequest", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "createAndLoadInterstitial", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "createAndLoadNativeAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);
    }

    private void hookGoogleMobileAdsMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.google.android.gms.ads.MobileAds";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "initialize", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);
    }

    private void hookGoogleAdManagerAdViewMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.google.android.gms.ads.admanager.AdManagerAdView";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "loadAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);
    }

    private void hookGoogleAdCustomEventAdapterMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "requestBannerAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "requestInterstitialAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "requestNativeAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.hookAllMethods(clazz, "showInterstitial", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);
    }

    private void hookGoogleNativeAdViewHolderMethods(XC_LoadPackage.LoadPackageParam lpParam) {
        final String className = "com.google.android.gms.ads.nativead.NativeAdViewHolder";
        XposedBridge.log("Trying to find class :: " + className + " and hook methods");
        final Class<?> clazz = XposedHelpers.findClass(className, lpParam.classLoader);
        if (clazz == null) {
            XposedBridge.log("Unable to find the class :: " + className + " to hook");
            return;
        }
        XposedBridge.hookAllMethods(clazz, "setNativeAd", XC_MethodReplacement.DO_NOTHING);
        XposedBridge.log("All method hooking done for class :: " + className);
    }
}
