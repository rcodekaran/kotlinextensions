package rkaran.extensions

import java.io.File

/**
 * Created by rkaran on 28/10/17.
 */

/**
 * Extract filename from path
 * @param pathSeparator Path separator used in path.
 * @return Filename.
 */
fun String.extractFilename(pathSeparator:Char = File.separatorChar):String = this.substring(this.lastIndexOf(pathSeparator, 0) + 1)