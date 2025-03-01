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

package elide.vfs.Filesystem_

import com.google.flatbuffers.Constants
import com.google.flatbuffers.FlatBufferBuilder
import com.google.flatbuffers.Table
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Suppress("unused")
class Metadata : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : Metadata {
        __init(_i, _bb)
        return this
    }
    val size : ULong
        get() {
            val o = __offset(4)
            return if(o != 0) bb.getLong(o + bb_pos).toULong() else 0UL
        }
    val compressed : ULong
        get() {
            val o = __offset(6)
            return if(o != 0) bb.getLong(o + bb_pos).toULong() else 0UL
        }
    val compression : Int
        get() {
            val o = __offset(8)
            return if(o != 0) bb.getInt(o + bb_pos) else 0
        }
    val modified : google.protobuf.Timestamp? get() = modified(google.protobuf.Timestamp())
    fun modified(obj: google.protobuf.Timestamp) : google.protobuf.Timestamp? {
        val o = __offset(10)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsMetadata(_bb: ByteBuffer): Metadata = getRootAsMetadata(_bb, Metadata())
        fun getRootAsMetadata(_bb: ByteBuffer, obj: Metadata): Metadata {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createMetadata(builder: FlatBufferBuilder, size: ULong, compressed: ULong, compression: Int, modifiedOffset: Int) : Int {
            builder.startTable(4)
            addCompressed(builder, compressed)
            addSize(builder, size)
            addModified(builder, modifiedOffset)
            addCompression(builder, compression)
            return endMetadata(builder)
        }
        fun startMetadata(builder: FlatBufferBuilder) = builder.startTable(4)
        fun addSize(builder: FlatBufferBuilder, size: ULong) = builder.addLong(0, size.toLong(), 0)
        fun addCompressed(builder: FlatBufferBuilder, compressed: ULong) = builder.addLong(1, compressed.toLong(), 0)
        fun addCompression(builder: FlatBufferBuilder, compression: Int) = builder.addInt(2, compression, 0)
        fun addModified(builder: FlatBufferBuilder, modified: Int) = builder.addOffset(3, modified, 0)
        fun endMetadata(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}
