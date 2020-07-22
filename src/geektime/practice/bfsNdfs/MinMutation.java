package geektime.practice.bfsNdfs;

import java.util.*;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-20
 */
public class MinMutation {

	public int m(String start, String end, String[] bank) {
		if (start.equals(end)) return 0;
		final char[] baseBankFactors = new char[]{'A', 'C', 'G', 'T'};

		final Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

		final Set<String> visited = new HashSet<>();
		visited.add(start);

		final Queue<String> queue = new LinkedList<>();
		queue.add(start);

		int level = 0;
		while (!queue.isEmpty()) {
			for (int i = queue.size(); i > 0; i--) {
				final String node = queue.remove();
				if (node.equals(end)) return level;

				final char[] geneCharArr = node.toCharArray();

				for (int j = 0; j < geneCharArr.length; j++) {
					final char originalFactor = geneCharArr[i];
					for (char factor : baseBankFactors) {
						geneCharArr[i] = factor;
						final String mutationGene = new String(geneCharArr);
						if (bankSet.contains(mutationGene) && !visited.contains(mutationGene)) {
							queue.add(mutationGene);
							visited.add(mutationGene);
						}
					}
					geneCharArr[i] = originalFactor;
				}
			}
			level++;
		}
		return -1;
	}


	public int minMutation(String start, String end, String[] bank) {
		if (start.equals(end)) return 0;
		Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
		char[] charSet = new char[]{'A', 'C', 'G', 'T'};
		int level = 0;
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		visited.add(start);
		while (!queue.isEmpty()) {
			for (int i = queue.size(); i > 0; i--) {
				String curr = queue.remove();
				if (curr.equals(end)) return level;
				char[] currArray = curr.toCharArray();
				for (int j = 0; j < currArray.length; j++) {
					char old = currArray[i];
					for (char c : charSet) {
						currArray[i] = c;
						String next = new String(currArray);
						if (!visited.contains(next) && bankSet.contains(next)) {
							visited.add(next);
							queue.offer(next);
						}
					}
					currArray[i] = old;
				}
			}
			level++;
		}
		return -1;
	}
}
