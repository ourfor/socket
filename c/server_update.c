#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <netdb.h>

char* deal(char*);

int main(){
	
	//设置服务器ip(点分十进制或者域名都可以)
	const char *host = "127.0.0.1";
	//选择要打开的端口或者服务
	const int server = 4578;

	// 打开套接字
	int serv_sock = socket(AF_INET,SOCK_STREAM,0);

	struct sockaddr_in serv_addr;

	//清空Serv_add中不需要设置的内容
	memset(&serv_addr,0,sizeof(struct sockaddr_in));
	
	serv_addr.sin_family = AF_INET;        //设置为IPv4地址
	serv_addr.sin_addr.s_addr = inet_addr(host);         // 设置ip地址
	serv_addr.sin_port = htons(server);

	// 告诉内核，将描述符与套接字关联
	bind(serv_sock,(struct sockaddr*)&serv_addr,sizeof(serv_addr));



	// 将主动套接装换为被动套接字
	listen(serv_sock,1024);


	// 开始使用accept来等待客户端连接
	struct sockaddr_in cli_addr;
	int cli_sock;
	while(1){
			socklen_t cli_addr_size = sizeof(cli_addr);

			cli_sock = accept(serv_sock,(struct sockaddr*)&cli_addr,&cli_addr_size);

			char *str;

			char message[40];
			// 读取客户机发来的信息
			read(cli_sock,message,sizeof(message));

			str = deal(message);

			// 向客户端写入数据
			write(cli_sock,str,sizeof(str));

			printf("客户端发送过来的信息为: %s\n",message);

	}

	close(cli_sock);
	close(serv_sock);



	exit(0);
}


char* deal(char *message){
	int choice = 0;
	for(int i=0;i<strlen(message);i++){
			choice *= 10;
			choice += message[i] - '0';
	}

	printf("你的选择为: %d\n",choice);
	switch(choice){
		case 1: 
			printf("Good Morning\n");
			return "Good Morning";
			break;
		case 2:
			printf("Good Night\n");
			return "Good Night";
			break;
		default:
			printf("Good Everning\n");
			return "Good Everning";
			break;
	}
}
