import java.util.Scanner;

public class NBody {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the information");
		double T = in.nextDouble();
		double dt = in.nextDouble();
		String fileName = in.next();
		
		double uniRadius = NBody.readRadius(fileName);
        Planet[] Planets = NBody.readPlanets(fileName);

		
		StdDraw.setScale(-uniRadius, uniRadius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        
        for (Planet var : Planets) {
            var.draw();
        }
        StdDraw.enableDoubleBuffering();
        
        for(double t=0;t<=T;t+=dt){
            //创造xForce和YForce数组
            double[] xForce=new double[Planets.length];
            double[] yForce=new double[Planets.length];
            //计算netForce
            for (int i=0;i<Planets.length;i++){
                xForce[i]=Planets[i].calcNetForceExertedByX(Planets);
                yForce[i]=Planets[i].calcNetForceExertedByY(Planets);
            }

            for (int i=0;i<Planets.length;i++){
                Planets[i].update(dt,xForce[i],yForce[i]);
            }
       
            StdDraw.picture(0,0,"images/starfield.jpg");

            for (int i=0;i<Planets.length;i++){
                Planets[i].draw();
            }

            StdDraw.show();

            StdDraw.pause(10);
        }

 
        /**
         * Print out the final state of the universe when time reaches T
         */
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", uniRadius);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                    Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
 
        }
 
    }

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int number = in.readInt();//the number of planets in each file is different, so we need to read specific numbers
		double radius = in.readDouble();
		Planet[] planets = new Planet[number];
		for (int i = 0; i < number; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return planets;
	}

	public static double readRadius(String filePath) {
		In in = new In(filePath);
		in.readInt();
		double r = in.readDouble();
		return r;

	}

}
