package network.xyo.sdkcorekotlin.queries

import kotlinx.coroutines.Deferred
import network.xyo.sdkcorekotlin.boundWitness.XyoBoundWitness
import network.xyo.sdkcorekotlin.persist.XyoStorageException

interface XyoGetOriginBlockByHash {
    /**
     * Gets an origin block by its previous hash field.
     *
     * @param originBlockHash The previous hash of the origin block the function is looking
     * to find.
     * @return a deferred XyoPartyOriginBlock that is has the previous hash.
     * @throws XyoStorageException if there is a problem reading
     */
    @Throws(XyoStorageException::class)
    fun getOriginBlockByBlockHash (originBlockHash: ByteArray) : Deferred<XyoBoundWitness?>
}