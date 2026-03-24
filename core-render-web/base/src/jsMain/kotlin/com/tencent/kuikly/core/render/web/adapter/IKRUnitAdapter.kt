/*
 * Tencent is pleased to support the open source community by making KuiklyUI
 * available.
 * Copyright (C) 2025 Tencent. All rights reserved.
 * Licensed under the License of KuiklyUI;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://github.com/Tencent-TDS/KuiklyUI/blob/main/LICENSE
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tencent.kuikly.core.render.web.adapter

/**
 * Adapter for converting raw Kotlin layout Float values to CSS value strings.
 *
 * By default (when no adapter is registered), [toPxF][com.tencent.kuikly.core.render.web.ktx.toPxF]
 * appends `"px"` directly to the Float, e.g. `100f → "100.0px"`.
 *
 * Register a custom implementation via [KuiklyRenderAdapterManager.krUnitAdapter] to:
 * - Change the CSS unit (e.g. `rem`, `vw`)
 * - Scale or transform the numeric value before output
 *
 * Example — rem units with a 100px root font size:
 * ```kotlin
 * KuiklyRenderAdapterManager.krUnitAdapter = object : IKRUnitAdapter {
 *     private val rootFontSize = 100f
 *     override fun toUnit(value: Float): String = "${value / rootFontSize}rem"
 * }
 * // height(100f) → "1.0rem"
 * // width(200f)  → "2.0rem"
 * ```
 *
 * Example — scale a 750-design to actual viewport width:
 * ```kotlin
 * KuiklyRenderAdapterManager.krUnitAdapter = object : IKRUnitAdapter {
 *     private val scale = window.innerWidth / 750f
 *     override fun toUnit(value: Float): String = "${value * scale}px"
 * }
 * // height(100f) on a 375px viewport → "50.0px"
 * ```
 */
interface IKRUnitAdapter {

    /**
     * Convert a raw Float layout value to a CSS value string.
     *
     * This method is called for every dimension that flows from the Kotlin layout engine
     * to the DOM (width, height, left, top, fontSize, borderRadius, etc.).
     *
     * @param value The raw numeric value produced by the Kotlin/Yoga layout engine,
     *              e.g. `100.0` from `height(100f)`.
     * @return A complete CSS value string including the unit suffix,
     *         e.g. `"100px"`, `"1.0rem"`, `"50.0px"`.
     */
    fun toUnit(value: Float): String
}
