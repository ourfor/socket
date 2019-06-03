

public class Animal{
	public static void main(String args[]){
		Dog dog = new Dog();
		dog.start();
		Cat cat = new Cat();
		cat.start();
		cat.run();
		dog.run();
		while(true){
			System.out.println("I am the main Thread");
		}
	}
}

class Dog extends Thread{
	public void run(){
		while(true){
			System.out.println("I am a dog");
		}
	}
}

class Cat extends Thread{
	public void run(){
		while(true){
			System.out.println("I am a cat");
		}
	}
}
