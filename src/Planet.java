
public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	// some calculation methods
	public double calcDistance(Planet p) {
		double xN = p.xxPos - xxPos;
		double yN = p.yyPos - yyPos;
		double dis = Math.sqrt(xN * xN + yN * yN);
		return dis;
	}

	public double calcForceExertedBy(Planet p) {
		double force;
		force = Math.abs(G * mass * p.mass) / (calcDistance(p) * calcDistance(p));
		return force;
	}

	// calcForceExertedByX and calcForceExertedByY

	public double calcForceExertedByX(Planet p) {
		double xN = p.xxPos - xxPos;
		// double yN = p.yyPos-yyPos;
		double forceX = (calcForceExertedBy(p) * xN) / calcDistance(p);
		return forceX;
	}

	public double calcForceExertedByY(Planet p) {
		double yN = p.yyPos - yyPos;
		double forceY = (calcForceExertedBy(p) * yN) / calcDistance(p);
		return forceY;

	}

	// calcNetForceExertedByX and calcNetForceExertedByY
	public double calcNetForceExertedByX(Planet[] p) {
		double Nforce = 0;
		boolean test;
		for (int i = 0; i < p.length; i++) {
			test = this.equals(p[i]);
			if (!test) {
				Nforce += this.calcForceExertedByX(p[i]);
			}
		}
		return Nforce;
	}

	public double calcNetForceExertedByY(Planet[] p) {
		double Nforce = 0;
		boolean test;
		for (int i = 0; i < p.length; i++) {
			test = this.equals(p[i]);
			if (!test) {
				Nforce += this.calcForceExertedByY(p[i]);
			}
		}
		return Nforce;
	}

	// Next, you’ll add a method that determines how much the forces exerted on the
	// planet will cause that planet to accelerate, and the resulting change in the
	// planet’s velocity and position in a small period of time dt.
	
	public void update(double dt, double Fx, double Fy) {
		double xA = Fx/mass;
		double yA = Fy/mass;
		
		xxVel = xxVel + dt*xA;
		yyVel = yyVel + dt*yA;
		
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
		
		
	}
	
	 public void draw() {
		 
	        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	    }
	
	
	
	
	
	
	
	

}
