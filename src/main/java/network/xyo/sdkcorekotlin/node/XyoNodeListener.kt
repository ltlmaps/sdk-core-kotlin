package network.xyo.sdkcorekotlin.node

import network.xyo.sdkcorekotlin.XyoError
import network.xyo.sdkcorekotlin.boundWitness.XyoBoundWitness

/**
 * THe listener for Xyo Nodes.
 */
interface XyoNodeListener {
    /**
     * This function will be called on every bound witness start.
     */
    fun onBoundWitnessStart()

    /**
     * This function will be called on evey time a bound witness ended successfully.
     */
    fun onBoundWitnessDiscovered(boundWitness : XyoBoundWitness)

    /**
     * This function will be called on evey time a bound witness did not end successfully.
     */
    fun onBoundWitnessEndFailure()

    /**
     *  This function will be called on every error.
     */
    fun onError (error: XyoError?)
}