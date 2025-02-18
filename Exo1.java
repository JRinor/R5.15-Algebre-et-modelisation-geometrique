// Trame des TP d'AMG : transformations geometriques
// BUT Info - 2024/2025
// Preparateur: P. Even, Universite de Lorraine / IUT de Saint-Die
// Complete par :
//
// Objectif : programer le deplacement de la cabine de telepherique
//   pour suivre le cable de sa position de depart a sa position d'arrivee.
//   Le mouvement est declenche par la barre d'espacement.
//   Il doit s'arreter a l'arrivee et reprendre depuis le point de depart
//   apres un nouvel appui de la barre d'espacement.
//
// Mise en oeuvre : completer la methode "nextStep" appelee en tache de
//   fond par l'application. Cette methode doit mettre dans le parametre
//   "pose" les positions successives de la cabine. Une position est codee
//   sous la forme d'une matrice homogene 4x4 avec une convention vecteur
//   colonne.
//   Le parametre "pose" vehicule les termes ligne par ligne de la matrice. 
//   Quand la methode "nextStep" retourne "FAUX", le mouvement s'arrete. 
//
// Repere de travail :
//   La verticale de la scene correspond a l'axe Z
//   L'axe X est oriente vers l'avant et l'axe Y vers la droite
// Repere de reference de la cabine : sommet de la perche
//   L'origine est le sommet de la perche 
//   L'avant de la cabine est dans la direction OY 
//
//   Geometrie de la scene :
//        Methodes d'instance de la classe RopewayScene
//   - float cableHeight () : hauteur du cableq
//   - float[] cableStart () : position de depart du cable (2 floats)
//   - float cableLength () : longueur du cable
//   - int ropewaySpeed () : valeur recommandee pour l'increment de mouvement
//



/** First training session on geometric transforms. */
public class Exo1 extends RopewayScene
{
  private float distance = 0.0f;



  /** Returns the next animation step pose.
   * @param pose Already allocated vector to fill in with new pose values.
   */
  public boolean nextStep(float[] pose) {

    if (distance >= cableLength()){
      return false;
    }
    
    pose[0] = 1.0f;   
    pose[1] = 0.0f;   
    pose[2] = 0.0f;  
    pose[3] = cableStart()[0] + distance;  
    pose[4] = 0.0f;   
    pose[5] = 1.0f;   
    pose[6] = 0.0f;   
    pose[7] =  cableStart()[1];  
    pose[8] = 0.0f;   
    pose[9] = 0.0f;   
    pose[10] = 1.0f;  
    pose[11] = cableHeight();
    pose[12] = 0.0f;  
    pose[13] = 0.0f;  
    pose[14] = 0.0f;  
    pose[15] = 1.0f;  

    distance += ropewaySpeed();
    return true;
  
  }

  /** Constructs the 3D scene used for training.
   * Prepares the intermediate storage objects.
   * @param p1 Type of the loaded scene.
   * @param p2 Parameters of the loaded scene.
   */
  private Exo1 (int p1, String[] p2)
  {
    super (p1, true, p2);
  }


  /** Runs a cabin animation.
   * @param args Run arguments.
   */
  public static void main (String[] args)
  {
    new ExoFrame (1, new Exo1 (0, args));
  }
}