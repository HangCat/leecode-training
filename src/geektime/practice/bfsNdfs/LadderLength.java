package geektime.practice.bfsNdfs;

import java.util.*;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-19
 */
public class LadderLength {

	public static void main(String[] args) {
		final String beginWord = "hit";
		final String endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

		System.out.println(ladderLengthSingleQueue(beginWord, endWord, wordList));
	}

	private static int ladderLengthSingleQueue(String beginWord, String endWord, List<String> wordList) {
		final Map<String, List<String>> allCombDict = fillWordMarkerMap(wordList);

		System.out.println(allCombDict);

		final Queue<TmpNode> queue = new LinkedList<>();
		queue.add(new TmpNode(beginWord, 1));
		final Set<String> visited = new HashSet<>();
		visited.add(beginWord);

		while (!queue.isEmpty()) {
			final TmpNode node = queue.poll();
			final String word = node.word;
			final int level = node.level;

			for (int i = 0; i < word.length(); i++) {
				final String maker = getWordMaker(word, i);
				final List<String> subLevelList = allCombDict.get(maker);
				if (subLevelList == null || subLevelList.isEmpty()) continue;
				for (String subWord : subLevelList) {
					if (subWord.equals(endWord)) {
						return level + 1;
					}
					if (!visited.contains(subWord)) {
						visited.add(subWord);
						queue.add(new TmpNode(subWord, level + 1));
					}
				}
			}
		}
		return 0;
	}

	private static String getWordMaker(String word, int i) {
		return word.substring(0, i) + "*" + word.substring(i + 1);
	}

	private static Map<String, List<String>> fillWordMarkerMap(List<String> wordList) {
		final Map<String, List<String>> allCombDict = new HashMap<>();
		wordList.forEach(word -> {
			for (int i = 0; i < word.length(); i++) {
				final String maker = getWordMaker(word, i);
				final List<String> wordMakerMapList
						= allCombDict.getOrDefault(maker, new ArrayList<>());
				wordMakerMapList.add(word);
				allCombDict.put(maker, wordMakerMapList);
			}
		});
		return allCombDict;
	}

	private static class TmpNode {
		String word;
		int level;

		TmpNode(String word, int level) {
			this.word = word;
			this.level = level;
		}
	}

	public static int ladderLengthDeque(String beginWord, String endWord, List<String> wordList) {
		if (!wordList.contains(endWord)) return 0;
		final Map<String, List<String>> allCombDict = fillWordMarkerMap(wordList);

		final Queue<TmpNode> headQueue = new LinkedList<>();
		headQueue.add(new TmpNode(beginWord, 1));
		final Queue<TmpNode> tailQueue = new LinkedList<>();
		tailQueue.add(new TmpNode(endWord, 1));

		final Map<String, Integer> headVisited = new HashMap<>();
		final Map<String, Integer> tailVisited = new HashMap<>();

		while (!headQueue.isEmpty() && !tailQueue.isEmpty()) {
			int ans;
			ans = visitWordNode(allCombDict, headQueue, headVisited, tailVisited);
			if (ans != -1) {
				return ans;
			}
			ans = visitWordNode(allCombDict, tailQueue, tailVisited, headVisited);
			if (ans != -1) {
				return ans;
			}
		}
		return 0;
	}

	private static int visitWordNode(Map<String, List<String>> allCombDict, Queue<TmpNode> queue,
	                                 Map<String, Integer> visited, Map<String, Integer> otherVisited) {
		final TmpNode node = queue.poll();
		final String word = node.word;
		final int level = node.level;
		for (int i = 0; i < word.length(); i++) {
			final String maker = getWordMaker(word, i);
			final List<String> wordMakerMapList = allCombDict.get(maker);
			if (wordMakerMapList == null || wordMakerMapList.isEmpty()) {
				return -1;
			}
			for (String subWord : wordMakerMapList) {
				if (otherVisited.containsKey(subWord)) {
					return level + otherVisited.get(subWord);
				}
				if (!visited.containsKey(subWord)) {
					queue.add(new TmpNode(subWord, level + 1));
					visited.put(subWord, level + 1);
				}
			}
		}
		return -1;
	}

}
