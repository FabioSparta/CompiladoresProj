public class AlgTest{
	public String encrypt(int key,int rot1,int rot2,String message){
		message = message + "-" + (key+rot1+rot2);
		return message;
	}

	public String decrypt(int key,int rot1,int rot2,String message){
		return message.split("-")[0];
	}
}