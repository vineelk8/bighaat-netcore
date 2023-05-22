package com.netcorebighaatv3;

import android.app.Application;
import android.content.Context;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.soloader.SoLoader;
import com.netcorebighaatv3.newarchitecture.MainApplicationReactNativeHost;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import android.view.View;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import com.netcore.android.Smartech;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import io.hansel.core.logger.HSLLogLevel;
import io.hansel.core.logger.HSLLogger;
import io.hansel.hanselsdk.Hansel;
import io.hansel.react.HanselRn;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
          // packages.add(new MyReactNativePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };

  private final ReactNativeHost mNewArchitectureNativeHost =
      new MainApplicationReactNativeHost(this);

  @Override
  public ReactNativeHost getReactNativeHost() {
    if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      return mNewArchitectureNativeHost;
    } else {
      return mReactNativeHost;
    }
  }

  @Override
  public void onCreate() {
    super.onCreate();
    // If you opted-in for the New Architecture, we enable the TurboModule system
    ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    SoLoader.init(this, /* native exopackage */ false);
    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
    Smartech.getInstance(new WeakReference<>(getApplicationContext())).initializeSdk(this);
    HSLLogLevel.all.setEnabled(true);
    HSLLogLevel.mid.setEnabled(true);
    HSLLogLevel.debug.setEnabled(true);

    Set<String> nativeIdSet = new HashSet<>();
    nativeIdSet.add("hansel_ignore_view_overlay");
    nativeIdSet.add("hansel_ignore_view");
    nativeIdSet.add("hansel_ignore_container");

    ReactFindViewUtil.addViewsListener(new ReactFindViewUtil.OnMultipleViewsFoundListener() {
        @Override
        public void onViewFound(final View view, String nativeID) {
            if (nativeID.equals("hansel_ignore_view_overlay")) {
                String[] values = view.getTag().toString().split("#");
                int parentsLayerCount = Integer.parseInt(values[0]);
                int childLayerIndex;
                if (values.length < 2 || values[1].isEmpty()) {
                    childLayerIndex = 0;
                } else {
                    childLayerIndex = Integer.parseInt(values[1]);
                }
                HanselRn.setHanselIgnoreViewTag(view, parentsLayerCount, childLayerIndex);
            }else{
                view.setTag(io.hansel.react.R.id.hansel_ignore_view, true);
            }
        }
    }, nativeIdSet);
  }

  /**
   * Loads Flipper in React Native templates. Call this in the onCreate method with something like
   * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
   *
   * @param context
   * @param reactInstanceManager
   */
  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
        Class<?> aClass = Class.forName("com.netcorebighaatv3.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
