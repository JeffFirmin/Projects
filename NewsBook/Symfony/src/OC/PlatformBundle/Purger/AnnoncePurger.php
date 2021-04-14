<?php
// src/OC/PlatformBundle/Purger/AdvertPurger.php

namespace OC\PlatformBundle\Purger;

use Doctrine\ORM\EntityManagerInterface;

class AnnoncePurger
{
  /**
   * @var EntityManagerInterface
   */
  private $em;

  // On injecte l'EntityManager
  public function __construct(EntityManagerInterface $em)
  {
    $this->em = $em;
  }

  public function purge($days)
  {
    $anonceRepository      = $this->em->getRepository('OCPlatformBundle:Annonce');

    // date d'il y a $days jours
    $date = new \Datetime($days.' days ago');

    // On récupère les annonces à supprimer
    $listAnnonces = $annonceRepository->getAnnoncesBefore($date);

    // Et on n'oublie pas de faire un flush !
    $this->em->flush();
  }
}
