package sort;

import java.io.*;
import java.util.*;


/**
 * Class that can be used to sort a large file by spliting said file into several temporary sorted files and
 * <p>
 * merging those files.
 *
 * @author Greg Cope
 */

public class StreamSorter {

	private final Comparator<String> sorter;
	private int maxChunkSize = 100000000;
	private List<File> outputs = new ArrayList<>();
	private String tempDirectory = "";


	public StreamSorter(Comparator<String> sorter) {
		this.sorter = sorter;
	}


	/**
	 * Sets the temporary directory
	 *
	 * @param temp
	 */

	public void setTempDirectory(String temp) {
		tempDirectory = temp;
		File file = new File(tempDirectory);
		if (!file.exists() || !file.isDirectory()) {
			throw new IllegalArgumentException("Parameter director is not a directory or does not exist");
		}

	}

	/**
	 * Sets the chunck size for temprary files
	 *
	 * @param size
	 */

	public void setMaximumChunkSize(int size) {
		this.maxChunkSize = size;
	}


	/**
	 * Reads the input io stream and splits it into sorted chunks which are written to temporary files.
	 *
	 * @param in
	 * @throws IOException
	 */

	public void splitChunks(InputStream in) throws IOException {
		outputs.clear();

		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String line;
			int currChunkSize = 0;
			while ((line = br.readLine()) != null) {
				lines.add(line);
				currChunkSize += line.length() + 1;
				if (currChunkSize >= maxChunkSize) {
					currChunkSize = 0;
					lines.sort(sorter);
					File file = new File(tempDirectory + "temp" + System.currentTimeMillis());
					outputs.add(file);
					writeOut(lines, new FileOutputStream(file));
					lines.clear();
				}
			}
			//write out the remaining chunk
			lines.sort(sorter);
			File file = new File(tempDirectory + "temp" + System.currentTimeMillis());
			outputs.add(file);
			writeOut(lines, new FileOutputStream(file));
			lines.clear();
		} catch (IOException io) {
			throw io;
		}
	}


	/**
	 * Writes the list of lines out to the output stream, append new lines after each line.
	 *
	 * @param list
	 * @param os
	 * @throws IOException
	 */

	private void writeOut(List<String> list, OutputStream os) throws IOException {
		try (final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));) {
			for (String s : list) {
				writer.write(s);
				writer.write("\n");
			}
			writer.flush();
		} catch (IOException io) {
			throw io;
		}
	}

	/**
	 * Reads the temporary files created by splitChunks method and merges them in a sorted manner into the output stream.
	 *
	 * @param os
	 * @throws IOException
	 */

	public void mergeChunks(OutputStream os) throws IOException {
		Map<StringWrapper, BufferedReader> map = new HashMap();
		List<BufferedReader> readers = new ArrayList<>();
		ComparatorDelegate delegate = new ComparatorDelegate();
		try (final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
			for (int i = 0; i < outputs.size(); i++) {
				BufferedReader reader = new BufferedReader(new FileReader(outputs.get(i)));
				readers.add(reader);
				String line = reader.readLine();
				if (line != null) {
					map.put(new StringWrapper(line), readers.get(i));
				}
			}
			///continue to loop until no more lines lefts
			List<StringWrapper> sorted = new LinkedList<StringWrapper>(map.keySet());
			while (map.size() > 0) {
				sorted.sort(delegate);
				StringWrapper line = sorted.remove(0);
				writer.write(line.string);
				writer.write("\n");
				BufferedReader reader = map.remove(line);
				String nextLine = reader.readLine();
				if (nextLine != null) {
					StringWrapper sw = new StringWrapper(nextLine);
					map.put(sw, reader);
					sorted.add(sw);
				}
			}
		} catch (IOException io) {
			throw io;
		} finally {
			for (int i = 0; i < readers.size(); i++) {
				try {
					readers.get(i).close();
				} catch (Exception e) {
				}
			}
			for (File output : outputs) {
				output.delete();
			}
		}
	}

	/**
	 * Delegate comparator to be able to sort the StringWrapper class. Delegates its behavior to
	 * <p>
	 * the sorter field.
	 *
	 * @author Greg Cope
	 */

	private class ComparatorDelegate implements Comparator<StringWrapper> {
		@Override
		public int compare(StringWrapper o1, StringWrapper o2) {
			return sorter.compare(o1.string, o2.string);
		}
	}


	/**
	 * Class which is a wrapper class for a String. This is necessary for String duplicates, which may cause equals/hashCode
	 * <p>
	 * conflicts within the HashMap used in the file merge.
	 *
	 * @author Greg Cope
	 */

	private class StringWrapper implements Comparable<StringWrapper> {
		private final String string;

		public StringWrapper(String line) {
			this.string = line;
		}

		@Override
		public int compareTo(StringWrapper o) {
			return string.compareTo(o.string);
		}
	}
}

