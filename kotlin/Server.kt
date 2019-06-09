import java.net.ServerSocket
import java.net.Socket 
import java.io.DataInputStream
import java.io.DataOutputStream

class Server{
    private val port: Int = 4578
    private var server: ServerSocket = null

    init{
        server = ServerSocket(port)
    }

    public fun Work(){
        val client: Socket = server.accept()
        val in: DataInputStream = DataInputStream(client.inputStream)
        val out: DataOutputStream = DataOutputStream(client.outputSteam)

    }

    public fun main(args: Array<String>){
        val server: Server = Server();
        println("Nice to meet you!")
    }
}

