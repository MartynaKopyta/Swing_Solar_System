package solarSystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public JButton slowerButton = new JButton("Slower"); 
	public JButton fasterButton = new JButton("Faster"); 
	public JButton orbitButton = new JButton("Toggle Orbit"); 
	public JButton pauseButton = new JButton("Pause");
	public JTextField changeSpeed = new JTextField("Set speed");
	public AnimationPanel animArea;
	public JPanel panel;
	
	
	
	public AppFrame(){
	setTitle("Solar system");
	setSize(800, 800);
	setResizable(false);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	solarGUI();
	
	}
	
	public void solarGUI() {
		setLayout(new BorderLayout());
		
		animArea = new AnimationPanel();
		this.add(animArea, BorderLayout.CENTER);
		
		panel = new JPanel();
		this.add(panel,  BorderLayout.SOUTH);
		
		panel.add(fasterButton);
		panel.add(slowerButton);
		panel.add(orbitButton);
		panel.add(pauseButton);
		panel.add(changeSpeed);
		
		fasterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animArea.speedUpAnimation();
				
			}
		});
		
		slowerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animArea.slowDownAnimation();
				
			}
		});
		
		orbitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animArea.toggleOrbit();
				
			}
		});
		
		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animArea.switchAnimationState();
				
			}
		});
		
		changeSpeed.setPreferredSize(new Dimension(72, 24));
		
		changeSpeed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = changeSpeed.getText();
				double speedDelta = 1;
				
				try {
					speedDelta = Double.parseDouble(input);
					
					if(speedDelta <= 0) {
						JOptionPane.showMessageDialog(null, "Given speed must be grater than zero", "Error", JOptionPane.ERROR_MESSAGE);
					}
					animArea.changeSpeed(speedDelta);
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Given speed must be a number", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
			
		});
		
	}
}
