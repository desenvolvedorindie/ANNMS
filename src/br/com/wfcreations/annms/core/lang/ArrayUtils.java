package br.com.wfcreations.annms.core.lang;

import java.util.HashSet;
import java.util.Set;

public class ArrayUtils {

	public static <T> boolean hasDuplicate(T[] strings) {
		Set<T> tempSet = new HashSet<T>();
		for (T str : strings)
			if (!tempSet.add(str))
				return true;
		return false;
	}

}
