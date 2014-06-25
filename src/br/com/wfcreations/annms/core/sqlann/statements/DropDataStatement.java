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
package br.com.wfcreations.annms.core.sqlann.statements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.core.config.Schema;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;
import br.com.wfcreations.annms.core.exception.ANNMSRequestExecutionException;
import br.com.wfcreations.annms.core.exception.ANNMSRequestValidationException;
import br.com.wfcreations.annms.core.sqlann.SQLANNStatement;
import br.com.wfcreations.annms.core.transport.message.DataDropResultMessage;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;

public class DropDataStatement implements SQLANNStatement {

	private static final Logger LOGGER = LoggerFactory.getLogger(DropDataStatement.class);

	public final String[] dataList;

	public final boolean ifExists;

	public final String query;

	public DropDataStatement(String[] dataList, boolean ifExists, String query) {
		this.dataList = dataList;
		this.ifExists = ifExists;
		this.query = query;
	}

	@Override
	public void checkAccess() {
	}

	@Override
	public void validate() throws ANNMSRequestValidationException {
		if (checkDuplicate(this.dataList))
			throw new ANNMSRequestValidationException(ANNMSExceptionCode.STORAGE, "data list has duplicated data");
	}

	@Override
	public ResultMessage execute() throws ANNMSRequestValidationException, ANNMSRequestExecutionException {
		List<String> nonExistent = new ArrayList<String>();
		List<String> removed = new ArrayList<String>();
		for (String dataName : dataList)
			if (Schema.instance.getDataInstance(dataName) != null) {
				Schema.instance.removeDataInstance(dataName);
				removed.add(dataName);
			} else
				nonExistent.add(dataName);

		if (removed.size() > 0)
			LOGGER.info("Dropped data list: {}", Arrays.toString(new String[removed.size()]));
		if (nonExistent.size() > 0 && !ifExists)
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.STORAGE, String.format("Non existent data: %s", Arrays.toString(nonExistent.toArray(new String[nonExistent.size()]))));
		return new DataDropResultMessage(removed.size());
	}

	private static boolean checkDuplicate(String[] strings) {
		Set<String> tempSet = new HashSet<String>();
		for (String str : strings)
			if (!tempSet.add(str))
				return true;
		return false;
	}
}