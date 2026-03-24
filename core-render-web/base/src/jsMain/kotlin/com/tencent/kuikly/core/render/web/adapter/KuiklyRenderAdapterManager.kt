package com.tencent.kuikly.core.render.web.adapter

object KuiklyRenderAdapterManager {
    /**
     * Color conversion adapter
     */
    var krColorParseAdapter: IKRColorParserAdapter? = null

    /**
     * Kuikly Log adapter
     */
    var krLogAdapter: IKRLogAdapter? = null

    /**
     * Text post-processor adapter
     */
    var krTextPostProcessorAdapter: IKRTextPostProcessorAdapter? = null

    /**
     * CSS unit adapter.
     *
     * When set, every call to [toPxF][com.tencent.kuikly.core.render.web.ktx.toPxF] delegates
     * to [IKRUnitAdapter.toUnit] instead of the default `"${value}px"` behaviour.
     *
     * This lets the host application globally control the CSS unit and/or scale all
     * layout values before they are written to the DOM.
     *
     * Set to `null` (default) to keep the original `px` behaviour.
     */
    var krUnitAdapter: IKRUnitAdapter? = null
}

object KuiklyRenderLog {
    fun i(tag: String, msg: String) {
        KuiklyRenderAdapterManager.krLogAdapter?.i(tag, msg)
    }

    fun d(tag: String, msg: String) {
        KuiklyRenderAdapterManager.krLogAdapter?.d(tag, msg)
    }

    fun e(tag: String, msg: String) {
        KuiklyRenderAdapterManager.krLogAdapter?.e(tag, msg)
    }
}
