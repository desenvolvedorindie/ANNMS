/*
 * Copyright (c) 2013, Welsiton Ferreira (wfcreations@gmail.com)
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
package br.com.wfcreations.annms.core.data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import br.com.wfcreations.annms.core.data.values.BooleanValue;
import br.com.wfcreations.annms.core.data.values.IntegerValue;
import br.com.wfcreations.annms.core.data.values.RealValue;
import br.com.wfcreations.annms.core.data.values.StringValue;

public interface DataType extends Serializable {

	public boolean validate(IValue value);

	public enum Native implements DataType {
		BOOLEAN(BooleanValue.class), INTEGER(IntegerValue.class), REAL(RealValue.class), STRING(StringValue.class);

		private Class<?> representation;

		Native(Class<?> representation) {
			this.representation = representation;
		}

		@Override
		public boolean validate(IValue value) {
			return (value.getClass().equals(representation));
		}
	};

	public class DateDataType implements DataType {

		private static final long serialVersionUID = 1L;

		private final String dateFormat;

		public DateDataType(String dateFormat) {
			this.dateFormat = dateFormat;
		}

		public String getDateFormat() {
			return dateFormat;
		}

		public static DateDataType create(String dateFormat) {
			return new DateDataType(dateFormat);
		}

		@Override
		public boolean validate(IValue value) {
			if (!value.getClass().equals(StringValue.class)) {
				System.out.println("lol");
				return false;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			try {
				sdf.parse((String) value.getValue());
				return true;
			} catch (ParseException e) {
				return false;
			}
		}

		@Override
		public String toString() {
			return "DATE(" + dateFormat + ")";
		}
	}

	public class ListDataType implements DataType {

		private static final long serialVersionUID = 1L;

		private final String[] listValues;

		public ListDataType(String[] listValues) {
			this.listValues = listValues;
		}

		public String[] getListValues() {
			return listValues;
		};

		@Override
		public boolean validate(IValue value) {
			return value.getClass().equals(StringValue.class) && Arrays.asList(listValues).contains(value.getValue());
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ListDataType other = (ListDataType) obj;
			if (!Arrays.equals(listValues, other.listValues))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return Arrays.toString(listValues).replace('[', '{').replace(']', '}');
		}
	}
}