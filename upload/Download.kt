package server

import java.io.DataInputStream
import java.net.ServerSocket
import java.net.Socket
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Scanner


class Download{
    private var server: ServerSocket? = null
    private val port = 4578


    companion object {
        @JvmStatic
        fun main(args: Array<String>){

            val serv: Download = Download()
            serv.init()

            println("服务器开始工作 ...")

            while(true){
                try{
                    val client: Socket = serv.server!!.accept()
                    val thread: myThread = myThread(client)
                    thread.start()

                } catch(e: Exception){
                    println(e)
                }

            }



        }
    }

    fun init(){
        server = ServerSocket(port)
    }

}

class myThread(client: Socket): Thread(){
    private var client: Socket? = null

    init{
        this.client = client
    }

    override fun run(){
        val host: String = "127.0.0.1"
        val port: Int = 4578
        var path: String = "/Users/sagit/Desktop/md/"


        try{

            val sin: DataInputStream = DataInputStream(client!!.getInputStream())
            val filename: String = sin.readUTF()

            val fis = client!!.getInputStream() as FileInputStream
            val file = File(path,filename)
            val fos = FileOutputStream(file)

            var bytes: ByteArray = ByteArray(1024)
            var length: Int = 0

            while(true){
                length = fis.read(bytes)
                if(length == -1) break
                fos.write(bytes,0,length)
            }

            println("文件$filename 保存成功")
            fis.close()
            fos.flush()
            fos.close()
            sin.close()

        } catch(e: Exception){
            println(e)
        } finally{
            client!!.close()

        }
    }
}