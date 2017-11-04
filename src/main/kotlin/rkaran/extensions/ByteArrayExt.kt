package rkaran.extensions

import java.util.*

/**
 * Created by rkaran on 29/10/17.
 */

/**
 * Encode byte-array to its base64 format.
 * @return base64 encoding.
 */
fun ByteArray.toBase64ByteArray() = Base64.getEncoder().encode(this)