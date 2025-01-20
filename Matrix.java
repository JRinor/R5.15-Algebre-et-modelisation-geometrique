import java.text.DecimalFormat;

/** 4x4 homogeneous matrix with column vector convention. */
public class Matrix {
    /** Internal representation: the terms of the matrix in an array. */
    private float[][] termes = new float[4][4];

    /** Temporary computation storage to avoid garbage collector activation. */
    private static float[][] tmp = new float[4][4];

    /** Builds a unit matrix. */
    public Matrix() {
        setIdentity();
    }

    /** Sets a unit matrix. */
    public void setIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                termes[i][j] = (i == j) ? 1.0f : 0.0f;
            }
        }
    }

    /** Sets the matrix as a translation matrix.
     * @param tx X component of the translation vector.
     * @param ty Y component of the translation vector.
     * @param tz Z component of the translation vector.
     */
    public void set(float tx, float ty, float tz) {
        setIdentity();
        termes[0][3] = tx;
        termes[1][3] = ty;
        termes[2][3] = tz;
    }

    /** Sets the matrix as a rotation matrix given an axis and cos/sin of an angle.
     * @param axis 'X', 'x', 'Y', 'y', 'Z', or 'z'.
     * @param cosAngle Cosine of the angle.
     * @param sinAngle Sine of the angle.
     */
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
            default:
                throw new IllegalArgumentException("Invalid axis: " + axis);
        }
    }

    /** Sets the matrix as a rotation matrix for a given axis and angle in degrees.
     * @param axis 'X', 'x', 'Y', 'y', 'Z', or 'z'.
     * @param angleDegre Angle in degrees.
     */
    public void set(char axis, float angleDegre) {
        float angleRad = (float) Math.toRadians(angleDegre);
        set(axis, (float) Math.cos(angleRad), (float) Math.sin(angleRad));
    }

    /** Sets the matrix as a uniform scaling matrix.
     * @param scale Scaling factor.
     */
    public void set(float scale) {
        setIdentity();
        termes[0][0] = scale;
        termes[1][1] = scale;
        termes[2][2] = scale;
    }

    /** Multiplies the matrix by another matrix.
     * @param mat Matrix to multiply by (on the right).
     */
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

    /** Multiplies the matrix on the left by another matrix.
     * @param mat Matrix to multiply by (on the left).
     */
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

    /** Gets a string representation of the matrix.
     * @return A string representation of the matrix.
     */
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.##");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                s.append("\t").append(df.format(termes[i][j]));
            }
            s.append('\n');
        }
        return s.toString();
    }
}