package network.xyo.sdkcorekotlin.node

interface XyoHeuristicGetter {

    /**
     * Gets the Heuristic for the getter. If the heuristic is null, the heuristic will not be
     * included in the payload.
     *
     * @return the Heuristic
     */
    fun getHeuristic() : ByteArray?
}