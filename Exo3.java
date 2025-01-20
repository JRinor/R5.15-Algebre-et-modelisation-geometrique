// Trame des TP d'AMG : transformations geometriques
// BUT Info - 2024/2025
// Preparateur: P. Even, Universite de Lorraine / IUT de Saint-Die
// Modifie par :
//
// Objectif : idem Exo2 + mouvement pendulaire :
//   Programer le deplacement de la cabine de telepherique
//   pour suivre le fil de sa position de depart a sa position d'arrivee. 
//   Le mouvement est declenche par la barre d'espacement.
//   Il doit s'arreter a l'arrivee et reprendre depuis le point de depart
//   apres un nouvel appui de la barre d'espacement.
//
// EN PLUS D'EXO2 : Le trajet doit s'accompagner d'un mouvement pendulaire
//   autour du cable sous la forme de deux cycles sinusoidaux d'une
//   amplitude de 40 degres.
//
// Mise en oeuvre : idem Exo2 EN UTILISANT LA CLASSE MATRIX (A COMPLETER)
//   Completer la methode "nextStep" appelee en tache de
//   fond par l'application. Cette methode doit mettre dans le parametre
//   "pose" les positions successives de la cabine. Une position est codee
//   sous la forme d'une matrice homogene 4x4 avec une convention vecteur
//   colonne.
//   Le parametre "pose" vehicule les termes ligne par ligne de la matrice.
//   Quand la methode "nextStep" retourne "FAUX", le mouvement s'arrete.
//
//
// ATTENTION : IL FAUT COMMENCER PAR COMPLETER LA CLASSE MATRICE
//



/** Third training session on geometric transforms. */
public class Exo3 extends RopewayScene
{
  /** Roll motion amplitude (degrees). */
  private final float MAX_ROLL = 40.0f;
  /** Number of roll cycles. */
  private final int NB_CYCLES = 2;

  /** Ropeway run length. */
  private float distance = 0.0f;
  /** Cosine of roll angle. */
  private float cosTheta = 1.0f;
  /** Sine of roll angle. */
  private float sinTheta = 0.0f;

  /** Cabin pose matrix. */
  private Matrix pose = new Matrix();
  /** Translation matrix. */
  private Matrix translation = new Matrix();
  /** Rotation matrix. */
  private Matrix rotation = new Matrix();


  /** Returns the next animation step pose.
   * @param pose Already allocated vector to fill in with new pose values.
   */
  // public boolean nextStep (float[] pose)
  // {
    // A COMPLETER (reprendre l'exo2)
  //  pose[11] = 0.5f;
  //  return (true);
  //}


  public boolean nextStep(float[] pose)
  {

    float newX = cableStart()[0] + distance * cosTheta;
    float newY = cableStart()[1] + distance * sinTheta;

    distance += ropewaySpeed();

    float balanceAngle = MAX_ROLL * (float) Math.sin(2 * Math.PI * distance / cableLength());

    translation.set(newX, newY, cableHeight());


    // pose[0] = cosTheta;
    // pose[1] = -sinTheta;
    // pose[2] = 0.0f;
    // pose[3] = newX;
    // pose[4] = sinTheta;
    // pose[5] = cosTheta;
    // pose[6] = 0.0f;
    // pose[7] = newY;
    // pose[8] = 0.0f;
    // pose[9] = 0.0f;
    // pose[10] = 1.0f;
    // pose[11] = cableHeight();
    // pose[12] = 0.0f;
    // pose[13] = 0.0f;
    // pose[14] = 0.0f;
    // pose[15] = 1.0f;

    

    if (distance >= cableLength()) return (false);


    return true;
  }


  /** Constructs the 3D scene used for the present exercice.
   * Prepares temporary storage for cabin motion computation.
   * @param p1 Type of the loaded scene.
   * @param p2 Parameters of the loaded scene.
   */
  private Exo3 (int p1, String[] p2)
  {
    super (p1, true, p2);


    float[] start = cableStart();
    float[] inter = cableInter();
    float dx = inter[0] - start[0];
    float dy = inter[1] - start[1];
    float length = (float) Math.sqrt(dx * dx + dy * dy);
    cosTheta = dx / length;
    sinTheta = dy / length;

    // Preparation du mouvement (a completer)
  }


  // private Exo2(int p1, String[] p2)
  // {
  //   super(p1, true, p2);

  //   float[] start = cableStart();
  //   float[] inter = cableInter();
  //   float dx = inter[0] - start[0];
  //   float dy = inter[1] - start[1];
  //   float length = (float) Math.sqrt(dx * dx + dy * dy);
  //   cosTheta = dx / length;
  //   sinTheta = dy / length;
  // }

  /** Runs a cabin animation.
   * @param args Run arguments.
   */
  public static void main (String[] args)
  {
    new ExoFrame (3, new Exo3 (1, args));
  }
}
