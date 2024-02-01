/*
    Copyright 2020-2024. Huawei Technologies Co., Ltd. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.huawei.hms.rn.push.local;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.huawei.hms.rn.push.constants.LocalNotification;

public class BitmapDataSubscriber extends BaseBitmapDataSubscriber {
    HmsLocalNotificationPicturesLoader hmsLocalNotificationPicturesLoader;
    LocalNotification.Bitmap bitmapType;

    public BitmapDataSubscriber(HmsLocalNotificationPicturesLoader loader, LocalNotification.Bitmap bitmapType) {
        this.hmsLocalNotificationPicturesLoader = loader;
        this.bitmapType = bitmapType;
    }

    @Override
    public void onNewResultImpl(@Nullable Bitmap bitmap) {
        if (bitmapType.equals(LocalNotification.Bitmap.LARGE_ICON)) {
            hmsLocalNotificationPicturesLoader.setLargeIcon(bitmap);
        } else {
            hmsLocalNotificationPicturesLoader.setBigPicture(bitmap);
        }
    }

    @Override
    public void onFailureImpl(DataSource dataSource) {
        if (bitmapType.equals(LocalNotification.Bitmap.LARGE_ICON)) {
            hmsLocalNotificationPicturesLoader.setLargeIcon(null);
        } else {
            hmsLocalNotificationPicturesLoader.setBigPicture(null);
        }
    }
}