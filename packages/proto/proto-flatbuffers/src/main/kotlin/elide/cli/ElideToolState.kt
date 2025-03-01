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

package elide.cli

import com.google.flatbuffers.Constants
import com.google.flatbuffers.FlatBufferBuilder
import com.google.flatbuffers.Table
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Suppress("unused")
class ElideToolState : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : ElideToolState {
        __init(_i, _bb)
        return this
    }
    val auth : elide.cli.LocalAuthToken? get() = auth(elide.cli.LocalAuthToken())
    fun auth(obj: elide.cli.LocalAuthToken) : elide.cli.LocalAuthToken? {
        val o = __offset(4)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    val config : elide.cli.CommandLineConfig? get() = config(elide.cli.CommandLineConfig())
    fun config(obj: elide.cli.CommandLineConfig) : elide.cli.CommandLineConfig? {
        val o = __offset(6)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsElideToolState(_bb: ByteBuffer): ElideToolState = getRootAsElideToolState(_bb, ElideToolState())
        fun getRootAsElideToolState(_bb: ByteBuffer, obj: ElideToolState): ElideToolState {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createElideToolState(builder: FlatBufferBuilder, authOffset: Int, configOffset: Int) : Int {
            builder.startTable(2)
            addConfig(builder, configOffset)
            addAuth(builder, authOffset)
            return endElideToolState(builder)
        }
        fun startElideToolState(builder: FlatBufferBuilder) = builder.startTable(2)
        fun addAuth(builder: FlatBufferBuilder, auth: Int) = builder.addOffset(0, auth, 0)
        fun addConfig(builder: FlatBufferBuilder, config: Int) = builder.addOffset(1, config, 0)
        fun endElideToolState(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}
