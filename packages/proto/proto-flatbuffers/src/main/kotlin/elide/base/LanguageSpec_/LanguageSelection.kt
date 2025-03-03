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

package elide.base.LanguageSpec_

import com.google.flatbuffers.Constants
import com.google.flatbuffers.FlatBufferBuilder
import com.google.flatbuffers.Table
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Suppress("unused")
class LanguageSelection : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : LanguageSelection {
        __init(_i, _bb)
        return this
    }
    val language : Int
        get() {
            val o = __offset(4)
            return if(o != 0) bb.getInt(o + bb_pos) else 0
        }
    val isoLanguage : String?
        get() {
            val o = __offset(6)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val isoLanguageAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(6, 1)
    fun isoLanguageInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 6, 1)
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsLanguageSelection(_bb: ByteBuffer): LanguageSelection = getRootAsLanguageSelection(_bb, LanguageSelection())
        fun getRootAsLanguageSelection(_bb: ByteBuffer, obj: LanguageSelection): LanguageSelection {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createLanguageSelection(builder: FlatBufferBuilder, language: Int, isoLanguageOffset: Int) : Int {
            builder.startTable(2)
            addIsoLanguage(builder, isoLanguageOffset)
            addLanguage(builder, language)
            return endLanguageSelection(builder)
        }
        fun startLanguageSelection(builder: FlatBufferBuilder) = builder.startTable(2)
        fun addLanguage(builder: FlatBufferBuilder, language: Int) = builder.addInt(0, language, 0)
        fun addIsoLanguage(builder: FlatBufferBuilder, isoLanguage: Int) = builder.addOffset(1, isoLanguage, 0)
        fun endLanguageSelection(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}
