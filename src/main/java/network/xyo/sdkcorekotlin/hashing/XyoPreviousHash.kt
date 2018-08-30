package network.xyo.sdkcorekotlin.hashing

import network.xyo.sdkcorekotlin.XyoError
import network.xyo.sdkcorekotlin.XyoResult
import network.xyo.sdkcorekotlin.data.XyoByteArrayReader
import network.xyo.sdkcorekotlin.data.XyoObject
import network.xyo.sdkcorekotlin.data.XyoObjectProvider

open class XyoPreviousHash(val hash: XyoHash) : XyoObject() {
    override val data: XyoResult<ByteArray> = hash.typed
    override val id: XyoResult<ByteArray> = XyoResult(byteArrayOf(major, minor))
    override val sizeIdentifierSize: XyoResult<Int?> = XyoResult<Int?>(null)

    companion object : XyoObjectProvider() {
        override val major: Byte = 0x02
        override val minor: Byte = 0x06
        override val sizeOfBytesToGetSize: XyoResult<Int?> = XyoResult(2)

        override fun readSize(byteArray: ByteArray): XyoResult<Int> {
            val hashCreator = XyoObjectProvider.getCreator(byteArray[0], byteArray[1])
            if (hashCreator.error != null) return XyoResult(XyoError(""))
            val hashCreatorValue = hashCreator.value ?: return XyoResult(XyoError(""))

            val sizeToRead = hashCreatorValue.sizeOfBytesToGetSize
            if (sizeToRead.error != null) return XyoResult(XyoError(""))
            val sizeToReadValue = sizeToRead.value ?: return XyoResult(XyoError(""))
            val hashCreatorSize = hashCreatorValue.readSize(XyoByteArrayReader(byteArray).read(2, sizeToReadValue))
            val hashCreatorSizeValue = hashCreatorSize.value ?: return XyoResult(XyoError("Problem reading hash child!"))

            return XyoResult(hashCreatorSizeValue + 2)
        }

        override fun createFromPacked(byteArray: ByteArray): XyoResult<XyoObject> {
            val hashCreated = XyoObjectProvider.create(byteArray)
            if (hashCreated.error != null) return XyoResult(XyoError(""))
            val hashCreatedValue = hashCreated.value as? XyoHash ?: return XyoResult(XyoError(""))

            return XyoResult(XyoPreviousHash(hashCreatedValue))
        }
    }
}