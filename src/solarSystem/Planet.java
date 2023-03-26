package solarSystem;
import java.awt.Image;


public class Planet {

	private String name;
	private double radius, speed, xpos, ypos;
	private Image im;
	
	public Planet(String name, double radius, double speed, double xpos, double ypos, Image im) {
		this.name = name;
		this.radius = radius;
		this.speed = speed;
		this.xpos = xpos;
		this.ypos = ypos;
		this.im = im;
	}

	public Image getIm() {
		return im;
	}

	public void setIm(Image im) {
		this.im = im;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getXpos() {
		return xpos;
	}

	public void setXpos(double xpos) {
		this.xpos = xpos;
	}

	public double getYpos() {
		return ypos;
	}

	public void setYpos(double ypos) {
		this.ypos = ypos;
	}
	

}
