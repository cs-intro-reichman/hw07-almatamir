
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String news = str.substring(1);
		return news;
	}

    public static char Head(String str)
	{
		return str.charAt(0);
	}
	
	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.length() == 0)
		return word2.length();
		else if (word2.length() == 0)
		return word1.length();
		else if (Head(word1) == Head(word2))
		return levenshtein(tail(word2), tail(word1));
		else {
			int op1 = levenshtein(tail(word1), word2);
			int op2 = levenshtein(word1, tail(word2));
			int op3 = levenshtein(tail(word1), tail(word2));
			return 1+Math.min(op2, Math.min(op1, op3));
		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i=0; i < dictionary.length; i++)
		{
			dictionary[i] = in.readString();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = Integer.MAX_VALUE;
		String s = word;
		for (int i = 0; i < dictionary.length; i++)
		{
			if (min > levenshtein(word, dictionary[i])) 
				{
					min = levenshtein(word, dictionary[i]);
					s = dictionary[i];
				}
	
		}
		if (min > threshold)
		return word;
		return s;
	}

}
