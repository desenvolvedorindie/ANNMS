package br.com.wfcreations.annms.core.neuralnetwork.learnrule;

import br.com.wfcreations.annms.core.data.IValue;

public interface IArtificialNeuralNetwork {
    
    public IValue[] run(IValue[] input);
    
    public boolean isReadyToRun();
    
}
