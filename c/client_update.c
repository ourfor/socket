#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>


int main(int argc,char **argv){

	// 设置服务端ip和端口
	char *serv_host = "127.0.0.1";
	int serv_port = 4578;
	
	// 第三个参数设置为0，这样就可以自动选择协议
	int sock = socket(AF_INET,SOCK_STREAM,0);

	// 服务器套接字
	struct sockaddr_in serv_addr;

	// 将不需要设置的参数置为0
	memset(&serv_addr,0,sizeof(struct sockaddr_in));

	serv_addr.sin_family = AF_INET;  // 设置为IPv4
	serv_addr.sin_addr.s_addr = inet_addr(serv_host);
	serv_addr.sin_port = htons(serv_port);

	// 开始连接服务器
	
	connect(sock,(struct sockaddr*)&serv_addr,sizeof(serv_addr));

	char buffer[40];

	// 读取服务端传输的信息
	
	//int send = atoi(argv[1]);
	char *send = argv[1];
	// read(sock,buffer,sizeof(buffer)-1);
	write(sock,&send,strlen(send)+1);

	read(sock,buffer,sizeof(buffer));
	// 打印接收的信息
	
	printf("Message from server: %s\t",buffer);

	close(sock);

	return 0;


}
