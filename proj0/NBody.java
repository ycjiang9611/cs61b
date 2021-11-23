public class NBody {
  public static String imageToDraw = "images/starfield.jpg";

  public static void main(String[] args) {
    //String first = StdIn.readLine();
    double T = Double.parseDouble(args[0]);
    //String second = StdIn.readLine();
    double dt = Double.parseDouble(args[1]);
    //String filename = StdIn.readLine();
    double radius = readRadius(args[2]);
    Planet[] planets = readPlanets(args[2]);
    //StdDraw.picture(0, 0, imageToDraw);
    //StdDraw.show();
    //for (Planet s : p) {
      //s.draw();
    //}
    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.enableDoubleBuffering();
    double t = 0.0;
    while (t < T) {
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      for (int i = 0; i < planets.length; i += 1) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i = 0; i < planets.length; i += 1) {
        planets[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.picture(0, 0, imageToDraw);
      for (Planet s : planets) {
        s.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      t += dt;
    }
    StdAudio.play("audio/2001.mid");
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }

  public static double readRadius(String a) {
    In in = new In(a);
    int numofplanets = in.readInt();
    double rad = in.readDouble();
    return rad;
  }

  public static Planet[] readPlanets(String a) {
    In in = new In(a);
    int numofplanets = in.readInt();
    double rad = in.readDouble();
    Planet[] res = new Planet[numofplanets];
    for (int i = 0; i < numofplanets; i += 1) {
      Planet p = new Planet(in.readDouble(), in.readDouble(),
      in.readDouble(),in.readDouble(), in.readDouble(), in.readString());
      res[i] = p;
    }
    return res;
  }
}
