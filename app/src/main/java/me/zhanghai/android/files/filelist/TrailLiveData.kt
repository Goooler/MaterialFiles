/*
 * Copyright (c) 2018 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.files.filelist

import android.os.Parcelable
import androidx.lifecycle.LiveData
import java8.nio.file.Path

class TrailLiveData : LiveData<TrailData>() {
    /**
     * 导航到某路径
     * @param path 该路径
     */
    fun navigateTo(lastState: Parcelable, path: Path) {
        val oldTrailData = value
        if (oldTrailData == null) {
            resetTo(path)
            return
        }
        value = oldTrailData.navigateTo(lastState, path)
    }

    /**
     * 重置到某路径
     * @param path 该路径
     */
    fun resetTo(path: Path) {
        value = TrailData.of(path)
    }

    /**
     * 返回上一级
     */
    fun navigateUp(): Boolean {
        val oldTrailData = value ?: return false
        val trailData = oldTrailData.navigateUp() ?: return false
        value = trailData
        return true
    }

    /**
     * 刷新
     */
    fun reload() {
        value = value
    }
}
