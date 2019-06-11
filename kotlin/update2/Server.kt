package server

import java.net.Socket
import java.net.ServerSocket
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.EOFException

class Server{

    object Work{

        @JvmStatic
        var server: ServerSocket? = null
        const val port: Int = 4578
        @JvmStatic
        public fun main(args: Array<String>){
            println("服务端工作中 ......")
            val server: Server = Server()
            server.init()
            server.onServer()
        }
    }

    private fun init(){
        try{
            Work.server = ServerSocket(Work.port)
        } catch(e: Exception){
            println(e)
        }
    }

    private fun onServer(){
        while(true){
            val client: Socket = Work.server!!.accept()
            val thread: ServerThread = ServerThread(client)

            thread.start()

        }
    }


}

class ServerThread(client: Socket): Thread(){
    private var client: Socket? = null

    init{
        this.client = client
    }

    override fun run() {
        try{
            val sin: DataInputStream = DataInputStream(client!!.getInputStream())
            val sout: DataOutputStream = DataOutputStream(client!!.getOutputStream())
            val send: String = "Welcome to connect this server"
            sout.writeUTF(send)
            var message: String = sin.readUTF()
            println("客户端发送过来的消息为: $message")
        } catch(e: EOFException){
            // 这是正常的异常，无需额外处理
        } catch(e: Exception){
            println(e)
        }
    }
}


