public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double distance;
		distance = Math.sqrt(Math.pow(xxPos-p.xxPos,2) + Math.pow(yyPos-p.yyPos,2));
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double force;
		force = (G * mass * p.mass) / Math.pow(calcDistance(p),2);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double forceX;
		forceX = (p.xxPos - xxPos)/calcDistance(p) * calcForceExertedBy(p);
		return forceX;
	}

	public double calcForceExertedByY(Planet p){
		double forceY;
		forceY = (p.yyPos - yyPos)/calcDistance(p) * calcForceExertedBy(p);
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] ps){
		double totalForceX = 0;
		for(Planet p:ps){
			if(calcDistance(p)!=0){
				totalForceX += calcForceExertedByX(p);
			}
		}
		return totalForceX;
	}

	public double calcNetForceExertedByY(Planet[] ps){
		double totalForceY = 0;
		for(Planet p:ps){
			if(calcDistance(p)!=0){
				totalForceY += calcForceExertedByY(p);
			}
		}
		return totalForceY;
	}

	public void draw(){
		String imageToDraw = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imageToDraw);
	}

	public void update(double dt, double fX, double fY){
		double aX, aY;
		aX = fX / mass;
		aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}
}