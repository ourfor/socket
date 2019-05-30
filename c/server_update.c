#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <unistd.h>
#include <netdb.h>


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

			char str[] = "Nice to meet you!";

			// 向客户端写入数据
			write(cli_sock,str,sizeof(str));
	}

	close(cli_sock);
	close(serv_sock);



	exit(0);
}

