package server

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.EOFException
import java.net.Socket
import java.net.ServerSocket

class Server{

    object Work{

        @JvmStatic
        private var server: ServerSocket? = null
        private const val port: Int = 4578

        @JvmStatic
        fun main(args: Array<String>){
            val onServer: Server = Server()
            println("服务器工作中 ......")
            onServer.Worker(server!!)
        }

        init{
            try {
                server = ServerSocket(port)
            } catch(e: Exception){
                println(e)
            }
        }
    }

    private fun Worker(server: ServerSocket){
        while(true){
            val client: Socket = server.accept()
            val thread: ServerThread = ServerThread(client)
            thread.start()
        }
    }

}

class ServerThread(client: Socket): Thread() {
    private var client: Socket? = null

    init{
        this.client = client
    }

    override fun run(){
        try{
            val sin: DataInputStream = DataInputStream(client!!.getInputStream())
            val sout: DataOutputStream = DataOutputStream(client!!.getOutputStream())
            var message: String = ""
            var send: String = ""

            send = "Have a good time"
            sout.writeUTF(send)

            while(true) {
                message = sin.readUTF()
                println("客户端发来的信息为: $message")
            }


        } catch(eof: EOFException){
            // 这是合法的异常
        } catch(e: Exception){
            println(e)
        }
    }

}