import java.io.FileOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class Start{
	public static void main(String args[]){
		Human person = new Human();
		person.setName("李白");
		person.setAge(20);
		person.setSex("男");

		try{
			File fp = new File("human.json");
			if(!fp.exists()) fp.createNewFile();
			FileOutputStream fos = new FileOutputStream(fp);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(person);
			oos.close();


		} catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
