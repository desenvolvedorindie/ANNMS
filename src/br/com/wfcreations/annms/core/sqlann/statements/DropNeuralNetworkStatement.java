package br.com.wfcreations.annms.core.sqlann.statements;

import br.com.wfcreations.annms.core.sqlann.IStatement;

public class DropNeuralNetworkStatement implements IStatement {

	public final String[] neuralnetworkList;

	public final boolean ifExists;

	public final String query;

	public DropNeuralNetworkStatement(String[] neuralnetworkList, boolean ifExists, String query) {
		this.neuralnetworkList = neuralnetworkList;
		this.ifExists = ifExists;
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
		return "DropNeuralNetworkStatement";
	}
}