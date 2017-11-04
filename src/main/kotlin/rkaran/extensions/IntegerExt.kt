package rkaran.extensions;

/**
 * Created by rkaran on 29/10/17.
 */

/**
 * Converts integer to string based on alphabet provided
 * @param alphabet Symbols used for encoding.
 * @return Encoded string.
 */
private fun Int.toString(alphabet:Array<Char>):String {
    if(0 == this) {
        return "0"
    } else {
        val buf = StringBuffer()
        var num = this
        while(0 != num) {
            buf.append(alphabet[num%alphabet.size])
            num /= 10
        }
        return buf.toString()
    }
}

/**
 * Converts integer to bitstring.
 * @return Encoded string.
 */
fun Int.toBitString() = this.toString(arrayOf('0', '1'))

/**
 * Converts integer to bitstring.
 * @return Encoded string.
 */
fun Int.toHexString():String {
    val alphabet = Array<Char>(16, { i -> '0'})
    var idx = 0
    for(c in '0'..'9') {
        alphabet[idx] = c
    }
    for(c in 'A'..'F') {
        alphabet[idx++] = c
    }
    return this.toString(alphabet)
}