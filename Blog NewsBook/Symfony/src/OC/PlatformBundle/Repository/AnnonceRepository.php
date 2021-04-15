<?php
// src/OC/PlatformBundle/Repository/AnnonceRepository.php

namespace OC\PlatformBundle\Repository;

use Doctrine\ORM\EntityRepository;
use Doctrine\ORM\QueryBuilder;
use Doctrine\ORM\Tools\Pagination\Paginator;

use OC\PlatformBundle\Entity\Annonce;

//classe où on définit toutes les fonctions qu'on appelera dans l'entité l'Annoce
class AnnonceRepository extends EntityRepository
{
  
  public function getSousRubriqueDivertissementMusique()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Musique' AND r.id= 1) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  
  public function getSousRubriqueDivertissementSortie()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Sortie' AND r.id= 1) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  
  public function getSousRubriqueDivertissementSpectacle()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Spectacle' AND r.id= 1) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  
  public function getSousRubriqueCultureConcert()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Concert' AND r.id= 2) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  
  public function getSousRubriqueCultureEvenement()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Evènement' AND r.id= 2) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  
  public function getSousRubriqueCultureActualite()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Actualité' AND r.id= 2) ");
  
  $results =$query->getResult();
  
  return $results; 
  }

  public function getSousRubriquePolitiqueGouvernementale()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Gouvernementale' AND r.id= 3) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  

  public function getSousRubriquePolitiqueAutres()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Autres' AND r.id= 3) ");

  $results =$query->getResult();
  
  return $results; 
  }
  
  public function getSousRubriqueSportBasket()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Basket' AND r.id= 4) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  
  
  public function getSousRubriqueSportFootball()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Football' AND r.id= 4) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  

  public function getSousRubriqueScienceInformatique()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Informatique' AND r.id= 5) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  

  public function getSousRubriqueScienceTechnologie()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Technologie' AND r.id= 5) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
   
   
  public function getSousRubriqueScienceBiologie()
  {
   $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Biologie' AND r.id= 5) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
   
  
  public function getSousRubriqueSportTennis()
  {
	 $query=$this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.Sousrubriques s WHERE s.name IN (SELECT t.name FROM OCPlatformBundle:Sousrubrique t JOIN t.rubrique r WHERE t.name = 'Tennis' AND r.id= 4) ");
  
  $results =$query->getResult();
  
  return $results; 
  }
  
  
 public function getAnnonceRubriqueScience()
  {
    
  $query = $this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.rubrique r WHERE r.name = 'Science '");
  $results = $query->getResult();

  return $results;
  }
  
 

  //fonction qui nous permet de récupérer toutes les annonces de la rubrique Politique
 public function getAnnonceRubriquePolitique()
  {
    
  $query = $this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.rubrique r WHERE r.name = 'Politique '");
  $results = $query->getResult();

  return $results;
  }
  
  //fonction qui nous permet de récupérer toutes les annonces de la rubrique Sport
public function getAnnonceRubriqueSport()
  {
    
  $query = $this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.rubrique r WHERE r.name = 'Sport '");
  $results = $query->getResult();

  return $results;
  }
  
 //fonction qui nous permet de récupérer toutes les annonces de la rubrique Culture
public function getAnnonceRubriqueCulture()
  {
    
  $query = $this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.rubrique r WHERE r.name = 'Culture '");
  $results = $query->getResult();

  return $results;
  }
  
 //fonction qui nous permet de récupérer toutes les annonces de la rubrique  Divertissement
public function getAnnonceRubriqueDivetissement()
  {
    
  $query = $this->_em->createQuery("SELECT a FROM OCPlatformBundle:Annonce a JOIN a.rubrique r WHERE r.name = 'Divertissement '");
  $results = $query->getResult();

  return $results;

  }


  //fonction qui nous permet de récupérer toutes les annonces publiées par l'utilisateur courant
  public function getAnnoncesUtilisateurCourant($username)
  {
    $query = $this->createQueryBuilder('a')
      ->leftJoin('a.user', 'u')
      ->addSelect('u')
      ->andWhere('u.username = :username')
      ->setParameter('username', $username)
      
      ->getQuery()
      ->getResult()
    ;
   return $query;
  }


//récupérer les nouvelles en fonction des plus commentés
  public function getAnnonceParCommentaire()
  {
    
  //$query = $this->_em->createQuery("SELECT thread.id FROM OCPlatformBundle:Thread t orderBy t.numComments DESC");
  
  $query = $this->createQueryBuilder('a');
  $query 
         ->leftJoin('a.thread', 't', 'with {t.id: a.id}')    
      //->addSelect('a')
       //->andWhere('a.id = t.id')
        ->orderBy('t.numComments', 'DESC')
     ;

  return $query 
    ->getQuery()
    ->getResult()
  ;
  }

//fonctions qui nous permet de récupérer toutes les annonces avec l'image, la rubrique, sours ribrique, utilisatuers correspondants
public function getAnnonces($page, $nbPerPage)
  {
    $query = $this->createQueryBuilder('a')
      ->leftJoin('a.image', 'i')
      ->addSelect('i')
      ->leftJoin('a.rubrique', 'c')
      ->addSelect('c')
      ->leftJoin('a.user', 'u')
      ->addSelect('u')
      ->orderBy('a.date', 'DESC')
      ->getQuery()
    ;

    $query
      // On définit l'annonce à partir de laquelle commencer la liste
      ->setFirstResult(($page-1) * $nbPerPage)
      // Ainsi que le nombre d'annonce à afficher sur une page
      ->setMaxResults($nbPerPage)
    ;

    // Enfin, on retourne l'objet Paginator correspondant à la requête construite
    // (n'oubliez pas le use correspondant en début de fichier)
    return new Paginator($query, true);
  }

}
