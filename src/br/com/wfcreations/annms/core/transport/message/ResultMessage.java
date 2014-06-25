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
package br.com.wfcreations.annms.core.transport.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wfcreations.annms.core.exception.ANNMSException;

public abstract class ResultMessage {

	public abstract void toThriftResult(List<Object> resultMessages);

	private static void mapResultMessage(List<Object> dataList, ResultMessage[] resultMessages) {
		for (ResultMessage resultMessage : resultMessages)
			resultMessage.toThriftResult(dataList);
	}

	public static String mapToThrift(ResultMessage[] resultMessages) {
		List<Object> dataList = new ArrayList<>();
		if (resultMessages != null)
			mapResultMessage(dataList, resultMessages);
		return process(dataList);
	}

	public static String mapToThriftWithError(ResultMessage[] resultMessages, ANNMSException e) {
		List<Object> dataList = new ArrayList<>();
		if (resultMessages != null)
			mapResultMessage(dataList, resultMessages);
		Map<String, String> error = new HashMap<String, String>();
		error.put("ERROR", e.getMessage());
		error.put("CODE", String.valueOf(e.code().getValue()));
		dataList.add(error);
		return process(dataList);
	}

	private static String process(List<Object> dataList) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(dataList);
		} catch (JsonProcessingException e1) {
			return "";
		}
	}
}