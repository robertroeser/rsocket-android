/*
 * Copyright 2016 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rsocket

internal class StreamIdSupplier private constructor(private var streamId: Int) {

    @Synchronized
    fun nextStreamId(): Int {
        streamId += 2
        return streamId
    }

    @Synchronized
    fun isBeforeOrCurrent(streamId: Int): Boolean {
        return this.streamId >= streamId && streamId > 0
    }

    companion object {

        fun clientSupplier(): StreamIdSupplier {
            return StreamIdSupplier(-1)
        }

        fun serverSupplier(): StreamIdSupplier {
            return StreamIdSupplier(0)
        }
    }
}
