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

package elide.vfs

import com.google.flatbuffers.Constants
import com.google.flatbuffers.FlatBufferBuilder
import com.google.flatbuffers.Table
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Suppress("unused")
class BundleHeader : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : BundleHeader {
        __init(_i, _bb)
        return this
    }
    val magic : UInt
        get() {
            val o = __offset(4)
            return if(o != 0) bb.getInt(o + bb_pos).toUInt() else 0u
        }
    val formatVersion : UInt
        get() {
            val o = __offset(6)
            return if(o != 0) bb.getInt(o + bb_pos).toUInt() else 0u
        }
    val compressionMode : Int
        get() {
            val o = __offset(8)
            return if(o != 0) bb.getInt(o + bb_pos) else 0
        }
    val crc32 : UInt
        get() {
            val o = __offset(10)
            return if(o != 0) bb.getInt(o + bb_pos).toUInt() else 0u
        }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsBundleHeader(_bb: ByteBuffer): BundleHeader = getRootAsBundleHeader(_bb, BundleHeader())
        fun getRootAsBundleHeader(_bb: ByteBuffer, obj: BundleHeader): BundleHeader {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createBundleHeader(builder: FlatBufferBuilder, magic: UInt, formatVersion: UInt, compressionMode: Int, crc32: UInt) : Int {
            builder.startTable(4)
            addCrc32(builder, crc32)
            addCompressionMode(builder, compressionMode)
            addFormatVersion(builder, formatVersion)
            addMagic(builder, magic)
            return endBundleHeader(builder)
        }
        fun startBundleHeader(builder: FlatBufferBuilder) = builder.startTable(4)
        fun addMagic(builder: FlatBufferBuilder, magic: UInt) = builder.addInt(0, magic.toInt(), 0)
        fun addFormatVersion(builder: FlatBufferBuilder, formatVersion: UInt) = builder.addInt(1, formatVersion.toInt(), 0)
        fun addCompressionMode(builder: FlatBufferBuilder, compressionMode: Int) = builder.addInt(2, compressionMode, 0)
        fun addCrc32(builder: FlatBufferBuilder, crc32: UInt) = builder.addInt(3, crc32.toInt(), 0)
        fun endBundleHeader(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}
