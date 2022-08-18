import java.util.*;

public class StringPermutation{
	public static void main(String[] args) {
		String s = "abc";
		List<String> result = new ArrayList<>();
		permuteString(s, new StringBuilder(), result); 

		for(String str : result) {
			System.out.println(str);
		}
	}

	public static void permuteString(String s,  StringBuilder cur, List<String> result) {
		if(s.length()==0) {
			result.add(cur.toString());
			return;
		}

		for(int i=0;i<s.length();i++) {
			String newString = s.substring(0,i)+s.substring(i+1);
			permuteString(newString, cur.append(s.charAt(i)), result);	
			cur=cur.delete(cur.length()-1, cur.length());
		}

	}
}