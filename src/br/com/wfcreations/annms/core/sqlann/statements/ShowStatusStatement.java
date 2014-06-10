package br.com.wfcreations.annms.core.sqlann.statements;

import br.com.wfcreations.annms.core.exception.ANNMSRequestExecutionException;
import br.com.wfcreations.annms.core.exception.ANNMSRequestValidationException;
import br.com.wfcreations.annms.core.sqlann.SQLANNStatement;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;

public class ShowStatusStatement implements SQLANNStatement {

	public final String query;

	public ShowStatusStatement(String query) {
		this.query = query;
	}

	@Override
	public void checkAccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate() throws ANNMSRequestValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultMessage execute() throws ANNMSRequestValidationException, ANNMSRequestExecutionException {
		return null;
	}

	@Override
	public String getId() {
		return "ShowStatusStatement";
	}

}
