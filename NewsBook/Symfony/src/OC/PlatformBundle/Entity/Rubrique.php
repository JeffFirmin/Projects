<?php
// src/OC/PlatformBundle/Entity/Rubrique.php

namespace OC\PlatformBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Table(name="oc_Rubriques")
 * @ORM\Entity(repositoryClass="OC\PlatformBundle\Repository\RubriqueRepository")
 */
class Rubrique
{
  /**
   * @ORM\Column(name="id", type="integer")
   * @ORM\Id
   * @ORM\GeneratedValue(strategy="AUTO")
   */
  private $id;

  /**
   * @ORM\Column(name="name", type="string", length=255)
   */
  private $name;

  /*
   * @ORM\OneToMany(targetEntity="OC\PlatformBundle\Entity\Sousrubrique", mappedBy="rubrique")
  */
  private $sousrubriques;
  

  /**
   * @return int
   */
  public function getId()
  {
    return $this->id;
  }

  /**
   * @param string $name
   */
  public function setName($name)
  {
    $this->name = $name;
  }

  /**
   * @return string
   */
  public function getName()
  {
    return $this->name;
  }

    
}
