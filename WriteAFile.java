import java.io.*;
class WriteAFile{
	public static void main(String[]args){
		try{
			FileWriter writer=new FileWriter("myText.txt");
			writer.write("hello world!");
			writer.close();

		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
}