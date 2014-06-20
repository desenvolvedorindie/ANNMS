package br.com.wfcreations.annms.core.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;

public class Data {

	private String name;

	private Attribute[] attributes;

	private Pattern[] patterns = new Pattern[0];

	public Data(String name, Attribute[] attributes) {
		HashSet<String> names = new HashSet<String>();
		for (int i = 0; i < attributes.length; i++) {
			if (names.contains(attributes[i].getName())) {
				names.clear();
				throw new IllegalArgumentException("Attribute " + attributes[i].getName() + " are not unique!");
			}
			names.add(attributes[i].getName());
		}
		this.name = name;
		names.clear();
		this.attributes = attributes;
	}

	public Data(InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(in);
		this.name = (String) ois.readObject();
		this.attributes = (Attribute[]) ois.readObject();
		this.patterns = (Pattern[]) ois.readObject();
	}

	public void save(OutputStream out) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(this.name);
		oos.writeObject(this.attributes);
		oos.writeObject(this.patterns);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int numAttributes() {
		return attributes.length;
	}

	public boolean hasAttributeType(DataType dataType) {
		for (Attribute attribte : attributes)
			if (attribte.getType().equals(dataType))
				return true;
		return false;
	}

	public Attribute getAttribute(int index) {
		return attributes[index];
	}

	public Attribute getAttributeByName(String name) {
		for (Attribute attribute : attributes)
			if (attribute.getName().equals(name))
				return attribute;
		return null;
	}

	public void renameAttribute(int index, String newName) {
		for (int i = 0; i < numAttributes(); i++) {
			if (i == index)
				continue;
			if (attributes[i].getName().equals(newName))
				throw new IllegalArgumentException("Attribute name '" + name + "' already present at position #" + i);
		}
		Attribute newAtt = new Attribute(newName, attributes[index].getType(), attributes[index].isNotNull());
		this.attributes[index] = newAtt;
	}

	public int numPatterns() {
		return patterns.length;
	}

	public Pattern getPattern(int index) {
		return patterns[index];
	}

	public Pattern setPattern(int index, Pattern pattern) {
		validatePattern(pattern);
		return patterns[index] = pattern;
	}

	public void swap(int i, int j) {
		Pattern tmp = patterns[i];
		patterns[i] = patterns[j];
		patterns[j] = tmp;
	}

	public void add(Pattern pattern) throws IllegalArgumentException {
		validatePattern(pattern);
		Pattern[] newPatterns = new Pattern[patterns.length + 1];
		System.arraycopy(this.patterns, 0, newPatterns, 0, this.patterns.length);
		newPatterns[newPatterns.length - 1] = pattern;
		this.patterns = newPatterns;
	}

	public void remove(int index) {
		Pattern[] newPatterns = new Pattern[patterns.length - 1];
		System.arraycopy(this.patterns, 0, newPatterns, 0, index);
		System.arraycopy(this.patterns, index + 1, newPatterns, index, patterns.length - index - 1);
		this.patterns = newPatterns;
	}

	public void removeAll() {
		patterns = new Pattern[0];
	}

	public Data clone() {
		Data clone = new Data(this.name, this.attributes.clone());
		clone.patterns = this.patterns.clone();
		return clone;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (!Arrays.equals(attributes, other.attributes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(patterns, other.patterns))
			return false;
		return true;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('#');
		sb.append(getName());
		sb.append(String.format("%n"));
		for (int i = 0; i < numAttributes(); i++) {
			sb.append(attributes[i].getName());
			sb.append('(');
			sb.append(attributes[i].getType());
			sb.append(')');
			if (i != numAttributes() - 1) {
				sb.append(", ");
			}
		}
		sb.append(String.format("%n"));
		int ptrnMax = patterns.length - 1;
		int attrMax = attributes.length - 1;
		for (int i = 0; i < patterns.length; i++) {
			for (int j = 0; j < attributes.length; j++) {
				sb.append(patterns[i].getElement(j).getValue());
				if (j != ptrnMax) {
					sb.append(", ");
				}
			}
			if (i == attrMax) {
				return sb.toString();
			}
			sb.append(String.format("%n"));
		}
		return sb.toString();
	}

	private void validatePattern(Pattern pattern) throws IllegalArgumentException {
		if (pattern.size() != attributes.length)
			throw new IllegalArgumentException("Invalid values numbers");
		for (int i = 0; i < attributes.length; i++)
			if (!attributes[i].validate(pattern.getElement(i)))
				throw new IllegalArgumentException("Invalid value type for attribute " + attributes[i].getName());
	}

	public static Data mergeInstances(Data first, Data second) {
		if (first.numPatterns() != second.numPatterns()) {
			throw new IllegalArgumentException("Patterns must be of the same size");
		}
		Attribute[] attr = new Attribute[first.numAttributes() + second.numAttributes()];
		System.arraycopy(first.attributes, 0, attr, 0, first.numAttributes());
		System.arraycopy(second.attributes, 0, attr, first.numAttributes(), second.numAttributes());
		Pattern[] patterns = new Pattern[first.numPatterns()];
		IValue[] tmp;
		for (int i = 0; i < first.numPatterns(); i++) {
			tmp = new IValue[first.numAttributes() + second.numAttributes()];
			System.arraycopy(first.patterns[i].values, 0, tmp, 0, first.numAttributes());
			System.arraycopy(second.patterns[i].values, 0, tmp, first.numAttributes(), second.numAttributes());
			patterns[i] = new Pattern(tmp);
		}
		Data mergeData = new Data(first.getName() + "_" + second.getName(), attr);
		mergeData.patterns = patterns;
		return mergeData;
	}
}