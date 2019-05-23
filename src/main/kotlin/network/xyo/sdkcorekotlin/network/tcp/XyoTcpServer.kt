package network.xyo.sdkcorekotlin.network.tcp


import network.xyo.sdkcorekotlin.network.XyoAdvertisePacket
import java.net.ServerSocket
import kotlin.concurrent.thread

class XyoTcpServer (val port: Int) {
    fun listen (callback: (pipe: XyoTcpPipe) -> (Unit)) = thread {
        val serverSocket = ServerSocket(port)
        val socket = serverSocket.accept()
        val pipe = XyoTcpPipe(socket, null)
        val firstData = pipe.waitForResponse()

        if (firstData != null) {
            pipe.initiationData = XyoAdvertisePacket(firstData)
            callback(pipe)
            return@thread
        }

        return@thread
    }
}