package br.com.wfcreations.annms.core.sqlann.statements;

import br.com.wfcreations.annms.core.sqlann.IStatement;

public class ShowDataStatusStatement implements IStatement {

	public final String name;

	public ShowDataStatusStatement(String name) {
		this.name = name;
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
		// TODO Auto-generated method stub
		return "ShowDataStatusStatement";
	}

}
