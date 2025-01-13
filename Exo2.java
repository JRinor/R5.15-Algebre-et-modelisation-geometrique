// Trame des TP d'AMG : transformations geometriques
// BUT Info - 2024/2025
// Preparateur: P. Even, Universite de Lorraine / IUT de Saint-Die
// Complete par :
//
// Objectif : programer le deplacement de la cabine de telepherique
//   pour suivre le fil de sa position de depart a sa position d'arrivee.
//   Le mouvement est declenche par la barre d'espacement.
//   Il doit s'arreter a l'arrivee et reprendre depuis le point de depart
//   apres un nouvel appui de la barre d'espacement.
//
// Mise en oeuvre : idem Exo1, a savoir :
//   Completer la methode "nextStep" appelee en tache de
//   fond par l'application. Cette methode doit mettre dans le parametre
//   "pose" les positions successives de la cabine. Une position est codee
//   sous la forme d'une matrice homogene 4x4 avec une convention vecteur
//   colonne.
//   Le parametre "pose" vehicule les termes ligne par ligne de la matrice. 
//   Quand la methode "nextStep" retourne "FAUX", le mouvement s'arrete.
//
// Geometrie de la scene :
//        Methodes d'instance de la classe RopewayScene
//   - float cableHeight () : hauteur du cable
//   - float[] cableStart () : position de depart (tableau de 2 float)
//   - float[] cableInter () : point de passage (tableau de 2 float)
//   - float cableLength () : longueur du cable
//   - int ropewaySpeed () : valeur recommandee pour l'increment de mouvement
//
// Precaution : reduire les risques de declenchement du garbage collector
//



/** Second training session on geometric transforms. */
public class Exo2 extends RopewayScene
{
  /** Ropeway run length. */
  private float distance = 0.0f;
  /** Cosine of the cable angle. */
  private float cosTheta = 1.0f;
  /** Sine of the cable angle. */
  private float sinTheta = 0.0f;


  /** Returns the next animation step pose.
   * @param pose Already allocated vector to fill in with new pose values.
   */
  public boolean nextStep (float[] pose)
  {
    // A MODIFIER
    pose[11] = 0.5f;
    return (false);
  }


  /** Constructs the 3D scene used for training.
   * Prepares the intermediate storage objects.
   * @param p1 Type of the loaded scene.
   * @param p2 Parameters of the loaded scene.
   */
  private Exo2 (int p1, String[] p2)
  {
    super (p1, true, p2);

    // A COMPLETER
  }


  /** Runs a cabin animation.
   * @param args Run arguments.
   */
  public static void main (String[] args)
  {
    new ExoFrame (2, new Exo2 (1, args));
  }
}
