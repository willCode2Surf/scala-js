package java
package lang

import scala.scalajs.js

object Math {
  val E: scala.Double = js.Math.E
  val PI: scala.Double = js.Math.PI

  def abs(a: scala.Int) = js.Math.abs(a).toInt
  def abs(a: scala.Long) = js.Math.abs(a).toLong
  def abs(a: scala.Float) = js.Math.abs(a).toFloat
  def abs(a: scala.Double) = js.Math.abs(a).toDouble

  def max(a: scala.Int, b: scala.Int) = js.Math.max(a, b).toInt
  def max(a: scala.Long, b: scala.Long) = js.Math.max(a, b).toLong
  def max(a: scala.Float, b: scala.Float) = js.Math.max(a, b).toFloat
  def max(a: scala.Double, b: scala.Double) = js.Math.max(a, b).toDouble

  def min(a: scala.Int, b: scala.Int) = js.Math.min(a, b).toInt
  def min(a: scala.Long, b: scala.Long) = js.Math.min(a, b).toLong
  def min(a: scala.Float, b: scala.Float) = js.Math.min(a, b).toFloat
  def min(a: scala.Double, b: scala.Double) = js.Math.min(a, b).toDouble

  def ceil(a: scala.Double): scala.Double = js.Math.ceil(a)
  def floor(a: scala.Double): scala.Double = js.Math.floor(a)

  def round(a: scala.Float): scala.Int = js.Math.round(a).toInt
  def round(a: scala.Double): scala.Long = js.Math.round(a).toLong

  def sqrt(a: scala.Double): scala.Double = js.Math.sqrt(a)
  def pow(a: scala.Double, b: scala.Double): scala.Double = js.Math.pow(a, b)

  def exp(a: scala.Double): scala.Double = js.Math.exp(a)
  def log(a: scala.Double): scala.Double = js.Math.log(a)
  def log10(a: scala.Double): scala.Double = log(a) / js.Math.LN10
  def log1p(a: scala.Double): scala.Double = log(a + 1)

  def sin(a: scala.Double): scala.Double = js.Math.sin(a)
  def cos(a: scala.Double): scala.Double = js.Math.cos(a)
  def tan(a: scala.Double): scala.Double = js.Math.tan(a)
  def asin(a: scala.Double): scala.Double = js.Math.asin(a)
  def acos(a: scala.Double): scala.Double = js.Math.acos(a)
  def atan(a: scala.Double): scala.Double = js.Math.atan(a)
  def atan2(y: scala.Double, x: scala.Double): scala.Double = js.Math.atan2(y, x)

  def random(): scala.Double = js.Math.random()

  def toDegrees(a: scala.Double): scala.Double = a * 180.0 / PI
  def toRadians(a: scala.Double): scala.Double = a / 180.0 * PI

  def signum(a: scala.Double): scala.Double = {
    if (a > 0) 1.0
    else if (a < 0) -1.0
    else a
  }

  def signum(a: scala.Float): scala.Float = {
    if (a > 0) 1.0f
    else if (a < 0) -1.0f
    else a
  }

  def cbrt(a: scala.Double): scala.Double = {
    if (a == 0 || a.isNaN)
      return a

    val sign = if (a < 0.0) -1.0 else 1.0
    val value = sign * a

    //Initial Approximation
    var x = 0.0
    var xi = pow(value, 0.3333333333333333)

    //Halley's Method (http://metamerist.com/cbrt/cbrt.htm)
    while (abs(x - xi) >= 1E-16) {
      x = xi
      val x3 = js.Math.pow(x, 3)
      val x3Plusa = x3 + value
      xi = x * (x3Plusa + value) / (x3Plusa + x3)
    }
    return sign * xi
  }

  def nextUp(a: scala.Double): scala.Double = {
  // js implementation of nextUp https://gist.github.com/Yaffle/4654250
    import scala.Double._
    if (a != a || a == PositiveInfinity)
      a
    else if (a == NegativeInfinity)
      -MaxValue
    else if (a == MaxValue)
      PositiveInfinity
    else if (a == 0)
      MinValue
    else {
      def iter(x: scala.Double, xi: scala.Double, n: scala.Double): scala.Double = {
        if (Math.abs(xi - x) >= 1E-16) {
          val c0 = (xi + x) / 2
          val c =
            if (c0 == NegativeInfinity || c0 == PositiveInfinity)
              x + (xi - x) / 2
            else
              c0
          if (n == c) xi
          else if (a < c) iter(x = x, xi = c, n = c)
          else iter(x = c, xi = xi, n = c)
        }
        else xi
      }
      val d = Math.max(Math.abs(a) * 2E-16, MinValue)
      val ad = a + d
      val xi0 =
        if (ad == PositiveInfinity) MaxValue
        else ad
      iter(x = a, xi = xi0, n = a)
    }
  }

  def nextAfter(a: scala.Double, b: scala.Double): scala.Double = {
    if (b < a)
      -nextUp(-a)
    else if (a < b)
      nextUp(a)
    else if (a != a || b != b)
      scala.Double.NaN
    else
      b
  }

  def ulp(a: scala.Double): scala.Double = {
    if (abs(a) == scala.Double.PositiveInfinity)
      scala.Double.PositiveInfinity
    else if (abs(a) == scala.Double.MaxValue)
      pow(2, 971)
    else
      nextAfter(abs(a), scala.Double.MaxValue) - a
  }

  // TODO The methods not available in the JavaScript Math object
}
