package client

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket


fun main(args: Array<String>){
    val host: String = "127.0.0.1"
    val port: Int = 4578
    println("客户端开始工作 ......")
    try{
        val client: Socket = Socket(host,port)
        val sin: DataInputStream = DataInputStream(client.getInputStream())
        val sout: DataOutputStream = DataOutputStream(client.getOutputStream())

        val message: String = sin.readUTF()
        println("接收到服务端写入信息: $message")

        var send: String = ""

        while(true) {
            print(">")
            send = readLine()!!
            sout.writeUTF(send)
        }


    } catch(e: Exception){
        println(e)
    }
}