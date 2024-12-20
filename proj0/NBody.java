public class NBody{

	public static double readRadius(String data){
		In in = new In(data);

		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();

		return secondItemInFile;
	}

	public static Planet[] readPlanets (String data){
		In in = new In(data);

		int n = in.readInt();
		double r = in.readDouble();

		Planet[] ps = new Planet[n];
		for(int i = 0; i < n; i+=1){
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			ps[i] = new Planet(xPos,yPos,xVel,yVel,m,img);
		}

		return ps;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double r = readRadius(filename);
		String imageToDraw = "images/starfield.jpg";
		StdDraw.enableDoubleBuffering();

		StdDraw.setScale(-r,r);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);

		Planet[] ps = readPlanets(filename);
		int n = ps.length;
		for(Planet p:ps){
			p.draw();
		}

		int t = 0;
		while(t < T){
			double[] xForce = new double[n];
			double[] yForce = new double[n];

			for(int i = 0; i < n; i+=1){
				xForce[i] = ps[i].calcNetForceExertedByX(ps);
				yForce[i] = ps[i].calcNetForceExertedByY(ps);
			}

			for(int i = 0; i < n; i+=1){
				ps[i].update(dt,xForce[i],yForce[i]);
			}

			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);
			for(Planet p:ps){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			t += dt;
		}
		
		StdOut.printf("%d\n", ps.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < ps.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
		                  ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
		}
	}
}