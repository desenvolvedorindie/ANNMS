/*
 * Copyright (c) Welsiton Ferreira (wfcreations@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice, this
 *  list of conditions and the following disclaimer in the documentation and/or
 *  other materials provided with the distribution.
 *
 *  Neither the name of the WFCreation nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.wfcreations.annms.api.data.utils;

import java.util.List;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.type.IType;

public abstract class AttributeUtils {

	public static boolean checkDuplicateAttribute(Attribute[] attributes1, Attribute[] attributes2) {
		for (Attribute attr1 : attributes1)
			for (Attribute attr2 : attributes2)
				if (attr1.getID().equals(attr2.getID()))
					return true;
		return false;
	}

	public static boolean checkDuplicateAttribute(List<Attribute> attributes1, List<Attribute> attributes2) {
		for (Attribute attr1 : attributes1)
			for (Attribute attr2 : attributes2)
				if (attr1.getID().equals(attr2.getID()))
					return true;
		return false;
	}

	public static String[] getAttributesNames(Data data) {
		String[] attributeNames = new String[data.getAttributesNum()];
		for (int i = 0; i < data.getAttributesNum(); i++)
			attributeNames[i] = data.getAttributeAt(i).getID().getValue();
		return attributeNames;
	}

	public static boolean hasAttributeType(Data data, IType dataType) {
		for (int i = 0; i < data.getAttributesNum(); i++)
			if (data.getAttributeAt(i).getType().equals(dataType))
				return true;
		return false;
	}

	public static Attribute[] cloneAttributes(Data data) {
		Attribute[] attributes = new Attribute[data.getAttributesNum()];
		for (int i = 0; i < data.getAttributesNum(); i++)
			attributes[i] = data.getAttributeAt(i);
		return attributes;
	}
}