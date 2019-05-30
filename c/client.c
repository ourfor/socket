#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

int main(){
	int sock = socket(AF_INET,SOCK_STREAM,0);
	struct sockaddr_in serv_addr;
	memset(&serv_addr,0,sizeof(serv_addr));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	serv_addr.sin_port = htons(1233);
	connect(sock,(struct sockaddr*)&serv_addr,sizeof(serv_addr));

	char buff[40];
	read(sock,buff,sizeof(buff)-1);

	printf("Message from server: %s\n",buff);

	close(sock);

	return 0;
}
