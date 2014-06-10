package br.com.wfcreations.annms.core.sqlann.statements;

import br.com.wfcreations.annms.core.sqlann.IStatement;

public class ShowStatusStatement implements IStatement {

	public final String query;

	public ShowStatusStatement(String query) {
		this.query = query;
	}

	@Override
	public void checkAccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getId() {
		return "ShowStatusStatement";
	}

}
