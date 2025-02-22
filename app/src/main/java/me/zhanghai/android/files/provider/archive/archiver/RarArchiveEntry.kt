/*
 * Copyright (c) 2019 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.files.provider.archive.archiver

import com.github.junrar.rarfile.FileHeader
import java.util.Date
import org.apache.commons.compress.archivers.ArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipEncoding

internal class RarArchiveEntry(val header: FileHeader, zipEncoding: ZipEncoding) : ArchiveEntry {
    private val name: String

    init {
        @Suppress("DEPRECATION")
        var name = header.fileNameW
        if (name.isNullOrEmpty()) {
            name = zipEncoding.decode(header.fileNameByteArray)
        }
        name = name.replace('\\', '/')
        this.name = name
    }

    override fun getName(): String = name

    override fun getSize(): Long = header.fullUnpackSize

    override fun isDirectory(): Boolean = header.isDirectory

    override fun getLastModifiedDate(): Date = header.mTime
}
