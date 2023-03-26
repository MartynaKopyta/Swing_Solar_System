package solarSystem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class AnimationPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	private Image sunIm;
	private Image earthIm;
	private Image jupiterIm;
	private Image mercuryIm;
	private Image venusIm;
	private Image marsIm;
	private Image moonIm;
	private int speedDelta = 2;
	private double xcenter, ycenter, alpha, xearth, yearth;
	private boolean isPaused = false;
	private boolean withOrbits = false;
	private final int refreshRate = 100;
	private Thread animThread;
	private List<Planet> planets = new ArrayList<Planet>();
	
	public AnimationPanel() {
		setBackground(Color.BLACK);
	    setDoubleBuffered(true);
	    
	    try {
			sunIm = ImageIO.read(this.getClass().getResource("sun.jpg"));
			earthIm = ImageIO.read(this.getClass().getResource("earth.jpg"));
			jupiterIm = ImageIO.read(this.getClass().getResource("jupiter.jpg"));
			mercuryIm = ImageIO.read(this.getClass().getResource("mercury.jpg"));
			venusIm = ImageIO.read(this.getClass().getResource("venus.jpg"));
			marsIm = ImageIO.read(this.getClass().getResource("mars.jpg"));
			moonIm = ImageIO.read(this.getClass().getResource("moon.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	    
	    xcenter = 370;
	    ycenter = 350;
	    
	    Planet mercury = new Planet("mercury", 110, 11.8337 , 300, 290, mercuryIm.getScaledInstance(20, 20, ABORT));
	    Planet venus = new Planet("venus", 150, 1.8808, 300, 290, venusIm.getScaledInstance(50, 50, ABORT));
	    Planet earth = new Planet("earth", 215, 1, 120, 290, earthIm.getScaledInstance(50, 50, ABORT));
	    Planet mars = new Planet("mars", 280, 0.6152, 300, 290, marsIm.getScaledInstance(30, 30, ABORT));
	    Planet jupiter = new Planet("jupiter", 330, 0.2480, 300, 290, jupiterIm.getScaledInstance(50, 50, ABORT));
	    Planet moon = new Planet("moon", 40, 8, 300, 290, moonIm.getScaledInstance(10, 10, ABORT));
	    
	    planets.add(mercury);
	    planets.add(venus);
	    planets.add(earth);
	    planets.add(jupiter);
	    planets.add(mars);
	    planets.add(moon);
	    alpha = 0;
	    
	    animThread = new Thread(this);
	    animThread.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(sunIm.getScaledInstance(120, 120, ABORT), 310, 290, this); 
		
		for(Planet p: planets) {
			g2.drawImage(p.getIm(), (int)(p.getXpos() - (p.getIm().getHeight(getFocusCycleRootAncestor())/2)), (int)(p.getYpos() - (p.getIm().getHeight(getFocusCycleRootAncestor())/2)), this);
			
			if(withOrbits) {
				if(p.getName()=="moon") {
					g.drawOval((int)(xearth - p.getRadius()),(int)(yearth - p.getRadius()), (int)(p.getRadius()*2) , (int)(p.getRadius()*2));
				} else {
					g.drawOval((int)(xcenter - p.getRadius()),(int)( ycenter - p.getRadius()), (int)(p.getRadius()*2) , (int)(p.getRadius()*2));
				}
			}
			
		}
		
		
	}
	public void switchAnimationState()
    {
    	isPaused = !isPaused;
    }

	public void slowDownAnimation() {
		for(Planet p: planets) {
			if(p.getSpeed() - speedDelta > 0) {
			p.setSpeed(p.getSpeed() - speedDelta);
			}
		}
		
	}

	public void toggleOrbit() {
		withOrbits = !withOrbits;
		
	}

	public void speedUpAnimation() {
		
		for(Planet p: planets) {
			if(p.getSpeed() - speedDelta>0) {
			p.setSpeed(p.getSpeed() + speedDelta);
			}
		}
	}

	@Override
	public void run() {
		
		while(true) {
			
			if(!isPaused) {
				alpha += 1;
				movement();
				repaint();
			}
			
			try {
				Thread.sleep(refreshRate);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
			}
		}
		
	}

	private void movement() {
		double x = 0;
		double y = 0;
		double r = 0;
		double speed = 0;
		
		for(int i = 0; i < (planets.size() - 1) ;i++) {
			Planet p = planets.get(i);
			x = p.getXpos();
			y = p.getXpos();
			r = p.getRadius();
			speed = p.getSpeed();
			
	    	x = xcenter + r*Math.cos(alpha*speed*Math.PI/180);
	    	y = ycenter + r*Math.sin(alpha*speed*Math.PI/180);
	        
	        if (y > this.getWidth()) {
	            y = 0.0;
	            x = 0.0;
	        }
	        
	        p.setXpos(x);
	        p.setYpos(y);   
			
		}
			Planet moon = planets.get(5);
			double xmoon = moon.getXpos();
			double ymoon = moon.getYpos();
			double rmoon = moon.getRadius();
			double smoon = moon.getSpeed();
			xearth = planets.get(2).getXpos();
			yearth = planets.get(2).getYpos();
			
			xmoon = xearth + rmoon*Math.cos(alpha*smoon*Math.PI/180);
			ymoon = yearth + rmoon*Math.sin(alpha*smoon*Math.PI/180);
			
			moon.setXpos(xmoon);
			moon.setYpos(ymoon);
			
		
	}

	public void changeSpeed(double delta) {
		double speedDelta = delta;
		for(Planet p: planets) {
			p.setSpeed(p.getSpeed()*speedDelta);
		}
		
	}
	

}
