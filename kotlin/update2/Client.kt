package client

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import java.net.InetAddress


object Client{

    @JvmStatic
    fun main(args: Array<String>){
        val host: String = "vpsa.ourfor.top"
        val inetTool: InetAddress = InetAddress.getByName(host)

        val port: Int = 4578
        try{

            val client: Socket = Socket(host,port)
            val sin: DataInputStream = DataInputStream(client.getInputStream())
            val sout: DataOutputStream = DataOutputStream(client.getOutputStream())

            var message: String = ""
            message = sin.readUTF()
            println("收到服务端发来的消息: $message")

            var send: String = ""
            print(">")
            send = readLine()!!

            sout.writeUTF(send)

        } catch(e: Exception){
        }
    }
}