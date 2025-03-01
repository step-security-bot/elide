/*
 * Copyright (c) 2023 Elide Ventures, LLC.
 *
 * Licensed under the MIT license (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   https://opensource.org/license/mit/
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License.
 */

// automatically generated by the FlatBuffers compiler, do not modify

package elide.structs.BloomFilter_

import com.google.flatbuffers.Constants
import com.google.flatbuffers.FlatBufferBuilder
import com.google.flatbuffers.Table
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Suppress("unused")
class FilterLayer : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : FilterLayer {
        __init(_i, _bb)
        return this
    }
    fun bitset(j: Int) : ULong {
        val o = __offset(4)
        return if (o != 0) {
            bb.getLong(__vector(o) + j * 8).toULong()
        } else {
            0uL
        }
    }
    val bitsetLength : Int
        get() {
            val o = __offset(4); return if (o != 0) __vector_len(o) else 0
        }
    val bitsetAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(4, 8)
    fun bitsetInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 4, 8)
    fun count(j: Int) : ULong {
        val o = __offset(6)
        return if (o != 0) {
            bb.getLong(__vector(o) + j * 8).toULong()
        } else {
            0uL
        }
    }
    val countLength : Int
        get() {
            val o = __offset(6); return if (o != 0) __vector_len(o) else 0
        }
    val countAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(6, 8)
    fun countInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 6, 8)
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsFilterLayer(_bb: ByteBuffer): FilterLayer = getRootAsFilterLayer(_bb, FilterLayer())
        fun getRootAsFilterLayer(_bb: ByteBuffer, obj: FilterLayer): FilterLayer {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createFilterLayer(builder: FlatBufferBuilder, bitsetOffset: Int, countOffset: Int) : Int {
            builder.startTable(2)
            addCount(builder, countOffset)
            addBitset(builder, bitsetOffset)
            return endFilterLayer(builder)
        }
        fun startFilterLayer(builder: FlatBufferBuilder) = builder.startTable(2)
        fun addBitset(builder: FlatBufferBuilder, bitset: Int) = builder.addOffset(0, bitset, 0)
        fun createBitsetVector(builder: FlatBufferBuilder, data: ULongArray) : Int {
            builder.startVector(8, data.size, 8)
            for (i in data.size - 1 downTo 0) {
                builder.addLong(data[i].toLong())
            }
            return builder.endVector()
        }
        fun startBitsetVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(8, numElems, 8)
        fun addCount(builder: FlatBufferBuilder, count: Int) = builder.addOffset(1, count, 0)
        fun createCountVector(builder: FlatBufferBuilder, data: ULongArray) : Int {
            builder.startVector(8, data.size, 8)
            for (i in data.size - 1 downTo 0) {
                builder.addLong(data[i].toLong())
            }
            return builder.endVector()
        }
        fun startCountVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(8, numElems, 8)
        fun endFilterLayer(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}
