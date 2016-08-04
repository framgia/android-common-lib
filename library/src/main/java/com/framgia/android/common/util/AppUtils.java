package com.framgia.android.common.util;

/**
 * Copyright 2016 Framgia, Inc.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.framgia.android.common.BuildConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * AppUtils
 */
public class AppUtils {

    /**
     * Get application package name
     *
     * @return package name
     */
    public static String getPackageName(Context ctx) {
        return ctx.getApplicationContext().getPackageName();
    }

    /**
     * generate hash key from signature
     *
     * @return device hash
     */
    public static String getHashDevice(Activity activity) throws NameNotFoundException,
            NoSuchAlgorithmException {
        final int flags = PackageManager.GET_SIGNATURES;
        PackageInfo info = activity.getPackageManager().getPackageInfo(getPackageName(activity),
                flags);
        String hashKey = null;
        for (Signature signature : info.signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            hashKey = Base64.encodeToString(md.digest(), Base64.DEFAULT);
            if (BuildConfig.DEBUG) {
                Log.d("KeyHash:", hashKey);
            }
        }

        if (TextUtils.isEmpty(hashKey)) {
            throw new Resources.NotFoundException("hash key not found");
        }

        return hashKey;
    }

}
