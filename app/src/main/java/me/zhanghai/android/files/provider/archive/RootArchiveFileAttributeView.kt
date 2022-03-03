/*
 * Copyright (c) 2019 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.files.provider.archive

import java.io.IOException
import java8.nio.file.Path
import me.zhanghai.android.files.provider.common.PosixFileAttributeView
import me.zhanghai.android.files.provider.common.PosixFileAttributes
import me.zhanghai.android.files.provider.root.RootPosixFileAttributeView

internal class RootArchiveFileAttributeView(
    attributeView: PosixFileAttributeView,
    private val path: Path
) : RootPosixFileAttributeView(attributeView) {
    @Throws(IOException::class)
    override fun readAttributes(): PosixFileAttributes {
        ArchiveFileSystemProvider.doRefreshIfNeeded(path)
        return super.readAttributes()
    }
}
