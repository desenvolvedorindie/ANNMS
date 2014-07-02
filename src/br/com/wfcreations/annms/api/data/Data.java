package br.com.wfcreations.annms.api.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.wfcreations.annms.api.data.select.Select;

public class Data {

	protected String name;

	protected List<Attribute> attributes = new ArrayList<>();

	protected List<Pattern> patterns = new ArrayList<>();

	public Data(String name, Attribute[] attributes) {
		if (Attribute.checkDuplicate(attributes))
			throw new IllegalArgumentException("Attributes are not unique!");
		this.name = name;
		for (Attribute attr : attributes)
			this.attributes.add(attr);
	}

	@SuppressWarnings("unchecked")
	public Data(InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(in);
		this.name = (String) ois.readObject();
		this.attributes = (List<Attribute>) ois.readObject();
		this.patterns = (List<Pattern>) ois.readObject();
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

	public int getAttributesNum() {
		return attributes.size();
	}

	public String[] getAttributesNames() {
		String[] attributeNames = new String[attributes.size()];
		int i = 0;
		for (Attribute attr : attributes) {
			attributeNames[i++] = attr.getName();
		}
		return attributeNames;
	}

	public boolean hasAttributeType(DataType dataType) {
		for (Attribute attribte : attributes)
			if (attribte.getType().equals(dataType))
				return true;
		return false;
	}

	public Attribute getAttributeAt(int index) {
		return attributes.get(index);
	}

	public Attribute getAttributeByName(String name) {
		for (Attribute attribute : attributes)
			if (attribute.getName().equals(name))
				return attribute;
		return null;
	}

	public void renameAttribute(int index, String newName) {
		for (int i = 0; i < getAttributesNum(); i++) {
			if (i == index)
				continue;
			if (attributes.get(i).getName().equals(newName))
				throw new IllegalArgumentException("Attribute name '" + name + "' already present at position #" + i);
		}
		this.attributes.set(index, new Attribute(newName, attributes.get(index).getType(), attributes.get(index).isNotNull()));
	}

	public int indexOfAttribute(String name) {
		for (int i = 0; i < attributes.size(); i++)
			if (attributes.get(i).getName().equals(name))
				return i;
		return -1;
	}

	public int getPatternsNum() {
		return patterns.size();
	}

	public int indexOfPattern(Pattern pattern) {
		return patterns.indexOf(pattern);
	}

	public Pattern getPatternAt(int index) {
		return patterns.get(index);
	}

	public Pattern setPatternAt(int index, Pattern pattern) {
		validatePattern(pattern);
		return patterns.set(index, pattern);
	}

	public void swap(int i, int j) {
		Collections.swap(patterns, i, j);
	}

	public void add(Pattern pattern) throws IllegalArgumentException {
		validatePattern(pattern);
		patterns.add(pattern);
	}

	public void addPatternAt(int index, Pattern pattern) {
		patterns.add(index, pattern);
	}

	public void removePatternAt(int index) {
		patterns.remove(index);
	}

	public void removeAllPatterns() {
		patterns.clear();
	}

	public Data slice(int from, int to) {
		List<Attribute> newAttributes = attributes.subList(from, to);
		Data data = new Data(name, newAttributes.toArray(new Attribute[newAttributes.size()]));
		List<Pattern> newPatterns = new ArrayList<>(patterns.size());
		for (Pattern pattern : patterns)
			newPatterns.add(new Pattern(Arrays.copyOfRange(pattern.values, from, to)));
		data.patterns = newPatterns;
		return data;
	}

	public Data slice(int index) {
		return slice(index, index + 1);
	}

	public Data fetch(Select where) {
		Data data = null;
		if (where == null)
			data = this.clone();
		else {
			Data data2 = null;
			int index;

			String[] key = where.columns().keySet().toArray(new String[where.columns().size()]);
			String[] value = where.columns().values().toArray(new String[where.columns().size()]);

			index = this.indexOfAttribute(key[0]);
			if (index < 0)
				throw new IllegalArgumentException(String.format("Attribute % not found", key[0]));

			data = this.slice(index);
			data.renameAttribute(0, value[0]);

			for (int i = 1; i < where.columns().size(); i++) {
				index = this.indexOfAttribute(key[i]);
				if (index < 0)
					throw new IllegalArgumentException(String.format("Attribute %s not found", key[i]));

				data2 = this.slice(index);
				data2.renameAttribute(0, value[i]);
				data = merge(data, data2);

			}
			data.setName(this.name);
		}
		return data;
	}

	@Override
	public Data clone() {
		Data clone = new Data(this.name, new ArrayList<>(this.attributes).toArray(new Attribute[this.attributes.size()]));
		clone.patterns = new ArrayList<>(patterns);
		return clone;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('#');
		sb.append(getName());
		sb.append(String.format("%n"));
		for (int i = 0; i < getAttributesNum(); i++) {
			sb.append(attributes.get(i).getName());
			sb.append('(');
			sb.append(attributes.get(i).getType());
			sb.append(')');
			if (i != getAttributesNum() - 1) {
				sb.append(", ");
			}
		}
		sb.append(String.format("%n"));
		int ptrnMax = patterns.size() - 1;
		int attrMax = attributes.size() - 1;
		for (int i = 0; i < patterns.size(); i++) {
			for (int j = 0; j < attributes.size(); j++) {
				sb.append(patterns.get(i).getValueAt(j).getValue().toString());
				if (j != attrMax) {
					sb.append(", ");
				}
			}
			if (i != ptrnMax) {
				sb.append(String.format("%n"));
			}
		}
		return sb.toString();
	}

	protected void validatePattern(Pattern pattern) throws IllegalArgumentException {
		if (pattern.getValuesNum() != attributes.size())
			throw new IllegalArgumentException("Invalid values numbers");
		for (int i = 0; i < attributes.size(); i++)
			if (!attributes.get(i).validate(pattern.getValueAt(i)))
				throw new IllegalArgumentException("Invalid value type for attribute " + attributes.get(i).getName());
	}

	public static Data merge(Data first, Data second) {
		if (first.getPatternsNum() != second.getPatternsNum())
			throw new IllegalArgumentException("Patterns must be of the same size");
		if (checkDuplicateAttribute(first.attributes, second.attributes))
			throw new IllegalArgumentException("Data 1 has the same attribute of Data 2");

		List<Attribute> attr = new ArrayList<>(first.getAttributesNum() + second.getAttributesNum());
		attr.addAll(first.attributes);
		attr.addAll(second.attributes);

		List<Pattern> patterns = new ArrayList<>(first.getPatternsNum());

		IValue[] tmp;
		for (int i = 0; i < first.getPatternsNum(); i++) {
			tmp = new IValue[first.getAttributesNum() + second.getAttributesNum()];
			System.arraycopy(first.patterns.get(i).values, 0, tmp, 0, first.getAttributesNum());
			System.arraycopy(second.patterns.get(i).values, 0, tmp, first.getAttributesNum(), second.getAttributesNum());
			patterns.add(new Pattern(tmp));
		}

		Data mergeData = new Data(first.getName() + "_" + second.getName(), attr.toArray(new Attribute[attr.size()]));
		mergeData.patterns = patterns;
		return mergeData;
	}

	private static boolean checkDuplicateAttribute(List<Attribute> attributes1, List<Attribute> attributes2) {
		for (Attribute attr1 : attributes1) {
			for (Attribute attr2 : attributes2)
				if (attr1.getName().equals(attr2.getName()))
					return true;
		}
		return false;
	}
}