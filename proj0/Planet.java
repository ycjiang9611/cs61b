public class Planet{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public static final double G = 6.67e-11;

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

  public double calcDistance(Planet p) {
    double res;
    res = Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos) +
          (yyPos - p.yyPos)*(yyPos - p.yyPos));
    return res;
  }

  public double calcForceExertedBy(Planet p) {
    double res;
    res = (G*mass*p.mass)/(calcDistance(p)*calcDistance(p));
    return res;
  }

  public double calcForceExertedByX(Planet p) {
    double res;
    res = calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    return res;
  }

  public double calcForceExertedByY(Planet p) {
    double res;
    res = calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    return res;
  }

  public double calcNetForceExertedByX(Planet[] arr) {
    double res = 0.0;
    for (Planet p : arr) {
      if (this.equals(p)) {
        continue;
      }
      res += calcForceExertedByX(p);
    }
    return res;
  }

  public double calcNetForceExertedByY(Planet[] arr) {
    double res = 0.0;
    for (Planet p : arr) {
      if (this.equals(p)) {
        continue;
      }
      res += calcForceExertedByY(p);
    }
    return res;
  }

  public void update(double dt, double fx, double fy) {
    double accx = fx/mass;
    double accy = fy/mass;
    xxVel += dt*accx;
    yyVel += dt*accy;
    xxPos += dt*xxVel;
    yyPos += dt*yyVel;
  }

  public void draw() {
    //StdDraw.clear();
    //StdDraw.enableDoubleBuffering();
    StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    StdDraw.show();
    //StdDraw.pause(10);
  }
}
