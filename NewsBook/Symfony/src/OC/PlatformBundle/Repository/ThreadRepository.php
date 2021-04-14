<?php
// src/OC/PlatformBundle/Repository/ThreadRepository.php

namespace OC\PlatformBundle\Repository;

use Doctrine\ORM\EntityRepository;
use Doctrine\ORM\QueryBuilder;
use Doctrine\ORM\Tools\Pagination\Paginator;

class ThreadRepository extends EntityRepository
{


 //récupérer les nouvelles en fonction des plus commentés
  public function getAnnonceParCommentaire()
  {
    
  //$query = $this->_em->createQuery("SELECT thread.id FROM OCPlatformBundle:Thread t orderBy t.numComments DESC");
  
  $query = $this->createQueryBuilder('t');
  $query 
         ->leftJoin('t.annonce', 'a')   
      //->addSelect('a')
         //->andWhere('t.id = a.id')
         ->orderBy('t.numComments', 'DESC')
      //->getQuery()
     ;
  //$results = $query->getResult(); 

  //$results = $query;

  //return $results;

  return $query 
    ->getQuery()
    ->getResult()
  ;

  
  }

 
}
