package network.xyo.sdkcorekotlin.data.array.single

import network.xyo.sdkcorekotlin.XyoResult
import network.xyo.sdkcorekotlin.data.XyoObject
import network.xyo.sdkcorekotlin.data.array.XyoArrayUnpacker
import java.nio.ByteBuffer

open class XyoSingleTypeArrayShort(override val elementMajor : Byte,
                                   override val elementMinor : Byte,
                                   override var array: Array<XyoObject>) : XyoSingleTypeArrayBase() {

    override val typedId: ByteArray?
        get() = byteArrayOf(elementMajor, elementMinor)

    override val id: XyoResult<ByteArray>
        get() = XyoResult(byteArrayOf(major, minor))

    override val sizeIdentifierSize: XyoResult<Int?>
        get() = sizeOfBytesToGetSize

    companion object : XyoArrayCreator() {
        override val minor: Byte
            get() = 0x02

        override fun readSize(byteArray: ByteArray): XyoResult<Int> {
            return XyoResult(ByteBuffer.wrap(byteArray).short.toInt())
        }

        override val sizeOfBytesToGetSize: XyoResult<Int?>
            get() = XyoResult(2)

        override fun createFromPacked(byteArray: ByteArray): XyoResult<XyoObject> {
            val unpackedArray = XyoArrayUnpacker(byteArray, true, 2)
            val unpackedArrayObject = XyoSingleTypeArrayShort(unpackedArray.majorType!!, unpackedArray.minorType!!, unpackedArray.array.toTypedArray())
            return XyoResult(unpackedArrayObject)
        }
    }
}