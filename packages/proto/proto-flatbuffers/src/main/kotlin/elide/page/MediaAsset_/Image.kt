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

package elide.page.MediaAsset_

import com.google.flatbuffers.Constants
import com.google.flatbuffers.FlatBufferBuilder
import com.google.flatbuffers.Table
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Suppress("unused")
class Image : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : Image {
        __init(_i, _bb)
        return this
    }
    fun asset(j: Int) : elide.page.MediaAsset_.Image_.ImageAsset? = asset(elide.page.MediaAsset_.Image_.ImageAsset(), j)
    fun asset(obj: elide.page.MediaAsset_.Image_.ImageAsset, j: Int) : elide.page.MediaAsset_.Image_.ImageAsset? {
        val o = __offset(4)
        return if (o != 0) {
            obj.__assign(__indirect(__vector(o) + j * 4), bb)
        } else {
            null
        }
    }
    val assetLength : Int
        get() {
            val o = __offset(4); return if (o != 0) __vector_len(o) else 0
        }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsImage(_bb: ByteBuffer): Image = getRootAsImage(_bb, Image())
        fun getRootAsImage(_bb: ByteBuffer, obj: Image): Image {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createImage(builder: FlatBufferBuilder, assetOffset: Int) : Int {
            builder.startTable(1)
            addAsset(builder, assetOffset)
            return endImage(builder)
        }
        fun startImage(builder: FlatBufferBuilder) = builder.startTable(1)
        fun addAsset(builder: FlatBufferBuilder, asset: Int) = builder.addOffset(0, asset, 0)
        fun createAssetVector(builder: FlatBufferBuilder, data: IntArray) : Int {
            builder.startVector(4, data.size, 4)
            for (i in data.size - 1 downTo 0) {
                builder.addOffset(data[i])
            }
            return builder.endVector()
        }
        fun startAssetVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(4, numElems, 4)
        fun endImage(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}
