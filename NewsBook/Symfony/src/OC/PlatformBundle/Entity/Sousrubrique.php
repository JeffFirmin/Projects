<?php
// src/OC/PlatformBundle/Entity/Sousrubrique.php

namespace OC\PlatformBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Table(name="oc_sous_Rubriques")
 * @ORM\Entity(repositoryClass="OC\PlatformBundle\Repository\SousrubriqueRepository")
 */
class Sousrubrique
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

  
  /**
   * @ORM\ManyToOne(targetEntity="OC\PlatformBundle\Entity\Rubrique", inversedBy="sousrubriques")
   */
  private $rubrique;

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

    /**
     * Set rubrique
     *
     * @param \OC\PlatformBundle\Entity\Rubrique $rubrique
     *
     * @return Sousrubrique
     */
    public function setRubrique(\OC\PlatformBundle\Entity\Rubrique $rubrique = null)
    {
        $this->rubrique = $rubrique;

        return $this;
    }

    /**
     * Get rubrique
     *
     * @return \OC\PlatformBundle\Entity\Rubrique
     */
    public function getRubrique()
    {
        return $this->rubrique;
    }
}
