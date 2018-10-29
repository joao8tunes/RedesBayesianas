package inference;

import java.util.ArrayList;
import java.util.Random;
import variable.ConditionalProbability;
import variable.Connection;
import variable.Evidence;
import variable.Result;
import variable.Variable;

public class Inference 
{

	public static Result exactMethod(ArrayList<Variable> network, ArrayList<Evidence> inputEvidences)
	{
		double probability = 1;
		String steps = "";
		ArrayList<ArrayList<Evidence>> enumeration = enumeration(network); 
		ArrayList<ArrayList<ConditionalProbability>> cpts = new ArrayList<ArrayList<ConditionalProbability>>();
		
		for (ArrayList<Evidence> ae : enumeration) {
			for (Evidence e : ae) {
				for (Evidence ie : inputEvidences) {
					if (e.getVariable().getIndexName().equals(ie.getVariable().getIndexName())) {
						e.setValue(ie.getValue());
					}
				}
			}
		}
		
		for (Variable v : network) {
			cpts.add(getCPTByEvidences(v, inputEvidences));
		}
		
		for (ArrayList<Evidence> ae : enumeration) {
			steps += "P(";
			for (int i = 0; i < ae.size(); ++i) {
				if (ae.get(i).hasEvidence()) {
					if (ae.get(i).getValue() == true) steps += "+" + ae.get(i).getVariable().getIndexName().toLowerCase();
					else steps += "-" + ae.get(i).getVariable().getIndexName().toLowerCase();
				}
				else steps += ae.get(i).getVariable().getIndexName();
				if (i+1 < ae.size()) steps += ", ";
			}
			steps += ") ";
		}
		steps += "= \n";
		for (ArrayList<ConditionalProbability> acp : cpts) {
			steps += "[";
			for (int i = 0; i < acp.size(); ++i) {
				steps += "P(";
				for (int j = 0; j < acp.get(i).getEvidences().size(); ++j) {
					if (acp.get(i).getEvidences().get(j).getValue() == true) steps += "+" + acp.get(i).getEvidences().get(j).getVariable().getIndexName().toLowerCase();
					else steps += "-" + acp.get(i).getEvidences().get(j).getVariable().getIndexName().toLowerCase();
					if (j+1 < acp.get(i).getEvidences().size()) steps += ",";
				}
				steps += ")";
				if (i+1 < acp.size()) steps += " + ";
			}
			steps += "] ";
		}
		steps += "= \n";
		for (ArrayList<ConditionalProbability> acp : cpts) {
			float sum = 0;
			steps += "(";
			
			for (int i = 0; i < acp.size(); ++i) {
				steps += acp.get(i).getProbability();
				sum += acp.get(i).getProbability();
				if (i+1 < acp.size()) steps += " + ";
			}
			
			probability *= sum;
			steps += ") ";
		}
		steps += "= " + probability;
		
		return new Result(steps, probability);
	}
	
	public static ArrayList<ConditionalProbability> getCPTByEvidences(Variable targetVariable, ArrayList<Evidence> inputEvidences)
	{
		ArrayList<ConditionalProbability> originalCPT = targetVariable.getConditionalProbabilities();
		ArrayList<ConditionalProbability> newCPT = new ArrayList<ConditionalProbability>(); 
		
		for (ConditionalProbability cp : originalCPT) {    //Based on original CPT.
			boolean equal = true;
			
			for (int i = 0; i < inputEvidences.size() && equal; ++i) {    //Remove (filter) all conditional probabilities that does not correspond to the input evidences (by inference).
				for (Evidence e : cp.getEvidences()) {
					if (e.getVariable().getIndexName().equals(inputEvidences.get(i).getVariable().getIndexName())) {
						if (e.getValue() != inputEvidences.get(i).getValue()) equal = false;
						break;
					}
				}
			}
			
			if (equal) newCPT.add(cp);
		}
		
		return newCPT;
	}
	
	public static ArrayList<ArrayList<Evidence>> enumeration(ArrayList<Variable> network)
	{
		ArrayList<ArrayList<Evidence>> conditionalProbabilities = new ArrayList<ArrayList<Evidence>>();
		
		for (Variable v : network) {
			ArrayList<Evidence> evidences = new ArrayList<Evidence>();
			evidences.add(new Evidence(v));
			
			for (Connection c : v.getInputConections()) {    //Its not a root variable.
				evidences.add(new Evidence(c.getSourceVariable()));
			}
			
			conditionalProbabilities.add(evidences);
		}
		
		return conditionalProbabilities;
	}
	
	public static float approximateMethod(ArrayList<Variable> network, ArrayList<Evidence> inputEvidences, int numberOfSamples)
	{
		float totalWeight = 0;
		int equals = 0;
		
		for (int i = 0; i < numberOfSamples; ++i) {
			ArrayList<Evidence> sample = new ArrayList<Evidence>();
			float currentWeight = 1;
			
			for (Variable v : network) {
				boolean itsEvidence = false;
				
				for (int j = 0; j < inputEvidences.size(); ++j) {    //The first one (index 0) is the inference question, and need be sampled for this method of conditional probability.
					if (inputEvidences.get(j).getVariable().getIndexName().equals(v.getIndexName())) {    //Its an evidence variable.
						sample.add(new Evidence(inputEvidences.get(j).getVariable(), inputEvidences.get(j).getValue()));
						currentWeight *= getConditionalProbability(inputEvidences.get(j), sample);
						itsEvidence = true;
					}
				}
				
				if (!itsEvidence) sample.add(new Evidence(v, (new Random()).nextBoolean()));
			}
			
			totalWeight += currentWeight;
			for (Evidence e : sample) {
				if (e.getVariable().getIndexName().equals(inputEvidences.get(0).getVariable().getIndexName())) {
					if (e.getValue() == inputEvidences.get(0).getValue()) ++equals;
					break;
				}
			}
		}
		
		return equals/totalWeight;
	}
	
	public static double getConditionalProbability(Evidence targetEvidence, ArrayList<Evidence> inputEvidences)
	{
		ArrayList<ConditionalProbability> conditionalProbabilities = new ArrayList<ConditionalProbability>(targetEvidence.getVariable().getConditionalProbabilities());
		
		for (ConditionalProbability cp : targetEvidence.getVariable().getConditionalProbabilities()) {
			for (Evidence e : cp.getEvidences()) {
				boolean removed = false;
				
				for (int i = 0; i < inputEvidences.size(); ++i) {
					if (e.getVariable().getIndexName().equals(inputEvidences.get(i).getVariable().getIndexName())) {
						if (e.getValue() != inputEvidences.get(i).getValue()) {
							conditionalProbabilities.remove(cp);
							removed = true;
							break;
						}
					}
				}
				
				if (removed) break;
			}
		}
		
		return conditionalProbabilities.get(0).getProbability();
	}
	
}