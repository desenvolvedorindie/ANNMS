package br.com.wfcreations.annms.core.neuralnetwork.learnrule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface LearnRule {
    public String id();
    public Class<? extends IArtificialNeuralNetwork>[] artificialNeuralNetworks();
}
