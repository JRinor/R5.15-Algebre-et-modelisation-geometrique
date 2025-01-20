// Trame des TP d'AMG : algebre et modelisation geometrique
// BUT Info - 2024/2025
// Preparateur: P. Even, Universite de Lorraine / IUT de Saint-Die
// Complete par :
//

import java.text.DecimalFormat;


/** 4x4 homogeneous matrix with column vector convention. */
public class Matrix
{
  /** Internal representation: the terms of the matrix in an array. */
  private float[][] termes = new float[4][4];

  /** Temporary computation storage to avoid garbage collector activation. */
  private static float[][] tmp = new float[4][4];


  /** Builds a unit matrix.
   */
  public Matrix ()
  {
    setIdentity ();
  }

  /** Builds a matrix from another one.
   * @param mat The original matrix.
   */
  public Matrix (Matrix mat)
  {
    set (mat);
  }

  /** Builds a rotation matrix from an angle value.
   * @param axis Possible values : 'X', 'x', 'Y', 'y', 'Z' or 'z'.
   * @param angleDegre Angle value in degrees.
   */
  public Matrix (char axis, float angleDegre)
  {
    set (axis, angleDegre);
  }

  /** Builds a rotation matrix from cosine and sine values.
   * @param axis Possible values : 'X', 'x', 'Y', 'y', 'Z' or 'z'.
   * @param cosAngle Angle cosine value.
   * @param sinAngle Angle sine value.
   */
  public Matrix (char axis, float cosAngle, float sinAngle)
  {
    set (axis, cosAngle, sinAngle);
  }

  /** Builds a translation matrix.
   * @param tx X component of the translation vector.
   * @param ty Y component of the translation vector.
   * @param tz Z component of the translation vector.
   */
  public Matrix (float tx, float ty, float tz)
  {
    set (tx, ty, tz);
  }

  /** Builds a uniform scaling matrix
   * @param scale Scaling factor.
   */
  public Matrix (float scale)
  {
    set (scale);
  }

  /** Builds a matrix from its columns.
   * @param vecX Transformed X reference vector.
   * @param vecY Transformed Y reference vector.
   * @param vecZ Transformed Z reference vector.
   * @param trsl Transformed reference origin point.
   */
  public Matrix (float[] vecX, float[] vecY, float[] vecZ, float[] trsl)
  {
    set (vecX, vecY, vecZ, trsl);
  }

  /** Sets a unit matrix.
   */
  public void setIdentity ()
  {
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++)
        termes[i][j] = ((i == j) ? 1.0f : 0.0f);
  }

  /** Sets the matrix from another one.
   * @param mat The original matrix.
   */
  public void set (Matrix mat)
  {
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++)
        termes[i][j] = mat.termes[i][j];
  }

  public void set(float tx, float ty, float tz) {
    setIdentity();
    termes[0][3] = tx;
    termes[1][3] = ty;
    termes[2][3] = tz;
}

public void set(char axis, float cosAngle, float sinAngle) {
    setIdentity();
    switch (axis) {
        case 'X':
        case 'x':
            termes[1][1] = cosAngle;
            termes[1][2] = -sinAngle;
            termes[2][1] = sinAngle;
            termes[2][2] = cosAngle;
            break;
        case 'Y':
        case 'y':
            termes[0][0] = cosAngle;
            termes[0][2] = sinAngle;
            termes[2][0] = -sinAngle;
            termes[2][2] = cosAngle;
            break;
        case 'Z':
        case 'z':
            termes[0][0] = cosAngle;
            termes[0][1] = -sinAngle;
            termes[1][0] = sinAngle;
            termes[1][1] = cosAngle;
            break;
    }
}

public void set(char axis, float angleDegre) {
    float angleRad = (float) Math.toRadians(angleDegre);
    float cosAngle = (float) Math.cos(angleRad);
    float sinAngle = (float) Math.sin(angleRad);
    set(axis, cosAngle, sinAngle);
}

public void set(float scale) {
    setIdentity();
    termes[0][0] = scale;
    termes[1][1] = scale;
    termes[2][2] = scale;
}

public void mult(Matrix mat) {
    float[][] result = new float[4][4];
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            result[i][j] = 0;
            for (int k = 0; k < 4; k++) {
                result[i][j] += termes[i][k] * mat.termes[k][j];
            }
        }
    }
    termes = result;
}

public void multLeft(Matrix mat) {
    float[][] result = new float[4][4];
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            result[i][j] = 0;
            for (int k = 0; k < 4; k++) {
                result[i][j] += mat.termes[i][k] * termes[k][j];
            }
        }
    }
    termes = result;
}




  /** Sets a matrix from its columns.
   * @param vecX Transformed X reference vector.
   * @param vecY Transformed Y reference vector.
   * @param vecZ Transformed Z reference vector.
   * @param trsl Transformed reference origin point.
   */
  public void set (float[] vecX, float[] vecY, float[] vecZ, float[] trsl)
  {
    termes[0][0] = vecX[0]; termes[1][0] = vecX[1];
      termes[2][0] = vecX[2]; termes[3][0] = 0.0f;
    termes[0][1] = vecY[0]; termes[1][1] = vecY[1];
      termes[2][1] = vecY[2]; termes[3][1] = 0.0f;
    termes[0][2] = vecZ[0]; termes[1][2] = vecZ[1];
      termes[2][2] = vecZ[2]; termes[3][2] = 0.0f;
    termes[0][3] = trsl[0]; termes[1][3] = trsl[1];
      termes[2][3] = trsl[2]; termes[3][3] = 1.0f;
  }


  /** Line by line fills matrix terms in an array.
   * @param array Collecting array.
   */
  public void toArray (float[] array)
  {
    for (int j = 0; j < 4; ++j)
      for (int i = 0; i < 4; ++i) array[j * 4 + i] = termes[j][i];
  }

  /** Gets a character string to represent the matrix.
   * @return a string of characters.
   */
  public String toString ()
  {
    DecimalFormat df = new DecimalFormat ("#0.##");
    String s = new String ();
    for (int i = 0; i < 4; i ++)
    {
      for (int j = 0; j < 4; j ++) s += "\t" + df.format (termes[i][j]);
      s += '\n';
    }
    return s;
  }
}
