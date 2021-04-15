<?php

// src/OC/PlatformBundle/Controller/AnnonceController.php
//{% include '@FOSComment/Thread/async.html.twig' with {'id': 'foo'} %};

namespace OC\PlatformBundle\Controller;

use OC\PlatformBundle\Entity\Annonce;
use OC\PlatformBundle\Entity\Rubrique;
use OC\PlatformBundle\Entity\Sousrubrique;
use OC\PlatformBundle\Form\AnnonceEditType;
use OC\PlatformBundle\Form\AnnonceType;
use OC\PlatformBundle\Form\UserType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\isXmlHttpRequest;
use Symfony\Component\HttpFoundation\RequestStack;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Symfony\Component\Security\Core\Exception\AccessDeniedException;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;
use Doctrine\ORM\QueryBuilder;


class AnnonceController extends Controller
{
  public function indexAction($page)
  {
    if ($page < 1) {
      throw new NotFoundHttpException('Page "'.$page.'" inexistante.');
    }

    // Ici on fixe le nombre d'annonces par page à 5
    $nbPerPage = 5;

    // On récupère notre objet Paginator
    $listAnnonces = $this->getDoctrine()
      ->getManager()
      ->getRepository('OCPlatformBundle:Annonce')
      ->getAnnonces($page, $nbPerPage)
    ;

    // On calcule le nombre total de pages grâce au count($listAnnonces) qui retourne le nombre total d'annonces
    $nbPages = ceil(count($listAnnonces) / $nbPerPage);

    // Si la page n'existe pas, on retourne une 404
    if ($page > $nbPages) {
      throw $this->createNotFoundException("La page ".$page." n'existe pas.");
    }

    // On donne toutes les informations nécessaires à la vue
    return $this->render('OCPlatformBundle:Annonce:index.html.twig', array(
      'listAnnonce' => $listAnnonces,
      'nbPages'     => $nbPages,
      'page'        => $page,
    ));
  }


 // la fonction qui gère toutes les actions concernat la vue
  public function viewAction($id)
  {
    $em = $this->getDoctrine()->getManager();

    // Pour récupérer une seule annonce, on utilise la méthode find($id)
    $annonce = $em->getRepository('OCPlatformBundle:Annonce')->find($id);

    // annonce est donc une instance de OC\PlatformBundle\Entity\Annonce
    // ou null si l'id $id n'existe pas, d'où ce if :
    if (null === $annonce) {
      throw new NotFoundHttpException("L'annonce d'id ".$id." n'existe pas.");
    }

    return $this->render('OCPlatformBundle:Annonce:view.html.twig', array(
      'annonce'           => $annonce,
    ));
  }

   /*
   * @Security("has_role('ROLE_ADMIN')")
   */

   //la fonction d'ajout du formulaire
  public function addAction(Request $request)
  {
  
    $annonce = new Annonce();
    
    //on récupère l'utilisateur cournant
    $user = $this->getUser();
    //on lie l'utilisateur courant à l'annonce créée
    $annonce->setUser($user);

    // Ici l'utilisateur a les droits suffisant,
    // on peut ajouter une annonce

    $form = $this->get('form.factory')->create(AnnonceType::class, $annonce);

    // Ici l'utilisateur a les droits suffisant,
    // on peut ajouter une annonce

    if ($request->isMethod('POST') && $form->handleRequest($request)->isValid()) {
      $em = $this->getDoctrine()->getManager();
      $em->persist($annonce);
      $em->flush();

      $request->getSession()->getFlashBag()->add('notice', 'Annonce bien enregistrée.');

      return $this->redirectToRoute('oc_platform_view', array('id' => $annonce->getId()));
    }

    return $this->render('OCPlatformBundle:Annonce:add.html.twig', array(
       'annonce' => $annonce,
      'form' => $form->createView(),
    ));
  } 
  
 
  //récupurer les publications de l'utilisateur courant
   public function annonceutilateurAction()
   {

    $annonce = new Annonce();
    //on récupère l'utilisateur cournant
    $usr = $this->getUser();

    $user = $usr->getUsername();
    //on lie l'utilisateur courant à l'annonce créée
    $annonce->setUser($usr);
  
      //on récupère les publications de l'utilisateur courant
      $listAnnonces = $this->getDoctrine()
      ->getManager()
      ->getRepository('OCPlatformBundle:Annonce')
      //->getAnnoncesUtilisateurCourant()
      ->getAnnoncesUtilisateurCourant($user)
    ;

       return $this->render('OCPlatformBundle:Annonce:annonceutilisateur.html.twig', array(
      'listAnnonce' => $listAnnonces,
      'annonce ' => $annonce,
      
    ));
    
   }


  //Récupérer toutes les annonces qui parlent de politque
  public function tagpolitqueAction(Request $request)
  {
    //on récupère les annoces à l'aide de la fonction getAnnonceRubriquePolitique qui se trouve dans le repository de l'annonce
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getAnnonceRubriquePolitique();

   //on lie avec la fonction getTags du bundle TagBundle
    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    return $this->render('OCPlatformBundle:Annonce:tagpolitique.html.twig',array(
        'annonces' => $annonces
    ));
  }

  
  //Récupérer toutes les annonces qui parlent de la culutre
  public function tagcultureAction(Request $request)
  {
    //on récupère les annoces à l'aide de la fonction getAnnonceRubriqueCulture qui se trouve dans le repository de l'annonce
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getAnnonceRubriqueCulture();

   //on lie avec la fonction getTags du bundle TagBundle
    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //on redirige vers le fichier pour la vue
    return $this->render('OCPlatformBundle:Annonce:tagculture.html.twig',array(
        'annonces' => $annonces
    ));
  }

 
  //Récupérer toutes les annonces qui parlent de Sport
  public function tagsportAction(Request $request)
  {
    //on récupère les annoces à l'aide de la fonction getAnnonceRubriqueSport qui se trouve dans le repository de l'annonce
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getAnnonceRubriqueSport();

        
    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:tagsport.html.twig',array(
        'annonces' => $annonces
    ));
  }

  
   //Récupérer toutes les annonces qui parlent de Divertissement
  public function tagdivertissementAction(Request $request)
   {
    //on récupère les annoces à l'aide de la fonction getAnnonceRubriqueDivetissement qui se trouve dans le repository de l'annonce
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getAnnonceRubriqueDivetissement();

    
    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }
    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:tagdivertissement.html.twig',array(
        'annonces' => $annonces
    ));
  }

 
 //Récupérer toutes les annonces qui parlent de Science
 public function tagscienceAction(Request $request)
  {
     //on récupère les annoces à l'aide de la fonction getAnnonceRubriqueScience qui se trouve dans le repository de l'annonce
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getAnnonceRubriqueScience();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

   //la rédirection
    return $this->render('OCPlatformBundle:Annonce:tagscience.html.twig',array(
        'annonces' => $annonces
    ));
  }

  
  public function sousrubriquemusiqueAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueDivertissementMusique();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquemusique.html.twig',array(
        'annonces' => $annonces
    ));
  }
  
  public function sousrubriquesortieAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueDivertissementSortie();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquesortie.html.twig',array(
        'annonces' => $annonces
    ));
  }
  
  
  public function sousrubriquespectacleAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueDivertissementSpectacle();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }
     //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquespectacle.html.twig',array(
        'annonces' => $annonces
    ));
  }
  
  
    public function sousrubriqueevenementAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueCultureEvenement();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

  //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriqueevenement.html.twig',array(
        'annonces' => $annonces
    ));
  } 
    
  public function sousrubriqueconcertAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueCultureConcert();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }
     //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriqueconcert.html.twig',array(
        'annonces' => $annonces
    ));
  }
   
  public function sousrubriqueactualiteAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueCultureActualite();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

   //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriqueactualite.html.twig',array(
        'annonces' => $annonces
    ));
  }
  
  public function sousrubriquegouvernementaleAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriquePolitiqueGouvernementale();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquegouvernementale.html.twig',array(
        'annonces' => $annonces
    ));
  }
  
public function sousrubriqueautresAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriquePolitiqueAutres();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

   //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriqueautres.html.twig',array(
        'annonces' => $annonces
    ));
  }

public function sousrubriquebasketAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueSportBasket();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

   //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquebasket.html.twig',array(
        'annonces' => $annonces
    ));
  }  
  
 public function sousrubriquefootballAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueSportFootball();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquefootball.html.twig',array(
        'annonces' => $annonces
    ));
  } 

public function sousrubriquetennisAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueSportTennis();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquetennis.html.twig',array(
        'annonces' => $annonces
    ));
  } 
  
public function sousrubriqueinformatiqueAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueScienceInformatique();

    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriqueinformatique.html.twig',array(
        'annonces' => $annonces
    ));
  } 
  
public function sousrubriquetechnologieAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueScienceTechnologie();

  
    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

   //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquetechnologie.html.twig',array(
        'annonces' => $annonces
    ));
  } 
  
public function sousrubriquebiologieAction(Request $request)
  {
    $em = $this->getDoctrine()->getManager();
    $annonces = $em->getRepository('OCPlatformBundle:Annonce')->getSousRubriqueScienceBiologie();

   
    foreach ($annonces as $annonce) {
        $annonce->setTags($this->getTags($annonce));
    }

    //la rédirection
    return $this->render('OCPlatformBundle:Annonce:sousrubriquebiologie.html.twig',array(
        'annonces' => $annonces
    ));
  }
  
  //Bundle qui vient du Tagbundle qui permet de récupérer plus facilement  les données de la base de données  
  /**
  * @param Annonce $annonce
  * @return \Doctrine\Common\Collections\ArrayCollection
  */
  public function getTags(Annonce $annonce) {
    $tagManager = $this->get('fpn_tag.tag_manager');
    $tagManager->loadTagging($annonce);

    return $annonce->getTags();
   }



  // Modifier une publication
  public function editAction($id, Request $request)
  {

    $annonce = new Annonce();
    
    //on récupère l'utilisateur cournant
    $user = $this->getUser();
    //on lie l'utilisateur courant à l'annonce créée
    $annonce->setUser($user);


    $em = $this->getDoctrine()->getManager();

    $annonce = $em->getRepository('OCPlatformBundle:Annonce')->find($id);

    if (null === $annonce) {
      throw new NotFoundHttpException("L'annonce d'id ".$id." n'existe pas.");
    }

    $form = $this->get('form.factory')->create(AnnonceEditType::class, $annonce);
    
    if ($request->isMethod('POST') && $form->handleRequest($request)->isValid()) {
      // Inutile de persister ici, Doctrine connait déjà notre annonce
      $em->flush();

      $request->getSession()->getFlashBag()->add('notice', 'Annonce bien modifiée.');

      return $this->redirectToRoute('oc_platform_view', array('id' => $annonce->getId()));
    }

    return $this->render('OCPlatformBundle:Annonce:edit.html.twig', array(
      'annonce' => $annonce,
      'form'   => $form->createView(),
    ));
  }


   //supprimer une publication
  public function deleteAction(Request $request, $id)
  {
    $em = $this->getDoctrine()->getManager();

    $annonce = $em->getRepository('OCPlatformBundle:Annonce')->find($id);

    if (null === $annonce) {
      throw new NotFoundHttpException("L'annonce d'id ".$id." n'existe pas.");
    }

    // On crée un formulaire vide, qui ne contiendra que le champ CSRF
    // Cela permet de protéger la suppression d'annonce contre cette faille
    $form = $this->get('form.factory')->create();

    if ($request->isMethod('POST') && $form->handleRequest($request)->isValid()) {
      $em->remove($annonce);
      $em->flush();

      $request->getSession()->getFlashBag()->add('info', "L'annonce a bien été supprimée.");

      return $this->redirectToRoute('oc_platform_home');
    }
    
    return $this->render('OCPlatformBundle:Annonce:delete.html.twig', array(
      'annonce' => $annonce,
      'form'   => $form->createView(),
    ));
  }

  
 //rendu du menu (visiblilité)
  public function menuAction($limit)
  {
    $em = $this->getDoctrine()->getManager();

    $listAnnonces = $em->getRepository('OCPlatformBundle:Annonce')->findBy(
      array(),                 // Pas de critère
      array('date' => 'desc'), // On trie par date décroissante
      $limit,                  // On sélectionne $limit annonces
      0                        // À partir du premier
    );

    return $this->render('OCPlatformBundle:Annonce:menu.html.twig', array(
      'listAnnonces' => $listAnnonces
    ));
  }
  

  //classer les les annonces en fontion des plus commentée (Marche pas bien)
  public function menucommentsAction($limit)
  {
    $em = $this->getDoctrine()->getManager();

    $listAnnonces = $em->getRepository('OCPlatformBundle:Annonce')->getAnnonceParCommentaire();
   /*->findBy(
      array(),                 // Pas de critère
      array('numComments' => 'desc'), // On trie par commentaire décroissant
      $limit,                  // On sélectionne $limit annonces
      0                        // À partir du premier
    );
   */
    return $this->render('OCPlatformBundle:Annonce:menucomments.html.twig', array(
      'listAnnonces' => $listAnnonces
    ));
  }


  // Facultatif pour tester la purge
  public function purgeAction($days, Request $request)
  {
    // On récupère notre service
    $purger = $this->get('oc_platform.purger.annonce');

    // On purge les annonces
    $purger->purge($days);

    // On ajoute un message flash arbitraire
    $request->getSession()->getFlashBag()->add('info', 'Les annonces plus vieilles que '.$days.' jours ont été purgées.');

    // On redirige vers la page d'accueil
    return $this->redirectToRoute('oc_platform_home');
  }
}

