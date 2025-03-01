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

module elide.ssr {
    requires java.base;
    requires kotlin.stdlib;
    requires kotlinx.serialization.core;
    requires io.micronaut.core;
    requires io.micronaut.http;

    requires elide.base;

    exports elide.ssr;
    exports elide.ssr.annotations;
    exports elide.ssr.type;
    exports elide.vm.annotations;
}
