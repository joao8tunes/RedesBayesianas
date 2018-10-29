package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import inference.Inference;
import variable.ConditionalProbability;
import variable.Connection;
import variable.Evidence;
import variable.Result;
import variable.Variable;

public class MainWindow extends JFrame 
{

	private static final long serialVersionUID = -1810393566512302281L;
	private JPanel contentPane;
	private JTextArea txtrTables;
	private ArrayList<Variable> network;

	public MainWindow() 
	{
		setTitle("Bayesian Networks - Tuberculosis Problem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 511);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Monospaced", Font.BOLD, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		
		JPanel networkPanel = new JPanel();
		tabbedPane.addTab("Network", null, networkPanel, null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_networkPanel = new GroupLayout(networkPanel);
		gl_networkPanel.setHorizontalGroup(
			gl_networkPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
		);
		gl_networkPanel.setVerticalGroup(
			gl_networkPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		scrollPane_3.setViewportView(panel);
		
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("/images/network.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		networkPanel.setLayout(gl_networkPanel);
		
		JPanel tablesPanel = new JPanel();
		tabbedPane.addTab("Tables", null, tablesPanel, null);
		
		JScrollPane scrollPaneTables = new JScrollPane();
		GroupLayout gl_tablesPanel = new GroupLayout(tablesPanel);
		gl_tablesPanel.setHorizontalGroup(
			gl_tablesPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPaneTables, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
		);
		gl_tablesPanel.setVerticalGroup(
			gl_tablesPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPaneTables, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
		);
		
		txtrTables = new JTextArea();
		txtrTables.setEditable(false);
		txtrTables.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPaneTables.setViewportView(txtrTables);
		tablesPanel.setLayout(gl_tablesPanel);
		
		JPanel inferencePanel = new JPanel();
		tabbedPane.addTab("Inference", null, inferencePanel, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Monospaced", Font.BOLD, 12));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Monospaced", Font.BOLD, 12));
		GroupLayout gl_inferencePanel = new GroupLayout(inferencePanel);
		gl_inferencePanel.setHorizontalGroup(
			gl_inferencePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblOutput)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
				.addGroup(gl_inferencePanel.createSequentialGroup()
					.addComponent(lblInput)
					.addContainerGap())
				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
		);
		gl_inferencePanel.setVerticalGroup(
			gl_inferencePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inferencePanel.createSequentialGroup()
					.addComponent(lblOutput)
					.addGap(6)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblInput)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
		);
		
		JTextArea textArea_Output = new JTextArea();
		textArea_Output.setEditable(false);
		textArea_Output.setCaretColor(Color.GREEN);
		textArea_Output.setForeground(Color.GREEN);
		textArea_Output.setBackground(Color.BLACK);
		textArea_Output.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane_1.setViewportView(textArea_Output);
		
		JTextArea textArea_Input = new JTextArea();
		textArea_Input.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {    //Enter.
					String[] inferences = textArea_Input.getText().trim().split(";"), inputEvidences;
					
					textArea_Output.setText("");
					for (int i = 0; i < inferences.length; ++i) {
						ArrayList<Evidence> evidences = new ArrayList<Evidence>();
						inferences[i] = inferences[i].trim().replaceAll(" ", "");
						if (inferences[i].startsWith("P")) {    //Exact probability (Enumeration).
							inputEvidences = inferences[i].replace("P(", "").replace(")", "").split(","); 
							for (int j = 0; j < inputEvidences.length; ++j) {
								if (inputEvidences[j].startsWith("+")) {
									boolean equal = false;
									inputEvidences[j] = inputEvidences[j].replace("+", "");
									
									if (searchEvidence(evidences, inputEvidences[j].toUpperCase())) {    //Repeated evidence are not allowed.
										JOptionPane.showMessageDialog(null, "Repeated evidences are not allowed!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									for (Variable v : network) {
										if (v.getIndexName().toLowerCase().equals(inputEvidences[j])) {
											evidences.add(new Evidence(v, true));
											equal = true;
										}
									}
									
									if (!equal) {
										JOptionPane.showMessageDialog(null, "An of the evidences does not correspond to a valid variable!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
								}
								else if (inputEvidences[j].startsWith("-")) {
									boolean equal = false;
									inputEvidences[j] = inputEvidences[j].replace("-", "");
									
									if (searchEvidence(evidences, inputEvidences[j].toUpperCase())) {    //Repeated evidence are not allowed.
										JOptionPane.showMessageDialog(null, "Repeated evidences are not allowed!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									for (Variable v : network) {
										if (v.getIndexName().toLowerCase().equals(inputEvidences[j])) {
											evidences.add(new Evidence(v, false));
											equal = true;
										}
									}
									
									if (!equal) {
										JOptionPane.showMessageDialog(null, "An of the evidences does not correspond to a valid variable!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "An of the inference questions its not correct!", "Error", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
							
							if (evidences.isEmpty()) {
								JOptionPane.showMessageDialog(null, "An of the inference questions doesnt have any evidences!", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							else {
								Result p_exact = Inference.exactMethod(network, evidences);
								evidences.get(0).setValue(evidences.get(0).getValue() ? false : true);    //Toggle.
								Result p_exact_toggle = Inference.exactMethod(network, evidences);
								textArea_Output.setText(textArea_Output.getText() + p_exact.getSteps() + "\n >>> " + inferences[i] + " = " + (p_exact.getProbability()*100)/(p_exact.getProbability()+p_exact_toggle.getProbability()) + "\n\n");
							}
						}
						else if (inferences[i].startsWith("~P")) {    //Approximate probability (Likelihood Weighting).
							int numberOfSamples; 
							String parts[] = inferences[i].split("_");
							
							if (parts.length != 2) {
								JOptionPane.showMessageDialog(null, "Malformed inference (invalid): approximate probability!", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							try {
								numberOfSamples = Integer.valueOf(parts[1]);    //P(...)_number.
								if (numberOfSamples <= 0) {
									JOptionPane.showMessageDialog(null, "Invalid number of samples!", "Error", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
							catch (NumberFormatException exc) {
								JOptionPane.showMessageDialog(null, "Invalid number of samples!", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							inputEvidences = parts[0].replace("~P(", "").replace(")", "").split(",");
							for (int j = 0; j < inputEvidences.length; ++j) {
								if (inputEvidences[j].startsWith("+")) {
									boolean equal = false;
									inputEvidences[j] = inputEvidences[j].replace("+", "");
									
									if (searchEvidence(evidences, inputEvidences[j].toUpperCase())) {    //Repeated evidence are not allowed.
										JOptionPane.showMessageDialog(null, "Repeated evidences are not allowed!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									for (Variable v : network) {
										if (v.getIndexName().toLowerCase().equals(inputEvidences[j])) {
											evidences.add(new Evidence(v, true));
											equal = true;
										}
									}
									
									if (!equal) {
										System.out.println("AQUI => " + inputEvidences[j]);
										JOptionPane.showMessageDialog(null, "An of the evidences does not correspond to a valid variable!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
								}
								else if (inputEvidences[j].startsWith("-")) {
									boolean equal = false;
									inputEvidences[j] = inputEvidences[j].replace("-", "");
									
									if (searchEvidence(evidences, inputEvidences[j].toUpperCase())) {    //Repeated evidence are not allowed.
										JOptionPane.showMessageDialog(null, "Repeated evidences are not allowed!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									for (Variable v : network) {
										if (v.getIndexName().toLowerCase().equals(inputEvidences[j])) {
											evidences.add(new Evidence(v, false));
											equal = true;
										}
									}
									
									if (!equal) {
										JOptionPane.showMessageDialog(null, "An of the evidences does not correspond to a valid variable!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "An of the inference questions its not correct!", "Error", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
							
							if (evidences.isEmpty()) {
								JOptionPane.showMessageDialog(null, "An of the inference questions doesnt have any evidences!", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							else {
								double p_aprox = Inference.approximateMethod(network, evidences, numberOfSamples);
								evidences.get(0).setValue(evidences.get(0).getValue() ? false : true);    //Toggle.
								double p_aprox_toggle = Inference.approximateMethod(network, evidences, numberOfSamples);
								textArea_Output.setText(textArea_Output.getText() + " >>> " + inferences[i] + " = " + (p_aprox_toggle*100)/(p_aprox+p_aprox_toggle) + "\n\n");
							}							
						}
						else {
							JOptionPane.showMessageDialog(null, "Malformed inference (invalid)!", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					
					textArea_Input.setText("");
					textArea_Input.setCaretPosition(0);
					textArea_Output.setCaretPosition(0);
				}
			}
		});
		textArea_Input.setCaretColor(Color.GREEN);
		textArea_Input.setForeground(Color.GREEN);
		textArea_Input.setBackground(Color.BLACK);
		textArea_Input.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane_2.setViewportView(textArea_Input);
		inferencePanel.setLayout(gl_inferencePanel);
		contentPane.setLayout(gl_contentPane);
		
		createNetwork();
		printTables();
		tabbedPane.setSelectedIndex(2);
	}
	
	private void createNetwork()
	{
		network = new ArrayList<Variable>();
		//Creating nodes (variables):
		network.add(new Variable("Visit to Asia", "A"));
		network.add(new Variable("Tuberculosis", "T"));
		network.add(new Variable("Either Tuberculosis or Lung Cancer", "E"));
		network.add(new Variable("Lung Cancer", "L"));
		network.add(new Variable("Positive X-Ray", "X"));
		network.add(new Variable("Smoker", "S"));
		network.add(new Variable("Bronchitis", "B"));
		network.add(new Variable("Dispinea", "D"));
		
		Variable variable;    //Auxiliar.
		
		//Defining connections (dependencies) between the variables:
		variable = getVariableByIndexName("A");
		variable.getOutputConections().add(new Connection(variable, getVariableByIndexName("T")));
		variable = getVariableByIndexName("T");
		variable.getInputConections().add(new Connection(getVariableByIndexName("A"), variable));
		variable.getOutputConections().add(new Connection(variable, getVariableByIndexName("E")));
		variable = getVariableByIndexName("X");
		variable.getInputConections().add(new Connection(getVariableByIndexName("E"), variable));
		variable = getVariableByIndexName("D");
		variable.getInputConections().add(new Connection(getVariableByIndexName("E"), variable));
		variable.getInputConections().add(new Connection(getVariableByIndexName("B"), variable));
		variable = getVariableByIndexName("L");
		variable.getInputConections().add(new Connection(getVariableByIndexName("S"), variable));
		variable.getOutputConections().add(new Connection(variable, getVariableByIndexName("E")));
		variable = getVariableByIndexName("B");
		variable.getInputConections().add(new Connection(getVariableByIndexName("S"), variable));
		variable.getOutputConections().add(new Connection(variable, getVariableByIndexName("D")));
		variable = getVariableByIndexName("E");
		variable.getInputConections().add(new Connection(getVariableByIndexName("T"), variable));
		variable.getInputConections().add(new Connection(getVariableByIndexName("L"), variable));
		variable.getOutputConections().add(new Connection(variable, getVariableByIndexName("X")));
		variable.getOutputConections().add(new Connection(variable, getVariableByIndexName("D")));
		//Defining probabilities (conditional probabilities) between the variables (the Conditional Probabilities Tables).
		variable = getVariableByIndexName("A");
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true))), 0.01));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false))), 1-0.01));
		variable = getVariableByIndexName("T");
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("A"), true))), 0.05));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("A"), false))), 0.01));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("A"), true))), 1-0.05));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("A"), false))), 1-0.01));		
		variable = getVariableByIndexName("E");
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("L"), true), new Evidence(getVariableByIndexName("T"), true))), 1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("L"), true), new Evidence(getVariableByIndexName("T"), false))), 1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("L"), false), new Evidence(getVariableByIndexName("T"), true))), 1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("L"), false), new Evidence(getVariableByIndexName("T"), false))), 0));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("L"), true), new Evidence(getVariableByIndexName("T"), true))), 1-1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("L"), true), new Evidence(getVariableByIndexName("T"), false))), 1-1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("L"), false), new Evidence(getVariableByIndexName("T"), true))), 1-1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("L"), false), new Evidence(getVariableByIndexName("T"), false))), 1-0));
		variable = getVariableByIndexName("X");
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("E"), true))), 0.98));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("E"), false))), 0.05));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("E"), true))), 1-0.98));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("E"), false))), 1-0.05));
		variable = getVariableByIndexName("L");
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("S"), true))), 0.1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("S"), false))), 0.01));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("S"), true))), 1-0.1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("S"), false))), 1-0.01));
		variable = getVariableByIndexName("S");
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true))), 0.5));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false))), 0.5));
		variable = getVariableByIndexName("B");
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("S"), true))), 0.6));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("S"), false))), 0.3));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("S"), true))), 1-0.6));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("S"), false))), 1-0.3));
		variable = getVariableByIndexName("D");
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("E"), true), new Evidence(getVariableByIndexName("B"), true))), 0.9));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("E"), true), new Evidence(getVariableByIndexName("B"), false))), 0.7));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("E"), false), new Evidence(getVariableByIndexName("B"), true))), 0.8));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, true), new Evidence(getVariableByIndexName("E"), false), new Evidence(getVariableByIndexName("B"), false))), 0.1));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("E"), true), new Evidence(getVariableByIndexName("B"), true))), 1-0.9));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("E"), true), new Evidence(getVariableByIndexName("B"), false))), 1-0.7));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("E"), false), new Evidence(getVariableByIndexName("B"), true))), 1-0.8));
		variable.getConditionalProbabilities().add(new ConditionalProbability(new ArrayList<Evidence>(Arrays.asList(new Evidence(variable, false), new Evidence(getVariableByIndexName("E"), false), new Evidence(getVariableByIndexName("B"), false))), 1-0.1));
	}
	
	private void printTables()
	{
		txtrTables.setText("");
		
		for (int v = 0; v < network.size(); ++v) {
			ArrayList<String> cpt = new ArrayList<String>();
			int length;
			
			for (ConditionalProbability cp : network.get(v).getConditionalProbabilities()) {
				String newRow = "| ";
				
				for (Evidence e : cp.getEvidences()) {
					if (e.getValue() == true) newRow += "+" + e.getVariable().getIndexName().toLowerCase() + " ";
					else newRow += "-" + e.getVariable().getIndexName().toLowerCase() + " ";
				}
				newRow += "| " + rightpad(String.valueOf(round(cp.getProbability(), 4)), 5) + "  |";
				cpt.add(newRow);
			}
			
			txtrTables.setText(txtrTables.getText() + "/");
			length = cpt.get(0).length()-2;
			for (int i = 0; i < length; ++i) {
				txtrTables.setText(txtrTables.getText() + "=");
			}
			txtrTables.setText(txtrTables.getText() + "\\\n| " + network.get(v).getIndexName());
			for (int i = 0; i < length-11; ++i) {
				txtrTables.setText(txtrTables.getText() + " ");
			}
			txtrTables.setText(txtrTables.getText() + "| P(" + network.get(v).getIndexName() + ")   |\n|");
			for (int i = 0; i < length; ++i) {
				txtrTables.setText(txtrTables.getText() + "=");
			}
			txtrTables.setText(txtrTables.getText() + "|\n");
			
			for (String row : cpt) {
				txtrTables.setText(txtrTables.getText() + row  + "\n");
			}
			txtrTables.setText(txtrTables.getText() + "\\");
			for (int i = 0; i < length; ++i) {
				txtrTables.setText(txtrTables.getText() + "=");
			}
			txtrTables.setText(txtrTables.getText() + "/");
			if (v+1 < network.size()) txtrTables.setText(txtrTables.getText() + "\n\n");
		}
		
		txtrTables.setCaretPosition(0);
	}
	
	public Variable getVariableByIndexName(String indexName)
	{
		for (Variable v : network) {
			if (v.getIndexName() == indexName) return v;
		}
		
		return null;
	}
	
	public static double round(double value, int scale) 
	{
	    return (new BigDecimal(value)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	private String rightpad(String text, int length) 
	{
	    return String.format("%-" + length + "." + length + "s", text);
	}
	
	private boolean searchEvidence(ArrayList<Evidence> evidences, String indexName)
	{
		for (Evidence e : evidences) {
			if (e.getVariable().getIndexName().equals(indexName)) return true;
		}
		return false;
	}
	
//	public static void main(String[] args) {
//	EventQueue.invokeLater(new Runnable() {
//		public void run() {
//			try {
//				MainWindow frame = new MainWindow();
//				frame.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	});
//}

}