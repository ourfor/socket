package server

import java.io.*
import java.net.Socket

fun main(args: Array<String>){
    val host = "127.0.0.1"
    val port = 4578
    var path = "/Users/sagit/Desktop/md"

    var bytes: ByteArray = ByteArray(1024)
    var length: Int = 0

    print("你想将哪里的文件上传到服务器:")
    val rc: Runtime = Runtime.getRuntime()
    rc.exec("open .")
    path = readLine()!!

    try{
        val client: Socket = Socket(host,port)
        val sout: DataOutputStream = DataOutputStream(client.getOutputStream())

        val filename: String = path.substring(path.lastIndexOf("/"))

        sout.writeUTF(filename)

        val fis = FileInputStream(File(path))
        val fos = client.getOutputStream() as FileOutputStream



        while(true){
            length = fis.read(bytes)
            if(length == -1) break

            fos.write(bytes,0,length)

        }

        fos.flush()
        fis.close()
        fos.close()
        sout.close()

    } catch(e: Exception){
        println(e)
    }
}