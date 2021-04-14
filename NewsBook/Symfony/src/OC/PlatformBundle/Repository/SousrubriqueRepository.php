<?php
// src/OC/PlatformBundle/Repository/SousrubriqueRepository.php

namespace OC\PlatformBundle\Repository;

use Doctrine\ORM\EntityRepository;

class SousrubriqueRepository extends EntityRepository
{
  public function getLikeQueryBuilder($pattern)
  {
    return $this
      ->createQueryBuilder('d')
      ->where('d.name LIKE :pattern')
      ->setParameter('pattern', $pattern)
    ;
  }
  
  public function getElementById($pattern)
  {
	  return $this
	  ->createQueryBuilder('d')
	  ->where('d.rubrique_id LIKE : pattern')
	  ->setParameter('pattern',$pattern);
	  
  }
}
