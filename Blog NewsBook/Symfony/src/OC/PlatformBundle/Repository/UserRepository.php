<?php
// src/OC/PlatformBundle/Repository/CategoryRepository.php

namespace OC\PlatformBundle\Repository;

use Doctrine\ORM\EntityRepository;

class CategoryRepository extends EntityRepository
{
  public function getLikeQueryBuilder($pattern)
  {
    return $this
      ->createQueryBuilder('u')
      ->where('u.username LIKE :pattern')
      ->setParameter('pattern', $pattern)
    ;
  }
}
