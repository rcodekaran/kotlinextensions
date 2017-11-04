package rkaran.extensions

/**
 * Created by rkaran on 26/10/17.
 */

/**
 * Converts array to human-readable string.
 */
fun <T> Array<T>.toReadableString():String {
    val buf = StringBuffer()
    buf.append('[')
    buf.append(this.map { obj -> obj.toString() }.joinToString(","))
    buf.append(']')
    return buf.toString()
}

/**
 * Finds first index of max element in the array
 * @param cmp Comparator for array element type.
 * @return first index of max element if array is non-empty, -1 otherwise
 */
fun <T> Array<T>.argmax(cmp:(x:T, y:T) -> Int):Int {
    if(this.isNotEmpty()) {
        var mxIdx = 0
        for((i,x) in this.withIndex()) {
            if(cmp(x, this[mxIdx]) > 0) {
                mxIdx = i
            }
        }
        return mxIdx
    } else {
        return -1
    }
}

/**
 * Swap elements at given positions in the array.
 * @param x first index
 * @param y second index
 */
fun <T> Array<T>.swap(x:Int, y:Int) {
    val tmp = this[x]
    this[x] = this[y]
    this[y] = tmp
}

/**
 * Partitions the array into 2 halves with elements in left half less than element in right half and returns the split
 * index. Element at split index is the one that would be present there if the sorted were sorted.
 * @param k index of element on the basis of which partition has to be carried out.
 * @param cmp Comparator for array element type.
 * @return Split index/
 */
fun <T> Array<T>.partition(k:Int, cmp:(x:T, y:T) -> Int):Int {
    var ptr1 = 0
    var ptr2 = this.size - 1
    var idx = 0
    var splitVal = this[k]
    while(ptr1 <= idx && idx < ptr2) {
        val comparision = cmp(this[ptr1], splitVal)
        if(comparision < 0) {
            this.swap(ptr1++, idx++)
        } else if(comparision > 0) {
            this.swap(ptr2--, idx)
        } else {
            ++idx
        }
    }
    return ptr1
}

/**
 * Computes floor index for a given target which is equal to the first index where the target element
 * is present (in case it is present) or index of just smaller element (if such an element is present) or -1 (otherwise).
 * @param target target value.
 * @param start starting index for subarray (inclusive).
 * @param end ending idnex for subarray (inclusive).
 * @param cmp Comparator array element type.
 * @return floor index.
 */
fun <T> Array<T>.floor(target:T, start:Int, end:Int, cmp:(x:T, y:T) -> Int):Int {
    if(start + 1 < end) {
        val mid = start + (end - start)/2
        val comparision = cmp(target, this[mid])
        if(comparision < 0) {
            return this.floor(target, start, mid - 1, cmp)
        } else if(comparision > 0) {
            return this.floor(target, mid + 1, end, cmp)
        } else {
            if(start < mid && 0 == cmp(this[mid - 1], target)) {
                return this.floor(target, start, mid - 1, cmp)
            } else {
                return mid
            }
        }
    } else if(start <= end) {
        if(cmp(target, this[start]) < 0) {
            return start - 1
        } else if(cmp(target, this[end]) < 0) {
            return start
        } else {
            return end
        }
    } else {
        return -1
    }
}

/**
 * Computes ceil index for a given target which is equal to the last index where the target element
 * is present (in case it is present) or index of just greater element (if such an element is present) or this.size (otherwise).
 * @param target target value.
 * @param start starting index for subarray (inclusive).
 * @param end ending idnex for subarray (inclusive).
 * @param cmp Comparator array element type.
 * @return ceil index.
 */
fun <T> Array<T>.ceil(target:T, start:Int, end:Int, cmp:(x:T, y:T) -> Int):Int {
    if(start + 1 < end) {
        val mid = start + (end - start)/2
        val comparision = cmp(target, this[mid])
        if(comparision < 0) {
            return this.ceil(target, start, mid - 1, cmp)
        } else if(comparision > 0) {
            return this.ceil(target, mid + 1, end, cmp)
        } else {
            if(end > mid && 0 == cmp(this[mid + 1], target)) {
                return this.ceil(target, mid + 1, end, cmp)
            } else {
                return mid
            }
        }
    } else if(start <= end) {
        if(cmp(target, this[end]) > 0) {
            return end + 1
        } else if(cmp(target, this[start]) > 0) {
            return end
        } else {
            return start
        }
    } else {
        return this.size
    }
}

/**
 * Computes indices of elements in sorted version of array
 * @param cmp Comparator for array element type.
 * @return Int array (arr) where arr[i] = position of i<sup>th</sup> element in sorted version of array.
 */
fun <T> Array<T>.sortIndices(cmp:(x:T, y:T) -> Int):Array<Int> {
    val arr = this.copyOf()
    val positions = Array<Int>(this.size, { i -> i })
    arr.sort()
    for(i in 0..(this.size - 1)) {
        positions[i] = arr.floor(this[i], 0, arr.size - 1, cmp)
    }
    return positions
}